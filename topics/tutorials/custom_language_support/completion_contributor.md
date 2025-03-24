<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 9. Completion Contributor

<link-summary>Sample implementation of code completion in Simple language files.</link-summary>

<tldr>

**Reference**: [](code_completion.md)

**Code**: [`SimpleCompletionContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCompletionContributor.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

Custom languages provide code completion using one of two approaches: Contributor and Reference-based (see [](reference_contributor.md)) completion.

## Define a Completion Contributor

For this tutorial, the `simple_language_plugin` provides custom completion for values in Simple Language property files.
Create [`SimpleCompletionContributor`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCompletionContributor.java)
by subclassing [`CompletionContributor`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java).
This rudimentary completion contributor always adds "Hello" to the completion variants result set, regardless of context:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCompletionContributor.java" include-symbol="SimpleCompletionContributor"}

## Register the Completion Contributor

The `SimpleCompletionContributor` implementation is registered in the plugin configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.completion.contributor"/></include> and specifying `language="Simple"`.

```xml
<extensions defaultExtensionNs="com.intellij">
  <completion.contributor
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleCompletionContributor"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the [`test.simple`](lexer_and_parser_definition.md#run-the-project) file.
Erase the property "English" and invoke [Basic Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#invoke-basic-completion).
The choice "Hello" is shown:

![Completion](completion.png)
