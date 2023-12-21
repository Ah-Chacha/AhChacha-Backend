package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor
@Table(name = "sleep")
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "sleep_id", nullable = false)
    private Long id;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Column(name = "quality_level", nullable = false)
    private int quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member memberId;


    @Builder
    public Sleep(Member memberId, Timestamp startTime, Timestamp endTime,int quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
        this.memberId = memberId;
    }
}
