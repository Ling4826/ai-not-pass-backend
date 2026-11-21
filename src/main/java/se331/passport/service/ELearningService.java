package se331.passport.service;

import java.util.List;

import se331.passport.entity.ELearningDTO;

public interface ELearningService {
    List<ELearningDTO> getELearningByLevel(int level);
}
