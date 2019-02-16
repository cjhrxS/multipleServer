package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.OilPressureEntity;

@Repository
public interface OilPressureRepository extends SensorRepository<OilPressureEntity> {
}
