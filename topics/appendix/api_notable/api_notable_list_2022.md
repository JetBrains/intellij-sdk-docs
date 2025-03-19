<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Notable Changes in IntelliJ Platform and Plugins API 2022.*

<link-summary>List of known Notable API Changes in 2022.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

## 2022.3

<include from="snippets.topic" element-id="gradlePluginVersion"/>

### IntelliJ Platform 2022.3

Display of previews for Intentions and Quick Fixes
: Intentions and Quick Fix actions now display a preview for the code changes. Read [this article](code_intentions_preview.md) to learn more about this feature.

Specify language for Intention
: Specify `<language>` in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.intentionAction"/></include> registration to avoid instantiating language-specific intentions in non-relevant places.

"Heavy" Brace Matching
: Alternative extension point to implement [](additional_minor_features.md#brace-matching) in background thread.

Injection Text Formatting
: Delegation of formatting can be controlled using dedicated [extension point](language_injection.md#formatting).

Specifying Threading for Actions
: Implementations of `AnAction` need to override `getActionUpdateThread()` as detailed in [](action_system.md#principal-implementation-overrides).

Threading assertions in tests
: All rules for [threading](threading_model.md) are now checked in tests as well.

Mapping New UI icons
: See [](icons.md#new-ui-icons) on how to provide additional icons.

### IntelliJ IDEA 2022.3

Unbundled plugins
: Several plugins (Haml, Jakarta EE: WebSockets, JBoss Seam, Spring WebSocket, Stylus, Tapestry, Vaadin, ZKM-Unscramble) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2022.2

### IntelliJ Platform 2022.2

Highlighting References
: Highlight references automatically via [](references_and_resolve.md#additional-highlighting).

## 2022.1

### IntelliJ Platform 2022.1

New Project Wizard
: The <control>New Project</control> wizard has been refreshed and some base [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) classes return `false` from the `isAvailable()` method. If your module builder extends a base class and is hidden in the 2022.1 wizard, override the method to return `true`.

External System Test Framework
: Available as `com.jetbrains.intellij.platform:external-system-test-framework` from [](intellij_artifacts.md), see [](external_system_integration.md#testing).

### IntelliJ IDEA 2022.1

Unbundled plugins
: Several plugins (Grails, Jakarta EE: Batch Applications, Jakarta EE: Server Faces (JSF), Jakarta EE: Web Services (JAX-WS), Jetty, Smali Support, Spring Batch, Spring Integration Patterns, Spring Web Services, WebLogic, WebSphere) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

### Maven Plugin 2022.1

Published Maven Test Framework
: Available as `com.jetbrains.intellij.maven:maven-test-framework` from [](intellij_artifacts.md), See also `TestFrameworkType.Plugin.Maven` test-framework available from [](tools_intellij_platform_gradle_plugin_types.md#TestFrameworkType-Plugin).
