<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Indexing and PSI Stubs

<link-summary>The indexing framework allows to access information about files content without loading them to memory and parsing.</link-summary>

## Indexes

The indexing framework provides a quick way to locate specific elements, e.g., files containing a certain word or methods with a particular name, in large codebases.
Plugin developers can use the existing indexes built by the IDE itself and build and use their own indexes.

It supports two main types of indexes:

* [](file_based_indexes.md)
* [](stub_indexes.md)

File-based indexes are built directly over the content of files.
Stub indexes are built over serialized *stub trees*.
A stub tree for a source file is a subset of its [PSI](psi.md) tree, which contains only externally visible declarations and is serialized in a compact binary format.

Querying a file-based index gets you the set of files matching a specific condition.
Querying a stub index gets you the set of matching PSI elements.
Therefore, custom language plugin developers typically use [stub indexes](stub_indexes.md) in their plugin implementations.

> [Index Viewer](https://plugins.jetbrains.com/plugin/13029-index-viewer/) plugin can be used to inspect indexes' contents and properties.

## Dumb Mode

Indexing is a potentially lengthy process.
It's performed in the background, and during this time, all IDE features are restricted to the ones that don't require indexes: basic text editing, version control, etc.
This restriction is managed by [`DumbService`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.kt).
Violations are reported via [`IndexNotReadyException`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/IndexNotReadyException.java), see its documentation for information on how to adapt callers.

`DumbService` provides API to query whether the IDE is currently in "dumb" mode (where index access is not allowed) or "smart" mode (with all index built and ready to use).
It also provides ways of delaying code execution until indexes are ready.

<video src="https://www.youtube.com/watch?v=ApdNfPuGJRU"/>

_Learn how techniques like dumb mode index access, on-demand indexing, and lightweight heuristics can boost plugin performance and streamline your development process,
all while maintaining robust coding assistance._

### `DumbAware` API

{id="DumbAwareAPI"}

> Use inspection <control>Plugin DevKit | Code | Can be DumbAware</control> (2025.1+) to find implementations
> that can potentially be marked as `DumbAware`.
>
{style="tip" title="Finding Candidates"}

#### Extension Points

Implementations of certain [extension points](plugin_extension_points.md) can be marked as available during Dumb Mode by implementing
[`DumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbAware.java).
Such extension points are marked with the
![DumbAware](https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square)
tag in [](intellij_platform_extension_point_list.md).

Commonly used extension points include [`CompletionContributor`](code_completion.md), [`(External)Annotator`](syntax_highlighting_and_error_highlighting.md#annotator) and various
[run configuration](run_configurations.md) EPs.
Since 2024.2, this includes also [intentions](code_intentions.md) and [quick-fixes](quick_fix.md).

#### Actions

For [actions](action_system.md) available during Dumb Mode, extend [`DumbAwareAction`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/project/DumbAwareAction.java) (do not override `AnAction.isDumbAware()` instead).

#### Other API

Other API might indicate its Dumb Mode compatibility by extending [`PossiblyDumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/PossiblyDumbAware.java).

### Testing

To toggle Dumb Mode for testing purposes, invoke <ui-path>Tools | Internal Actions | Enter/Exit Dumb Mode</ui-path>
while the IDE is running in [internal mode](enabling_internal.md).

## Gists

Sometimes, the following conditions hold:

* The aggregation functionality of file-based indexes is not needed.
  One just needs to calculate some data based on a particular file's contents and cache it on disk.
* Eagerly calculating the data for the entire project during indexing isn't needed (e.g., it slows down the indexing, and/or this data probably will ever be required for a minor subset of all project files).
* The data can be recalculated lazily on request without significant performance penalties.

A [file-based index](file_based_indexes.md) can be used in such cases, but file gists provide a way to perform data calculation lazily, caching on disk, and a more lightweight API.
Please see [`VirtualFileGist`](%gh-ic%/platform/indexing-api/src/com/intellij/util/gist/VirtualFileGist.java) and [`PsiFileGist`](%gh-ic%/platform/indexing-api/src/com/intellij/util/gist/PsiFileGist.java) documentation.

> Note performance implications noted in [`VirtualFileGist`](%gh-ic%/platform/indexing-api/src/com/intellij/util/gist/VirtualFileGist.java) Javadoc.

**Example:**

- `VirtualFileGist`: [`ImageInfoIndex`](%gh-ic%/images/src/org/intellij/images/index/ImageInfoIndex.java) calculating image dimensions/bit depth needed to be displayed in specific parts of UI.
- `PsiFileGist`: [`JavaSimplePropertyGist`](%gh-ic%/java/java-indexing-impl/src/com/intellij/psi/impl/JavaSimplePropertyGist.kt) providing simple properties in Java

## Improving Indexing Performance

### Performance Metrics

<primary-label ref="2020.2"/>

Indexing performance metrics in JSON format are generated in [logs directory](https://intellij-support.jetbrains.com/hc/en-us/articles/206544519-Directories-used-by-the-IDE-to-store-settings-caches-plugins-and-logs) (see [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) for development instance).
These are additionally available in HTML format starting with 2021.1.

### Avoid Using AST

Use [lexer](implementing_lexer.md) information instead of parsed trees if possible.

If impossible, use light AST which doesn't create memory-hungry AST nodes inside, so traversing it might be faster.
Obtain [`LighterAST`](%gh-ic%/platform/core-api/src/com/intellij/lang/LighterAST.java) by casting `FileContent` input parameter to [`PsiDependentFileContent`](%gh-ic%/platform/core-api/src/com/intellij/util/indexing/PsiDependentFileContent.java) and calling `getLighterAST()`.
Make sure to traverse only the nodes you need to.
See also [`RecursiveLighterASTNodeWalkingVisitor`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/RecursiveLighterASTNodeWalkingVisitor.java) and [`LightTreeUtil`](%gh-ic%/platform/core-impl/src/com/intellij/psi/impl/source/tree/LightTreeUtil.java) for useful utility methods.

For [stub index](stub_indexes.md), implement [`LightStubBuilder`](%gh-ic%/platform/core-impl/src/com/intellij/psi/stubs/LightStubBuilder.java).

If a custom language contains lazy-parseable elements that never or rarely contain any stubs, consider implementing [`StubBuilder.skipChildProcessingWhenBuildingStubs()`](%gh-ic%/platform/core-api/src/com/intellij/psi/StubBuilder.java) (preferably using Lexer/node text).

For indexing XML, also consider using [`NanoXmlUtil`](%gh-ic%/platform/indexing-impl/src/com/intellij/util/xml/NanoXmlUtil.java).

### Shared Project Indexes

For bigger projects, building and providing pre-built shared project indexes can be beneficial, see [Shared project indexes](https://www.jetbrains.com/help/idea/shared-indexes.html#project-shared-indexes).
See also [IntelliJ Shared Indexes Tool Example](https://github.com/JetBrains/intellij-shared-indexes-tool-example).
