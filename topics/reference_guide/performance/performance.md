[//]: # (title: Optimizing Performance)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> See also [](psi_performance.md) and [](indexing_and_psi_stubs.md#improving-indexing-performance).
>
{type="note"}

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
