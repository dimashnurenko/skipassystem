package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dmitry.shnurenko.skipass.SkiPass;
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
public class SystemFailPassagesForPeriodTest {
    private final dmitry.shnurenko.system.System system    = createSystem();
    private final Turnslite                      turnslite = createTurnslite();

    private SkiPass skiPass;

    @Given("^create ski pass with type TEN_LIFTS$")
    public void shouldCreateSkiPassWithTypeTEN_LIFTS() throws Throwable {
        skiPass = system.createSkiPass(TEN_LIFTS);
    }

    @When("^ski pass ([^/*]*) times$")
    public void shouldScanSkiPassDefinedCountOfTimes(int scanCount) throws Throwable {
        for (int i = 0; i < scanCount; i++) {
            turnslite.scan(skiPass);
            
            Thread.sleep(50);
        }
    }

    @Then("^get fail passages from system. The count of passages must be ([^/*]*)$")
    public void systemShouldReturnFailPassagesCount(int failPassagesCount) throws Throwable {
        List<SkiPass> failPassages = system.findFailPassagesForPeriod(now().minusMinutes(2),
                                                                      now().plusMinutes(2),
                                                                      TEN_LIFTS);

        assertThat(failPassages.size(), is(equalTo(failPassagesCount)));
    }
}
