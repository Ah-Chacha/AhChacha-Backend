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


    //ENUM TYPE이 문제다 ...... ㅜ
    //ENUM 끼면 걍 ㅅ 발 의존성 주입이 안되요 시ㅣㅣ발 ㅜㅜㅜㅜ
    @Modifying
    @Query(value = "update member set age = :age, height = :height, gender = :gender, weight = :weight, role_type = 'USER' where platform = :provider and platform_id = :id", nativeQuery = true)
    void updateMember(@Param("age") int age, @Param("height") int height, @Param("gender") String gender, @Param("weight") int weight, @Param("provider") String provider, @Param("id") String id);

}
