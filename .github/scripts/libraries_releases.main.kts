#!/usr/bin/env kotlin

/**
 * This script is used to update the versions of libraries stored in the v-releases.list releases file.
 */

import java.io.File
import java.net.HttpURLConnection
import java.net.URL

val FILE_PATH = "v-releases.list"
val UNKNOWN = "unknown"

val releasesList = mapOf(
        "gradle-intellij-plugin-version" to ReleaseInfo(
                type = ReleaseType.GitHub,
                url = "https://github.com/JetBrains/intellij-platform-gradle-plugin/releases/latest",
        ),
        "gradle-grammar-kit-plugin-version" to ReleaseInfo(
                type = ReleaseType.GitHub,
                url = "https://github.com/JetBrains/gradle-grammar-kit-plugin/releases/latest",
        ),
)

val vars = releasesList.mapValues { (key, releaseInfo) ->
  when (releaseInfo.type) {
    ReleaseType.GitHub -> run {
      try {
        URL(releaseInfo.url).openConnection().run {
          (this as HttpURLConnection).instanceFollowRedirects = false
          getHeaderField("Location").split('/').last().removePrefix("v")
        }
      } catch (e: Exception) {
        println("Cannot resolve the latest $key version")
        UNKNOWN
      }
    }
  }
}.map { (key, version) ->
  "<var name=\"$key\" value=\"$version\"/>"
}

"""
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE vars
        SYSTEM "https://resources.jetbrains.com/writerside/1.0/vars.dtd">

<vars>
  ${vars.joinToString("\n  ")}
</vars>
""".trimStart().let(file(FILE_PATH)::writeText)



fun file(path: String) = File(System.getenv("GITHUB_WORKSPACE") ?: "../../").resolve(path).also(File::createNewFile)

enum class ReleaseType {
  GitHub
}

data class ReleaseInfo(
        val type: ReleaseType,
        val url: String,
)
