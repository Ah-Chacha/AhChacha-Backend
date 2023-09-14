package AhChacha.Backend.repository;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByPlatformAndPlatformId(Platform platform, String platformId);
}
