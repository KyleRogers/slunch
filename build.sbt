import play.Project._

name := """slunch"""

version := "1.0-SNAPSHOT"

resolvers += "Cloudbees Repository" at "http://repository-saucelabs.forge.cloudbees.com/release/"

checksums in update := Nil

libraryDependencies ++= Seq(
  "org.webjars" %% "webjars-play" % "2.2.0",
  "org.webjars" % "bootstrap" % "2.3.1"
)

//Test Dependencies
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.0" % "test",
  "info.cukes" %% "cucumber-scala" % "1.1.5" % "test",
  "info.cukes" % "cucumber-junit" % "1.1.5" % "test",
  "org.seleniumhq.selenium" % "selenium-java" % "2.40.0" % "test",
  "com.saucelabs.selenium" % "selenium-client-factory" % "2.13" % "test",
  "com.saucelabs.selenium" % "sauce-ondemand-driver" % "2.13" % "test",
  "com.saucelabs.selenium" % "selenium-embedded-rc-driver" % "2.13" % "test"
)

playScalaSettings
