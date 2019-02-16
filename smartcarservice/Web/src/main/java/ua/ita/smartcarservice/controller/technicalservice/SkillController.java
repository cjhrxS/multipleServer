package ua.ita.smartcarservice.controller.technicalservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.service.technicalservice.SkillService;

import java.util.List;

/**
 * Controller (REST) that works only with skills
 * Opportunities: Get all the skills
 */
@RestController
public class SkillController {

    @Autowired
    private SkillService skillService;

    @GetMapping("/api/skillBySto/{id}")
    public ResponseEntity<List<SkillDto>> getSkillByStoId(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillNameBySto(id), HttpStatus.OK);
    }

    @GetMapping("/api/skillByCar/{id}")
    public ResponseEntity<List<SkillDto>> getSkillByCarId(@PathVariable Long id) {
        return new ResponseEntity<>(skillService.getSkillNameByCarId(id), HttpStatus.OK);
    }

    /*
     * Method for getting all the skills
     */
    @GetMapping("/api/v1/skills")
    public ResponseEntity<List<SkillDto>> getAllSkill() {

        ResponseEntity<List<SkillDto>> responseEntity;

        try {
            responseEntity = new ResponseEntity<>(skillService.getAllSkills(), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return responseEntity;
    }


}
