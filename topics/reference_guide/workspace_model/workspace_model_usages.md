<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Usage Examples

<primary-label ref="2024.2"/>

<link-summary>Workspace Model usages</link-summary>

See also [](workspace_model_entity_mutation.md) and [](workspace_model_entity_read.md) topics.

## Module

### Search Module by Name

```kotlin
val moduleId = ModuleId("moduleName")
val entityStorage: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
entityStorage.resolve(moduleId)
```

### Rename Module

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val moduleEntity: ModuleEntity =
  workspaceModel.currentSnapshot.resolve(ModuleId(moduleName))
writeAction {
  workspaceModel.update("Change module name") { builder ->
    builder.modifyModuleEntity(moduleEntity) {
      this.name = newModuleName
    }
  }
}
```

### `ModuleEntity` Creation

Creating a new
[`ModuleEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/module.kt),
the legacy bridge will be created by the platform.
An important part here is the entity source.
To serialize an entity in project configuration files under the <path>.idea</path> folder, use
[`JpsProjectFileEntitySource`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/jpsEntitySources.kt).

<tabs>

<tab title="2024.3+">

```kotlin
import com.intellij.workspaceModel.ide.legacyBridge.LegacyBridgeJpsEntitySourceFactory
// ...

val workspaceModel = WorkspaceModel.getInstance(project)
val moduleId = ModuleId(moduleName)
if (moduleId in workspaceModel.currentSnapshot) {
  // Module with such `ModuleId` already exists
  ...
}

val baseModuleDir = workspaceModel.getVirtualFileUrlManager()
  .getOrCreateFromUrl("file://foo/bar")
val moduleEntitySource =
    LegacyBridgeJpsEntitySourceFactory.getInstance(project)
        .createEntitySourceForModule(baseModuleDir, null)
WorkspaceModel.getInstance(project).update("Add new module") { builder ->
  val moduleEntity =
      ModuleEntity(moduleName, emptyList(), moduleEntitySource)
  builder.addEntity(moduleEntity)
}
```

</tab>

<tab title="2024.2">

```kotlin
import com.intellij.workspaceModel.ide.impl.LegacyBridgeJpsEntitySourceFactory
// ...

val workspaceModel = WorkspaceModel.getInstance(project)
val moduleId = ModuleId(moduleName)
if (moduleId in workspaceModel.currentSnapshot) {
  // Module with such `ModuleId` already exists
  ...
}

val baseModuleDir = workspaceModel.getVirtualFileUrlManager()
  .getOrCreateFromUrl("file://foo/bar")
val moduleEntitySource =
    LegacyBridgeJpsEntitySourceFactory
        .createEntitySourceForModule(project, baseModuleDir, null)
WorkspaceModel.getInstance(project).update("Add new module") { builder ->
  val moduleEntity =
      ModuleEntity(moduleName, emptyList(), moduleEntitySource)
  builder.addEntity(moduleEntity)
}
```

<snippet id="LegacyBridgeJpsEntitySourceFactory-internal-note">
> Note that [`LegacyBridgeJpsEntitySourceFactory`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/ide/impl/LegacyBridgeJpsEntitySourceFactory.kt) is an internal API.
> It is exceptionally allowed to use it in plugins.
>
{style="tip"}
</snippet>

</tab>

</tabs>

### Add Library Dependency to Module

A project-level library is added as a dependency to the module.

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val moduleEntity = workspaceModel.currentSnapshot
  .resolve(ModuleId(moduleName)) ?: return
workspaceModel.update("Adding new module dependency") { builder ->
  builder.modifyModuleEntity(moduleEntity) {
    val libraryId = LibraryId(
      libraryName,
      LibraryTableId.ProjectLibraryTableId
    )
    this.dependencies.add(
      LibraryDependency(libraryId, false, DependencyScope.COMPILE)
    )
  }
}
```

### Searching for Module-Containing Path

Search for content roots and source roots with required URLs and determine the `ModuleEntity` to which they belong.

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val virtualFileUrl = workspaceModel.getVirtualFileUrlManager()
  .getOrCreateFromUrl("file://foo/bar/src")

workspaceModel.currentSnapshot.getVirtualFileUrlIndex()
  .findEntitiesByUrl(virtualFileUrl)
  .mapNotNull {
    if (it is SourceRootEntity) {
      it.contentRoot.module
    } else if (it is ContentRootEntity) {
      it.module
    } else {
      null
    }
  }
```

## Library

### `LibraryEntity` Creation

