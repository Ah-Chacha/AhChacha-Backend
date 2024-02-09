package AhChacha.Backend.dto.exercise.response;

import AhChacha.Backend.domain.Exercise;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseResponse {
    private Long id;
    private int quantity;
    private String type;

    public static ExerciseResponse of(Exercise exercise){
        return new ExerciseResponse(exercise.getId(),
                exercise.getQuantity(),
                exercise.getType());
    }
}
