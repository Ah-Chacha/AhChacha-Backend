package AhChacha.Backend.controller.dto;

import AhChacha.Backend.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OAuth2TokenRequestDto {
    private String access_token;

    public String getOAuth2AccessToken() {
        return access_token;
    }


}
