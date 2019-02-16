package ua.ita.smartcarservice.dto.sensors;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateForChartDto {

    private String sensorType;

    private long carId;

    private String date;

    public DateForChartDto(String sensorType, long carId, String date) {
        this.sensorType = sensorType;
        this.carId = carId;
        this.date = date.replace('%', ' ');
    }
}
