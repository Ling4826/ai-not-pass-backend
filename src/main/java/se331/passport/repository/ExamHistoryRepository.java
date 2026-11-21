package se331.passport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.passport.entity.ExamHistory;

public interface ExamHistoryRepository extends JpaRepository<ExamHistory, Long> {
    List<ExamHistory> findByMember_IdOrderByAttemptedAtDesc(Long memberId);
    long countByPassedTrue();
    long countByLevel_Id(Long levelId);
    long countByLevel_IdAndPassedTrue(Long levelId);
}
