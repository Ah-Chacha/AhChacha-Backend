package AhChacha.Backend.dto.exercise.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequest {
    private int quantity;
    private String type;
}
