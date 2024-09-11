<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Entity Read

<primary-label ref="2024.2"/>

<link-summary>Entity read of Workspace Entities</link-summary>

## Entities Querying

[`EntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt)
provides the ability to query data from
[`WorkspaceModel`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt).

### Basic request

To request all entities of the concrete type use
[`EntityStorage.entities()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt).

```kotlin
// Take a copy of the current storage
val currentSnapshot: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
// Search for the module with the given name throughout the whole list
val moduleEntity: ModuleEntity? =
  currentSnapshot.entities(ModuleEntity::class.java)
    .find { it.name == moduleName }

// Extension function shortcut for
// EntityStorage.entities(E::class.java)
currentSnapshot.entities<ModuleEntity>()
```

## Index-Based Queries

Throughout the entity's lifecycle, a number of internal indexes are created and maintained to speed up certain types of searches.

### `EntitySource` Index

<tldr>

**Reference**: [](workspace_model_entity_properties.md#entitysource)

</tldr>

This index stores information about entities'
[`EntitySource`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntitySource.kt)
and allows obtaining all entities matching the given predicate.

```kotlin
val currentSnapshot: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
// Querying all entities of different types
// but with concrete entitySource
currentSnapshot.entitiesBySource { it is GradleEntitySource }.forEach {
  // ...
}
```

### `VirtualFileUrl` Index

<tldr>

**Reference**: [](workspace_model_entity_properties.md#virtualfileurl)

</tldr>

[`EntityStorage.getVirtualFileUrlIndex()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt)
provides a way to quickly find entities referring to a particular
[`VirtualFileUrl`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/url/VirtualFileUrl.java).

```kotlin
val currentSnapshot: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
// Searching all entities with the given path
currentSnapshot.getVirtualFileUrlIndex()
  .findEntitiesByUrl(virtualFileUrl)
```

### `SymbolicEntityId` Index

<tldr>

**Reference**: [](workspace_model_entity_properties.md#symbolicentityid)

</tldr>

Allows searching for an entity by its
[`SymbolicEntityId`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/SymbolicEntityId.kt).

```kotlin
val moduleId = ModuleId("moduleName")
val entityStorage: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
entityStorage.resolve(moduleId)
```

### Arbitrary Data

Provides a way to associate
[`WorkspaceEntity`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt)
with an external data type.
The association survives modifications of an entity, and is automatically removed when the entity is deleted.
Use
[`MutableEntityStorage.getMutableExternalMapping()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/MutableEntityStorage.kt)
to fill the index and
[`EntityStorage.getExternalMapping()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt)
to access it.

#### Example

In the following example, data stored in instances of `Foo` is associated with `ModuleEntity`.

##### Filling the Index

```kotlin
// Сlass whose instances we want to store in the index
data class Foo(val someData: String)
// Unique identifier of the concrete mapping
val externalMappingKey = ExternalMappingKey.create<Foo>("intellij.foo")

val mutableEntityStorage = WorkspaceModel.getInstance(project)
  .currentSnapshot.toBuilder()
// Getting mutable instance of specific mapping
val mutableFooExternalMapping =
  mutableEntityStorage.getMutableExternalMapping(externalMappingKey)

val moduleEntity = mutableEntityStorage
  .entities(ModuleEntity::class.java).single()
// Associate data with specific `ModuleEntity`
mutableFooExternalMapping.addMapping(moduleEntity, Foo("someData"))
```

##### Getting the Data

```kotlin
// Сlass whose instances we want to store in the index
data class Foo(val someData: String)

// Unique identifier of the concrete mapping
val externalMappingKey = ExternalMappingKey.create<Foo>("intellij.foo")

val entityStorage = WorkspaceModel.getInstance(project).currentSnapshot
// Immutable instance of specific mapping
val fooExternalMapping = entityStorage
  .getExternalMapping(externalMappingKey)

val moduleEntity = entityStorage
  .entities(ModuleEntity::class.java).single()
// Getting the data associated with specific `ModuleEntity`
val foo = fooExternalMapping.getDataByEntity(moduleEntity)
```
