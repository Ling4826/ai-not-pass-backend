package se331.passport.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.LevelDetailDTO;
import se331.passport.security.user.User;
import se331.passport.service.LevelDetailService;

@RestController
@RequiredArgsConstructor
public class LevelDetailController {
    final LevelDetailService levelDetailService;

    @GetMapping("/members/me/level-detail")
    public LevelDetailDTO getMyLevelDetail(@AuthenticationPrincipal User user) {
        return levelDetailService.getMyLevelDetail(user);
    }
}
