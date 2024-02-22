package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "patientInfo")
public class PatientInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    @Comment("체중")
    private int weight;

    @Comment("신장")
    private int height;

    @Comment("생일")
    private LocalDate birthday;

    @Comment("휴대폰 번호")
    private String phoneNumber;

    @OneToOne(mappedBy = "patientInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Member member;

    @Builder
    public PatientInfo(int weight, int height, LocalDate birthday, String phoneNumber, Member member) {
        this.weight = weight;
        this.height = height;
        this.birthday = birthday;
        this.phoneNumber = phoneNumber;
        this.member = member;
    }
}
