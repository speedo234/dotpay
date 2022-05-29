package africa.dotpay.dotpay.constants;


import africa.dotpay.dotpay.processor.RequestItemProcessor;
import africa.dotpay.dotpay.utility.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class InitGlobalConstants {

    @Value("${duration}")
    private String duration;

    @Value("${limit}")
    private int limit;

    @Value("${accessFile}")
    private String accessFile;

    @Value("${start}")
    private String start;


    @Autowired
    @Qualifier("dateTimeFormatter")
    private DateTimeFormatter dateTimeFormatter;

    static final Logger logger = LoggerFactory.getLogger(RequestItemProcessor.class);

    public void initGlobalConstants() {
        initDuration();
//        initLimit();
        initAccessFile();
        initStart();
        initEnd();
        logger.info(toString());
    }

    private void initDuration(){
        if(duration.equalsIgnoreCase(Duration.HOURLY.getTimeDuration())) {
            GlobalConstants.setDuration(Duration.HOURLY);
            initLimit(200);
        }
        else if(duration.equalsIgnoreCase(Duration.DAILY.getTimeDuration())) {
            GlobalConstants.setDuration(Duration.DAILY);
            initLimit(500);
        }
        else {
            throw new UnsupportedOperationException("wrong input data for duration property | accepted values are " +
                    Duration.HOURLY.getTimeDuration() + " or " + Duration.DAILY.getTimeDuration());
        }
    }


    private void initLimit(int limit){
        if(this.limit > 0)
            GlobalConstants.setLimit(this.limit);
        else
            GlobalConstants.setLimit(limit);
    }

    private void initAccessFile(){
        GlobalConstants.setAccessFile(accessFile);
    }


    private void initStart(){
        try{
            start = start.replaceFirst("\\.", " ");
            LocalDateTime startDateTime = LocalDateTime.parse(start, dateTimeFormatter);
            GlobalConstants.setStart(startDateTime);
        }catch (DateTimeParseException dtpe){
            dtpe.printStackTrace();
            throw new UnsupportedOperationException("start property string could not be parsed into a LocalDateTime");
        }
    }

    private void initEnd(){
        try{
            GlobalConstants.setEnd( DateUtil.getEndDateTime(GlobalConstants.getStart(), GlobalConstants.getDuration().getTimeDuration() ));
        }catch (DateTimeParseException dtpe){
            dtpe.printStackTrace();
            throw new UnsupportedOperationException("start property string could not be parsed into a LocalDateTime");
        }
    }

    @Override
    public String toString() {
        return "InitGlobalConstants{" +
                "duration='" + GlobalConstants.getDuration().getTimeDuration() + '\'' +
                ", limit=" + GlobalConstants.getLimit() +
                ", accessFile='" + GlobalConstants.getAccessFile() + '\'' +
                ", start='" + GlobalConstants.getStart() + '\'' +
                ", end='" + GlobalConstants.getEnd().toString() + '\'' +
                '}';
    }
}

