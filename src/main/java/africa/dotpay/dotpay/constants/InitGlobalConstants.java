package africa.dotpay.dotpay.constants;


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
    @Qualifier("dateTimeFormatter")
    private DateTimeFormatter dateTimeFormatter;


    public void initGlobalConstants() {
        initDuration();
//        initLimit();
        initAccessFile();
        initStart();
    }

    private void initDuration(){
        if(duration.equalsIgnoreCase(Duration.HOURLY.getDuration())) {
            GlobalConstants.duration = Duration.HOURLY;
            initLimit(200);
        }
        else if(duration.equalsIgnoreCase(Duration.DAILY.getDuration())) {
            GlobalConstants.duration = Duration.DAILY;
            initLimit(500);
        }
        else {
            throw new UnsupportedOperationException("wrong input data for duration property | accepted values are " + Duration.HOURLY.getDuration() + " or " + Duration.DAILY.getDuration());
        }
    }


    private void initLimit(int limit){
        if(this.limit > 0)
            GlobalConstants.limit = this.limit;
        else
            GlobalConstants.limit = limit;
        System.out.println("xoxoxoxoxox==>> "+GlobalConstants.limit);
    }

    private void initAccessFile(){
        GlobalConstants.accessFile = accessFile;
    }


    private void initStart(){
        try{
            start = start.replaceFirst("\\.", " ");
            LocalDateTime startDateTime = LocalDateTime.parse(start, dateTimeFormatter);
            GlobalConstants.start = startDateTime;
        }catch (DateTimeParseException dtpe){
            dtpe.printStackTrace();
            throw new UnsupportedOperationException("start property string could not be parsed into a LocalDateTime");
        }

    }


}











