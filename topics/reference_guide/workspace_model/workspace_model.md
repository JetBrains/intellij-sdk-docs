<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Workspace Model

<primary-label ref="2024.1"/>

<link-summary>Introduction to Workspace Model representing a project's structure</link-summary>

> WIP. [Original WIKI page](https://youtrack.jetbrains.com/articles/IJPL-A-502/WorkspaceModel).
>
> Please discuss in #ijsdk-wsm.
>
{style="warning"}

The Workspace Model represents the project's structure and all its elements, such as [modules](module.md), [libraries](library.md), [SDKs](sdk.md), [facets](facet.md),
and other configurable project components.
It provides a generic storage for entities describing the user's workspace.

## Why a New API?

There are several problems with the approach that has been used to represent the [project model](project_structure.md) (configuration of modules, libraries, facet, artifacts, etc.)
in the IntelliJ Platform for more than 15 years.

### Structure

Different project model parts are stored in separate project-level and module-level services
([`ModuleManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/module/ModuleManager.kt),
[`ModuleRootManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java),
[`LibraryTable`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/LibraryTable.java),
etc.).

They're implemented independently, and the project model has no single entry point.
This leads to code duplication and complicates bulk modifications of project model elements, since you need to manually track modifiable instances of the model elements
or create special wrappers to do it (see [`IdeModifiableModelsProvider`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/IdeModifiableModelsProvider.java)).

### Serialization

There is no way to serialize the project model’s entire content in a binary format to cache it or pass it to another process.

### Reuse

The project model interfaces ([`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java),
[`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java),
[`Facet`](%gh-ic%/platform/lang-core/src/com/intellij/facet/Facet.java),
etc.) are highly coupled with the API that is only available inside the IDE process and its component management system.
It is not possible to reuse those interfaces in other processes (e.g., inside the build process or inside the process that imports data from Gradle)
or when the project hasn't yet been initialized (e.g., inside the <control>New Project</control> dialog).

Thus, separate representations of project model elements are necessary:

- [`JpsProject`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/JpsProject.java),
  [`JpsModule`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/module/JpsModule.java),
  [`JpsLibrary`](%gh-ic%/jps/model-api/src/org/jetbrains/jps/model/library/JpsLibrary.java),
  etc. for the build process
- [`ProjectData`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/project/ProjectData.java),
  [`ModuleData`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/project/ModuleData.java),
  [`LibraryData`](%gh-ic%/platform/external-system-api/src/com/intellij/openapi/externalSystem/model/project/LibraryData.java),
  etc. for the importing process

All these places must be updated everytime something in the project model is changed.

### Flexibility

The project model concepts were created for [IntelliJ IDEA](idea.md) before the IntelliJ Platform accommodated a variety of languages.
Many common interfaces like
[`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java) and
[`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java)
still have Java-specific properties.
This makes it harder to reuse them for other languages and technologies, and there is no simple way to create new language-specific concepts and use them in the model.

## Implementation

Workspace Model introduces a unified data structure that stores the project configuration and doesn't have to be used from the IDE process.
The implementation heavily relies on [Persistent Data Structure](https://en.wikipedia.org/wiki/Persistent_data_structure) that allows using a
model snapshot without fear that it will be modified during an interaction.

## Interoperability with Project Model API

New implementations of the old [project model](project_structure.md) interfaces
([`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java),
[`ModuleManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/module/ModuleManager.kt),
[`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java),
etc.) store their data in
[`WorkspaceModel`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt)
in corresponding entities defined in the `com.intellij.platform.workspace.jps.entities` package.

Implementations of the old interfaces (so-called _legacy bridges_) use entities of these types to store data.

## Basic Use Cases

[`WorkspaceModel`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt) allows performing operations as with the old [Project Model](project_structure.md) API,
but via a single entry point.

### Search Module by Name

<compare type="top-bottom" first-title="Project Model API" second-title="Workspace Model API">

<code-block lang="kotlin">
ModuleManager.getInstance(project).findModuleByName("moduleName")
</code-block>

<code-block lang="kotlin">
val moduleId = ModuleId("moduleName")
val entityStorage: ImmutableEntityStorage =
  WorkspaceModel.getInstance(project).currentSnapshot
entityStorage.resolve(moduleId)
</code-block>

</compare>

[`ImmutableEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt)
is an immutable snapshot of the storage state and isn't affected by the further modifications of the storage.

### Renaming Module

<compare type="top-bottom" first-title="Project Model API" second-title="Workspace Model API">

<code-block lang="kotlin">
readAndWriteAction {
  val moduleManager = ModuleManager.getInstance(project)
  val modifiableModel = moduleManager.getModifiableModel()
  val module = modifiableModel.findModuleByName(moduleName)
  modifiableModel.renameModule(module, newModuleName)
  writeAction {
    modifiableModel.commit()
  }
}

</code-block>

<code-block lang="kotlin">
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
</code-block>

</compare>

[`ModuleEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/module.kt)
entity from the storage that describes configuration of a
[`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)
was created for compatibility with the old API.

All project model elements have a correspondent entity to store the data in Workspace Model, for example:

- [`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java) &rarr; [`LibraryEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/dependencies.kt)
- [`Sdk`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/Sdk.java) &rarr; [`SdkEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/sdk.kt)

All operations can be performed on entities.

[//]: # (todo add links)
More about entity mutation and declaration can be found in correspondent chapters.

[//]: # (todo clarify paragraph)
Use [`WorkspaceModel`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt) to access an instance of this storage used by the IDE process.
Types of entities in the storage are represented by interfaces extending [`WorkspaceEntity`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt).
Their instances are stored in [`VersionedEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/VersionedEntityStorage.kt),
which provides access to the current [`ImmutableEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt)
and allows modifying entities via [`MutableEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/MutableEntityStorage.kt).
Modifications are performed on copies of the original entities, so they don't affect old snapshots.

Entities are organized into a direct acyclic graph by optional parent-child relationships.
The storage maintains the consistency of such relations: if a child entity has a non-null reference to a parent entity, it is removed when the parent entity is removed or changed to point to a different child entity.

Also, symbolic (soft) references between entities are supported via [`SymbolicEntityId`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/SymbolicEntityId.kt).

Properties of entities may be of a restricted set of types only, see [`WorkspaceEntity`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt) for details.

It is possible to link objects of arbitrary types with entities using [`ExternalEntityMapping`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/ExternalEntityMapping.kt).

