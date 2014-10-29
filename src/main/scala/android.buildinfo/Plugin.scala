package android.buildinfo

import sbt._

object Plugin extends sbt.Plugin with AndroidBuildInfo {
  import Keys._

  lazy val androidBuildInfo        = TaskKey[Seq[File]]("android-buildinfo",
    """A task that generates android buildinfo xml values file.
      |The file is generated in res/values directory as one would expect
      |""".stripMargin)
  lazy val androidBuildInfoFileName  = SettingKey[String]("android-buildinfo-filename", "A name for generated xml file")
  lazy val androidBuildInfoKeys    = SettingKey[Seq[AndroidBuildInfo]]("android-buildinfo-keys",
    "Keys that should be written in settings file")



  lazy val androidBuildInfoSettings: Seq[Def.Setting[_]] = Seq(
    androidBuildInfo <<= (resourceManaged in Compile,
        androidBuildInfoFileName, androidBuildInfoKeys, streams) map {
      (dir, fileName, keys, taskStreams) =>
      Seq(Generator(dir / "sbt-android-buildinfo", fileName, keys, taskStreams.cacheDirectory).generate())
    },
    androidBuildInfoKeys      <<= (name, version, scalaVersion, sbtVersion) apply ((n, v, sv, sbtV) => Seq(
                                    "name" -> n,
                                    "version" -> v,
                                    "scalaVersion" -> sv,
                                    "sbtVersion" -> sbtV
                                  )),
    androidBuildInfoFileName   := "android_buildinfo"
  )
}
