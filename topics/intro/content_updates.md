[//]: # (title: Content Updates)
<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page lists notable additions and updates to the SDK documentation and [](code_samples.md).

See [GitHub Changelog](https://github.com/JetBrains/intellij-sdk-docs/commits/main) ([RSS](https://github.com/JetBrains/intellij-sdk-docs/commits/main.atom)) for a detailed changelog.

> [Subscribe to Marketplace Developer News](https://jb.gg/mp-updates) to receive news and announcements.
> Also follow [JBPlatform](https://twitter.com/JBPlatform/) on Twitter and visit [JetBrains Platform Blog](https://blog.jetbrains.com/platform/).
>
{type="tip"}

## 2022

### June-22

PHP Type Providers
: Add section [](php_open_api_php_type_providers.md) about type providers describing type inference in PhpStorm and how to implement your own type provider for PHP.

Postfix Completion
: Add [](postfix_completion.md) section explaining how to implement generating or wrapping the existing code into additional constructs without navigating the caret back.

Gradle IntelliJ Plugin
: Add [](tools_gradle_intellij_plugin.md) documentation to _Appendix III — Tools_.

Minor Changes and Additions
:
- Add a small section to [](php_open_api.md#utility-classes) describing `PhpFilePathUtils` utility class.
- Add mention of the way to programmatically open an autocomplete popup to [](code_completion.md).

### May-22

Navigation Bar
: Add [](navbar.md) section with an [accompanying tutorial](structure_aware_navbar.md) showing how to implement a custom navigation bar for a custom language.

Inlay Hints
: Add section [](inlay_hints.md) describing special markers that appear in the editor and provide additional information code, like the names of the parameters that a called method expects.

Minor Changes and Additions
:
- Add a small section to [](editor_components.md) describing convenient `EditorTextField` subclasses.
- Add descriptions for the following EPs to [](additional_minor_features.md): [](additional_minor_features.md#recognizing-complex-multi-block-expressions), [](additional_minor_features.md#breadcrumbs), [](additional_minor_features.md#plain-text-completion), [](additional_minor_features.md#splitting-and-joining-list-constructs), [](additional_minor_features.md#suggesting-rename-and-change-signature-refactorings), [](additional_minor_features.md#reader-mode), [](additional_minor_features.md#background-colors-for-editors-and-project-view), [](additional_minor_features.md#custom-names-and-tooltips-for-editor-tabs).
- Add small section to [](project_view.md#decorating-project-view-nodes) describing how to modify the representation of nodes in the project view.
- Add [](rename_refactoring.md) paragraphs mentioning `RenameInputValidator(Ex)`.

### April-22

Internal API Migration
: As API annotated with `ApiStatus.@Internal` should not be used in plugins, refer to [](api_internal.md) for a list corresponding API replacements and additional information.

### March-22

Code Samples Build Scripts Migrated to Kotlin
: [All samples](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples) now use Kotlin DSL in Gradle build scripts.

Android Studio Releases List
: Add [](android_studio_releases_list.md) section containing a complete list of the Android Studio releases with the relevant IntelliJ IDEA release version specified.

Alternatives to Implementing a Plugin
: Add [](plugin_alternatives.md) section describing alternative approaches to extending IDE functionality without actual plugin development.

### February-22

Parameter Info
: Add [](parameter_info.md) section explaining how information about function parameters can be shown in the editor.

File and Code Templates
: Add [](file_and_code_templates.md) section explaining how to implement functionality that allows generating files and code fragments containing repetitive text and patterns.

### January-22

Highlighting
: Add [](syntax_errors.md) and [](controlling_highlighting.md) sections explaining syntax highlighting basics and filtering highlighting information.

IDE Infrastructure
: Add sections [](ide_infrastructure.md#application-events) and [](ide_infrastructure.md#plugin-management).

## 2021

### December-21

Trusted Project
: Potentially unsafe features must be guarded using [Trusted Project API](trusted_projects.md).

### November-21

Language Injection
: Add [](language_injection.md) section that shows how the IntelliJ Platform handles different languages within the same source file.


### September-21

IDE Infrastructure
: [](ide_infrastructure.md) handles Logging, Error Reporting, Runtime Information, and how to provide Context Help.

Extension Point Lists: Listeners, Deprecation status
: [](extension_point_list.md) now contains sections listing all provided [Listener](plugin_listeners.md) Topics. See also corresponding Extension Point Lists under _Part VIII — Product Specific_. Also, all deprecated API now has a dedicated tag.

### July-21

Plugin Signing
: [](plugin_signing.md) describes the plugin signing process, explains how to generate a certificate, configure the Gradle [`signPlugin`](tools_gradle_intellij_plugin.md#signplugin-task) task, and introduces a standalone CLI tool.

### June-21

Testing FAQ
: [](testing_faq.md) page lists common issues, useful classes, and techniques for writing and maintaining tests.

Documentation Provider
: Add [](documentation.md) section with an [accompanying tutorial](documentation_provider.md) that show how to implement a `DocumentationProvider` for custom languages.

### May-21

IDE specific Extension Point Lists
: See _Part VIII — Product Specific_.

New Guide - Explore the IntelliJ Platform API
: Add a new section [](explore_api.md) that describes how plugin authors work with the IntelliJ Platform API and what tools they use.

### March-21

Element Patterns
: Add a new section about [Element Patterns](element_patterns.md) that are used when implementing [Completion Contributors](completion_contributor.md) or [PSI Reference Contributors](psi_references.md#contributed-references).

Editor - Text Selection
: Add a new section about [Text Selection EPs](text_selection.md) and describe [`ExtendWordSelectionHandler`](upsource:///platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java).

SDK Setup Assistance
: Added a code sample to the SDK tutorial that expands on [assisting in the setup of an SDK](sdk.md#assisting-in-setting-up-an-sdk).

Unified AST
: [Unified Abstract Syntax Tree (UAST)](uast.md) allows providing features that will work across all supported JVM languages (Java, Kotlin, Scala, Groovy).

## 2020

### December-20

IntelliJ Platform Explorer
: Explore usages of [Extension Points](extension_point_list.md) in open-source plugins using [IntelliJ Platform Explorer](https://jb.gg/ipe).

### November-20

Extension Point List
: All EPs [available in IJ Platform and Android](extension_point_list.md) can now be browsed conveniently.

### August-20

README added to Code Samples
: All code samples used in this guide now come with <path>README</path>, making it easier to browse them. They can be conveniently accessed via a [separate GitHub repository](https://github.com/JetBrains/intellij-sdk-code-samples).

### June-20

Dynamic Plugins update
: Added new sections _Code_ and _Troubleshooting_ to [Dynamic Plugins](dynamic_plugins.md).

GitHub IntelliJ Platform Plugin Template
: Create new plugins with a preconfigured project scaffold and CI in [one click](github_template.md).

Disposer & Disposable
: Added [reference](disposers.md) discussing resource cleanup/management.

### May-20

Settings (Preferences)
: Added [guide](settings_guide.md) and [tutorial](settings_tutorial.md) on integrating with IDE Settings dialog.

UI Inspector
: Inspect Swing components and associated data (like `AnAction` for menu item) using [UI Inspector](internal_ui_inspector.md).

### March-20

JCEF Support
: Allows [embedding](jcef.md) Chromium-based browser in the IDE.

### February-20

All Code Samples converted to Gradle
: [All samples](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples) now use the [recommended solution](gradle_build_system.md) of setting up plugin projects.

### January-20

Custom Language Support Tutorial converted to Gradle
: The [corresponding tutorial](custom_language_support_tutorial.md) and [Testing a Custom Language Plugin](writing_tests_for_plugins.md) have been updated and enhanced as well.

Targeting specific IDEs
: [Part VIII — Product Specific](plugin_compatibility.md) has been expanded massively, now also covering each IDE with its dedicated page.

## 2019

### December-19

Dynamic Plugins support
: Added starting point [Dynamic Plugins](dynamic_plugins.md) for migrating plugins (IntelliJ Platform 2020.1 and later).

Plugin Components migration
: Components being a legacy feature, the [updated page](plugin_components.md) describes migrating them to modern replacement API.

### October-19

Part X — Plugin Repository moved
: All contents have been moved to [JetBrains Marketplace Documentation](https://plugins.jetbrains.com/docs/marketplace/).

### July-19

New page: Optimizing Performance
: Optimizing performance when [working with PSI](psi_performance.md), [during indexing](indexing_and_psi_stubs.md#improving-indexing-performance), and [](general_threading_rules.md#avoiding-ui-freezes).

### May-19

New Page: Kotlin UI DSL
: [Describes preferred way](kotlin_ui_dsl.md) of building UI/dialogs for IntelliJ Platform 2019.2 and later.
