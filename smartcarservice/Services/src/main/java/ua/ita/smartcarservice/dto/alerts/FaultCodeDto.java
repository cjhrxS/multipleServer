package ua.ita.smartcarservice.dto.alerts;



import lombok.Data;
import ua.ita.smartcarservice.entity.technicalservice.SkillEntity;

@Data
public class FaultCodeDto{

	 private long id;
	 private String faultCode;
	 private String description;
	 private String type;
	 private SkillEntity skill;
	 
	public FaultCodeDto(long id, String faultCode, String description, String type, SkillEntity skill) {
		this.id = id;
		this.faultCode = faultCode;
		this.description = description;
		this.type = type;
		this.skill = skill;
	}
}
