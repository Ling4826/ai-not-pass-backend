package se331.passport.dao;

import se331.passport.entity.ELearning;

import java.util.List;

public interface ELearningDao {
    List<ELearning> getELearningsByLevel(int levelNumber);
}
