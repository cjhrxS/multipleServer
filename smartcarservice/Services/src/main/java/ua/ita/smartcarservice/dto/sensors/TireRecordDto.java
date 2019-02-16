package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TireRecordDto extends ARecordDto {

    private Map<String, Double> value;

}
