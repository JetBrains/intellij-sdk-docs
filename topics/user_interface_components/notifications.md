[//]: # (title: Notifications)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

One of the leading design principles is avoiding the use of modal message boxes for notifying the user about errors and other situations that may warrant the user's attention.
As a replacement, the IntelliJ Platform provides multiple non-modal notification UI options.

For an overview, refer to [Notifications](https://jetbrains.design/intellij/controls/notifications/) in IntelliJ Platform UI Guidelines.

### Dialogs

When working in dialog, instead of checking the validity of the input when the _OK_ button is pressed and notifying the user about invalid data with a modal dialog, the recommended approach is to use [`DialogWrapper.doValidate()`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java), which was described previously.

### Editor Hints

For actions invoked from the editor (such as refactorings, navigation actions and different code insight features), the best way to notify the user about the inability to perform an action is to use the [`HintManager`](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) class.
Its method `showErrorHint()` displays a floating popup above the editor which is automatically hidden when the user starts performing another action in the editor.
Other [`HintManager`](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) methods can be used for displaying other kinds of non-modal notification hints over an editor.

### Editor Banner

For UI reference, see [Banner](https://jetbrains.design/intellij/controls/banner/) in the IntelliJ Platform UI Guidelines.

Notifications that appear at the top of the file editor are a great way to ask the user to take an important action that would otherwise impede their experience if ignored (e.g., missing SDK, setup/project configuration requiring user input).

Register an implementation of [`EditorNotifications.Provider`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotifications.java) using `com.intellij.editorNotificationProvider` extension point.
If no [index access](indexing_and_psi_stubs.md#dumb-mode) is required, it can implement [`DumbAware`](upsource:///platform/core-api/src/com/intellij/openapi/project/DumbAware.java) to be shown during indexing.

A commonly used UI implementation is [`EditorNotificationPanel`](upsource:///platform/platform-api/src/com/intellij/ui/EditorNotificationPanel.java).

### "Got It" Notification

Use to highlight important new/changed features via [`GotItMessage`](upsource:///platform/platform-impl/src/com/intellij/ui/GotItMessage.java).
See [Got It tooltip](https://jetbrains.design/intellij/controls/got_it_tooltip/) in IntelliJ Platform UI Guidelines for an overview.

### Top-Level Notifications (Balloons)

The most general way to display non-modal notifications is to use the [`Notifications`](upsource:///platform/ide-core/src/com/intellij/notification/Notifications.java) class.

It has two main advantages:

* The user can control the way each notification type is displayed under <menupath>Settings/Preferences | Appearance & Behavior | Notifications</menupath>
* All displayed notifications are gathered in the <control>Event Log</control> tool window and can be reviewed later

For UI reference, see [Balloon](https://jetbrains.design/intellij/controls/balloon/) in the IntelliJ Platform UI Guidelines.

The specific method used to display a notification is [`Notifications.Bus.notify()`](upsource:///platform/ide-core/src/com/intellij/notification/Notifications.java).
If the current Project is known, please use overload with `Project` parameter, so the notification is shown in its associated frame.

The text of the notification can include HTML tags.

Use `Notification.addAction(AnAction)` to add links below the content, use [`NotificationAction`](upsource:///platform/ide-core/src/com/intellij/notification/NotificationAction.java) for convenience.

The `groupId` parameter of the [`Notification`](upsource:///platform/ide-core/src/com/intellij/notification/Notification.java) constructor specifies a notification type.
The user can choose the display type corresponding to each notification type under <menupath>Settings/Preferences | Appearance & Behavior | Notifications</menupath>.

To specify the preferred display type, you need to use [`NotificationGroup`](upsource:///platform/ide-core/src/com/intellij/notification/NotificationGroup.kt) to create notifications.

Please see the following steps for setup, depending on the target platform version.

<tabs>

<tab title="2020.3 and later">

`NotificationGroup` is registered in <path>plugin.xml</path> using `com.intellij.notificationGroup` extension point.
Use `key` to provide a localized group display name.

```xml

<extensions defaultExtensionNs="com.intellij">
  <notificationGroup id="Custom Notification Group"
                     displayType="BALLOON"
                     key="notification.group.name"/>
</extensions>
```

Registered instances can then be obtained via their `id`.

> Code insight is available for parameters expecting notification group `id`.
>
{type="tip"}

```java
public class MyNotifier {

  public static void notifyError(@Nullable Project project,
                                 String content) {
    NotificationGroupManager.getInstance()
            .getNotificationGroup("Custom Notification Group")
            .createNotification(content, NotificationType.ERROR)
            .notify(project);
  }

}
```

</tab>

<tab title="Pre-2020.3">

`NotificationGroup` is registered in code.

```java
public class MyNotifier {

  private static final NotificationGroup NOTIFICATION_GROUP =
          new NotificationGroup("Custom Notification Group", NotificationDisplayType.BALLOON, true);

  public static void notifyError(@Nullable Project project, String content) {
    NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR)
            .notify(project);
  }

}
```

</tab>

</tabs>
