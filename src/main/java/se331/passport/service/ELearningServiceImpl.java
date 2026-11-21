package se331.passport.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import se331.passport.dao.ELearningDao;
import se331.passport.entity.ELearningDTO;
import se331.passport.util.PassportMapper;

@Service
@RequiredArgsConstructor
public class ELearningServiceImpl implements ELearningService {
    final ELearningDao eLearningDao;

    @Override
    public List<ELearningDTO> getELearningByLevel(int levelNumber) {
        return PassportMapper.INSTANCE.getELearningDto(eLearningDao.getELearningsByLevel(levelNumber));
    }
}
