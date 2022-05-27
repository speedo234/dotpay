package africa.dotpay.dotpay.repository;


import africa.dotpay.dotpay.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Repository
public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {


//    List<UserAccessLog> findUserAccessLogByDateTimeBetween(
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDateTime,
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDateTime);


    @Query(value="SELECT * "
            + "FROM USER_ACCESS_LOG u where date_time between :start and :end ", nativeQuery = true)
    List<UserAccessLog> findUserAccessLogByDateTimeBetween(String start, String end);


}
