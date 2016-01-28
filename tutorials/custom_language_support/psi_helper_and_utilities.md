---
title: 6. PSI Helpers and Utilities
---


### 6.1. Define helper methods for generated PSI elements

If we want to have custom methods in PSI classes we need to define them separately and ask Grammar-Kit to embed them into generated code.

Let's define an utility class with these helper methods.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/psi/impl/SimplePsiImplUtil.java %}
```

### 6.2. Update grammar and regenerate the parser

Now we tell to use this utility class in the grammar file via *stubParserClass* attribute.

To tell which methods for which PSI classes must be used we specify methods for particular rule.

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

  psiImplUtilClass="com.simpleplugin.psi.impl.SimplePsiImplUtil"
}

simpleFile ::= item_*

private item_ ::= (property|COMMENT|CRLF)

property ::= (KEY? SEPARATOR VALUE?) | KEY {methods=[getKey getValue]}
```

After we made our changes to the grammar we can regenerate the parser and PSI classes.

### 6.3. Define an utility to search properties

Now we need an utility class to search PSI elements for defined properties over the project.
We will use this utility later when implementing code assistance.

```java
{% include /code_samples/simple_language_plugin/src/com/simpleplugin/SimpleUtil.java %}
```

----------------
[Previous](syntax_highlighter_and_color_settings_page.md)
[Top](/tutorials/custom_language_support_tutorial.md)
[Next](annotator.md)

