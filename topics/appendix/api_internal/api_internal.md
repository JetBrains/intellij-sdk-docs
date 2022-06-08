[//]: # (title: Internal API Migration)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Lists private API annotated with ApiStatus.Internal and corresponding replacement.</excerpt>

This page lists commonly used API annotated with [`org.jetbrains.annotations.ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) which indicates it is _private API_ and must not be used outside of IntelliJ Platform itself:

> _`ApiStatus.Internal` Javadoc:_
>
> Indicates that the annotated element (class, method, field, etc.) **must not be considered as a public API**. It's made visible to allow
> usages in other packages of the declaring library, but it **must not be used outside of that library**. Such elements
> may be renamed, changed or removed in future versions.

Such violations are reported from [Plugin Verifier](api_changes_list.md#plugin-verifier) and are highlighted in the IDE using [dedicated inspection](api_changes_list.md#ide-support).

Each entry is mapped to its corresponding _Replacement_, pointing to recommended API.

> The list is not complete and will be updated continuously. Please check corresponding code documentation when encountering any API not listed below.
>
> In some cases, such documentation might not be available inside the IDE for the current target plaform version. Please use <control>Go to file</control> to browse the latest version in the [intellij-community](https://github.com/jetbrains/intellij-community) GitHub repository instead.
>
> Please use the feedback form on the bottom of this page if you encounter missing or unclear information.
>
{type="tip"}

## IntelliJ Platform

| Internal API                             | Replacement                                                                                                                                                                     |
|------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `AnAction.applyTextOverride()`           | [](basic_action_system.md#setting-the-override-text-element)                                                                                                                    |
| `ApplicationLoadListener`                | See [](plugin_components.md#application-startup)                                                                                                                                |
| `BuildNumber.currentVersion()`           | Use `ApplicationInfo.getBuild()`                                                                                                                                                |
| `CompactVirtualFileSet`                  | Use `VfsUtilCore.createCompactVirtualFileSet()`                                                                                                                                 |
| `DefaultPicoContainer`                   | Use [extension points](plugin_extensions.md) and [services](plugin_services.md)                                                                                                 |
| `EdtDataContext`                         | [See Doc](upsource:///platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/EdtDataContext.java)                                                                    |
| `FileTypeIndex.NAME`                     | Use static methods in `FileTypeIndex` directly                                                                                                                                  |
| `IElementType.getDebugName()`            | Override/use `IElementType.toString()`                                                                                                                                          |
| `IconLoader.CachedImageIcon`             | Use methods exposed in `IconLoader`                                                                                                                                             |
| `IconLoader.LazyIcon`                    | Use `IconLoader.createLazy()`                                                                                                                                                   |
| `IndexingDataKeys`                       | [See Doc](upsource:///platform/core-impl/src/com/intellij/util/indexing/IndexingDataKeys.java)                                                                                  |
| `Module.getModuleFile()`                 | [See Doc](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                            |
| `Module.getModuleFilePath()`             | [See Doc](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                            |
| `Module.getModuleTypeName()`             | [See Doc](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                            |
| `ModuleTypeManager.registerModuleType()` | Use `com.intellij.moduleType` extension point instead, [`ModuleType`](upsource:///platform/lang-core/src/com/intellij/openapi/module/ModuleType.java)                           |
| `PathMacros.setMacro()`                  | Use `com.intellij.pathMacroContributor` extension point, [`PathMacroContributor`](upsource:///platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| `PlatformUtils`                          | [See Doc](upsource:///platform/core-api/src/com/intellij/util/PlatformUtils.java)                                                                                               |
| `PluginClassLoader`                      | Cast to [`PluginAwareClassLoader`](upsource:///platform/extensions/src/com/intellij/ide/plugins/cl/PluginAwareClassLoader.java)                                                 |
| `PluginManager.getLogger()`              | Use own logger, see [](ide_infrastructure.md#logging)                                                                                                                           |
| `ProjectLibraryTable`                    | Use `LibraryTablesRegistrar.getLibraryTable()`                                                                                                                                  |
| `SVGLoader`                              | Use `ImageLoader.loadFromResource()`                                                                                                                                            |
| `ScrollBarPainter`                       | [See Doc](upsource:///platform/platform-api/src/com/intellij/ui/components/ScrollBarPainter.java)                                                                               |
| `UtilKt.targetPresentation()`            | [See Doc](upsource:///platform/lang-impl/src/com/intellij/codeInsight/navigation/util.kt)                                                                                       |

## Plugins

### Database Plugin

| Internal API                 | Replacement                                                                                                                                   |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| `DbDataSource.getDelegate()` | For connection config use `DbDataSource.getConnectionConfig()`, for `LocalDataSource` use `DbImplUtil.getMaybeLocalDataSource(DasDataSource)` |


## Exceptions

The API listed in this table is currently (or was previously) marked with `@ApiStatus.Internal`, but its status has changed in the meantime (or will change).
Therefore, any reported violations can be disregarded.

| Internal API                                                                                                                                           | Note                                            |
|--------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------|
| [`BundleBase`](upsource:///platform/util/src/com/intellij/BundleBase.java)                                                                             | Made public in 2022.1                           |
| [`IdFilter`](upsource:///platform/indexing-api/src/com/intellij/util/indexing/IdFilter.java)                                                           | Reverted in 2021.2/3                            |
| [`RunAnythingCommandLineProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingCommandLineProvider.kt) | Made public in 2021.3                           |
| `org.jetbrains.yaml.meta.*`                                                                                                                            | YAML Metadata API will be made public in 2022.2 |
| `PhpExpectedFunctionArgument`                                                                                                                          | Made public in 2022.1                           |

> Missing entries? Please let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please leave your email in case we need more details.
>
{type="note"}
