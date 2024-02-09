package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "habit")
public class Habit extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long habitId;

    @Comment("예방 음식 섭취 개수")
    @Column(nullable = false)
    private int foodNum;

    @Column(name = "alcohol", nullable = false)
    private boolean alcohol;

    @Column(name = "alcohol_quantity", nullable = false)
    private int alcoholQuantity;

    @Comment("하루 독서 시간")
    @Column(name = "reading_time", nullable = false)
    private int readingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Habit(int foodNum, boolean alcohol, int alcoholQuantity, int readingTime, Member member) {
        this.foodNum = foodNum;
        this.alcohol = alcohol;
        this.alcoholQuantity = alcoholQuantity;
        this.readingTime = readingTime;
        this.member = member;
    }
}
