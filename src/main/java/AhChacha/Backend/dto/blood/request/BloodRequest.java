package AhChacha.Backend.dto.blood.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequest {
    private Long id;
    private int systolicPressure;   //수축기 혈압
    private int diastolicPressure;  //이완기 혈압
    private int heartRate;
    private int bloodSugar;
    private int cholesterol;
}
