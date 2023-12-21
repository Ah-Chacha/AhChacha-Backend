package AhChacha.Backend.controller;


import AhChacha.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class MemberController {

    private final MemberService memberService;



    /*@GetMapping("/oauth2/code/google")
    public String successGoogleLogin(@RequestParam("code") String accessCode) {
        System.out.println("accessCode = " + accessCode);
        return accessCode;
    }*/

}