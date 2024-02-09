package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SleepRepository extends JpaRepository<Sleep, Long> {
    List<Sleep> findAllByMember(Member member);
}
