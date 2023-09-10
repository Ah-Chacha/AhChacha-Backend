package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor
@Table(name = "exercise")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exercise_id", nullable = false)
    private Long id;

    @Column(name = "exercise_time", nullable = false)
    private int time;

    @Column(name = "exercise_type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member memberId;

    @Column(name = "exercise_day", nullable = false)
    private Timestamp date;
}
