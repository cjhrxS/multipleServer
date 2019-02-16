package ua.ita.smartcarservice.service.technicalservice;

import ua.ita.smartcarservice.dto.booking.WorkerDto;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.TechnicalServiceEntity;

import java.util.List;

public interface WorkerService {

    void saveWorker(UserEntity workerEntity, Long skillId,
                    TechnicalServiceEntity technicalServiceEntity);

    void updateWorker(UserEntity workerEntity, SkillEntity skillEntity);

    void deleteWorker(Long workerId) throws Exception;

    List<WorkerDto> getByUserTechnicalServiceAndWorkersSkill(String name, Long stoId);

    List<WorkerDto> getByCarIdAndWorkersSkill(String name, Long carId);

    List<UserEntity> getWorkersByTechnicalServiceId(TechnicalServiceEntity technicalServiceEntity);

    public List<UserEntity> getAllWorkers();
}
