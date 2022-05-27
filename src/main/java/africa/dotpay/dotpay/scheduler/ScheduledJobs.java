package africa.dotpay.dotpay.scheduler;


import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
@EnableScheduling
public class ScheduledJobs {
//    private final JobLauncher jobLauncher;
//
//    @Autowired
//    @Qualifier("importRequestJob")
//    private final Job importRequestJob;
//
//    @Scheduled(cron="*/10 * * * * *")
//    public void startBatchJob() throws Exception{
//        System.out.println("======="+new Date().toString());
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("time", new Date().toString()).toJobParameters();
////                .addString("time", "2022-05-26 19:15:41.274000").toJobParameters();
//
//        jobLauncher.run(importRequestJob, jobParameters);
//    }



}
