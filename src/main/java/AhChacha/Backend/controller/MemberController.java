package AhChacha.Backend.controller;

import AhChacha.Backend.dto.member.request.MemberRequest;
import AhChacha.Backend.dto.member.response.MemberIdResponse;
import AhChacha.Backend.dto.member.response.MemberResponse;
import AhChacha.Backend.dto.member.response.MyInfoResponse;
import AhChacha.Backend.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/list")
    public ResponseEntity<MemberResponse> getMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    @GetMapping("/me/{memberId}")
    public ResponseEntity<MyInfoResponse> getMyInfo(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMyInfo(memberId));
    }

    @PutMapping("/me/{memberId}")
    public ResponseEntity<MemberIdResponse> updateMember(@PathVariable Long memberId, @RequestBody @Valid MemberRequest memberRequest) {
        return ResponseEntity.ok(memberService.updateMember(memberId, memberRequest));
    }
}
