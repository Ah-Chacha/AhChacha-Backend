package AhChacha.Backend.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Blood {

    @Id
    @Column(name = "blood_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "mesuer_time", nullable = false)
    private Timestamp measureTime = new Timestamp(System.currentTimeMillis());

}
