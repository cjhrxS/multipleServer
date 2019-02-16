package ua.ita.smartcarservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.entity.Car;

import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;


    //for used car
    public void create (Car car){
        carRepository.save(car);
    }

    public List<CarDto> findAll() {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findAll()) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public List<CarDto> findByUserId(Long id) {
        List<CarDto> carDtos = new ArrayList<>();

        for (Car car : carRepository.findByUserId(id)) {
            carDtos.add(getCarDto(car));
        }
        return carDtos;
    }

    public CarDto getCarById(Long id) {
        Car car = carRepository.getCarById(id);
        CarDto carDto = getCarDto(car);
        return carDto;
    }

    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    public CarDto findByVin(String vin) {
        Car car = carRepository.findByVin(vin);
        CarDto carDto = getCarDto(car);
        return carDto;
    }


    //for Car => CarDto
    public CarDto getCarDto(Car car) {
        CarDto carDto = new CarDto(car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getGraduation_year(),
                car.getNumber(),
                car.getPrice(),
                car.getVin(),
                car.getEnd_guarantee(),
                car.getDealer(),
                car.getUser(),
                car.getVehicleInspections());

        return carDto;
    }

    //for CarDto => CarDto
    public Car getCar(CarDto carDto) {
        Car car = new Car(
                carDto.getBrand(),
                carDto.getModel(),
                carDto.getGraduation_year(),
                carDto.getNumber(),
                carDto.getVin(),
                carDto.getCarOwner());
        return car;
    }
}
