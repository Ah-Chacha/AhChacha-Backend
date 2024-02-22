package AhChacha.Backend.dto.member.request;

import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequest {
    private String name;
    private RoleType roleType;
    private String email;
    private String status;
    private String profileImage;
}
