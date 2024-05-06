<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notifications

<link-summary>Notifying users about errors, action statuses, or other events without interrupting their workflow by showing modal message boxes requiring confirmation.</link-summary>

<tldr>

**UI Guidelines:** [](notification_types.md), [](banner.md), [](got_it_tooltip.md), [](balloon.md)

</tldr>

One of the leading design principles is avoiding the use of modal message boxes for notifying the user about errors and other situations that may warrant the user's attention.
As a replacement, the IntelliJ Platform provides multiple non-modal notification UI options.

### Dialogs

When working in a dialog, instead of checking the validity of the input when the <control>OK</control> button is pressed and notifying the user about invalid data with a modal dialog, the recommended approach is to use
[`DialogWrapper.doValidate()`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java),
described in the [Dialogs](dialog_wrapper.md#input-validation) section.

### Editor Hints

For actions invoked from the editor (such as refactorings, navigation actions, and different code insight features), the best way to notify the user about the inability to perform an action is to use the [`HintManager`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) class.
Its method `showErrorHint()` displays a floating popup above the editor which is automatically hidden when the user starts performing another action in the editor.
Other [`HintManager`](%gh-ic%/platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java) methods can be used for displaying other kinds of non-modal notification hints over an editor.

### Editor Banner

For UI reference, see [](banner.md) in UI Guidelines.

Notifications that appear at the top of the file editor are a great way to ask the user to take an important action that would otherwise impede their experience if ignored (e.g., missing SDK, setup/project configuration requiring user input).

Register an implementation of [`EditorNotifications.Provider`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotifications.java) using `com.intellij.editorNotificationProvider` extension point.
If access to indexes is not required, it can be marked [dumb aware](indexing_and_psi_stubs.md#DumbAwareAPI).

A commonly used UI implementation is [`EditorNotificationPanel`](%gh-ic%/platform/platform-api/src/com/intellij/ui/EditorNotificationPanel.java).

### "Got It" Notification
{id="gotIt"}

Use to highlight important new/changed features via [`GotItTooltip`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/GotItTooltip.kt).
See [](got_it_tooltip.md) in UI Guidelines for an overview.

### Top-Level Notifications (Balloons)
{id="balloons"}

The most general way to display non-modal notifications is to use the [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java) class.

It has two main advantages:

* The user can control the way each notification type is displayed under <ui-path>Settings | Appearance & Behavior | Notifications</ui-path>
* All displayed notifications are gathered in the <control>Event Log</control> tool window and can be reviewed later

For UI reference, see [](balloon.md) in UI Guidelines.

> See [](tool_windows.md#tool-window-notification) for showing balloons for a specific tool window.

The specific method used to display a notification is [`Notifications.Bus.notify()`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java).
If the current Project is known, please use overload with `Project` parameter, so the notification is shown in its associated frame.

The text of the notification can include HTML tags for presentation purposes.
Use `Notification.addAction(AnAction)` to add links below the content, use [`NotificationAction`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationAction.java) for convenience.

The `groupId` parameter of the [`Notification`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notification.java) constructor specifies a notification type.
The user can choose the display type corresponding to each notification type under <ui-path>Settings | Appearance & Behavior | Notifications</ui-path>.

To specify the preferred display type, you need to use [`NotificationGroup`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationGroup.kt) to create notifications.

Please see the following steps for setup, depending on the target platform version.

<tabs>

<tab title="2020.3 and later">

`NotificationGroup` is registered in <path>[plugin.xml](plugin_configuration_file.md)</path> using `com.intellij.notificationGroup` extension point.
Use `key` to provide a localized group display name.

```xml

<extensions defaultExtensionNs="com.intellij">
  <notificationGroup id="Custom Notification Group"
                     displayType="BALLOON"
                     key="notification.group.name"/>
</extensions>
```

Registered instances can then be obtained via their `id`.

> Code insight is available for parameters expecting a notification group `id`.
>

<br/>

```java
public class MyNotifier {

  public static void notifyError(Project project, String content) {
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
      new NotificationGroup("Custom Notification Group",
              NotificationDisplayType.BALLOON, true);

  public static void notifyError(Project project, String content) {
    NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR)
        .notify(project);
  }

}
```

</tab>

</tabs>
