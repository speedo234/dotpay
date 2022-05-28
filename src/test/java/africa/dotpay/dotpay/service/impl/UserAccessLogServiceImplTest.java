package africa.dotpay.dotpay.service.impl;

import africa.dotpay.dotpay.constants.GlobalConstants;
import africa.dotpay.dotpay.model.BlockedIpDtoImpl;
import africa.dotpay.dotpay.model.BlockedIpTable;
import africa.dotpay.dotpay.model.IBlockedIpDto;
import africa.dotpay.dotpay.repository.UserAccessLogRepository;
import africa.dotpay.dotpay.service.UserAccessLogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@MockitoSettings(strictness = Strictness.STRICT_STUBS)
@ExtendWith(MockitoExtension.class)
class UserAccessLogServiceImplTest {

    @Mock
    UserAccessLogRepository userAccessLogRepository;

    @Mock
    GlobalConstants globalConstants;

    @InjectMocks
    UserAccessLogServiceImpl userAccessLogService;


    @BeforeEach
    void setUp() {

    }


    @Test
    void getCount() {
        Long expected = 100000L;
        when( userAccessLogRepository.count() ).thenReturn( 100000L );
        Long actual = userAccessLogService.getCount();
        assertThat( actual ).isEqualTo( expected );
        assertThat( actual ).isNotNegative( );
        assertThat( actual ).isNotNull();
    }

    @Disabled
    void getBlockedIps() {
        /*final*/ String start = "2022-01-01T00:00:00";
        /*final*/ String end = "2022-01-01T00:00:00";
        /*final*/ int limit = 100;

        IBlockedIpDto iBlockedIpDto = new BlockedIpDtoImpl();
        ((BlockedIpDtoImpl) iBlockedIpDto).setIp("192.168.129.191");
        ((BlockedIpDtoImpl) iBlockedIpDto).setRequestnumber(444L);
        List<IBlockedIpDto> iBlockedIpDtoList = new ArrayList<>();
        iBlockedIpDtoList.add(iBlockedIpDto);
        iBlockedIpDtoList.add(iBlockedIpDto);

//        when( globalConstants.start.toString() ).thenReturn( start );
//        when( globalConstants.end.toString() ).thenReturn( end );
//        when( globalConstants.limit ).thenReturn( limit );
        when( userAccessLogRepository.findUserAccessLogByDateTimeAndCount("2022-01-01T00:00:00", "2022-01-01T01:00:00", 100) ).thenReturn( iBlockedIpDtoList );
        List<BlockedIpTable> actual = userAccessLogService.getBlockedIps();

        System.out.println("==actual.siz=>>>> "+actual.size());

        assertThat( actual.size() ).isGreaterThan( 0 );
        assertThat( actual ).isNotNull();
    }

    @Test
    void clearUserAccessLog() {
        userAccessLogService.clearUserAccessLog();
        verify(userAccessLogRepository).deleteAll();
    }
}