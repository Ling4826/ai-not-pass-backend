package se331.passport.service;

import java.util.List;

import se331.passport.entity.QuestionDTO;

public interface QuestionService {
    List<QuestionDTO> getQuestionsByLevel(int level);
    QuestionDTO createQuestion(QuestionDTO questionDTO);
    QuestionDTO updateQuestion(Long id, QuestionDTO questionDTO);
    void deleteQuestion(Long id);
}