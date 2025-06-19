<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 7. Annotator

<link-summary>Sample implementation of annotator highlighting resolved and unresolved Simple language properties in Java strings.</link-summary>

<tldr>

**Reference**: [](syntax_highlighting_and_error_highlighting.md#annotator)

**Code**: [`SimpleAnnotator`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java)

**Testing**: [](annotator_test.md)
</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

An `Annotator` helps highlight and annotate any code based on specific rules.
This section adds annotation functionality to support the Simple Language in the context of Java code.

## Required Project Configuration Changes

Classes defined in this step of the tutorial depend on `com.intellij.psi.PsiLiteralExpression` (the PSI representation for String literals in Java code) at runtime.

A dependency on the [Java plugin](idea.md#java-plugin) must be [declared explicitly](plugin_dependencies.md).
First, add a dependency on the Java plugin in the Gradle build script:

```kotlin
dependencies {
  intellijPlatform {
    // ...
    bundledPlugin("com.intellij.java")
  }
}
```

See <path>[build.gradle.kts](%gh-sdk-samples-master%/simple_language_plugin/build.gradle.kts)</path> for the reference.

Then, declare the dependency in <path>[plugin.xml](plugin_configuration_file.md)</path> (use code insight)

```xml
<depends>com.intellij.java</depends>
```

## Define an Annotator

The [`SimpleAnnotator`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java) subclasses [`Annotator`](%gh-ic%/platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java).
Consider a literal string that starts with "simple:" as a prefix of a Simple Language key.
It isn't part of the Simple Language, but it is a useful convention for detecting Simple Language keys embedded as string literals in other languages, like Java.
Annotate the `simple:key` literal expression and differentiate between a well-formed vs. an unresolved property.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java" include-symbol="SimpleAnnotator"}

> If the above code is copied at this stage of the tutorial, then remove the line below the comment _** Tutorial step 19. …_ as
> `SimpleCreatePropertyQuickFix` is not defined until [later](quick_fix.md) in the tutorial.
>
{title="SimpleCreatePropertyQuickFix"}

## Register the Annotator

Using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.annotator"/></include> in the plugin configuration file, register the Simple Language annotator class for `JAVA` language:

```xml
<extensions defaultExtensionNs="com.intellij">
  <annotator
      language="JAVA"
      implementationClass="org.intellij.sdk.language.SimpleAnnotator"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

As a test, define the following Java file containing a Simple Language `prefix:value` pair:

```java
public class Test {
  public static void main(String[] args) {
    System.out.println("simple:website");
  }
}
```

Open this Java file in an IDE Development Instance running the `simple_language_plugin` to check if the IDE resolves a property:

![Annotator](annotator.png){width="800"}

If the property is an undefined name, the annotator flags the code with an error.

![Unresolved property](unresolved_property.png){width="800"}

Try changing the Simple Language [color settings](syntax_highlighter_and_color_settings_page.md#run-the-project) to differentiate the annotation from the default language color settings.
