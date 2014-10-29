sbt-android-buildinfo
=============

*I know this because build.sbt knows this.*

Unlike ```sbt-buildinfo```, ```sbt-android-buildinfo``` generates xml android values file from your build definitions.

Add to your project
-------
Put following in your project/plugins.sbt:
```scala
resolvers += Resolver.url("sbt-android-plugin",
                          url("https://raw.github.com/dant3/sbt-android-plugin/repo/"))(Resolver.ivyStylePatterns)

addSbtPlugin("com.github.dant3" % "sbt-android-buildinfo" % "0.1")
```

Then in your build.sbt:
```scala
android.buildinfo.Plugin.androidBuildInfoSettings
```

Add this to your build.sbt if you want to automatically generate build info file on packaging:
```scala
resourceGenerators in Compile <+= android.buildinfo.Plugin.androidBuildInfo
```

Use with ```android-sdk-plugin```
-------
To use together with [android-sdk-plugin](https://github.com/pfn/android-sdk-plugin) you can put following code in your build project:

```scala
import sbt._
import android.Keys._

object Tasks {
    def collectGeneratedBuildInfo = (android.buildinfo.Plugin.androidBuildInfo,
                                     projectLayout in Android).map {
        (buildInfo, layout) => {
            val resTarget = layout.bin / "resources" / "res" / "values"
            for {
                file <- buildInfo
            } IO.copyFile(file, resTarget / file.getName, preserveLastModified = true)
        }
    }
}
```

And then in your build.sbt:

```scala
collectResources in Android := {
    Tasks.collectGeneratedBuildInfo.value
    (collectResources in Android).value
}
```

License
-------

Apache 2.0 License
