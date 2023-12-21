package AhChacha.Backend.service;

import AhChacha.Backend.dto.request.SleepRequest;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Sleep;
import AhChacha.Backend.exception.BadRequestException;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.SleepRepository;
import AhChacha.Backend.dto.response.SleepResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static AhChacha.Backend.dto.response.status.BaseExceptionResponseStatus.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {

    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public SleepResponse createSleep(SleepRequest sleepRequest, Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isPresent()) {
            Member member1 = member.get();
            Sleep sleep = Sleep.builder()
                    .startTime(sleepRequest.getStartTime())
                    .endTime(sleepRequest.getEndTime())
                    .quality(sleepRequest.getQuality())
                    .memberId(member1)
                    .build();
            return new SleepResponse(sleepRepository.save(sleep).getId());
        }
        throw new BadRequestException(BAD_REQUEST);
    }
}
