package dmitry.shnurenko.skipass.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.type.DayTimeUtil.todayDateWithTime;
import static dmitry.shnurenko.skipass.type.ScannerType.TIME_SCANNER;
import static dmitry.shnurenko.skipass.type.Time.*;
import static dmitry.shnurenko.skipass.type.TimeLimitType.*;
import static java.time.Month.DECEMBER;
import static java.time.Month.JANUARY;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class TimeLimitTypeTest {

    @Test
    public void timeLimitTypeScannerShouldBeReturned() {
        assertThat(FIVE_DAYS.getScannerType(), is(equalTo(TIME_SCANNER)));
    }

    @Test
    public void morningSkiPassActiveTimeShouldBeReturned() {
        LocalDateTime startActivePeriod = todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime endActivePeriod = todayDateWithTime(MORNING_UNTIL.hours(), MORNING_UNTIL.minutes());

        assertThat(HALF_DAY_MORNING.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(HALF_DAY_MORNING.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }

    @Test
    public void eveningSkiPassActiveTimeShouldBeReturned() {
        LocalDateTime startActivePeriod = todayDateWithTime(EVENING_FROM.hours(), EVENING_FROM.minutes());
        LocalDateTime endActivePeriod = todayDateWithTime(EVENING_UNTIL.hours(), EVENING_UNTIL.minutes());

        assertThat(HALF_DAY_EVENING.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(HALF_DAY_EVENING.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }

    @Test
    public void fullSeasonSkiPassActiveTimeShouldBeReturned() {
        int currentYear = LocalDateTime.now().getYear();

        LocalDateTime startActivePeriod = LocalDateTime.of(currentYear, JANUARY, 1, 0, 0);
        LocalDateTime endActivePeriod = LocalDateTime.of(currentYear, DECEMBER, 31, 23, 59);

        assertThat(FULL_SEASON.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(FULL_SEASON.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }

    @Test
    public void oneDaySkiPassActiveTimeShouldBeReturned() {
        LocalDateTime startActivePeriod = todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime endActivePeriod = todayDateWithTime(END_SKI_DAY.hours(), END_SKI_DAY.minutes());

        assertThat(ONE_DAY.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(ONE_DAY.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }

    @Test
    public void twoDaysSkiPassActiveTimeShouldBeReturned() {
        LocalDateTime startActivePeriod = todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime endActivePeriod = startActivePeriod.plusDays(2)
                                                         .withHour(END_SKI_DAY.hours())
                                                         .withMinute(END_SKI_DAY.minutes());

        assertThat(TWO_DAYS.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(TWO_DAYS.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }

    @Test
    public void fiveDaysSkiPassActiveTimeShouldBeReturned() {
        LocalDateTime startActivePeriod = todayDateWithTime(MORNING_FROM.hours(), MORNING_FROM.minutes());
        LocalDateTime endActivePeriod = startActivePeriod.plusDays(5)
                                                         .withHour(END_SKI_DAY.hours())
                                                         .withMinute(END_SKI_DAY.minutes());

        assertThat(FIVE_DAYS.getStartActivePeriod(), is(equalTo(startActivePeriod)));
        assertThat(FIVE_DAYS.getEndActivePeriod(), is(equalTo(endActivePeriod)));
    }
}