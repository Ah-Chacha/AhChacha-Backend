package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.PatientInfo;
import AhChacha.Backend.dto.patient.request.PatientRequest;
import AhChacha.Backend.dto.patient.response.PatientResponse;
import AhChacha.Backend.exception.BadRequestException;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import AhChacha.Backend.repository.MemberRepository;
import AhChacha.Backend.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class PatientService {

    private final PatientRepository patientRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public PatientResponse savePatientInfo(Long id, PatientRequest patientRequest) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
        if(patientRepository.findPatientInfoByMemberId(id).isPresent()) {
            log.info("환자 정보가 이미 있습니다.");
            throw new BadRequestException(VALID_EXCEPTION);
        } else {
            PatientInfo patientInfo = patientRequest.toPatientInfo(member);
            return PatientResponse.of(patientRepository.save(patientInfo));
        }
    }
}
