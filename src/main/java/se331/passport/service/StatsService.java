package se331.passport.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import se331.passport.entity.ExamHistory;
import se331.passport.entity.MemberStatsDTO;

public interface StatsService {
    MemberStatsDTO getMemberStats();

    Page<ExamHistory> getExamHistories(Pageable pageable);
}