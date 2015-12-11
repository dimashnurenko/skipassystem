package dmitry.shnurenko.skipass;

import dmitry.shnurenko.skipass.type.DayTimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.type.Time.MORNING_FROM;
import static dmitry.shnurenko.skipass.type.Time.MORNING_UNTIL;
import static dmitry.shnurenko.skipass.type.TimeLimitType.HALF_DAY_MORNING;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLimitSkiPassTest {

    private TimeLimitSkiPass skiPass = new TimeLimitSkiPass(HALF_DAY_MORNING);

    @Test
    public void startAndAndTimeShouldBeReturned() {
        LocalDateTime expectedStartTime = DayTimeUtil.todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime expectedEndTime = DayTimeUtil.todayDateWithTime(MORNING_UNTIL.hours(), MORNING_UNTIL.minutes());

        assertThat(skiPass.getStartActivePeriod(), is(equalTo(expectedStartTime)));
        assertThat(skiPass.getEndActivePeriod(), is(equalTo(expectedEndTime)));
    }

    @Test
    public void skiPassTypeShouldBeReturned() {
        assertThat(skiPass.getType(), is(equalTo(HALF_DAY_MORNING)));
    }
}