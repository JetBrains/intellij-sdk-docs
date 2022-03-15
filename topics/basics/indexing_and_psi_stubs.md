[//]: # (title: Indexing and PSI Stubs)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Indexes

The indexing framework provides a quick way to locate specific elements, e.g., files containing a certain word or methods with a particular name, in large codebases.
Plugin developers can use the existing indexes built by the IDE itself and build and use their own indexes.

It supports two main types of indexes:

* [](file_based_indexes.md)
* [](stub_indexes.md)

File-based indexes are built directly over the content of files.
Stub indexes are built over serialized *stub trees*.
A stub tree for a source file is a subset of its PSI tree, which contains only externally visible declarations and is serialized in a compact binary format.

Querying a file-based index gets you the set of files matching a specific condition.
Querying a stub index gets you the set of matching PSI elements.
Therefore, custom language plugin developers typically use stub indexes in their plugin implementations.

> [Index Viewer](https://plugins.jetbrains.com/plugin/13029-index-viewer/) plugin can be used to inspect indexes' contents and properties.
>
{type="tip"}

## Dumb Mode

Indexing is a potentially lengthy process.
It's performed in the background, and during this time, IDE features are restricted to the ones that don't require index: basic text editing, version control, etc.
This restriction is managed by [`DumbService`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbService.java).
Violations are reported via [`IndexNotReadyException`](upsource:///platform/core-api/src/com/intellij/openapi/project/IndexNotReadyException.java), please see its javadoc on how to adapt callers.

`DumbService` provides API to query whether the IDE is currently in "dumb" mode (where index access is not allowed) or "smart" mode (with all index built and ready to use).
It also provides ways of delaying code execution until indexes are ready.
Please see its JavaDoc for more details.

## Gists

Sometimes, the following conditions hold:

* The aggregation functionality of file-based indexes is not needed.
  One just needs to calculate some data based on a particular file's contents and cache it on disk.
* Eagerly calculating the data for the entire project during indexing isn't needed (e.g., it slows down the indexing, and/or this data probably will ever be required for a minor subset of all project files).
* The data can be recalculated lazily on request without significant performance penalties.

A [file-based index](file_based_indexes.md) can be used in such cases, but file gists provide a way to perform data calculation lazily, caching on disk, and a more lightweight API.
Please see [`VirtualFileGist`](upsource:///platform/indexing-api/src/com/intellij/util/gist/VirtualFileGist.java) and [`PsiFileGist`](upsource:///platform/indexing-api/src/com/intellij/util/gist/PsiFileGist.java) documentation.

**Example:**

- `VirtualFileGist`: [`ImageInfoIndex`](upsource:///images/src/org/intellij/images/index/ImageInfoIndex.java) calculating image dimensions/bit depth needed to be displayed in specific parts of UI.
- `PsiFileGist`: [`JavaSimplePropertyGist`](upsource:///java/java-indexing-impl/src/com/intellij/psi/impl/JavaSimplePropertyGist.kt) providing simple properties in Java

## Improving Indexing Performance

### Performance Metrics

Indexing performance metrics in JSON format are generated in [logs directory](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs) (see [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) for development instance) in 2020.2 and later.
These are additionally available in HTML format starting with 2021.1.

### Avoid Using AST

Use [lexer](implementing_lexer.md) information instead of parsed trees if possible.

If impossible, use light AST which doesn't create memory-hungry AST nodes inside, so traversing it might be faster.
Obtain [`LighterAST`](upsource:///platform/core-api/src/com/intellij/lang/LighterAST.java) by casting `FileContent` input parameter to [`PsiDependentFileContent`](upsource:///platform/core-api/src/com/intellij/util/indexing/PsiDependentFileContent.java) and calling `getLighterAST()`.
Make sure to traverse only the nodes you need to.
See also [`LighterASTNodeVisitor`](upsource:///platform/core-impl/src/com/intellij/psi/impl/source/tree/LighterASTNodeVisitor.java) and [`LightTreeUtil`](upsource:///platform/core-impl/src/com/intellij/psi/impl/source/tree/LightTreeUtil.java) for useful utility methods.

For [stub index](stub_indexes.md), implement [`LightStubBuilder`](upsource:///platform/core-impl/src/com/intellij/psi/stubs/LightStubBuilder.java).

If a custom language contains lazy-parseable elements that never or rarely contain any stubs, consider implementing [`StubBuilder.skipChildProcessingWhenBuildingStubs()`](upsource:///platform/core-api/src/com/intellij/psi/StubBuilder.java) (preferably using Lexer/node text).

For indexing XML, also consider using [`NanoXmlUtil`](upsource:///platform/indexing-impl/src/com/intellij/util/xml/NanoXmlUtil.java).

### Consider Prebuilt Stubs

If your language has a massive standard library, which is mostly the same for all users, you can avoid stub-indexing it in each installation by providing prebuilt stubs with your distribution.
See [`PrebuiltStubsProvider`](upsource:///platform/indexing-impl/src/com/intellij/psi/stubs/PrebuiltStubs.kt) extension.
