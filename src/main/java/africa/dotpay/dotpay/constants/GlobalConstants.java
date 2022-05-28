package africa.dotpay.dotpay.constants;



import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;


//@Getter
//@Setter
@Component
public class GlobalConstants {

    private static Duration duration;
    private static int limit;
    private static String accessFile;
    private static LocalDateTime start = LocalDateTime.now();
    private static LocalDateTime end = LocalDateTime.now();

    public GlobalConstants() {
    }

    public /*static*/ Duration getDuration() {
        return duration;
    }

    public /*static*/ void setDuration(Duration duration) {
        GlobalConstants.duration = duration;
    }

    public /*static*/ int getLimit() {
        return limit;
    }

    public /*static*/ void setLimit(int limit) {
        GlobalConstants.limit = limit;
    }

    public /*static*/ String getAccessFile() {
        return accessFile;
    }

    public /*static*/ void setAccessFile(String accessFile) {
        GlobalConstants.accessFile = accessFile;
    }

    public /*static*/ LocalDateTime getStart() {
        return start;
    }

    public /*static*/ void setStart(LocalDateTime start) {
        GlobalConstants.start = start;
    }

    public /*static*/ LocalDateTime getEnd() {
        return end;
    }

    public /*static*/ void setEnd(LocalDateTime end) {
        GlobalConstants.end = end;
    }
}