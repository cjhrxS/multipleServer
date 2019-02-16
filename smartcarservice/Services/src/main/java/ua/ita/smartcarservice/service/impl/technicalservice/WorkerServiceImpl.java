package ua.ita.smartcarservice.service.impl.technicalservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;
import ua.ita.smartcarservice.entity.technicalservice.UserTechnicalService;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.technicalservice.SkillRepository;
import ua.ita.smartcarservice.repository.technicalservice.TechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.UserTechnicalServiceRepository;
import ua.ita.smartcarservice.repository.technicalservice.WorkersSkillRepository;
import ua.ita.smartcarservice.service.technicalservice.WorkerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillRepository skillRepository;

    @Autowired
    WorkersSkillRepository workersSkillRepository;

    @Autowired
    TechnicalServiceRepository technicalServiceRepository;

    @Autowired
    UserTechnicalServiceRepository userTechnicalServiceRepository;

    @Override
    public void saveWorker(UserEntity workerEntity, Long skillId, TechnicalServiceEntity technicalServiceEntity) {
        UserEntity createdWorkerEntity = userRepository.save(workerEntity);
        SkillEntity skillEntity = skillRepository.getOne(skillId);

        workersSkillRepository.save(new WorkersSkill(createdWorkerEntity, skillEntity));
        userTechnicalServiceRepository.save(new UserTechnicalService(workerEntity, technicalServiceEntity));
    }

    @Override
    public List<UserEntity> getAllWorkers() {

        List<UserEntity> list = userRepository.getUserEntitiesByRoleName("ROLE_WORKER");
        return list;
    }

    @Override
    public void updateWorker(UserEntity workerEntity, SkillEntity skillEntity) {
        userRepository.save(workerEntity);
        workersSkillRepository.save(new WorkersSkill(workerEntity, skillEntity));
    }

    @Override
    public void deleteWorker(Long workerId) throws Exception {
        userRepository.deleteById(workerId);
    }


    @Override
    public List<WorkerDto> getByUserTechnicalServiceAndWorkersSkill(String name, Long stoId) {
        List<WorkerDto> workerDtos = new ArrayList<>();
       /* for (UserEntity worker : userRepository.getByUserTechnicalServiceAndWorkersSkill(name, stoId)) {
            workerDtos.add(getWorkerDto(worker));
        }*/
        return workerDtos;
    }

    @Override
    public List<WorkerDto> getByCarIdAndWorkersSkill(String name, Long carId) {
        List <WorkerDto> workerDtos = new ArrayList<>();
        for(UserEntity worker : userRepository.getByCarIdAndWorkersSkill(name, carId)){
            workerDtos.add(getWorkerDto(worker));
        }
        return workerDtos;
    }

    @Override
    public List<UserEntity> getWorkersByTechnicalServiceId(TechnicalServiceEntity technicalServiceEntity) {
        UserTechnicalService userTechnicalService = userTechnicalServiceRepository.getByTechnicalServiceId(technicalServiceEntity);


        return userRepository.getByUserTechnicalService(userTechnicalService);
    }

    private WorkerDto getWorkerDto(UserEntity worker) {
        WorkerDto workerDto = new WorkerDto();

        workerDto.setWorkerId(worker.getId());
        workerDto.setFullName(worker.getFullName());

        return workerDto;
    }
}
