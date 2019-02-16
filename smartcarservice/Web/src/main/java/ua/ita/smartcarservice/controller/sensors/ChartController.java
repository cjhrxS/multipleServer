package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sensors.DateForChartDto;
import ua.ita.smartcarservice.dto.sensors.IChartDto;
import ua.ita.smartcarservice.service.sensors.SensorService;
import ua.ita.smartcarservice.service.sensors.SensorServiceFactory;

@RestController
@RequestMapping(value = "/api/chart")
public class ChartController {

    @Autowired
    private SensorServiceFactory serviceFactory;

    private SensorService getService(DateForChartDto dateForChartDto){
        return serviceFactory.getService(dateForChartDto.getSensorType());
    }

    @GetMapping("/day")
    public ResponseEntity<IChartDto> getAllByDay(@RequestParam(value = "sensorType") String sensorType,
                                                 @RequestParam(value = "carId") Long carId,
                                                 @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getAllByDay(dateForChartDto));
    }

    @GetMapping("/month")
    public ResponseEntity<IChartDto> getAllByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getAvgByMonth(dateForChartDto));
    }

    @GetMapping("/month/min")
    public ResponseEntity<IChartDto> getMinByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getMinByMonth(dateForChartDto));
    }

    @GetMapping("/month/max")
    public ResponseEntity<IChartDto> getMaxByMonth(@RequestParam(value = "sensorType") String sensorType,
                                                   @RequestParam(value = "carId") Long carId,
                                                   @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getMaxByMonth(dateForChartDto));
    }

    @GetMapping("/year")
    public ResponseEntity<IChartDto> getAllByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getAvgByYear(dateForChartDto));
    }

    @GetMapping("/year/min")
    public ResponseEntity<IChartDto> getMinByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getMinByYear(dateForChartDto));
    }

    @GetMapping("/year/max")
    public ResponseEntity<IChartDto> getMaxByYear(@RequestParam(value = "sensorType") String sensorType,
                                                  @RequestParam(value = "carId") Long carId,
                                                  @RequestParam(value = "date") String date){
        DateForChartDto dateForChartDto = new DateForChartDto(sensorType, carId, date);
        return getResponse(getService(dateForChartDto).getMaxByYear(dateForChartDto));
    }

    private ResponseEntity<IChartDto> getResponse(IChartDto chartDto){
        if(chartDto.dataSize() == 0)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        System.out.println(chartDto.toString());


        return new ResponseEntity<>(chartDto, HttpStatus.OK);
    }
}
