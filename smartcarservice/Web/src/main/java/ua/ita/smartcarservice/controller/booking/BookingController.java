package ua.ita.smartcarservice.controller.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.service.BookingService;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/api/sessionById")
    public ResponseEntity <List <WorkTimeDto>> findAllByWorker_WorkerId(@RequestParam(value = "workerId", required = false)
                                                                                String workerId) {
        return new ResponseEntity <>(bookingService.findAllByWorkerId(Long.valueOf(workerId)), HttpStatus.OK);

    }

    @PostMapping("/api/bookingTime")
    public ResponseEntity <HashMap <LocalDate, List <WorkTimeDto>>> findTimeToBooking(@RequestBody BookingDto bookingDto) {
        return new ResponseEntity <>(bookingService.findTimeToBooking(bookingDto), HttpStatus.OK);
    }

    @PostMapping("/api/addBooking")
    public ResponseEntity <HttpStatus> addSession(@RequestBody NewBookingDto newSessionDto) {
        if (bookingService.addSession(newSessionDto)) {
            return new ResponseEntity <>(HttpStatus.OK);
        } else {
            return new ResponseEntity <>(HttpStatus.BAD_REQUEST);
        }
    }
}