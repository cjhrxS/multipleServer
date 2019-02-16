package ua.ita.smartcarservice.dto.alerts;

import java.sql.Date;
import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;

@Data
public class VehicleInspectionDto {
	
	private long id;
	private Date dateOfInspection;
	private Integer mileageOfCar;
	private Car car;
	
	public VehicleInspectionDto(long id, Date dateOfInspection, Integer mileageOfCar, Car car) {
		this.id = id;
		this.dateOfInspection = dateOfInspection;
		this.mileageOfCar = mileageOfCar;
		this.car = car;
	}
	
	public VehicleInspectionDto(VehicleInspection vehicleInspection) {
		this.id = vehicleInspection.getId();
		this.dateOfInspection = vehicleInspection.getDateOfInspection();
		this.mileageOfCar = vehicleInspection.getMileageOfCar();
		this.car = vehicleInspection.getCar();
	}

}

