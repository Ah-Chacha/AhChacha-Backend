package AhChacha.Backend.controller;


import AhChacha.Backend.domain.Provider;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.service.CustomOAuth2UserService;
import AhChacha.Backend.service.MemberService;
import AhChacha.Backend.dto.SignUpDto;
import AhChacha.Backend.dto.request.LoginRequest;
import AhChacha.Backend.dto.request.OAuth2TokenRequest;
import AhChacha.Backend.dto.request.SignUpRequest;
import AhChacha.Backend.dto.request.TokenRequest;
import AhChacha.Backend.dto.response.SignUpResponse;
import AhChacha.Backend.dto.response.TokenResponse;
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
        System.out.println("accessToken = " + accessToken);
        //memberService.requestUserInfo(accessToken);
        return ResponseEntity.ok(memberService.requestUserInfo(accessToken));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(memberService.login(loginRequest));
    }


    @PostMapping("/sign-up/general")
    public ResponseEntity<SignUpResponse> signUpWithEmail(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok(memberService.signUpWithEmail(signUpRequest));
    }


    /*@PostMapping("/signup/social")
    public ResponseEntity<TokenDto> socialSignUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(memberService.socialSignUp(signUpDto));
    }*/


    @PostMapping("/sign-up/{provider}/{id}")
    public ResponseEntity<SignUpResponse> signUp(@PathVariable("provider") Provider provider, @PathVariable("id") String id, @RequestBody SignUpDto signUpDto) throws Exception {
        System.out.println("provider = " + provider);
        System.out.println("id = " + id);
        return ResponseEntity.ok(memberService.signUp(signUpDto, provider, id));
    }


    @GetMapping("/sign-up/{provider}/{id}")
    public String signUp1(@PathVariable("provider") Provider provider, @PathVariable("id") String id) throws Exception {
        //memberService.signUp(signUpDto, provider, id);
        System.out.println("provider = " + provider);
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