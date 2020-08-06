---
title: Notifications
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

One of the leading design principles is avoiding the use of modal message boxes for notifying the user about errors and other situations that may warrant the user's attention.
As a replacement, the *IntelliJ Platform* provides multiple non-modal notification UI options.

### Dialogs

When working in dialog, instead of checking the validity of the input when the _OK_ button is pressed and notifying the user about invalid data with a modal dialog, the recommended approach is to use
[`DialogWrapper.doValidate()`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogWrapper.java),
which was described previously.

### Editor Hints

For actions invoked from the editor (such as refactorings, navigation actions and different code insight features), the best way to notify the user about the inability to perform an action is to use the
[`HintManager`](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java)
class.
Its method `showErrorHint()` displays a floating popup above the editor which is automatically hidden when the user starts performing another action in the editor.
Other
[`HintManager`](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java)
methods can be used for displaying other kinds of non-modal notification hints over an editor.

### Top-Level Notifications

The most general way to display non-modal notifications is to use the
[`Notifications`](upsource:///platform/platform-api/src/com/intellij/notification/Notifications.java)
class.

It has two main advantages:

*  The user can control the way each notification type is displayed under `Settings | Appearance & Behavior | Notifications`

*  All displayed notifications are gathered in the Event Log tool window and can be reviewed later

The specific method used to display a notification is
[`Notifications.Bus.notify()`](upsource:///platform/platform-api/src/com/intellij/notification/Notifications.java).
If the current Project is known, please use overload with `Project` parameter, so the notification is shown in its associated frame.

The text of the notification can include HTML tags.

Use `Notification.addAction(AnAction)` to add links below the content, use [`NotificationAction`](upsource:///platform/platform-api/src/com/intellij/notification/NotificationAction.java) for convenience. 

The `groupDisplayId` parameter of the
[`Notification`](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java)
constructor specifies a notification type.
The user can choose the display type corresponding to each notification type under `Settings | Appearance and Behavior | Notifications`.
To specify the preferred display type, you need to use
[`NotificationGroup`](upsource:///platform/platform-api/src/com/intellij/notification/NotificationGroup.kt) 
to create notifications.

#### Example

Simple use of notifications using
[`NotificationGroup`](upsource:///platform/platform-api/src/com/intellij/notification/NotificationGroup.kt).

```java
public class MyGroovyDSLErrorsNotifier {
  private final NotificationGroup NOTIFICATION_GROUP = 
          new NotificationGroup("Groovy DSL errors", NotificationDisplayType.BALLOON, true);

  public Notification notify(String content) {
    return notify(null, content);
  }

  public Notification notify(Project project, String content) {
    final Notification notification = NOTIFICATION_GROUP.createNotification(content, NotificationType.ERROR);
    notification.notify(project);
    return notification;
  }
}
```

Usage of the class with
[`NotificationGroup`](upsource:///platform/platform-api/src/com/intellij/notification/NotificationGroup.kt)
above.

```java
MyGroovyDSLErrorsNotifier myGroovyDSLErrorsNotifier = new MyGroovyDSLErrorsNotifier();
myGroovyDSLErrorsNotifier.notify("Some Groovy DSL error text");
```
