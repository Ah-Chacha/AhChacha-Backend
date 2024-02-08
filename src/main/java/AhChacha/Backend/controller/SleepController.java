package AhChacha.Backend.controller;



import AhChacha.Backend.dto.request.SleepRequest;
import AhChacha.Backend.dto.response.sleep.SleepsResponse;
import AhChacha.Backend.service.MemberService;
import AhChacha.Backend.service.SleepService;
import AhChacha.Backend.dto.response.sleep.SleepIdResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/sleep")
@RequiredArgsConstructor
@RestController
public class SleepController {

    private final SleepService sleepService;

    private final MemberService memberService;

    @PostMapping("/{memberId}")
    public ResponseEntity<SleepIdResponse> save(@RequestBody SleepRequest sleepRequest,
                                                @PathVariable(value = "memberId") Long id) {
        log.info("[SleepController.createSleep]");
        SleepIdResponse response = sleepService.save(sleepRequest, id);
        return ResponseEntity.ok(response);
    }

    // 수면 기록 리스트 전체 조회
    @GetMapping("/{memberId}")
    public ResponseEntity<SleepsResponse> find(@PathVariable("memberId") Long id){
        log.info("[SleepController.findSleep] memberId={} ", id);
        SleepsResponse response = sleepService.findAllSleep(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{sleepId}")
    public ResponseEntity<SleepIdResponse> update(@PathVariable("sleepId") Long id,
                                                  @RequestBody SleepRequest sleepRequest){
        log.info("[SleepController.update]");
        SleepIdResponse response = sleepService.update(id, sleepRequest);
        return ResponseEntity.ok(response);
    }


}
