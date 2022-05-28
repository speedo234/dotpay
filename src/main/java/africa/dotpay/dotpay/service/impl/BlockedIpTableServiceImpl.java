package africa.dotpay.dotpay.service.impl;


import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.repository.BlockedIpTableRepository;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    @Override
    public void clearBlockedIp() {
        logger.info("clearing BlockedIpTable table...");
        blockedIpTableRepository.deleteAll();
    }

    @Override
    public Long getCount() {
        return blockedIpTableRepository.count();
    }

}
