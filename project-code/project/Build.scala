import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "Spring4Play2"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
            "org.springframework"    %    "spring-context"    %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-core"       %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-beans"      %    "3.0.7.RELEASE"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
      // Add your own project settings here      
    )

}
