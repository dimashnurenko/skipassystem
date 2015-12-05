package dmitry.shnurenko.system;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPass.Type;
import dmitry.shnurenko.skipass.SkiPassFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.SkiPass.Type.*;
import static dmitry.shnurenko.system.Time.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class SkiPassCreatorTest {

    private SkiPassCreator creator = new SkiPassCreator(new SkiPassFactory());

    @Test
    public void skiPassWithCustomParametersShouldBeCreated() {
        LocalDateTime startActiveTime = LocalDateTime.now();
        LocalDateTime endActiveTime = startActiveTime.plusHours(1);

        SkiPass customSkiPass = creator.createCustomSkiPass(TEN_LIFTS, startActiveTime, endActiveTime, 10);

        assertSkiPass(customSkiPass, 10, startActiveTime, endActiveTime, TEN_LIFTS);
    }

    private void assertSkiPass(SkiPass skiPass,
                               int liftCounts,
                               LocalDateTime startActiveTime,
                               LocalDateTime endActiveTime,
                               Type type) {
        assertThat(skiPass.getLiftCounts(), is(equalTo(liftCounts)));
        assertThat(skiPass.getStartActiveTime(), is(equalTo(startActiveTime)));
        assertThat(skiPass.getEndActiveTime(), is(equalTo(endActiveTime)));
        assertThat(skiPass.getType(), is(equalTo(type)));
    }

    @Test
    public void fullSeasonSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(FULL_SEASON);

        int year = LocalDateTime.now().getYear();

        assertSkiPass(skiPass,
                      0,
                      LocalDateTime.of(year, 1, 1, 0, 0),
                      LocalDateTime.of(year, 12, 31, 23, 59),
                      FULL_SEASON);
    }

    @Test
    public void halfDayMorningSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(HALF_DAY_MORNING);

        assertTimeLimitSkiPass(skiPass,
                               MORNING_FROM.hours(),
                               MORNING_FROM.minutes(),
                               MORNING_UNTIL.hours(),
                               MORNING_UNTIL.minutes(),
                               HALF_DAY_MORNING);
    }

    private void assertTimeLimitSkiPass(SkiPass skiPass,
                                        int hourFrom,
                                        int minuteFrom,
                                        int hourUntil,
                                        int minuteUntil,
                                        Type type) {
        assertThat(skiPass.getStartActiveTime().getHour(), is(equalTo(hourFrom)));
        assertThat(skiPass.getStartActiveTime().getMinute(), is(equalTo(minuteFrom)));
        assertThat(skiPass.getEndActiveTime().getHour(), is(equalTo(hourUntil)));
        assertThat(skiPass.getEndActiveTime().getMinute(), is(equalTo(minuteUntil)));
        assertThat(skiPass.getType(), is(equalTo(type)));
        assertThat(skiPass.getLiftCounts(), is(equalTo(0)));
    }

    @Test
    public void halfDayEveningSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(HALF_DAY_EVENING);

        assertTimeLimitSkiPass(skiPass,
                               EVENING_FROM.hours(),
                               EVENING_FROM.minutes(),
                               EVENING_UNTIL.hours(),
                               EVENING_UNTIL.minutes(),
                               HALF_DAY_EVENING);
    }

    @Test
    public void oneDaySkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(ONE_DAY);

        assertCountDaySkiPass(skiPass, 1, ONE_DAY);
    }

    private void assertCountDaySkiPass(SkiPass skiPass, int countActiveDays, Type type) {
        LocalDateTime startDay = skiPass.getStartActiveTime();
        LocalDateTime endDay = skiPass.getEndActiveTime();

        assertThat(startDay.plusDays(countActiveDays).getDayOfMonth(), is(equalTo(endDay.getDayOfMonth())));
        assertThat(skiPass.getType(), is(equalTo(type)));
        assertThat(skiPass.getLiftCounts(), is(equalTo(0)));
    }

    @Test
    public void twoDaySkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(TWO_DAYS);

        assertCountDaySkiPass(skiPass, 2, TWO_DAYS);
    }

    @Test
    public void fiveDaySkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(FIVE_DAYS);

        assertCountDaySkiPass(skiPass, 5, FIVE_DAYS);
    }

    @Test
    public void tenLiftsSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(TEN_LIFTS);

        assertLiftLimitSkiPass(skiPass, 10, TEN_LIFTS);
    }

    private void assertLiftLimitSkiPass(SkiPass skiPass, int countLifts, Type type) {
        assertThat(skiPass.getLiftCounts(), is(equalTo(countLifts)));
        assertThat(skiPass.getStartActiveTime(), is(nullValue()));
        assertThat(skiPass.getEndActiveTime(), is(nullValue()));
        assertThat(skiPass.getType(), is(equalTo(type)));
    }

    @Test
    public void twentyLiftsSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(TWENTY_LIFTS);

        assertLiftLimitSkiPass(skiPass, 20, TWENTY_LIFTS);
    }

    @Test
    public void oneHundredLiftsSkiPassShouldBeCreated() {
        SkiPass skiPass = creator.create(ONE_HUNDRED_LIFTS);

        assertLiftLimitSkiPass(skiPass, 100, ONE_HUNDRED_LIFTS);
    }
}