<!-- Copyright 2000-2025 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# Internal API Migration

<web-summary>
Lists private APIs and their replacements in IntelliJ Platform and plugins.
</web-summary>

<link-summary>Lists private API annotated with @ApiStatus.Internal/@IntellijInternalApi and corresponding replacement.</link-summary>

This page lists commonly used API annotated with [`@ApiStatus.Internal`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java)
or [`@IntellijInternalApi`](%gh-ic%/platform/util/src/com/intellij/openapi/util/IntellijInternalApi.kt)
which indicates it is _private API_ and must not be used outside of IntelliJ Platform itself:

> Indicates that the annotated element (class, method, field, etc.) **must not be considered as a public API**. It's made visible to allow
> usages in other packages of the declaring library, but it **must not be used outside of that library**. Such elements
> may be renamed, changed, or removed in future versions.
>
{title="ApiStatus.Internal Javadoc"}

Such violations are reported from [](verifying_plugin_compatibility.md#plugin-verifier) and are highlighted in the IDE using a [dedicated inspection](verifying_plugin_compatibility.md#ide-support).

Each entry is mapped to its corresponding _Replacement_, pointing to the recommended API.

<snippet id="notComplete">

> The lists are not complete and will be updated continuously.
>
> Check the corresponding code documentation when encountering any API not listed on this page.
> In some cases, such documentation might not be available inside the IDE for the current target platform version.
> Use <control>Go to file</control> to browse the latest version in the [intellij-community](%gh-ic-master%/) GitHub repository instead.
>
> Use the feedback form at the bottom of this page if you encounter missing or unclear information.
>
{style="note"}

</snippet>

## IntelliJ Platform

| Internal API                                                                  | Replacement                                                                                                                                                                                                                                 |
|-------------------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `AnAction.applyTextOverride()`                                                | [](plugin_configuration_file.md#idea-plugin__actions__action__override-text)                                                                                                                                                                |
| `ApplicationLoadListener`                                                     | See [](plugin_components.md#application-startup)                                                                                                                                                                                            |
| `BuildNumber.currentVersion()`                                                | Use `ApplicationInfo.getBuild()`                                                                                                                                                                                                            |
| `CompactVirtualFileSet`                                                       | Use `VfsUtilCore.createCompactVirtualFileSet()`                                                                                                                                                                                             |
| `DefaultPicoContainer`                                                        | Use [extension points](plugin_extensions.md) and [services](plugin_services.md)                                                                                                                                                             |
| `ExperimentalUI.isNewUI()`                                                    | Use [`NewUI.isEnabled()`](%gh-ic%/platform/platform-api/src/com/intellij/ui/NewUI.java)                                                                                                                                                     |
| `FileTypeIndex.NAME`                                                          | Use static methods in `FileTypeIndex` directly                                                                                                                                                                                              |
| `IElementType.getDebugName()`                                                 | Override/use `IElementType.toString()`                                                                                                                                                                                                      |
| `IconLoader.CachedImageIcon`                                                  | Use methods exposed in `IconLoader`                                                                                                                                                                                                         |
| `IconLoader.LazyIcon`                                                         | Use `IconLoader.createLazy()`                                                                                                                                                                                                               |
| `IndexingDataKeys`                                                            | [See Doc](%gh-ic%/platform/core-impl/src/com/intellij/util/indexing/IndexingDataKeys.java)                                                                                                                                                  |
| `Module.getModuleFile()`                                                      | [See Doc](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                                                                                            |
| `Module.getModuleFilePath()`                                                  | [See Doc](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                                                                                            |
| `Module.getModuleTypeName()`                                                  | [See Doc](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                                                                                            |
| `ModuleTypeManager.registerModuleType()`                                      | Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.moduleType"/></include> instead. [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java)                           |
| `PathMacros.setMacro()`                                                       | Use <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.pathMacroContributor"/></include>. [`PathMacroContributor`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| `PlatformUtils`                                                               | [See Doc](%gh-ic%/platform/core-api/src/com/intellij/util/PlatformUtils.java)                                                                                                                                                               |
| `PluginClassLoader`                                                           | Cast to [`PluginAwareClassLoader`](%gh-ic%/platform/extensions/src/com/intellij/ide/plugins/cl/PluginAwareClassLoader.java)                                                                                                                 |
| `PluginManager.getLogger()`                                                   | Use own logger, see [](ide_infrastructure.md#logging)                                                                                                                                                                                       |
| `PreloadingActivity`                                                          | Use `StartupActivity.Background` ([docs](plugin_components.md#project-open)) with atomic flag to run only once during IDE lifetime                                                                                                          |
| `ProjectLibraryTable`                                                         | Use `LibraryTablesRegistrar.getLibraryTable()`                                                                                                                                                                                              |
| `SVGLoader`                                                                   | Use `ImageLoader.loadFromResource()`                                                                                                                                                                                                        |
| `ScrollBarPainter`                                                            | [See Doc](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/ScrollBarPainter.java)                                                                                                                                               |
| `ToolWindowManager.registerToolWindow(String, RegisterToolWindowTaskBuilder)` | [False positive](https://youtrack.jetbrains.com/issue/MP-6705) from [Plugin Verifier](verifying_plugin_compatibility.md)                                                                                                                    |
| `UtilKt.targetPresentation()`                                                 | [See Doc](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/navigation/util.kt)                                                                                                                                                       |

## Plugins

### Database Plugin

| Internal API                 | Replacement                                                                                                                                   |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| `DbDataSource.getDelegate()` | For connection config use `DbDataSource.getConnectionConfig()`, for `LocalDataSource` use `DbImplUtil.getMaybeLocalDataSource(DasDataSource)` |

## Exceptions

The API listed in this table is currently (or was previously) marked with `@ApiStatus.Internal`, but its status has changed in the meantime (or will change).
Therefore, any reported violations can be disregarded.

| Internal API                                                                                                                                         | Note                                            |
|------------------------------------------------------------------------------------------------------------------------------------------------------|-------------------------------------------------|
| [`AnAction.setShortcutSet()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnAction.java)                                    | Made public in 2023.3                           |
| [`AnimatedIcon.ANIMATION_IN_RENDERER_ALLOWED`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java)                                      | Made public in 2021.3                           |
| [`BaseExpirableExecutor.expireWith()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/BaseExpirableExecutor.java)                    | Made public in 2023.2                           |
| [`BundleBase`](%gh-ic%/platform/util/src/com/intellij/BundleBase.kt)                                                                                 | Made public in 2022.1                           |
| [`CodeVisionPlaceholderCollector`](%gh-ic%/platform/lang-impl/src/com/intellij/codeInsight/codeVision/CodeVisionPlaceholderCollector.kt)             | Made public in 2024.2                           |
| [`IdFilter`](%gh-ic%/platform/indexing-api/src/com/intellij/util/indexing/IdFilter.java)                                                             | Made public in 2021.2/3                         |
| [`HashingStrategy`](%gh-ic%/platform/util/base/src/com/intellij/util/containers/HashingStrategy.java)                                                | Made public in 2023.1                           |
| [`JsonCustomStructureViewFactory`](%gh-ic%/json/src/com/intellij/json/structureView/JsonCustomStructureViewFactory.java)                             | Made public in 2023.2                           |
| [`RunAnythingCommandLineProvider`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingCommandLineProvider.kt)   | Made public in 2021.3                           |
| [`SearchEverywhereFoundElementInfo`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/actions/searcheverywhere/SearchEverywhereFoundElementInfo.java) | Made public in 2023.3                           |
| `PhpExpectedFunctionArgument`                                                                                                                        | Made public in 2022.1                           |
| `org.jetbrains.yaml.meta.*`                                                                                                                          | YAML Metadata API will be made public in 2023.1 |

<include from="api_internal.md" element-id="notComplete"/>
