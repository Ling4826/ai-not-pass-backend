package se331.passport.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se331.passport.dao.StatsDao;
import se331.passport.entity.Level;
import se331.passport.entity.LevelStatsDTO;
import se331.passport.entity.MemberStatsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.passport.entity.ExamHistory;
@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    final StatsDao statsDao;

    @Override
    public MemberStatsDTO getMemberStats() {
        List<LevelStatsDTO> levels = new ArrayList<>();
        for (Level level : statsDao.getLevels()) {
            levels.add(LevelStatsDTO.builder()
                    .levelNumber(level.getLevelNumber())
                    .name(level.getName())
                    .memberCount(statsDao.countMembersByLevel(level.getId()))
                    .examCount(statsDao.countExamsByLevel(level.getId()))
                    .passedCount(statsDao.countPassedExamsByLevel(level.getId()))
                    .build());
        }
        return MemberStatsDTO.builder()
                .totalMembers(statsDao.countMembers())
                .activeMembers(statsDao.countActiveMembers())
                .totalExams(statsDao.countExams())
                .passedExams(statsDao.countPassedExams())
                .levels(levels)
                .build();
    }
    @Override
    public Page<ExamHistory> getExamHistories(Pageable pageable) {
        return statsDao.getExamHistories(pageable);
    }
}