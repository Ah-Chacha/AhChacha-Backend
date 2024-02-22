package AhChacha.Backend.repository;

import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.PatientInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<PatientInfo, Long> {

    Optional<PatientInfo> findPatientInfoByMemberId(Long id);

}
