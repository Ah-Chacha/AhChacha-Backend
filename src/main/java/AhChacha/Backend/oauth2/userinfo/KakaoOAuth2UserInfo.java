package AhChacha.Backend.oauth2.userinfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo{

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getProfileImageUrl() {
        return String.valueOf(properties.get("profile_image"));
    }

    @Override
    public String getName() {
        return String.valueOf(properties.get("nickname"));
    }

    @Override
    public String getEmail() {
        return String.valueOf(attributes.get("account_email")); //카카오는 비즈앱 전환해야 이메일이 받아진다고 합니다.
    }
}