Creating a new
[`LibraryEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/dependencies.kt),
the legacy bridge will be created by the platform.


<tabs>

<tab title="2024.3+">

```kotlin
import com.intellij.workspaceModel.ide.legacyBridge.LegacyBridgeJpsEntitySourceFactory
// ...

val currentSnapshot = WorkspaceModel.getInstance(project).currentSnapshot
val libraryTableId = LibraryTableId.ProjectLibraryTableId

val libraryId = LibraryId(libraryName, libraryTableId)
if (libraryId in currentSnapshot) {
  // Library with such `LibraryId` already exist
  ...
}

val libraryEntitySource =
    LegacyBridgeJpsEntitySourceFactory.getInstance(project)
        .createEntitySourceForProjectLibrary(null)
val libraryEntity = LibraryEntity(
  libraryName,
  libraryTableId, emptyList(),
  libraryEntitySource
)
WorkspaceModel.getInstance(project).update("Add new library") { builder ->
  builder.addEntity(libraryEntity)
}
```

</tab>

<tab title="2024.2">

```kotlin
import com.intellij.workspaceModel.ide.impl.LegacyBridgeJpsEntitySourceFactory
// ...

val currentSnapshot = WorkspaceModel.getInstance(project).currentSnapshot
val libraryTableId = LibraryTableId.ProjectLibraryTableId

val libraryId = LibraryId(libraryName, libraryTableId)
if (libraryId in currentSnapshot) {
  // Library with such `LibraryId` already exist
  ...
}

val libraryEntitySource =
    LegacyBridgeJpsEntitySourceFactory
        .createEntitySourceForProjectLibrary(project, null)
val libraryEntity = LibraryEntity(
  libraryName,
  libraryTableId, emptyList(),
  libraryEntitySource
)
WorkspaceModel.getInstance(project).update("Add new library") { builder ->
  builder.addEntity(libraryEntity)
}
```

<include from="workspace_model_usages.md" element-id="LegacyBridgeJpsEntitySourceFactory-internal-note"></include>

</tab>

</tabs>

### Searching for Library by Root Type and URL

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val virtualFileUrlManager = workspaceModel.getVirtualFileUrlManager()
// URL that we will look for in library entities
val virtualFileUrl = virtualFileUrlManager
  .getOrCreateFromUrl("file://foo/bar")
workspaceModel.currentSnapshot.getVirtualFileUrlIndex()
  .findEntitiesByUrl(virtualFileUrl).filterIsInstance<LibraryEntity>().filter {
    it.roots.any {
      it.url == virtualFileUrl &&
              it.type == LibraryRootTypeId.SOURCES
    }
  }
```

## Roots

### Add Content Root and Source Root for Module

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val virtualFileUrlManager = workspaceModel.getVirtualFileUrlManager()

val moduleEntity = workspaceModel.currentSnapshot
  .resolve(ModuleId(moduleName)) ?: ...
val contentRootUrl = virtualFileUrlManager
  .getOrCreateFromUrl("file://foo/bar")
val sourceRootUrl = virtualFileUrlManager
  .getOrCreateFromUrl("file://foo/bar/src")
workspaceModel.update("Adding source root") { builder ->
  val contentRootEntity = ContentRootEntity(contentRootUrl, emptyList(), moduleEntity.entitySource) {
    val sourceRootEntity =
      SourceRootEntity(
        sourceRootUrl,
        SourceRootTypeId("java-source"),
        moduleEntity.entitySource
      )
    this.sourceRoots = mutableListOf(sourceRootEntity)
  }
  builder.modifyModuleEntity(moduleEntity) {
    this.contentRoots = mutableListOf(contentRootEntity)
  }
}
```

### Adding Different Types of Library Roots

Two library roots of different types are defined, and a new exclude root is added.

```kotlin
val workspaceModel = WorkspaceModel.getInstance(project)
val virtualFileUrlManager = workspaceModel.getVirtualFileUrlManager()
val libraryEntity = workspaceModel.currentSnapshot
  .resolve(
    LibraryId(
      libraryName,
      LibraryTableId.ProjectLibraryTableId
    )
  ) ?: ...

val sourceRoot = LibraryRoot(
  virtualFileUrlManager
    .getOrCreateFromUrl("file://foo/bar"), LibraryRootTypeId.SOURCES
)
val compiledRoot = LibraryRoot(
  virtualFileUrlManager
    .getOrCreateFromUrl("file://foo/baz"), LibraryRootTypeId.COMPILED
)
workspaceModel.update("Adding library roots") { builder ->
  builder.modifyLibraryEntity(libraryEntity) {
    this.roots = mutableListOf(sourceRoot, compiledRoot)
    // Adding new exclude root
    val virtualFileUrl = virtualFileUrlManager
      .getOrCreateFromUrl("file://foo/out")
    this.excludedRoots =
      excludedRoots + ExcludeUrlEntity(virtualFileUrl, this.entitySource)
  }
}
```

## Miscellaneous

### Migration of Caches Relying on `Library`/`Module`

Using `Library` or `Module` as a key on maps has a number of disadvantages:

- Can’t be used as a key for collections that rely on hashcode calculation, as these objects are mutable by their nature.
- The fact that they extend `Disposable` (see [](disposers.md)) imposes additional difficulties.

To eliminate these shortcomings, use
[`EntityPointer`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityPointer.kt).
It represents a pointer to an entity which can be stored anywhere.
The pointer can be obtained via
[`WorkspaceEntity.createPointer()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt).
An instance of this class stores an internal ID of the entity and doesn't contain the pointer to the original storage, so it's ok to
store them in long-living data structures as it won't create a memory leak.

The pointer will resolve to the same entity for the same storage and will survive modifications.
If the entity is removed or replaced by a different one via
[`MutableEntityStorage.replaceBySource()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/MutableEntityStorage.kt),
the pointer may either resolve to `null` or to a completely different entity which reused the same internal ID.
To be sure that the pointer resolves to the original entity, also [subscribe to changes](workspace_model_event_listening.md) in the storage.

