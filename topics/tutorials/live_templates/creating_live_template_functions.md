<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Creating New Functions for Live Templates

<link-summary>Adding new functions for use in Live Templates.</link-summary>

The [Predefined Functions](https://www.jetbrains.com/help/idea/template-variables.html#predefined_functions) are the building blocks for creating [Parameterized Templates and Surround Templates](https://www.jetbrains.com/help/idea/using-live-templates.html#live_templates_types).
However, sometimes the Predefined Functions are not enough.

This tutorial illustrates how to add custom functions to an IntelliJ Platform plugin and make them available for use by Live Templates.
As an example, a function is created to convert a selection to Title Case.
Refer to the SDK code sample [`live_templates`](%gh-sdk-samples-master%/live_templates).

## Implementing a New Function

Under the hood, the predefined functions for Live Templates are called _macros_.
A new custom function for Live Templates is implemented in `TitleCaseMacro`, which extends [`MacroBase`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/template/macro/MacroBase.java).
Three `TitleCaseMacro` methods are of particular interest:
* The `TitleCaseMacro()` constructor passes the name and description of the macro to the parent constructor.
* The `isAcceptableInContext()` method tests whether the macro is available in the current context.
  The test relies on the [`MarkdownContext`](providing_live_templates.md#implement-templatecontexttype) object previously defined in the `live_templates` plugin.
* The `calculateResult()` method gets invoked when the titleCase function is used in a Live Template.
  The text to be capitalized is retrieved from the Live Template and converted to Title Case.

```java
```
{src="live_templates/src/main/java/org/intellij/sdk/liveTemplates/TitleCaseMacro.java" include-symbol="TitleCaseMacro"}

## Adding a Live Template

Using the procedures previously discussed for [Template Creation](providing_live_templates.md#template-creation) and [Export the Live Template](providing_live_templates.md#export-the-live-template), add a Live Template to the [Markdown.xml](%gh-sdk-samples-master%/live_templates/src/main/resources/liveTemplates) file for the plugin.
The XML representation of an example Live Template using the new `titleCase` function is listed below.

There is only one variable, `TITLE`.
The expression for `TITLE` evaluates to the `titleCase` function provided by the plugin.
The argument to the `titleCase` function is `SELECTION`, which tells the IntelliJ Platform to operate on the current selection.

```xml
<template
    name="mc"
    value="$TITLE$"
    description="SDK: Convert to title case"
    toReformat="true"
    toShortenFQNames="false">
  <variable
      name="TITLE"
      expression="titleCase(SELECTION)"
      defaultValue="the quick brown fox"
      alwaysStopAt="true"/>
  <context>
    <option name="MARKDOWN" value="true"/>
  </context>
</template>
```

## Register Extension Point

Using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.liveTemplateMacro"/></include>, register the implementation with the IntelliJ Platform.

```xml
<extensions defaultExtensionNs="com.intellij">
  <liveTemplateMacro
      implementation="org.intellij.sdk.liveTemplates.TitleCaseMacro"/>
</extensions>
```

## Check Plugin

Now verify the plugin is working correctly.
* Run the plugin in a Development Instance.
* Create a new file `testing.md` and enter several words in lower case.
* Highlight the text and enter invoke <ui-path>Code | Surround With...</ui-path> to open the <control>Surround With</control> popup.
  Confirm that the <control>SDK: Convert to title case</control> is available in the popup, and select it.
* The selected text will change to have each word capitalized.
