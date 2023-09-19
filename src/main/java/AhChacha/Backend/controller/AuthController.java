package AhChacha.Backend.controller;

import AhChacha.Backend.controller.dto.SignUpDto;
import AhChacha.Backend.domain.Provider;
import AhChacha.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final MemberService memberService;

    @PostMapping("/sign-up/{provider}/{id}")
    public String signUp(@PathVariable("provider") Provider provider, @PathVariable("id") String id, @RequestBody SignUpDto signUpDto) throws Exception {
        memberService.signUp(signUpDto, provider, id);
        System.out.println("provider = " + provider);
        System.out.println("id = " + id);
        return "회원가입 성공";
    }

    /*@PostMapping("/login")
    public ResponseEntity<TokenDto> login()*/

    //private final OAuth2Service oAuth2Service;
    /*@GetMapping("/code/google")
    public ResponseEntity<String> successGoogleLogin(@RequestParam("code") String accessCode) {
        return oAuth2Service.getGoogleAccessToken(accessCode);
    }*/


}
