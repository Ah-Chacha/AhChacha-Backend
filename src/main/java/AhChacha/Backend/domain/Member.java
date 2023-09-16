package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "/Users/lyouxsun/Desktop/LS/Backend/src/main/resources/picture/profileImage.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "profile_image")
    private String profileImage;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(name="email")
    private String email;

    @Column(name="class")
    @Enumerated(value = EnumType.STRING)
    private Classification classification;

    @Column(name="create_at")
    private Timestamp createDate;

    @Column(name= "status")
    private String status = STATUS_ACTIVE;
    @Column(name="login_id")
    private String loginId;

    @Column(name="login_password")
    private String loginPassword;

    @Column(name="weight")
    private int weight;

    @Column(name = "platform")
    @Enumerated(value = EnumType.STRING)
    private Provider provider;

    @Column(name = "platform_id")
    private String providerId;

    @Column(name = "role_type")
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;


    @Builder
    public Member(String phoneNumber, Provider provider, String loginId, String email, String nickname, String profileImage, String providerId, RoleType roleType) {
        this.provider = provider;
        this.loginId = loginId;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.providerId = providerId;
        this.roleType = roleType;
        this.phoneNumber = phoneNumber;
    }


}
