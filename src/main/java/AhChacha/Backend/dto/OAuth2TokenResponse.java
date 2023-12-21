package AhChacha.Backend.dto;

import AhChacha.Backend.domain.Provider;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OAuth2TokenResponse {
    private String access_token;
    private Provider provider;
    private String id;


}