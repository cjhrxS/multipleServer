package ua.ita.smartcarservice.entity.booking;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import ua.ita.smartcarservice.entity.Car;
import ua.ita.smartcarservice.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "schedule")
public class WorkTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sessionId;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startSession;

    @Column(nullable = false, length = 20, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endSession;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "carId", nullable = false)
    private Car car;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private UserEntity worker;

}