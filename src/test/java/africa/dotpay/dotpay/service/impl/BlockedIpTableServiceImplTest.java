package africa.dotpay.dotpay.service.impl;

import africa.dotpay.dotpay.constants.GlobalConstants;
import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.repository.BlockedIpTableRepository;
import africa.dotpay.dotpay.repository.UserAccessLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class BlockedIpTableServiceImplTest {

    @Mock
    BlockedIpTableRepository blockedIpTableRepository;

    @InjectMocks
    BlockedIpTableServiceImpl blockedIpTableService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void saveBlockedIp() {
        BlockedIpTable blockedIpTable = new BlockedIpTable();
        blockedIpTable.setIp("192.168.62.176");
        blockedIpTable.setRequestNumber(444L);
        blockedIpTable.setComment("this ip is blocked");
        blockedIpTableService.saveBlockedIp(blockedIpTable);
        verify(blockedIpTableRepository).save(blockedIpTable);
    }

    @Test
    void saveBlockedIpList() {
        BlockedIpTable blockedIpTable = new BlockedIpTable();
        blockedIpTable.setIp("192.168.62.176");
        blockedIpTable.setRequestNumber(444L);
        blockedIpTable.setComment("this ip is blocked");
        List<BlockedIpTable> blockedIpTableList = Arrays.asList(blockedIpTable, blockedIpTable);
        blockedIpTableService.saveBlockedIpList(blockedIpTableList);
        verify(blockedIpTableRepository).saveAll(blockedIpTableList);
    }

    @Test
    void clearBlockedIp() {
        blockedIpTableService.clearBlockedIp();
        verify(blockedIpTableRepository).deleteAll();
    }

    @Test
    void getCount() {
        Long expected = 1000L;
        when(blockedIpTableRepository.count()).thenReturn(1000L);
        Long actual = blockedIpTableService.getCount();
        assertThat(actual).isEqualTo(expected);
    }
}