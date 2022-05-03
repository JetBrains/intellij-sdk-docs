[//]: # (title: PSI Performance)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

See also [](general_threading_rules.md#avoiding-ui-freezes) and [](indexing_and_psi_stubs.md#improving-indexing-performance).

> [IDE Perf](https://plugins.jetbrains.com/plugin/15104-ide-perf) plugin provides on-the-fly performance diagnostic tools, including a dedicated view for [`CachedValue`](#cache-results-of-heavy-computations) metrics.
>
{type="tip"}

## Avoid Expensive Methods of `PsiElement`

Avoid `PsiElement`'s methods which are expensive with deep trees.

`getText()` traverses the whole tree under the given element and concatenates strings, consider using `textMatches()` instead.

`getTextRange()`, `getContainingFile()`, and `getProject()` traverse the tree up to the file, which can be long in very nested trees.
If you only need PSI element length, use `getTextLength()`.

`getContainingFile()` and `getProject()` often can be computed once per task and then stored in fields or passed via parameters.

Additionally, methods such as `getText()`, `getNode()`, or `getTextRange()`, need the AST, obtaining which can be quite an expensive operation, see next section.

## Avoid Using Many PSI Trees/Documents

Avoid loading too many parsed trees or documents into memory at the same time.
Ideally, only AST nodes from files open in the editor should be present in the memory.
Everything else, even if it's needed for resolve/highlighting purposes, can be accessed via PSI interfaces, but its implementations should [use stubs](stub_indexes.md) underneath, which are less CPU- and memory-expensive.

If stubs don't suit your case well (e.g., the information you need is large and/or very rarely needed, or you're developing a plugin for a language whose PSI you don't control), you can create a [custom index or gist](indexing_and_psi_stubs.md).

To ensure you're not loading AST accidentally, you can use [`AstLoadingFilter`](upsource:///platform/core-api/src/com/intellij/util/AstLoadingFilter.java) in production and `PsiManagerEx.setAssertOnFileLoadingFilter()` in tests.

The same applies to documents: only the ones opened in editors should be loaded.
Usually, you shouldn't need document contents (as most information can be retrieved from PSI).
If you nevertheless need documents, consider saving the information you need to provide in a [custom index or gist](indexing_and_psi_stubs.md) to get it more cheaply later.
If you still need documents, then at least ensure you load them one by one and don't hold them on strong references to let GC free the memory as quickly as possible.

## Cache Results of Heavy Computations

Method calls such as `PsiElement.getReference(s)`, `PsiReference.resolve()` (and `multiResolve()` and other equivalents) or computation of expression types, type inference results, control flow graphs, etc. can be expensive.
To avoid paying this cost several times, the result of such computation can be cached and reused.
Usually, [`CachedValue`](upsource:///platform/core-api/src/com/intellij/psi/util/CachedValue.java) works well for this purpose.

If the information you cache depends only on a subtree of the current PSI element (and nothing else: no resolve results or other files), you can cache it in a field in your `PsiElement` implementation and drop the cache in an override of `ASTDelegatePsiElement.subtreeChanged()`.
