// NOTE (2014-02-07, Marc): 
//   * Leave as -SNAPSHOT version, you can continually publish to that
//   * Bump to non-SNAPSHOT before a release
//   * After successful release, `git tag v0.1.0; git push --tags` to reflect released code
//   * Then bump this to next -SNAPSHOT version
version := "0.1.1-SNAPSHOT"

// Uncomment to publish only to TIM Group Repo 
//publishTo := Some("TIM Group Repo" at "http://repo.youdevise.com:8081/nexus/content/repositories/yd-release-candidates")
//
//credentials += Credentials(new File("/etc/sbt/credentials"))

publishMavenStyle := true

publishTo <<= version { v: String =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/youdevise/narrative-scala</url>
  <licenses>
    <license>
      <name>MIT license</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:youdevise/narrative-scala.git</url>
    <connection>scm:git:git@github.com:youdevise/narrative-scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>ShafeenTejani</id>
      <name>Shafeen Tejani</name>
      <email>shafeen.tejani@timgroup.com</email>
    </developer>
  </developers>
)

// NOTE (2013-04-23, Marc): To publish to Sonatype:
//
// 1. Install sbt-extras as ~/bin/sbt-extras.sh, to handle multiple versions of sbt gracefully
//      https://github.com/paulp/sbt-extras
//
// 2. Generate and publish your GPG key
//      https://docs.sonatype.org/display/Repository/How+To+Generate+PGP+Signatures+With+Maven
//
// 3. Setup sbt-pgp plugin
//      ~/.sbt/0.12.3/plugins/gpg.sbt:
//          addSbtPlugin("com.typesafe.sbt" % "sbt-pgp" % "0.8")
//
// 4. Setup local Sonatype credentials
//      ~/.sbt/0.12.3/plugins/sonatype.sbt:
//          credentials += Credentials("Sonatype Nexus Repository Manager",
//                                     "oss.sonatype.org",
//                                     USERNAME,
//                                     PASSWORD)
//
// 5. Bump version
//       For snapshot versions: 1.0.0-SNAPSHOT
//       For releases: 1.0.0
//
// 6. Deploy and Stage to Sonatype
//       sbt-extras.sh clean publish-signed            // scalaVersion as configured above
//       sbt-extras.sh ++2.9.2 clean publish-signed    // scalaVersion given on command line
//
// 7. Manually release it to Maven Central
//       https://docs.sonatype.org/display/Repository/Sonatype+OSS+Maven+Repository+Usage+Guide#SonatypeOSSMavenRepositoryUsageGuide-8a.ReleaseIt

// NOTE: If you have multiple GPG keys, and it is signing with the wrong one, you must
// do additional steps:
//
//   2.a. List your GPG keys to identify the one you want to sign with
//          gpg --list-keys
//
//   2.b. Edit ~/.gnupg/gpg.conf to set the default-key to the desired key id
//
//   2.c. Launch gpg-agent in your active terminal
//          eval $(gpg-agent --daemon)
//
//   2.d. Uncomment the following line so that SBT will use the GPG app (and its settings)
//useGpg := true
