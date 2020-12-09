---
title: Content Updates
---

This page lists notable additions and updates to the SDK documentation and code samples.
       
Follow [JBPlatform](https://twitter.com/JBPlatform/) on Twitter and visit [JetBrains Platform Blog](https://blog.jetbrains.com/platform/) for the latest announcements.

See [Recently Updated](/recently_updated.md) ([RSS](https://github.com/JetBrains/intellij-sdk-docs/commits/master.atom)) for a detailed changelog.

## 2020
                   
### November

Extension Point List
: All EPs [available in IJ Platform and Android](/appendix/resources/extension_point_list.md) can now be browsed conveniently.

### August

README added to Code Samples
: All code samples used in this guide now come with `README` making it easier to browse them. They can be conveniently accessed via a [separate GitHub repository](https://github.com/JetBrains/intellij-sdk-code-samples).

### June-20

Dynamic Plugins update
: Added new sections _Code_ and _Troubleshooting_ to [Dynamic Plugins](/basics/plugin_structure/dynamic_plugins.md).

GitHub IntelliJ Platform Plugin Template
: Create new plugins with a preconfigured project scaffold and CI in [one click](/tutorials/github_template.md).

Disposer & Disposable
: Added [reference](/basics/disposers.md) discussing resource cleanup/management.

### May-20

Settings (Preferences)
: Added [guide](/reference_guide/settings_guide.md) and [tutorial](/tutorials/settings_tutorial.md) on integrating with IDE Settings dialog.

UI Inspector
: Inspect Swing components and associated data (like `AnAction` for menu item) using [UI Inspector](/reference_guide/internal_actions/internal_ui_inspector.md).

### March-20

JCEF Support (_Experimental Feature_)
: Allows [embedding](/reference_guide/jcef.md) Chromium-based browser in the IDE.

### February-20

All Code Samples converted to Gradle
: [All samples](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples) now use the [recommended solution](/tutorials/gradle_build_system.md) of setting up plugin projects.

### January-20

Custom Language Support Tutorial converted to Gradle
: The [corresponding tutorial](/tutorials/custom_language_support_tutorial.md) and [Testing a Custom Language Plugin](/tutorials/writing_tests_for_plugins.md) have been updated and enhanced as well.

Targeting specific IDEs
: [Part VIII - Product Specific](/basics/getting_started/plugin_compatibility.md) has been expanded massively, now also covering each IDE with its own dedicated page.

## 2019

### December-19

Dynamic Plugins support
: Added starting point [Dynamic Plugins](/basics/plugin_structure/dynamic_plugins.md) for migrating plugins (IntelliJ Platform 2020.1 and later).

Plugin Components migration
: Components being a legacy feature, the [updated page](/basics/plugin_structure/plugin_components.md) describes migrating them to modern replacement API.

### October-19

Part X - Plugin Repository moved
: All contents have been moved to [JetBrains Marketplace](https://plugins.jetbrains.com/docs/marketplace/about-marketplace.html) documentation.

### July-19

New page: Optimizing Performance
: [How to improve performance](/reference_guide/performance/performance.md) working with PSI, indexing, and avoiding UI freezes.

### May-19

New Page: Kotlin UI DSL
: [Describes preferred way](/user_interface_components/kotlin_ui_dsl.md) of building UI/dialogs for IntelliJ Platform 2019.2 and later.