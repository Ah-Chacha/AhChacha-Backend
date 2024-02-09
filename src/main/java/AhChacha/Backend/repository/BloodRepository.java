package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Blood;
import AhChacha.Backend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BloodRepository extends JpaRepository<Blood, Long> {
    List<Blood> findAllByMember(Member member);
}
