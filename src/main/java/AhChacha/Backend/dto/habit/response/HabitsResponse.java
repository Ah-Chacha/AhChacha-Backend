package AhChacha.Backend.dto.habit.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HabitsResponse {
    private List<HabitResponse> habitResponses;
}
