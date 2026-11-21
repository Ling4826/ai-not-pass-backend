package se331.passport.dao;

import java.util.List;

import se331.passport.entity.Benefit;
import se331.passport.entity.ServiceItem;

public interface LevelDetailDao {
    List<Benefit> getBenefitsByLevel(int levelNumber);

    List<ServiceItem> getServicesUpToLevel(int levelNumber);
}
