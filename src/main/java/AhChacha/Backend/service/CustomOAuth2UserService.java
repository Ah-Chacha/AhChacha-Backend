package AhChacha.Backend.service;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import AhChacha.Backend.exception.NotFoundException;
import AhChacha.Backend.exception.status.BaseExceptionResponseStatus;
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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static AhChacha.Backend.domain.Platform.*;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.PLATFORM_NOT_FOUND;
import static AhChacha.Backend.exception.status.BaseExceptionResponseStatus.USER_NOT_FOUND;


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
        System.out.println("registrationId = " + registrationId);
        Platform platform = getPlatform(registrationId);
        System.out.println("platform = " + platform);
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        Map<String, Object> attributes = oAuth2User.getAttributes();

        System.out.println("userNameAttributeName = " + userNameAttributeName);

        OAuth2AttributesRequest extractAttributes = OAuth2AttributesRequest.of(platform, userNameAttributeName, attributes);

        System.out.println("extractAttributes.getOAuth2UserInfo() = " + extractAttributes.getOAuth2UserInfo());

        Member createdMember = getMember(extractAttributes, platform);

        System.out.println("createdMember.getRoleType() = " + createdMember.getRoleType());
        System.out.println("createdMember.getPlatformId() = " + createdMember.getPlatformId());
        System.out.println("createdMember.getPlatform() = " + createdMember.getPlatform());
        System.out.println("extractAttributes = " + extractAttributes.getNameAttributeKey());

        return new CustomOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(createdMember.getRoleType().getKey())),
                attributes,
                extractAttributes.getNameAttributeKey(),
                createdMember.getPlatform(),
                createdMember.getRoleType()
        );
    }
    private Member getMember(OAuth2AttributesRequest extractAttributes, Platform platform) {
        System.out.println("platform = " + platform);
        System.out.println("extractAttributes.getOAuth2UserInfo().getId() = " + extractAttributes.getOAuth2UserInfo().getId());
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            System.out.println("ID: " + member.getId());
            System.out.println("Name: " + member.getName());
            System.out.println("Email: " + member.getEmail());
            System.out.println("member.getPlatformId() = " + member.getPlatformId());
            System.out.println("member = " + member.getPlatform());
            System.out.println();
        }

        if(memberRepository.findByPlatformAndPlatformId(platform, extractAttributes.getOAuth2UserInfo().getId()).isPresent()) {
            Member findmember = memberRepository.findByPlatformAndPlatformId(platform, extractAttributes.getOAuth2UserInfo().getId()).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));
            return findmember;
        } else {
            System.out.println("Nulll!!!!!!!!!!!!!!!!!");
            return saveMember(extractAttributes, platform);
        }
    }

    @Transactional
    public Member saveMember(OAuth2AttributesRequest extractAttributes, Platform platform) {
        System.out.println("platform !!@!#!!#= " + platform);
        Member createdMember = extractAttributes.toMember(platform, extractAttributes.getOAuth2UserInfo());
        return memberRepository.save(createdMember);
    }

    private Platform getPlatform(String registrationId) {
        try {
            return Platform.valueOf(registrationId.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException(PLATFORM_NOT_FOUND);
        }
    }
}