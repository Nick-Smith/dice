organization := "nick.smith"

name := "dice"

version := "1-SNAPSHOT"

scalaVersion := "2.9.1"

seq(webSettings :_*)

// Dependency Management

resolvers ++= Seq(
  "Sonatype OSS" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Guardian Github Releases" at "http://guardian.github.com/maven/repo-releases"
)

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % "2.0.4",
  "commons-codec" % "commons-codec" % "1.5",
  "org.slf4j" % "slf4j-api" % "1.6.4",
  "org.slf4j" % "slf4j-jdk14" % "1.6.4"
)

libraryDependencies ++= Seq(
  "javax.servlet" % "servlet-api" % "2.5" % "provided",
  "org.mortbay.jetty" % "jetty" % "6.1.22" % "container"
)

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "1.6.1" % "test",
  "org.scalatra" %% "scalatra-scalatest" % "2.0.2" % "test",
  "org.scalatra" %% "scalatra-specs2" % "2.0.4" % "test"
)

// Twirl
seq(Twirl.settings: _*)

seq(scalariformSettings: _*)

// Compilation

maxErrors := 20

javacOptions ++= Seq("-source", "1.6", "-target", "1.6", "-encoding", "utf8")

scalacOptions ++= Seq("-unchecked", "-optimise", "-deprecation", "-Xcheckinit", "-encoding", "utf8")
