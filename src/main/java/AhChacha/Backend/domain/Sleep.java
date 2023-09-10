package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity @Getter
@NoArgsConstructor
@Table(name = "sleep")
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sleep_id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private int startTime;

    @Column(name = "end_time", nullable = false)
    private int endTime;

    @Column(name = "sleep_day", nullable = false)
    private int date;

    @Column(name = "quality_level", nullable = false)
    private int quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member memberId;
}
