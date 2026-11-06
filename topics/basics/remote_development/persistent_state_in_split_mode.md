<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Persistent State Component in Split Mode

<primary-label ref="2026.1"/>

<link-summary>Synchronize persistent plugin settings correctly between frontend and backend in Split Mode.</link-summary>

This article shows how to make a [Persistent State Component](persisting_state_of_components.md) synchronize correctly between the frontend and the backend.

At a high level, it requires:

1. [Creating a persistent state component.](#1-create-a-persistentstatecomponent)
2. [Registering sync metadata with `RemoteSettingInfoProvider`.](#2-register-a-remotesettinginfoprovider)
3. [Declaring the settings in XML so the initial synchronization can happen.](#3-declare-the-settings-in-xml)
4. [Choosing the right sync direction for a use case.](#4-choose-the-right-sync-direction)

This setup is especially important in [split mode](split_mode_and_remote_development.md), where settings may exist on both sides and need to stay in sync.

## 1. Create a `PersistentStateComponent`

Start by implementing a settings component in a standard way.

In the case of extending [`SimplePersistentStateComponent`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/components/SimplePersistentStateComponent.kt), it is recommended to override `noStateLoaded()`.
This helps handle the case in which the remote side sends an empty state: instead of leaving the component in an unexpected state, it is explicitly reset it to its defaults.

```kotlin
@State(
  name = "MySettings",
  storages = [Storage("my-settings.xml")]
)
class MySettings :
    SimplePersistentStateComponent<MySettings.State>(State()) {

  class State {
    var mySetting: Boolean = true
  }

  override fun noStateLoaded() {
    // Reset to defaults when remote sends empty state:
    loadState(State())
  }
}
```

It is important, because during synchronization, one side may receive no previously stored state.
If that happens, `noStateLoaded()` provides a safe and predictable fallback.

## 2. Register a `RemoteSettingInfoProvider`

The next step is informing the platform that the persistent state component should participate in frontend/backend synchronization.

This is achieved by implementing [`RemoteSettingInfoProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/settings/RemoteSettingInfoProvider.kt) in the shared module, and registering it on both the frontend and the backend.

In practice, this usually means either:

* registering it in a shared XML module descriptor, or
* registering the same provider in both frontend and backend XML module descriptors.

Example:

```kotlin
class MySettingsRemoteInfoProvider : RemoteSettingInfoProvider {
  override fun getRemoteSettingsInfo() = mapOf(
    "MySettings" to RemoteSettingInfo(direction = Direction.InitialFromFrontend)
    // Use InitialFromBackend for project-level settings
  )
}
```

Then register it in `plugin.xml`:

```xml
<extensions defaultExtensionNs="com.intellij">
  <rdct.remoteSettingProvider
    implementation="com.example.MySettingsRemoteInfoProvider"/>
</extensions>
```

`RemoteSettingInfoProvider` supplies synchronization metadata for a persistent state component.
In particular, it tells the platform which component should be synced and in which direction the initial state should flow.

## 3. Declare the Settings in XML

This step is required for initial synchronization.

If settings are not declared in XML, synchronization will not start until the user changes the setting manually for the first time.

Use one of the following declarations, depending on the scope of settings:

```xml
<!-- application-level settings: -->
<applicationSettings service="com.example.MySettings"/>

<!-- project-level settings: -->
<projectSettings service="com.example.MySettings"/>
```

These declarations make the settings visible to the synchronization infrastructure from the start.
Without them, the platform does not know that the settings should be included in the initial synchronization.

## 4. Choose the Right Sync Direction

When registering settings, it is required to define the [`RemoteSettingInfo.direction`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/settings/RemoteSettingInfo.kt) determining where the initial value should come from.

In most cases, the correct choice depends on whether the settings are application-level or project-level.
Use the following guidance for choosing the right direction:

| Direction             | Recommended Use                                                                                                                       |
|-----------------------|---------------------------------------------------------------------------------------------------------------------------------------|
| `InitialFromFrontend` | The default choice for application-level settings. Use it unless there is a specific reason not to.                                   |
| `InitialFromBackend`  | The default choice for project-level settings. Use it unless there is a specific reason not to.                                       |
| `OnlyFromFrontend`    | Use when the setting is owned entirely by the frontend module and the backend module is not able to interpret and manage the setting. |
| `OnlyFromBackend`     | Use when the setting is owned entirely by the backend module and the frontend module is not able to interpret and manage the setting. |

<include from="snippets.topic" element-id="missingContent"/>
