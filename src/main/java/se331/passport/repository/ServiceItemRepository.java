package se331.passport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import se331.passport.entity.ServiceItem;

public interface ServiceItemRepository extends JpaRepository<ServiceItem,Long> {
    List<ServiceItem> findByLevel_LevelNumberLessThanEqualOrderByIdAsc(int levelNumber);
}
