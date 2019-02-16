package ua.ita.smartcarservice.service;


import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.entity.Car;

import java.util.List;

public interface CarService {

    List<CarDto> findByUserId(Long id);

    CarDto findByVin(String vin);

    CarDto getCarById(Long id);

    void create(Car car);

    List<CarDto> findAll ();

    void deleteById (Long id);

}
