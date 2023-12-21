package AhChacha.Backend.service;

import AhChacha.Backend.dto.SignUpDto;
import AhChacha.Backend.dto.request.LoginRequest;
import AhChacha.Backend.dto.request.SignUpRequest;
import AhChacha.Backend.dto.request.TokenRequest;
import AhChacha.Backend.dto.response.SignUpResponse;
import AhChacha.Backend.dto.response.TokenResponse;
import AhChacha.Backend.domain.*;
import AhChacha.Backend.jwt.TokenProvider;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;



    //토큰 만료 시 처리에 관한 작업 필요함



    @Transactional
    public TokenResponse requestUserInfo(String accessToken) {
        String GOOGLE_USERINFO_REQUEST_URL="https://www.googleapis.com/oauth2/v1/userinfo";


        RestTemplate restTemplate = new RestTemplate();

        //header에 accessToken을 담는다.
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","Bearer "+accessToken);

        //HttpEntity를 하나 생성해 헤더를 담아서 restTemplate으로 구글과 통신하게 된다.
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);
        System.out.println("response = " + response.getBody());

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response.getBody());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        System.out.println("jsonObject = " + jsonObject);

        try {
            String id = (String) jsonObject.get("id");
            String name = (String) jsonObject.get("name");
            String picture = (String) jsonObject.get("picture");
            System.out.println("id = " + id);
            if(memberRepository.findByProviderId(id).isPresent()) {
                Optional<Member> member1 = memberRepository.findByProviderId(id);
                if(member1.isPresent()) {
                    Member member2 = member1.get();
                    TokenResponse tokenResponse = tokenProvider.generateTokenDtoByAuthName(id, Provider.GOOGLE, member2.getId());
                    RefreshToken refreshToken = RefreshToken.builder()
                            .key(id)
                            .value(tokenResponse.getRefreshToken())
                            .build();
                    refreshTokenRepository.save(refreshToken);
                    return tokenResponse;
                } else {
                    try {
                        throw new Exception();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else {
                Member member = Member.builder()     //처음
                        .nickname(name)
//                        .provider(Provider.GOOGLE)
                        .providerId(id)
                        .profileImage(picture)
//                        .roleType(RoleType.GUEST)
                        .build();
                memberRepository.save(member);
                TokenResponse tokenResponse = tokenProvider.generateTokenDtoByAuthName(id, Provider.GOOGLE, member.getId());
                RefreshToken refreshToken = RefreshToken.builder()
                        .key(id)
                        .value(tokenResponse.getRefreshToken())
                        .build();
                refreshTokenRepository.save(refreshToken);
                return tokenResponse;
            }
        } catch (JSONException e) {;
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public SignUpResponse signUp(SignUpDto signUpDto, Provider provider, String id) throws Exception {

        /*
        //JWT 발급
        TokenDto tokenDto = tokenProvider.generateTokenDtoByAuthName(id);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(id)
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);*/


        //이메일 중첩 확인 등등 해야댐

        Optional<Member> member = memberRepository.findByProviderAndProviderId(provider, id);

        if(member.isPresent()) {
            Member member1 = member.get();
            String provider_to_string = "";

            if(provider.toString().equals("GOOGLE")) {
                provider_to_string = "GOOGLE";
            } else if (provider.toString().equals("KAKAO")) {
                provider_to_string = "KAKAO";
            }

            String gender_to_string = "";
            if(signUpDto.getGender().toString().equals("MAN")) {
                gender_to_string = "MAN";
            } else if (signUpDto.getGender().toString().equals("WOMAN")) {
                gender_to_string = "WOMAN";
            }

            memberRepository.updateMember(signUpDto.getAge(), signUpDto.getHeight(), gender_to_string, signUpDto.getWeight(), provider_to_string, id);


            return SignUpResponse.of(member1);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /*String provider_to_string = "";

        if(provider.toString().equals("GOOGLE")) {
            provider_to_string = "GOOGLE";
        } else if (provider.toString().equals("KAKAO")) {
            provider_to_string = "KAKAO";
        }

        String gender_to_string = "";
        if(signUpDto.getGender().toString().equals("MAN")) {
            gender_to_string = "MAN";
        } else if (signUpDto.getGender().toString().equals("WOMAN")) {
            gender_to_string = "WOMAN";
        }

        memberRepository.updateMember(signUpDto.getAge(), signUpDto.getHeight(), gender_to_string, signUpDto.getWeight(), provider_to_string, id);
        //System.out.println("signUpDto = " + signUpDto.getPhoneNumber());


        /*Member member = Member.builder()
                .email(signUpDto.getEmail())
                .roleType(RoleType.USER)
                .phoneNumber(signUpDto.getPhonenumber())
                .build();*/

        //memberRepository.save(member);

        //memberRepository.findByProviderAndProviderId()

        //return null;

    }


    @Transactional
    public TokenResponse reissue(TokenRequest tokenRequest) {    //토큰 재발급
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequest.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequest.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getValue().equals(tokenRequest.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenResponse.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenResponse;
    }

    @Transactional
    public TokenResponse login(LoginRequest tokenRequest) {
        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = tokenRequest.toAuthentication();

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())
                .value(tokenResponse.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);
        // 5. 토큰 발급
        return tokenResponse;
    }

    @Transactional
    public SignUpResponse signUpWithEmail(SignUpRequest signUpRequest) {
        if(memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 가입된 사용자입니다.");
        }

        Member member = signUpRequest.toMember(passwordEncoder);

        return SignUpResponse.of(memberRepository.save(member));
    }

    /*public TokenDto socialSignUp(SignUpDto signUpDto) {


        //JWT 발급
        TokenDto tokenDto = tokenProvider.generateTokenDtoByAuthName(id);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(id)
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);


        //이메일 중첩 확인 등등 해야댐

        Optional<Member> member = memberRepository.findByProviderAndProviderId(provider, id);

        String provider_to_string = "";

        if(provider.toString().equals("GOOGLE")) {
            provider_to_string = "GOOGLE";
        } else if (provider.toString().equals("KAKAO")) {
            provider_to_string = "KAKAO";
        }

        memberRepository.updateMember(signUpDto.getEmail(), signUpDto.getPhoneNumber(), provider_to_string, id);
        System.out.println("signUpDto = " + signUpDto.getPhoneNumber());


        /*Member member = Member.builder()
                .email(signUpDto.getEmail())
                .roleType(RoleType.USER)
                .phoneNumber(signUpDto.getPhonenumber())
                .build();

        //memberRepository.save(member);

        //memberRepository.findByProviderAndProviderId()

        return tokenDto;
    }*/
}