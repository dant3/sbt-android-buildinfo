sbtPlugin := true

name := "sbt-android-buildinfo"

organization := "com.github.dant3"

version := "0.1"

// sbtVersion in Global := "0.13.0"

// scalaVersion in Global := "2.10.2"

scalacOptions := Seq("-unchecked", "-deprecation")

description := "sbt plugin to generate build info xml file"

licenses := Seq(("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html")))
