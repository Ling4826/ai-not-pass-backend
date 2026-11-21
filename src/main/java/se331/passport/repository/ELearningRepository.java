package se331.passport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se331.passport.entity.ELearning;
public interface ELearningRepository extends JpaRepository<ELearning, Long> {
    List<ELearning> findByLevel_LevelNumber(int levelNumber);
}
