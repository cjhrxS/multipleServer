package ua.ita.smartcarservice.entity.sensors.common;

import lombok.Data;
import org.springframework.stereotype.Component;
import ua.ita.smartcarservice.entity.sensors.ISensorEntity;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
public class SensorEntityFactory {

    private Map<String, Class> entityFactory = new HashMap<>();

    public SensorEntityFactory() {
        entityInit();
    }

    private void entityInit() {
        for (SensorEntities entity : SensorEntities.values()) {
            entityFactory.put(entity.getSensorType(), entity.getSensorEntity());
        }
    }

    public ISensorEntity getEntity(String type) {
        try {
            return (ISensorEntity) entityFactory.get(type).newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
