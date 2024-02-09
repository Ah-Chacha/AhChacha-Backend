package AhChacha.Backend.dto.habit.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitRequest {
    private int foodNum;
    private int alcoholQuantity;
    private int readingTime;

}
