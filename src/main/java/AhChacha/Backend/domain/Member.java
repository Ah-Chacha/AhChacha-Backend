package AhChacha.Backend.domain;

import AhChacha.Backend.converter.PlatformConverter;
import AhChacha.Backend.dto.member.request.MemberRequest;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
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

    public Member(String name, String profileImage) {
        this.name = name;
        this.profileImage = profileImage;
    }

    public Member(Platform platform, String platformId, String profileImage, RoleType roleType, String name, String email){
        this.platform = platform;
        this.platformId = platformId;
        this.profileImage = profileImage;
        this.roleType = roleType;
        this.name = name;
        this.email = email;
    }

    public Member of(String email) {
        return Member.builder()
                .email(email)
                .build();
    }

    public void updateMember(MemberRequest memberRequest) {
        name = memberRequest.getName();
        roleType = memberRequest.getRoleType();
        email = memberRequest.getEmail();
        status = memberRequest.getStatus();
        profileImage = memberRequest.getProfileImage();
    }

}