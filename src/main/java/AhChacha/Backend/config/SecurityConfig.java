package AhChacha.Backend.config;


import AhChacha.Backend.jwt.JwtAccessDeniedHandler;
import AhChacha.Backend.jwt.JwtAuthenticationEntryPoint;
import AhChacha.Backend.jwt.TokenProvider;
import AhChacha.Backend.oauth2.OAuth2LoginFailureHandler;
import AhChacha.Backend.oauth2.OAuth2LoginSuccessHandler;
import AhChacha.Backend.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)// FormLogin 사용 X
                .httpBasic(AbstractHttpConfigurer::disable)// httpBasic 사용 X
                .csrf(AbstractHttpConfigurer::disable) // csrf 보안 사용 X
                .headers(httpSecurityHeadersConfigurer -> httpSecurityHeadersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))


                // 세션 사용하지 않으므로 STATELESS로 설정


                //== URL별 권한 관리 옵션 ==//
                .authorizeHttpRequests(authorize -> authorize

                        // 아이콘, css, js 관련
                        // 기본 페이지, css, image, js 하위 폴더에 있는 자료들은 모두 접근 가능, h2-console에 접근 가능
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/", "/css/**", "/images/**", "/js/**", "/favicon.ico", "/h2-console/**", "/profile").permitAll()
                        .requestMatchers("/api/member/sign-up").permitAll() // 회원가입 접근 가능
                        .requestMatchers("/auth/token").permitAll()
                        .anyRequest().permitAll()) // 위의 경로 이외에는 모두 인증된 사용자만 접근 가능
                //== 소셜 로그인 설정 ==//
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuth2LoginSuccessHandler)
                        .failureHandler(oAuth2LoginFailureHandler)
                        .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                                .userService(customOAuth2UserService)))


                .apply(new JwtSecurityConfig(tokenProvider));

                /*.oauth2Login()
                .successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
                .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정
                .userInfoEndpoint().userService(customOAuth2UserService); // customUserService 설정*/

        // 원래 스프링 시큐리티 필터 순서가 LogoutFilter 이후에 로그인 필터 동작
        // 따라서, LogoutFilter 이후에 우리가 만든 필터 동작하도록 설정
        // 순서 : LogoutFilter -> JwtAuthenticationProcessingFilter -> CustomJsonUsernamePasswordAuthenticationFilter
        /*http.addFilterAfter(customJsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);
        http.addFilterBefore(jwtAuthenticationProcessingFilter(), CustomJsonUsernamePasswordAuthenticationFilter.class);*/

        return http.build();
    }


}