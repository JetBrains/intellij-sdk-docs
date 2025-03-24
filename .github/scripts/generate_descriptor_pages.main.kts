#!/usr/bin/env kotlin

// Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.

/**
 * Generates the elements content for pages defined in [descriptors].
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

val renderedElementPaths = mutableListOf<String>()

val descriptors = listOf(
  DescriptorInfo("https://jb.gg/sdk-docs/plugin-descriptor.yaml", "topics/basics/plugin_structure/plugin_configuration_file.md"),
  DescriptorInfo("https://jb.gg/sdk-docs/templates-descriptor.yaml", "topics/tutorials/live_templates/live_templates_configuration_file.md")
)

descriptors.forEach { descriptor ->
  processDescriptor(descriptor)
}

fun processDescriptor(descriptor: DescriptorInfo) {
  val content = URL(descriptor.dataUrl).readText()
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
    ?: throw RuntimeException("Failed to parse ${descriptor.dataUrl}")

  val newFileContent = StringBuilder()
  val patternToInsertAfter = "[//]: # (GENERATED CONTENT START)"
  val patternToSkipUntil = "[//]: # (GENERATED CONTENT END)"

  file(descriptor.filePath).useLines { lines ->
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

  file(descriptor.filePath).writeText(newFileContent.toString())
}

fun renderContent(content: DocumentationContent): String {
  val sb = StringBuilder()
  sb.appendLine()
  sb.appendLine("[//]: # (This content is generated by generate_descriptor_pages.main.kts script.)")
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
  val includedElements = elements
    .filter { it.isIncluded() }
  for (element in includedElements) {
    if (element.deprecatedSince != null) continue
    val elementSectionLink = element.getPath(parentPath)
    appendHierarchyLink(element, level, elementSectionLink)
    appendElementsHierarchy(element.children.unwrap(), level + 1, elementSectionLink)
  }
}

fun StringBuilder.appendHierarchyLink(element: Element, level: Int, elementSectionLink: String) {
  val elementName = if (element.isWildcard()) (element.descriptiveName ?: "*") else "`<${element.name}>`"
  val internalLabelOrEmpty = if (element.getOwnOrParentInternalNote() != null) "  ![Internal][internal]" else ""
  val deprecationLabelOrEmpty = if (element.deprecatedSince != null) "  ![Deprecated][deprecated]" else ""
  appendLine(
    """${"  ".repeat(level)}- [$elementName](#$elementSectionLink)$internalLabelOrEmpty$deprecationLabelOrEmpty"""
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
  val includedElements = elements.filter { it.isIncluded() }
  val regularElements = includedElements.filter { it.deprecatedSince == null }
  val deprecatedElements = includedElements.filter { it.deprecatedSince != null }
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
  appendInternalNote(element)
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
  val title = if (element.isWildcard()) (element.descriptiveName ?: "*") else "`${element.name}`"
  appendLine("\n#${"#".repeat(level)} $title")
  val attributes = StringBuilder()
  attributes.append("{")
  attributes.append("#$elementSectionLink")
  if (addDeprecationLabel) {
    attributes.append(" collapsible=\"true\" initial-collapse-state=\"collapsed\"")
  }
  attributes.appendLine("}")
  appendLine(attributes.toString())
  if (addDeprecationLabel && element.deprecatedSince != null) {
    appendLine("<primary-label ref=\"Deprecated\"/>\n")
  } else if (element.since != null) {
    appendLine("<primary-label ref=\"${element.since}\"/>\n")
  }
}

fun StringBuilder.appendInternalNote(item: DocumentationItem) {
  val internalNote = item.getOwnOrParentInternalNote() ?: return
  appendWarningNote(internalNote)
}

fun StringBuilder.appendDeprecationNote(element: Element) {
  val deprecationNote = element.deprecationNote ?: return
  appendWarningNote(deprecationNote)
}

fun StringBuilder.appendWarningNote(text: String) {
  val warning = text.lines().filter { it.isNotEmpty() }.joinToString(separator = "\n") { "> $it" }
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
  appendLine("{type=\"narrow\"}")
  appendLine("Deprecated")
  appendLine(":")
  appendLine("since $deprecatedSince\n")
}

fun StringBuilder.appendSupportDetails(supportDetails: String?) {
  supportDetails ?: return
  appendLine("{type=\"narrow\"}")
  appendLine("Supported")
  appendLine(":")
  appendLine(supportDetails)
}

fun StringBuilder.appendRequirement(requirement: Requirement?) {
  if (requirement == null) return
  appendLine("{type=\"narrow\"}")
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
  val includedAttributes = attributeWrappers
    .mapNotNull { it.attribute }
    .filter { it.isIncluded() }
  if (includedAttributes.isNotEmpty()) {
    appendLine("\n\n{type=\"narrow\"}")
    appendLine("Attributes")
    appendLine(":")
    for ((index, attribute) in includedAttributes.withIndex()) {
      appendAttribute(attribute, index == includedAttributes.lastIndex)
    }
  }
}

fun StringBuilder.appendAttribute(
  attribute: Attribute,
  /*ugly hack to not render vertical space between paragraphs in list items (see WRS-1830)*/ isLast: Boolean
) {
  append("- `${attribute.name}`")
  appendAttributeRequirementAndAvailability(attribute)
  appendAttributeInternalNote(attribute)
  appendAttributeDeprecationInfo(attribute)
  if (isLast) {
    append("\n")
  }
  val level = if (isLast) 2 else 1
  attribute.description?.trim()?.let { append(it.indentLines(level)) }
  attribute.defaultValue?.trim()?.let {
    appendLine("<br/>")
    append("Default value: $it.".indentLines(level))
  }
  appendLine()
}

