package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.SpeedEntity;

@Repository
public interface SpeedRepository extends SensorRepository<SpeedEntity> {
}
