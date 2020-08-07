---
title: Indexing and PSI Stubs
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Indices

The indexing framework provides a quick way to locate certain elements, e.g. files containing a certain word or methods with a particular name, in large code bases. Plugin developers can use the existing indexes built by the IDE itself, as well as build and use their own indexes.

It supports two main types of indexes:

* [File-based indices](/basics/indexing_and_psi_stubs/file_based_indexes.md)
* [Stub indices](/basics/indexing_and_psi_stubs/stub_indexes.md)

File-based indexes are built directly over the content of files. Stub indexes are built over serialized *stub trees*. A stub tree for a source file is a subset of its PSI tree which contains only externally visible declarations and is serialized in a compact binary format.

Querying a file-based index gets you the set of files matching a certain condition. Querying a stub index gets you the set of matching PSI elements. Therefore, custom language plugin developers should typically use stub indexes in their plugin implementations.

> **TIP** [Indices Viewer](https://plugins.jetbrains.com/plugin/13029-indices-viewer/) is a plugin that helps inspecting indices' contents and properties. 
Please see also [Improving indexing performance](/reference_guide/performance/performance.md#improving-indexing-performance).

## Dumb Mode

Indexing is a potentially long process. It's performed in background, and during this time, IDE's features are restricted to the ones that don't require index: basic text editing, version control etc. This restriction is managed by [`DumbService`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbService.java).

`DumbService` provides API to query whether the IDE is currently in "dumb" mode (where index access is not allowed) or "smart" mode (with all index built and ready to use). It also provides ways of delaying code execution until indices are ready. Please see its javadoc for more details.

## Gists

Sometimes, the following conditions hold:

* the aggregation functionality of file-based indices is not needed. One just needs to calculate some data based on particular file's contents, and cache it on disk
* eagerly calculating the data for the entire project during indexing isn't needed (e.g. it slows down the indexing, and/or this data probably will ever be needed for a minor subset of all project files)
* the data can be recalculated lazily on request without major performance penalties

In such cases, file-based index can be used, but file gists provide a way to perform data calculation lazily, caching on disk, and a more lightweight API. Please see [`VirtualFileGist`](upsource:///platform/indexing-api/src/com/intellij/util/gist/VirtualFileGist.java) and [`PsiFileGist`](upsource:///platform/indexing-api/src/com/intellij/util/gist/PsiFileGist.java) documentation.
