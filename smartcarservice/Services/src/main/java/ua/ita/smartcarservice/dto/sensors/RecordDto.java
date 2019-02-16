package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecordDto extends ARecordDto {

    private double value;
}
