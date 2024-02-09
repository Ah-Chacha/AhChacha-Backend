package AhChacha.Backend.dto.habit.response;

import AhChacha.Backend.domain.Habit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitResponse {
    private Long id;
    private int foodNum;
    private int alcoholQuantity;
    private int readingTime;

    public static HabitResponse of(Habit habit) {
        return new HabitResponse(habit.getId(),
                habit.getFoodNum(),
                habit.getAlcoholQuantity(),
                habit.getReadingTime());
    }
}