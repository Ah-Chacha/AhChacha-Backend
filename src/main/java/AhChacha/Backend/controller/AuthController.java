package AhChacha.Backend.controller;


import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.dto.oauth.request.OAuth2TokenRequest;
import AhChacha.Backend.dto.oauth.request.SignUpRequest;
import AhChacha.Backend.dto.oauth.request.TokenRequest;
import AhChacha.Backend.dto.oauth.response.TokenResponse;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.service.AuthService;
import AhChacha.Backend.service.CustomOAuth2UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MemberRepository memberRepository;


    //FE에서 AccessToken넘김
    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestBody OAuth2TokenRequest oAuth2TokenRequest) {
        String accessToken = oAuth2TokenRequest.getOAuth2AccessToken();
        log.info("accessToken = " + accessToken);
//        memberService.requestUserInfo(accessToken);
        return ResponseEntity.ok(authService.requestUserInfo(accessToken));       // 질문 : 이렇게 호출하면 requestUserInfo를 두번 호출하지 않아?
        //이거 안써여 모바일용
    }


    // 추가정보 입력
    @PostMapping("/sign-up/{platform}/{id}")
    public ResponseEntity<TokenResponse> signUp(@PathVariable("platform") Platform platform,
                                                @PathVariable("id") String id,
                                                @RequestBody @Valid SignUpRequest signUpRequest) throws Exception {
        log.info("[AuthController.signUp - post]");
        return ResponseEntity.ok(authService.signUp(signUpRequest, platform, id));
    }



    @GetMapping("/sign-up/{platform}/{id}")
    public ModelAndView signUp(@PathVariable("platform") Platform platform,
                         @PathVariable("id") String id) throws Exception {
        log.info("[AuthController.signUp - get]");
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/loginSuccess.html");

//        TokenResponse tokenResponse;

        return mav;
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(authService.reissue(tokenRequest));
    }
}
