package AhChacha.Backend.domain;

import AhChacha.Backend.converter.PlatformConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseTimeEntity {

    public static final String STATUS_ACTIVE = "active";
    public static final String DEFAULT_PROFILE_ROOT = "./profileImage.png";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Convert(converter = PlatformConverter.class)
    private Platform platform;

    private String platformId;

    private String name;

    private String email;

    private String status = STATUS_ACTIVE;

    @Enumerated(value = EnumType.STRING)
    private RoleType roleType;

    private String profileImage = DEFAULT_PROFILE_ROOT;

    @Setter
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private PatientInfo patientInfo;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Blood> bloods = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Exercise> exercises = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Habit> habits = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Sleep> sleeps = new ArrayList<>();

    @Builder
    public Member(String name, String email, RoleType roleType) {
        this.name = name;
        this.email = email;
        this.roleType = roleType;
    }

    public Member of(String email) {
        return Member.builder()
                .email(email)
                .build();
    }

}