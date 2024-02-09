package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Sleep;
import AhChacha.Backend.dto.sleep.request.SleepRequest;
import AhChacha.Backend.dto.sleep.response.SleepIdResponse;
import AhChacha.Backend.dto.sleep.response.SleepResponse;
import AhChacha.Backend.dto.sleep.response.SleepsResponse;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.SleepRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.SLEEP_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

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
        return memberRepository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
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
        Sleep sleep = sleepRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SLEEP_NOT_FOUND));
        sleep.update(request);
        return new SleepIdResponse(sleep.getId());
    }


    public void delete(Long id) {
        Sleep sleep = sleepRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(SLEEP_NOT_FOUND));
        sleepRepository.delete(sleep);
    }
}
