---
title: 3. Grammar and Parser
---

### 3.1. Define a token type

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/psi/SimpleTokenType.java %}
```

### 3.2. Define an element type

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/psi/SimpleElementType.java %}
```

### 3.3. Define grammar

Define a grammar for the properties language with */com/simpleplugin/Simple.bnf* file.

```java
{
  parserClass="com.simpleplugin.parser.SimpleParser"

  extends="com.intellij.extapi.psi.ASTWrapperPsiElement"

  psiClassPrefix="Simple"
  psiImplClassSuffix="Impl"
  psiPackage="com.simpleplugin.psi"
  psiImplPackage="com.simpleplugin.psi.impl"

  elementTypeHolderClass="com.simpleplugin.psi.SimpleTypes"
  elementTypeClass="com.simpleplugin.psi.SimpleElementType"
  tokenTypeClass="com.simpleplugin.psi.SimpleTokenType"
}

simpleFile ::= item_*

private item_ ::= (property|COMMENT|CRLF)

property ::= (KEY? SEPARATOR VALUE?) | KEY
```

As you see a properties file can contain properties, comments and line breaks.

The grammar defines how flexible the support for a language can be.
We specified that a property may have or may not have key and value.
This lets the IDE still recognise incorrectly defined properties and provide corresponding code analysis and quick-fixes.

### 3.4. Generate a parser

Now when the grammar is defined we can generate a parser with PSI classes via *Generate Parser Code* from the context menu or via *⌘⇧G* shortcut on *Simple.bnf* file.
The Grammar-Kit will generate a parser and PSI elements in *gen* folder.
Mark this folder as a source root and make sure everything is compiled without errors.

![Parser](img/generated_parser.png)

-------
[Previous](language_and_filetype.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](lexer_and_parser_definition.md)


