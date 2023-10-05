package AhChacha.Backend.controller.dto;

import AhChacha.Backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponseDto {
    private Long id;

    public static SignUpResponseDto of(Member member) {
        return new SignUpResponseDto(member.getId());
    }
}
