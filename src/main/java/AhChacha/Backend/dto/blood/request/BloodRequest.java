package AhChacha.Backend.dto.blood.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequest {
    private Long id;

    @Min(40) @Max(200)
    private int systolicPressure;   //수축기 혈압

    @Min(20) @Max(150)
    private int diastolicPressure;  //이완기 혈압

    @Min(20) @Max(250)
    private int heartRate;

    private int bloodSugar;
    private int cholesterol;
}
