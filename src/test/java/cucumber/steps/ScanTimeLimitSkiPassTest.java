package cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dmitry.shnurenko.skipass.SkiPass;
import dmitry.shnurenko.skipass.TimeLimitSkiPass;
import dmitry.shnurenko.skipass.type.TimeLimitType;
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
public class ScanTimeLimitSkiPassTest {

    private final System    system    = createSystem();
    private final Turnslite turnslite = createTurnslite();

    private TimeLimitSkiPass skiPass;

    @Given("^create time limit ski pass with type ([^/*]*)$")
    public void shouldCreateSkiPassWithType(TimeLimitType type) throws Throwable {
        skiPass = (TimeLimitSkiPass) system.createSkiPass(type);
    }


    @When("^scan ski pass via turnslite$")
    public void shouldScanSkiPassViaTurnslite() throws Throwable {
        turnslite.scan(skiPass);
    }

    @Then("^system will have one successful passage if ski pass was scanned in active period or one failed passage if ski pass was scanned in not active period with type ([^/*]*)$")
    public void systemShouldHaveOneSuccessPassageOrOneFailDependsOnScanTime(TimeLimitType type) throws Throwable {
        LocalDateTime startActivePeriod = skiPass.getStartActivePeriod();
        LocalDateTime endActivePeriod = skiPass.getEndActivePeriod();

        LocalDateTime currentTime = now();

        boolean isInActivePeriod = currentTime.isAfter(startActivePeriod) && currentTime.isBefore(endActivePeriod);

        checkPassages(isInActivePeriod ? 1 : 0, isInActivePeriod ? 0 : 1, type, isInActivePeriod);
    }

    private void checkPassages(int countSuccessPassages,
                               int countFailPassages,
                               TimeLimitType type,
                               boolean isInActivePeriod) {
        List<SkiPass> successPassages = system.findSuccessPassagesForPeriod(now().minusMinutes(1),
                                                                            now().plusMinutes(1),
                                                                            type);

        List<SkiPass> failPassages = system.findFailPassagesForPeriod(now().minusMinutes(1),
                                                                      now().plusMinutes(1),
                                                                      type);
        assertThat(successPassages.size(), is(equalTo(countSuccessPassages)));
        assertThat(isInActivePeriod ? successPassages.get(0) : failPassages.get(0), is(equalTo(skiPass)));

        assertThat(failPassages.size(), is(equalTo(countFailPassages)));
    }
}
