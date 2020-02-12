---
title: 7. Annotator
---

An [Annotator](/reference_guide/custom_language_support/syntax_highlighting_and_error_highlighting.md#annotator) helps highlight and annotate any code based on specific rules.
This section adds annotation functionality to support the Simple language in the context of Java code.

* bullet list
{:toc} 

## 7.1. Define an Annotator
The `SimpleAnnotator` subclasses [`Annotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java).
Consider a literal string that starts with "simple:" as a prefix of a Simple language key.
It isn't part of the Simple language, but it is a useful convention for detecting Simple language keys embedded as string literals in other languages, like Java.
Annotate the `simple:key` literal expression, and differentiate between a well-formed vs. an unresolved property: 
```java
{% include /code_samples/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java %}
```

## 7.2. Register the Annotator
Using an extension point, register the Simple language annotator class  with the IntelliJ Platform:
```xml
  <extensions defaultExtensionNs="com.intellij">
    <annotator language="JAVA" implementationClass="org.intellij.sdk.language.SimpleAnnotator"/>
  </extensions>
```

## 7.3. Run the Project
As a test, define the following Java file containing a Simple language `prefix:value` pair:
```java
public class Test {
    public static void main(String[] args) {
        System.out.println("simple:website");
    }
}
```

Open this Java file in an IDE Development Instance running the `simple_language_plugin` to check if the IDE resolves a property: 

![Annotator](img/annotator.png){:width="800px"}

If the property is an undefined name, the annotator flags the code with an error.

![Unresolved property](img/unresolved_property.png){:width="800px"}

Try changing the Simple language [color settings](/tutorials/custom_language_support/syntax_highlighter_and_color_settings_page.md#run-the-project-1) to differentiate the annotation from the default language color settings.