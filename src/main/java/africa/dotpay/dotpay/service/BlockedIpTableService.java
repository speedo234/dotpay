package africa.dotpay.dotpay.service;


import africa.dotpay.dotpay.model.BlockedIpTable;

import java.util.List;
import java.util.Map;

public interface BlockedIpTableService {


    public void saveBlockedIp(BlockedIpTable blockedIpTable);

    public void saveBlockedIpList(List<BlockedIpTable> blockedIpTableList);

    public void processHashMapToDb(Map<String, List<BlockedIpTable>> checkBlockedIpResponse);

}
