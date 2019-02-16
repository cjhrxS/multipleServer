package ua.ita.smartcarservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import ua.ita.smartcarservice.entity.alerts.VehicleInspection;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import ua.ita.smartcarservice.entity.sales.DealerEntity;
import ua.ita.smartcarservice.entity.sensors.MileageEntity;

import javax.persistence.*;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;
import java.util.List;
import java.util.Set;

@EntityScan
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false, unique = false)
    private String brand;

    @Column(length = 20, nullable = false, unique = false)
    private String model;


    @Column(length = 20, nullable = false, unique = false)
    private String graduation_year;


    @Column(length = 100, nullable = false, unique = true)
    private String number;

    @Column(nullable = true, unique = false)
    private Double price;


    @Column(nullable = false, unique = true)
    private String vin;

    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date end_guarantee;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "dealer_id", nullable = true)
    private DealerEntity dealer;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = true)
    private UserEntity user;

    @JsonIgnore
    @OneToMany(mappedBy = "car")
    private Set<VehicleInspection> vehicleInspections;

    @JsonIgnore
    @OneToMany (mappedBy = "car")
    private Set<WorkTime> workTimes;
    
    //needed for getByMileage method
  	@OneToMany(fetch = FetchType.LAZY, mappedBy = "car")
  	private List<MileageEntity> mileageEntities;

    public Car() {
    }

    //For new cars
    public Car(String brand, String model, String graduation_year, String number, Double price, String vin,
               Date end_guarantee, DealerEntity dealer, UserEntity user, Set<VehicleInspection> vehicleInspections) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.price = price;
        this.vin = vin;
        this.end_guarantee = end_guarantee;
        this.dealer = dealer;
        this.user = user;
        this.vehicleInspections = vehicleInspections;
    }

    //For used cars
    public Car(String brand, String model, String graduation_year, String number, String vin, UserEntity user) {
        this.brand = brand;
        this.model = model;
        this.graduation_year = graduation_year;
        this.number = number;
        this.vin = vin;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getGraduation_year() {
        return graduation_year;
    }

    public void setGraduation_year(String graduation_year) {
        this.graduation_year = graduation_year;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getEnd_guarantee() {
        return end_guarantee;
    }

    public void setEnd_guarantee(Date end_guarantee) {
        this.end_guarantee = end_guarantee;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<VehicleInspection> getVehicleInspections() {
        return vehicleInspections;
    }

    public void setVehicleInspections(Set<VehicleInspection> vehicleInspections) {
        this.vehicleInspections = vehicleInspections;
    }

    public DealerEntity getDealer() {
        return dealer;
    }

    public void setDealer(DealerEntity dealer) {
        this.dealer = dealer;
    }
}