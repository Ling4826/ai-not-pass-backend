package se331.passport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import se331.passport.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByLevel_LevelNumberOrderByIdAsc(int levelNumber);
}
