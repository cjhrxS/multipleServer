package ua.ita.smartcarservice.dto.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

@Data
public class WorkerSkillDto {
    private UserEntity workerEntity;
    private SkillEntity skillEntity;
}
