[//]: # (title: Listeners)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Listeners allow subscription to application and project events.</excerpt>

_Listeners_ allow plugins to declaratively subscribe to events delivered through the message bus (see [Messaging infrastructure](messaging_infrastructure.md) for details).
Listener implementations must be stateless and may not implement life-cycle (e.g., `Disposable`).

You can define both application- and project-level listeners.

> All available listeners/topics are listed on [](extension_point_list.md) under _Listeners_ sections.
> Browse usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).
>
{type="tip"}

Declarative registration of listeners allows you to achieve better performance than registering listeners from code.
The advantage is because listener instances get created lazily — the first time an event is sent to the topic — and not during application startup or project opening.

> Defining listeners in <path>plugin.xml</path> is supported starting with version 2019.3 of the platform.
>
{type="note"}

## Defining Application-Level Listeners

To define an application-level listener, add the following section to your <path>plugin.xml</path>:

```xml
<idea-plugin>
  <applicationListeners>
    <listener
        class="myPlugin.MyListenerClass"
        topic="BaseListenerInterface"/>
  </applicationListeners>
</idea-plugin>
```

The `topic` attribute specifies the listener interface corresponding to the type of events you want to receive.
Usually, this is the interface used as the type parameter of the [`Topic`](upsource:///platform/extensions/src/com/intellij/util/messages/Topic.java) instance for the type of events.
The `class` attribute specifies the class in your plugin that implements the listener interface and receives the events.

As a specific example, if you want to receive events about all [Virtual File System](virtual_file_system.md) changes, you need to implement the `BulkFileListener` interface, corresponding to the topic `VirtualFileManager.VFS_CHANGES`.
To subscribe to this topic from code, you could use something like the following snippet:

```java
messageBus.connect().subscribe(VirtualFileManager.VFS_CHANGES,
    new BulkFileListener() {
        @Override
        public void after(@NotNull List<? extends VFileEvent> events) {
          // handle the events
        }
});
```

To use declarative registration, you no longer need to reference the `Topic` instance.
Instead, you refer directly to the listener interface class:

```xml
<applicationListeners>
  <listener
      class="myPlugin.MyVfsListener"
      topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
</applicationListeners>
```

Then you provide the listener implementation as a top-level class:

```java
package myPlugin;

public class MyVfsListener implements BulkFileListener {
  @Override
  public void after(@NotNull List<? extends VFileEvent> events) {
    // handle the events
  }
}
```

## Defining Project-Level Listeners

[Project](project.md)-level listeners are registered in the same way, except that the top-level tag is `<projectListeners>`.
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

The class implementing the listener interface can define a one-argument constructor accepting a [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java), and it will receive the instance of the project for which the listener is created:

```java
package myPlugin;

public class MyToolWindowListener implements ToolWindowManagerListener {
  private final Project project;

  public MyToolwindowListener(Project project) {
    this.project = project;
  }

  @Override
  public void stateChanged(@NotNull ToolWindowManager toolWindowManager) {
    // handle the state change
  }
}
```

## Additional Attributes

Registration of listeners can be restricted using the following attributes:

- `os` - allows restricting listener to given OS, e.g., `os="windows"` for Windows only (2020.1 and later)
- `activeInTestMode` - set to `false` to disable listener if [`Application.isUnitTestMode()`](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java) returns `true`
- `activeInHeadlessMode` - set to `false` to disable listener if [`Application.isHeadlessEnvironment()`](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java) returns `true`.
  Also, covers `activeInTestMode` as test mode implies headless mode.
