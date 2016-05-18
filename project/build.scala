import org.scalatra.sbt._
import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._
import sbtassembly._
import com.typesafe.sbt.SbtScalariform.{ScalariformKeys, scalariformSettings}
import scalariform.formatter.preferences._
import twirl.sbt.TwirlPlugin._

object DiceBuild extends Build {
  val Organization = "nick-smith"
  val Name = "dice"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.5"
  val ScalatraVersion = "2.3.0"

  val jettyVersion = "9.1.5.v20140505"
  val json4sVersion = "3.2.10"
  val servletApiVersion = "3.1.0"
  val scalazVersion = "7.1.1"
  val typesafeConfigVersion = "1.2.1"
  val scalaLoggingVersion = "3.1.0"
  val apacheCommonsVersion = "3.4"

  lazy val project = Project (
    Name,
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalariformSettings ++ Twirl.settings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      //scalacOptions += "-Ylog-classpath",
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.eclipse.jetty" % "jetty-webapp" % jettyVersion % "container;compile",
        "org.eclipse.jetty" % "jetty-plus" % jettyVersion % "container",
        "javax.servlet" % "javax.servlet-api" % servletApiVersion,
        "org.json4s"   %% "json4s-native" % json4sVersion,
        "org.json4s"   %% "json4s-jackson" % json4sVersion,
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.scalaz" %% "scalaz-core" % scalazVersion,
        "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
        "com.typesafe.scala-logging" %% "scala-logging" % scalaLoggingVersion,
        "org.apache.commons" % "commons-lang3" % apacheCommonsVersion
      ),
      assemblyMergeStrategy in assembly := {
        case "version.txt" => MergeStrategy.discard
        case "mime.types" => MergeStrategy.discard
        case x =>
          val oldStrategy = (assemblyMergeStrategy in assembly).value
          oldStrategy(x)
      },
      ScalariformKeys.preferences := ScalariformKeys.preferences.value
        .setPreference(DoubleIndentClassDeclaration, true)
    )
  )
}