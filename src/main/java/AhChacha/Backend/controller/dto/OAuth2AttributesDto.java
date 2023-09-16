package AhChacha.Backend.controller.dto;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Provider;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.oauth2.userinfo.GoogleOAuth2UserInfo;
import AhChacha.Backend.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;

@Getter
public class OAuth2AttributesDto {

    private String nameAttributeKey;
    private OAuth2UserInfo oAuth2UserInfo;

    @Builder
    public OAuth2AttributesDto(String nameAttributeKey, OAuth2UserInfo oAuth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    public static OAuth2AttributesDto of(Provider provider, String userNameAttributeKey, Map<String, Object> attributes) {
        /*if (platform == Platform.GOOGLE) {
            return ofGoogle(userNameAttributeKey, attributes);
        }*/
        System.out.println("platform = " + provider);
        return ofGoogle(userNameAttributeKey, attributes);
    }

    public static OAuth2AttributesDto ofGoogle(String userNameAttributeKey, Map<String, Object> attributes) {
        System.out.println("userNameAttributeKey = " + userNameAttributeKey);
        return OAuth2AttributesDto.builder()
                .nameAttributeKey(userNameAttributeKey)
                .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                .build();
    }

    public Member toMember(Provider provider, OAuth2UserInfo oAuth2UserInfo) {
        System.out.println("platform = " + provider);
        return Member.builder()
                .provider(provider)
                .providerId(oAuth2UserInfo.getId())
                .email(UUID.randomUUID() + "@socialUser.com")
                .nickname(oAuth2UserInfo.getNickname())
                .profileImage(oAuth2UserInfo.getProfileImageUrl())
                .roleType(RoleType.GUEST)
                .build();
    }

}
