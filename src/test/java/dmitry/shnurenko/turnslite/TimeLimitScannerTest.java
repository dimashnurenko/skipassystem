package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.TimeLimitSkiPass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.FAIL;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.SUCCESS;
import static java.time.LocalDateTime.now;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLimitScannerTest {

    @Mock
    private TimeLimitSkiPass skiPass;

    private SkiPassScanner<TimeLimitSkiPass> checker = new TimeLimitScanner();

    @Test
    public void skiPassShouldBeCheckedSuccessWhenCheckTimeIsInPeriod() {
        when(skiPass.getStartActivePeriod()).thenReturn(now().minusMinutes(2));
        when(skiPass.getEndActivePeriod()).thenReturn(now().plusMinutes(2));

        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
    }

    @Test
    public void skiPassShouldBeCheckedFailWhenCheckTimeIsNotInPeriod() {
        when(skiPass.getStartActivePeriod()).thenReturn(now().plusMinutes(2));
        when(skiPass.getEndActivePeriod()).thenReturn(now().plusMinutes(4));

        assertThat(checker.scan(skiPass), is(equalTo(FAIL)));
    }
}