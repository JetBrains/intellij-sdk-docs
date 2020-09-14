---
title: Symbols
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **WARNING** This API is currently in development and thus in experimental state

[`Symbol`](upsource:///platform/core-api/src/com/intellij/model/Symbol.java) is a semantic element in some model, 
e.g. language model or framework model. 
It serves as a link between different platform APIs, such as navigation, finding usages or renaming. 
The platform obtains the target symbol from a [declaration](declarations_and_references_20203.md#declarations) 
or by resolving a [reference](declarations_and_references_20203.md#references), and then uses it to perform an action.
The `PsiElement` is considered as an element in the source tree (enhanced `ASTNode`). `Symbol` decouples semantic actions from PSI.

`Symbol` is not required to be backed by a `PsiElement`, it is incorrect to try to obtain the `PsiElement` from a `Symbol`.  
`Symbol` is not required to be bound to a `Project` as well, meaning the same instance might be shared between projects.

**Examples**:
- Java local variable is a symbol in Java language model, it's backed by a `PsiVariable` element;
- Compiled class is a symbol in JVM model, it's backed by JDK library stubs, and it's not bound to any project;
- Spring Bean is a symbol in Spring framework model, it's defined on-the-fly by framework support 
  (not backed by a `PsiElement`) and bound to a `Project`;
- Database column is a symbol defined by data source (not backed by a `PsiElement`)
and not bound to a `Project` since DB elements might be shared between projects.


### Lifecycle

The `Symbol` instance is expected to stay valid within a single read action, which means it's safe to pass the instance to different APIs.
`Symbol` instance should not be referenced between read actions. One should create a pointer via `Symbol#createPointer()` 
in the current read action, and then call `Pointer#dereference()` to obtain a `Symbol` instance in the subsequent read action.
