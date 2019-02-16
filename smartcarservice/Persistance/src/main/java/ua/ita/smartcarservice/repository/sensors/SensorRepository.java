package ua.ita.smartcarservice.repository.sensors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import ua.ita.smartcarservice.entity.sensors.BaseSensorEntity;

import java.time.LocalDateTime;
import java.util.List;

@NoRepositoryBean
public interface SensorRepository<T extends BaseSensorEntity> extends JpaRepository<T, Long> {

    /* READ */

    @Query("SELECT t FROM #{#entityName} t WHERE t.car.id = :carId " +
            "AND DAY(t.date) = DAY(:date) AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "ORDER BY t.date")
    List<T> getAllByDay(@Param("date") LocalDateTime date,
                        @Param("carId") long carId);

    @Query("SELECT DAY(t.date), AVG(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY t.date")
    List<T> getAvgByMonth(@Param("date") LocalDateTime date,
                          @Param("carId") long carId);

    @Query("SELECT DAY(t.date), MAX(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY t.date")
    List<T> getMaxByMonth(@Param("date") LocalDateTime date,
                          @Param("carId") long carId);

    @Query("SELECT DAY(t.date), MIN(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND MONTH(t.date) = MONTH(:date) AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY DAY(t.date)" +
            "ORDER BY t.date")
    List<T> getMinByMonth(@Param("date") LocalDateTime date,
                          @Param("carId") long carId);

    @Query("SELECT MONTH(t.date), AVG(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY t.date")
    List<T> getAvgByYear(@Param("date") LocalDateTime date,
                         @Param("carId") long carId);

    @Query("SELECT MONTH(t.date), MAX(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY t.date")
    List<T> getMaxByYear(@Param("date") LocalDateTime date,
                         @Param("carId") long carId);

    @Query("SELECT MONTH(t.date), MIN(t.value) " +
            "FROM #{#entityName} t " +
            "WHERE t.car.id = :carId AND YEAR(t.date) = YEAR(:date)" +
            "GROUP BY MONTH(t.date)" +
            "ORDER BY t.date")
    List<T> getMinByYear(@Param("date") LocalDateTime date,
                         @Param("carId") long carId);

}

