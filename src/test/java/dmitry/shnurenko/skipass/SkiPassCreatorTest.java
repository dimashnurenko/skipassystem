package dmitry.shnurenko.skipass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.TEN_LIFTS;
import static dmitry.shnurenko.skipass.type.ScannerType.LIFTS_SCANNER;
import static dmitry.shnurenko.skipass.type.ScannerType.TIME_SCANNER;
import static dmitry.shnurenko.skipass.type.TimeLimitType.HALF_DAY_MORNING;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class SkiPassCreatorTest {

    @Test
    public void skiPassWithTimeScannerTypeShouldBeCreated() {
        SkiPass skiPass = SkiPassCreator.create(HALF_DAY_MORNING);

        assertThat(skiPass.getScannerType(), is(equalTo(TIME_SCANNER)));
    }

    @Test
    public void skiPassWithLiftsScannerTypeShouldBeCreated() {
        SkiPass skiPass = SkiPassCreator.create(TEN_LIFTS);

        assertThat(skiPass.getScannerType(), is(equalTo(LIFTS_SCANNER)));
    }
}