# Notable Changes in IntelliJ Platform and Plugins API 2023.*

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>List of known Notable API Changes in 2023.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).


<include from="tools_gradle_intellij_plugin.md" element-id="gradle_plugin_223_problem"/>

## 2023.3

### IntelliJ Platform 2023.3

External Annotators in Dumb Mode
: [](syntax_highlighting_and_error_highlighting.md#external-annotator) can now run in during indexing.

## 2023.2

### IntelliJ Platform 2023.2

Language Server Protocol (LSP) API
: Please see this [blogpost](https://jb.gg/lsp).

Check presence of JVM library
: Use [dedicated API](psi_cookbook.md#how-do-i-check-the-presence-of-a-jvm-library) to check presence via class FQN or Maven coordinates.

Inspection description: code snippets highlighting
: Embedded code is shown with [syntax highlighting](code_inspections.md#code-snippets).

Intentions that can not show meaningful before/after preview
: Intentions not modifying code can specify `<skipBeforeAfter>true</skipBeforeAfter>` in their registration.

JCEF-based SVG viewer
: SVG pictures are now shown using [JCEF](jcef.md) instead of rendering via Apache Batik library ([Details](https://youtrack.jetbrains.com/issue/IDEA-230850)).

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
: Accessing index data in [nested calls](file_based_indexes.md#nested-index-access) is now possible.

File Type Index Topic
: [`FileTypeIndex.IndexChangeListener`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FileTypeIndex.java) allows monitoring addition/removal of files by `FileType`.

Run Annotator During Indexing
: [Annotators](syntax_highlighting_and_error_highlighting.md#annotator) can implement `DumbAware` to run during indexing (e.g., providing additional syntax highlighting).

Obsolete API Status
: Newly introduced `ApiStatus.@Obsolete` marks API that should not be used for new code, see [](verifying_plugin_compatibility.md#obsolete-api).
