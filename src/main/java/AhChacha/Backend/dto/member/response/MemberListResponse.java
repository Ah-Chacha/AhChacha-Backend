package AhChacha.Backend.dto.member.response;

import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberListResponse {
    private Long id;
    private String name;
    private Platform platform;
    private String platformId;
    private RoleType roleType;
}
