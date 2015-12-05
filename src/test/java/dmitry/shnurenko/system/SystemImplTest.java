package dmitry.shnurenko.system;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.Type;
import dmitry.shnurenko.turnslite.Turnslite;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static dmitry.shnurenko.skipass.SkiPass.Type.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class SystemImplTest {

    @Mock
    private Turnslite      turnslite;
    @Mock
    private SkiPassCreator creator;
    @Mock
    private SkiPass        skiPass;

    @InjectMocks
    private SystemImpl system;

    @Before
    public void setUp() {
        when(creator.create(Matchers.<Type>anyObject())).thenReturn(skiPass);
    }

    @Test
    public void skiPassShouldBeCreated() {
        system.createSkiPass(TEN_LIFTS);

        verify(creator).create(TEN_LIFTS);
    }

    @Test
    public void skiPassShouldBeBlocked() {
        system.blockSkiPass(skiPass);

        system.onSkiPassScanSuccess(skiPass);

        verify(turnslite).turnRedLight();
        verify(turnslite, never()).turnGreenLight();
    }

    @Test
    public void createdPassagesForPeriodShouldBeReturned() {
        System system = new SystemImpl(turnslite, creator);

        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime until = from.plusMinutes(2);
        LocalDateTime createdTime = LocalDateTime.now();

        when(skiPass.getCreationDate()).thenReturn(createdTime);

        system.createSkiPass(TEN_LIFTS);

        List<SkiPass> createdPassesForPeriod = system.getCreatedPassesForPeriod(from, until);

        assertThat(createdPassesForPeriod.size(), is(equalTo(1)));
        assertThat(createdPassesForPeriod.get(0), is(equalTo(skiPass)));
    }

    @Test
    public void skiPassScanShouldBeSuccess() {
        system.onSkiPassScanSuccess(skiPass);

        verify(turnslite).turnGreenLight();
    }

    @Test
    public void skiPassScanShouldBeFail() {
        system.onSkiPassScanFail(skiPass);

        verify(turnslite).turnRedLight();
    }

    @Test
    public void successPassagesForPeriodShouldBeReturned() {
        when(skiPass.getType()).thenReturn(TEN_LIFTS);

        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime until = from.plusMinutes(2);

        system.onSkiPassScanSuccess(skiPass);

        List<SkiPass> successPassagesForPeriod = system.getSuccessPassagesForPeriod(from, until, TEN_LIFTS);

        assertThat(successPassagesForPeriod.size(), is(equalTo(1)));
        assertThat(successPassagesForPeriod.get(0), is(equalTo(skiPass)));
    }

    @Test
    public void failPassagesForPeriodShouldBeReturned() {
        when(skiPass.getType()).thenReturn(TEN_LIFTS);

        LocalDateTime from = LocalDateTime.now().minusMinutes(1);
        LocalDateTime until = from.plusMinutes(2);

        system.onSkiPassScanFail(skiPass);

        List<SkiPass> failPassagesForPeriod = system.getFailPassagesForPeriod(from, until, TEN_LIFTS);

        assertThat(failPassagesForPeriod.size(), is(equalTo(1)));
        assertThat(failPassagesForPeriod.get(0), is(equalTo(skiPass)));
    }
}