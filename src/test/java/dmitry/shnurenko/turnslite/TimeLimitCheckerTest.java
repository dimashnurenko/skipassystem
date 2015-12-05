package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPassFactory;
import dmitry.shnurenko.skipass.SkiPassParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.SkiPass.Type.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLimitCheckerTest {

    private SkiPassFactory factory = new SkiPassFactory();
    private SkiPassChecker checker = new TimeLimitChecker();

    @Test
    public void skiPassShouldBeCheckedSuccessWhenCheckTimeIsInPeriod() {
        LocalDateTime startActiveTime = LocalDateTime.now().minusMinutes(2);
        LocalDateTime endActiveTime = startActiveTime.plusMinutes(4);

        SkiPass skiPass = factory.createSkiPass(SkiPassParameters.getBuilder()
                                                                 .withStartActiveTime(startActiveTime)
                                                                 .withEndActiveTime(endActiveTime)
                                                                 .withType(TEN_LIFTS)
                                                                 .build());
        boolean isActive = checker.check(skiPass);

        assertThat(isActive, is(true));
    }

    @Test
    public void skiPassShouldBeCheckedFailWhenCheckTimeIsNotInPeriod() {
        LocalDateTime startActiveTime = LocalDateTime.now().plusMinutes(2);
        LocalDateTime endActiveTime = startActiveTime.plusMinutes(4);

        SkiPass skiPass = factory.createSkiPass(SkiPassParameters.getBuilder()
                                                                 .withStartActiveTime(startActiveTime)
                                                                 .withEndActiveTime(endActiveTime)
                                                                 .withType(TEN_LIFTS)
                                                                 .build());
        boolean isActive = checker.check(skiPass);

        assertThat(isActive, is(false));
    }
}