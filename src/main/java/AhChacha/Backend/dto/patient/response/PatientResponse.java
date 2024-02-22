package AhChacha.Backend.dto.patient.response;

import AhChacha.Backend.domain.PatientInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatientResponse {
    private Long id;

    public static PatientResponse of(PatientInfo patientInfo) {
        return new PatientResponse(patientInfo.getId());
    }

}
