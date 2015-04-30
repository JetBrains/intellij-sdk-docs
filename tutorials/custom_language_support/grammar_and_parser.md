---
layout: editable
title: 3. Grammar and Parser
---

### 1. Define a token type

```java
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.SimpleLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SimpleTokenType extends IElementType {
    public SimpleTokenType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SimpleTokenType." + super.toString();
    }
}
```

### 2. Define an element type

```java
package com.simpleplugin.psi;

import com.intellij.psi.tree.IElementType;
import com.simpleplugin.SimpleLanguage;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SimpleElementType extends IElementType {
    public SimpleElementType(@NotNull @NonNls String debugName) {
        super(debugName, SimpleLanguage.INSTANCE);
    }
}
```

### 3. Define grammar

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

### 4. Generate a parser

Now when the grammar is defined we can generate a parser with PSI classes via *Generate Parser Code* from the context menu or via *⌘⇧G* shortcut on *Simple.bnf* file.
The Grammar-Kit will generate a parser and PSI elements in *gen* folder.
Mark this folder as a source root and make sure everything is compiled without errors.

![Parser](img/generated_parser.png)

-------
[Previous](language_and_filetype.html)
[Top](cls_tutorial.html)
[Next](lexer_and_parser_definition.html)


