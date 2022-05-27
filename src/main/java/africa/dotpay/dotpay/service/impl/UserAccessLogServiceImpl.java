package africa.dotpay.dotpay.service.impl;


import africa.dotpay.dotpay.constants.GlobalConstants;
import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.model.UserAccessLog;
import africa.dotpay.dotpay.repository.UserAccessLogRepository;
import africa.dotpay.dotpay.service.BlockedIpTableService;
import africa.dotpay.dotpay.service.UserAccessLogService;
import africa.dotpay.dotpay.utility.DateUtil;
import africa.dotpay.dotpay.utility.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserAccessLogServiceImpl implements UserAccessLogService {


    static final Logger logger = LoggerFactory.getLogger(UserAccessLogServiceImpl.class);

    @Autowired
    UserAccessLogRepository userAccessLogRepository;

    @Autowired
    BlockedIpTableService blockedIpTableService;

    @Override
    public Long getCount() {
        return userAccessLogRepository.count();
    }


    public Map<String, List<BlockedIpTable> > getIpExceededLimits(){
        LocalDateTime endDateTime = DateUtil.getEndDateTime(GlobalConstants.start, GlobalConstants.duration.getDuration());
        List<UserAccessLog> userAccessLogList = userAccessLogRepository.findUserAccessLogByDateTimeBetween(GlobalConstants.start.toString(), endDateTime.toString());
        logger.info("userAccessLogList.size() => {} ", userAccessLogList.size());

        Set<UserAccessLog> distinctUserAccessLogHashSet = new HashSet<>(userAccessLogList);
        logger.info("distinctUserAccessLogHashSet.size() => {} ", distinctUserAccessLogHashSet.size());

        return checkBlockedIp( userAccessLogList, distinctUserAccessLogHashSet);//abstracted into another method for readability. methods should not be too long
    }


    private Map<String, List<BlockedIpTable>> checkBlockedIp( List<UserAccessLog> userAccessLogList, Set<UserAccessLog> distinctUserAccessLogHashSet){
        int totalDataCount = 0;
        int totalBlockedCount = 0;
        String comment = Util.generateComment(GlobalConstants.limit);
        Map<String, List<BlockedIpTable> > blockedIpTableHashMap = new HashMap<>();
        for(UserAccessLog distinctUserAccessLog: distinctUserAccessLogHashSet){
            int distinctUserAccessLogFrequency = Collections.frequency( userAccessLogList, distinctUserAccessLog);
            if(distinctUserAccessLogFrequency > GlobalConstants.limit){
                List<BlockedIpTable> blockedIpTableList = userAccessLogList.stream()
                        .filter( u -> u.equals( distinctUserAccessLog )  )
                        .map( u -> BlockedIpTable.of(u, comment ) ).collect(Collectors.toList());
                blockedIpTableHashMap.put(distinctUserAccessLog.getIp(), blockedIpTableList);
                logger.info("ip => {} is greater than {}", distinctUserAccessLog.getIp(), GlobalConstants.limit);
                logger.info("blockUserAccessLogList=> {} <====> {}", blockedIpTableList.get(0).getIp(), blockedIpTableList.size());
                logger.info("<===================================>");
                totalBlockedCount = totalBlockedCount + blockedIpTableList.size();
            }
            totalDataCount = totalDataCount + distinctUserAccessLogFrequency;
        }
        logger.info("totalDataCount=-> {} ", totalDataCount);
        logger.info("totalBlockedCount=-> {} ", totalBlockedCount);
        return blockedIpTableHashMap;
    }


}
