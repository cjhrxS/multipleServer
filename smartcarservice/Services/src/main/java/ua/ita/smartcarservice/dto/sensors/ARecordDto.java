package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;

@Data
public abstract class ARecordDto {

    private String sensorType;

    private String carVin;

    private String date;

}
