<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 4. Lexer and Parser Definition

<link-summary>Sample implementation of Simple language lexer and parser.</link-summary>

<tldr>

**Reference**: [](implementing_lexer.md)

**Code**: [`Simple.flex`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/Simple.flex),
[`SimpleLexerAdapter`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLexerAdapter.java),
[`SimpleFile`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleFile.java),
[`SimpleTokenSets`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleTokenSets.java),
[`SimpleParserDefinition`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleParserDefinition.java)

**Testing**: [](parsing_test.md)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

The lexical analyzer defines how the contents of a file are broken into tokens, which is the basis for supporting custom language features.
The easiest way to create a lexer is to use [JFlex](https://jflex.de/).

## Define a Lexer

Define a [`Simple.flex`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/Simple.flex) file with rules for the Simple Language lexer in package `org.intellij.sdk.language`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/Simple.flex"}

## Generate a Lexer Class

Now generate a lexer class via <ui-path>Run JFlex Generator</ui-path> from the context menu on <path>Simple.flex</path> file.

> Users from China, please see [important configuration](https://github.com/JetBrains/Grammar-Kit/issues/300#issuecomment-1476498645).

The Grammar-Kit plugin uses the JFlex lexer generation.
When running for the first time, JFlex prompts for a destination folder to download the JFlex library and skeleton.
Choose the project root directory, for example <path>code_samples/simple_language_plugin</path>.

After that, the IDE generates the lexer under the <path>gen</path> directory, for example in <path>simple_language_plugin/src/main/gen/org/intellij/sdk/language/SimpleLexer</path>.

> [](tools_gradle_grammar_kit_plugin.md) can be used alternatively.
>

## Define a Lexer Adapter

The JFlex lexer needs to be adapted to the IntelliJ Platform Lexer API.
Implement [`SimpleLexerAdapter`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLexerAdapter.java) by subclassing [`FlexAdapter`](%gh-ic%/platform/core-impl/src/com/intellij/lexer/FlexAdapter.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleLexerAdapter.java" include-symbol="SimpleLexerAdapter"}

## Define a Root File

The [`SimpleFile`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleFile.java) implementation is the top-level node of the [tree of `PsiElements`](implementing_parser_and_psi.md) for a Simple Language file.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleFile.java" include-symbol="SimpleFile"}

## Define Token Sets

Define all sets of related token types from `SimpleTypes` in [`SimpleTokenSets`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleTokenSets.java).

```java

```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleTokenSets.java" include-symbol="SimpleTokenSets"}

## Define a Parser

The Simple Language parser is defined in [`SimpleParserDefinition`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleParserDefinition.java) by subclassing [`ParserDefinition`](%gh-ic%/platform/core-api/src/com/intellij/lang/ParserDefinition.java).
To avoid unnecessary classloading when initializing the extension point implementation, all `TokenSet` return values should use constants from dedicated `$Language$TokenSets` class.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleParserDefinition.java" include-symbol="SimpleParserDefinition"}

## Register the Parser Definition

Registering the parser definition in the <path>[plugin.xml](plugin_configuration_file.md)</path> file makes it available to the IntelliJ Platform.
Use the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.parserDefinition"/></include> for registration.
For example, see <path>simple_language_plugin/src/main/resources/META-INF/plugin.xml</path>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.parserDefinition
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleParserDefinition"/>
</extensions>
```

## Run the Project

Run the plugin by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Create a <path>test.simple</path>  file with the following content:

```properties
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

Use the [PsiViewer plugin or built-in PSI viewer](explore_api.md#internalMode) and check how the lexer breaks the content of the file into tokens,
and the parser transforms the tokens into PSI elements.

![PSI Elements](psi_elements.png)
