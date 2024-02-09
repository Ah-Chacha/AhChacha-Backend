package AhChacha.Backend.controller;


import AhChacha.Backend.dto.sleep.request.SleepRequest;
import AhChacha.Backend.dto.sleep.response.SleepIdResponse;
import AhChacha.Backend.dto.sleep.response.SleepsResponse;
import AhChacha.Backend.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/sleep")
@RequiredArgsConstructor
@RestController
public class SleepController {

    private final SleepService sleepService;

    @PostMapping("/{memberId}")
    public ResponseEntity<SleepIdResponse> save(@RequestBody SleepRequest sleepRequest,
                                                @PathVariable(value = "memberId") Long id) {
        log.info("[SleepController.save]");
        SleepIdResponse response = sleepService.save(sleepRequest, id);
        return ResponseEntity.ok(response);
    }

    @Comment("수면 기록 리스트 전체 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<SleepsResponse> find(@PathVariable("memberId") Long id) {
        log.info("[SleepController.find] memberId={} ", id);
        SleepsResponse response = sleepService.findAllSleep(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{sleepId}")
    public ResponseEntity<SleepIdResponse> update(@PathVariable("sleepId") Long id,
                                                  @RequestBody SleepRequest sleepRequest) {
        log.info("[SleepController.update]");
        SleepIdResponse response = sleepService.update(id, sleepRequest);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{sleepId}")
    public ResponseEntity<Void> delete(@PathVariable("sleepId") Long id) {
        log.info("[SleepController.delete]");
        sleepService.delete(id);
        return ResponseEntity.ok().build();
    }
}
