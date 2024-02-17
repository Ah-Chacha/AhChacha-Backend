package AhChacha.Backend.dto.exercise.request;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRequest {
    @PositiveOrZero
    private int quantity;
    private String type;
}
