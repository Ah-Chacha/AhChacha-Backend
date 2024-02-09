package AhChacha.Backend.domain;

import AhChacha.Backend.dto.exercise.request.ExerciseRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "exercise")
public class Exercise extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Comment("하루 운동 시간")
    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Exercise(int quantity, String type, Member member) {
        this.quantity = quantity;
        this.type = type;
        this.member = member;
    }

    public void update(ExerciseRequest request){
        quantity = request.getQuantity();
        type = request.getType();
    }

}
