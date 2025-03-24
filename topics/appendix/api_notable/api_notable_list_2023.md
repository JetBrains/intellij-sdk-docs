<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2023.*

<link-summary>List of known Notable API Changes in 2023.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2023.3

### IntelliJ Platform 2023.3

Threading Model changes
: Please see updated [](threading_model.md).

External Annotators in Dumb Mode
: [](syntax_highlighting_and_error_highlighting.md#external-annotator) can now run in during indexing.

Local inspections performance
: Custom language plugins with many inspections should consider registering a default visitor to improve processing, see [](code_inspections_and_intentions.md#inspections-performance).

### IntelliJ IDEA 2023.3

Unbundled plugins
: Several plugins (Android, Ant, GlassFish, Plugin DevKit) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2023.2

### IntelliJ Platform 2023.2

Language Server Protocol (LSP) API
: Provide custom language support by using language servers, see [](language_server_protocol.md).

Check presence of JVM library
: Use [dedicated API](psi_cookbook.md#how-do-i-check-the-presence-of-a-jvm-library) to check presence via class FQN or Maven coordinates.

Inspection description: code snippets highlighting
: Embedded code is shown with [syntax highlighting](code_inspections.md#code-snippets).

Intentions that cannot show meaningful before/after preview
: Intentions not modifying code can specify `<skipBeforeAfter>true</skipBeforeAfter>` in their registration.

JCEF-based SVG viewer
: SVG pictures are now shown using [JCEF](embedded_browser_jcef.md) instead of rendering via the Apache Batik library ([Details](https://youtrack.jetbrains.com/issue/IDEA-230850)).

### IntelliJ IDEA 2023.2

Unbundled plugins
: Several plugins (Play 1, Resin, Struts 2, tcServer) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2023.1

API for quick documentation
: [](documentation.md) for custom languages is provided through the
[`DocumentationTarget`](%gh-ic%/platform/lang-impl/src/com/intellij/platform/backend/documentation/DocumentationTarget.kt)
API for versions 2023.1 or later.

### IntelliJ Platform 2023.1

Declarative Inspection Options
: [Code inspections](code_inspections.md) can provide additional options in a [declarative](inspection_options.md#declarative-inspection-options) way which has several benefits over the [UI-based](inspection_options.md#ui-based-inspection-options) approach.

Nested Index Access
: ~~Accessing index data in [nested calls](file_based_indexes.md#nested-index-access) is now possible.~~
**NOTE: Please do not use yet** This is known to cause problems under certain conditions, please watch this [issue](https://youtrack.jetbrains.com/issue/IJPL-265/Nested-index-lookups-still-leads-to-deadlocks).

File Type Index Topic
: [`FileTypeIndex.IndexChangeListener`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) allows monitoring the addition/removal of files by `FileType`.

Run Annotator During Indexing
: [Annotators](syntax_highlighting_and_error_highlighting.md#annotator) can implement `DumbAware` to run during indexing (e.g., providing additional syntax highlighting).

Obsolete API Status
: Newly introduced `@ApiStatus.Obsolete` marks API that should not be used for new code, see [](verifying_plugin_compatibility.md#obsolete-api).
