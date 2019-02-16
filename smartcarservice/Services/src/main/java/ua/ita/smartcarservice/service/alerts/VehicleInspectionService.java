package ua.ita.smartcarservice.service.alerts;

import java.util.Date;
import java.util.List;
import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;

public interface VehicleInspectionService {
	
	void saveVehicleInspection (VehicleInspectionDto vehicleInspectionDto);

	List<VehicleInspectionDto> getCarsForYearlyInspection ();
	
	VehicleInspectionDto getVehicleInspection(long id);

	VehicleInspection getLastVehicleInspection(long carId);
	
	Date getDateOfLastVehicleInspection(long carId);
	
	List<Car> getCarsForVehicleInspectionByMileage();
}
