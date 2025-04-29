<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Listeners

<link-summary>Listeners allow subscription to application and project events.</link-summary>

_Listeners_ allow plugins to subscribe to events delivered through the message bus (see [Messaging infrastructure](messaging_infrastructure.md) for details).

Listeners are defined at the application (global) or [project](project.md) level.

> All available listeners/topics are listed on [](intellij_platform_extension_point_list.md) and [](intellij_community_plugins_extension_point_list.md)
> under _Listeners_ sections.
>
> Browse usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).
>
{title="Locating Listeners/Topics"}

Listener implementations must be stateless and may not implement life-cycle (e.g., `Disposable`).
Use inspection <control>Plugin DevKit | Code | Listener implementation implements 'Disposable'</control> to verify (2023.3).

Declarative registration of listeners allows achieving better performance than registering listeners from code.
The advantage is because listener instances get created lazily — the first time an event is sent to the topic — and not during application startup or project opening.

## Defining Application-Level Listeners

To define an application-level listener, add the [`<applicationListeners>`](plugin_configuration_file.md#idea-plugin__applicationListeners) section to <path>[plugin.xml](plugin_configuration_file.md)</path>:

```xml
<idea-plugin>
  <applicationListeners>
    <listener
        class="myPlugin.MyListenerClass"
        topic="BaseListenerInterface"/>
  </applicationListeners>
</idea-plugin>
```

The `topic` attribute specifies the listener interface corresponding to the type of events to receive.
Usually, this is the interface used as the type parameter of the [`Topic`](%gh-ic%/platform/extensions/src/com/intellij/util/messages/Topic.java) instance for the type of events.
The `class` attribute specifies the class in the plugin that implements the listener interface and receives the events.

As a specific example, to receive events about all [](virtual_file_system.md) changes, implement the `BulkFileListener` interface, corresponding to the topic `VirtualFileManager.VFS_CHANGES`.
To subscribe to this topic from code, use something like the following snippet:

```java
messageBus.connect().subscribe(VirtualFileManager.VFS_CHANGES,
    new BulkFileListener() {
      @Override
      public void after(@NotNull List<? extends VFileEvent> events) {
        // handle the events
      }
});
```

To use declarative registration, it's no longer required to reference the `Topic` instance.
Instead, refer directly to the listener interface class:

```xml
<applicationListeners>
  <listener
      class="myPlugin.MyVfsListener"
      topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
</applicationListeners>
```

Then provide the listener implementation:

```java
package myPlugin;

final class MyVfsListener implements BulkFileListener {
  @Override
  public void after(@NotNull List<? extends VFileEvent> events) {
    // handle the events
  }
}
```

## Defining Project-Level Listeners

[](project.md)-level listeners are registered in the same way, except that the top-level tag is [`<projectListeners>`](plugin_configuration_file.md#idea-plugin__projectListeners).
They can be used to listen to project-level events, for example, [tool window](tool_windows.md) operations:

```xml
<idea-plugin>
  <projectListeners>
    <listener
        class="myPlugin.MyToolWindowListener"
        topic="com.intellij.openapi.wm.ex.ToolWindowManagerListener"/>
  </projectListeners>
</idea-plugin>
```

The class implementing the listener interface can define a one-argument constructor accepting a [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java), and it will receive the instance of the project for which the listener is created:

```java
package myPlugin;

final class MyToolWindowListener implements ToolWindowManagerListener {
  private final Project project;

  MyToolWindowListener(Project project) {
    this.project = project;
  }

  @Override
  public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {
    // handle the state change
  }
}
```

## Additional Attributes

Registration of listeners can be restricted using the following attributes.

`os`
: Allows restricting listener to a given OS, e.g., `os="windows"` for Windows only.

`activeInTestMode`
: Set to `false` to disable listener if [`Application.isUnitTestMode()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) returns `true`

`activeInHeadlessMode`
: Set to `false` to disable the listener if [`Application.isHeadlessEnvironment()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java) returns `true`.
  Also covers `activeInTestMode` as test mode implies headless mode.

> If declared listener topics are intended to be used by other plugins depending on your plugin, consider [bundling their sources](bundling_plugin_openapi_sources.md) in the plugin distribution.
>
{style="note"}
