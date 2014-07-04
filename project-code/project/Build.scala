import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val userName        = System.getProperty("user.name")
    val appName         = "spring"
    val appVersion      = "2.2.3"

    val appDependencies = Seq(
            "org.springframework"    %    "spring-context"    %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-core"       %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-beans"      %    "3.0.7.RELEASE"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
        organization := "play",
        //publishMavenStyle := true,
        publishTo := Some(Resolver.ssh("IMIS Maven2 Repository SSH", "ez.imis.intra", "/var/www/maven2/")
                            (Resolver.mavenStylePatterns) as(userName) withPermissions("0664"))
    )
}
