package AhChacha.Backend.oauth2;

import AhChacha.Backend.controller.dto.TokenDto;
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
import java.io.PrintWriter;

@Service
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    private ObjectMapper objectMapper = new ObjectMapper();
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("OAuth2 Login Success");
        System.out.println("success");
        try {
            CustomOAuth2User customOAuth2User = (CustomOAuth2User) authentication.getPrincipal();

            if(customOAuth2User.getRoleType() == RoleType.GUEST) {
                TokenDto tokenDto = tokenProvider.generateAccessToken(customOAuth2User.getEmail());
                RefreshToken refreshToken = RefreshToken.builder()
                        .key(customOAuth2User.getEmail())
                        .value(tokenDto.getRefreshToken())
                        .build();
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                String result = objectMapper.writeValueAsString(tokenDto);

                response.sendRedirect("/auth/sign-up");
                response.getWriter().write(result);
                refreshTokenRepository.save(refreshToken);
                System.out.println("result = " + result);

            } else {
                TokenDto tokenDto = loginSuccess(response, customOAuth2User);
                response.setContentType("application/json");
                response.setCharacterEncoding("utf-8");

                String result = objectMapper.writeValueAsString(tokenDto);
                response.getWriter().write(result);
            }
        } catch (Exception e) {
            throw e;
        }
    }

    //dto를 리턴하고 싶은데... redirect를 여기서 하려면.. response 함수를 써야하는듯..
    @Transactional
    private TokenDto loginSuccess(HttpServletResponse response, CustomOAuth2User customOAuth2User) throws IOException {
        //refresh 토큰 확인?? or 로그인 시 마다 토큰 새로 발급?
        TokenDto tokenDto = tokenProvider.generateAccessToken(customOAuth2User.getEmail());
        RefreshToken refreshToken = RefreshToken.builder()
                .key(customOAuth2User.getEmail())
                .value(tokenDto.getRefreshToken())
                .build();
        refreshTokenRepository.save(refreshToken);
        return tokenDto;
    }
}
