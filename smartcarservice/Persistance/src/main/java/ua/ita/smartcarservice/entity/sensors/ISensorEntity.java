package ua.ita.smartcarservice.entity.sensors;


import ua.ita.smartcarservice.entity.Car;

import java.time.LocalDateTime;

public interface ISensorEntity {

    void setDate(LocalDateTime date);

    LocalDateTime getDate();

    void setValue(double value);

    double getValue();

    Car getCar();

    void setCar(Car car);

}
