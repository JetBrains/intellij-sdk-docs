[//]: # (title: Notable Changes in IntelliJ Platform and Plugins API 2022.*)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

## 2022.3

### IntelliJ Platform 2022.3

Specify language for Intention
: Specify `<language>` in `com.intellij.intentionAction` EP registration to avoid instantiating language-specific intentions in non-relevant places.

"Heavy" Brace Matching
: Alternative extension point to implement [](additional_minor_features.md#brace-matching) in background thread.

Injection Text Formatting
: Delegation of formatting can be controlled using dedicated [extension point](language_injection.md#formatting).

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
: Available as `com.jetbrains.intellij.maven:maven-test-framework` from [](intellij_artifacts.md).
