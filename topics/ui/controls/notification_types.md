<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notifications

Notifications inform users about the status of user or system initiated operations. They can have different actions depending on the message.

There are four types of notifications:

- [Alert](alert.md)
- [Banner](banner.md)
- [Balloon](balloon.md)
- [Tool window balloon](tool_window_balloon.md)

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
  <tr><td>User action</td>
  <td>Context</td>
  <td>Type</td>
  <td>Examples</td></tr>
  <tr>
    <td>Required immediately
    </td>
    <td>All
    </td>
    <td>Alerts
    </td>
    <td>
      Confirm Restart<br/><br/>
      Opening projects in new window<br/><br/>
      When trying to rename a method, but a conflict is found<br/><br/>
      Need a dependency before using a feature<br/>
      <img src="../../../images/ui/notifications/alert.png" />
   </td>
  </tr>
  <tr>
    <td>Required, but not immediately</td>
    <td>
      File tabs<br/><br/>
      Tool windows
    </td>
    <td>Banners</td>
    <td>
      Configuring SDK for your project<br/><br/>
      Requiring a Gradle sync for tools to work properly<br/>
      <img src="../../../images/ui/notifications/banner.png" />
    </td>
    </tr>
  <tr>
    <td>Not required</td>
    <td>Tool windows</td>
    <td>Tool Window balloons</td>
    <td>
      Status of task completion<br/><br/>
      When Find Usages is invoked on a method, use a tool window balloon to show the feedback since the results will be found in the Find tool window<br/>
      <img src="../../../images/ui/notifications/toolwindow_balloon.png"  />
    </td>
  </tr>
  <tr>
    <td></td>
    <td>All but file tabs or tool windows</td>
    <td>Sticky balloons</td>
    <td>
      IDE and Plugin Updates
      <img src="../../../images/ui/notifications/sticky_toast.png" />
    </td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>Timed balloons</td>
    <td>
      Module imported<br/><br/>
      Framework detection
      <img src="../../../images/ui/notifications/timed_toast.png" />
    </td>
  </tr>
</table>

**Exception**: If the action is highly recommended, consider using Banners across all files for visibility instead of Sticky balloon.
