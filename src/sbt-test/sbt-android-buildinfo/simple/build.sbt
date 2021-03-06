import android.buildinfo.Plugin._

name := "helloworld"

version := "0.1"

scalaVersion := "2.10.2"

androidBuildInfoSettings

resourceGenerators in Compile <+= androidBuildInfo

val year = 2014

val debug = true


androidBuildInfoKeys <<= name.apply{(n) => Seq(
    "name" -> n,
    "year" -> year,
    "debug" -> debug
  )
}

val check = taskKey[Unit]("checks this plugin")

check := {
  val resDir = (resourceManaged in Compile).value
  val file = (resDir / "sbt-android-buildinfo" / "res" / "values" / "android_buildinfo.xml")
  val lines = scala.io.Source.fromFile(file).getLines().toList
  val yearLine = "<integer name=\"year\">" + year + "</integer>"
  val debugLine = "<bool name=\"debug\">" + debug + "</bool>"
  lines match {
    case "<?xml version='1.0' encoding='UTF-8'?>" ::
        "<!-- This file was generated by sbt-android-buildinfo. -->" ::
        "<resources>" ::
        "<string name=\"name\">helloworld</string>" ::
        `yearLine` ::
        `debugLine` ::
        "</resources>" :: Nil =>
    case _ => sys.error("unexpected output: \n" + lines.mkString("\n"))
  }
}
