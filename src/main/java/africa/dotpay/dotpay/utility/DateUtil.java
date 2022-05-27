package africa.dotpay.dotpay.utility;


import africa.dotpay.dotpay.constants.Duration;
import africa.dotpay.dotpay.service.impl.UserAccessLogServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class DateUtil {

    static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static LocalDateTime getEndDateTime(LocalDateTime startDateTime, final String duration){

        LocalDateTime endDateTime = null;

        if(duration.equalsIgnoreCase(Duration.HOURLY.getDuration())){
            logger.info("::1::{} triggered", Duration.HOURLY.getDuration());
            endDateTime = startDateTime.plusHours( Duration.HOURLY.getAddedHours() );
        }
        else if(duration.equalsIgnoreCase(Duration.DAILY.getDuration())){
            logger.info("::2:{} triggered", Duration.DAILY.getDuration());
            endDateTime = startDateTime.plusHours( Duration.DAILY.getAddedHours() );
        }else{
            logger.info(":::duration entered was not {} or {}", Duration.HOURLY.getDuration(), Duration.DAILY.getDuration());
        }
        return endDateTime;
    }


}
