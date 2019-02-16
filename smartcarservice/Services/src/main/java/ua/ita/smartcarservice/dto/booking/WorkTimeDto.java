package ua.ita.smartcarservice.dto.booking;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WorkTimeDto {
    private LocalDateTime startSession;

    private LocalDateTime endSession;
}
