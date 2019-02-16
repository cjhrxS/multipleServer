package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.FuelEntity;

@Repository
public interface FuelRepository extends SensorRepository<FuelEntity> {
}
