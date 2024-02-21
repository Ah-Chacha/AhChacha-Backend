package AhChacha.Backend.dto.oauth.request;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import AhChacha.Backend.oauth2.userinfo.GoogleOAuth2UserInfo;
import AhChacha.Backend.oauth2.userinfo.KakaoOAuth2UserInfo;
import AhChacha.Backend.oauth2.userinfo.NaverOAuth2UserInfo;
import AhChacha.Backend.oauth2.userinfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.PLATFORM_NOT_FOUND;

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
        if (platform == Platform.GOOGLE) {
            return OAuth2AttributesRequest.builder()
                    .nameAttributeKey(userNameAttributeKey)
                    .oAuth2UserInfo(new GoogleOAuth2UserInfo(attributes))
                    .build();
        } else if (platform == Platform.KAKAO) {
            return OAuth2AttributesRequest.builder()
                    .nameAttributeKey(userNameAttributeKey)
                    .oAuth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                    .build();
        } else if (platform == Platform.NAVER) {
            return OAuth2AttributesRequest.builder()
                    .nameAttributeKey(userNameAttributeKey)
                    .oAuth2UserInfo(new NaverOAuth2UserInfo(attributes))
                    .build();
        }
        else throw new NotFoundException(PLATFORM_NOT_FOUND);
    }


    public Member toMember(Platform platform, OAuth2UserInfo oAuth2UserInfo) {
        System.out.println("platform = " + platform);
        return new Member(platform,
                oAuth2UserInfo.getId(),
                oAuth2UserInfo.getProfileImageUrl(),
                RoleType.GUEST,
                oAuth2UserInfo.getName(),
                oAuth2UserInfo.getEmail()
                );
    }

}