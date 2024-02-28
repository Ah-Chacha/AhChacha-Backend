package AhChacha.Backend.domain;

import AhChacha.Backend.dto.blood.request.BloodRequest;
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
    @Column(name = "blood_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("수축기 혈압 : 40mmHg 이상 200mmHg 이하")
    @Column(name = "systolic_pressure", nullable = false)
    private int systolicPressure;

    @Comment("이완기 혈압 : 20mmHg 이상 150mmHg 이하")
    @Column(name = "diastolic_pressure", nullable = false)
    private int diastolicPressure;

    @Comment("심박수 : 20bpm 이상 250 bpm 이하")
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

    public static Blood of(Member member, BloodRequest request) {
        return Blood.builder()
                .member(member)
                .systolicPressure(request.getSystolicPressure())
                .diastolicPressure(request.getDiastolicPressure())
                .heartRate(request.getHeartRate())
                .bloodSugar(request.getBloodSugar())
                .cholesterol(request.getCholesterol())
                .build();
    }

    public void update(BloodRequest request) {
        this.systolicPressure = request.getSystolicPressure();
        this.diastolicPressure = request.getDiastolicPressure();
        this.heartRate = request.getHeartRate();
        this.bloodSugar = request.getBloodSugar();
        this.cholesterol = request.getCholesterol();
    }
}
