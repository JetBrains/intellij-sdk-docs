<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Entity Declaration

<primary-label ref="2024.2"/>

<link-summary>Declaration of Workspace Entities</link-summary>

The Workspace Model allows defining types of entities.

> Defining custom entities is not yet available to third-party plugins, see also [](#generating-entity-implementations).
>
{style="warning" title="Custom Entities"}

> Entities' declaration is still an experimental feature and can be changed at any time.
> While this feature is still experimental, it is important for understanding the data
> stored inside the Workspace Model and its relations.
>
{style="warning" title="Experimental Status"}

To declare an entity type, a Kotlin interface has to be created, which directly or indirectly extends
[`WorkspaceEntity`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt).
The entity interface must contain read-only properties only.

A nested `Builder` interface with setters for these properties, and a `companion` object which allows creating instances of the entity
are generated automatically along with the implementations (see [](#generating-entity-implementations)).
The platform currently provides some predefined types of entities
(see [`entities`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities) package),
but they're supposed to be used only for interoperability with code which uses the project model API.

Plugins should define and use their own types of entities if they need to store framework-specific data.

## Examples

### Default and Computable Fields

See [](workspace_model_entity_properties.md#property-kinds).

```kotlin
interface GradleEntity : WorkspaceEntity {
  val test: String

  // Field with default value
  val version: Int
    @Default get() = 5
  val description: Description

  // Computable field
  val name: String
    get() = description.name
}

data class Description(
  val name: String,
  val valid: Boolean,
  val modifications: Int
)
```

### Parent &rarr; Child References

See [](workspace_model_entity_properties.md#parent-child-relationship).

```kotlin
interface MavenEntity : WorkspaceEntity {
  val version: Int
  val name: String
  val root: VirtualFileUrl

  val kotlinEntities: List<@Child KotlinEntity>
}

interface KotlinEntity : WorkspaceEntity {
  val name: String
  val maven: MavenEntity
}
```

### Soft Reference using `SymbolicEntityId`

See [](workspace_model_entity_properties.md#symbolicentityid) and [](workspace_model_entity_properties.md#symbolic-references).

```kotlin
interface MavenEntity : WorkspaceEntityWithSymbolicId {
  val version: Int
  val name: String
  override val symbolicId: MavenId
    get() = MavenId(true, name)
}

interface KotlinEntity : WorkspaceEntity {
  val name: String
  val mavenId: MavenId
}

data class MavenId(
  val valid: Boolean,
  override val presentableName: String
) : SymbolicEntityId<MavenEntity>
```

### Reference via Extension Property

Due to the limitation that a reference must be declared on both entities, there needs to be an option to declare a reference to an entity from another module.
This can be done by declaring an extension property for the entity located outside the module.
This property must use the [`WorkspaceEntity.extension()`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/WorkspaceEntity.kt) delegate to work correctly.

Example: a `KotlinEntity` declaring a reference to the `MavenEntity` located in a different module:

<compare type="top-bottom" first-title="'Maven' module" second-title="'Kotlin' module">

<code-block lang="kotlin">
interface MavenEntity: WorkspaceEntity {
  val version: Int
  val name: String
}
</code-block>

<code-block lang="kotlin">
interface KotlinEntity: WorkspaceEntity {
  val name: String
  val maven: MavenEntity
}
val MavenEntity.kotlinEntity: @Child KotlinEntity
  by WorkspaceEntity.extension()
</code-block>

</compare>

### Inheritance

For inheritance, the supertype entity must be marked using
[`Abstract`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/annotations/Abstract.kt) annotation.

See [`PackagingElementEntity`](%gh-ic%/platform/workspace/jps/src/com/intellij/java/workspace/entities/artifact.kt),
more samples can be found in the
[`com.intellij.platform.workspace.jps.entities`](%gh-ic%/platform/workspace/jps/src/com/intellij/platform/workspace/jps/entities)
package.

## Generating Entity Implementations

> This feature is currently not available to third-party plugins and enabled only for the IntelliJ IDEA project itself.
>
{style="warning"}

After defining the entity interface, generate some code with the <control>Generate Workspace Model Implementation</control> action,
available from the module's context menu in the <control>Project</control> tool window or via <ui-path>Help | Find Action</ui-path>
on the currently open file.

Entity implementations can also be generated via the <control>Generate implementation</control> quick-fix on the interface's name element,
which is provided by inspection <ui-path>Plugin DevKit | Workspace model | Generate implementation</ui-path>.

Along with generating implementations for the interfaces in the <path>gen</path> source folder, this action also adds the `Builder` interface
to an entity as well as some additional methods in the file where the entity is located.

Comments surround all generated blocks:

```
//region generated code
//endregion
```

Don't edit the generated code blocks. If something is wrong with the generated code, try re-executing the action.
