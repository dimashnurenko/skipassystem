package dmitry.shnurenko.skipass.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeTest {

    @Test
    public void morningTimeShouldBeReturnedFromWhichSkiPassIsActive() {
        assertThat(Time.MORNING_FROM.hours(), is(equalTo(9)));
        assertThat(Time.MORNING_FROM.minutes(), is(equalTo(0)));
    }

    @Test
    public void morningTimeShouldBeReturnedUntilWhichSkiPassIsActive() {
        assertThat(Time.MORNING_UNTIL.hours(), is(equalTo(13)));
        assertThat(Time.MORNING_UNTIL.minutes(), is(equalTo(0)));
    }

    @Test
    public void eveningTimeShouldBeReturnedFromWhichSkiPassIsActive() {
        assertThat(Time.EVENING_FROM.hours(), is(equalTo(13)));
        assertThat(Time.EVENING_FROM.minutes(), is(equalTo(0)));
    }

    @Test
    public void eveningTimeShouldBeReturnedUntilWhichSkiPassIsActive() {
        assertThat(Time.EVENING_UNTIL.hours(), is(equalTo(17)));
        assertThat(Time.EVENING_UNTIL.minutes(), is(equalTo(0)));
    }

    @Test
    public void endSkiDayTimeShouldBeReturned() {
        assertThat(Time.END_SKI_DAY.hours(), is(equalTo(20)));
        assertThat(Time.END_SKI_DAY.minutes(), is(equalTo(0)));
    }
}