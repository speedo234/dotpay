package africa.dotpay.dotpay.service.impl;


import africa.dotpay.dotpay.constants.GlobalConstants;
import africa.dotpay.dotpay.constants.InitGlobalConstants;
import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.model.IBlockedIpDto;
import africa.dotpay.dotpay.repository.UserAccessLogRepository;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import africa.dotpay.dotpay.service.UserAccessLogService;
import africa.dotpay.dotpay.utility.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserAccessLogServiceImpl implements UserAccessLogService {


    static final Logger logger = LoggerFactory.getLogger(UserAccessLogServiceImpl.class);

    @Autowired
    UserAccessLogRepository userAccessLogRepository;

    @Autowired
    BlockedIpTableService blockedIpTableService;

    @Autowired
    GlobalConstants globalConstants;

    @Override
    public Long getCount() {
        return userAccessLogRepository.count();
    }


    public List<BlockedIpTable> getBlockedIps(){

        List<IBlockedIpDto> iBlockedIpProjectionList = userAccessLogRepository
                .findUserAccessLogByDateTimeAndCount(
                        GlobalConstants.getStart().toString(),
                        GlobalConstants.getEnd().toString(),
                        GlobalConstants.getLimit() );
        return iBlockedIpProjectionList.stream().map( b ->
                {
                    logger.info("IPs with exceeded limit of {} ==> {}", GlobalConstants.getLimit(), b.getIp());
                         String comment = Util.generateComment(GlobalConstants.getLimit(), b.getRequestnumber());
                         return BlockedIpTable.of(b, comment );
                }
        ).collect(Collectors.toList());
    }

    @Override
    public void clearUserAccessLog() {
        logger.info("clearing UserAccessLog table...");
        userAccessLogRepository.deleteAll();
    }



}
