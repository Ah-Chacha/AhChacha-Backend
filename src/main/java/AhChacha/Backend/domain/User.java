package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

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
    @Builder.Default
    private String profileImage = DEFAULT_PROFILE_ROOT;

    @Column(name="phone_number", nullable = false)
    private String phoneNumber;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="class", nullable = false)
    private Classification classification;

    @Column(name="create_at", nullable = false)
    @Builder.Default
    private Timestamp createDate = new Timestamp(System.currentTimeMillis());

    @Column(name= "status", nullable = false)
    @Builder.Default()
    private String status = STATUS_ACTIVE;
    @Column(name="login_id", nullable = false)
    private String loginId;

    @Column(name="login_password", nullable = false)
    private String loginPassword;

    @Column(name="weight", nullable = false)
    private int weight;

}
