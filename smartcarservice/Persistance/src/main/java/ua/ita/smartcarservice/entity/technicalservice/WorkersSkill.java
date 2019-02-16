package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "workers_skills")
public class WorkersSkill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private UserEntity workerId;

    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private SkillEntity skill;

    public WorkersSkill() {
    }

    public WorkersSkill(UserEntity workerId, SkillEntity skill) {
        this.workerId = workerId;
        this.skill = skill;
    }
}
