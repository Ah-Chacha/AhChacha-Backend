package AhChacha.Backend.domain;

import AhChacha.Backend.dto.habit.request.HabitRequest;
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
    private Long id;

    @Comment("예방 음식 섭취 개수")
    @Column(nullable = false)
    private int foodNum;

    @Column(nullable = false)
    private int alcoholQuantity;

    @Comment("하루 독서 시간")
    @Column(nullable = false)
    private int readingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Habit(int foodNum, int alcoholQuantity, int readingTime, Member member) {
        this.foodNum = foodNum;
        this.alcoholQuantity = alcoholQuantity;
        this.readingTime = readingTime;
        this.member = member;
    }

    public void update(HabitRequest request) {
        this.foodNum = request.getFoodNum();
        this.alcoholQuantity = request.getAlcoholQuantity();
        this.readingTime = request.getReadingTime();
    }
}
