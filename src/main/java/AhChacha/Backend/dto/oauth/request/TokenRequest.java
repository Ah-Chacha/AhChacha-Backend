package AhChacha.Backend.dto.oauth.request;

import AhChacha.Backend.domain.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequest {

    private String accessToken;
    private String refreshToken;
    private String id;
    private Platform platform;
    private Long member_id;
}
