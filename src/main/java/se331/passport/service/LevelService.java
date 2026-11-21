package se331.passport.service;

import se331.passport.entity.Level;
import se331.passport.entity.Benefit;
import se331.passport.entity.BenefitDTO;
import se331.passport.entity.ServiceItem;
import se331.passport.entity.ServiceItemDTO;


import java.util.List;

public interface LevelService {
    List<Level> getLevels();
    Level getLevel(Long id);
    Benefit addBenefit(Long levelId, BenefitDTO benefitDTO);
    Benefit updateBenefit(Long id, BenefitDTO benefitDTO);
    ServiceItem addServiceItem(Long levelId, ServiceItemDTO serviceItemDTO);
    ServiceItem updateServiceItem(Long id, ServiceItemDTO serviceItemDTO);
    Level updatePassCriteria(Long id, Integer passCriteria);
    void deleteBenefit(Long id);
    void deleteServiceItem(Long id);
}