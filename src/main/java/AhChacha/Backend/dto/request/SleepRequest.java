package AhChacha.Backend.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SleepRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int quality;
}
