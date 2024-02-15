package AhChacha.Backend.oauth2;

import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.domain.RoleType;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.PLATFORM_NOT_FOUND;


@Getter
public class CustomOAuth2User extends DefaultOAuth2User {

    /**
     * Constructs a {@code DefaultOAuth2User} using the provided parameters.
     *
     * @param authorities      the authorities granted to the user
     * @param attributes       the attributes about the user
     * @param nameAttributeKey the key used to access the user's &quot;name&quot; from
     *                         {@link #getAttributes()}
     */


    //private String email;
    private RoleType roleType;

    private Platform platform;
    public CustomOAuth2User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes, String nameAttributeKey, Platform platform, RoleType roleType) {
        super(authorities, attributes, nameAttributeKey);
        System.out.println("authorities = " + authorities);
        //this.email = email;
        this.platform = platform;
        this.roleType = roleType;
    }

    public Platform getPlatform() {
        System.out.println("getAttribute(\"resultcode\") = " + getAttribute("resultcode"));
        System.out.println("getAttribute(\"id\") = " + getAttribute("id"));
        if (getAttribute("resultcode")!= null) {
            return Platform.NAVER;
        } else if (getAttribute("id") != null) {
            return Platform.KAKAO;
        } else if (getAttribute("sub") != null) {
            return Platform.GOOGLE;
        } else throw new NotFoundException(PLATFORM_NOT_FOUND);
    }
}