package AhChacha.Backend.controller;



import AhChacha.Backend.controller.dto.SleepInputDto;
import AhChacha.Backend.service.MemberService;
import AhChacha.Backend.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/sleep")
@RequiredArgsConstructor
@RestController
public class SleepController {

    private final SleepService sleepService;

    private final MemberService memberService;

    @PostMapping("/input")
    public String inputSleep(@RequestBody SleepInputDto sleepInputDto, @RequestParam("memberId") Long id) {
        System.out.println("sleepInputDto = " + sleepInputDto);
        sleepService.inputSleepData(sleepInputDto, id);
        return "수면데이터 입력";
    }
}
