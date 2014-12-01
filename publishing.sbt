credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

publishTo <<= version { v: String =>
    val repoType = if (v.trim.endsWith("SNAPSHOT")) "snapshots" else "releases"
    val repository = s"repo/$repoType"
    Some(Resolver.file("repository", file(repository))(Resolver.mavenStylePatterns))
}

pomExtra := <url>https://github.com/dant3/sbt-android-buildinfo</url>
    <licenses>
        <license>
            <name>Apache 2.0</name>
            <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
            <distribution>repo</distribution>
        </license>
    </licenses>
    <scm>
        <url>scm:git:git@github.com:dant3/sbt-android-buildinfo.git</url>
        <connection>scm:git:git@github.com:dant3/sbt-android-buildinfo.git</connection>
    </scm>
    <developers>
        <developer>
            <id>dant3</id>
            <name>Viacheslav Blinov</name>
            <url>http://github.com/dant3</url>
        </developer>
    </developers>
