package acceptance.schritte

import cucumber.api.scala.{DE, ScalaDsl}
import acceptance.infrastruktur.Browser
import org.scalatest.Matchers._


/**
 *
 * @author Stefan Penndorf <stefan@cyphoria.net>
 */
class WillkommenSchritte extends ScalaDsl with DE with Browser {

  Wenn("""^ein Besucher die Startseite aufruft$"""){ () =>
    browser.goTo("/")
  }

  Dann("""^wird die Überschrift "([^"]*)" angezeigt$"""){ (titel: String) =>
    browser.find("h1").getText should be (titel)
  }
}
