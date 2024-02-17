package AhChacha.Backend.dto.oauth.request;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.RoleType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @NotNull
    private String roleType;

}
