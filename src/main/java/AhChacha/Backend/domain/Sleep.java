package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "sleep")
public class Sleep extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("수면 시작 시간")
    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Comment("수면 종료 시간")
    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @Comment("수면의 질 : 1~5 중 택 1")
    @Column(name = "quality_level", nullable = false)
    private int quality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;


    @Builder
    public Sleep(Member member, Timestamp startTime, Timestamp endTime, int quality) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.quality = quality;
        this.member = member;
    }
}
