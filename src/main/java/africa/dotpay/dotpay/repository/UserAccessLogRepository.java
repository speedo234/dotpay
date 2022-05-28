package africa.dotpay.dotpay.repository;



import africa.dotpay.dotpay.model.IBlockedIpDto;
import africa.dotpay.dotpay.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {

    @Query(value="SELECT IP AS IP, COUNT(IP) AS REQUESTNUMBER "
            + "FROM USER_ACCESS_LOG u where date_time between :start and :end GROUP BY IP HAVING COUNT(IP) > :count ", nativeQuery = true)
    List<IBlockedIpDto> findUserAccessLogByDateTimeAndCount(String start, String end, int count);


}
