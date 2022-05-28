package africa.dotpay.dotpay.processor;



import africa.dotpay.dotpay.model.UserAccessLog;
import africa.dotpay.dotpay.request.RequestInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RequestItemProcessor implements ItemProcessor<RequestInput, UserAccessLog> {


    static final Logger logger = LoggerFactory.getLogger(RequestItemProcessor.class);

    static int counter = 0;

    @Autowired
    @Qualifier("dateTimeFormatter2")
    DateTimeFormatter dateTimeFormatter2;


    @Override
    public UserAccessLog process(final RequestInput requestInput) throws Exception{//transform the request before mapping to UserAccessLog
        //transform dateTimeString to LocalDateTime here...
        String dateTimeString = requestInput.getDateTime();
        String ip = requestInput.getIp();
        String req = requestInput.getRequest();
        int status = requestInput.getStatus();
        String userAgent = requestInput.getUserAgent();
        LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, dateTimeFormatter2);

        final UserAccessLog transformedRequest = new UserAccessLog(dateTime, ip, req, status, userAgent);
        counter++;
//        logger.info("PLEASE HOLD ON! DATA UPLOAD STILL IN PROGRESS... YOU WILL BE NOTIFIED WHEN DATA UPLOAD IS DONE... ");
        logger.info("loaded {} records and counting...",counter);

        return transformedRequest;
    }




}
