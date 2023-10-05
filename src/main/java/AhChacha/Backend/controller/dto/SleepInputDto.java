package AhChacha.Backend.controller.dto;


import AhChacha.Backend.domain.Sleep;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SleepInputDto {
    private int startTime;
    private int endTime;
    private int sleep_length;
    private int quality;

}
