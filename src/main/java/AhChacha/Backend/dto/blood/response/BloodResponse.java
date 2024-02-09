package AhChacha.Backend.dto.blood.response;

import AhChacha.Backend.domain.Blood;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BloodResponse {
    private Long id;
    private int systolicPressure;
    private int diastolicPressure;
    private int heartRate;
    private int cholesterol;

    public static BloodResponse of(Blood blood) {
        return new BloodResponse(blood.getId(),
                blood.getSystolicPressure(),
                blood.getDiastolicPressure(),
                blood.getHeartRate(),
                blood.getCholesterol());
    }
}
