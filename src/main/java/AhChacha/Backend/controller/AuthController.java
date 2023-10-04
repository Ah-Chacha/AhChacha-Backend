package AhChacha.Backend.controller;

import AhChacha.Backend.controller.dto.*;
import AhChacha.Backend.domain.Provider;
import AhChacha.Backend.repository.MemberRepository;
//import AhChacha.Backend.service.CustomOAuth2UserService;
//import AhChacha.Backend.service.CustomOAuth2UserService;
//import AhChacha.Backend.service.CustomOAuth2UserService;
import AhChacha.Backend.service.CustomOAuth2UserService;
import AhChacha.Backend.service.MemberService;
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
    public ResponseEntity<TokenDto> getAccessToken(@RequestBody OAuth2TokenRequestDto oAuth2TokenRequestDto) {
        String accessToken = oAuth2TokenRequestDto.getOAuth2AccessToken();
        System.out.println("accessToken = " + accessToken);
        //memberService.requestUserInfo(accessToken);
        return ResponseEntity.ok(memberService.requestUserInfo(accessToken));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(memberService.login(memberRequestDto));
    }


    @PostMapping("/sign-up/general")
    public String signUpWithEmail(@RequestBody GeneralSignUpDto generalSignUpDto) {
        memberService.signUpWithEmail(generalSignUpDto);
        return "회원가입 성공";
    }


    /*@PostMapping("/signup/social")
    public ResponseEntity<TokenDto> socialSignUp(@RequestBody SignUpDto signUpDto) {
        return ResponseEntity.ok(memberService.socialSignUp(signUpDto));
    }*/


    @PostMapping("/sign-up/{provider}/{id}")
    public String signUp(@PathVariable("provider") Provider provider, @PathVariable("id") String id, @RequestBody SignUpDto signUpDto) throws Exception {
        memberService.signUp(signUpDto, provider, id);
        System.out.println("provider = " + provider);
        System.out.println("id = " + id);
        return "추가정보 입력 성공";
    }


    @GetMapping("/sign-up/{provider}/{id}")
    public String signUp1(@PathVariable("provider") Provider provider, @PathVariable("id") String id) throws Exception {
        //memberService.signUp(signUpDto, provider, id);
        System.out.println("provider = " + provider);
        System.out.println("id = " + id);
        TokenDto tokenDto;

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
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(memberService.reissue(tokenRequestDto));
    }
}