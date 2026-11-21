package se331.passport.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.ExamHistory;
import se331.passport.repository.ExamHistoryRepository;

@Repository
@RequiredArgsConstructor
public class ExamHistoryDaoImpl implements ExamHistoryDao {
    final ExamHistoryRepository examHistoryRepository;

    @Override
    public ExamHistory save(ExamHistory history) {
        return examHistoryRepository.save(history);
    }

    @Override
    public List<ExamHistory> findByMemberId(Long memberId) {
        return examHistoryRepository.findByMember_IdOrderByAttemptedAtDesc(memberId);
    }
}
