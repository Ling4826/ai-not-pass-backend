package se331.passport.dao;

import java.util.List;
import java.util.Optional;

import se331.passport.entity.Level;
import se331.passport.entity.Question;

public interface QuestionDao {
    List<Question> getQuestionsByLevel(int levelNumber);
    Question save(Question question);

    Optional<Level> findLevelByNumber(int levelNumber);

    Optional<Question> findById(Long id);

    void deleteById(Long id);
}
