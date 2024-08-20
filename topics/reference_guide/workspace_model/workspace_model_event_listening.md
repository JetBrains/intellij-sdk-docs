<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Event Listening

<primary-label ref="2024.2"/>

<link-summary>Listening to Workspace Model events</link-summary>

There are two ways to subscribe to events from the Workspace Model:
via [listener](#WorkspaceModelChangeListener) or by subscribing to the [flow](#subscription-via-kotlin-flow) of events.

### `EntityChange` Events

The order of
[`EntityChange`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/MutableEntityStorage.kt)
events is predefined:

1. `EntityChange.Removed`
2. `EntityChange.Replaced`
3. `EntityChange.Added`

The `Added` and `Removed` events are straightforward and generated in case of added or removed entities.

The `Replaced` event is generated in case if any of the fields of the entity changes the value in the newer version of storage.
This event is generated in two cases:

- "primitive" field change (`Int`, `String`, `data class`, etc.)
- changes of references to other entities

The change of references may happen indirectly by modifying the referred entity.
For example, when removing a child entity, two events are generated: `Removed` for a child and `Replaced` for a parent.
When adding a new child entity, again two events will be generated: `Added` for a child and `Replaced` for a parent.

#### Examples

##### Example 1

Assuming the following structure of entities: `A` &rarr; `B` &rarr; `C`.
Where `A` is the root entity and `B` is its child, and `C` is the child of `B`.

- Modify a primitive field of `C`: [**Replaced(C)**]
- Remove `C`: [**Replaced(B)**, **Removed(C)**]
- Remove reference between `B` and `C`: [**Replaced(B)**, **Replaced(C)**]
- Remove `B`: [**Replaced(A)**, **Removed(B)**, **Removed(C)**] – `C` is cascade removed

##### Example 2

Assuming the following structure of entities:
Initial: `A` &rarr; `B`  `C`
After: `A`  `C` &rarr; `B`

Move child `B` from `A` to `C`: [**Replaced(A)**, **Replaced(B)**, **Replaced(C)**]

## Subscription via `WorkspaceModelChangeListener`

{id="WorkspaceModelChangeListener"}

The Workspace Model storage allows us to subscribe to all of its changes using the [](plugin_listeners.md) mechanism via
[`WorkspaceModelChangeListener`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt).

The listener offers both
[`WorkspaceModelChangeListener.beforeChanged()`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt)
and
[`WorkspaceModelChangeListener.changed()`](%gh-ic%/platform/backend/workspace/src/WorkspaceModelTopics.kt)
callbacks.
The object passed to these methods as a parameter
[`VersionedStorageChange`](%gh-ic%/platform/workspace/storage/src/com/intellij/platform/workspace/storage/VersionedEntityStorage.kt)
contains snapshots before and after the modification.
It can be filtered by the concrete type of entity and action (`Added`/`Removed`/`Replaced`).

Examples:

- [`LanguageLevelChangedListener`](%gh-ic%/java/java-impl/src/com/intellij/openapi/roots/impl/LanguageLevelChangedListener.java) to handle Java language level changes
- [`PackagePrefixIndex`](%gh-ic%/java/java-analysis-impl/src/com/intellij/psi/impl/PackagePrefixIndex.java) to update package-prefixes for Java source roots

Registration of listeners can be done in two ways, depending on requirements:

### Programmatic Registration

Listener instance subscription is managed by code, and it starts receiving events only after explicit registration.

```kotlin
val busConnection = project.messageBus.connect(cs)
busConnection.subscribe(WorkspaceModelTopics.CHANGED,
  object : WorkspaceModelChangeListener {

    override fun changed(event: VersionedStorageChange) {
      // Get all changes for ModuleEntity
      val moduleChanges = event.getChanges(ModuleEntity::class.java)
      // Filtering entities which were removed
      moduleChanges.filterIsInstance<EntityChange.Removed<ModuleEntity>>()
        .forEach { removedModuleEntity ->
          ...
        }
    }
  })
```

### Declarative Registration

[Declarative registration](plugin_listeners.md) is available by registering the Project-level listener in <path>plugin.xml</path>.
Listener instances will be automatically instantiated on the first event to the topic.

```xml
<idea-plugin>
  <projectListeners>
    <listener
      class="com.my.plugin.MyCustomWorkspaceModelChangeListener"
      topic="com.intellij.platform.backend.workspace.WorkspaceModelChangeListener"/>
  </projectListeners>
</idea-plugin>
```

## Subscription via Kotlin Flow

As the IntelliJ Platform started adapting [](kotlin_coroutines.md) in its APIs and internal code, it is also possible to subscribe to the
events via [Kotlin Flows](https://kotlinlang.org/docs/flow.html) exposed via the
[`WorkspaceModel.eventLog`](%gh-ic%/platform/backend/workspace/src/WorkspaceModel.kt)
property.

In addition to the regular events subscription, flow allows performing incremental computation:
calculate information based on the initial storage and modify it according to the updates.
This can be useful for incremental updates of Workspace Model-related caches.

If the subscription should be set up after a project is opened, use
[`ProjectActivity`](plugin_components.md#project-open).
This is also the way to migrate from the existing [`WorkspaceModelChangeListener`](#WorkspaceModelChangeListener)-based approach.
Keep in mind that it might miss the few first updates of the model, which is expected because the initial version of the storage will be provided.

Since this listener is asynchronous, there is no guarantee that indexes built on top of
[`WorkspaceFileIndex`](%gh-ic%/platform/projectModel-impl/src/com/intellij/workspaceModel/core/fileIndex/WorkspaceFileIndex.kt)
or the project model will be updated.

### Incremental Computation

To perform incremental computations, the last known version of the storage and the event of updates is required.
This can be obtained by collecting indexed events.
Assume we build some index and keep it updated:

```kotlin
workspaceModel.eventLog.collectIndexed { index, event ->
  if (index == 0) {
    buildIndex(event.storageAfter)
  } else {
    updateIndex(event)
  }
}
```
