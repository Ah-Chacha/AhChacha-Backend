package AhChacha.Backend.service;

import AhChacha.Backend.controller.dto.SignUpDto;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;


    public void signUp(SignUpDto signUpDto) throws Exception {

        //이메일 중첩 확인 등등 해야댐

        Member member = Member.builder()
                .email(signUpDto.getEmail())
                .roleType(RoleType.USER)
                .phoneNumber(signUpDto.getPhonenumber())
                .build();

        memberRepository.save(member);
    }
}