fun StringBuilder.appendAttributeRequirementAndAvailability(attribute: Attribute) {
  val requirement = attribute.requirement
  val since = attribute.since
  val until = attribute.until
  if (requirement == null && since == null && until == null) {
    appendLine()
  } else {
    val requiredText = when (requirement?.required) {
      Required.YES -> "**required**"
      Required.NO -> "optional"
      Required.YES_FOR_PAID -> "required for paid or freemium plugins"
      else -> ""
    }
    val availabilityText = when {
      since != null && until != null -> "available since $since, until $until"
      since != null -> "available since $since"
      until != null -> "available until $until"
      else -> ""
    }
    val requirementDetails = requirement?.details ?: emptyList()
    val content = (listOf(requiredText, availabilityText) + requirementDetails.map { it.trim() })
      .filter { it.isNotEmpty() }
    append(" _(")
    append(content.joinToString(separator = "; "))
    appendLine(")_<br/>")
  }
}

fun StringBuilder.appendAttributeInternalNote(attribute: Attribute) {
  val internalNote = attribute.getOwnOrParentInternalNote() ?: return
  append("**Internal Use Only:** ".indentLines(2))
  val noteWithoutLineBreaks = internalNote.replace('\n', ' ').trimEnd()
  append(noteWithoutLineBreaks)
  appendLine()
}

