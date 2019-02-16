package ua.ita.smartcarservice.entity.sensors;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "fact_oil_level")
public class OilLevelEntity extends SensorEntity implements ISensorEntity {

}