package ua.ita.smartcarservice.service.sensors;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.common.SensorTypes;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorServiceFactory {

    @Autowired
    @Qualifier("basic")
    private SensorService sensorService;

    @Autowired
    @Qualifier("tire")
    private SensorService tireService;


    private Map<String, SensorService> serviceFactory = new HashMap<>();

    public SensorService getService(String type) {
        return (type.equals(SensorTypes.TIRE_PRESSURE.toString()))
                ? tireService : sensorService;
    }

}
