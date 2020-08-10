---
title: Plugin Listeners
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> **NOTE** Defining listeners in `plugin.xml` is supported starting with version 2019.3 of the platform.

> **NOTE** Listener implementations must be stateless and may not implement life-cycle (e.g., `Disposable`).

_Listeners_ allow plugins to declaratively subscribe to events delivered through the message bus (see [Messaging infrastructure](/reference_guide/messaging_infrastructure.md) for details). 

You can define both application- and project-level listeners.

Declarative registration of listeners allows you to achieve better performance compared to registering listeners
from code, because listener instances are created lazily (the first time an event is sent to the topic), and not
during application startup or project opening.

## Defining Application-Level Listeners

To define an application-level listener, add the following section to your `plugin.xml`:

```xml
<applicationListeners>
  <listener class="myPlugin.MyListenerClass" topic="BaseListenerInterface"/>
</applicationListeners>
```

The `topic` attribute specifies the listener interface corresponding to the type of events you want to receive.
Normally, this is the interface used as the type parameter of the [`Topic`](upsource:///platform/extensions/src/com/intellij/util/messages/Topic.java) instance for the type of events.
The `class` attribute specifies the class in your plugin that implements the listener interface and receives
the events. 

As a specific example, if you want to receive events about all changes in the virtual file system, you need
to implement the `BulkFileListener` interface, corresponding to the topic `VirtualFileManager.VFS_CHANGES`.
To subscribe to this topic from code, you could use something like the following snippet:

```java
messageBus.connect().subscribe(VirtualFileManager.VFS_CHANGES, new BulkFileListener() {
    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        // handle the events
    }
});
```

To use declarative registration, you no longer need to reference the `Topic` instance. Instead, you refer directly
to the listener interface class:

```xml
<applicationListeners>
  <listener class="myPlugin.MyVfsListener" 
            topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
</applicationListeners>
```

Then you provide the listener implementation as a top-level class:

```java
public class MyVfsListener implements BulkFileListener {
    @Override
    public void after(@NotNull List<? extends VFileEvent> events) {
        // handle the events
    }
}
```

## Defining Project-Level Listeners

Project-level listeners are registered in the same way, except that the top-level tag is 
`<projectListeners>`. They can be used to listen to project-level events, for example, tool window operations:

```xml
<projectListeners>
    <listener class="MyToolwindowListener" 
              topic="com.intellij.openapi.wm.ex.ToolWindowManagerListener" />
</projectListeners>
```

The class implementing the listener interface can define a one-argument constructor accepting a `Project`,
and it will receive the instance of the project for which the listener is created:

```java
public class MyToolwindowListener implements ToolWindowManagerListener {
    private final Project project;

    public MyToolwindowListener(Project project) {
        this.project = project;
    }

    @Override
    public void stateChanged() {
        // handle the state change
    }
}
```                  

## Additional Attributes

Registration of listeners can be restricted using the following attributes:

- `os` - allows to restrict listener to given OS, e.g., `os="windows"` for Windows only (2020.1 and later)
- `activeInTestMode` - set to `false` to disable listener if `com.intellij.openapi.application.Application.isUnitTestMode()`==`true` 
- `activeInHeadlessMode` - set to `false` to disable listener if `com.intellij.openapi.application.Application.isHeadlessEnvironment()`==`true`. Also covers `activeInTestMode` as test mode implies headless mode. 