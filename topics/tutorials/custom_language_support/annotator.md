[//]: # (title: 7. Annotator)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

An `Annotator` helps highlight and annotate any code based on specific rules.
This section adds annotation functionality to support the Simple Language in the context of Java code.

**Reference**: [Annotator](syntax_highlighting_and_error_highlighting.md#annotator)

## Required Project Configuration Changes

Classes defined in this step of the tutorial depend on `com.intellij.psi.PsiLiteralExpression` (the PSI representation for String literals in Java code) at runtime.
Using `PsiLiteralExpression` [introduces a dependency](plugin_compatibility.md#modules-specific-to-functionality) on `com.intellij.java`.

Beginning in version 2019.2, a dependency on Java plugin [must be declared explicitly](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/).
First, add a dependency on the Java plugin in the Gradle build script:

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  plugins.set(listOf("com.intellij.java"))
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
  plugins = ['com.intellij.java']
}
```

</tab>
</tabs>

Then, declare the dependency in <path>plugin.xml</path> (use code insight)

```xml
<depends>com.intellij.java</depends>
```

## Define an Annotator

The `SimpleAnnotator` subclasses [`Annotator`](upsource:///platform/analysis-api/src/com/intellij/lang/annotation/Annotator.java).
Consider a literal string that starts with "simple:" as a prefix of a Simple Language key.
It isn't part of the Simple Language, but it is a useful convention for detecting Simple Language keys embedded as string literals in other languages, like Java.
Annotate the `simple:key` literal expression, and differentiate between a well-formed vs. an unresolved property.

> The use of new `AnnotationHolder` syntax starting 2020.2, which uses the builder format.
>
{type="note"}

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleAnnotator.java"}

> If the above code is copied at this stage of the tutorial, then remove the line below the comment "** Tutorial step 18.3 …" The quick fix class in that line is not defined until later in the tutorial.
>
{type="tip"}

## Register the Annotator

Using the `com.intellij.annotator` extension point in the plugin configuration file, register the Simple Language annotator class with the IntelliJ Platform:

```xml
<extensions defaultExtensionNs="com.intellij">
  <annotator
      language="JAVA"
      implementationClass="org.intellij.sdk.language.SimpleAnnotator"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

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
