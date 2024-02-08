package AhChacha.Backend.dto.response.sleep;

import AhChacha.Backend.domain.Sleep;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SleepResponse {

    private Long id;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private int quality;

    public static SleepResponse of(Sleep sleep){
        return new SleepResponse(sleep.getId(),
                sleep.getStartTime(),
                sleep.getEndTime(),
                sleep.getQuality());
    }

}
