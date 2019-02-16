package ua.ita.smartcarservice.service.impl.alerts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.ita.smartcarservice.dto.alerts.VehicleInspectionDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;
import ua.ita.smartcarservice.repository.alerts.VehicleInspectionRepository;
import ua.ita.smartcarservice.service.alerts.VehicleInspectionService;
@Service
public class VehicleInspectionImpl implements VehicleInspectionService{

	@Autowired
	private VehicleInspectionRepository vehicleInspectionRepository;
	
	@Override
	public void saveVehicleInspection(VehicleInspectionDto vehicleInspectionDto) {
		VehicleInspection entity = new VehicleInspection(
				vehicleInspectionDto.getId(),
				vehicleInspectionDto.getDateOfInspection(),
				vehicleInspectionDto.getMileageOfCar(), 
				vehicleInspectionDto.getCar());
		vehicleInspectionRepository.save(entity);
	}

	@Override
	public List<VehicleInspectionDto> getCarsForYearlyInspection() {
		List<VehicleInspectionDto> toReturn = new ArrayList();
		for (VehicleInspection v : vehicleInspectionRepository.getCarsForYearlyInspection()) {
			toReturn.add(new VehicleInspectionDto(v));
		}
		return toReturn;
	}

	@Override
	public VehicleInspectionDto getVehicleInspection(long id) {
		VehicleInspection entity  = vehicleInspectionRepository.getOne(id);
		VehicleInspectionDto dto = new VehicleInspectionDto(entity.getId(), entity.getDateOfInspection(), entity.getMileageOfCar(), entity.getCar());
		return dto;
	}

	@Override
	public List<Car> getCarsForVehicleInspectionByMileage() {
		return vehicleInspectionRepository.getCarsForInspectionByMileage();
	}

	@Override
	public VehicleInspection getLastVehicleInspection(long carId) {
		return vehicleInspectionRepository.getLastVehicleInspection(carId);
	}

	@Override
	public Date getDateOfLastVehicleInspection(long carId) {
		return vehicleInspectionRepository.getDateOfLastVehicleInspection(carId);
	}
	
}
