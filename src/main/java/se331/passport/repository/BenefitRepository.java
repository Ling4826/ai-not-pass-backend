package se331.passport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.passport.entity.Benefit;

public interface BenefitRepository extends JpaRepository<Benefit,Long> {
    List<Benefit> findByLevel_LevelNumberOrderByIdAsc(int levelNumber);
}
