package se331.passport.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se331.passport.service.ELearningService;

@RestController
@RequiredArgsConstructor
public class ELearningController {
    final ELearningService eLearningService;
    @GetMapping("/elearnings")
    public Object getELearningByLevel(@RequestParam int level) {
        return eLearningService.getELearningByLevel(level);
    }
}
