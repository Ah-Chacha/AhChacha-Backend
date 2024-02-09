package AhChacha.Backend.dto.oauth.response;

import AhChacha.Backend.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long id;

    public static SignUpResponse of(Member member) {
        return new SignUpResponse(member.getId());
    }
}
