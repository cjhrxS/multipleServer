package ua.ita.smartcarservice.repository.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.ita.smartcarservice.entity.booking.WorkTime;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<WorkTime, Long> {

    @Query("select w from WorkTime as w where w.worker.id = :workerId " +
            "and w.startSession >= :starttime and w.startSession <= :endtime")
    List<WorkTime> findTimeWhenWork(@Param("workerId") Long workerId,
                                   @Param("starttime") LocalDateTime time,
                                   @Param("endtime") LocalDateTime endt);

    @Query("select count(w) from WorkTime as w where :starttime < w.endSession and :endtime > w.startSession")
    int selectNumberOfSessionWithDate(@Param("starttime") LocalDateTime starttime, @Param("endtime") LocalDateTime endtime);

    List<WorkTime> findAllByWorkerId(Long id);
}
