package acceptance.infrastruktur

import play.api.test.{Helpers, FakeApplication, TestServer, TestBrowser}
import play.api.test.Helpers._
import play.api.test.TestServer
import play.api.test.FakeApplication
import cucumber.api.scala.ScalaDsl
import org.openqa.selenium.firefox.FirefoxDriver


trait Browser {

  def browser = TestUmgebung.browser.get
  //def startSeite = browser.createPage(classOf[StartSeite])

}


object TestUmgebung extends ScalaDsl {

  val anwendung = new FakeAnwendungInitialisierer().initAnwendung
  var browser: Option[TestBrowser] = None


  Before { _ => Unit
    browser = Some(new LocalFirefoxInitialisierer().initBrowser)
  }

  After { _ => Unit
    browser.foreach(_.quit)
    browser = None
  }
}

case class Anwendung(url: String)

class LocalFirefoxInitialisierer {
  val webDriver: Class[FirefoxDriver] = Helpers.FIREFOX

  def initBrowser = TestBrowser.of(webDriver, Some(TestUmgebung.anwendung.url))
}

class FakeAnwendungInitialisierer {
  val applicationPort: Int = Helpers.testServerPort

  def initAnwendung = {
    val application = FakeApplication(additionalConfiguration = inMemoryDatabase())
    val server = TestServer(applicationPort, application)
    server.start()

    Runtime.getRuntime.addShutdownHook(new Thread(){
      override def run() = server.stop()
    })

    new Anwendung("http://localhost:" + applicationPort)
  }
}
