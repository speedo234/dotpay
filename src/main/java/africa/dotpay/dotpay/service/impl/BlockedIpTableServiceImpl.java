package africa.dotpay.dotpay.service.impl;


import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.repository.BlockedIpTableRepository;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


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

    public void processIpExceededLimitsToDb( Map<String, List<BlockedIpTable>>  blockedIpTableMap ){
        logger.info("checkBlockedIpResponse.size()======================>>>> {} ", blockedIpTableMap.size());
        for (Map.Entry<String, List<BlockedIpTable>> entry : blockedIpTableMap.entrySet()) {
            logger.info("{} <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<: {} ",entry.getKey() , entry.getValue());
            List<BlockedIpTable> blockedIpTableList = entry.getValue();
            saveBlockedIpList( blockedIpTableList );
            logger.info("just saved blocked ip {} to db ", entry.getKey());
        }
        logger.info("done processing for blocked ip to db ==============================================================================");
    }





}
