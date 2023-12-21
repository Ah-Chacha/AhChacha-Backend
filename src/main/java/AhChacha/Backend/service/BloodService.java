package AhChacha.Backend.service;

import AhChacha.Backend.dto.request.BloodRequest;
import AhChacha.Backend.domain.Blood;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.repository.BloodRepository;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BloodService {
    private final BloodRepository bloodRepository;
    private final MemberRepository memberRepository;

    //등록
    @Transactional
    public String createBlood(BloodRequest bloodRequest, Long id){
        Optional<Member> member = memberRepository.findById(id);
        if(member.isPresent()){
            Member member1 = member.get();
            /**
             * bloodid 생성해서 저장해야한다.
             */

            Blood blood = Blood.builder()
                    .member(member1)
                    .measureTime(bloodRequest.getMeasureTime())
                    .systolicPressure(bloodRequest.getSystolicPressure())
                    .diastolicPressure(bloodRequest.getDiastolicPressure())
                    .heartRate(bloodRequest.getHeartRate())
                    .build();
            bloodRepository.save(blood);
        }else {
            try {
                throw new Exception();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return "혈액 데이터 등록";
    }

    //조회
    public String findBlood(){
        return "혈액 데이터 조회";

    }

    //수정
    public String updateBlood(BloodRequest bloodRequest, Long id){
        return "혈액 데이터 수정";
    }

    //삭제
    public String deleteBlood(Long id, Timestamp time){
        return "혈액 데이터 삭제";
    }
}
