package AhChacha.Backend.dto.request;


import AhChacha.Backend.domain.Gender;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String email;
    private String password;
    private Timestamp birthday;
    private int weight;
    private int height;
//    private Gender gender;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .loginPassword(password)
                .birthday(birthday)
                .weight(weight)
                .height(height)
//                .gender(gender)
//                .roleType(RoleType.USER)
                .build();
    }


}
