package se331.passport.dao;

import java.util.List;

import se331.passport.entity.ExamHistory;

public interface ExamHistoryDao {
    ExamHistory save(ExamHistory history);

    List<ExamHistory> findByMemberId(Long memberId);
}
