package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
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

    @Column(name = "sleep_length")
    private int sleep_length;

    @Column(name = "rem")
    private int rem;

    @Column(name = "sleep_day")
    private int date;

    @Column(name = "quality_level", nullable = false)
    private int quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Member memberId;


    @Builder
    public Sleep(Member memberId, int startTime, int endTime, int sleep_length, int rem, int date, int quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.sleep_length= sleep_length;
        this.rem = rem;
        this.date = date;
        this.quality = quality;
        this.memberId = memberId;
    }
}
