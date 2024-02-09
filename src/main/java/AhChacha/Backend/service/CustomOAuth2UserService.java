package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.oauth2.CustomOAuth2User;
import AhChacha.Backend.dto.oauth.request.OAuth2AttributesRequest;
import AhChacha.Backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Map;




@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;


    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println("userRequest = " + userRequest);
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        System.out.println("In loadUser!!!!!!!!!!!!!!!!!");

        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Platform platform = getProvider(registrationId);
        System.out.println("platform = " + platform);
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        OAuth2AttributesRequest extractAttributes = OAuth2AttributesRequest.of(platform, userNameAttributeName, attributes);

        Member createdMember = getMember(extractAttributes, platform);

        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdMember.getRoleType().getKey())),
                attributes,
                extractAttributes.getNameAttributeKey(),
                createdMember.getPlatform(),
                createdMember.getRoleType()
        );
    }
    private Member getMember(OAuth2AttributesRequest extractAttributes, Platform platform) {
        Member findMember = memberRepository.findByPlatformAndPlatformId(platform,
                extractAttributes.getOAuth2UserInfo().getId()).orElse(null);
        if(findMember == null) {
            return saveMember(extractAttributes, platform);
        }
        return findMember;
    }

    @Transactional
    public Member saveMember(OAuth2AttributesRequest extractAttributes, Platform platform) {
        Member createdMember = extractAttributes.toMember(platform);
        return memberRepository.save(createdMember);
    }

    private Platform getProvider(String registrationId) {
        /*if(KAKAO.equals(registrationId)) {
            return Platform.KAKAO;
        }*/
        return Platform.GOOGLE;
    }
}