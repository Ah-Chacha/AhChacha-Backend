package AhChacha.Backend.controller;



import AhChacha.Backend.dto.request.SleepRequest;
import AhChacha.Backend.service.MemberService;
import AhChacha.Backend.service.SleepService;
import AhChacha.Backend.dto.response.SleepResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/sleep")
@RequiredArgsConstructor
@RestController
public class SleepController {

    private final SleepService sleepService;

    private final MemberService memberService;

    @PostMapping("/{memberId}")
    public ResponseEntity<SleepResponse> createSleep(@RequestBody SleepRequest sleepRequest, @PathVariable(value = "memberId") Long id) {
        log.info("[SleepController.createSleep]");
        SleepResponse response = sleepService.createSleep(sleepRequest, id);
        return ResponseEntity.ok(response);
    }
}
