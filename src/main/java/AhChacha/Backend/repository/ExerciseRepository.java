package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Exercise;
import AhChacha.Backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByMember(Member member);
}
