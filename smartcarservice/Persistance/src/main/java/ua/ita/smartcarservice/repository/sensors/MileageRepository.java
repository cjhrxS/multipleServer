package ua.ita.smartcarservice.repository.sensors;

import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.sensors.MileageEntity;

@Repository
public interface MileageRepository extends SensorRepository<MileageEntity> {
}
