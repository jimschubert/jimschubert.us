resolvers += Classpaths.typesafeResolver

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

addSbtPlugin("com.sqality.scct" % "sbt-scct" % "0.2.2")
