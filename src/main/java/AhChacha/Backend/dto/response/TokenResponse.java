package AhChacha.Backend.dto.response;

import AhChacha.Backend.domain.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenResponse {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private String id;
    private Platform platform;
    private Long member_id;
}
