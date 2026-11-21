package se331.passport.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.ExamHistory;
import se331.passport.entity.MemberStatsDTO;
import se331.passport.service.StatsService;
import se331.passport.util.PassportMapper;

@RestController
@RequiredArgsConstructor
public class StatsController {
    final StatsService statsService;

    @GetMapping("/members/stats")
    public MemberStatsDTO getMemberStats() {
        return statsService.getMemberStats();
    }

    @GetMapping("/members/exam-histories")
    public ResponseEntity<?> getExamHistories(
            @RequestParam(value = "_limit", required = false) Integer perPage,
            @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? 10 : perPage;
        page = page == null ? 1 : page;
        Page<ExamHistory> pageOutput = statsService.getExamHistories(
                PageRequest.of(page - 1, perPage, Sort.by("attemptedAt").descending()));
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(PassportMapper.INSTANCE.getRecentExamDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
}