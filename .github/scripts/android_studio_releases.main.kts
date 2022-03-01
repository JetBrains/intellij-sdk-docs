#!/usr/bin/env kotlin

/**
 * This script is used to update the Android Studio releases page.
 * At first, it fetches the list of Android Studio updates from the official `updates.xml` file.
 * Parsed list is used to generate the Markdown table.
 * The actual IntelliJ IDEA release version is obtained with the help of the JetBrains Data Services API.
 */
@file:DependsOn("org.simpleframework:simple-xml:2.7.1")
@file:DependsOn("org.json:json:20211205")

import org.json.JSONObject
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Path
import org.simpleframework.xml.Root
import org.simpleframework.xml.core.Persister
import java.io.File
import java.net.URL

val DATA_SERVICES_RELEASES_URL = "https://data.services.jetbrains.com/products/releases"
val ANDROID_STUDIO_RELEASES_URL = "https://dl.google.com/android/studio/patches/updates.xml"
val RELEASES_FILE_PATH = "topics/_generated/android_studio_releases.md"

fun resolveMarketingRelease(build: String) = URL("$DATA_SERVICES_RELEASES_URL?code=IC&build=$build").openStream().use {
  it.readBytes().toString(Charsets.UTF_8).let { content ->
    (JSONObject(content).getJSONArray("IIC").first() as JSONObject).getString("version")
  }
}

URL(ANDROID_STUDIO_RELEASES_URL).openStream().use { inputStream ->
  inputStream.reader().run {
    Persister().read(ProductsReleases::class.java, readText())
  }.run {
    """
    | Android Studio | Channel | Build Number | IntelliJ IDEA Build Number | IntelliJ IDEA Release |
    |----------------|---------|--------------|----------------------------|-----------------------|

    """.trimIndent() + channels.distinctBy { it.number }.joinToString("\n") {
      val name = it.version.replace('|', '-')
      val channel = it.status
      val number = it.number.split('-').last()
      val ijBuild = it.apiVersion.split('-').last()
      val ijRelease = ijBuild.let(::resolveMarketingRelease)

      "| $name | $channel | $number | $ijBuild | $ijRelease |"
    }
  }.let {
    "<chunk id=\"releases_table\">\n\n$it\n\n</chunk>"
  }.let {
    File(System.getenv("GITHUB_WORKSPACE")).resolve(RELEASES_FILE_PATH).writeText(it)
  }
}

@Root(name = "products", strict = false)
data class ProductsReleases(
        @field:ElementList(name = "channel", inline = true)
        @field:Path("product")
        var channels: List<Channel> = mutableListOf()
)

@Root(strict = false)
data class Channel(
        @field:Attribute
        var status: String = "",

        @field:Path("build")
        @field:Attribute
        var apiVersion: String = "",

        @field:Path("build")
        @field:Attribute
        var number: String = "",

        @field:Path("build")
        @field:Attribute
        var version: String = "",
)
