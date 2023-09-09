package AhChacha.Backend.domain;

import jakarta.persistence.*;
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
    private int systolicPressure;

    @Column(name = "diastolic_pressure", nullable = false)
    private int diastolicPressure;

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
    private User user;
}
