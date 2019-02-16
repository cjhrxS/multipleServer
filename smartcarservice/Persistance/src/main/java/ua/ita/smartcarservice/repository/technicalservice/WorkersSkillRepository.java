package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;
import ua.ita.smartcarservice.entity.technicalservice.WorkersSkill;

@Repository
public interface WorkersSkillRepository extends JpaRepository<WorkersSkill, Long> {
    WorkersSkill getBySkill(SkillEntity skillEntity);
}
