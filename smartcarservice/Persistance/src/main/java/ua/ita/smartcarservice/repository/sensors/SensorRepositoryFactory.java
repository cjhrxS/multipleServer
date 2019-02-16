package ua.ita.smartcarservice.repository.sensors;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;
import ua.ita.smartcarservice.entity.sensors.common.SensorTypes;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorRepositoryFactory {

    @Autowired
    private SpeedRepository speedRepository;

    @Autowired
    private BatteryRepository batteryRepository;

    @Autowired
    private FuelRepository fuelRepository;

    @Autowired
    private MileageRepository mileageRepository;

    @Autowired
    private OilPressureRepository oilPressureRepository;

    @Autowired
    private OilLevelRepository oilLevelRepository;


    private Map<String, SensorRepository<? extends BaseSensorEntity>> repositoryFactory = new HashMap<>();

    public SensorRepository<? extends BaseSensorEntity> getRepository(String type) {
        if (repositoryFactory.size() == 0)
            repositoryInit();
        return repositoryFactory.get(type);
    }

    private void repositoryInit() {
        repositoryFactory.put(SensorTypes.SPEED.toString(), speedRepository);
        repositoryFactory.put(SensorTypes.BATTERY.toString(), batteryRepository);
        repositoryFactory.put(SensorTypes.FUEL.toString(), fuelRepository);
        repositoryFactory.put(SensorTypes.MILEAGE.toString(), mileageRepository);
        repositoryFactory.put(SensorTypes.OIL_PRESSURE.toString(), oilPressureRepository);
        repositoryFactory.put(SensorTypes.OIL_LEVEL.toString(), oilLevelRepository);
    }

}
