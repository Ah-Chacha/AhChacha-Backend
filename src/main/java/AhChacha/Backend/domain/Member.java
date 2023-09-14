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
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "profile_image", nullable = false)
    private String profileImage;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="class", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Classification classification;

    @Column(name="create_at", nullable = false)
    private Timestamp createDate;

    @Column(name= "status", nullable = false)
    private String status = STATUS_ACTIVE;
    @Column(name="login_id", nullable = false)
    private String loginId;

    @Column(name="login_password", nullable = false)
    private String loginPassword;

    @Column(name="weight", nullable = false)
    private int weight;

    @Column(name = "platform")
    @Enumerated(value = EnumType.STRING)
    private Platform platform;

    @Column(name = "platform_id")
    private String platformId;

    @Column(name = "role_type")
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;


    @Builder
    public Member(Platform platform, String loginId, String email, String nickname, String profileImage, String platformId, RoleType roleType) {
        this.platform = platform;
        this.loginId = loginId;
        this.email = email;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.platformId = platformId;
        this.roleType = roleType;
    }


}
