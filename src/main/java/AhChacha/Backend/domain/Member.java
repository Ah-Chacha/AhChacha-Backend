package AhChacha.Backend.domain;

import AhChacha.Backend.converter.ProviderConverter;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "./profileImage.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status = STATUS_ACTIVE;

    @Column(name = "role_type")
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @Nullable
    @Column(name = "profile_image")
    private String profileImage = DEFAULT_PROFILE_ROOT;

    @Nullable
    @Column(name = "phone_number")
    private String phoneNumber;


    @Column(name = "platform")
    @Convert(converter = ProviderConverter.class)
    private Provider provider;

    @Column(name = "platform_id")
    private String providerId;


    @Builder
    public Member(String name, String profileImage, String phoneNumber,
                  String email, String password, RoleType roleType) {
        this.name = name;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.roleType = roleType;
    }

    public Member of(String email) {
        return Member.builder()
                .email(email)
                .build();
    }

}