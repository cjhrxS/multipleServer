package ua.ita.smartcarservice.controller.technicalservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.dto.booking.WorkerWithSkillDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.util.HashMap;
import java.util.List;

@RestController
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @PostMapping("/api/workerBySkill")
    public ResponseEntity<HashMap<String, List<WorkerDto>>> getAllBySkillAndSto(@RequestBody WorkerWithSkillDto
                                                                                        workerWithSkillDto) {
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap<>();
        for (String s : workerWithSkillDto.getName()) {
            workersBySkillName.put(s, workerService.getByUserTechnicalServiceAndWorkersSkill(s, workerWithSkillDto.getSearchId()));
        }

        return new ResponseEntity<>(workersBySkillName, HttpStatus.OK);
    }

    @PostMapping("/api/workerByCar")
    public ResponseEntity<HashMap<String, List<WorkerDto>>> getAllByCarAndSto(@RequestBody WorkerWithSkillDto
                                                                                        workerWithSkillDto) {
        HashMap<String, List<WorkerDto>> workersBySkillName = new HashMap<>();
        for (String s : workerWithSkillDto.getName()) {
            workersBySkillName.put(s, workerService.getByCarIdAndWorkersSkill(s, workerWithSkillDto.getSearchId()));
        }

        return new ResponseEntity<>(workersBySkillName, HttpStatus.OK);
    }

    @GetMapping("/api/v1/workers")
    public ResponseEntity<List<UserEntity>> getAllWorkers() {
        ResponseEntity<List<UserEntity>> responseEntity;

        try{
            responseEntity = new ResponseEntity<>(workerService.getAllWorkers(), HttpStatus.OK);
        } catch(Exception e){
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }


}

