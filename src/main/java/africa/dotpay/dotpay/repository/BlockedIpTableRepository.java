package africa.dotpay.dotpay.repository;


import africa.dotpay.dotpay.model.BlockedIpTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BlockedIpTableRepository extends JpaRepository<BlockedIpTable, Long> {


}
