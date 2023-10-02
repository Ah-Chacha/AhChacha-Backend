package AhChacha.Backend.repository;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByProviderAndProviderId(Provider provider, String providerId);

    Optional<Member> findByProviderId(String providerId);


    //ENUM TYPE이 문제다 ...... ㅜ
    //ENUM 끼면 걍 ㅅ 발 의존성 주입이 안되요 시ㅣㅣ발 ㅜㅜㅜㅜ
    @Modifying
    @Query(value = "update member set email = :email, phone_number = :phoneNumber, role_type = 'USER' where platform = :provider and platform_id = :id", nativeQuery = true)
    void updateMember(@Param("email") String email, @Param("phoneNumber") String phoneNumber, @Param("provider") String provider, @Param("id") String id);

}
