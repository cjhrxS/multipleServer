package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class WorkerWithSkillDto {

    private List<String> name;

    private Long searchId;

}
