package africa.dotpay.dotpay.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
@Entity
@NoArgsConstructor
@ToString
public class BlockedIpTable implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    private String ip;
    private Long requestNumber;
    private String comment;


    public BlockedIpTable(UserAccessLog userAccessLog) {
        this.ip = userAccessLog.getIp();
        this.requestNumber = userAccessLog.getId();
    }

    public BlockedIpTable(String ip, Long requestNumber, String comment) {
        this.ip = ip;
        this.requestNumber = requestNumber;
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockedIpTable that = (BlockedIpTable) o;
        return getIp().equals(that.getIp());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIp());
    }


    public static BlockedIpTable of(IBlockedIpDto iBlockedIpDto, String comment){
        return new BlockedIpTable(iBlockedIpDto.getIp(), iBlockedIpDto.getRequestNumber(), comment);
    }


}
