package africa.dotpay.dotpay.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;


@Configuration
public class DateTimeFormatterConfig {


    @Value(("${date.time.format}"))
    private String dateTimeFormat;


    @Value(("${date.time.format2}"))
    private String dateTimeFormat2;


    @Bean("dateTimeFormatter")
    public DateTimeFormatter getDateTimeFormatter(){
        return DateTimeFormatter.ofPattern(dateTimeFormat);
    }


    @Bean("dateTimeFormatter2")
    public DateTimeFormatter getDateTimeFormatter2(){
        return DateTimeFormatter.ofPattern(dateTimeFormat2);
    }


}













