[//]: # (title: Symbols)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> This API is available starting from 2020.3 and is currently in development and thus in an experimental state.
>
{type="warning"}

<excerpt rel="excerpt"/>
<p id="excerpt">
A symbol is a semantic element in some model, e.g., language or framework model.
</p>

The IntelliJ Platform uses [`Symbol`](upsource:///platform/core-api/src/com/intellij/model/Symbol.java) to represent symbols, and `Symbol` serves as a link between Platform APIs, such as navigation, finding usages, or renaming.
This API allows implementing the same functionalities as in the [References and Resolve](references_and_resolve.md) mechanism, but it is a more abstract concept not limited to connecting only PSI elements.
The platform obtains the target symbol from a [declaration](declarations_and_references.md#declarations) or by resolving a [reference](declarations_and_references.md#references) and then uses it to perform an action.
The [`PsiElement`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java) is considered as an element in the source tree (enhanced `ASTNode`).
`Symbol` decouples semantic actions from [PSI](psi.md).

A `Symbol` is not required to be backed by a `PsiElement`, and it is incorrect to try to obtain the `PsiElement` from a `Symbol`.
`Symbol` is not required to be bound to a `Project` as well, meaning the same instance might be shared between [projects](project.md).

Examples:

- Java local variable is a symbol in Java language model, it's backed by a `PsiVariable` element.
- Compiled class is a symbol in JVM model, it's backed by JDK library stubs, and it's not bound to any project.
- Spring Bean is a symbol in [Spring framework model](spring_api.md), it's defined on-the-fly by framework support (not backed by a `PsiElement`) and bound to a `Project`.
- Database column is a symbol defined by data source (not backed by a `PsiElement`) and not bound to a `Project` since database elements might be shared between projects.

## Lifecycle

The `Symbol` instance is expected to stay valid within a single read action, which means it's safe to pass the instance to different APIs.
A `Symbol` instance should not be referenced between read actions.
One should create a pointer via `Symbol.createPointer()`  in the current read action, and then call `Pointer.dereference()` to obtain a `Symbol` instance in the subsequent read action.
