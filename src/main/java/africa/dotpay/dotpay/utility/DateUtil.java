package africa.dotpay.dotpay.utility;


import africa.dotpay.dotpay.constants.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class DateUtil {

    static final Logger logger = LoggerFactory.getLogger(DateUtil.class);


    private DateUtil() {
    }

    public static LocalDateTime getEndDateTime(final LocalDateTime startDateTime, final String duration){
        LocalDateTime endDateTime = null;
        if(duration.equalsIgnoreCase(Duration.HOURLY.getTimeDuration()))
            endDateTime = startDateTime.plusHours( Duration.HOURLY.getAddedHours() );
        else if(duration.equalsIgnoreCase(Duration.DAILY.getTimeDuration()))
            endDateTime = startDateTime.plusHours( Duration.DAILY.getAddedHours() );
        else
            logger.info(":::duration entered was not {} or {}", Duration.HOURLY.getTimeDuration(), Duration.DAILY.getTimeDuration());

        return endDateTime;
    }


}
