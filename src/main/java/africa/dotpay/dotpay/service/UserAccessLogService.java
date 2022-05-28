package africa.dotpay.dotpay.service;


import africa.dotpay.dotpay.model.BlockedIpTable;

import java.util.List;

public interface UserAccessLogService {


    public Long getCount();

    public List<BlockedIpTable> getBlockedIps();

    public void clearUserAccessLog();

}
