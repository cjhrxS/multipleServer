package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.technicalservice.SkillDto;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.service.technicalservice.SkillService;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of Skill service
 */
@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository repository;

    /*
    Method for getting all the Skills from DB
     */
    @Override
    public List<SkillDto> getAllSkills() {
        List<SkillDto> allSkill = new ArrayList<>();
        for (SkillEntity skill : repository.findAll()) {
            allSkill.add(getSkillDto(skill));
        }
        return allSkill;
    }

    /*
    Method converts Skill entity to DTO
     */
    @Override
    public SkillDto getSkillDto(SkillEntity skill) {
        SkillDto skillDto = new SkillDto();
        skillDto.setId(skill.getSkillId());
        skillDto.setName(skill.getName());
        //skillDto.setFaultCode(skill.getFaultCode());
        skillDto.setRequiredTime(skill.getRequiredTime());
        return skillDto;
    }

    @Override
    public List<SkillDto> getSkillNameBySto(Long id){
        List<SkillDto> skillBySto = new ArrayList <>();
        for(SkillEntity skill : repository.getSkillNameBySto(id)){
            skillBySto.add(getSkillDto(skill));
        }
        return skillBySto;
    }

    @Override
    public List<SkillDto> getSkillNameByCarId(Long carId){
        List<SkillDto> skillBySto = new ArrayList <>();
        for(SkillEntity skill : repository.getSkillNameByCarId(carId)){
            skillBySto.add(getSkillDto(skill));
        }
        return skillBySto;
    }


    /*
    Method returns Skill entity by id
     */
    @Override
    public SkillEntity getSkillById(Long id) {
        return repository.findById(id).get();
    }
}
