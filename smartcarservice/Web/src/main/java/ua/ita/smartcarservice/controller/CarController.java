package ua.ita.smartcarservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.ita.smartcarservice.dto.CarDto;
import ua.ita.smartcarservice.dto.UserDto;
import ua.ita.smartcarservice.entity.UserEntity;

import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.service.CarService;
import ua.ita.smartcarservice.service.UserService;

import ua.ita.smartcarservice.service.impl.CarServiceImpl;
import ua.ita.smartcarservice.service.impl.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private UserService userService;

    /*   Delete car by id*/
    @DeleteMapping("/car/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        try {
            CarDto car = carService.getCarById(id);
            if (car.getId() == id) {
                carService.deleteById(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (NullPointerException e) {        //which exeption???
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /* Get car by id*/
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {

        CarDto car;
        try {
            car = carService.getCarById(id);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Get all cars */
    @GetMapping("/cars/all")
    public ResponseEntity<List<CarDto>> findAll() {
        List<CarDto> cars = carService.findAll();
        if (cars.isEmpty()) {
            return new ResponseEntity<>(cars, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }


    /* Get owner's cars */
    @GetMapping("/ownercars/{id}")
    public ResponseEntity<List<CarDto>> findByUserId(@PathVariable Long id) {
        List<CarDto> cars = carService.findByUserId(id);
        if (cars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    /*  Get car by vin */
    @GetMapping("/carbyvin/{vin}")
    public ResponseEntity<CarDto> findByVin(@PathVariable String vin) {
        CarDto car;
        try {
            car = carService.findByVin(vin);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    /* Create car */
    @PutMapping("/ucar")
    public ResponseEntity createCar(@RequestParam(value = "brand") String brand,
                                    @RequestParam(value = "model") String model,
                                    @RequestParam(value = "graduationyear") String graduation_year,
                                    @RequestParam(value = "number") String number,
                                    @RequestParam(value = "vin") String vin,
                                    @RequestParam(value = "username") String username) {

        UserEntity carOwner = this.userService.findUser(username);

        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setGraduation_year(graduation_year);
        car.setNumber(number);
        car.setVin(vin);
        car.setUser(carOwner);
        carService.create(car);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}
