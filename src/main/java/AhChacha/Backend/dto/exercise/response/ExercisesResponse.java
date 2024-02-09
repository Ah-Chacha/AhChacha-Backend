package AhChacha.Backend.dto.exercise.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExercisesResponse {
    private List<ExerciseResponse> exerciseResponses;
}
