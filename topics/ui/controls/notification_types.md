<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notifications

<link-summary>UI guidelines on using notifications.</link-summary>

Notifications inform users about the status of user or system initiated operations. They can have different actions depending on the message.

There are four types of notifications:

- Alert
- [Banner](banner.md)
- [Balloon](balloon.md)
- Tool window balloon

## What notification to use

Consider two factors when deciding which notification type to use.

**User action**

<p> Are the users required to address the notification before they can proceed with current tasks?</p>
  - Required immediately
  - Required, but not immediately
  - Not required

**Context of trigger**

<p> What initiated the notification? Does the initiation point to a particular context or location?</p>
  - File tab
  - Tool windows
  - Other

Use the following table to determine which notification to use based on the two factors:

<table>
  <tr>
  <td width="16%">User action</td>
  <td width="16%">Context</td>
  <td width="16%">Type</td>
  <td width="52%">Examples</td></tr>
  <tr>
    <td>Required immediately
    </td>
    <td>All
    </td>
    <td>Alerts
    </td>
    <td>
      <p>Confirm Restart</p>
      <p>Opening projects in new window</p>
      <p>When trying to rename a method, but a conflict is found</p>
      <p>Need a dependency before using a feature</p>
      <p><img src="alert.png" width="406" /></p>
   </td>
  </tr>
  <tr>
    <td>Required, but not immediately</td>
    <td>
      <p>File tabs</p>
      <p>Tool windows</p>
    </td>
    <td>Banners</td>
    <td>
      <p>Configuring SDK for your project</p>
      <p>Requiring a Gradle sync for tools to work properly</p>
      <p><img src="banner.png" width="431" /></p>
    </td>
    </tr>
  <tr>
    <td>Not required</td>
    <td>Tool windows</td>
    <td>Tool Window balloons</td>
    <td>
      <p>Status of task completion</p>
      <p>When Find Usages is invoked on a method, use a tool window balloon to show the feedback since the results will be found in the Find tool window</p>
      <p><img src="toolwindow_balloon.png" width="208" /></p>
    </td>
  </tr>
  <tr>
    <td></td>
    <td>All but file tabs or tool windows</td>
    <td>Sticky balloons</td>
    <td>
      IDE and Plugin Updates
      <img src="sticky_toast.png" width="391" />
    </td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>Timed balloons</td>
    <td>
      <p>Module imported</p>
      <p>Framework detection</p>
      <p><img src="timed_toast.png" width="391" /></p>
    </td>
  </tr>
</table>

**Exception**: If the action is highly recommended, consider using Banners across all files for visibility instead of Sticky balloon.
