package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Blood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloodRepository extends JpaRepository<Blood, Long> {
}
