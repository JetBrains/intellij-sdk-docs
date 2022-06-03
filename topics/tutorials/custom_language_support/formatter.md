[//]: # (title: 16. Formatter)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

The IntelliJ Platform includes a powerful framework for implementing formatting for custom languages.
A formatter enables reformatting code automatically based on code style settings.
The formatter controls spaces, indents, wrap, and alignment.

**Reference**: [](code_formatting.md)

## Define a Block

The formatting model represents the formatting structure of a file as a tree of [`Block`](upsource:///platform/code-style-api/src/com/intellij/formatting/Block.java) objects, with associated indent, wrap, alignment and spacing settings.
The goal is to cover each PSI element with such a block.
Since each block builds its children's blocks, it can generate extra blocks or skip any PSI elements.
Define `SimpleBlock` based on [`AbstractBlock`](upsource:///platform/code-style-impl/src/com/intellij/psi/formatter/common/AbstractBlock.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleBlock.java"}

## Define a Formatting Model Builder

Define a formatter that removes extra spaces except for the single spaces around the property separator:

<compare style="top-bottom">

```properties
foo  =    bar
```

```properties
foo = bar
```
</compare>

Create `SimpleFormattingModelBuilder` by subclassing [`FormattingModelBuilder`](upsource:///platform/code-style-api/src/com/intellij/formatting/FormattingModelBuilder.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleFormattingModelBuilder.java"}

## Register the Formatter

The `SimpleFormattingModelBuilder` implementation is registered with the IntelliJ Platform in the plugin configuration file using the `com.intellij.lang.formatter` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.formatter
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleFormattingModelBuilder"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Open the example Simple Language [properties file ](lexer_and_parser_definition.md#run-the-project) in the IDE Development Instance.
Add some extra spaces around the `=` separator between `language` and `English`.
Reformat the code by invoking <menupath>Code | Reformat File...</menupath> dialog and choose <control>Run</control>.

![Formatter](formatter.png)
