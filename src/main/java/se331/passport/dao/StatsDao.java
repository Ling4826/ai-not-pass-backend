package se331.passport.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.passport.entity.Level;
import se331.passport.entity.ExamHistory;
public interface StatsDao {
    long countMembers();

    long countActiveMembers();

    long countExams();

    long countPassedExams();

    List<Level> getLevels();

    long countMembersByLevel(Long levelId);

    long countExamsByLevel(Long levelId);

    long countPassedExamsByLevel(Long levelId);

    Page<ExamHistory> getExamHistories(Pageable pageable);
}