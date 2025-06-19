<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Content Updates

<link-summary>Notable updates and additions to this documentation.</link-summary>

This page lists notable additions and updates to the SDK documentation and [](code_samples.md).

See [GitHub Changelog](https://github.com/JetBrains/intellij-sdk-docs/commits/main) ([RSS](https://github.com/JetBrains/intellij-sdk-docs/commits/main.atom)) for a detailed changelog.

<include from="snippets.topic" element-id="subscribeNews"/>

## 2025

### May
{#may-25}

New Project Wizard
: Add a new section about implementing project wizards: [](new_project_wizard.md).

Minor Changes and Additions
:
- Add [](editors.md#editors-faq) section.
- Extension Points and Listeners are now grouped per plugin, for example, [](intellij_community_plugins_extension_point_list.md#java-plugin).
- Revise and expand [](code_completion.md), [](icons.md), [UI FAQ: Icons](ui_faq.md#icons) sections.
- Add [](explore_api.md#debugger-entry-points) allowing identifying code responsible for invoked actions.

### March
{march-25}

Integration Testing
: Add new section [](integration_tests.md).

Minor Changes and Additions
:
- Document [](action_system.md#action-id-code-insight) added in 2025.1.

### February
{february-25}

Support Channel Update
:
Updated support channels to the new [JetBrains Platform](https://platform.jetbrains.com) community forum.
See the [The JetBrains Platform Gets a New Community Space](https://blog.jetbrains.com/platform/2025/02/the-jetbrains-platform-gets-a-new-community-space/) blog post for more context.

Revamped Threading and Execution Context topics
:
Reorganized [threading](threading_model.md) and [coroutines](kotlin_coroutines.md) topics structure.
Updated [](execution_contexts.topic) with the information about contexts available since 2024.2.

### January
{january-25}

Coroutines on EDT and Locks
: Added a [page](coroutine_edt_and_locks.md) describing the current implicit locking behavior on EDT launched from coroutines and planned changes with migration hints.

## 2024

### December
{december-24}

Live Templates Configuration File
: Added a page describing the structure and elements of [live templates configuration files](live_templates_configuration_file.md).

### August
{august-24}

Workspace Model
: [](workspace_model.md) represents the project's structure and all its elements and replaces the existing [](project_model.md).

Minor Changes and Additions
:
- Updated list of [supported features](language_server_protocol.md#supported-features) for Language Server Protocol (LSP).

### July
{july-24}

Threading Model
:
Revamp the [Threading Model](threading_model.md) page (formerly _General Threading Rules_) and add a new page describing [background processes](background_processes.md), including cancellation and progress tracking.

Coroutines Read Actions
:
Added FAQ section and explanation of [why suspending inside the block is not allowed](coroutine_read_actions.topic#why-can-t-i-suspend-inside-the-block).

### June
{june-24}

Code Formatter
: Rework [](code_formatting.md) page, extending explanations and updating content.


### May
{may-24}

Minor Changes and Additions
:
- How to support [grammar checks](spell_checking.md#grammar-checks) provided by Grazie plugin in custom languages.
- How to provide [code vision provider](inlay_hints.md#code-vision-provider) name and description in the settings.
- How to manage [Web Symbols context](websymbols_context.md) detection.

### April
{april-24}

Plugin Internationalization
: Add [](internationalization.md) and [](providing_translations.md) pages describing IDE and plugin translation possibilities and best practices.

Minor Changes and Additions
:
- How to mark functionality available during indexing via [](indexing_and_psi_stubs.md#DumbAwareAPI).
- Move Extension Point and Listener Lists to the _Resources_ section and split the main _Extension Point and Listener List_ into: _IntelliJ Platform_, _IntelliJ Community Plugins_, and _Android Plugin_.

### March
{#march-24}

Kotlin Coroutines
: Add [](kotlin_coroutines.md) describing how to write asynchronous code in an imperative style.

Minor Changes and Additions
:
- Add [documentation](plugin_extension_points.md#error-handling) on how to handle errors and deprecations in extensions.
- Note changes in how highlighting is now [performed more efficiently](syntax_highlighting_and_error_highlighting.md#order-of-running-highlighting) in 2024.1.

### February
{#february-24}

IntelliJ Platform Gradle Plugin 2.x (Early Access Preview)
: Add [documentation](tools_intellij_platform_gradle_plugin.md) for the next generation of Gradle tooling for plugin development.

## 2023

### December
{#december-23}

JCEF
: Revamp [JCEF (Java Chromium Embedded Framework)](embedded_browser_jcef.md) page.

User Interface FAQ
: Added [](ui_faq.md).

Language Server Protocol (LSP)
: Added [](language_server_protocol.md).

### November
{#november-23}

Minor Changes and Additions
:
- Add information about [executing actions programmatically](action_system.md#executing-actions-programmatically).
- Please see [](tools_gradle_intellij_plugin.md#attaching-sources) on how to set up 2023.2/3 IDEs for Gradle plugin projects.

### October
{#october-23}

Kotlin Persisting State Component
: Add an [example](persisting_state_of_components.md#implementing-the-persistentstatecomponent-interface) of a persistent state component implemented in Kotlin.

Minor Changes and Additions
:
- Add section [](module.md#storing-a-reference-to-a-module).

### September
{#september-23}

Run Configuration Macros
: Add [](run_configurations.md#macros) section describing how to support dynamic expandable values in run configuration settings.

Inlay Hints
: Update [](inlay_hints.md) page with the information about new APIs.

Threading Model
: Update [](threading_model.md) to reflect changes in 2023.3 platform.

Minor Changes and Additions
:
- Add information about ordering [quick fixes](code_inspections.md#quick-fix-implementation) and [intentions actions](code_intentions.md#techniques-used).
- Clarify the information about [declarative inlay hints](inlay_hints.md#declarative-inlay-hints-provider) customization possibilities.

### July
{#july-23}

Run Configurations
: Describe techniques for [simplifying run configuration settings editors](run_configurations.md#simplifying-settings-editors).

### June
{#june-23}

Execution
: Rework [](execution.md), [](run_configurations.md), and [](run_configurations_tutorial.md) pages.

Minor Changes and Additions
:
- Clarify [the syntax highlighting](testing_highlighting.md#syntax-highlighting) test file format and test implementation initial approach.
- Clarify referencing icons by paths and icon holder class constants in [](icons.md).
- Add information about requirements for persistent state components to be included in [the _Settings Sync_ plugin synchronization mechanism](persisting_state_of_components.md#backup-and-sync-plugin).

### April
{#april-23}

Documentation
: Rework [](documentation.md) page and adapt it to the new `DocumentationTargetProvider` framework.

### March
{#march-23}

Web Symbols
: Add [](websymbols.md) documentation, which is a framework that simplifies web technology development
by utilizing the [](symbols.md) API and supporting custom syntaxes.

Open Source Plugins Extension Points
: Added [](oss_plugins_extension_point_list.md) for plugins bundled with [](idea_ultimate.md) and other IDEs.



### February
{#february-23}

Inspection Options
: Add a section on [](inspection_options.md), which allows extending inspection behavior based on the input provided by user at runtime.

Minor Changes and Additions
:
- Add section on [](icons.md#new-ui-icons).
- Document [](spell_checking.md#runtimedictionaryprovider) EP for spellchecking.

### January
{#january-23}

Minor Changes and Additions
:
- Update _IDE Support_ section in [](verifying_plugin_compatibility.md).
- UI Inspector: update [](internal_ui_inspector.md#specific-component-properties) and add section [](internal_ui_inspector.md#inspecting-settings).

## 2022

### December
{#december-22}

Intention Preview
: Add information about how to prepare intentions to show [](code_intentions_preview.md).

Minor Changes and Additions
:
- Add information for new [](kotlin_ui_dsl_version_2.md#cell-align) methods in Kotlin UI DSL Version 2.

### November
{#november-22}

Plugin User Experience
: Add a new section about how to improve [plugin UX](plugin_user_experience.md) and overall plugin quality.

Minor Changes and Additions
:
- Add information about threading in Actions in [](action_system.md#principal-implementation-overrides).

### October
{#october-22}

Minor Changes and Additions
:
- Add information about [sharing settings](persisting_state_of_components.md#sharing-settings-between-ide-installations) between different IDEs installations.

### September
{#september-22}

Extract "Themes" part
:
All the content related to [themes customization](theme_structure.md) and creating a project using the [DevKit approach](developing_themes.md) has been moved to a new [_Themes_](themes_getting_started.md) part.
Content has been refreshed to match the current state of the project and SDK wizards.

Spell Checking
: Add [](spell_checking.md) section with an [accompanying tutorial](spell_checking_strategy.md) showing how to implement a spell checking for a custom language.

Minor Changes and Additions
:
- Add descriptions for the following EPs to [](additional_minor_features.md): [](additional_minor_features.md#prevent-error-highlighting-of-files), [](additional_minor_features.md#provide-fully-qualified-names-fqn-for-elements), [](additional_minor_features.md#label-files-as-test-files), [](additional_minor_features.md#move-statements-up-and-down-in-the-editor).
- Add section about [](ide_infrastructure.md#power-save-mode).
- Highlight references automatically via [](references_and_resolve.md#additional-highlighting)
- Language injections: controlling [](language_injection.md#formatting)

### August
{#august-22}

Plugin Configuration Page
: Update the [](plugin_configuration_file.md) page to describe all the elements in detail.

Source links migrated from Upsource to GitHub
: All source links now point to GitHub instead of Upsource (which is going to be [sunset](https://blog.jetbrains.com/upsource/2022/01/31/upsource-end-of-sales-announcement/)).

### July
{#july-22}

Status Bar Widgets
: Add section [](status_bar_widgets.md) describing how to implement your own status bar widgets.

Minor Changes and Additions
:
- Add overview of [](action_system.md#useful-action-base-classes).

### June
{#june-22}

PHP Type Providers
: Add section [](php_open_api_php_type_providers.md) about type providers describing type inference in PhpStorm and how to implement your own type provider.

Postfix Completion
: Add [](postfix_completion.md) section explaining how to implement generating or wrapping the existing code into additional constructs without navigating the caret back.

Gradle IntelliJ Plugin
: Add [](tools_gradle_intellij_plugin.md) documentation to _Tooling_.

Bundling Plugin API Sources
: Add the [](bundling_plugin_openapi_sources.md) section explaining how to expose plugin API sources to dependent plugin developers.

Minor Changes and Additions
:
- Add a small section to [](php_open_api.md#utility-classes) describing `PhpFilePathUtils` utility class.
- Add mention of the way to programmatically open an autocomplete popup to [](code_completion.md).
- Add a small section to [](icons.md#animated-icons) describing animated icons.
- Moved [](tools_gradle_grammar_kit_plugin.md) documentation to _Tooling_.

### May
{#may-22}

Navigation Bar
: Add [](navbar.md) section with an [accompanying tutorial](structure_aware_navbar.md) showing how to implement a navigation bar for a custom language.

Inlay Hints
: Add section [](inlay_hints.md) describing special markers that appear in the editor and provide additional information, like the names of the parameters that a called method expects.

Minor Changes and Additions
:
- Add a small section to [](editor_components.md) describing convenient `EditorTextField` subclasses.
- Add descriptions for the following EPs to [](additional_minor_features.md):
  [](additional_minor_features.md#recognizing-complex-multi-block-expressions),
  [](additional_minor_features.md#breadcrumbs),
  [](additional_minor_features.md#plain-text-completion),
  [](additional_minor_features.md#splitting-and-joining-list-constructs),
  [](additional_minor_features.md#suggesting-rename-and-change-signature-refactorings),
  [](additional_minor_features.md#reader-mode).
- Add small section to [](project_view.md#decorating-project-view-nodes) describing how to modify the representation of nodes in the project view.
- Add [](rename_refactoring.md) paragraphs mentioning `RenameInputValidator(Ex)`.

### April
{#april-22}

Internal API Migration
: As API annotated with `@ApiStatus.Internal` must not be used in plugins, refer to [](api_internal.md) for a list corresponding API replacements and additional information.

### March
{#march-22}

Code Samples Build Scripts Migrated to Kotlin
: All [](code_samples.md) now use Kotlin DSL in their Gradle build scripts.

Android Studio Releases List
: Add [](android_studio_releases_list.md) section containing a complete list of the Android Studio releases with the relevant IntelliJ IDEA release version specified.

Alternatives to Implementing a Plugin
: Add [](plugin_alternatives.md) section describing alternative approaches to extending IDE functionality without actual plugin development.

### February
{#february-22}

Parameter Info
: Add [](parameter_info.md) section explaining how information about function parameters can be shown in the editor.

File and Code Templates
: Add [](file_and_code_templates.md) section explaining how to implement functionality that allows generating files and code fragments containing repetitive text and patterns.

### January
{#january-22}

Highlighting
: Add [](syntax_errors.md) and [](controlling_highlighting.md) sections explaining syntax highlighting basics and filtering highlighting information.

IDE Infrastructure
: Add sections [](ide_infrastructure.md#application-events) and [](ide_infrastructure.md#plugin-management).

## 2021

### December
{#december-21}

Trusted Project
: Potentially unsafe features must be guarded using [Trusted Project API](trusted_projects.md).

### November
{#november-21}

Language Injection
: Add [](language_injection.md) section that shows how the IntelliJ Platform handles different languages within the same source file.


### September
{#september-21}

IDE Infrastructure
: [](ide_infrastructure.md) handles Logging, Error Reporting, Runtime Information, and how to provide Context Help.

Extension Point Lists: Listeners, Deprecation status
: [](intellij_platform_extension_point_list.md) now contains sections listing all provided [Listener](plugin_listeners.md) Topics. See also corresponding Extension Point Lists under _Product Specific_. Also, all deprecated API now has a dedicated tag.

### July
{#july-21}

Plugin Signing
: [](plugin_signing.md) describes the plugin signing process, explains how to generate a certificate, configure the Gradle [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, and introduces a standalone CLI tool.

### June
{#june-21}

Testing FAQ
: [](testing_faq.md) page lists common issues, useful classes, and techniques for writing and maintaining tests.

Documentation Provider
: Add [](documentation.md) section with an [accompanying tutorial](documentation_provider.md) that show how to implement a `DocumentationProvider` for custom languages.

### May
{#may-21}

IDE specific Extension Point Lists
: See _Product Specific_.

New Guide - Explore the IntelliJ Platform API
: Add a new section [](explore_api.md) that describes how plugin authors work with the IntelliJ Platform API and what tools they use.

### March
{#march-21}

Element Patterns
: Add a new section about [Element Patterns](element_patterns.md) that are used when implementing [Completion Contributors](completion_contributor.md) or [PSI Reference Contributors](psi_references.md#contributed-references).

Editor - Text Selection
: Add a new section about [Text Selection EPs](text_selection.md) and describe [`ExtendWordSelectionHandler`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/editorActions/ExtendWordSelectionHandler.java).

SDK Setup Assistance
: Added a code sample to the SDK tutorial that expands on [assisting in the setup of an SDK](sdk.md#assisting-in-setting-up-an-sdk).

Unified AST
: [Unified Abstract Syntax Tree (UAST)](uast.md) allows providing features that will work across all supported JVM languages (Java, Kotlin, Scala, Groovy).

## 2020

### December
{#december-20}

IntelliJ Platform Explorer
: Explore usages of [Extension Points](intellij_platform_extension_point_list.md) in open-source plugins using [IntelliJ Platform Explorer](https://jb.gg/ipe).

### November
{#november-20}

Extension Point List
: All EPs [available in IJ Platform and Android](intellij_platform_extension_point_list.md) can now be browsed conveniently.

### August
{#august-20}

README added to Code Samples
: All code samples used in this guide now come with <path>README</path>, making it easier to browse them. They can be conveniently accessed via a [separate GitHub repository](%gh-sdk-samples-master%/).

### June
{#june-20}

Dynamic Plugins update
: Added new sections _Code_ and _Troubleshooting_ to [Dynamic Plugins](dynamic_plugins.md).

GitHub IntelliJ Platform Plugin Template
: Create new plugins with a preconfigured project scaffold and CI in [one click](plugin_github_template.md).

Disposer & Disposable
: Added [reference](disposers.md) discussing resource cleanup/management.

### May
{#may-20}

Settings (Preferences)
: Added [guide](settings_guide.md) and [tutorial](settings_tutorial.md) on integrating with IDE Settings dialog.

UI Inspector
: Inspect Swing components and associated data (like `AnAction` for menu item) using [UI Inspector](internal_ui_inspector.md).

### March
{#march-20}

JCEF Support
: Allows [embedding](embedded_browser_jcef.md) Chromium-based browser in the IDE.

### February
{#february-20}

All Code Samples converted to Gradle
: [All samples](https://github.com/JetBrains/intellij-sdk-docs/tree/main/code_samples) now use the [recommended solution](creating_plugin_project.md) of setting up plugin projects.

### January
{#january-20}

Custom Language Support Tutorial converted to Gradle
: The [corresponding tutorial](custom_language_support_tutorial.md) and [Testing a Custom Language Plugin](writing_tests_for_plugins.md) have been updated and enhanced as well.

Targeting specific IDEs
: [Product Specific](plugin_compatibility.md) has been expanded massively, now also covering each IDE with its dedicated page.

## 2019

### December
{#december-19}

Dynamic Plugins support
: Added starting point [Dynamic Plugins](dynamic_plugins.md) for migrating plugins (IntelliJ Platform 2020.1 and later).

Plugin Components migration
: Components being a legacy feature, the [updated page](plugin_components.md) describes migrating them to modern replacement API.

### October
{#october-19}

Plugin Repository moved
: All contents have been moved to [JetBrains Marketplace Documentation](https://plugins.jetbrains.com/docs/marketplace/).

### July
{#july-19}

New page: Optimizing Performance
: Optimizing performance when [working with PSI](psi_performance.md), [during indexing](indexing_and_psi_stubs.md#improving-indexing-performance), and [](threading_model.md#avoiding-ui-freezes).

### May
{#may-19}

New Page: Kotlin UI DSL
: [Describes preferred way](kotlin_ui_dsl.md) of building UI/dialogs for IntelliJ Platform 2019.2 and later.
