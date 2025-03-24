# Notable Changes in IntelliJ Platform and Plugins API 2021.*

<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<link-summary>List of known Notable API Changes in 2021.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

## 2021.3

### IntelliJ Platform 2021.3

External code formatter tools API
: Allows integration of standalone tools like `shfmt`: [](code_formatting.md#external-code-formatter).

Simplified API for `CustomComponentAction`
: Use new method [`CustomComponentAction.updateCustomComponent(Component, Presentation)`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/ex/CustomComponentAction.java) to synchronize given Presentation and component state.

Highlighting API exposes priority range
: Use `getPriorityRange()` in [`AnnotationSession`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/AnnotationSession.java)/[`LocalInspectionToolSession`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/LocalInspectionToolSession.java) to optimize highlighting for coarse-grained files (e.g., `PsiPlainTextFile` files with single node).

## 2021.2

### IntelliJ Platform 2021.2

Unit test mode: non-production `IconManager`
: Now `com.intellij.ui.IconManager.createDeferredIcon()` doesn't use `iconProducer` which might result in "wrong" composite icons and failed assertions. Override `UsefulTestCase.isIconRequired()` returning `true` to restore production icons. Alternatively, invoke `Registry.get("psi.deferIconLoading").setValue(false)` in `setUp()` and `Registry.get("psi.deferIconLoading").resetToDefault()` in `tearDown()`.

Constructor Injection disabled for Extensions
: Please obtain necessary components only when needed (logged as ERROR now).

Language Injection
: Use [`LanguageInjectionContributor`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionContributor.java) (<include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.languageInjectionContributor"/></include>)
and [`LanguageInjectionPerformer`](%gh-ic%/platform/core-api/src/com/intellij/lang/injection/general/LanguageInjectionPerformer.java) (<include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.languageInjectionPerformer"/></include>)
to customize [language injection](language_injection.md#languageinjectioncontributor-and-languageinjectionperformer).

### IntelliJ IDEA 2021.2

Unbundled plugins
: Several plugins (Arquillian, AspectJ, CoffeeScript, Debugger Support for JSP (JSR45), Drools, Guice, Helidon, Java EE: Enterprise Java Beans (EJB), Spring OSGi, Spring Web Flow) have been unbundled. The IDE will suggest installation if the project contains related framework dependency. If your plugin depends on them, users will need to install them from the [JetBrains Marketplace](https://plugins.jetbrains.com).

## 2021.1

### IntelliJ Platform 2021.1

_Add unambiguous imports on the fly_ for custom languages
: Override [`ReferenceImporter#isAddUnambiguousImportsOnTheFlyEnabled()`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/daemon/ReferenceImporter.java) and provide corresponding user setting. Implement `HintAction` with `fixSilently()` and hook it up to highlighting as a quick fix for unresolved reference.

Ability to override encoding per `VirtualFile`
: Implement [`FileEncodingProvider`](%gh-ic%/platform/core-api/src/com/intellij/openapi/vfs/encoding/FileEncodingProvider.java) and register in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileEncodingProvider"/></include>.

[JCEF](embedded_browser_jcef.md): wrapper for `CefBrowser`
: [`JBCefOsrHandlerBrowser`](%gh-ic%/platform/ui.jcef/jcef/JBCefOsrHandlerBrowser.java) forwards to custom `CefRenderHandler`, e.g., for off-screen rendering.

### Java Plugin 2021.1

Testframework: JUnit4 variant for `LightJavaCodeInsightFixtureTestCase`
: Use [`LightJavaCodeInsightFixtureTestCase4`](%gh-ic%/java/testFramework/src/com/intellij/testFramework/fixtures/LightJavaCodeInsightFixtureTestCase4.kt).
