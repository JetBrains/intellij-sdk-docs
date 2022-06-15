[//]: # (title: 9. Completion Contributor)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

Custom languages provide code completion using one of two approaches: Contributor and Reference-based (see [](reference_contributor.md)) completion.

**Reference**: [](code_completion.md)

## Define a Completion Contributor

For this tutorial, the `simple_language_plugin` provides custom completion for values in Simple Language property files.
Create a completion contributor by subclassing [`CompletionContributor`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java).
This rudimentary completion contributor always adds "Hello" to the completion variants result set, regardless of context:

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleCompletionContributor.java"}

## Register the Completion Contributor

The `SimpleCompletionContributor` implementation is registered in the plugin configuration file using the `com.intellij.completion.contributor` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <completion.contributor
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleCompletionContributor"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Open the [`test.simple`](lexer_and_parser_definition.md#run-the-project) file.
Erase the property "English" and invoke [Basic Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#invoke-basic-completion).
The choice "Hello" is shown:

![Completion](completion.png)
