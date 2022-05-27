package africa.dotpay.dotpay.config;


import africa.dotpay.dotpay.listener.JobCompletionNotificationListener;
import africa.dotpay.dotpay.model.UserAccessLog;
import africa.dotpay.dotpay.processor.RequestItemProcessor;
import africa.dotpay.dotpay.request.RequestInput;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
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













