#!/usr/bin/env kotlin

/**
 * This script is used to update the Android Studio releases page.
 * At first, it fetches the list of Android Studio updates from the official `updates.xml` file.
 * Parsed list is used to generate the Markdown table.
 * The actual IntelliJ IDEA release version is obtained with the help of the JetBrains Data Services API.
 */
@file:DependsOn("org.jsoup:jsoup:1.14.3")
@file:DependsOn("net.swiftzer.semver:semver:1.1.2")
@file:DependsOn("org.simpleframework:simple-xml:2.7.1")
@file:DependsOn("org.json:json:20211205")

import net.swiftzer.semver.SemVer
import org.jsoup.Jsoup
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.core.Persister
import java.io.BufferedInputStream
import java.io.File
import java.io.FileOutputStream
import java.net.URL
import java.util.zip.ZipFile

val RELEASES_FILE_PATH_MD = "topics/_generated/android_studio_releases.md"
val RELEASES_FILE_PATH_XML = "topics/_generated/android_studio_releases.xml"
val INTELLIJ_RELEASES = "https://www.jetbrains.com/intellij-repository/releases/"
val ANDROID_STUDIO_HOST = "https://developer.android.com"
val CHANNEL_BADGES_LIST = """
  [release]: https://img.shields.io/badge/-release-blue?style=flat-square
  [patch]: https://img.shields.io/badge/-patch-orange?style=flat-square
  [rc]: https://img.shields.io/badge/-rc-red?style=flat-square
  [beta]: https://img.shields.io/badge/-beta-darkred?style=flat-square
  [canary]: https://img.shields.io/badge/-canary-lightgrey?style=flat-square
  [preview]: https://img.shields.io/badge/-preview-darktgrey?style=flat-square
"""

val platformBuildToVersionMapping = INTELLIJ_RELEASES.fetch { content ->
  Jsoup.parse(content, "").select("h2:contains(com.jetbrains.intellij.idea) + table tbody tr").mapNotNull { tr ->
    val (version, build) = tr.select("td:nth-child(odd)").map { SemVer.parse(it.text()) }
    (build to version).takeIf { version.major > 2000 }
  }.toMap().toSortedMap()
}

val frameUrl = "$ANDROID_STUDIO_HOST/studio/archive".fetch { content ->
  Jsoup.parse(content, "").select("devsite-iframe iframe[src]").firstOrNull()?.attr("src")
}.let { "$ANDROID_STUDIO_HOST/$it" }

frameUrl.fetch { content ->
  val contentFile = file(RELEASES_FILE_PATH_XML)
  val current = contentFile.takeIf { it.length() > 0 }?.let {
    Persister().read(Content::class.java, it)
  } ?: Content()
  val nameToBuildMapping = current.items.associate { it.name to it.build }

  Jsoup.parse(content, "").select("section.expandable").run {
    mapIndexed { index, item ->
      val title = item.select("p").firstOrNull()?.text() ?: throw IllegalStateException("No title found")
      val (name, channelRaw, date) = """^([\w ]+ \(?[\d.]+\)? ?(?:(\w+) \d+)?) (\w+ \d+, \d+)$""".toRegex().find(title)?.groupValues?.drop(1)
              ?: emptyList()

      println("# $name")
      println("  ${index + 1}/$size")

      val href = item.select(".downloads a[href$=.zip]").firstOrNull()?.attr("href")
      val version = href?.split('/')?.let { it[it.indexOf("ide-zips") + 1] }
              ?: throw IllegalStateException("No version found for $name")
      val build = nameToBuildMapping[name].takeUnless(String?::isNullOrBlank) ?: run { href.resolveBuild() }
      val platformBuild = build.split('-').last().toLooseVersion()

      val platformVersion = platformBuildToVersionMapping[platformBuild] ?: run {
        platformBuildToVersionMapping.entries.findLast { it.key < platformBuild }?.value
      }
      val channel = channelRaw.takeIf { it.isNotBlank() } ?: "Release"

      println("  version='${version}'")
      println("  build='${build}'")
      println("  platformBuild='${platformBuild}'")
      println("  platformVersion='${platformVersion}'")

      Item(name, build, version, channel, platformBuild.toString(), platformVersion.toString(), date)
    }
  }.let {
    val version = with(current) {
      when (items.hashCode() != it.hashCode()) {
        true -> version + 1
        false -> version
      }
    }
    Content(version, it)
  }.also {
    Persister().write(it, contentFile)
  }.also { (_, items) ->
    ("""
    <chunk id="releases_table">
    """ + items.groupBy { it.version.toLooseVersion().major }.entries.joinToString("\n\n") {
      """
        ## ${it.key}.*

        ${it.value.renderTable()}
      """
    } + """
      $CHANNEL_BADGES_LIST
    </chunk>

    <chunk id="releases_table_short">
      ${items.distinctBy(Item::version).take(5).renderTable()}
      $CHANNEL_BADGES_LIST
    </chunk>
  """).split("\n").joinToString("\n", transform = String::trim).let(file(RELEASES_FILE_PATH_MD)::writeText)
  }
}

fun List<Item>.renderTable() = """
  | Release Name | Channel | Release Date | Version | IntelliJ IDEA Version |
  |--------------|:-------:|--------------|---------|-----------------------|
""" + sortedByDescending { it.version.toLooseVersion() }.joinToString("\n") {
  val name = it.name.removePrefix("Android Studio").trim()
  val channel = it.channel.lowercase().run { "![$this][$this]" }
  val date = it.date
  val version = "**${it.version}** <br/> ${it.build}"
  val platform = "**${it.platformVersion}** <br/> ${it.platformBuild}"

  "| $name | $channel | $date | $version | $platform |"
}

fun <T> String.fetch(block: (String) -> T) = URL(this).openStream().use { inputStream ->
  block(inputStream.readBytes().toString(Charsets.UTF_8))
}

fun <T> String.download(block: (File) -> T) = URL(this).openStream().use { inputStream ->
  BufferedInputStream(inputStream).use { bis ->
    File.createTempFile("android-studio", ".zip").also(File::deleteOnExit).let { tempFile ->
      FileOutputStream(tempFile).use { outputStream ->
        println("  Downloading $this to $tempFile")
        ByteArray(1024).let { data ->
          var count: Int
          while (bis.read(data, 0, data.size).also { count = it } != -1) {
            outputStream.write(data, 0, count)
          }
        }
      }
      block(tempFile)
    }
  }
}

fun String.resolveBuild() = download { file ->
  ZipFile(file).use { zip ->
    zip.getEntry("android-studio/build.txt").let { entry ->
      zip.getInputStream(entry).use { inputStream ->
        inputStream.readBytes().toString(Charsets.UTF_8)
      }.also {
        println("  Resolved build number: $it")
      }
    }
  }.also {
    file.delete()
  }
}

fun String.toLooseVersion() = split('.').map { it.take(4).toInt() }.let {
  val (major, minor, patch) = it + 0
  SemVer(major, minor, patch)
}

fun file(path: String) = File(System.getenv("GITHUB_WORKSPACE") ?: "../../").resolve(path).also(File::createNewFile)

@Root(strict = false)
data class Content(
        @field:Attribute var version: Int = 1,
        @field:ElementList(inline = true, required = false) var items: List<Item> = mutableListOf(),
)

data class Item(
        var name: String = "",
        var build: String = "",
        var version: String = "",
        var channel: String = "",
        var platformBuild: String? = null,
        var platformVersion: String? = null,
        var date: String = "",
)
