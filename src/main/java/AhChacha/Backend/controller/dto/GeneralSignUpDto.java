package AhChacha.Backend.controller.dto;


import AhChacha.Backend.domain.Gender;
import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GeneralSignUpDto {

    private String email;
    private String password;
    private int age;
    private int weight;
    private int height;
    private Gender gender;


    public Member toMember(PasswordEncoder passwordEncoder) {
        Timestamp createTime = new Timestamp(System.currentTimeMillis());
        return Member.builder()
                .email(email)
                .loginPassword(password)
                .age(age)
                .weight(weight)
                .height(height)
                .gender(gender)
                .roleType(RoleType.USER)
                .createDate(createTime)
                .build();
    }


}
