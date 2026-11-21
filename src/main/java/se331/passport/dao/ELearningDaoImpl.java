package se331.passport.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import se331.passport.entity.ELearning;
import se331.passport.repository.ELearningRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ELearningDaoImpl implements ELearningDao {
    final ELearningRepository eLearningRepository;

    @Override
    public List<ELearning> getELearningsByLevel(int levelNumber) {
        return eLearningRepository.findByLevel_LevelNumber(levelNumber);
    }
}
