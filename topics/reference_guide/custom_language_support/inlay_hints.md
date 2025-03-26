<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Inlay Hints

<link-summary>Providing additional code information directly in the editor without changing the document content.</link-summary>

<tldr>

**Product Help:** [Inlay Hints](https://www.jetbrains.com/help/idea/inlay-hints.html)

</tldr>

Inlay hints render small pieces of information directly into the editor and give developers additional code insight without disturbing the workflow.
A well-known example is parameter hints that usually display the name of the function parameters as given in its declaration.
They are closely related to [](parameter_info.md) which shows parameter types for all possible overloads of a function but opens as a popup overlaying the code.

Inlay hints are flexible and have a wide range of applications in the IntelliJ Platform.
For instance, the following are well-known examples where inlay hints are used:

- Java uses inlays to display type annotations in Java chained method calls.
- Kotlin uses inlays in range expressions to show, e.g. less-than, or less-than-or-equal signs to let developers know if intervals are inclusive or exclusive.
- In version-controlled projects, the author of the code is shown using inlay hints.

## Implementation

The main characteristic of the inlay is the way it is displayed in the editor:
- **inline** - inlays displayed in the code between code tokens
- **block** - inlays displayed above a code block

Depending on the requirements and target IntelliJ Platform version, there are several extension points to choose from, when implementing inlay hints.

This section describes the available APIs and their use cases.

> To inspect existing Inlays in the IDE, use [UI Inspector](internal_ui_inspector.md#editor).
> Corresponding entries from <ui-path>Settings | Editor | Inlay Hints</ui-path> are also available from [](internal_ui_inspector.md#inspecting-settings).

### Inlay Parameter Hints Provider

Inlay parameter hints are simple string **inline** inlays placed before parameter names in method and function calls.
It is not possible to provide advanced presentation and behavior of inlay parameter hints.

To provide inlay parameter hints, implement
[`InlayParameterHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java)
and register it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.parameterNameHints"/></include> (EP).
The API documentation of `InlayParameterHintsProvider` explains in detail the rationale behind all methods.

**Examples:**
- [`GroovyInlayParameterHintsProvider`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/GroovyInlayParameterHintsProvider.kt) - shows parameter hints in Groovy code
- [`KotlinInlayParameterHintsProvider`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/hints/KotlinInlayParameterHintsProvider.kt) - shows parameter hints in Kotlin code

To suppress inlay parameter hints in specific places, implement
[`ParameterNameHintsSuppressor`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt)
and register it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.parameterNameHintsSuppressor"/></include>.

### Declarative Inlay Hints Provider
<primary-label ref="2023.1"/>

Declarative inlay hints are **inline** textual inlays that can hold expandable list of clickable items.
Please note this API has limited presentation customization possibilities due to its UI-independent design, which allows utilizing it by different frontend technologies (not only in Swing).

To provide declarative inlay hints implement declarative
[`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProvider.kt)
and register it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProvider"/></include>.
See the API documentation for the details.

**Examples:**
- [`JavaImplicitTypeDeclarativeInlayHintsProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/hints/JavaImplicitTypeDeclarativeInlayHintsProvider.kt) - shows inferred type for variables declared with the `var` keyword in Java code when the inferred type may not be clear
- [`JavaMethodChainsDeclarativeInlayProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/hints/JavaMethodChainsDeclarativeInlayProvider.kt) - shows method return types in call chains in Java code

To provide a custom configuration UI, implement
[`InlayHintsCustomSettingsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsCustomSettingsProvider.kt)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProviderCustomSettingsProvider"/></include>.

Preview texts displayed for inlay hint providers under <ui-path>Settings | Editor | Inlay Hints</ui-path> must be located in plugin resources in <path>inlayProviders/\$provider_id\$</path> directory, for example, <path>inlayProviders/java.implicit.types</path>.
The <path>\$provider_id\$</path> value must match the `providerId` attribute of <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.declarativeInlayProvider"/></include>.
The preview file name must be <path>preview.\$ext\$</path>, where <path>\$ext\$</path> is a default extension of a supported file type, for example, <path>preview.java</path>.
Hints displayed in the preview panel are defined with the dedicated markup:

<tabs>
<tab title="2023.2+">

```
/*<# Displayed Hint #>*/
```

</tab>
<tab title="Pre-2023.2">

```
<# Displayed Hint #>
```

</tab>
</tabs>

**Examples**:
- [`inlayProviders/java.implicit.types/preview.java`](%gh-ic%/java/java-impl/resources/inlayProviders/java.implicit.types/preview.java)
- [`inlayProviders/groovy.lambda.parameter.inlay.provider/preview.groovy`](%gh-ic%/plugins/groovy/resources/inlayProviders/groovy.lambda.parameter.inlay.provider/preview.groovy)

### Code Vision Provider
<primary-label ref="2022.1"/>

> This API is still in experimental state and may be changed without preserving backward compatibility.
>
{style="note"}

Code vision provider allows for providing **block** inlay hints for elements like class, method, field, etc.
If there are multiple hints provided for a single element, all will be displayed in the same line to save vertical space.

Code vision hints can be displayed over the element, or on the right, at the end of line.
It is configurable by users in <ui-path>Settings | Editor | Inlay Hints | Code vision</ui-path> by choosing a value in <control>Default position for metrics</control> combo box, or by selecting <control>Position</control> in specific provider entries.

There are three extension points for implementing a code vision provider:
- [`DaemonBoundCodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hints/codeVision/DaemonBoundCodeVisionProvider.kt) registered
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.daemonBoundCodeVisionProvider"/></include>
- [`CodeVisionProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionProvider.kt) registered
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.codeVisionProvider"/></include>
- [`CodeVisionGroupSettingProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/settings/CodeVisionGroupSettingProvider.kt) registered
  in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.config.codeVisionGroupSettingProvider"/></include>

`DaemonBoundCodeVisionProvider` API should be used in cases when code vision entries are related to PSI, so that calculated values are invalidated and recalculated on PSI changes.

`CodeVisionProvider` API should be used for cases when presented information doesn't depend on the PSI.

The `CodeVisionGroupSettingProvider` is necessary for displaying the name and description of the code vision provider in the settings.
The `groupId` must match the value specified in the implementation of the `CodeVisionProvider`; if not specified, it defaults to the `id`.
The `groupName` is the name shown in the code vision group, and the `description` will be visible in the right details panel.

Code vision preview is available under <ui-path>Settings | Editor | Inlay Hints | Code vision</ui-path>.
In case if multiple preview examples in different languages are provided for an existing code vision provider, IDE will choose the most important from its point of view.
For example, IntelliJ IDEA will use Java preview, PhpStorm will use PHP preview, etc.

To provide a preview example for a custom code vision provider:
1. Create a <path>codeVisionProviders/\$model_id\$/preview.\$ext\$</path> file, for example, <path>codeVisionProviders/vcs.code.vision/preview.java</path>.
   The <path>\$modelId\$</path> must match the `groupId` of `CodeVisionGroupSettingProvider` and `modelId` (see 2.).
2. Register an `com.intellij.codeInsight.codeVisionSettingsPreviewLanguage`, which specifies the preview `language` and `modelId`.

**Examples:**
- [`JavaInheritorsCodeVisionProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/JavaInheritorsCodeVisionProvider.kt) - shows number of Java class or method inheritors. Clicking the inlay hint opens the list of inheritors. This provider is `DaemonBoundCodeVisionProvider`.
- [`JavaReferencesCodeVisionProvider`](%gh-ic%/java/java-impl/src/com/intellij/codeInsight/daemon/impl/JavaReferencesCodeVisionProvider.kt) - shows number of usages of Java class or member. Clicking the inlay opens the list of usages or navigates to the usage if only one exists. This provider is `DaemonBoundCodeVisionProvider`.
- [`VcsCodeVisionProvider`](%gh-ic%/platform/vcs-impl/lang/src/com/intellij/codeInsight/hints/VcsCodeVisionProvider.kt) - shows the author of a given element, e.g., class or method, based on VCS information. This provider is `CodeVisionProvider`.

### Inlay Hints Provider

Inlay hints provider allows for implementing both **inline** and **block** inlay hints with custom presentation and behavior.
See the API documentation for the details.

> For implementing **inline** inlay hints in versions 2023.1 and newer, [](#declarative-inlay-hints-provider) is recommended.
>
> For implementing **block** inlay hints in versions 2022.1 and newer, [](#code-vision-provider) is recommended.
>
{title="Deprecation Notice" style="warning"}

To provide inlay hints, implement
[`InlayHintsProvider`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt)
and register it in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.inlayProvider"/></include>.
See the API documentation for the details.

**Examples:**
- [`GroovyLocalVariableTypeHintsInlayProvider`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/types/GroovyLocalVariableTypeHintsInlayProvider.kt) - shows local variable types in Groovy code
- [`MarkdownTableInlayProvider`](%gh-ic%/plugins/markdown/core/src/org/intellij/plugins/markdown/editor/tables/ui/MarkdownTableInlayProvider.kt) - _decorates_ tables in Markdown files.
- For a more complex example, see
  [`KotlinLambdasHintsProvider`](%gh-ic%/plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/hints/KotlinLambdasHintsProvider.kt),
  its parent class and all implementations.

### Further Tips

1. Go to
   <ui-path>Settings | Editor | Inlay Hints</ui-path> ([Product Help](https://www.jetbrains.com/help/idea/inlay-hints.html)) and check out inlays that have already been implemented.
2. To support multiple languages with a single type of inlay hints, see declarative
   [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/declarative/InlayHintsProviderFactory.kt) (2023.1+)
   or
   [`InlayHintsProviderFactory`](%gh-ic%/platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt) (pre-2023.1).
3. For testing inlay hints, see
   [`InlayHintsProviderTestCase`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/utils/inlays/InlayHintsProviderTestCase.kt)
   and [`InlayParameterHintsTest`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/utils/inlays/InlayParameterHintsTest.kt).
