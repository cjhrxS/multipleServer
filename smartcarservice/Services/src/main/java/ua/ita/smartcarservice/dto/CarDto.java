package ua.ita.smartcarservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;
import ua.ita.smartcarservice.entity.sales.DealerEntity;

import java.util.Date;
import java.util.Set;

@Data
public class CarDto {

    private Long id;
    private String brand;
    private String model;
    private String graduation_year;
    private String number;
    private Double price;
    private String vin;
    private Date end_guarantee;
    private DealerEntity dealerEntity;
    private UserEntity carOwner;

    @JsonIgnore
    private Set<VehicleInspection> vehicleInspections;

    public CarDto() {
    }

    //for new car
    public CarDto(Long id, String brand, String model, String graduation_year, String number, Double price, String vin, Date end_guarantee, DealerEntity dealer, UserEntity carOwner, Set<VehicleInspection> vehicleInspections) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.end_guarantee = end_guarantee;
        this.dealerEntity = dealer;
        this.carOwner = carOwner;
        this.vehicleInspections = vehicleInspections;
    }

    //for user car
    public CarDto(String brand, String model, String graduation_year, String number, String vin, UserEntity carOwner) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.carOwner = carOwner;
    }


}
