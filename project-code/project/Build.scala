import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "spring"
    val appVersion      = "1.0-SNAPSHOT"

    val appDependencies = Seq(
            "org.springframework"    %    "spring-context"    %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-core"       %    "3.0.7.RELEASE",
            "org.springframework"    %    "spring-beans"      %    "3.0.7.RELEASE"
    )

    val main = PlayProject(appName, appVersion, appDependencies, mainLang = JAVA).settings(
        organization := "play",
        publishMavenStyle := true,
        publishTo <<= (version) { version: String =>
            val nexus = "https://maven.library.tamu.edu/content/repositories/"
            if (version.trim.endsWith("SNAPSHOT")) 
                Some("TAMU Snapshot Repository" at nexus + "snapshots/") 
            else                                   
                Some("TAMU Release Repository"  at nexus + "releases/")
           },
        credentials += Credentials(file(Path.userHome + "/.mavenCredentials"))
    )
}