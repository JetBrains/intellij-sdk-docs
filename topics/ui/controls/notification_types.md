<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notifications

<link-summary>UI guidelines on using notifications.</link-summary>

Notifications inform users about the status of user or system initiated operations. They can have different actions depending on the message.

Types of notifications:

- Alert
- [Banner](banner.md)
- [Notification balloon](balloon.md)

## What notification to use

Choose a notification type based on a combination of two factors:

1. Is **user action** required to proceed?
    - Required immediately
    - Required, but not immediately
    - Not required

2. From what **context** the notification was initiated?
    - Editor
    - Tool window
    - Dialog
    - Any other location


### Alert
An action is required immediately, in any context.

![](notification_type_alert.png){width=706}

### Banner
[Main article](banner.md)

An action is required but not immediately, in the editor, tool windows, and dialogs.
![](notification_type_banner_action_required_editor.png){width=706}

An action is not required in a dialog.
![](notification_type_banner_action_required_dialog.png){width=706}

### Notification balloon
[Main article](balloon.md)

An action is not required to proceed, in any context except dialogs.

![](notification_type_balloon_action_not_required.png){width=706}
