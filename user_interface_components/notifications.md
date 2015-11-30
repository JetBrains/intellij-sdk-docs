---
title: Notifications
---


## Notifications

One of the leading design principles in recent versions of *IntelliJ IDEA* is avoiding the use of modal message boxes for notifying the user about errors and other situations that may warrant the user's attention.
As a replacement, the *IntelliJ Platform* provides multiple non-modal notification UI options.

### Dialogs

When working in a modal dialog, instead of checking the validity of the input when the `OK` button is pressed and notifying the user about invalid data with a modal dialog, the recommended approach is to use
[DialogBuilder.doValidate()](upsource:///platform/platform-api/src/com/intellij/openapi/ui/DialogBuilder.java),
which was described previously.

### Editor Hints

For actions invoked from the editor (such as refactorings, navigation actions and different code insight features), the best way to notify the user about the inability to perform an action is to use the
[HintManager](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java)
class.
Its method `showErrorHint()` displays a floating popup above the editor which is automatically hidden when the user starts performing another action in the editor.
Other
[HintManager](upsource:///platform/platform-api/src/com/intellij/codeInsight/hint/HintManager.java)
methods can be used for displaying other kinds of non-modal notification hints over an editor.

### Top-Level Notifications

The most general way to display non-modal notifications is to use the
[Notifications](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java)
class.

It has two main advantages:

*  The user can control the way each notification type is displayed under `Settings | Notifications`

*  All displayed notifications are gathered in the Event Log toolwindow and can be reviewed later

The specific method used to display a notification is
[Notifications.Bus.notify()](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java).
The text of the notification can include HTML tags.
You can allow the user to interact with the notification by including hyperlink tags in the notification text and passing a
[NotificationListener](upsource:///platform/platform-api/src/com/intellij/notification/NotificationListener.java)
instance to the constructor of the
[Notification](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java)
class.

The `groupDisplayId` parameter of the
[Notification](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java)
constructor specifies a notification type.
The user can choose the display type corresponding to each notification type under `Settings | Notifications`.
To specify the preferred display type, you need to call
[Notifications.Bus.register()](upsource:///platform/platform-api/src/com/intellij/notification/Notification.java)
before displaying any notifications.

