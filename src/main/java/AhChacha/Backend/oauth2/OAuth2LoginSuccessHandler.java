package AhChacha.Backend.oauth2;

import AhChacha.Backend.dto.oauth.response.TokenResponse;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RefreshToken;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.jwt.TokenProvider;
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


@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {



    // 클라이언트에서 로그인 완료 이후 authentication 인증 이후 access token 까지 발급 받아야 함 !!!!!!!!!!

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        System.out.println("success");
        try {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            System.out.println("authentication = " + authentication);
            if(customOAuth2User.getRoleType() == RoleType.GUEST) {

                //JWT 토큰발급은 추가 정보 입력 폼 이후로
                //authentication 을 인자로 넘길 수가 있나..



                Platform platform = customOAuth2User.getPlatform();
                response.sendRedirect("/auth/sign-up/"+ platform +"/"+authentication.getName());
                System.out.println("/auth/sign-up/"+ platform +"/"+authentication.getName());
                //회원가입 화면으로 redirect
                //refreshTokenRepository.save(refreshToken);
                //System.out.println("result = " + result);

            } else {
                TokenResponse tokenResponse = loginSuccess(response, customOAuth2User, authentication);
                System.out.println("tokenDto = " + tokenResponse);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                String result = objectMapper.writeValueAsString(tokenResponse);
                response.getWriter().write(result);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //dto를 리턴하고 싶은데... redirect를 여기서 하려면.. response 함수를 써야하는듯..
    @Transactional
    public TokenResponse loginSuccess(HttpServletResponse response, CustomOAuth2User customOAuth2User, Authentication authentication) throws IOException {
        //refresh 토큰 확인?? or 로그인 시 마다 토큰 새로 발급?
        TokenResponse tokenResponse = tokenProvider.generateTokenDto(authentication);
        RefreshToken refreshToken = RefreshToken.builder()
                .key(authentication.getName())   //refreshToken = platformId
                .value(tokenResponse.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenResponse;
    }




}




