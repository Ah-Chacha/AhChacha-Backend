package AhChacha.Backend.dto.sleep.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SleepRequest {
    @Past
    private LocalDateTime startTime;

    @PastOrPresent
    private LocalDateTime endTime;

    @Min(1) @Max(5)
    private int quality;
}
