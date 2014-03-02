package acceptance;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Stefan Penndorf <stefan@cyphoria.net>
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format = {"pretty", "html:target/cucumber"}, monochrome = false, features = "test/resources/features" , tags = "@current")
public class CurrentAcceptanceTests {
}
