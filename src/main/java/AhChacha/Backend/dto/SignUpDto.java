package AhChacha.Backend.dto;

import AhChacha.Backend.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {

    private int age;
    private int weight;
    private int height;
    private Gender gender;
}
