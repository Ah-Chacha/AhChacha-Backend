package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.dto.member.request.MemberRequest;
import AhChacha.Backend.dto.member.response.MemberIdResponse;
import AhChacha.Backend.dto.member.response.MemberResponse;
import AhChacha.Backend.dto.member.response.MemberListResponse;
import AhChacha.Backend.dto.member.response.MyInfoResponse;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse getMembers() {
        List<Member> members = memberRepository.findAll();
        List<MemberListResponse> list = members.stream()
                .map(m -> new MemberListResponse(m.getId(), m.getName(), m.getPlatform(), m.getPlatformId(), m.getRoleType()))
                .collect(Collectors.toList());
        return new MemberResponse(list);
    }

    public MyInfoResponse getMyInfo(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        return MyInfoResponse.of(member);
    }

    @Transactional
    public MemberIdResponse updateMember(Long id, MemberRequest memberRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        member.updateMember(memberRequest);
        return new MemberIdResponse(member.getId());
    }
}
