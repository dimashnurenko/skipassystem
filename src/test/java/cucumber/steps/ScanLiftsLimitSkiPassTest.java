package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.SkiPassCreator;
import dmitry.shnurenko.skipass.type.LiftsLimitType;
import dmitry.shnurenko.system.System;
import dmitry.shnurenko.turnslite.Turnslite;

import java.time.LocalDateTime;
import java.util.List;

import static java.time.LocalDateTime.now;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static util.TestUtils.createSystem;
import static util.TestUtils.createTurnslite;

/**
 * @author Dmitry Shnurenko
 */
public class ScanLiftsLimitSkiPassTest {

    private final Turnslite turnslite = createTurnslite();
    private final System    system    = createSystem();

    private SkiPass skiPass;

    @Given("^create lift limit ski pass with type ([^/*]*)$")
    public void shouldCreateSkiPassWithType(LiftsLimitType type) throws Throwable {
        skiPass = SkiPassCreator.create(type);
    }

    @When("^scan ski pass via turnslite ([^/*]*) times$")
    public void shouldScanSkiPassTimes(int skiPassCountScan) throws Throwable {
        for (int i = 0; i < skiPassCountScan; i++) {
            turnslite.scan(skiPass);

            //System need some time to perform business logic
            Thread.sleep(100);
        }
    }

    @Then("^system will have ([^/*]*) successful passages and ([^/*]*) failed passages with type ([^/*]*)$")
    public void systemShouldHaveSuccessAndFailPassages(int successPassages,
                                                       int failPassages,
                                                       LiftsLimitType type) throws Throwable {
        LocalDateTime startPeriod = now().minusMinutes(3);
        LocalDateTime endPeriod = now().plusMinutes(3);

        List<SkiPass> successPassagesList = system.findSuccessPassagesForPeriod(startPeriod, endPeriod, type);

        assertThat(successPassagesList.size(), is(equalTo(successPassages)));

        List<SkiPass> failPassagesList = system.findFailPassagesForPeriod(startPeriod, endPeriod, type);

        assertThat(failPassagesList.size(), is(equalTo(failPassages)));
    }
}
