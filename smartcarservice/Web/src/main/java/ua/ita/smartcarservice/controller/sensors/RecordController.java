package ua.ita.smartcarservice.controller.sensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.sensors.RecordDto;
import ua.ita.smartcarservice.dto.sensors.TireRecordDto;
import ua.ita.smartcarservice.service.sensors.SensorService;

@RestController
@RequestMapping(value = "/api/record")
public class RecordController {

    @Autowired
    @Qualifier("basic")
    private SensorService sensorService;

    @Autowired
    @Qualifier("tire")
    private SensorService tireService;

    @PostMapping
    public void addRecord(@RequestBody RecordDto recordDto){
        sensorService.addRecord(recordDto);
    }

    @PostMapping("/tire")
    public void addTireRecord(@RequestBody TireRecordDto recordDto){
        tireService.addRecord(recordDto);
    }
}
