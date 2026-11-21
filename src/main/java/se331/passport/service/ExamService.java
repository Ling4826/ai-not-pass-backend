package se331.passport.service;

import java.util.List;

import se331.passport.entity.ExamHistoryDTO;
import se331.passport.entity.ExamQuestionDTO;
import se331.passport.entity.ExamResultDTO;
import se331.passport.entity.ExamSubmitDTO;
import se331.passport.security.user.User;

public interface ExamService {
    List<ExamQuestionDTO> getExamQuestions(User user);

    ExamResultDTO submitExam(User user, ExamSubmitDTO dto);

    List<ExamHistoryDTO> getMyExamHistories(User user);
}
