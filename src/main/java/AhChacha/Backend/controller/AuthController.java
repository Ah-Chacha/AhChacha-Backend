package AhChacha.Backend.controller;


import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.dto.oauth.request.SignUpRequest;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.service.CustomOAuth2UserService;
import AhChacha.Backend.service.MemberService;
import AhChacha.Backend.dto.oauth.request.LoginRequest;
import AhChacha.Backend.dto.oauth.request.OAuth2TokenRequest;
import AhChacha.Backend.dto.oauth.request.TokenRequest;
import AhChacha.Backend.dto.oauth.response.SignUpResponse;
import AhChacha.Backend.dto.oauth.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final MemberService memberService;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MemberRepository memberRepository;





    //FE에서 AccessToken넘김
    @PostMapping("/token")
    public ResponseEntity<TokenResponse> getAccessToken(@RequestBody OAuth2TokenRequest oAuth2TokenRequest) {
        String accessToken = oAuth2TokenRequest.getOAuth2AccessToken();
        log.info("accessToken = " + accessToken);
//        memberService.requestUserInfo(accessToken);
        return ResponseEntity.ok(memberService.requestUserInfo(accessToken));       // 질문 : 이렇게 호출하면 requestUserInfo를 두번 호출하지 않아?
        //이거 안써여 모바일용
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(memberService.login(loginRequest));
    }


    /*@PostMapping("/sign-up/general")
    public ResponseEntity<SignUpResponse> signUpWithEmail(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(memberService.signUpWithEmail(signUpRequest));
    }*/

    /*@PostMapping("/signup/social")
    public ResponseEntity<TokenDto> socialSignUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(memberService.socialSignUp(signUpDto));
    }*/


    //추가정보 입력
    @PostMapping("/sign-up/{provider}/{id}")
    public ResponseEntity<TokenResponse> signUp(@PathVariable("provider") Platform platform, @PathVariable("id") String id, @RequestBody SignUpRequest signUpRequest) throws Exception {
        System.out.println("provider = " + platform);
        System.out.println("id = " + id);
        return ResponseEntity.ok(memberService.signUp(signUpRequest, platform, id));
    }


    @GetMapping("/sign-up/{provider}/{id}")
    public String signUp1(@PathVariable("provider") Platform platform, @PathVariable("id") String id) throws Exception {
        //memberService.signUp(signUpDto, provider, id);
        System.out.println("provider = " + platform);
        System.out.println("id = " + id);
        TokenResponse tokenResponse;

        return "회원가입 성공";
    }


    /*@PostMapping("/login")
    public ResponseEntity<TokenDto> login()*/

    //private final OAuth2Service oAuth2Service;
    /*@GetMapping("/code/google")
    public ResponseEntity<String> successGoogleLogin(@RequestParam("code") String accessCode) {
        return oAuth2Service.getGoogleAccessToken(accessCode);
    }*/


    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestBody TokenRequest tokenRequest) {
        return ResponseEntity.ok(memberService.reissue(tokenRequest));
    }
}
