package africa.dotpay.dotpay.service.impl;


import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.model.UserAccessLog;
import africa.dotpay.dotpay.repository.BlockedIpTableRepository;
import africa.dotpay.dotpay.repository.UserAccessLogRepository;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import africa.dotpay.dotpay.service.UserAccessLogService;
import africa.dotpay.dotpay.utility.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class BlockedIpTableServiceImpl implements BlockedIpTableService {


    static final Logger logger = LoggerFactory.getLogger(BlockedIpTableServiceImpl.class);

    @Autowired
    BlockedIpTableRepository blockedIpTableRepository;


    @Override
    public void saveBlockedIp(BlockedIpTable blockedIpTable) {
        blockedIpTableRepository.save(blockedIpTable);
    }

    @Override
    public void saveBlockedIpList(List<BlockedIpTable> blockedIpTableList) {
        blockedIpTableRepository.saveAll(blockedIpTableList);
    }


    public void processHashMapToDb( Map<String, List<BlockedIpTable>>  checkBlockedIpResponse ){
        logger.info("checkBlockedIpResponse.size()======================>>>> {} ", checkBlockedIpResponse.size());
        for (Map.Entry<String, List<BlockedIpTable>> entry : checkBlockedIpResponse.entrySet()) {
            logger.info(entry.getKey() + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<:" + entry.getValue());
            List<BlockedIpTable> blockedIpTableList = entry.getValue();
            saveBlockedIpList( blockedIpTableList );
            logger.info("just saved blocked ip {} to db ", entry.getKey());
        }
        logger.info("done processing for blocked ip to db ==============================================================================");
    }





}
