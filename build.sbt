name := """play-tollboothCore"""

version := "1.0-SNAPSHOT"

lazy val GatlingTest = config("gatling") extend Test

lazy val root = (project in file(".")).enablePlugins(PlayJava, GatlingPlugin).configs(GatlingTest)
  .settings(inConfig(GatlingTest)(Defaults.testSettings): _*)
  .settings(
    scalaSource in GatlingTest := baseDirectory.value / "/gatling/simulation"
  )

scalaVersion := "2.11.11"

libraryDependencies += filters
libraryDependencies += javaJpa

libraryDependencies += "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final"
libraryDependencies += "io.dropwizard.metrics" % "metrics-core" % "3.2.1"
libraryDependencies += "com.palominolabs.http" % "url-builder" % "1.1.0"
libraryDependencies += "net.jodah" % "failsafe" % "1.0.3"

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.2" % Test
libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.2.2" % Test

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % "2.4.0"

libraryDependencies ++= Seq(
  javaWs
)

libraryDependencies += "org.springframework" % "spring-context" % "4.3.5.RELEASE"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
resolvers += "mandubian maven bintray" at "https://dl.bintray.com/mandubian/maven"

routesGenerator := InjectedRoutesGenerator



PlayKeys.externalizeResources := false

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))
