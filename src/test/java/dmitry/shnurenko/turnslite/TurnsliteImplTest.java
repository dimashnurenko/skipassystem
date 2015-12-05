package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.LimitType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static dmitry.shnurenko.skipass.SkiPass.LimitType.LIFTS_LIMIT;
import static dmitry.shnurenko.skipass.SkiPass.LimitType.TIME_LIMIT;
import static java.time.LocalDateTime.now;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TurnsliteImplTest {

    @Mock
    private SkiPassScanListener listener;
    @Mock
    private SkiPass             skiPass;

    private TurnsliteImpl turnslite;

    @Before
    public void setUp() {
        Map<LimitType, SkiPassChecker> checkers = new HashMap<>();
        checkers.put(LIFTS_LIMIT, new LiftLimitChecker());
        checkers.put(TIME_LIMIT, new TimeLimitChecker());

        turnslite = new TurnsliteImpl(checkers);

        turnslite.addScanListener(listener);
    }

    @Test
    public void liftLimitSkiPassScanShouldBeSuccess() {
        when(skiPass.getLimitType()).thenReturn(LIFTS_LIMIT);
        when(skiPass.getLiftCounts()).thenReturn(2);

        turnslite.scan(skiPass);

        verify(listener).onSkiPassScanSuccess(skiPass);
    }

    @Test
    public void liftLimitSkiPassScanShouldBeFail() {
        when(skiPass.getLimitType()).thenReturn(LIFTS_LIMIT);
        when(skiPass.getLiftCounts()).thenReturn(-1);

        turnslite.scan(skiPass);

        verify(listener).onSkiPassScanFail(skiPass);
    }

    @Test
    public void timeLimitSkiPassScanShouldBeSuccess() {
        when(skiPass.getLimitType()).thenReturn(TIME_LIMIT);
        when(skiPass.getStartActiveTime()).thenReturn(now().minusHours(1));
        when(skiPass.getEndActiveTime()).thenReturn(now().plusHours(1));

        turnslite.scan(skiPass);

        verify(listener).onSkiPassScanSuccess(skiPass);
    }

    @Test
    public void timeLimitSkiPassScanShouldBeFail() {
        when(skiPass.getLimitType()).thenReturn(TIME_LIMIT);
        when(skiPass.getStartActiveTime()).thenReturn(now().plusHours(1));
        when(skiPass.getEndActiveTime()).thenReturn(now().plusHours(2));

        turnslite.scan(skiPass);

        verify(listener).onSkiPassScanFail(skiPass);
    }
}