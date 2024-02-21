package AhChacha.Backend.oauth2;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.dto.oauth.response.TokenResponse;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RefreshToken;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import AhChacha.Backend.jwt.TokenProvider;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.RefreshTokenRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.PLATFORM_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;


@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {



    // 클라이언트에서 로그인 완료 이후 authentication 인증 이후 access token 까지 발급 받아야 함 !!!!!!!!!!

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        System.out.println("success");
        try {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            System.out.println("authentication = " + authentication);
            System.out.println("customOAuth2User = " + customOAuth2User);
            System.out.println("customOAuth2User.getName() = " + customOAuth2User.getName());
            System.out.println("request = " + request);
            System.out.println("response = " + response);

            Platform platform = customOAuth2User.getPlatform();

            if(customOAuth2User.getRoleType() == RoleType.GUEST) {



                System.out.println("platform = " + platform);


                if (platform == Platform.GOOGLE || platform == Platform.KAKAO) {
                    response.sendRedirect("/auth/sign-up/"+platform.getKey()+"/"+authentication.getName());
                } else if (platform == Platform.NAVER) {
                    System.out.println("!!!!! = ");
                    Map<String, String> map = new HashMap<>();
                    String[] keyValuePairs = authentication.getName().substring(1, authentication.getName().length() - 1).split(", ");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    String id = map.get("id");
                    System.out.println("id = " + id);
                    response.sendRedirect("/auth/sign-up/NAVER/"+id);
                } else throw new NotFoundException(PLATFORM_NOT_FOUND);
                //response.sendRedirect("/auth/sign-up/"+ platform +"/"+authentication.getName());
                System.out.println("/auth/sign-up/"+ platform +"/"+authentication.getName());
                //회원가입 화면으로 redirect
                //refreshTokenRepository.save(refreshToken);
                //System.out.println("result = " + result);

            } else {
                if (platform == Platform.GOOGLE  || platform == Platform.KAKAO) {
                    TokenResponse tokenResponse = loginSuccess(authentication, platform);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    String result = objectMapper.writeValueAsString(tokenResponse);
                    response.getWriter().write(result);
                } else if (platform == Platform.NAVER) {
                    Map<String, String> map = new HashMap<>();
                    String[] keyValuePairs = authentication.getName().substring(1, authentication.getName().length() - 1).split(", ");
                    for (String pair : keyValuePairs) {
                        String[] entry = pair.split("=");
                        map.put(entry[0], entry[1]);
                    }
                    String id = map.get("id");
                    System.out.println("id = " + id);
                    TokenResponse tokenResponse = loginSuccessForNaver(authentication, id);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    String result = objectMapper.writeValueAsString(tokenResponse);
                    response.getWriter().write(result);
                } else throw new NotFoundException(PLATFORM_NOT_FOUND);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //dto를 리턴하고 싶은데... redirect를 여기서 하려면.. response 함수를 써야하는듯..
    @Transactional
    public TokenResponse loginSuccess(Authentication authentication, Platform platform) {
        Member member = memberRepository.findByPlatformAndPlatformId(platform, authentication.getName())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication, platform, member.getId(), authentication.getName());
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())   //refreshToken = platformId
                .value(tokenResponse.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenResponse;
    }

    @Transactional
    public TokenResponse loginSuccessForNaver(Authentication authentication, String id) {
        Member member = memberRepository.findByPlatformAndPlatformId(Platform.NAVER, id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication, Platform.NAVER, member.getId(), id);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(id)   //refreshToken = platformId
                .value(tokenResponse.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenResponse;
    }




}




