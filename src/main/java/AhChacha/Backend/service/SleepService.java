package AhChacha.Backend.service;

import AhChacha.Backend.controller.dto.SleepInputDto;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Sleep;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {


    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;
    @Transactional
    public String inputSleepData(SleepInputDto sleepInputDto, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            Member member1 = member.get();
            Sleep sleep = Sleep.builder()
                    .startTime(sleepInputDto.getStartTime())
                    .endTime(sleepInputDto.getEndTime())
                    .sleep_length(sleepInputDto.getSleep_length())
                    .quality(sleepInputDto.getQuality())
                    .memberId(member1)
                    .build();
            sleepRepository.save(sleep);
        } else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return "수면 데이터 입력";
    }
}
