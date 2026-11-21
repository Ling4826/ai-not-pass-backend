package se331.passport.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import se331.passport.entity.Benefit;
import se331.passport.entity.ServiceItem;
import se331.passport.repository.BenefitRepository;
import se331.passport.repository.ServiceItemRepository;

@Repository
@RequiredArgsConstructor
public class LevelDetailDaoImpl implements LevelDetailDao {
    final BenefitRepository benefitRepository;
    final ServiceItemRepository serviceItemRepository;

    @Override
    public List<Benefit> getBenefitsByLevel(int levelNumber) {
        return benefitRepository.findByLevel_LevelNumberOrderByIdAsc(levelNumber);
    }

    @Override
    public List<ServiceItem> getServicesUpToLevel(int levelNumber) {
        return serviceItemRepository.findByLevel_LevelNumberLessThanEqualOrderByIdAsc(levelNumber);
    }
}
