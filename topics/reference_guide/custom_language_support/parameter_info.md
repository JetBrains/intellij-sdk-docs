<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Parameter Info

<link-summary>Implementing parameter info handler allows displaying method/function parameter names and types before providing actual values.</link-summary>

<tldr>

**Product Help:** [Parameter info](https://www.jetbrains.com/help/idea/viewing-reference-information.html#view-parameter-info)

</tldr>

Custom languages can use
[`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.codeInsight.parameterInfo"/></include> to show information about parameters in method and function calls.
This is a convenient way to display type signatures directly as a popup in the editor without having to consult the documentation.
If it is available, the IDE can show this popup automatically after a short delay, or it can be invoked explicitly via
<ui-path>View | Parameter Info</ui-path>.

Parameter info is dynamic and can update the displayed information when the caret is moved or additional code is typed.
This allows for highlighting entries or marking the current parameter at the caret position.
Therefore, the interface of the
[`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java)
EP consists of methods for initially collecting the required information
to display parameter information at the caret position as well as methods to update what should be displayed during edits.

## Implementation

Language authors implement
[`ParameterInfoHandler`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandler.java)
which takes two type parameters: `ParameterOwner` and `ParameterType`.
For the explanations that follow, we assume that `ParameterOwner` is a PSI element that represents a function call in a language,
and `ParameterType` represents (possibly several) function definitions.

Additionally, `ParameterInfoHandler` uses several context types that are mutable and used to adjust what and how parameter information is displayed.
These contexts are, e.g.,
[`CreateParameterInfoContext`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/CreateParameterInfoContext.java),
[`UpdateParameterInfoContext`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/UpdateParameterInfoContext.java)
and
[`ParameterInfoUIContext`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoUIContext.java)
and they all derive from
[`ParameterInfoContext`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoContext.java).

### Initial Phase

The initial phase describes what happens when no parameter info is currently displayed, and it is invoked either automatically or by the user.

1. The `findElementForParameterInfo()` method is called.
   When overriding this method, language authors use the provided `CreateParameterInfoContext` to access, e.g., the file and offset of the current editor.
   The goal is to identify the `ParameterOwner` i.e., a function call at the current offset if it exists.
   It is advised to extract the actual search for the function call into a separate method since it can be re-used later.
   The `findElementForParameterInfo()` implementation should find all matching function definitions and store them using `setItemsToShow()` of the context parameter.
2. If the returned function call element is valid, the `showParameterInfo()` method is invoked.
   Implementations of this method usually just call `showHint()` of the `CreateParameterInfoContext` providing the offset at which the popup should appear.
3. For each item to show from step 1, the `updateUI()` method is called.
   No heavy work is allowed in this method since it runs on [EDT](threading_model.md), and it should only update the UI representation using, e.g.,
   `setUIComponentEnabled()` or `setupUIComponentPresentation()` of the provided `ParameterInfoUIContext`.
4. After that the following methods are called which will be explained in the next phase: `findElementForUpdatingParameterInfo()`,
   `updateParameterInfo()`, `updateUI()`.

### Update Phase

When a parameter info popup is displayed and the user types something or moves the caret, the displayed information is updated.
This allows for, e.g., highlighting a function usage with different arguments or simply moving the parameter info box closer to the caret.
Therefore, when the user moves the caret or types something, the following happens:

1. The `syncUpdateOnCaretMove()` method is called.
2. The `findElementForUpdatingParameterInfo()` method is called, and it should find the correct function call (`ParameterOwner`) for the changed caret position.
   Implementations return `null` if an appropriate element could not be found or if it is different from `getParameterOwner()` of the provided `UpdateParameterInfoContext`.
   If `null` is returned, the `dispose()` method is called.
3. The `processFoundElementForUpdatingParameterInfo()` method is called which allows for additional adjustments of the `UpdateParameterInfoContext`.
   By default, this method does nothing and it's usually not necessary to implement it.
4. The `updateParameterInfo()` is called. Many implementations only invoke `setParameterOwner()` of the `UpdateParameterInfoContext` here.
5. The `updateUI()` method is called for each item in the `getItemsToShow()` array of the context which was collected in the initial phase.

## Further Tips

Language authors can implement
[`ParameterInfoHandlerWithTabActionSupport`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoHandlerWithTabActionSupport.java)
to extend the parameter info functionality with the ability to jump between parameter positions by pressing the tab key.
For recurring tasks like finding the index of the current parameter in a function call,
[`ParameterInfoUtils`](%gh-ic%/platform/lang-api/src/com/intellij/lang/parameterInfo/ParameterInfoUtils.java) provides a collection of useful functions.

It is further helpful to inspect all the context-interfaces that extend from `ParameterInfoContext` and can be found in the `com.intellij.lang.parameterInfo` package
as they provide insight into what data of the parameter info can be accessed and changed in the different stages.

Methods of the `ParameterInfoHandler` that have a default implementation can usually be ignored.
`syncUpdateOnCaretMove()` and `supportsOverloadSwitching()` are used internally by the IntelliJ Platform and are not required to be implemented by plugins.
The `dispose()` method is called when the currently displayed parameter info is invalidated and destroyed.
Only `isWhitespaceSensitive()` which is used in the `getCurrentOffset()` method of
[`ParameterInfoControllerBase`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/hint/ParameterInfoControllerBase.java)
should be implemented when whitespace matters in a language.

Note that parameter info works during indexing (using incomplete data) when the implementation is marked as [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).
It is recommended to adapt tests for [dumb mode](indexing_and_psi_stubs.md#dumb-mode) since the results might be surprising,
and more changes to the handler might be required for better results.

Finally, language authors should be aware of the global
[`CodeInsightSettings#SHOW_FULL_SIGNATURES_IN_PARAMETER_INFO`](%gh-ic%/platform/analysis-impl/src/com/intellij/codeInsight/CodeInsightSettings.java)
setting that can be used to present results that are consistent with the default IDE behavior.
For Java, for instance, the IDE shows the full signature of the method/function on parameter info if this setting is enabled.

## Examples

Existing, moderately complex, implementations of `ParameterInfoHandler` in the IntelliJ Platform that can serve as a reference are:

* [`XPathParameterInfoHandler`](%gh-ic%/plugins/xpath/xpath-lang/src/org/intellij/lang/xpath/XPathParameterInfoHandler.java)
* [`XmlParameterInfoHandler`](%gh-ic%/xml/impl/src/com/intellij/codeInsight/hint/api/impls/XmlParameterInfoHandler.java)

Implementations of third-party plugins can be discovered using the
[IntelliJ Platform Explorer](https://jb.gg/ipe?extensions=com.intellij.codeInsight.parameterInfo).
