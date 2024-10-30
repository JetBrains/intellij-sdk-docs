#!/usr/bin/env kotlin

// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

/**
 * Generates the elements content for the plugin_configuration_file.md page.
 * It fetches the elements data from <TODO>.
 */

@file:DependsOn("org.yaml:snakeyaml:2.3")

import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.LoaderOptions
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import org.yaml.snakeyaml.representer.Representer

import java.io.File
import java.net.URL

/*
This script creates a plugin configuration file content and inserts it into plugin_configuration_file.md page.
The proper solution is to create a snippet file in topics/_generated,
but we can't use this approach until WRS-1009 is fixed.
*/

val PLUGIN_DESCRIPTOR_DATA_URL = "https://jb.gg/sdk-docs/plugin-descriptor.yaml"
val PLUGIN_CONFIGURATION_FILE_PATH = "topics/basics/plugin_structure/plugin_configuration_file.md"

val content = URL(PLUGIN_DESCRIPTOR_DATA_URL).readText()
  .run {
    val loaderOptions = LoaderOptions().apply {
      isEnumCaseSensitive = false
    }
    val representer = Representer(DumperOptions()).apply {
      propertyUtils.isSkipMissingProperties = true
    }
    val constructor = Constructor(DocumentationContent::class.java, loaderOptions)
    Yaml(constructor, representer).load<DocumentationContent>(this)
  }
  .takeIf { it?.elements != null }
  ?: throw RuntimeException("Failed to parse $PLUGIN_DESCRIPTOR_DATA_URL")

val renderedElementPaths = mutableListOf<String>()

val newFileContent = StringBuilder()
val patternToInsertAfter = "[//]: # (GENERATED CONTENT START)"
val patternToSkipUntil = "[//]: # (GENERATED CONTENT END)"

// delete it when WRS-6339 is done
val renderedPageNameToTopicNames = mutableMapOf<String, String>().apply {
  // init map once:
  file("topics").walk().forEach { child ->
    if (child.isFile && child.extension == "md" || child.extension == "topic") {
      val renderedPageName = child.name
        .replace("_", "-")
        .replace(".md", ".html")
        .replace(".topic", ".html")
      if (this.containsKey(renderedPageName)) {
        println("WARN: '${child.name.substringBeforeLast('.')}' topic file name is duplicated under 'topics' directory")
      }
      this[renderedPageName] = child.name
    }
  }
}

file(PLUGIN_CONFIGURATION_FILE_PATH).useLines { lines ->
  var insideGeneratedContent = false
  for (line in lines) {
    if (line.trim() == patternToInsertAfter) {
      insideGeneratedContent = true
      newFileContent.appendLine(line)
      newFileContent.append(renderContent(content))
    } else if (line.trim() == patternToSkipUntil) {
      insideGeneratedContent = false
      newFileContent.appendLine(line)
    } else if (!insideGeneratedContent) {
      newFileContent.appendLine(line)
    }
  }
}

file(PLUGIN_CONFIGURATION_FILE_PATH).writeText(newFileContent.toString())

fun renderContent(content: DocumentationContent): String {
  val sb = StringBuilder()
  sb.appendLine()
  sb.appendLine("[//]: # (This content is generated by plugin_configuration_file.main.kts.)")
  sb.appendLine("[//]: # (DO NOT EDIT IT MANUALLY)")
  sb.appendLine()
  sb.appendContentHierarchy(content)
  sb.appendContentElements(content)
  sb.appendLine()
  return sb.toString().cleanup()
}

fun StringBuilder.appendContentHierarchy(content: DocumentationContent) {
  appendElementsHierarchy(content.elements.unwrap(), level = 0, parentPath = "")
}

fun StringBuilder.appendElementsHierarchy(elements: List<Element>, level: Int, parentPath: String) {
  for (element in elements) {
    if (element.deprecatedSince != null) continue
    val elementSectionLink = element.getPath(parentPath)
    appendHierarchyLink(element, level, elementSectionLink)
    appendElementsHierarchy(element.children.unwrap(), level + 1, elementSectionLink)
  }
}

