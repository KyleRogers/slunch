package acceptance.infrastruktur

import play.api.test.{Helpers, TestBrowser}
import play.api.test.Helpers._
import play.api.test.TestServer
import play.api.test.FakeApplication
import cucumber.api.scala.ScalaDsl
import org.openqa.selenium.remote.DesiredCapabilities
import com.saucelabs.selenium.client.factory.SeleniumFactory
import scala.util.Properties


trait Browser {

  def browser = TestUmgebung.browser.get

  //def startSeite = browser.createPage(classOf[StartSeite])

}


object TestUmgebung extends ScalaDsl {

  val anwendung = new TestAnwendungInitialisierer().initAnwendung
  var browser: Option[TestBrowser] = None


  Before {
    _ => Unit
      browser = Some(new FirefoxInitialisierer().initBrowser)
  }

  After {
    _ => Unit
      browser.foreach(_.quit)
      browser = None
  }
}

case class Anwendung(url: String)

class FirefoxInitialisierer {

  def initBrowser = new TestBrowser(
    SeleniumFactory.createWebDriver(DesiredCapabilities.firefox()),
    Some(TestUmgebung.anwendung.url))
}

class TestAnwendungInitialisierer {
  private val applicationPort: Int = Helpers.testServerPort

  def initAnwendung = {
    val startUrl = Properties.envOrNone("SELENIUM_URL")

    new Anwendung(startUrl.getOrElse(initLokal()))
  }

  private def initLokal(): String = {
    val application = FakeApplication(additionalConfiguration = inMemoryDatabase())
    val server = TestServer(applicationPort, application)
    server.start()

    Runtime.getRuntime.addShutdownHook(new Thread() {
      override def run() = server.stop()
    })

    "http://localhost:" + applicationPort

  }

}
