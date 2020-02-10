---
title: 9. Completion Contributor
---

Custom languages provide code completion using one of [two approaches](/reference_guide/custom_language_support/code_completion.md).
The Simple language plugin implements the less complex of the two methods, reference completion.

* bullet list
{:toc}

## 9.1. Define a Completion Contributor
For the purposes of this tutorial, the `simple_language` plugin provides custom completion for values in Simple language property files.
Create a completion contributor by subclassing [`CompletionContributor`](upsource:///platform/analysis-api/src/com/intellij/codeInsight/completion/CompletionContributor.java).
This rudimentary completion contributor always adds "Hello" to the results set, regardless of context:
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleCompletionContributor.java %}
```

## 9.2. Register the Completion Contributor
The `SimpleCompletionContributor` implementation is registered with the IntelliJ Platform using the `completion.contributor` extension point.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <completion.contributor language="Simple" implementationClass="com.intellij.sdk.language.SimpleCompletionContributor"/>
  </extensions>
```

## 9.3. Run the Project
Run the `simple_language` plugin in a Development Instance and open the [`test.simple`](/tutorials/custom_language_support/lexer_and_parser_definition.md#run-the-project) file.
Erase the property "English" and invoke [Basic Code Completion](https://www.jetbrains.com/help/idea/auto-completing-code.html#invoke-basic-completion). 
The choice "Hello" is shown:

![Completion](img/completion.png){:width="800px"}
