package ua.ita.smartcarservice.repository.alerts;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.alerts.VehicleInspection;

@Repository
public interface VehicleInspectionRepository extends JpaRepository<VehicleInspection, Long> {

	@Query ("select v from VehicleInspection v inner join v.car c "
			+ "where c.id = :carId and v.dateOfInspection "
			+ "in (select max(dateOfInspection) from VehicleInspection)")
	VehicleInspection getLastVehicleInspection (@Param ("carId") long carId);
	
	@Query ("select v.dateOfInspection from VehicleInspection v inner join v.car c "
			+ "where c.id = :carId and v.dateOfInspection "
			+ "in (select max(dateOfInspection) from VehicleInspection)")
	Date getDateOfLastVehicleInspection (@Param ("carId") long carId);
	
	//  select all where: lastInspectionDate away from todays date unless on 365 days and
	//  car has not have an appointment on vehicle inspection
	//  skill_id = 7 is vehicle inspectors id 
	@Query (value = 
			"select * from vehicle_inspection v\r\n" + 
			"where v.date_of_inspection in\r\n" + 
			"	(select max(date_of_inspection) from vehicle_inspection\r\n" + 
			"	where DATE(date_of_inspection) <= date_sub(NOW(), INTERVAL 365 DAY)\r\n" + 
			"	group by car_id)\r\n" + 
			"AND v.car_id not in \r\n" + 
			"	(select car_id from schedule s\r\n" + 
			"	where s.worker_id in \r\n" + 
			"		(select ws.worker_id from workers_skills ws\r\n" + 
			"		where ws.skill_id = 7)\r\n" + 
			"	);" , 
			nativeQuery = true)
	List<VehicleInspection> getCarsForYearlyInspection ();
	
	 @Query("select c from Car c "
			+ "inner join c.vehicleInspections v "
			+ "inner join c.mileageEntities f "
			+ "where v.dateOfInspection in "
			+ 	"(select max(vi.dateOfInspection) from c.vehicleInspections vi  "
			+ 	"group by c.id) "
			+ "AND f.id in"
			+ 	"(select max(me.id) from c.mileageEntities me) "
			+ "AND "
			+ 	"(f.value - v.mileageOfCar) >= 10"
			)
	List<Car> getCarsForInspectionByMileage();
}




//"select * from vehicle_inspection v \r\n" + 
//"where v.date_of_inspection in\r\n" + 
//"	(select max(date_of_inspection) from vehicle_inspection \r\n" + 
//"	where DATE(date_of_inspection) <= date_sub(NOW(), INTERVAL 365 DAY)\r\n" + 
//"	group by car_id)\r\n" + 
//"AND v.car_id not in \r\n" + 
//"	(select id from schedule s\r\n" + 
//"	inner join user w on w.worker_id = s.worker_id \r\n" + 
//"	where skill_id = 7);"
