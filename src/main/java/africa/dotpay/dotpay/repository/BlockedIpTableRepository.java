package africa.dotpay.dotpay.repository;


import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.model.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BlockedIpTableRepository extends JpaRepository<BlockedIpTable, Long> {


}
