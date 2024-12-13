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
    - [Required immediately](#action-is-required-immediately)
    - [Required but not immediately](#action-is-required-but-not-immediately)
    - [Not required](#action-is-not-required-to-proceed)

2. From what **context** the notification was initiated?
    - Editor
    - Tool window
    - Dialog
    - Any other location


### Action is required immediately
Use an alert in any context:
![An alert 'Open Project' asking where to open 'myJavaProject' with options to cancel, open in a new window or this window](notification_type_alert.png){width=706}

### Action is required but not immediately
Use a [banner](banner.md) if the context is the editor, a tool window, or a dialog:
![A banner in the editor with a warning 'Project JDK is not defined' and action 'Setup JDK'](notification_type_banner_action_required_editor.png){width=706}

In any other context, use a [notification balloon](balloon.md).

### Action is not required to proceed
Use a [notification balloon](balloon.md) in any context except dialogs:
![A notification balloon warning about a shortcut conflict with a description and actions 'Modify shortcuts' and 'Don't show again'](notification_type_balloon_action_not_required.png){width=706}

In a dialog, use a [banner](banner.md):
![A banner in a dialog with a notification 'Interactive lesson available' and actions 'Open lesson' and an icon button to close the banner](notification_type_banner_action_required_dialog.png){width=706}


