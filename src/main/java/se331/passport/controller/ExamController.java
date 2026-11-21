package se331.passport.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.ExamHistoryDTO;
import se331.passport.entity.ExamQuestionDTO;
import se331.passport.entity.ExamResultDTO;
import se331.passport.entity.ExamSubmitDTO;
import se331.passport.security.user.User;
import se331.passport.service.ExamService;

@RestController
@RequiredArgsConstructor
public class ExamController {
    final ExamService examService;

    @GetMapping("/exams/questions")
    public List<ExamQuestionDTO> getExamQuestions(@AuthenticationPrincipal User user) {
        return examService.getExamQuestions(user);
    }

    @PostMapping("/exams")
    public ResponseEntity<ExamResultDTO> submitExam(@AuthenticationPrincipal User user, @RequestBody ExamSubmitDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(examService.submitExam(user, dto));
    }

    @GetMapping("/members/me/exam-histories")
    public List<ExamHistoryDTO> getMyExamHistories(@AuthenticationPrincipal User user) {
        return examService.getMyExamHistories(user);
    }
}
