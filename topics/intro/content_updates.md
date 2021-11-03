[//]: # (title: Content Updates)
<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page lists notable additions and updates to the SDK documentation and [code samples](https://github.com/JetBrains/intellij-sdk-code-samples).

Follow [JBPlatform](https://twitter.com/JBPlatform/) on Twitter and visit [JetBrains Platform Blog](https://blog.jetbrains.com/platform/) for the latest announcements.

See [GitHub Changelog](https://github.com/JetBrains/intellij-sdk-docs/commits/main) ([RSS](https://github.com/JetBrains/intellij-sdk-docs/commits/main.atom)) for a detailed changelog.

## 2021

### November-21

Language Injection
: Add [Language Injection](language_injection.md) section that shows how the IntelliJ Platform handles different languages within the same source file.


### September-21

IDE Infrastructure
: [IDE Infrastructure](ide_infrastructure.md) handles Logging, Error Reporting, Runtime Information, and how to provide Context Help.

Extension Point Lists: Listeners, Deprecation status
: [Extension Point List](extension_point_list.md) now contains sections listing all provided [Listener](plugin_listeners.md) Topics. See also corresponding Extension Point Lists under _Part VIII - Product Specific_. Also, all deprecated API now has a dedicated tag.

### July-21

Plugin Signing
: [Plugin Signing](plugin_signing.md) describes the plugin signing process, explains how to generate a certificate, configure the Gradle `signPlugin` task, and introduces a standalone CLI tool.

### June-21

Testing FAQ
: [Testing FAQ](testing_faq.md) page lists common issues, useful classes, and techniques for writing and maintaining tests.

Documentation Provider
: Add [Documentation](documentation.md) section with an [accompanying tutorial](documentation_provider.md) that show how to implement a `DocumentationProvider` for custom languages.

### May-21

IDE specific Extension Point Lists
: See _Part VIII - Product Specific_.

New Guide - Explore the IntelliJ Platform API
: Add a new section [Explore the IntelliJ Platform API](explore_api.md) that describes how plugin authors work with the IntelliJ Platform API and what tools they use.

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
: All code samples used in this guide now come with `README`, making it easier to browse them. They can be conveniently accessed via a [separate GitHub repository](https://github.com/JetBrains/intellij-sdk-code-samples).

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

JCEF Support (_Experimental Feature_)
: Allows [embedding](jcef.md) Chromium-based browser in the IDE.

### February-20

All Code Samples converted to Gradle
: [All samples](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples) now use the [recommended solution](gradle_build_system.md) of setting up plugin projects.

### January-20

Custom Language Support Tutorial converted to Gradle
: The [corresponding tutorial](custom_language_support_tutorial.md) and [Testing a Custom Language Plugin](writing_tests_for_plugins.md) have been updated and enhanced as well.

Targeting specific IDEs
: [Part VIII - Product Specific](plugin_compatibility.md) has been expanded massively, now also covering each IDE with its dedicated page.

## 2019

### December-19

Dynamic Plugins support
: Added starting point [Dynamic Plugins](dynamic_plugins.md) for migrating plugins (IntelliJ Platform 2020.1 and later).

Plugin Components migration
: Components being a legacy feature, the [updated page](plugin_components.md) describes migrating them to modern replacement API.

### October-19

Part X - Plugin Repository moved
: All contents have been moved to [JetBrains Marketplace](https://plugins.jetbrains.com/docs/marketplace/about-marketplace.html) documentation.

### July-19

New page: Optimizing Performance
: [How to improve performance](performance.md) working with PSI, indexing, and avoiding UI freezes.

### May-19

New Page: Kotlin UI DSL
: [Describes preferred way](kotlin_ui_dsl.md) of building UI/dialogs for IntelliJ Platform 2019.2 and later.
