<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Workspace Model

<primary-label ref="2024.2"/>

<link-summary>Introduction to Workspace Model representing a project's structure</link-summary>

The Workspace Model represents the project's structure and all its elements, such as [modules](module.md), [libraries](library.md), [SDKs](sdk.md), [facets](facet.md),
and other configurable project components.
It provides a generic storage for entities describing the user's workspace while maintaining full
[interoperability](#interoperability) with the [](project_model.md).

<include from="project.md" element-id="useWorkspaceModelAPI"/>

## Why a New API?

There are several problems with the approach that has been used to represent the [project model](project_model.md) (configuration of modules, libraries, facet, artifacts, etc.)
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
The Workspace Model uses its own storage that is Platform-independent and can be reused in different places.

### Flexibility

The project model concepts were created for [IntelliJ IDEA](idea.md) before the IntelliJ Platform accommodated a variety of languages.
Many common interfaces like
[`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java) and
[`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java)
still have Java-specific properties.
This makes it harder to reuse them for other languages and technologies, and there is no simple way to create new language-specific concepts and use them in the model.

## Concepts and Implementation

Workspace Model introduces a unified data structure that stores the project configuration and doesn't have to be used from the IDE process.
The implementation heavily relies on [Persistent Data Structure](https://en.wikipedia.org/wiki/Persistent_data_structure) that allows using a
model snapshot without fear that it will be modified during an interaction.

### Entities

Types of entities in the storage are represented by interfaces extending
[`WorkspaceEntity`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt) (see [](workspace_model_entity_declaration.md)).
All operations can be performed on entities.
Properties of entities may be of a restricted set of types only, see [](workspace_model_entity_properties.md) for details.

### Storage

Their instances are stored in [`VersionedEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/VersionedEntityStorage.kt),
which provides access to the current [`ImmutableEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/EntityStorage.kt),
an immutable snapshot of the storage state which isn't affected by further modifications of the storage.

### Modification

Modifying entities is performed via
[`MutableEntityStorage`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/MutableEntityStorage.kt).
Modifications are performed on copies of the original entities, so they don't affect old snapshots (see [](workspace_model_entity_mutation.md)).

### Relationships

Entities are organized into a direct acyclic graph by optional [parent-child relationships](workspace_model_entity_properties.md#parent-child-relationship).
The storage maintains the consistency of such relations: if a child entity has a non-null reference to a parent entity, it is removed when the parent entity is removed or changed to point to a different child entity.

[Symbolic (soft) references](workspace_model_entity_properties.md#symbolic-references) between entities are supported.
It is also possible to [link objects](workspace_model_entity_read.md#arbitrary-data) of arbitrary types with entities.

## Interoperability with Project Model API

{id="interoperability"}

New implementations of the [Project Model](project_model.md) interfaces store their data in
Workspace Model in corresponding entities.

The following table maps to corresponding Workspace Model API and usage samples.

| Project Structure Element | Project Model API                                                                                    | Workspace Model API                                                                                                | Workspace Model API Usage             |
|---------------------------|------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------|---------------------------------------|
| [](module.md)             | [`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)                    | [`ModuleEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/module.kt)        | [](workspace_model_usages.md#module)  |
| [](sdk.md)                | [`Sdk`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/projectRoots/Sdk.java)            | [`SdkEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/sdk.kt)              |                                       |
| [](library.md)            | [`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java) | [`LibraryEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/dependencies.kt) | [](workspace_model_usages.md#library) |
| [](facet.md)              | [`Facet`](%gh-ic%/platform/lang-core/src/com/intellij/facet/Facet.java)                              | [`FacetEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities/facet.kt)          |                                       |

[`WorkspaceModel`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt)
allows performing operations as with the [](project_model.md) API,
but via a single entry point, see also [](workspace_model_usages.md).
