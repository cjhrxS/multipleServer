package ua.ita.smartcarservice.service.impl.sensors;

import lombok.Data;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ParamsProvider {

    LocalDateTime date;
    Long carId;

}
