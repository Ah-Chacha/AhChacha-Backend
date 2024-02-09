package AhChacha.Backend.service;

import AhChacha.Backend.domain.Blood;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.dto.blood.request.BloodRequest;
import AhChacha.Backend.dto.blood.response.BloodIdResponse;
import AhChacha.Backend.dto.blood.response.BloodResponse;
import AhChacha.Backend.dto.blood.response.BloodsResponse;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.repository.BloodRepository;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.BLOOD_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BloodService {
    private final BloodRepository bloodRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public BloodIdResponse save(Long memberId, BloodRequest request) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        Blood blood = Blood.of(member, request);
        return new BloodIdResponse(bloodRepository.save(blood).getId());
    }

    public BloodsResponse findAll(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        List<Blood> bloods = bloodRepository.findAllByMember(member);

        return new BloodsResponse(bloods.stream()
                .map(BloodResponse::of)
                .toList());
    }

    @Transactional
    public BloodIdResponse update(BloodRequest request, Long id) {
        Blood blood = bloodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BLOOD_NOT_FOUND));
        blood.update(request);
        return new BloodIdResponse(blood.getId());
    }

    @Transactional
    public void delete(Long id) {
        Blood blood = bloodRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BLOOD_NOT_FOUND));
        bloodRepository.delete(blood);
    }
}
