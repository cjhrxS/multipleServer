package ua.ita.smartcarservice.entity.technicalservice;

import lombok.Data;
import ua.ita.smartcarservice.entity.alerts.FaultCode;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "skills")
public class SkillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long skillId;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long requiredTime;

    @OneToMany(mappedBy = "workerId")
    private List<WorkersSkill> workersSkill;
    
    @OneToMany(fetch = FetchType.LAZY,
  		  cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
  		  mappedBy = "skill")
    private List<FaultCode> faultCode;

    public SkillEntity() {
    }

    public SkillEntity(String name, Long requiredTime) {
        this.name = name;
        this.requiredTime = requiredTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillEntity skill = (SkillEntity) o;
        return name.equals(skill.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
