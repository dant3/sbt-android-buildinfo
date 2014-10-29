sbt-android-buildinfo
=============

*I know this because build.sbt knows this.*

Unlike ```sbt-buildinfo```, ```sbt-android-buildinfo``` generates xml android values file from your build definitions.

Usage
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
