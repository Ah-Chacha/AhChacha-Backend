package AhChacha.Backend.dto.request;


import AhChacha.Backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String email;

    public Member toMember() {
        return Member.builder()
                .email(email)
                .build();
    }


}
