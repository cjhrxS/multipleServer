package ua.ita.smartcarservice.service.impl.sensors;

import ua.ita.smartcarservice.entity.sensors.ISensorEntity;
import ua.ita.smartcarservice.entity.sensors.TirePressureEntity;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LabelsProvider {

    List<String> getMonths(List<Object[]> records){
        List<String> names = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        List<String> lables = new ArrayList<>();
        for(Object[] el : records){
            lables.add(names.get((int)el[0] - 1));
        }
        return lables;
    }

    List<String> getDays(List<Object[]> records){
        List<String> lables = new ArrayList<>();
        for(Object[] el : records){
            lables.add(String.valueOf(el[0]));
        }
        return lables;
    }

    List<String> getHours(List<ISensorEntity> records){
        List<String> labels = new ArrayList<>();
        records.forEach((entity) -> labels.add(dateTimeToTime(entity.getDate())));
        return labels;
    }

    List<String> getTireHours(List<TirePressureEntity> records){
        List<String> labels = new ArrayList<>();
        records.forEach((entity) -> labels.add(dateTimeToTime(entity.getDate())));
        return labels;
    }

    private String dateTimeToTime(LocalDateTime dateTime){
        int hour = dateTime.getHour();
        int minutes = dateTime.getMinute();
        LocalTime time = LocalTime.of(hour, minutes);
        return parseTimeToString(time);
    }

    private String parseTimeToString (LocalTime time){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }

}
