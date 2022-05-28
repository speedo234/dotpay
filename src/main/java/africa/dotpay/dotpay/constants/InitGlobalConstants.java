package africa.dotpay.dotpay.constants;


import africa.dotpay.dotpay.utility.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@Component
public class InitGlobalConstants {


    //@Value("${duration}") String duration, @Value("${limit}") int limit, @Value("${accessFile}") String accessFile, @Value("${start}") String start

    @Value("${duration}")
    private String duration;

    @Value("${limit}")
    private int limit;

    @Value("${accessFile}")
    private String accessFile;

    @Value("${start}")
    private String start;

    @Autowired
    GlobalConstants globalConstants;


    @Autowired
    @Qualifier("dateTimeFormatter")
    private DateTimeFormatter dateTimeFormatter;


    public void initGlobalConstants() {
//        globalConstants = new GlobalConstants();
        initDuration();
//        initLimit();
        initAccessFile();
        initStart();
        initEnd();
    }

    private void initDuration(){
        if(duration.equalsIgnoreCase(Duration.HOURLY.getTimeDuration())) {
//            GlobalConstants.duration = Duration.HOURLY;
            globalConstants.setDuration(Duration.HOURLY);
            initLimit(200);
        }
        else if(duration.equalsIgnoreCase(Duration.DAILY.getTimeDuration())) {
//            GlobalConstants.duration = Duration.DAILY;
            globalConstants.setDuration(Duration.DAILY);
            initLimit(500);
        }
        else {
            throw new UnsupportedOperationException("wrong input data for duration property | accepted values are " +
                    Duration.HOURLY.getTimeDuration() + " or " + Duration.DAILY.getTimeDuration());
        }
    }


    private void initLimit(int limit){
        if(this.limit > 0)
//            GlobalConstants.limit = this.limit;
            globalConstants.setLimit(this.limit);
        else
            globalConstants.setLimit(limit);
//            GlobalConstants.limit = limit;
    }

    private void initAccessFile(){
        globalConstants.setAccessFile(accessFile);
//        GlobalConstants.accessFile = accessFile;
    }


    private void initStart(){
        try{
            start = start.replaceFirst("\\.", " ");
            LocalDateTime startDateTime = LocalDateTime.parse(start, dateTimeFormatter);
            globalConstants.setStart(startDateTime);
//            GlobalConstants.start = startDateTime;
        }catch (DateTimeParseException dtpe){
            dtpe.printStackTrace();
            throw new UnsupportedOperationException("start property string could not be parsed into a LocalDateTime");
        }
    }

    private void initEnd(){
        try{
            globalConstants.setEnd( DateUtil.getEndDateTime(globalConstants.getStart(), globalConstants.getDuration().getTimeDuration() ));
//            GlobalConstants.end = DateUtil.getEndDateTime(GlobalConstants.start, GlobalConstants.duration.getTimeDuration());
        }catch (DateTimeParseException dtpe){
            dtpe.printStackTrace();
            throw new UnsupportedOperationException("start property string could not be parsed into a LocalDateTime");
        }
    }


}











