package africa.dotpay.dotpay.service;


import africa.dotpay.dotpay.model.BlockedIpTable;

import java.util.List;
import java.util.Map;

public interface UserAccessLogService {


    public Long getCount();

    public List<BlockedIpTable> getBlockedIps();

    public void clearUserAccessLog();

}
