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

Choose a notification type based on a combination of these factors:

1. **User action:** is it required to proceed?
    - Required immediately
    - Required, but not immediately
    - Not required

2. **Trigger location:** from where the notification was created? Options:
    - Editor
    - Tool window
    - Dialog
    - Any other location


### Alert
- User action: required
- Trigger location: any

Examples: confirm restart or exit, choose were to open a new project.

### Banner


### Notification balloon

<table>
  <tr>
  <td width="16%">User action</td>
  <td width="16%">Location</td>
  <td width="16%">Type</td>
  <td width="52%">Examples</td></tr>
  <tr>
    <td>Required immediately
    </td>
    <td>Any
    </td>
    <td>Alert
    </td>
    <td>
      <p>Confirming restart</p>
      <p>Opening projects in new window</p>
      <p>When trying to rename a method, but a conflict is found</p>
      <p><img src="alert.png" width="406" /></p>
   </td>
  </tr>
  <tr>
    <td>Required, but not immediately</td>
    <td>
      <p>Editor</p>
      <p>Tool window</p>
    </td>
    <td>Banner</td>
    <td>
      <p>Configuring SDK for your project</p>
      <p>Requiring a Gradle sync for tools to work properly</p>
      <p><img src="banner.png" width="431" /></p>
    </td>
    </tr>
  <tr>
    <td></td>
    <td>Other locations</td>
    <td>Sticky balloon</td>
    <td>
      IDE and plugin updates
      <img src="sticky_toast.png" width="391" />
    </td>
  </tr>
  <tr>
    <td>Not required</td>
    <td>Tool window</td>
    <td>Tool window balloon</td>
    <td>
      <p>Status of task completion</p>
      <p>When Find Usages is invoked on a method, use a tool window balloon to show the feedback since the results will be found in the Find tool window</p>
      <p><img src="toolwindow_balloon.png" width="208" /></p>
    </td>
  </tr>
  <tr>
    <td></td>
    <td></td>
    <td>Timed balloon</td>
    <td>
      <p>Module imported</p>
      <p>Framework detection</p>
      <p><img src="timed_toast.png" width="391" /></p>
    </td>
  </tr>
</table>
