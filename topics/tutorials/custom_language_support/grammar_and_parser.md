[//]: # (title: 3. Grammar and Parser)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

In order for the IntelliJ Platform to parse a Simple Language file, tokens and elements must be defined based on [`IElementType`](upsource:///platform/core-api/src/com/intellij/psi/tree/IElementType.java).
The Simple Language grammar must also be defined to generate a parser.

**Reference**:
- [](implementing_lexer.md)
- [](implementing_parser_and_psi.md)

## Define a Token Type

Create `SimpleTokenType` in the `org.intellij.sdk.language.psi` package (see the `simple_language_plugin` code sample) by subclassing `IElementType`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleTokenType.java"}

## Define an Element Type

Create the `SimpleElementType` in the `org.intellij.sdk.language.psi` package by subclassing `IElementType`.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/psi/SimpleElementType.java"}

## Define the Grammar

Define a grammar for the Simple Language in the <path>com/intellij/sdk/language/Simple.bnf</path> file.

```properties
{
  parserClass="org.intellij.sdk.language.parser.SimpleParser"

  extends="com.intellij.extapi.psi.ASTWrapperPsiElement"

  psiClassPrefix="Simple"
  psiImplClassSuffix="Impl"
  psiPackage="org.intellij.sdk.language.psi"
  psiImplPackage="org.intellij.sdk.language.psi.impl"

  elementTypeHolderClass="org.intellij.sdk.language.psi.SimpleTypes"
  elementTypeClass="org.intellij.sdk.language.psi.SimpleElementType"
  tokenTypeClass="org.intellij.sdk.language.psi.SimpleTokenType"
}

simpleFile ::= item_*

private item_ ::= (property|COMMENT|CRLF)

property ::= (KEY? SEPARATOR VALUE?) | KEY
```

Please see [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) documentation for more details on BNF syntax.

The grammar defines the flexibility of the support for a language.
The above grammar specifies that a property may have or may not have a key and value.
This flexibility allows the IntelliJ Platform to recognize incorrectly defined properties and provide corresponding code analysis and quick-fixes.

Note that the `SimpleTypes` class in the `elementTypeHolderClass` attribute above specifies the name of a class that gets generated from the grammar; it doesn't exist at this point.

## Generate a Parser

Now that the grammar is defined, generate a parser with PSI classes via <control>Generate Parser Code</control> from the context menu on the <path>Simple.bnf</path> file.
This step generates a parser and PSI elements in the <path>/src/main/gen</path> folder of the project.

> [Gradle Grammar-Kit Plugin](tools_gradle_grammar_kit_plugin.md) can be used alternatively.
>
{type="tip"}

![Parser](generated_parser.png){width="800"}

## Add Generated Sources Root

To include the sources generated into <path>/src/main/gen</path>, the project's `sourceSets` must be expanded by inserting the following line in the project's Gradle build script:

<tabs>
<tab title="Kotlin">

```kotlin
sourceSets["main"].java.srcDirs("src/main/gen")
```

</tab>
<tab title="Groovy">

```groovy
sourceSets.main.java.srcDirs 'src/main/gen'
```

</tab>
</tabs>

Reload the Gradle project for changes to take effect and build the project.
