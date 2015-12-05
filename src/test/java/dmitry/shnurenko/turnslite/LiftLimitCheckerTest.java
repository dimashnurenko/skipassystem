package dmitry.shnurenko.turnslite;

import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPassFactory;
import dmitry.shnurenko.skipass.SkiPassParameters;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static dmitry.shnurenko.skipass.SkiPass.Type.TEN_LIFTS;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(MockitoJUnitRunner.class)
public class LiftLimitCheckerTest {
    private SkiPassFactory factory = new SkiPassFactory();
    private SkiPassChecker checker = new LiftLimitChecker();

    @Test
    public void skiPassShouldBeChecked() {
        SkiPass skiPass = factory.createSkiPass(SkiPassParameters.getBuilder()
                                                                 .withLiftCount(1)
                                                                 .withType(TEN_LIFTS)
                                                                 .build());
        boolean isActive = checker.check(skiPass);

        assertThat(isActive, is(true));

        boolean isActive2 = checker.check(skiPass);

        assertThat(isActive2, is(false));
    }

}