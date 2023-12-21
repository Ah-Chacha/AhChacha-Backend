package AhChacha.Backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2TokenRequest {
    private String access_token;

    public String getOAuth2AccessToken() {
        return access_token;
    }


}