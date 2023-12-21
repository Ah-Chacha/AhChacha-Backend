package AhChacha.Backend.domain;

import AhChacha.Backend.converter.ProviderConverter;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member {

    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "./profileImage.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    /*@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "rt_key")
    private RefreshToken refreshToken;*/

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

//    @Column(name="class")
//    @Enumerated(value = EnumType.STRING)
//    private Classification classification;

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

    @Column(name = "height")
    private int height;

//    @Column(name = "gender")
//    @Enumerated(value = EnumType.STRING)
//    private Gender gender;
//
    @Column(name = "platform")
    //@Enumerated(value = EnumType.STRING)
    @Convert(converter = ProviderConverter.class)
    private Provider provider;

    @Column(name = "platform_id")
    private String providerId;

    @Column(name = "role_type")
    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    @Column(name="birthday")
    private Timestamp birthday;


    @Builder
    public Member(String name, String nickname, String profileImage, String phoneNumber,
                  String email, String loginId, String loginPassword,
                  int weight, int height, String providerId, Timestamp birthday) {
//        this.provider = provider;
        this.name = name;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.loginId = loginId;
        this.loginPassword = loginPassword;
//        this.providerId = providerId;
//        this.roleType = roleType;
//        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.providerId = providerId;
        this.birthday = birthday;
    }


}