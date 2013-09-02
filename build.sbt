name := "resume"

resolvers ++= Seq(
"twttr" at "http://maven.twttr.com",
"Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
"Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies ++= Seq(
"com.twitter" % "finagle-core" % "1.2.3",
"com.twitter" % "finatra" % "1.3.7"
)

seq(ScctPlugin.instrumentSettings : _*)
