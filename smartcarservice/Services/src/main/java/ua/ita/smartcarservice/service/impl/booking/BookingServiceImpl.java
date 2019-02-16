package ua.ita.smartcarservice.service.impl.booking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ita.smartcarservice.dto.booking.BookingDto;
import ua.ita.smartcarservice.dto.booking.WorkTimeDto;
import ua.ita.smartcarservice.dto.booking.NewBookingDto;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.booking.TimePoint;
import ua.ita.smartcarservice.entity.booking.WorkTime;
import ua.ita.smartcarservice.repository.CarRepository;
import ua.ita.smartcarservice.repository.UserRepository;
import ua.ita.smartcarservice.repository.booking.BookingRepository;
import ua.ita.smartcarservice.service.BookingService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List <WorkTimeDto> findAllByWorkerId(Long workerId) {
        List <WorkTimeDto> workDtos = new ArrayList <>();
        for (WorkTime workTime : bookingRepository.findAllByWorkerId(workerId)) {
            workDtos.add(getSessionDto(workTime));
        }
        return workDtos;
    }


    @Override
    public boolean addSession(NewBookingDto newBookingDto) {
        Car car = carRepository.getOne(newBookingDto.getCarId());
        List <WorkTime> sessionsToAdd = new ArrayList <>();
        if (bookingRepository.selectNumberOfSessionWithDate(parseDate(newBookingDto.getStarttime()), parseDate(newBookingDto.getEndtime())) != 0
                && LocalDateTime.now().compareTo(parseDate(newBookingDto.getStarttime())) < 0) {
            return false;
        }
        for (String id : newBookingDto.getWorkerId()) {
            WorkTime session = getEntity(newBookingDto);
            session.setCar(car);
            session.setWorker(userRepository.getOne(Long.valueOf(id)));
            sessionsToAdd.add(session);
        }

        bookingRepository.saveAll(sessionsToAdd);
        return true;
    }

    @Override
    public HashMap <LocalDate, List <WorkTimeDto>> findTimeToBooking(BookingDto bookingDto) {
        List <Long> workerId = new ArrayList <>();
        Integer timeToNeedInMinute = Integer.parseInt(bookingDto.getNeedTime());
        Integer numberOfDay = Integer.parseInt(bookingDto.getNumberOfDay());
        LocalDate time = LocalDate.parse(bookingDto.getTime());

        for (String s : bookingDto.getWorkerId()) {
            workerId.add(Long.valueOf(s));
        }

        List <WorkTimeDto> timeToWork = findTimeWhenWork(workerId, time, numberOfDay);

        return getFreeTimeForEveryDay(timeToWork, time, numberOfDay, timeToNeedInMinute);

    }


    private List <WorkTimeDto> findTimeWhenWork(List <Long> workerId, LocalDate time, int numberOfDay) {
        List <WorkTimeDto> findTimeWhenWork = new ArrayList <>();
        LocalDate end = time.plusDays(numberOfDay);
        LocalDateTime starttime = LocalDateTime.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth(), 10, 0);
        LocalDateTime endtime = LocalDateTime.of(end.getYear(), end.getMonthValue(), end.getDayOfMonth(), 18, 0);
        for (Long id : workerId) {
            for (WorkTime session : bookingRepository.findTimeWhenWork(id, starttime, endtime)) {
                findTimeWhenWork.add(getSessionDto(session));
            }
        }
        return findTimeWhenWork;
    }

    private HashMap <LocalDate, List <WorkTimeDto>> getFreeTimeForEveryDay(List <WorkTimeDto> timeToWork, LocalDate time, int numberOfDay, int timeToNeed) {
        HashMap <LocalDate, List <WorkTimeDto>> freeTime = new HashMap <>();
        HashMap <LocalDate, List <WorkTimeDto>> bookingShedule = new HashMap <>();
        for (int i = 0; i <= numberOfDay; i++) {
            freeTime.put(time.plusDays(i), new ArrayList <>());
        }
        if (timeToWork != null) {
            for (WorkTimeDto sessionDto : timeToWork) {
                List <WorkTimeDto> newList = freeTime.get(getKey(sessionDto.getStartSession()));
                newList.add(sessionDto);
                freeTime.put(getKey(sessionDto.getStartSession()), newList);
            }
        }

        for (LocalDate localDate : freeTime.keySet()) {
            bookingShedule.put(localDate, findFreeTime(findAllTimePoints(freeTime.get(localDate), localDate), timeToNeed));
        }

    
        return bookingShedule;


    }

    private LocalDate getKey(LocalDateTime time) {
        return LocalDate.of(time.getYear(), time.getMonthValue(), time.getDayOfMonth());
    }

    private List <TimePoint> findAllTimePoints(List <WorkTimeDto> timeToWork, LocalDate time) {
        List <TimePoint> timePoints = new ArrayList <>();

        WorkTime workDay = new WorkTime();
        workDay.setStartSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 10, 0));
        workDay.setEndSession(LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), 18, 0));

        timePoints.add(new TimePoint(workDay.getStartSession(), true));
        timePoints.add(new TimePoint(workDay.getEndSession(), false));

        for (WorkTimeDto sessionDto : timeToWork) {
            timePoints.add(new TimePoint(sessionDto.getStartSession(), true));
            timePoints.add(new TimePoint(sessionDto.getEndSession(), false));
        }

        Collections.sort(timePoints);
        return timePoints;
    }

    private List <WorkTimeDto> findFreeTime(List <TimePoint> timePoints, int timeToNeedInMinute) {
        List <WorkTimeDto> freeTime = new ArrayList <>();

        TimePoint start = timePoints.get(0);
        int workNow = 0;

        for (int i = 1; i < timePoints.size() - 1; i++) {
            if (timePoints.get(i).isPosition()) {
                if (workNow == 0 && getDeltaTimeInMinut(start.getTime(), timePoints.get(i).getTime()) >= timeToNeedInMinute) {
                    WorkTimeDto sessionDto = new WorkTimeDto();
                    sessionDto.setStartSession(start.getTime());
                    sessionDto.setEndSession(timePoints.get(i).getTime());
                    freeTime.add(sessionDto);
                }
                workNow++;

            } else {
                start = timePoints.get(i);
                workNow--;
            }
        }
        if (freeTime.isEmpty() && timePoints.size() == 2
                && getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime()) >= timeToNeedInMinute) {
            WorkTimeDto sessionDto = new WorkTimeDto();
            sessionDto.setStartSession(start.getTime());
            sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
            freeTime.add(sessionDto);
        } else {
            WorkTimeDto sessionDto = new WorkTimeDto();
            if (getDeltaTimeInMinut(timePoints.get(timePoints.size() - 2).getTime(), timePoints.get(timePoints.size() - 1).getTime()) >= timeToNeedInMinute) {
                sessionDto.setStartSession(timePoints.get(timePoints.size() - 2).getTime());
                sessionDto.setEndSession(timePoints.get(timePoints.size() - 1).getTime());
                freeTime.add(sessionDto);
            }
        }

        return freeTime;

    }


    private WorkTimeDto getSessionDto(WorkTime session) {
        WorkTimeDto sessionDto = new WorkTimeDto();
        sessionDto.setStartSession(session.getStartSession());
        sessionDto.setEndSession(session.getEndSession());
        return sessionDto;
    }

    private int getDeltaTimeInMinut(LocalDateTime first, LocalDateTime second) {
        return (second.getHour() - first.getHour()) * 60 + (second.getMinute() - first.getMinute());
    }

    private WorkTime getEntity(WorkTimeDto sessionDto) {
        WorkTime session = new WorkTime();
        session.setStartSession(sessionDto.getStartSession());
        session.setEndSession(sessionDto.getEndSession());
        return session;
    }

    private WorkTime getEntity(NewBookingDto newBookingDto) {

        WorkTime session = new WorkTime();
        session.setStartSession(parseDate(newBookingDto.getStarttime()));
        session.setEndSession(parseDate(newBookingDto.getEndtime()));
        return session;
    }

    private LocalDateTime parseDate(String s) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(s, formatter);
    }

}

