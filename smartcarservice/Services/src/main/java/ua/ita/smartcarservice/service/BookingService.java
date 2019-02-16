package ua.ita.smartcarservice.service;

import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

public interface BookingService {

    HashMap <LocalDate, List <WorkTimeDto>> findTimeToBooking(BookingDto bookingDto);

    boolean addSession(NewBookingDto newSessionDto);

    List <WorkTimeDto> findAllByWorkerId(Long workerId);
}
