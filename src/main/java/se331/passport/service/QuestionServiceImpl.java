package se331.passport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import se331.passport.dao.QuestionDao;
import se331.passport.entity.Level;
import se331.passport.entity.Question;
import se331.passport.entity.QuestionDTO;
import se331.passport.util.PassportMapper;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    final QuestionDao questionDao;

    @Override
    public List<QuestionDTO> getQuestionsByLevel(int levelNumber) {
        return PassportMapper.INSTANCE.getQuestionDto(questionDao.getQuestionsByLevel(levelNumber));
    }

    private Level validateAndGetLevel(QuestionDTO questionDTO) {
    if (questionDTO.getText() == null || questionDTO.getText().isBlank()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Question text is required");
    }
    if (questionDTO.getChoices() == null || questionDTO.getChoices().size() < 2
            || questionDTO.getChoices().stream().anyMatch(c -> c == null || c.isBlank())) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "At least 2 non-empty choices are required");
    }
    Integer answer = questionDTO.getCorrectAnswer();
    if (answer == null || answer < 0 || answer >= questionDTO.getChoices().size()) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "correctAnswer must be the index of a choice");
    }
    if (questionDTO.getLevel() == null || questionDTO.getLevel().getLevelNumber() == null) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "level.levelNumber is required");
    }
    return questionDao.findLevelByNumber(questionDTO.getLevel().getLevelNumber())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Level not found"));
}

    @Override
    public QuestionDTO createQuestion(QuestionDTO questionDTO) {
       Level level = validateAndGetLevel(questionDTO);
        Question question = PassportMapper.INSTANCE.getQuestion(questionDTO);
        question.setLevel(level);
        return PassportMapper.INSTANCE.getQuestionDto(questionDao.save(question));
    }

    @Override
    public QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO) {
        Question question = questionDao.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found"));
        Level level = validateAndGetLevel(questionDTO);
        question.setText(questionDTO.getText());
        question.setChoices(new ArrayList<>(questionDTO.getChoices()));
        question.setCorrectAnswer(questionDTO.getCorrectAnswer());
        question.setLevel(level);
        return PassportMapper.INSTANCE.getQuestionDto(questionDao.save(question));
    }

    @Override
    public void deleteQuestion(Long id) {
        if (questionDao.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Question not found");
        }
        questionDao.deleteById(id);
    }
}