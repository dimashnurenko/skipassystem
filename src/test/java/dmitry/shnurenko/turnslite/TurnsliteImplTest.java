package dmitry.shnurenko.turnslite;

import com.google.common.collect.ImmutableMap;
import dmitry.shnurenko.skipass.LiftsLimitSkiPass;
import dmitry.shnurenko.skipass.TimeLimitSkiPass;
import dmitry.shnurenko.skipass.type.ScannerType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.TEN_LIFTS;
import static dmitry.shnurenko.skipass.type.ScannerType.LIFTS_SCANNER;
import static dmitry.shnurenko.skipass.type.ScannerType.TIME_SCANNER;
import static dmitry.shnurenko.skipass.type.TimeLimitType.HALF_DAY_MORNING;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.FAIL;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.SUCCESS;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TurnsliteImplTest {

    @Mock
    private SkiPassScanListener               listener;
    @Mock
    private TimeLimitSkiPass                  timeLimitSkiPass;
    @Mock
    private LiftsLimitSkiPass                 liftsLimitSkiPass;
    @Mock
    private SkiPassScanner<TimeLimitSkiPass>  timeLimitScanner;
    @Mock
    private SkiPassScanner<LiftsLimitSkiPass> liftLimitScanner;

    private TurnsliteImpl turnslite;

    @Before
    public void setUp() {
        Map<ScannerType, SkiPassScanner> scanners = ImmutableMap.of(TIME_SCANNER, timeLimitScanner,
                                                                    LIFTS_SCANNER, liftLimitScanner);

        turnslite = new TurnsliteImpl(scanners);
        turnslite.addScanListener(listener);
    }

    @Test
    public void liftLimitSkiPassScanShouldBeSuccess() {
        when(timeLimitSkiPass.getType()).thenReturn(HALF_DAY_MORNING);
        when(timeLimitSkiPass.getScannerType()).thenReturn(LIFTS_SCANNER);
        when(timeLimitScanner.scan(timeLimitSkiPass)).thenReturn(SUCCESS);

        turnslite.scan(timeLimitSkiPass);

        verify(timeLimitScanner).scan(timeLimitSkiPass);
        verify(listener).onSkiPassScanSuccess(timeLimitSkiPass);
    }

    @Test
    public void liftLimitSkiPassScanShouldBeFail() {
        when(liftsLimitSkiPass.getType()).thenReturn(TEN_LIFTS);
        when(liftsLimitSkiPass.getScannerType()).thenReturn(LIFTS_SCANNER);
        when(liftLimitScanner.scan(liftsLimitSkiPass)).thenReturn(FAIL);

        turnslite.scan(liftsLimitSkiPass);

        verify(listener).onSkiPassScanFail(liftsLimitSkiPass);
    }

    @Test
    public void timeLimitSkiPassScanShouldBeSuccess() {
        when(timeLimitSkiPass.getType()).thenReturn(HALF_DAY_MORNING);
        when(timeLimitSkiPass.getScannerType()).thenReturn(TIME_SCANNER);
        when(timeLimitScanner.scan(timeLimitSkiPass)).thenReturn(SUCCESS);

        turnslite.scan(timeLimitSkiPass);

        verify(listener).onSkiPassScanSuccess(timeLimitSkiPass);
    }

    @Test
    public void timeLimitSkiPassScanShouldBeFail() {
        when(timeLimitSkiPass.getType()).thenReturn(HALF_DAY_MORNING);
        when(timeLimitSkiPass.getScannerType()).thenReturn(TIME_SCANNER);
        when(timeLimitScanner.scan(timeLimitSkiPass)).thenReturn(FAIL);

        turnslite.scan(timeLimitSkiPass);

        verify(listener).onSkiPassScanFail(timeLimitSkiPass);
    }
}