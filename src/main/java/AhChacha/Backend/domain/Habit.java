package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity @Getter
@NoArgsConstructor
@Table(name = "habit")
public class Habit {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "habit_id", nullable = false)
    private Long id;

    @Column(name = "alcohol", nullable = false)
    private boolean alcohol;

    @Column(name = "alcohol_quantity", nullable = false)
    private int alcocholQuantity;

    @Column(name = "reading_time", nullable = false)
    private int readingTime;

    @Column(name = "habit_day", nullable = false)
    private Timestamp date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;
}
