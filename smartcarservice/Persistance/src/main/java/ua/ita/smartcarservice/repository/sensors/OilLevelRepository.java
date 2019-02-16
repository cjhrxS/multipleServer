package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.OilLevelEntity;

@Repository
public interface OilLevelRepository extends SensorRepository<OilLevelEntity> {
}
