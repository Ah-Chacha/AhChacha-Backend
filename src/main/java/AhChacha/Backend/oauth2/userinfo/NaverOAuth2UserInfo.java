package AhChacha.Backend.oauth2.userinfo;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
        System.out.println("attributes = " + attributes);
    }

    Map<String, Object> response = (Map<String, Object>) attributes.get("response");

    @Override
    public String getId() {
        return (String) response.get("id");
    }

    @Override
    public String getProfileImageUrl() {
        return (String) response.get("profile_image");
    }

    @Override
    public String getName() {
        return (String) response.get("name");
    }

    @Override
    public String getEmail() {
        return (String) response.get("email");
    }
}
