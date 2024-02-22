package AhChacha.Backend.dto.member.response;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MyInfoResponse {
    private Long id;
    private String name;
    private Platform platform;
    private String platformId;
    private RoleType roleType;
    private String email;
    private String status;
    private String profileImage;

    public static MyInfoResponse of(Member member) {
        return new MyInfoResponse(member.getId(), member.getName(), member.getPlatform(), member.getPlatformId(), member.getRoleType(), member.getEmail(), member.getStatus(), member.getProfileImage());
    }
}
