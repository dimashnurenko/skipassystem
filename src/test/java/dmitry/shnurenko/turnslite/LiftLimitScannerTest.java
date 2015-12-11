package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.LiftsLimitSkiPass;
import dmitry.shnurenko.skipass.SkiPassCreator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.TEN_LIFTS;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.FAIL;
import static dmitry.shnurenko.turnslite.Turnslite.ScanStatus.SUCCESS;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class LiftLimitScannerTest {
    private SkiPassScanner<LiftsLimitSkiPass> checker = new LiftLimitScanner();

    @Test
    public void skiPassCheckShouldBeSuccessWhenItHasLifts() {
        LiftsLimitSkiPass skiPass = (LiftsLimitSkiPass) SkiPassCreator.create(TEN_LIFTS);

        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
    }

    @Test
    public void skiPassCheckShouldBeFailWhenItHasNotLifts() {
        LiftsLimitSkiPass skiPass = (LiftsLimitSkiPass) SkiPassCreator.create(TEN_LIFTS);

        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(SUCCESS)));
        assertThat(checker.scan(skiPass), is(equalTo(FAIL)));
    }
}