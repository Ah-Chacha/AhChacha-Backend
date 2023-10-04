package AhChacha.Backend.controller.dto;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralSignUpDto {

    private String email;
    private String password;
    private String phoneNumber;
    private String nickname;


    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .loginPassword(password)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .roleType(RoleType.USER)
                .build();
    }


}
