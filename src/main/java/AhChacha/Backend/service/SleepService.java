package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Sleep;
import AhChacha.Backend.dto.request.SleepRequest;
import AhChacha.Backend.dto.response.sleep.SleepIdResponse;
import AhChacha.Backend.dto.response.sleep.SleepResponse;
import AhChacha.Backend.dto.response.sleep.SleepsResponse;
import AhChacha.Backend.exception.notfound.NotFoundMemberException;
import AhChacha.Backend.exception.notfound.NotFoundSleepException;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SleepService {

    private final SleepRepository sleepRepository;
    private final MemberRepository memberRepository;

    public SleepsResponse findAllSleep(Long id) {
        Member member = validateMemberId(id);
        List<Sleep> sleeps = sleepRepository.findAllByMember(member);
        return new SleepsResponse(sleeps.stream().map(SleepResponse::of).toList());
    }

    private Member validateMemberId(Long id) {
        return memberRepository.findById(id).orElseThrow(NotFoundMemberException::new);
    }

    @Transactional
    public SleepIdResponse save(SleepRequest sleepRequest, Long memberId) {
        Member member = validateMemberId(memberId);
        Sleep sleep = Sleep.builder()
                .startTime(sleepRequest.getStartTime())
                .endTime(sleepRequest.getEndTime())
                .quality(sleepRequest.getQuality())
                .member(member)
                .build();
        return new SleepIdResponse(sleepRepository.save(sleep).getId());
    }

    @Transactional
    public SleepIdResponse update(Long id, SleepRequest request) {
        Sleep sleep = sleepRepository.findById(id).orElseThrow(NotFoundSleepException::new);
        sleep.update(request);
        return new SleepIdResponse(sleep.getId());
    }


}
