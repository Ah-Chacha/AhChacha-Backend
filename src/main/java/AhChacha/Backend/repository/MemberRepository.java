package AhChacha.Backend.repository;


import AhChacha.Backend.domain.Member;
import AhChacha.Backend.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByProviderAndProviderId(Provider provider, String providerId);


    //@Query(value = "")

}
