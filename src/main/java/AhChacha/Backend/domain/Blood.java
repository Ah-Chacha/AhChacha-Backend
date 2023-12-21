package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor
@Table(name = "Blood")
public class Blood {

    @Id
    @Column(name = "blood_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mesuer_time", nullable = false)
    private Timestamp measureTime;

    @Column(name="systolic_pressure", nullable = false)
    private int systolicPressure;   //수축기 혈압

    @Column(name = "diastolic_pressure", nullable = false)
    private int diastolicPressure;  //이완기 혈압

    //심박수
    @Column(name = "heart_rate", nullable = false)
    private int heartRate;

    @Column(name = "blood_sugar", nullable = false)
    private int bloodSugar;

    @Column(name = "cholesterol", nullable = false)
    private int cholesterol;

    @Column(name = "blood_day", nullable = false)
    private Timestamp bloodDay;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member member;

    @Builder
    public Blood(Member member, Timestamp measureTime, int systolicPressure, int diastolicPressure,
                 int heartRate, int bloodSugar, int cholesterol, Timestamp bloodDay) {
        this.member = member;
        this.measureTime = measureTime;
        this.systolicPressure = systolicPressure;
        this.diastolicPressure = diastolicPressure;
        this.heartRate = heartRate;
        this.bloodSugar = bloodSugar;
        this.cholesterol = cholesterol;
        this.bloodDay = bloodDay;
    }
}
