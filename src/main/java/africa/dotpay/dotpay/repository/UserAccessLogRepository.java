package africa.dotpay.dotpay.repository;



import africa.dotpay.dotpay.model.IBlockedIpDto;
import africa.dotpay.dotpay.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {


    @Query(value="SELECT * "
            + "FROM USER_ACCESS_LOG u where date_time between :start and :end ", nativeQuery = true)
    List<UserAccessLog> findUserAccessLogByDateTimeBetween(String start, String end);
    //SELECT IP, COUNT(IP) AS REQUEST_NUMBER FROM USER_ACCESS_LOG WHERE date_time between :start and :end GROUP BY IP HAVING COUNT(IP) > 400

//    @Query(value="SELECT *, COUNT(IP) AS REQUEST_NUMBER FROM USER_ACCESS_LOG WHERE DATE_TIME between :start and :end GROUP BY IP HAVING COUNT(IP) > :count ", nativeQuery = true)
//    List<UserAccessLog> findUserAccessLogByDateTimeBetween(String start, String end, int count);


    @Query(value="SELECT IP AS IP, COUNT(IP) AS REQUESTNUMBER "
            + "FROM USER_ACCESS_LOG u where date_time between :start and :end GROUP BY IP HAVING COUNT(IP) > :count ", nativeQuery = true)
    List<IBlockedIpDto> projectiongetMyDataForMe(String start, String end, int count);


}