fun StringBuilder.appendHierarchyLink(element: Element, level: Int, elementSectionLink: String) {
  appendLine(
    """${"  ".repeat(level)}- [`<${element.name}>`](#$elementSectionLink)${if (element.deprecatedSince != null) "  ![Deprecated][deprecated]" else ""}"""
  )
}

fun StringBuilder.appendContentElements(content: DocumentationContent) {
  appendElements(content.elements.unwrap(), level = 1, parentPath = "", isUnderDeprecatedParent = false)
}

fun StringBuilder.appendElements(
  elements: List<Element>,
  level: Int,
  parentPath: String,
  isUnderDeprecatedParent: Boolean
) {
  // nested deprecated elements are "regular" to not render deprecation label multiple times
  val regularElements = elements.filter { it.deprecatedSince == null }
  val deprecatedElements = elements.filter { it.deprecatedSince != null }
  for (element in regularElements) {
    appendElement(element, level, parentPath, false, false)
  }
  if (deprecatedElements.isNotEmpty()) {
    for (element in deprecatedElements) {
      appendElement(element, level, parentPath, true, !isUnderDeprecatedParent)
    }
  }
}

fun StringBuilder.appendElement(
  element: Element,
  level: Int,
  parentPath: String,
  isUnderDeprecatedParent: Boolean,
  addDeprecationLabel: Boolean
) {
  val elementSectionLink = element.getPath(parentPath)
  if (renderedElementPaths.contains(elementSectionLink)) return

  appendSectionHeader(element, level, elementSectionLink, addDeprecationLabel)
  appendDeprecationNote(element)
  appendReferences(element.references)
  element.description?.trim()?.let { appendLine("$it\n") }
  appendDeprecationVersion(element.deprecatedSince)
  appendSupportDetails(element.sdkDocsSupportDetails)
  appendRequirement(element.requirement)
  appendDefaultValue(element.defaultValue)
  appendAttributes(element.attributes)
  appendChildren(element, elementSectionLink)
  appendExamples(element.examples)

  renderedElementPaths.add(elementSectionLink)

  appendElements(element.children.unwrap(), level + 1, elementSectionLink, isUnderDeprecatedParent)
}

