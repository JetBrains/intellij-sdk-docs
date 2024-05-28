#!/usr/bin/env kotlin

/**
 * This script is used to update the versions of libraries stored in the v-releases.list releases file.
 */
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.7.0-RC")

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.io.File
import java.net.URL

val FILE_PATH = "v-releases.list"
val UNKNOWN = "unknown"

val releasesList = mapOf(
  "gradle-intellij-plugin-version" to ReleaseInfo(
    type = ReleaseInfo.Type.GitHub,
    url = "https://api.github.com/repos/JetBrains/intellij-platform-gradle-plugin/releases",
    transformer = { list -> list.first { it.startsWith("2.") } }
  ),
  "gradle-intellij-plugin" to ReleaseInfo(
    type = ReleaseInfo.Type.GitHub,
    url = "https://api.github.com/repos/JetBrains/intellij-platform-gradle-plugin/releases",
    transformer = { list -> list.first { it.startsWith("1.") } }
  ),
  "gradle-grammar-kit-plugin-version" to ReleaseInfo(
    type = ReleaseInfo.Type.GitHub,
    url = "https://api.github.com/repos/JetBrains/gradle-grammar-kit-plugin/releases",
  ),
)

val vars = releasesList.mapValues { (key, releaseInfo) ->
  when (releaseInfo.type) {
    ReleaseInfo.Type.GitHub -> run {
      try {
        val content = URL(releaseInfo.url).readText()
        Json.decodeFromString<JsonArray>(content)
          .mapNotNull { it.jsonObject["name"] }
          .map { it.jsonPrimitive.content.removePrefix("v") }
          .run(releaseInfo.transformer)
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

data class ReleaseInfo(
  val type: Type,
  val url: String,
  val transformer: (List<String>) -> String = { it.first() },
) {

  enum class Type { GitHub }
}
