package AhChacha.Backend.repository;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByPlatformAndPlatformId(Platform platform, String providerId);

    Optional<Member> findByPlatformId(String providerId);

    boolean existsByEmail(String email);


    @Modifying
    @Query(value = "update member set role_type = :role_type where platform = :platform and platform_id = :id", nativeQuery = true)
    void updateMember(@Param("role_type") String role_type, @Param("platform") String platform, @Param("id") String id);

}
