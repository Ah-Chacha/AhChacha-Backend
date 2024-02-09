package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Habit;
import AhChacha.Backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HabitRepository extends JpaRepository<Habit, Long> {
    List<Habit> findAllByMember(Member member);
}
