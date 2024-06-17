<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2024.*

<link-summary>List of known Notable API Changes in 2024.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.md" element-id="gradlePluginVersion"/>

## 2024.2

### IntelliJ Platform 2024.2

Dumb-aware intentions and quick-fixes
: Mark implementations as enabled during indexing, see [](indexing_and_psi_stubs.md#DumbAwareAPI).

Testing: `ProjectActivity`
: Executed asynchronously now during tests, see [](testing_faq.md#how-to-handle-projectactivity).

Testing: indexes
: Indexes are now built asynchronously during tests, see [](testing_faq.md#how-to-handle-indexing).

## 2024.1

### IntelliJ Platform 2024.1

Bundled Localization
: Initial plugin [localization](providing_translations.md#translated-elements) capabilities.

Using Coroutines
: It is now recommended to use [](kotlin_coroutines.md) for asynchronous code.

Running highlighting
: Highlighting is now performed more efficiently, please refer to [](syntax_highlighting_and_error_highlighting.md#order-of-running-highlighting).

Status bar widget for LSP servers
: Language plugins using LSP can now provide their status for [](language_server_protocol.md#status-bar-integration).

Cached Values and Dumb Mode
: Note change for [](psi_performance.md#projectRootManagerDependency).

Saving Settings on background thread
: Saving project/application [settings](settings.md) is no longer performed on [EDT](general_threading_rules.md) to avoid freezes. [Issue](https://youtrack.jetbrains.com/issue/IJPL-127/Save-project-application-settings-on-background-thread)

### IntelliJ IDEA 2024.1

Unbundled plugins
: Several plugins (Cucumber Groovy, Cucumber Java) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).
