package africa.dotpay.dotpay.service;


import africa.dotpay.dotpay.model.BlockedIpTable;

import java.util.List;

public interface BlockedIpTableService {

    public void saveBlockedIp(BlockedIpTable blockedIpTable);

    public void saveBlockedIpList(List<BlockedIpTable> blockedIpTableList);

    public void clearBlockedIp();

    public Long getCount();

}
