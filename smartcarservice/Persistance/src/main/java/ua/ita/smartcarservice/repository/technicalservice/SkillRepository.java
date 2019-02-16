package ua.ita.smartcarservice.repository.technicalservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<SkillEntity, Long> {

    SkillEntity getBySkillId(SkillEntity skillEntity);

    @Query("select distinct w.skill from WorkersSkill as w " +
            "left join UserTechnicalService as ut on ut.userId = w.workerId " +
            "where ut.technicalServiceId.technicalServiceId = :stoId")
    List<SkillEntity> getSkillNameBySto(@Param("stoId") Long stoId);


    @Query("select distinct w.skill from WorkersSkill as w " +
            "left join UserTechnicalService as ut on w.workerId = " +
            "(select ut.userId from UserTechnicalService as ut " +
            "left join Car as c on ut.userId = c.user " +
            "where c.id = :id)")
    List<SkillEntity> getSkillNameByCarId(@Param("id") Long id);
}
