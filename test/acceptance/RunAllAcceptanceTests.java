package acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Stefan Penndorf <stefan@cyphoria.net>
 */
@RunWith(Cucumber.class)
@CucumberOptions(format = {"pretty", "html:target/cucumber", "json:target/cucumber"}, features = "test/resources/features", monochrome = false)
public class RunAllAcceptanceTests {
}
