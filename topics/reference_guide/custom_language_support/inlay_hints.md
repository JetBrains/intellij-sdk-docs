[//]: # (title: Inlay Hints)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

[Inlay Hints](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inlay-hints)
render small pieces of information directly into the editor and give developers additional code insight without disturbing the workflow.
A well-known example is parameter hints that usually display the name of the function parameters as given in its declaration.
They are closely related to [](parameter_info.md) which shows parameter types for all possible overloads of a function but opens as a popup overlaying the code.

Inlay hints are flexible and have a wide range of applications in the IntelliJ Platform.
For instance, the following are well-known examples where inlay hints are used:

- Java uses inlays to display type annotations in
  [Java chained calls](https://www.jetbrains.com/help/idea/inlay-hints-java.html#method_chains).
- In version-controlled projects, the author of the code is shown using inlay hints.
- Kotlin uses inlays in range expressions to show, e.g. less-than, or less-than-or-equal signs to let
  developers know if intervals are inclusive or exclusive.

The IntelliJ Platform offers two extension points (EP) that plugin developers can implement to create inlay hints:

- The `com.intellij.codeInsight.parameterNameHints` EP is used to provide simple text inlays for, e.g.,
  parameter names in method and function calls.
- The `com.intellij.codeInsight.inlayProvider` EP is used for more general cases where plugin developers
  need extended control or want to implement interactive features for inlay hints.

The main difference between both EPs is that the first one only lets you place string inlays
while the second one allows for the placement of inline and block inlays with customizable representation.

## Implementation

### Simple Text Inlay Hints

Implement
[`InlayParameterHintsProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayParameterHintsProvider.java)
and register it as `com.intellij.codeInsight.parameterNameHints` EP.
The API documentation of `InlayParameterHintsProvider` explains in detail the rationale behind all methods.

Examples can be found in the following IntelliJ Platform plugins:

- [`GroovyInlayParameterHintsProvider`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/GroovyInlayParameterHintsProvider.kt)
  implements inline hints for Groovy methods.
- [`KotlinInlayParameterHintsProvider`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/hints/KotlinInlayParameterHintsProvider.kt)
  implements parameter hints for Kotlin language.

### Advanced Inlay Hints

Implement
[`InlayHintsProvider`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProvider.kt)
and register it as `com.intellij.codeInsight.inlayProvider` EP.
The API documentation of `InlayHintsProvider` explains in detail the rationale behind all methods.

Examples can be found in the following IntelliJ Platform plugins:

- Groovy provides several implementations of this EP that can serve as a reference:
  [`GroovyParameterTypeHintsInlayProvider`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/types/GroovyParameterTypeHintsInlayProvider.kt),
  [`GroovyLocalVariableTypeHintsInlayProvider`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/types/GroovyLocalVariableTypeHintsInlayProvider.kt),
  and [`GroovyImplicitNullArgumentHintProvider`](upsource:///plugins/groovy/src/org/jetbrains/plugins/groovy/codeInsight/hint/GroovyImplicitNullArgumentHintProvider.kt).
- Markdown uses this EP for _decorating_ tables in
  [`MarkdownTableInlayProvider`](upsource:///plugins/markdown/core/src/org/intellij/plugins/markdown/editor/tables/ui/MarkdownTableInlayProvider.kt).
- For a more complex example, see
  [`KotlinLambdasHintsProvider`](upsource:///plugins/kotlin/idea/src/org/jetbrains/kotlin/idea/codeInsight/hints/KotlinLambdasHintsProvider.kt),
  its parent class and all implementations.

### Further Tips

1. Go to
   [Settings | Editor | Inlay Hints](https://www.jetbrains.com/help/idea/inlay-hints.html) and check out inlays that have already been implemented.
   It gives insight into what’s possible.
2. If you want to support multiple languages with a single type of inlay hints, please see
   [`InlayHintsProviderFactory`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/InlayHintsProviderFactory.kt)
3. If you want to suppress inlay hints in specific places, please implement
   [`ParameterNameHintsSuppressor`](upsource:///platform/lang-api/src/com/intellij/codeInsight/hints/ParameterNameHintsSuppressor.kt)
   and register it as `com.intellij.codeInsight.parameterNameHintsSuppressor` EP.
4. For testing inlay hints, see
   [`InlayHintsProviderTestCase`](upsource:///platform/testFramework/src/com/intellij/testFramework/utils/inlays/InlayHintsProviderTestCase.kt)
   and [`InlayParameterHintsTest`](upsource:///platform/testFramework/src/com/intellij/testFramework/utils/inlays/InlayParameterHintsTest.kt).
5. If you need to force inlay hints to update when using
   [`DaemonCodeAnalyzer#restart()`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/daemon/DaemonCodeAnalyzer.java),
   please use
   [`ParameterHintsPassFactory#forceHintsUpdateOnNextPass()`](upsource:///platform/lang-impl/src/com/intellij/codeInsight/hints/ParameterHintsPassFactory.java)
   before you call `restart()`.
   If you want to force an update on a specific editor, note that the method also has an overload that takes an editor instance.
