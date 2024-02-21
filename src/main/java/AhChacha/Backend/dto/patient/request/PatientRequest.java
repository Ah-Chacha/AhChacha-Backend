package AhChacha.Backend.dto.patient.request;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.PatientInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
    private int weight;
    private int height;
    private LocalDate birthday;
    private String phoneNumber;

    public PatientInfo toPatientInfo(Member member) {
        return PatientInfo.builder()
                .member(member)
                .weight(weight)
                .height(height)
                .birthday(birthday)
                .phoneNumber(phoneNumber)
                .build();
    }
}
