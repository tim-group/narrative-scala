name := "narrative-scala"

organization := "com.timgroup"

version := "0.1"
  
crossScalaVersions := Seq("2.9.1", "2.9.2", "2.10.3")

libraryDependencies += "org.scalatest" %% "scalatest" % "1.9.1" % "test"

libraryDependencies += "org.mockito" % "mockito-core" % "1.9.5" % "test"

publishTo := Some("TIM Group Repo" at "http://repo.youdevise.com:8081/nexus/content/repositories/yd-release-candidates")

credentials += Credentials(new File("/etc/sbt/credentials"))
