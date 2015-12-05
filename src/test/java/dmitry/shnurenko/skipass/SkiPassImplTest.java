package dmitry.shnurenko.skipass;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

import static dmitry.shnurenko.skipass.SkiPass.LimitType.LIFTS_LIMIT;
import static dmitry.shnurenko.skipass.SkiPass.Type.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class SkiPassImplTest {
    private static final LocalDateTime START_ACTIVE_TIME = LocalDateTime.now();
    private static final LocalDateTime END_ACTIVE_TIME   = START_ACTIVE_TIME.plusHours(1);

    private SkiPassParameters parameters;
    private SkiPassImpl       skiPass;


    @Before
    public void setUp() {
        parameters = SkiPassParameters.getBuilder()
                                      .withType(TEN_LIFTS)
                                      .withStartActiveTime(START_ACTIVE_TIME)
                                      .withEndActiveTime(END_ACTIVE_TIME)
                                      .withLiftCount(10)
                                      .build();
        skiPass = new SkiPassImpl(parameters);
    }

    @Test
    public void verifyCreatedSkiPass() {
        assertThat(skiPass.getLiftCounts(), is(equalTo(10)));
        assertThat(skiPass.getType(), is(equalTo(TEN_LIFTS)));
        assertThat(skiPass.getStartActiveTime(), is(equalTo(START_ACTIVE_TIME)));
        assertThat(skiPass.getEndActiveTime(), is(equalTo(END_ACTIVE_TIME)));
    }

    @Test
    public void countLiftsTypeShouldBeReturnedWhenCountLiftsIsMoreThenZero() {
        assertThat(skiPass.getLimitType(), is(equalTo(LIFTS_LIMIT)));
    }

    @Test
    public void liftCountsShouldBeReduced() {
        skiPass.reduceLiftCounts();

        assertThat(skiPass.getLiftCounts(), is(equalTo(9)));
    }

    @Test
    public void differentSkiPassesShouldHadDifferentIdsAndAreNotEquals() {
        SkiPass testSkiPass = new SkiPassImpl(parameters);

        assertThat(skiPass.equals(testSkiPass), is(equalTo(false)));
    }
}