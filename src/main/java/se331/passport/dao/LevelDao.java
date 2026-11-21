package se331.passport.dao;

import se331.passport.entity.Level;
import se331.passport.entity.Benefit;
import se331.passport.entity.ServiceItem;

import java.util.List;

public interface LevelDao {
    List<Level> getLevels();
    Level getLevel(Long id);
    Benefit getBenefit(Long id);
    Benefit saveBenefit(Benefit benefit);
    ServiceItem getServiceItem(Long id);
    ServiceItem saveServiceItem(ServiceItem serviceItem);
    Level saveLevel(Level level);
    void deleteBenefit(Long id);
    void deleteServiceItem(Long id);
}