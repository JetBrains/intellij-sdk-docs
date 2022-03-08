[//]: # (title: Optimizing Performance)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> [IDE Perf](https://plugins.jetbrains.com/plugin/15104-ide-perf) plugin provides on-the-fly performance diagnostic tools, including a dedicated view for [`CachedValue`](#cache-results-of-heavy-computations) metrics.
>
{type="tip"}

> See also [](indexing_and_psi_stubs.md#improving-indexing-performance)
>
{type="note"}

## Working with PSI Efficiently

#### Avoid Expensive Methods of `PsiElement`

Avoid `PsiElement`'s methods which are expensive with deep trees.

`getText()` traverses the whole tree under the given element and concatenates strings, consider `textMatches()` instead.

`getTextRange()`, `getContainingFile()`, and `getProject()` traverse the tree up to the file, which can be long in very nested trees.
If you only need PSI element length, use `getTextLength()`.

File and project often can be computed once per some analysis and then stored in fields or passed via parameters.

Additionally, methods such as `getText()`, `getNode()`, or `getTextRange()`, need the AST, obtaining which can be quite an expensive operation.
See below.

#### Avoid Using Many PSI Trees/Documents

Avoid loading too many parsed trees or documents into memory at the same time.
Ideally, only AST nodes from files open in the editor should be present in the memory.
Everything else, even if it's needed for resolve/highlighting purposes, can be accessed via PSI interfaces, but its implementations should [use stubs](stub_indexes.md) underneath, which are less CPU- and memory-expensive.

If stubs don't suit your case well (e.g., the information you need is large and/or very rarely needed, or you're developing a plugin for a language whose PSI you don't control), you can create a [custom index or gist](indexing_and_psi_stubs.md).

To ensure you're not loading AST accidentally, you can use [`AstLoadingFilter`](upsource:///platform/core-api/src/com/intellij/util/AstLoadingFilter.java) in production and `PsiManagerEx.setAssertOnFileLoadingFilter()` in tests.

The same applies to documents: only the ones opened in editors should be loaded.
Usually, you shouldn't need document contents (as most information can be retrieved from PSI).
If you nevertheless need documents, consider saving the information you need to provide in a [custom index or gist](indexing_and_psi_stubs.md) to get it more cheaply later.
If you still need documents, then at least ensure you load them one by one and don't hold them on strong references to let GC free the memory as quickly as possible.

#### Cache Results of Heavy Computations

Method calls such as `PsiElement.getReference(s)`, `PsiReference.resolve()` (and `multiResolve()` and other equivalents) or computation of expression types, type inference results, control flow graphs, etc. can be expensive.
To avoid paying this cost several times, the result of such computation can be cached and reused.
Usually, [`CachedValue`](upsource:///platform/core-api/src/com/intellij/psi/util/CachedValue.java) works well for this purpose.

If the information you cache depends only on a subtree of the current PSI element (and nothing else: no resolve results or other files), you can cache it in a field in that `PsiElement` and drop the cache in an override of `ASTDelegatePsiElement.subtreeChanged()`.

## Avoiding UI Freezes

#### Do not Perform Long Operations in UI Thread

In particular, don't traverse VFS, parse PSI, resolve references or query `FileBasedIndex`.

There are cases when the platform itself invokes such expensive code (e.g., resolve in `AnAction.update()`).
We're trying to eliminate them.
Meanwhile, you can try to speed up what you can in your plugin, it'll be beneficial anyway, as it'll also improve background highlighting performance.

`WriteAction`s currently have to happen on UI thread, so to speed them up, you can try moving as much as possible out of write action into a preparation step which can be then invoked in background (e.g., using `ReadAction.nonBlocking()`).

Don't do anything expensive in event listeners.
Ideally, you should only clear some caches.
You can also schedule background processing of events, but be prepared that some new events might be delivered before your background processing starts, and thus the world might have changed by that moment or even in the middle of background processing.
Consider using [`MergingUpdateQueue`](upsource:///platform/ide-core/src/com/intellij/util/ui/update/MergingUpdateQueue.java) and `ReadAction.nonBlocking()` to mitigate these issues.

Massive batches of VFS events can be pre-processed in background, see [`AsyncFileListener`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/AsyncFileListener.java) (2019.2 or later).

#### Don't block EDT by long non-cancellable `ReadAction`s in background threads

See [General Threading Rules](general_threading_rules.md), especially its section on [Read Action Cancellability](general_threading_rules.md#read-action-cancellability).
