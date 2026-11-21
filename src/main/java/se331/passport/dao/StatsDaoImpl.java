package se331.passport.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.Level;
import se331.passport.repository.ExamHistoryRepository;
import se331.passport.repository.LevelRepository;
import se331.passport.repository.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.passport.entity.ExamHistory;
@Repository
@RequiredArgsConstructor
public class StatsDaoImpl implements StatsDao {
    final MemberRepository memberRepository;
    final ExamHistoryRepository examHistoryRepository;
    final LevelRepository levelRepository;

    @Override
    public long countMembers() {
        return memberRepository.count();
    }

    @Override
    public long countActiveMembers() {
        return memberRepository.countByActiveTrue();
    }

    @Override
    public long countExams() {
        return examHistoryRepository.count();
    }

    @Override
    public long countPassedExams() {
        return examHistoryRepository.countByPassedTrue();
    }

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll(Sort.by("levelNumber"));
    }

    @Override
    public long countMembersByLevel(Long levelId) {
        return memberRepository.countByLevel_Id(levelId);
    }

    @Override
    public long countExamsByLevel(Long levelId) {
        return examHistoryRepository.countByLevel_Id(levelId);
    }

    @Override
    public long countPassedExamsByLevel(Long levelId) {
        return examHistoryRepository.countByLevel_IdAndPassedTrue(levelId);
    }

    @Override
    public Page<ExamHistory> getExamHistories(Pageable pageable) {
        return examHistoryRepository.findAll(pageable);
    }
}