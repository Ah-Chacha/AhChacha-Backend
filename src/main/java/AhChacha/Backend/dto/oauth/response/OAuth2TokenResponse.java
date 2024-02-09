package AhChacha.Backend.dto.oauth.response;

import AhChacha.Backend.domain.Platform;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2TokenResponse {
    private String access_token;
    private Platform platform;
    private String id;


}