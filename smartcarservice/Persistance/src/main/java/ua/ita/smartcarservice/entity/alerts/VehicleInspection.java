package ua.ita.smartcarservice.entity.alerts;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import ua.ita.smartcarservice.entity.Car;

@Data
@Entity
@Table(name = "vehicle_inspection")
public class VehicleInspection {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

	@Column(name = "date_of_inspection")
	private Date dateOfInspection;
	
	@Column(name = "mileage_of_car")
	private Integer mileageOfCar;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;

	public VehicleInspection() {
	}
	
	public VehicleInspection(long id, Date dateOfInspection, Integer mileageOfCar, Car car) {
		this.id = id;
		this.dateOfInspection = dateOfInspection;
		this.mileageOfCar = mileageOfCar;
		this.car = car;
	}
}
