package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.system.System;
import dmitry.shnurenko.turnslite.Turnslite;

import java.util.List;

import static dmitry.shnurenko.skipass.type.LiftsLimitType.TEN_LIFTS;
import static java.time.LocalDateTime.now;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static util.TestUtils.createSystem;
import static util.TestUtils.createTurnslite;

/**
 * @author Dmitry Shnurenko
 */
public class SystemSuccessPassagesForPeriodTest {

    private final System    system    = createSystem();
    private final Turnslite turnslite = createTurnslite();

    private SkiPass skiPass;

    @Given("^create ski pass$")
    public void shouldCreateSkiPass() throws Throwable {
        skiPass = system.createSkiPass(TEN_LIFTS);
    }

    @When("^scan ski passes ([^/*]*) times during period$")
    public void shouldScanSkiPassesDuringPeriod(int scanTimes) throws Throwable {
        for (int i = 0; i < scanTimes; i++) {
            turnslite.scan(skiPass);

            Thread.sleep(50);
        }
    }

    @Then("^get success passages from system. The count of passages must be ([^/*]*)$")
    public void systemShouldReturnSuccessPassages(int successPassagesCount) throws Throwable {
        List<SkiPass> successPassages = system.findSuccessPassagesForPeriod(now().minusMinutes(2),
                                                                            now().plusMinutes(2),
                                                                            TEN_LIFTS);

        assertThat(successPassages.size(), is(equalTo(successPassagesCount)));
    }
}
