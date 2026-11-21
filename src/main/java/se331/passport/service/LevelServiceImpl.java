package se331.passport.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se331.passport.dao.LevelDao;
import se331.passport.entity.Level;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import se331.passport.entity.Benefit;
import se331.passport.entity.BenefitDTO;
import se331.passport.entity.ServiceItem;
import se331.passport.entity.ServiceItemDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LevelServiceImpl implements LevelService {
    final LevelDao levelDao;

    @Override
    public List<Level> getLevels() {
        return levelDao.getLevels();
    }

    @Override
    public Level getLevel(Long id) {
        return levelDao.getLevel(id);
    }

    private Level findLevel(Long levelId) {
        Level level = levelDao.getLevel(levelId);
        if (level == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Level not found");
        }
        return level;
    }

    private void validateBenefit(BenefitDTO benefitDTO) {
        if (benefitDTO.getDescription() == null || benefitDTO.getDescription().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Benefit description is required");
        }
    }

    private void validateServiceItem(ServiceItemDTO serviceItemDTO) {
        if (serviceItemDTO.getName() == null || serviceItemDTO.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service name is required");
        }
        if (serviceItemDTO.getBadge() == null || serviceItemDTO.getBadge().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Service badge is required");
        }
    }

    @Override
    public Benefit addBenefit(Long levelId, BenefitDTO benefitDTO) {
        Level level = findLevel(levelId);
        validateBenefit(benefitDTO);
        Benefit benefit = Benefit.builder()
                .description(benefitDTO.getDescription().trim())
                .level(level)
                .build();
        return levelDao.saveBenefit(benefit);
    }

    @Override
    public Benefit updateBenefit(Long id, BenefitDTO benefitDTO) {
        Benefit benefit = levelDao.getBenefit(id);
        if (benefit == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benefit not found");
        }
        validateBenefit(benefitDTO);
        benefit.setDescription(benefitDTO.getDescription().trim());
        return levelDao.saveBenefit(benefit);
    }

    @Override
    public ServiceItem addServiceItem(Long levelId, ServiceItemDTO serviceItemDTO) {
        Level level = findLevel(levelId);
        validateServiceItem(serviceItemDTO);
        ServiceItem serviceItem = ServiceItem.builder()
                .name(serviceItemDTO.getName().trim())
                .description(serviceItemDTO.getDescription())
                .badge(serviceItemDTO.getBadge().trim())
                .level(level)
                .build();
        return levelDao.saveServiceItem(serviceItem);
    }

    @Override
    public ServiceItem updateServiceItem(Long id, ServiceItemDTO serviceItemDTO) {
        ServiceItem serviceItem = levelDao.getServiceItem(id);
        if (serviceItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found");
        }
        validateServiceItem(serviceItemDTO);
        serviceItem.setName(serviceItemDTO.getName().trim());
        serviceItem.setDescription(serviceItemDTO.getDescription());
        serviceItem.setBadge(serviceItemDTO.getBadge().trim());
        return levelDao.saveServiceItem(serviceItem);
    }

    @Override
    public Level updatePassCriteria(Long id, Integer passCriteria) {
        Level level = findLevel(id);
        if (passCriteria == null || passCriteria < 0 || passCriteria > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pass criteria must be between 0 and 100");
        }
        level.setPassCriteria(passCriteria);
        return levelDao.saveLevel(level);
    }

    @Override
    public void deleteBenefit(Long id) {
        if (levelDao.getBenefit(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Benefit not found");
        }
        levelDao.deleteBenefit(id);
    }

    @Override
    public void deleteServiceItem(Long id) {
        if (levelDao.getServiceItem(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Service not found");
        }
        levelDao.deleteServiceItem(id);
    }
}