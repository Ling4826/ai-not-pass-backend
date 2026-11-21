package se331.passport.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.passport.entity.Level;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Level,Long>{
    Optional<Level> findByLevelNumber(Integer levelNumber);
}

