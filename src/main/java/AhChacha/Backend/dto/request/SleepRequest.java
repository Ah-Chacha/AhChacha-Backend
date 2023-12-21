package AhChacha.Backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SleepRequest {
    private Timestamp startTime;
    private Timestamp endTime;
    private int quality;
//    private String note;

}
