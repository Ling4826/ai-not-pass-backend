package se331.passport.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se331.passport.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se331.passport.entity.QuestionDTO;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequiredArgsConstructor
public class QuestionController {
    final QuestionService questionService;

    @GetMapping("/questions")
    public Object getQuestionsByLevel(@RequestParam int level) {
        return questionService.getQuestionsByLevel(level);
    }

    @PostMapping("/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.createQuestion(questionDTO));
    }

    @PutMapping("/questions/{id}")
public QuestionDTO updateQuestion(@PathVariable Long id, @RequestBody QuestionDTO questionDTO) {
    return questionService.updateQuestion(id, questionDTO);
}

@DeleteMapping("/questions/{id}")
public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    questionService.deleteQuestion(id);
    return ResponseEntity.noContent().build();
}
}