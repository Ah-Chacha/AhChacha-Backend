package AhChacha.Backend.controller;


import AhChacha.Backend.controller.dto.SignUpDto;
import AhChacha.Backend.controller.dto.TokenDto;
import AhChacha.Backend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;




}
