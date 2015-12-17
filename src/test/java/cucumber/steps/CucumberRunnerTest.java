package cucumber.steps;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Dmitry Shnurenko
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "json:/target"},
                 features = {"src/test/resources/features"},
                 glue = {"cucumber/steps"})
public class CucumberRunnerTest {
}
