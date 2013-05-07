resolvers ++= Seq(
    Classpaths.typesafeResolver,
    "Maven Central" at "http://repo1.maven.org/maven2",
    "sbt-idea-repo" at "http://mpeltonen.github.com/maven/",
    "spray repo" at "http://repo.spray.cc",
  Resolver.url("sbt-plugin-releases",
    url("http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns)
)

addSbtPlugin("io.spray" % "sbt-twirl" % "0.6.1")

addSbtPlugin("com.typesafe.sbtscalariform" % "sbtscalariform" % "0.3.1")

libraryDependencies <+= sbtVersion(v => "com.github.siasia" %% "xsbt-web-plugin" % (v + "-0.2.10"))

libraryDependencies <+= sbtVersion(v => "com.mojolly.scalate" %% "xsbt-scalate-generator" % (v + "-0.1.5"))

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.1.0")
