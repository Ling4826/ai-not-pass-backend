package se331.passport.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.Level;
import se331.passport.entity.Question;
import se331.passport.repository.LevelRepository;
import se331.passport.repository.QuestionRepository;

@Repository
@RequiredArgsConstructor
public class QuestionDaoImpl implements QuestionDao {
    final QuestionRepository questionRepository;
    final LevelRepository levelRepository;
    @Override
    public List<Question> getQuestionsByLevel(int levelNumber) {
        return questionRepository.findByLevel_LevelNumberOrderByIdAsc(levelNumber);
    }

    @Override
    public Question save(Question question) {
        return questionRepository.save(question);
    }

    @Override
    public Optional<Level> findLevelByNumber(int levelNumber) {
        return levelRepository.findByLevelNumber(levelNumber);
    }

    @Override
    public Optional<Question> findById(Long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        questionRepository.deleteById(id);
    }
}