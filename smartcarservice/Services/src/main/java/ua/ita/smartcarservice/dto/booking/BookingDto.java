package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.util.List;

@Data
public class BookingDto {

    List<String> workerId;

    String time;

    String numberOfDay;

    String needTime;

}
