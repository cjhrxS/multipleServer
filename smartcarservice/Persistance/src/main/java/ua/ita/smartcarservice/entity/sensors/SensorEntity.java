package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class SensorEntity extends BaseSensorEntity{

    @Column(nullable = false)
    protected double value;

}
