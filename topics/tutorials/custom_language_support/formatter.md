<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 16. Formatter

<link-summary>Sample implementation of Simple language formatter.</link-summary>

<tldr>

**Reference**: [](code_formatting.md)

**Code**: [`SimpleBlock`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleBlock.java),
[`SimpleFormattingModelBuilder`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFormattingModelBuilder.java)

**Testing**: [](formatter_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

The IntelliJ Platform includes a powerful framework for implementing formatting for custom languages.
A formatter enables reformatting code automatically based on code style settings.
The formatter controls spaces, indents, wrap, and alignment.

## Define a Block

The formatting model represents the formatting structure of a file as a tree of [`Block`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/Block.java) objects, with associated indent, wrap, alignment, and spacing settings.
The goal is to cover each PSI element with such a block.
Since each block builds its children's blocks, it can generate extra blocks or skip any PSI elements.
Define [`SimpleBlock`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleBlock.java) based on [`AbstractBlock`](%gh-ic%/platform/code-style-impl/src/com/intellij/psi/formatter/common/AbstractBlock.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleBlock.java" include-symbol="SimpleBlock"}

## Define a Formatting Model Builder

Define a formatter that removes extra spaces except for the single spaces around the property separator:

<compare type="top-bottom">

```properties
foo  =    bar
```

```properties
foo = bar
```
</compare>

Create [`SimpleFormattingModelBuilder`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFormattingModelBuilder.java) by implementing [`FormattingModelBuilder`](%gh-ic%/platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFormattingModelBuilder.java" include-symbol="SimpleFormattingModelBuilder"}

## Register the Formatter

The ``SimpleFormattingModelBuilder` implementation is registered with the IntelliJ Platform in the plugin configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.formatter"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.formatter
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleFormattingModelBuilder"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the example Simple Language [properties file](lexer_and_parser_definition.md#run-the-project) in the IDE Development Instance.
Add some extra spaces around the `=` separator between `language` and `English`.
Reformat the code by invoking <ui-path>Code | Reformat File...</ui-path> dialog and choose <control>Run</control>.

![Formatter](formatter.png)
