package AhChacha.Backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BloodRequest {
    private Long id;

    private Timestamp measureTime;

    private int systolicPressure;   //수축기 혈압

    private int diastolicPressure;  //이완기 혈압

    private int heartRate;
}
