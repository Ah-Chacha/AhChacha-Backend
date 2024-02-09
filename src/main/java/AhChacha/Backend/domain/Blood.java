package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "Blood")
public class Blood extends BaseTimeEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Comment("수축기 혈압")
    @Column(name = "systolic_pressure", nullable = false)
    private int systolicPressure;

    @Comment("이완기 혈압")
    @Column(name = "diastolic_pressure", nullable = false)
    private int diastolicPressure;

    @Comment("심박수")
    @Column(name = "heart_rate", nullable = false)
    private int heartRate;

    @Comment("혈당")
    @Column(name = "blood_sugar", nullable = false)
    private int bloodSugar;

    @Column(name = "cholesterol", nullable = false)
    private int cholesterol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Blood(Member member, int systolicPressure, int diastolicPressure,
                 int heartRate, int bloodSugar, int cholesterol) {
        this.member = member;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.bloodSugar = bloodSugar;
        this.cholesterol = cholesterol;
    }
}
