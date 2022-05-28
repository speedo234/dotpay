package africa.dotpay.dotpay.model;



public class BlockedIpDtoImpl implements IBlockedIpDto {


    String ip;
    Long requestnumber;


    @Override
    public String getIp() {
        return this.ip;
    }

    @Override
    public Long getRequestnumber() {
        return this.requestnumber;
    }


    public String setIp(String ip) {
        return this.ip = ip;
    }


    public Long setRequestnumber(Long requestnumber) {
        return this.requestnumber = requestnumber;
    }

}
