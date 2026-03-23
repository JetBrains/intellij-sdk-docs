<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notification Balloons

<tldr>

**UI Guidelines:** [](balloon.md)

**Product Help:** [Notifications](https://www.jetbrains.com/help/idea/notifications.html)

**Video Overview:** [Notification Baloons](https://www.youtube.com/watch?v=e0ietjbbTms)

</tldr>

<link-summary>Showing relevant information without interrupting the flow using notification balloons.</link-summary>

Sometimes, it is required to show relevant information without interrupting the developer’s flow.
The most general way is to use *notification balloons*.
They inform users of the events or system states related to a project or IDE.

To display a notification, provide two things.
First, a notification group to act as a configurable channel.
Then, a notification instance itself.

## Notification Group

Declare a notification group as an extension in the plugin descriptor.
It is registered using the [`com.intellij.notificationGroup`](https://jb.gg/ipe?extensions=com.intellij.notificationGroup) extension point.
Set the display type to `BALLOON` constant. Then, provide a human-readable identifier.

```xml
<extensions defaultExtensionNs="com.intellij">
   <notificationGroup id="Bagel" displayType="BALLOON" />
</extensions>
```

> A good ID completes this phrase: "Notifications in this group tell the user about…". See the [UI Guidelines for further tips on naming conventions](balloon.md#naming-a-notification-group).
>
{style="tip"}

## Timeline notification

Next, create a new [`Notification`](%gh-ic%/platform/ide-core/src/com/intellij/notification/Notifications.java) instance.
The constructor takes three arguments:

1. Notification group ID
2. Content text
3. Type (controls the icon that's displayed)

Use the `notify` method and pass in the current project to show the notification in its associated frame.

```kotlin
Notification("Bagel", "Bagel was eaten", NotificationType.INFORMATION)
   .notify(e.project)
```

> Use [Plugin DevKit](https://plugins.jetbrains.com/plugin/22851-plugin-devkit/) to get the code insight for notification group identifiers.
>
{style="tip"}

By default, a notification is *timed*, as it will automatically disappear after 10 seconds. However, it will remain in the <control>Notifications</control> tool window, until cleared, in the *Timeline* section.

## Configuring Notifications

Notifications are configured in <ui-path>Settings | Appearance & Behavior | Notifications</ui-path>.
Observe the <control>Bagel</control> notification group with <control>Popup type</control> set to <control>Balloon</control> and <control>Show in tool window</control> being checked.

See [Notifications Product Help](https://www.jetbrains.com/help/idea/notifications.html#notifications_tool_window) for specific user-facing options.

## Notification Icons

Customize the notification icon according to [message severity](balloon.md#message-severity).
Use the constants from [`NotificationType`](%gh-ic%/platform/ide-core/src/com/intellij/notification/NotificationType.java).

1. Generic information: `INFORMATION`.
2. Disruptions or events requiring user action: `WARNING`.
3. Critical events or states: `ERROR`.

When possible, use a plugin or functionality icon instead of the <control>Information</control> icon.
See the [Icons](#icons) section for more information.

## Notification Actions

A notification balloon [can contain actions](balloon.md#actions), rendered as a link or button.

```kotlin
Notification("Bagel", "Bagel was eaten", NotificationType.INFORMATION)
  .addAction(NotificationAction.createSimpleExpiring("Track calories") {
    // add an action
  })
  .notify(e.project)
```

Chain `addAction` method with `createSimpleExpiring` method, which provides a trailing lambda for the action handler.
When the user clicks it, the notification dismisses automatically.
However, it will stay in the Notification tool window with the hyperlink greyed-out.

### Multiple Actions

Anything beyond two actions [gets hidden](balloon.md#number-of-actions) in a <control>More</control> menu.
Put the most important actions first.

## Notification Title and Body

To give more context, [use a title and a body](balloon.md#text).
The title briefly describes what happened, and the body explains the impact or what the user can do about it.
The overloaded constructor takes an extra string before the content – that’s the title.

```kotlin
Notification("Bagel", "Bagel was eaten", getBagelCounterMessage(), NotificationType.INFORMATION)
  .notify(e.project)
```

> The notification body may contain HTML code for presentation purposes.
>
{style="note"}

## Suggestions

In some cases, the functionality needs to prompt or notify the user to take action or provide input.

[*Suggestions*](balloon.md#suggest-an-action-to-configure-a-project-or-an-ide) show the primary action as a noticeable button.
Unlike timed notifications, suggestions won’t go away on their own.
The user has to act and dismiss them explicitly.
Their notification group is configured as `STICKY_BALLOON`.

```xml
<notificationGroup id="Bagel File"
                   displayType="STICKY_BALLOON" />
```

To mark the notification as a suggestion, set its suggestion type to `true`.

```kotlin
Notification("Bagel File", "Bagel file detected", NotificationType.INFORMATION)
  .setSuggestionType(true)
  // ...
```

Suggestions have a dedicated section in the <control>Notifications</control> tool window.

> A `Notification` has the `expire` method for explicit expirations.
>
{style="tip"}

## Tool Window Notifications

A [tool window](tool_windows.md) can trigger a long-running operation.
For example, the <control>Find in Files</control> action takes a couple of seconds to search for a string in a large project tree, doing that in the background.
When there are no matches, a notification balloon is shown.
However, instead of the usual location, the notification balloon is displayed directly next to the tool window icon.
In the plugin descriptor, declare the notification group with a display type set to `TOOL_WINDOW`.
As the notification group’s notifications are explicitly bound to a specific tool window, provide its identifier in the `toolWindowId` attribute.

```xml
<notificationGroup
  id="Order in Bakery"
  displayType="TOOL_WINDOW"
  toolWindowId="Bakery"
/>

<toolWindow
  id="Bakery"
  ...
/>
```

The `toolWindowId` matches the `id` value declared in the `com.intellij.toolWindow` extension.

In the code, use the standard `Notification.notify` method to show it.

```kotlin
Notification("Order in Bakery", "Bagel is ready", NotificationType.INFORMATION)
  .addAction(NotificationAction.createSimpleExpiring("Eat bagel") {
      // handle 'Eat bagel' link
   })
notification.notify(e.project)
```

Such notification is automatically dismissed on any keypress or mouse click.
Due to space constraints, the notification title should be as short as possible.
Additionally, use at most one notification action, which will be rendered as a hyperlink.
Generally, the tool window notifications don’t need to be shown in the <control>Notifications</control> tool window.
See the [corresponding section](#notification-balloons-without-entries-in-the-notifications-tool-window) for the implementation.

## Icons

The [UI guidelines recommend](balloon.md#information) using a plugin or functionality icon instead of the generic _Information_ icon.
[Provide an icon](icons.md#icons-class) along with its accompanying constant.
The `Notification.setIcon` overrides the icon from the constructor argument.

```kotlin
Notification("Bagel", "Bagel was eaten", NotificationType.INFORMATION)
  .setIcon(Icons.Bagel)
  //...
```

## Localization

The notification group identifier is not a technical identifier, but a human-readable string directly mapped to the IDE settings user interface.
However, it can be localized.

> See [Bundled Translations](providing_translations.md#bundled-translations) for more information about directory layout and resource bundle formats.
>
{style="tip"}

Make sure that the plugin descriptor declares a [plugin resource bundle](plugin_configuration_file.md#idea-plugin__resource-bundle):

```xml
<resource-bundle>messages.BagelBundle</resource-bundle>
```

Then declare a `key` element that contains a localized notification group ID.

```xml
<notificationGroup id="Bagel File"
  key="bagel.file.notification.group"
  displayType="STICKY_BALLOON" />
```

> The `id` attribute is mandatory, even if it is localized in the default resource bundle key.
 Alternatively, provide a resource bundle name in the `bundle` attribute to override a resource bundle name from the `<resource-bundle>` element.
>
{style="warning"}

## Configuring notification display settings

Some use-cases require a specific display configuration for the notification balloons.
Use them carefully, as they might go against the default user experience guidelines.

### Notification balloons without entries in the Notifications tool window

Occasionally, there are notifications that do not need to be logged in the <control>Notification</control> tool window.
This is the standard case with Tool Window notifications.
However, there are other usages as well.
A <control>Hotswap Reload</control> action or a <control>Breakpoint Hit</control> is shown as notification balloons and then completely hidden.
Such a notification group has the `isLogByDefault` attribute set to `false`.
This matches the <control>Show in tool window</control> option being disabled.

### Notifications tool window entry without a notification balloon

To log a new entry into the <control>Notifications</control> tool entry without showing a balloon, configure the `displayType` to `NONE`.
This matches the <control>Popup type</control> list being set to <control>No popup</control>.

## Obsolete API

Previously, there were multiple SDK approaches to notification balloons.
They are considered to be obsolete or too complex.

- `NotificationGroup` is considered an internal data structure.
Its factory methods are replaced with `Notification` constructor and subsequent builder methods.
The `NotificationGroupManager`, its `getNotificationGroup` method are no longer necessary, as the reference to the notification group is resolved via the notification group identifier.
- The `Notifications.Bus.notify` method can be replaced with the `notify` method on the `Notification` instance.
- `NotificationsManager` is considered to be internal.
Its methods should be replaced by the corresponding methods in the `Notification` class.

# Summary

- A *notification group* is a user-configurable channel for notifications.
Directly maps to a configuration item in the IDE settings.
- *Timeline notification* shows for 10 seconds and is automatically dismissed.
The primary action is shown as a hyperlink.
Uses a `BALLOON` constant in the notification group.
- *Suggestion* needs to be dismissed explicitly.
The primary action is shown as a button.
Uses a `STICKY_BALLON` constant in the notification group.
Needs to set `setNotificationType(true)` on the `Notification` instance.
Suggestions are shown in a dedicated section in the *Notifications* tool window.
- *Tool Window Notification* visually points to the specific tool windows.
The primary action is shown as a hyperlink.
It is automatically dismissed on click or keypress.
- Use the `Notification` class to create instances, its builder methods to configure, and the `notify` method to show a notification.

