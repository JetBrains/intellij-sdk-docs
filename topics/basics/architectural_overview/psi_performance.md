<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PSI Performance

<link-summary>Performance tips on working with PSI.</link-summary>

See also [](threading_model.md#avoiding-ui-freezes) and [](indexing_and_psi_stubs.md#improving-indexing-performance).

> [IDE Perf](https://plugins.jetbrains.com/plugin/15104-ide-perf) plugin provides on-the-fly performance diagnostic tools, including a dedicated view for [`CachedValue`](#cache-results-of-heavy-computations) metrics.

## Overview

PSI has a lot of time-space compromises.
There are tons of PSI elements in IDE memory, so the IntelliJ Platform and language plugins strive to keep them as compact as possible, storing very little data inside.
As a result, many things are recomputed on every call of getter methods on `PsiElement` and its subclasses.
They are not stored inside the fields of `PsiElement`s.

For example, consider the following Java expression:

```java
String.format("Hello, %s!", name);
```

This is its (simplified) PSI:

```
PsiMethodCallExpression:String.format("Hello, %s!", name)
  PsiReferenceExpression:String.format
  PsiExpressionList
    PsiJavaToken:LPARENTH
    PsiLiteralExpression:"Hello, %s!"
    PsiJavaToken:COMMA
    PsiReferenceExpression:name
    PsiJavaToken:RPARENTH
```

Assume that plugin code receives `PsiMethodCallExpression call` and needs to get the first and last expression passed as arguments to this method call.
This can be done with `methodCall.getArgumentList().getExpressions()`, which does the following:
1. `getArgumentList()` traverses the linked list of children, looking for an element of a proper type (in this case – `PsiExpressionList`)
2. `getExpressions()` traverses the children to find all the expressions (elements of `PsiExpression`), then allocates an array of the target size, and then traverses the children again to fill in this array.

These `get*()` methods are not simple getters that return a readily available value – keep this in mind when working with PSI.

As a rule, avoid calling the same method twice, one after another.
Instead, it's better to store the result in a local variable.

Find the first and last expression argument passed to a method call could be implemented as follows:

<compare type="top-bottom" first-title="Inefficient" second-title="Optimized">

```java
PsiExpression first = call.getArgumentList().getExpressions()[0];
int lastIndex = call.getArgumentList().getExpressionCount() - 1;
PsiExpression last = call.getArgumentList().getExpressions()[lastIndex];
```

```java
PsiExpression[] expressions = call.getArgumentList().getExpressions();
PsiExpression first = expressions[0];
PsiExpression last = expressions[expressions.length - 1];
```

</compare>

## Avoid Expensive Methods of `PsiElement`

Avoid `PsiElement`'s methods, which are expensive with deep trees.

`getText()` traverses the whole tree under the given element and concatenates strings, consider using `textMatches()` instead.

`getTextRange()`, `getContainingFile()`, and `getProject()` traverse the tree up to the file, which can be long in very nested trees.
If you only need PSI element length, use `getTextLength()`.

`getContainingFile()` and `getProject()` often can be computed once per task and then stored in fields or passed via parameters.

Additionally, methods such as `getText()`, `getNode()`, or `getTextRange()` require the AST, and accessing it can be an expensive operation, as explained in the next section.

## Avoid Using Many PSI Trees/Documents

Avoid loading too many parsed trees or documents into memory at the same time.
Ideally, only AST nodes from files open in the editor should be present in the memory.
Everything else, even if it's necessary for resolve/highlighting purposes, can be accessed via PSI interfaces, but its implementations should [use stubs](stub_indexes.md) underneath, which are less CPU- and memory-expensive.

If stubs don't suit your case well (e.g., the information you need is large and/or very rarely needed, or you're developing a plugin for a language whose PSI you don't control), you can create a [custom index or gist](indexing_and_psi_stubs.md).

To ensure you're not loading AST accidentally, you can use [`AstLoadingFilter`](%gh-ic%/platform/core-api/src/com/intellij/util/AstLoadingFilter.java) in production and `PsiManagerEx.setAssertOnFileLoadingFilter()` in tests.

The same applies to documents: only the ones opened in editors should be loaded.
Usually, you shouldn't need document contents (as most information can be retrieved from PSI).
If you nevertheless need documents, consider saving the information you need to provide in a [custom index or gist](indexing_and_psi_stubs.md) to get it more cheaply later.
If you still need documents, then at least ensure you load them one by one and don't hold them on strong references to let GC free the memory as quickly as possible.

## Cache Results of Heavy Computations

Method calls such as `PsiElement.getReference()` (and `getReferences()`), `PsiReference.resolve()` (and `multiResolve()` and other equivalents) or computation of expression types, type inference results, control flow graphs, etc. can be expensive.
To avoid paying this cost several times, the result of such computation can be cached and reused.
Usually, [`CachedValue`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValue.java) created with [`CachedValuesManager`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/CachedValuesManager.java) works well for this purpose.

If the information you cache depends only on a subtree of the current PSI element (and nothing else: no resolve results or other files), you can cache it in a field in your `PsiElement` implementation and drop the cache in an override of `ASTDelegatePsiElement.subtreeChanged()`.

### Using `ProjectRootManager` as a Dependency
{id="projectRootManagerDependency"}

<primary-label ref="2024.1"/>

The platform no longer increments root changes modification tracker on finish of [dumb mode](indexing_and_psi_stubs.md#dumb-mode).
If cached values use [`ProjectRootManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java) as a dependency
(without [`PsiModificationTracker`](%gh-ic%/platform/core-api/src/com/intellij/psi/util/PsiModificationTracker.java))
and at the same time depend on [indexes](indexing_and_psi_stubs.md), a dependency on
[`DumbService`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.kt) must be added.
