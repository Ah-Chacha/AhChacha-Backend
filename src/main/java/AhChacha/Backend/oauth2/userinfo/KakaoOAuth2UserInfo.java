package AhChacha.Backend.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProfileImageUrl() {
        return String.valueOf(attributes.get("profile_image"));
    }

    @Override
    public String getName() {
        return String.valueOf(attributes.get("nickname"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("account_email"));
    }
}
