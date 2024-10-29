#!/usr/bin/env kotlin

/**
 * This script is used to update the Android Studio releases page.
 * At first, it fetches the list of Android Studio updates from an XML file generated on TeamCity.
 * Parsed list is used to generate the Markdown table.
 */
@file:DependsOn("net.swiftzer.semver:semver:1.2.0")
@file:DependsOn("org.simpleframework:simple-xml:2.7.1")

import net.swiftzer.semver.SemVer
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root
import org.simpleframework.xml.core.Persister
import java.io.File
import java.net.URL
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

val RELEASES_LIST = "https://jb.gg/android-studio-releases-list.xml"
val RELEASES_FILE_PATH_MD = "topics/_generated/android_studio_releases.md"
val CHANNEL_BADGES_LIST = """
  [release]: https://img.shields.io/badge/-Release-blue?style=flat-square
  [patch]: https://img.shields.io/badge/-Patch-orange?style=flat-square
  [rc]: https://img.shields.io/badge/-RC-red?style=flat-square
  [beta]: https://img.shields.io/badge/-Beta-darkred?style=flat-square
  [canary]: https://img.shields.io/badge/-Canary-lightgrey?style=flat-square
  [preview]: https://img.shields.io/badge/-Preview-darkgrey?style=flat-square
"""

val content = URL(RELEASES_LIST).readText()
  .run { Persister().read(Content::class.java, this) }
  ?: throw RuntimeException("Failed to parse releases list")

val xml = """

<snippet id="releases_table">
${
  content.items.groupBy { it.version.toLooseVersion().major }.entries.joinToString("\n\n") {
    """
      ## ${it.key}.*
      ${it.value.renderTable()}
    """
  }
}
$CHANNEL_BADGES_LIST
</snippet>

""".split("\n").joinToString("\n", transform = String::trim).let(file(RELEASES_FILE_PATH_MD)::writeText)

fun List<Item>.renderTable() = """
  | Release Name | Channel | Version | IntelliJ IDEA Version | Release Date |
  |--------------|---------|---------|-----------------------|--------------|
""" + sortedByDescending { it.version.toLooseVersion() }.joinToString("\n") {
  val names = it.name.removePrefix("Android Studio").trim().split("|")
  val channel = it.channel.run { "![$this][${this.lowercase()}]" }
  val localDate = LocalDate.parse(it.date, DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.US))
  val date = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.US).format(localDate).replace(" ", "&nbsp;")
  val version = "**${it.version}**<p>${it.build.replaceFirst("AI-", "AI&#8209;")}</p>"
  val platform = "**${it.platformVersion}**<p>${it.platformBuild}</p>"

  val tableName: String = when {
    names.size == 2 -> "<p><b>${names[0]}</b></p><p>${names[1]}</p>"
    else -> "<b>${names[0]}</b>"
  }

  "| $tableName | $channel | $version | $platform | $date |"
}

fun String.toLooseVersion() = split('.').map { it.take(4).toInt() }.let {
  val (major, minor, patch) = it + 0
  SemVer(major, minor, patch)
}

fun file(path: String) = File(System.getenv("GITHUB_WORKSPACE") ?: "../../").resolve(path).also(File::createNewFile)

@Root(strict = false, name = "content")
data class Content(
  @field:Attribute
  var version: Int = 1,

  @field:ElementList(inline = true, entry = "item")
  var items: List<Item> = mutableListOf(),
)

data class Item(
  @field:Element
  var name: String = "",

  @field:Element
  var build: String = "",

  @field:Element
  var version: String = "",

  @field:Element
  var channel: String = "",

  @field:Element
  var platformBuild: String? = null,

  @field:Element
  var platformVersion: String? = null,

  @field:Element
  var date: String = "",

  @field:ElementList(inline = true, entry = "download")
  var downloads: List<Download> = mutableListOf(),
)

data class Download(
  @field:Element
  var link: String = "",

  @field:Element
  var size: String = "",

  @field:Element
  var checksum: String = "",
)
