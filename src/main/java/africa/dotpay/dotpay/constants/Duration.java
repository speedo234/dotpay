package africa.dotpay.dotpay.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Duration {
    HOURLY("hourly", 1),
    DAILY("daily", 24);

    String timeDuration;
    int addedHours;
}