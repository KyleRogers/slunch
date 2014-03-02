package acceptance;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Stefan Penndorf <stefan@cyphoria.net>
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber"}, features = "test/resources/features", monochrome = false)
public class RunAllAcceptanceTests {
}
