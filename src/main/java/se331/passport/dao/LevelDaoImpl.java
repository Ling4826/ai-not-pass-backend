package se331.passport.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import se331.passport.entity.Level;
import se331.passport.repository.LevelRepository;
import se331.passport.entity.Benefit;
import se331.passport.entity.ServiceItem;
import se331.passport.repository.BenefitRepository;
import se331.passport.repository.ServiceItemRepository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LevelDaoImpl implements LevelDao {
    final LevelRepository levelRepository;
    final BenefitRepository benefitRepository;
    final ServiceItemRepository serviceItemRepository;

    @Override
    public List<Level> getLevels() {
        return levelRepository.findAll(Sort.by("levelNumber"));
    }

    @Override
    public Level getLevel(Long id) {
        return levelRepository.findById(id).orElse(null);
    }

    @Override
    public Benefit getBenefit(Long id) {
        return benefitRepository.findById(id).orElse(null);
    }

    @Override
    public Benefit saveBenefit(Benefit benefit) {
        return benefitRepository.save(benefit);
    }

    @Override
    public ServiceItem getServiceItem(Long id) {
        return serviceItemRepository.findById(id).orElse(null);
    }

    @Override
    public ServiceItem saveServiceItem(ServiceItem serviceItem) {
        return serviceItemRepository.save(serviceItem);
    }

    @Override
    public Level saveLevel(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void deleteBenefit(Long id) {
        benefitRepository.deleteById(id);
    }

    @Override
    public void deleteServiceItem(Long id) {
        serviceItemRepository.deleteById(id);
    }
}