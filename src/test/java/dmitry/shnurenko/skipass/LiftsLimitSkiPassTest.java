package dmitry.shnurenko.skipass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class LiftsLimitSkiPassTest {

    private LiftsLimitSkiPass skiPass = new LiftsLimitSkiPass(TEN_LIFTS);

    @Test
    public void liftsShouldBeReduced() {
        assertThat(skiPass.reduceLifts(), is(equalTo(9)));
        assertThat(skiPass.reduceLifts(), is(equalTo(8)));
    }

    @Test
    public void skiPassTypeShouldBeReturned() {
        assertThat(skiPass.getType(), is(equalTo(TEN_LIFTS)));
    }
}