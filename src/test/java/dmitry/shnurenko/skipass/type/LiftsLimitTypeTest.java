package dmitry.shnurenko.skipass.type;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.*;
import static dmitry.shnurenko.skipass.type.ScannerType.LIFTS_SCANNER;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class LiftsLimitTypeTest {

    @Test
    public void liftsScannerTypeShouldBeReturned() {
        assertThat(TEN_LIFTS.getScannerType(), is(equalTo(LIFTS_SCANNER)));
    }

    @Test
    public void tenLiftsShouldBeReturnedWhenGetCountOfLiftsInTenLiftsType() {
        assertThat(TEN_LIFTS.getCountLifts(), is(equalTo(10)));
    }

    @Test
    public void twentyLiftsShouldBeReturnedWhenGetCountOfLiftsInTwentyLiftsType() {
        assertThat(TWENTY_LIFTS.getCountLifts(), is(equalTo(20)));
    }

    @Test
    public void hundredLiftsShouldBeReturnedWhenGetCountOfLiftsInHundredLiftsType() {
        assertThat(ONE_HUNDRED_LIFTS.getCountLifts(), is(equalTo(100)));
    }
}