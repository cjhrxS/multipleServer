package ua.ita.smartcarservice.entity.sensors;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.ita.smartcarservice.entity.Car;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class BaseSensorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    protected Car car;

    @Column(nullable = false, columnDefinition = "DATETIME")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime date = LocalDateTime.now();

}
