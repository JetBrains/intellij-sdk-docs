[//]: # (title: 4. Lexer and Parser Definition)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

The lexical analyzer defines how the contents of a file are broken into tokens, which is the basis for supporting custom language features.
The easiest way to create a lexer is to use [JFlex](https://jflex.de/).

**Reference**: [](implementing_lexer.md)

## Define a Lexer

Define a <path>Simple.flex</path> file with rules for the Simple Language lexer in package `org.intellij.sdk.language`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/Simple.flex"}

## Generate a Lexer Class

Now generate a lexer class via <menupath>Run JFlex Generator</menupath> from the context menu on <path>Simple.flex</path> file.

The Grammar-Kit plugin uses the JFlex lexer generation.
When running for the first time, JFlex prompts for a destination folder to download the JFlex library and skeleton.
Choose the project root directory, for example <path>code_samples/simple_language_plugin</path>.

After that, the IDE generates the lexer under the <path>gen</path> directory, for example in <path>simple_language_plugin/src/main/gen/org/intellij/sdk/language/SimpleLexer</path>.

> [](tools_gradle_grammar_kit_plugin.md) can be used alternatively.
>
{type="tip"}

## Define a Lexer Adapter

The JFlex lexer needs to be adapted to the IntelliJ Platform Lexer API.
This is done by subclassing [`FlexAdapter`](upsource:///platform/core-api/src/com/intellij/lexer/FlexAdapter.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLexerAdapter.java"}

## Define a Root File

The `SimpleFile` implementation is the top-level node of the [tree of `PsiElements`](implementing_parser_and_psi.md) for a Simple Language file.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleFile.java"}

## Define a Parser

The Simple Language parser is defined by subclassing [`ParserDefinition`](upsource:///platform/core-api/src/com/intellij/lang/ParserDefinition.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleParserDefinition.java"}

## Register the Parser Definition

Registering the parser definition in the <path>plugin.xml</path> file makes it available to the IntelliJ Platform.
Use the `com.intellij.lang.parserDefinition` extension point for registration.
For example, see <path>simple_language_plugin/src/main/resources/META-INF/plugin.xml</path>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.parserDefinition
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleParserDefinition"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](gradle_prerequisites.md#running-a-simple-gradle-based-intellij-platform-plugin) task.

Create a <path>test.simple</path>  file with the following content:

```text
# You are reading the ".properties" entry.
! The exclamation mark can also mark text as comments.
website = https://en.wikipedia.org/
language = English
# The backslash below tells the application to continue reading
# the value onto the next line.
message = Welcome to \
          Wikipedia!
# Add spaces to the key
key\ with\ spaces = This is the value that could be looked up with the key "key with spaces".
# Unicode
tab : \u0009
```

Now open the <control>PsiViewer</control> tool window and check how the lexer breaks the content of the file into tokens, and the parser transforms the tokens into PSI elements.

![PSI Elements](psi_elements.png)
