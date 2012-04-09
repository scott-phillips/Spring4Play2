import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "AnnotationConfigExample"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
      "play" % "spring_2.9.1" % "1.0-SNAPSHOT"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
        resolvers += "TAMU Snapshot Repository" at "https://maven.library.tamu.edu/content/repositories/snapshots/"
    )

}
