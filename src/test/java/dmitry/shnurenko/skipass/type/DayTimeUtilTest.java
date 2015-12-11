package dmitry.shnurenko.skipass.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.type.Time.MORNING_FROM;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class DayTimeUtilTest {

    @Test
    public void todayDateWithDefinedTimeShouldBeReturned() {
        LocalDateTime todayDate = DayTimeUtil.todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime now = LocalDateTime.now();

        assertThat(todayDate.getYear(), is(equalTo(now.getYear())));
        assertThat(todayDate.getMonth(), is(equalTo(now.getMonth())));
        assertThat(todayDate.getDayOfMonth(), is(equalTo(now.getDayOfMonth())));
        assertThat(todayDate.getHour(), is(equalTo(MORNING_FROM.hours())));
        assertThat(todayDate.getMinute(), is(equalTo(MORNING_FROM.minutes())));
    }
}