<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 12. Folding Builder

<link-summary>Sample implementation folding builder allowing to see the values of Simple language properties referenced in Java files.</link-summary>

<tldr>

**Code**: [`SimpleFoldingBuilder`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java)

**Testing**: [](folding_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

A folding builder identifies the folding regions in the code.
In this step of the tutorial, the folding builder is used to identify folding regions and replace the regions with specific text.
Rather than the usual practice of using a folding builder to collapse a class, method, or comments to fewer lines, the folding builder replaces Simple Language keys with their corresponding values.

## Define a Folding Builder

The [`SimpleFoldingBuilder`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java) replaces usages of properties with their values by default.
Start by subclassing [`FoldingBuilderEx`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingBuilderEx.java)

Note that `SimpleFoldingBuilder` is marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI),
which means the class is allowed to run in [dumb mode](indexing_and_psi_stubs.md#dumb-mode), when indexes are in background update.

> A folding builder must implement [`DumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbAware.java) to function in this tutorial and pass tests.
>
{style="note"}

The `buildFoldRegions()` method searches down a PSI tree from `root` to find all literal expressions containing the [simple prefix](annotator.md#define-an-annotator) `simple:`.
The remainder of such a string is expected to contain a Simple Language key, and so the text range is stored as a [`FoldingDescriptor`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/FoldingDescriptor.java).

The `getPlaceholderText()` method retrieves the Simple Language value corresponding to the key associated with the (ASTNode) provided.
The IntelliJ Platform uses the value to substitute for the key when the code gets folded.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFoldingBuilder.java" include-symbol="SimpleFoldingBuilder"}

## Register the Folding Builder

The `SimpleFoldingBuilder` implementation is registered with the IntelliJ Platform in the plugin configuration file using
the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.foldingBuilder"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.foldingBuilder
      language="JAVA"
      implementationClass="org.intellij.sdk.language.SimpleFoldingBuilder"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Now when a Java file is opened in the editor, it shows the property's value instead of the key.
This is because `SimpleFoldingBuilder.isCollapsedByDefault()` always returns `true`.
Try using <ui-path>Code | Folding | Expand All</ui-path> to show the key rather than the value.

![Folding](folding.png)
