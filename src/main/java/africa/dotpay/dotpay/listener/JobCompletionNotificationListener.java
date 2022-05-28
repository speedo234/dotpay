package africa.dotpay.dotpay.listener;



import africa.dotpay.dotpay.constants.Duration;
import africa.dotpay.dotpay.constants.GlobalConstants;
import africa.dotpay.dotpay.constants.InitGlobalConstants;
import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import africa.dotpay.dotpay.service.UserAccessLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {


    static final Logger logger = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    @Autowired
    private UserAccessLogService userAccessLogService;

    @Autowired
    private BlockedIpTableService blockedIpTableService;

    @Autowired
    InitGlobalConstants initGlobalConstants;

    @Override
    public void beforeJob(final JobExecution jobExecution){
        final Long dataCountInDb = userAccessLogService.getCount();
            logger.info("ABOUT TO START DATA UPLOAD!!! {} ",dataCountInDb);
        }

    @Override
    public void afterJob(final JobExecution jobExecution){
        initGlobalConstants.initGlobalConstants();
        final Long dataCountInDb = userAccessLogService.getCount();
        if(jobExecution.getStatus() == BatchStatus.COMPLETED ){
            logger.info("DATA UPLOAD IS DONE!!! PROCEEDING WITH DATA ANALYSIS FOR {} RECORDS", dataCountInDb);
            logger.info("duration is => {} ", GlobalConstants.duration.getTimeDuration());
            final Map<String, List<BlockedIpTable>> ipExceededLimits = userAccessLogService.getIpExceededLimits();
            blockedIpTableService.processIpExceededLimitsToDb( ipExceededLimits );
        }
    }


}
