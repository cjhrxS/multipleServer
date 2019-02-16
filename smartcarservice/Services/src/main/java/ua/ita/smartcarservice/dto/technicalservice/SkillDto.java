package ua.ita.smartcarservice.dto.technicalservice;

import lombok.Data;

import java.util.List;

@Data
public class SkillDto {

    private Long id;

    private String name;

    private Long requiredTime;

   // private List<FaultCode> faultCode;
}
