package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.BatteryEntity;

@Repository
public interface BatteryRepository extends SensorRepository<BatteryEntity> {
}
