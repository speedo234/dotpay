package africa.dotpay.dotpay.listener;


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
import org.springframework.stereotype.Component;

import java.util.List;


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
        logger.info("================================================================");
        logger.info("==========================JOB INITIALIZING=======================");
        logger.info("=================================================================");

        logger.info("DB CLEAN UP!!! ");
        userAccessLogService.clearUserAccessLog();
        blockedIpTableService.clearBlockedIp();
        logger.info("CLEAN UP SUCCESSFUL!!! ");
        logger.info("JOB FIRING UP!!! ");
        }

    @Override
    public void afterJob(final JobExecution jobExecution){
        initGlobalConstants.initGlobalConstants();
        final Long dataCountInDb = userAccessLogService.getCount();
        if(jobExecution.getStatus() == BatchStatus.COMPLETED ){
            logger.info("FINISHED LOADING {} RECORDS TO UserAccessLog Table", dataCountInDb);
            final List<BlockedIpTable> blockedIpTableList = userAccessLogService.getBlockedIps();
            blockedIpTableService.saveBlockedIpList( blockedIpTableList );

            logger.info("================================================================");
            logger.info("==========================JOB FINISHED==========================");
            logger.info("================================================================");
        }
    }


}