fun StringBuilder.appendSectionHeader(
  element: Element,
  level: Int,
  elementSectionLink: String,
  addDeprecationLabel: Boolean
) {
  appendLine("\n#${"#".repeat(level)} `${element.name}`")
  val attributes = StringBuilder()
  attributes.append("{")
  attributes.append("#$elementSectionLink")
  if (addDeprecationLabel) {
    attributes.append(" collapsible=\"true\" initial-collapse-state=\"collapsed\"")
  }
  attributes.appendLine("}")
  appendLine(attributes.toString())
  if (addDeprecationLabel && element.deprecatedSince != null) {
    appendLine("\n<primary-label ref=\"Deprecated\"/>\n")
  } else if (element.since != null) {
    appendLine("\n<primary-label ref=\"${element.since}\"/>\n")
  }
}

fun StringBuilder.appendDeprecationNote(element: Element) {
  val note = element.deprecationNote ?: return
  val warning = note.lines().filter { it.isNotEmpty() }.joinToString(separator = "\n") { "> $it" }
  appendLine(warning)
  appendLine(">")
  appendLine("{style=\"warning\"}\n")
}

fun StringBuilder.appendReferences(references: List<String>) {
  if (references.isEmpty()) return
  appendLine("<tldr>\n")
  append("**Reference:** ${references.joinToString()}")
  appendLine("\n</tldr>\n")
}

fun StringBuilder.appendDeprecationVersion(deprecatedSince: String?) {
  deprecatedSince ?: return
  appendLine("{style=\"narrow\"}")
  appendLine("Deprecated")
  appendLine(":")
  appendLine("since $deprecatedSince\n")
}

fun StringBuilder.appendSupportDetails(supportDetails: String?) {
  supportDetails ?: return
  appendLine("{style=\"narrow\"}")
  appendLine("Supported")
  appendLine(":")
  appendLine(supportDetails)
}

fun StringBuilder.appendRequirement(requirement: Requirement?) {
  if (requirement == null) return
  appendLine("{style=\"narrow\"}")
  appendLine("Required")
  append(": ")
  val requiredText = when (requirement.required) {
    Required.YES -> "**yes**"
    Required.NO -> "no"
    Required.YES_FOR_PAID -> "only for paid or freemium plugins"
    Required.UNKNOWN -> null
  }
  if (requiredText != null) {
    append(requiredText)
    if (requirement.details.isNotEmpty()) {
      append("; ")
    }
  }
  for ((index, detail) in requirement.details.withIndex()) {
    append(detail.trim()).appendLine(if (index != requirement.details.lastIndex) "<br/>" else "")
  }
  appendLine()
}

fun StringBuilder.appendDefaultValue(defaultValue: String?) {
  if (defaultValue == null) return
  appendLine("\nDefault value")
  appendLine(": $defaultValue")
}

fun StringBuilder.appendAttributes(attributeWrappers: List<AttributeWrapper>) {
  val attributes = attributeWrappers.mapNotNull { it.attribute }
  if (attributes.isNotEmpty()) {
    appendLine("\n\nAttributes")
    appendLine(":")
    for (attribute in attributes) {
      appendAttribute(attribute)
    }
  }
}

fun StringBuilder.appendAttribute(attribute: Attribute) {
  append("- `${attribute.name}`")
  appendAttributeRequirement(attribute.requirement)
  attribute.description?.trim()?.let { append(it.indentLines(level = 1)) }
  attribute.defaultValue?.trim()?.let {
    appendLine("<br/>")
    append("Default value: $it.".indentLines(level = 1))
  }
  appendLine()
}

fun StringBuilder.appendAttributeRequirement(requirement: Requirement?) {
  if (requirement == null) {
    appendLine()
  } else {
    val requiredText = when (requirement.required) {
      Required.YES -> "**required**"
      Required.NO -> "optional"
      Required.YES_FOR_PAID -> "required for paid or freemium plugins"
      Required.UNKNOWN -> ""
    }
    val content = (listOf(requiredText) + requirement.details.map { it.trim() }).filter { it.isNotEmpty() }
    append(" _(")
    append(content.joinToString(separator = "; "))
    appendLine(")_<br/>")
  }
}

fun StringBuilder.appendChildren(parent: Element, parentPath: String) {
  if (parent.children.isEmpty() && parent.childrenDescription == null) return
  if (parent.childrenDescription != null) {
    appendLine("\nChildren")
    appendLine(":")
    appendLine(parent.childrenDescription)
  } else {
    val elements = if (parent.containsItself) (parent.children.unwrap() + parent) else parent.children.unwrap()
    if (elements.isEmpty()) return
    val children = elements.filter { it.deprecatedSince == null }
    val deprecatedChildren = elements.filter { it.deprecatedSince != null }
    appendLine("\nChildren")
    appendLine(":")
    for (child in children.sortedBy { it.name }) {
      val childPath = if (child == parent) parentPath else child.getPath(parentPath)
      appendHierarchyLink(child, 1, childPath)
    }
    if (deprecatedChildren.isNotEmpty()) {
      for (deprecatedElement in deprecatedChildren.sortedBy { it.name }) {
        appendHierarchyLink(deprecatedElement, 1, deprecatedElement.getPath(parentPath))
      }
    }
  }
}

fun StringBuilder.appendExamples(examples: List<String>?) {
  if (examples == null) return
  if (examples.size == 1) {
    val example = examples.first()
    appendLine("\nExample")
    appendLine(":")
    appendLine(example.trim().indentLines(1))
  } else if (examples.size > 1) {
    appendLine("\nExamples")
    appendLine(":")
    for (example in examples) {
      appendLine("- ${example.trim()}")
    }
  }
}

fun Element.getPath(parentPath: String): String {
  if (sdkDocsFixedPath.isNotEmpty()) {
    return sdkDocsFixedPath.joinToString(separator = "__")
  }
  return if (parentPath == "") name!! else "${parentPath}__${name}"
}

fun String.indentLines(level: Int): String {
  return lines().joinToString(separator = "\n") { "${"  ".repeat(level)}$it" }
}

fun List<ElementWrapper>.unwrap(): List<Element> {
  return mapNotNull { it.element }
}

fun file(path: String): File {
  return File(System.getenv("GITHUB_WORKSPACE") ?: "../../").resolve(path).also(File::createNewFile)
}

fun String.cleanup(): String {
  return this
    .cleanupElementLinks()
    .removeAttributeLinks()
    .removeDocProviderSpecificAttributes()
    .internalizeLinks()
}

fun String.cleanupElementLinks(): String {
  // [`some-element`](#element:path__to__some-element) -> [`some-element`](#path__to__some-element)
  return replace("](#element:", "](#")
}

fun String.removeAttributeLinks(): String {
  val attributeLinkRegex = Regex("\\[(.*?)]\\(#attribute:.*?\\)")
  return attributeLinkRegex.replace(this) { matchResult ->
    matchResult.groupValues[1]
  }
}

fun String.removeDocProviderSpecificAttributes(): String {
  return replace(Regex("\\{fqn=.*?}"), "")
}

// delete it when WRS-6339 is done
fun String.internalizeLinks(): String {
  val markdownLinkRegex = Regex("\\[(.*?)]\\((.*?)\\)")
  return markdownLinkRegex.replace(this) { matchResult ->
    val linkText = matchResult.groupValues[1]
    val originalUrl = matchResult.groupValues[2]
    val internalLink = getInternalLink(originalUrl)
    if (internalLink != null) {
      "[$linkText]($internalLink)"
    } else {
      matchResult.value
    }
  }
}

fun getInternalLink(url: String): String? {
  val sdkDocsBaseUrl = "https://plugins.jetbrains.com/docs/intellij/"
  if (url.startsWith(sdkDocsBaseUrl)) {
    val internalUrl = url.removePrefix(sdkDocsBaseUrl)
    val renderedPageName = internalUrl.substringBefore("#")
    val topicName = renderedPageNameToTopicNames[renderedPageName] ?: return null
    return if (internalUrl.contains('#')) internalUrl.replaceBefore("#", topicName) else topicName
  }
  return null
}

// ============
// content data classes (synchronize this with org.jetbrains.idea.devkit.documentation.DocumentationContent):

data class DocumentationContent(
  var elements: List<ElementWrapper> = emptyList()
)

// allows for referencing attributes by anchors in YAML
data class ElementWrapper(
  var element: Element? = null
)

data class Element(
  var name: String? = null,
  var sdkDocsFixedPath: List<String> = emptyList(),
  var since: String? = null,
  var until: String? = null,
  var deprecatedSince: String? = null,
  var deprecationNote: String? = null,
  var description: String? = null,
  var sdkDocsSupportDetails: String? = null,
  var attributes: List<AttributeWrapper> = emptyList(),
  var containsItself: Boolean = false,
  var childrenDescription: String? = null,
  var children: List<ElementWrapper> = emptyList(),
  var references: List<String> = emptyList(),
  var requirement: Requirement? = null,
  var defaultValue: String? = null,
  var examples: List<String> = emptyList(),
)

// allows for referencing attributes by anchors in YAML
data class AttributeWrapper(
  var attribute: Attribute? = null,
)

data class Attribute(
  var name: String? = null,
  var since: String? = null,
  var until: String? = null,
  var requirement: Requirement? = null,
  var description: String? = null,
  var defaultValue: String? = null,
)

data class Requirement(
  var required: Required = Required.UNKNOWN,
  var details: List<String> = emptyList(),
)

enum class Required {
  YES,
  NO,
  YES_FOR_PAID,
  UNKNOWN
}
