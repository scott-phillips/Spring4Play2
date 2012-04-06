import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "SpringSampleProject"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "spring4play2" % "spring4play2_2.9.1" % "1.0-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
        resolvers += "Local Play Repository" at "file://Users/aggiescott/Development/play/current/repository/local/"
    )

}
