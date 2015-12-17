package cucumber.steps;

import cucumber.api.java.en.And;
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
public class BlockSkiPassTest {

    private final Turnslite turnslite = createTurnslite();
    private final System    system    = createSystem();

    private SkiPass shouldBlock;
    private SkiPass other;

    @Given("^create two ski passes$")
    public void shouldCreateTwoSkiPasses() throws Throwable {
        shouldBlock = system.createSkiPass(TEN_LIFTS);
        other = system.createSkiPass(TEN_LIFTS);
    }

    @And("^system should have two created ski passes$")
    public void systemShouldHaveTwoCreatedSkiPasses() throws Throwable {
        List<SkiPass> createdSkiPasses = system.findCreatedPassesForPeriod(now().minusMinutes(2), now().plusMinutes(2));

        assertThat(createdSkiPasses.size(), is(equalTo(2)));
        assertThat(createdSkiPasses.contains(shouldBlock), is(equalTo(true)));
        assertThat(createdSkiPasses.contains(other), is(equalTo(true)));
    }

    @When("^block one of the created ski passes$")
    public void shouldBlockOneSkiPass() throws Throwable {
        system.blockSkiPass(shouldBlock);
    }

    @Then("^scan blocked ski pass. The passage should be forbidden$")
    public void passageShouldBeForbiddenWhenScanBlockedSkiPass() throws Throwable {
        turnslite.scan(shouldBlock);

        List<SkiPass> failPassages = system.findFailPassagesForPeriod(now().minusMinutes(1),
                                                                      now().plusMinutes(1),
                                                                      TEN_LIFTS);

        assertThat(failPassages.size(), is(equalTo(1)));
        assertThat(failPassages.get(0), is(equalTo(shouldBlock)));
    }

    @Then("^scan not blocked ski pass. The passage should be allowed$")
    public void passageShouldBeAllowedWhenScanNotBlockedSkiPass() throws Throwable {
        turnslite.scan(other);

        List<SkiPass> successPassages = system.findSuccessPassagesForPeriod(now().minusMinutes(1),
                                                                            now().plusMinutes(1),
                                                                            TEN_LIFTS);

        assertThat(successPassages.size(), is(equalTo(1)));
        assertThat(successPassages.get(0), is(equalTo(other)));
    }
}
