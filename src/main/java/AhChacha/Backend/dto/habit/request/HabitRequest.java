package AhChacha.Backend.dto.habit.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {
    @Min(0) @Max(20)
    private int foodNum;

    @PositiveOrZero
    private int alcoholQuantity;

    @PositiveOrZero
    private int readingTime;

}
