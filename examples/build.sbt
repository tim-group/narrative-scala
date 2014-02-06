name := "narrative-scala-examples"
 
version := "0.1"

crossScalaVersions := Seq("2.9.2", "2.10.3")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "com.timgroup" %% "narrative-scala" % "0.1" % "test"

resolvers := Seq("TIM Group Reposonatype - Sonatype" at "http://repo/nexus/content/repositories/sonatype-releases/")
