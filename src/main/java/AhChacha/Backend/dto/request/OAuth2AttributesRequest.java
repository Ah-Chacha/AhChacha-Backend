package AhChacha.Backend.dto.request;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.oauth2.userinfo.GoogleOAuth2UserInfo;
import AhChacha.Backend.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Map;

@Getter
public class OAuth2AttributesRequest {

    private String nameAttributeKey;
    private OAuth2UserInfo oAuth2UserInfo;

    @Builder
    public OAuth2AttributesRequest(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuth2AttributesRequest of(Platform platform, String userNameAttributeKey, Map<String, Object> attributes) {
        /*if (platform == Platform.GOOGLE) {
            return ofGoogle(userNameAttributeKey, attributes);
        }*/
        System.out.println("platform = " + platform);
        return ofGoogle(userNameAttributeKey, attributes);
    }


    public static OAuth2AttributesRequest ofGoogle(String userNameAttributeKey, Map<String, Object> attributes) {
        System.out.println("userNameAttributeKey = " + userNameAttributeKey);
        return OAuth2AttributesRequest.builder()
                .nameAttributeKey(userNameAttributeKey)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public Member toMember(Platform platform) {
        System.out.println("platform = " + platform);
        return new Member(platform,
                oAuth2UserInfo.getId(),
                oAuth2UserInfo.getProfileImageUrl());

    }

}