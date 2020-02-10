---
title: 15. Formatter
---

The IntelliJ Platform includes a powerful framework for implementing formatting for custom languages.
A [formatter](/reference_guide/custom_language_support/code_formatting.md) enables reformatting code automatically based on code style settings.
The formatter controls spaces, indents, wrap, and alignment.

* bullet list
{:toc}

## 15.1. Define a Block
The formatting model builds represents the formatting structure of a file as a tree of [`Block`](upsource:///platform/lang-api/src/com/intellij/formatting/Block.java) objects, with associated indent, wrap, alignment and spacing setting
The goal is to cover each PSI element with such a block. 
Since each block builds its children's blocks, it can generate extra blocks or skip any PSI elements.
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleBlock.java %}
```

## 15.2. Define a Formatting Model Builder
Define a formatter which removes extra spaces except the single spaces around the property separator.
For example, reformat `foo  =    bar` to `foo = bar`.
```java
{% include /code_samples/simple_language/src/main/java/com/intellij/sdk/language/SimpleFormattingModelBuilder.java %}
```

## 15.3. Register the Formatter
The `SimpleFormattingModelBuilder` implementation is registered with the IntelliJ Platform in `plugin.xml` using the `lang.formatter` extension point. 
```xml
 <extensions defaultExtensionNs="com.intellij">
   <lang.formatter language="Simple" implementationClass="com.intellij.sdk.language.SimpleFormattingModelBuilder"/>
  </extensions>
```

## 15.4. Run the Project
Add some extra spaces around the `=` separator between `language` and `English`.
Reformat the code by selecting **Code \| Show Reformat File Dialog** and choose **Run**.

![Formatter](img/formatter.png){:width="800px"}
