[//]: # (title: Internal API Migration)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page lists commonly used API annotated with [`org.jetbrains.annotations.ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) which indicates it's _private API_ and must not be used outside of IntelliJ Platform itself:

 > _ApiStatus.Internal Javadoc_:
 >
 > Indicates that the annotated element (class, method, field, etc.) **must not be considered as a public API**. It's made visible to allow
 > usages in other packages of the declaring library, but it **must not be used outside of that library**. Such elements
 > may be renamed, changed or removed in future versions.

Such violations are reported from [Plugin Verifier](api_changes_list.md#plugin-verifier) and are highlighted in the IDE using [dedicated inspection](api_changes_list.md#ide-support).

Each entry is mapped to its corresponding _Replacement_, pointing to recommended API.

 > The list is not complete and will be updated continuously. Please check corresponding code documentation when encountering API not listed below.
 >
 {type="tip"}

## IntelliJ Platform

| Internal API                                                         | Replacement                                                                                                                                                                     |
|----------------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `com.intellij.openapi.module.Module.getModuleFile()`                 | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java#L47)                                        |
| `com.intellij.openapi.module.Module.getModuleFilePath()`             | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java#L47)                                        |
| `com.intellij.openapi.module.Module.getModuleTypeName()`             | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java#L180)                                       |
| `com.intellij.openapi.module.ModuleTypeManager.registerModuleType()` | Use `com.intellij.moduleType` extension point instead, [`ModuleType`](upsource:///platform/lang-core/src/com/intellij/openapi/module/ModuleType.java)                           |
| `com.intellij.util.PlatformUtils`                                    | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/util/PlatformUtils.java)                                               |
| `com.intellij.util.pico.DefaultPicoContainer`                        | Use [extension points](plugin_extensions.md) and [services](plugin_services.md)                                                                                                 |
| `com.intellij.ide.ApplicationLoadListener`                           | See [](plugin_components.md#application-startup)                                                                                                                                |
| `com.intellij.openapi.actionSystem.AnAction.applyTextOverride()`     | [](basic_action_system.md#setting-the-override-text-element)                                                                                                                    |
| `com.intellij.psi.search.FileTypeIndex.NAME`                         | Use static methods in `FileTypeIndex` directly                                                                                                                                  |
| `com.intellij.psi.tree.IElementType.getDebugName()`                  | Override/use `toString()`                                                                                                                                                       |
| `com.intellij.util.indexing.IndexingDataKeys`                        | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/core-impl/src/com/intellij/util/indexing/IndexingDataKeys.java)                                  |
| `com.intellij.openapi.util.IconLoader.LazyIcon`                      | Use `com.intellij.openapi.util.IconLoader.createLazy()`                                                                                                                         |
| `com.intellij.openapi.util.IconLoader.CachedImageIcon`               | Use methods exposed in `com.intellij.openapi.util.IconLoader`                                                                                                                   |
| `com.intellij.ui.components.ScrollBarPainter`                        | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/ui/components/ScrollBarPainter.java)                               |
| `com.intellij.ide.plugins.PluginManager.getLogger()`                 | Use own logger [](ide_infrastructure.md#logging)                                                                                                                                |
| `com.intellij.openapi.actionSystem.impl.EdtDataContext`              | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/EdtDataContext.java)                    |
| `com.intellij.openapi.application.PathMacros.setMacro()`             | Use `com.intellij.pathMacroContributor` extension point, [`PathMacroContributor`](upsource:///platform/core-api/src/com/intellij/openapi/application/PathMacroContributor.java) |
| `com.intellij.openapi.roots.impl.libraries.ProjectLibraryTable`      | Use `com.intellij.openapi.roots.libraries.LibraryTablesRegistrar.getLibraryTable()` instead                                                                                     |
| `com.intellij.openapi.vfs.CompactVirtualFileSet`                     | Use `com.intellij.openapi.vfs.VfsUtilCore#createCompactVirtualFileSet()`                                                                                                        |
| `com.intellij.openapi.util.BuildNumber.currentVersion()`             | Use `com.intellij.openapi.application.ApplicationInfo.getBuild()`                                                                                                               |
| `com.intellij.codeInsight.navigation.UtilKt.targetPresentation()`    | [See Doc](https://github.com/JetBrains/intellij-community/blob/master/platform/lang-impl/src/com/intellij/codeInsight/navigation/util.kt)                                       |

## Plugins

### Database Plugin

| Internal API                                          | Replacement                                                                                                                                   |
|-------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| `com.intellij.database.psi.DbDataSource.getDelegate() | For connection config use `DbDataSource#getConnectionConfig()`, for `LocalDataSource` use `DbImplUtil#getMaybeLocalDataSource(DasDataSource)` |
