package ua.ita.smartcarservice.entity.booking;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TimePoint implements Comparable<TimePoint> {

    public TimePoint(LocalDateTime time, boolean position){
        this.time = time;
        this.position = position;
    }

    private LocalDateTime time;

    private boolean position;

    public int compareTo(TimePoint timePoint){
        if(this.time.compareTo(timePoint.time) == 0){
            return this.position ? 1 : -1;
        }

        return this.time.compareTo(timePoint.time);
    }
}
