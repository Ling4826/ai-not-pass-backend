package se331.passport.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.passport.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Page<Member> findByNameIgnoreCaseContainingAndSurnameIgnoreCaseContainingAndProvinceIgnoreCaseContainingAndLevel_NameIgnoreCaseContaining(
            String name, String surname, String province, String level, Pageable pageRequest);
        long countByActiveTrue();
        long countByLevel_Id(Long levelId);
}