fun StringBuilder.appendAttributeDeprecationInfo(attribute: Attribute) {
  val deprecatedSince = attribute.deprecatedSince
  val deprecationNote = attribute.deprecationNote
  if (deprecatedSince != null || deprecationNote != null) {
    append(
      (if (deprecatedSince != null) "**_Deprecated since ${deprecatedSince}_**" else "**_Deprecated_**").indentLines(2)
    )
    if (deprecationNote != null) {
      val noteWithoutLineBreaks = deprecationNote.replace('\n', ' ').trimEnd()
      append(": $noteWithoutLineBreaks")
    }
    appendLine()
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
    val children = elements
      .filter { it.deprecatedSince == null }
      .filter { it.isIncluded() }
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
    .fixWildcardLinks()
    .removeInternalLinks()
    .removeAttributeLinks()
    .removeDocProviderSpecificAttributes()
    .internalizeLinks()
}

fun String.cleanupElementLinks(): String {
  // [`some-element`](#element:path__to__some-element) -> [`some-element`](#path__to__some-element)
  return replace("](#element:", "](#")
}

fun String.fixWildcardLinks(): String {
  // reason: Writerside can't handle links with * and the error page is displayed
  // [wildcard](path__to__*) -> [wildcard](#path__to__-)
  return replace("__*", "__-")
}

fun String.removeInternalLinks(): String {
  val internalLinkRegex = Regex("\\[([^]]*)]\\(([^ )]*)\\)\\{internal}")
  return internalLinkRegex.replace(this) { matchResult ->
    val text = matchResult.groupValues[1]
    text
  }
}

fun String.removeAttributeLinks(): String {
  val attributeLinkRegex = Regex("\\[([^\\[\\]]*?)]\\(#attribute:.*?\\)")
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

fun DocumentationItem.isIncluded(): Boolean {
  return this.shouldBeRenderedIn(RenderContext.SDK_DOCS)
}

class DescriptorInfo(val dataUrl: String, val filePath: String)

// ============
// content data classes (synchronize this with org.jetbrains.idea.devkit.documentation.DocumentationContent):

data class DocumentationContent(
  var elements: List<ElementWrapper> = emptyList()
)


// allows for referencing elements by anchors in YAML
data class ElementWrapper(
  var element: Element? = null
)

interface DocumentationItem {
  val parent: DocumentationItem?
  val renderContexts: List<RenderContext>
  val internalNote: String?

  fun shouldBeRenderedIn(context: RenderContext): Boolean {
    generateSequence(this) { it.parent }.toList().reversed().forEach {
      if (!it.renderContexts.contains(context)) {
        return false
      }
    }
    return true
  }

  fun getOwnOrParentInternalNote(): String? {
    return internalNote ?: parent?.getOwnOrParentInternalNote()
  }
}

data class Element(
  var name: String? = null,
  var descriptiveName: String? = null,
  var sdkDocsFixedPath: List<String> = emptyList(),
  var since: String? = null,
  var until: String? = null,
  var deprecatedSince: String? = null,
  var deprecationNote: String? = null,
  var description: String? = null,
  override var internalNote: String? = null,
  var sdkDocsSupportDetails: String? = null,
  var attributes: List<AttributeWrapper> = emptyList(),
  var containsItself: Boolean = false,
  var childrenDescription: String? = null,
  var children: List<ElementWrapper> = emptyList(),
  var references: List<String> = emptyList(),
  var requirement: Requirement? = null,
  var defaultValue: String? = null,
  var examples: List<String> = emptyList(),
  var path: List<String> = emptyList(),
  override var parent: DocumentationItem? = null,
  override var renderContexts: List<RenderContext> = RenderContext.values().toList(), // included in all by default
) : DocumentationItem {

  fun isWildcard(): Boolean {
    return name == "*"
  }

  fun copy(): Element {
    return this.copy(attributes = this.attributes.map { it.copy() })
  }

  override fun toString(): String {
    return "Element(name=$name, path=$path)"
  }
}

// allows for referencing attributes by anchors in YAML
data class AttributeWrapper(
  var attribute: Attribute? = null,
) {
  fun copy(): AttributeWrapper {
    return this.copy(attribute = this.attribute?.copy())
  }
}

data class Attribute(
  var name: String? = null,
  var since: String? = null,
  var until: String? = null,
  var deprecatedSince: String? = null,
  var deprecationNote: String? = null,
  var requirement: Requirement? = null,
  var description: String? = null,
  override var internalNote: String? = null,
  var defaultValue: String? = null,
  var path: List<String> = emptyList(),
  override var parent: DocumentationItem? = null,
  override var renderContexts: List<RenderContext> = RenderContext.values().toList(), // included in all by default
) : DocumentationItem

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

enum class RenderContext {
  SDK_DOCS,
  DOC_PROVIDER
}
