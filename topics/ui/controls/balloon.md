<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notification Balloon

<link-summary>UI guidelines on using notification balloons.</link-summary>

<tldr>

**Implementation:** [`Notifications`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java)

**Related:** [](notifications.md#balloons)

</tldr>

Notification balloons inform users of the events or system states related to a project or IDE.

![A notification balloon for a found database connection with an action to connect to it](notification_balloon.png){width=706}

## Suggestion and timeline notifications

There are two types of notification balloons:
* **Suggestion** notifications show the recommended action as a noticeable button. Use to suggest an automatic configuration for a project or an IDE, or to ask for user input.
* **Timeline** notifications show useful actions as links. Use for showing results of long processes, automatic configuration, or actions without context.

These types appear in different sections of the Notifications tool window:

![The Notifications toolwindow with suggestion and timeline notifications in different sections](notification_balloon_toolwindow.png){width=706}

## When to use

### Show result of a potentially long process

Use a timeline notification.

![Two notifications with results of long processes: project build and git push](notification_balloon_timeline_long_process.png){width=706}

### Show result of an action without context

Use a timeline notification when an action was called from a context that is already closed, like a dialog or a popup.

![Two notifications with results of actions that are not tied to a particular dialog: the UI language was changed and project folders excluded from Windows Defender](notification_balloon_timeline_no_context_action.png){width=706}

### Show result of an automatic configuration

Use a timeline notification when the project or IDE settings were automatically configured, and the user might need to check the changes.

![A notification about automatic project configuration: the workspace is restored after a branch is checked out](notification_balloon_timeline_automatic_configuration.png){width=706}

### Suggest an action to configure a project or an IDE

Use a suggestion notification to promote an action for project or IDE configuration when not taking this action might lead to less optimal functioning or errors.

![Two examples of a suggestion notification for project or IDE configuration: Windows Defender affecting the IDE with a suggestion to explude a project from it, and a suggestion for switching the UI to an available language](notification_balloon_suggestion_configuration.png){width=706}

### Request user input

Use a suggestion notification to ask the user for an additional input or action that is not connected to their own workflows.

![Two suggestion notifications for requiring user input: a request to fill a survey and a notice about a discount for PhpStorm](notification_balloon_suggestion_request_input.png){width=706}


## How to use

### Sticky or timed

**Sticky** notifications stay on screen until the user clicks any of its actions or closes it. By default, use this behavior for [suggestion notifications](#suggestion-and-timeline-notifications).

**Timed** notifications stay on screen for 10 seconds and then hide. By default, use this behavior for [timeline notifications](#suggestion-and-timeline-notifications).

Change the default behavior if it makes sense in a particular use case.


### Message severity

#### Error

Use to inform of a critical event or state that might disrupt the user experience.

![An error notification about a plugin being suspended because it requires another plugin that is missing](notification_balloon_severity_error.png){width=706}

#### Warning

Use in case an event or state might slow down the user's work or require an action to fix the project or IDE settings.

![A warning notification about IDE shortcuts conflicting with macOS shortcuts](notification_balloon_severity_warning.png){width=706}

#### Information

Use in all other cases.

![An information notification requesting to fill a survey about a plugin](notification_balloon_severity_info.png){width=706}

<br/>

When possible, use a plugin or functionality icon instead of the <control>Info</control> icon. This helps identifing the source of the notification quicker.

![Two notifications with dedicated icons instead of the generic info icon: database connection found with the database icon and suggested plugins found with the generic plugin icon](notification_balloon_plugin_icon.png){width=706}


### Text

#### Writing

[Write short and clear](writing_short.md) as notifications have limited space and may appear for a short time.

Use sentence case for both the title and the body.

Follow the [punctuation rules](punctuation.md).

#### Title
Describe the event and the context in which it occurred. The context could be the name of a plugin, library, or functionality.

#### Body
Provide the details on the event or system state to help users decide what to do next. Consider answering these questions:
* What was the cause of this state?
* What are the consequences?
* What is affected: files, libraries, versions, plugins, etc.?

#### Using only title or body

Use only the title when it is short and fits in one line.

![A notification with the title only about the number of files updated after git pull](notification_balloon_title_only.png){width=706}

Use only the body text when it fits in two lines and the title would duplicate its meaning.

<format color="Green" style="bold">Correct</format>
![A notification with the body only about the workspace being restored after branch checkout](notification_balloon_body_only_correct.png){width=706}

<format color="Red" style="bold">Incorrect</format>
![The same notification as above but with duplicating information in the title](notification_balloon_body_only_incorrect.png){width=706}

#### Long messages are collapsed

Only the first two lines of the body text are visible by default, the rest is shown when expanded. Place the most important information in the beginning of the body text so it is visible by default.

![A notification about Windows Defender slowing down the IDE in the collapsed and expanded states. The notification body is long and takes 9 lines](notification_balloon_body_two_lines.png){width=706}


### Actions

Add actions to help users take the next steps: fix a problem, view relevant information, configure settings, etc.


#### Number of actions

The preferable number of actions is two, as it is easier for the user to choose. If more than two actions are useful, place the most likely to be used first, and hide the others under the <control>More</control> dropdown:

![The Windows Defender notification with the primary action "Exclude folders" on the left and 3 more actions under the "More" dropdown link](notification_balloon_more_actions.png){width=706}


#### Actions in error and warning notifications
If the notification reports an error or warning, always provide an action to help users fix the problem:

![An error notification about the plugin being suspended because it requires another plugin to work with actions "Manage plugins" and "Disable the suspended plugin"](notification_balloon_error_actions.png){width=706}

If no actions are available, provide more details in the body text: how to fix or what was the cause.

<format color="Green" style="bold">Correct</format>
![A notification about a plugin being incompatible with the current IDE version with a suggestion to update the plugin](notification_balloon_error_actions_text_correct.png){width=706}

<format color="Red" style="bold">Incorrect</format>
![The name notification but without the suggestion to update the plugin](notification_balloon_error_actions_text_incorrect.png){width=706}


#### The "Don't show again" action

If the notification is informational, and there is a possibility it might appear too often, add the <control>Don’t show again</control> action:

![A notification about a database connection found with the "Don't show again link"](notification_balloon.png){width=706}


#### Capitalization

Use sentence capitalization for all actions in notification balloons.



## Naming a notification group

Each notification balloon belongs to a group. Groups can be seen in <ui-path>Settings | Appearance & Behavior | Notifications</ui-path>.

To name a notification group, follow these rules:

* Name the group with an ending to the phrase "Notifications in this group notify the user about…". Examples: _Automatic indent detection_, _Content root duplicates_.
* If a name about a particular process or event cannot be given, use the name of a subsystem or plugin. Examples: _HTTP Client_, _Power Save Mode_.
* When a group contains notifications about errors or problems, do not use a verb. Example: _Debugger errors_, not _Debugger errors ~~found~~_.
* Do not use words "notification" or "group". They are implied from the settings context.

