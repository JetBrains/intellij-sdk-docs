<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notification Balloon

<link-summary>UI guidelines on using notification balloons.</link-summary>

<tldr>

**Implementation:** [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java)

**Related:** [](notifications.md#balloons)

</tldr>

Notifications balloons inform users of the events or system states related to a project or IDE.

![](notification_balloon.png){width=706}

## Suggestion and timeline notifications

There are two types of notification balloons:
* **Suggestion** notifications show the recommended action as a noticeable button.
* **Timeline** notifications show useful actions as links.

These types appear in different sections of the Notifications tool window:

![](notification_balloon_toolwindow.png){width=706}

## When to use

### Show result of a potentially long process

Use a timeline notification.

![](notification_balloon_timeline_long_process.png){width=706}

### Show result of an action without context

Use a timeline notification when an action was called from the context already closed, like a dialog or a popup.

![](notification_balloon_timeline_no_context_action.png){width=706}

### Show result of an automatic configuration

Use a timeline notification when the project or IDE settings were automatically configured and it is likely the user might need to check the changes.

![](notification_balloon_timeline_automatic_configuration.png){width=706}

### Suggest an action to configure project or IDE

Use a suggestion notification to promote an action for project or IDE configuration, when not taking this action might lead to less optimal functioning or errors.

![](notification_balloon_suggestion_configuration.png){width=706}

### Request user input

Use a suggestion notification to ask the user for an additional input or action that is not connected to their own workflows.

![](notification_balloon_suggestion_request_input.png){width=706}


## How to use

### Sticky or timed

**Sticky** notifications stay on screen until the user clicks any of its actions or closes it. By default, use this behavior for **suggestion** notifications.

**Timed** notifications stay on screen for 10 seconds and then hide. By default, use this behavior for **timeline** notifications.

Change the default behavior if it makes sense in a particular use case.


### Message severity

#### Error

Use to inform of a critical event or state that might disrupt the user's experience.

![](notification_balloon_severity_error.png){width=706}

#### Warning

Use in case an event or state might slow the user's work down or require an action to fix the project or IDE settings.

![](notification_balloon_severity_warning.png){width=706}

#### Information

Use in all other cases.

![](notification_balloon_severity_info.png){width=706}

When possible, use a plugin or functionality icon instead of the info icon. This helps identifing the source of the notification quicker.

![](notification_balloon_plugin_icon.png){width=706}


### Text

#### Writing

[Write short and clear](writing_short.md) as notifications have limited space and may appear for a short time.

Use sentence case and follow the [punctuation rules](punctuation.md).

#### Title
Describe the event and the context in which it occurred. The context could be the name of a plugin, library, or functionality.

#### Body
Provide the details on the event or system state to help users decide what to do next. Consider answering questions:
* What was the cause of this state?
* What are the consequences?
* What is affected: files, libraries, versions, plugins, etc.?

#### Using only title or body

Use only the title when it is short and fits in one line.

![](notification_balloon_title_only.png){width=706}

Use only the body text when it fits in two lines and the title would duplicate its meaning.

<format color="Red" style="bold">Incorrect</format>
![](notification_balloon_body_only_incorrect.png){width=706}

<format color="Green" style="bold">Correct</format>
![](notification_balloon_body_only_correct.png){width=706}


#### Two lines of body text are visible by default

Only the first two lines of the body text are visible by default, the rest is shown when expanded. Place the most important information in the beginning of the body text so it is visible by default.

![](notification_balloon_body_two_lines.png){width=706}


### Actions

Add actions to help users take the next steps: fix a problem, view relevant information, configure settings, etc.


#### Number of actions

The preferable number of actions is two, as it is easier for the user to choose. If more than two actions are useful, place the most likely to be used first, and hide the others under the "More" dropdown:

![](notification_balloon_more_actions.png){width=706}


#### Actions in error and warning notifications
If the notification reports an error or warning, always provide an action to help users fix the problem:

![](notification_balloon_error_actions.png){width=706}

If no actions are available, provide more details in the body text: how to fix or what was the cause.

<format color="Red" style="bold">Incorrect</format>
![](notification_balloon_error_actions_text_incorrect.png){width=706}

<format color="Green" style="bold">Correct</format>
![](notification_balloon_error_actions_text_correct.png){width=706}


#### "Don't show again"

If the notification is informational and there is a possibility it might appear too often, add the "Don’t show again" action:

![](notification_balloon.png){width=706}


#### Capitalization

Use sentence capitalization for all actions in notification balloons.



## Naming a notification group

Each notification balloon belongs to a group. Groups can be seen in <ui-path>Settings | Appearance & Behavior | Notifications</ui-path>.

To name a notification group, follow these rules:

* Name the group with an ending to the phrase "Notifications in this group notify the user about…". Examples: _Automatic indent detection_, _Content root duplicates_.
* If a name about a particular process or event cannot be given, use the name of a subsystem or plugin. Examples: _HTTP Client_, _Power Save Mode_.
* When a group contains notifications about errors or problems, do not use a verb. Example: _Debugger errors_, not _Debugger errors ~~found~~_.
* Do not use words "notification" or "group". They are implied from the settings context.

