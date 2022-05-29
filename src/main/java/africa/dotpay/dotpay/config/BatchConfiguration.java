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
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;


@EnableBatchProcessing
@Configuration
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    DataSource dataSource;

    @Value("${accessFile}")
    private String accessFile;

    @Value("${chunk.size}")
    private int chunkSize;

    @Bean
    public BeanPropertyItemSqlParameterSourceProvider getBeanPropertyItemSqlParameterSourceProvider(){
        return new BeanPropertyItemSqlParameterSourceProvider<UserAccessLog>();
    }



    @Bean
    public FlatFileItemReader<RequestInput> reader(){
        String[] columns = { "date_time", "ip", "request", "status", "user_agent" };
        return new FlatFileItemReaderBuilder<RequestInput>()
                .name("itemReader")
//                .resource(new ClassPathResource(accessFile))
                .resource(new FileSystemResource(accessFile))
                .targetType(RequestInput.class)
                .delimited()
                .delimiter("|")
                .names(columns)
                .linesToSkip(0)
                .build();
    }


    @Bean
    public RequestItemProcessor processor(){//bean for creating processor which transforms the request
        return new RequestItemProcessor();
    }


    @Bean
    public JdbcBatchItemWriter<UserAccessLog> writer(){//write data to database
        JdbcBatchItemWriter<UserAccessLog> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(getBeanPropertyItemSqlParameterSourceProvider());
        writer.setSql("INSERT INTO USER_ACCESS_LOG (date_time, ip, request, status, user_agent) values(:dateTime, :ip, :request, :status, :userAgent)");
        writer.setDataSource(dataSource);
        return writer;
    }


    @Bean("importRequestJob")
    public Job importRequestJob(JobCompletionNotificationListener listener){
        return jobBuilderFactory.get("importRequestJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }


    @Bean
    public Step step1(){//define bean for steps in the flow of execution
        return stepBuilderFactory.get("step1")
                .<RequestInput, UserAccessLog> chunk(chunkSize)
                .reader(reader())//read from flat file
                .processor(processor())//transform read data
                .writer(writer())//write data to db
                .build();//build step
    }



}













