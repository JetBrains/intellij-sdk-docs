<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Progress Bar

<link-summary>UI guidelines on using progress bars.</link-summary>

<tldr>

**Implementation:** [`JProgressBar`](https://docs.oracle.com/javase/tutorial/uiswing/components/progress.html)

</tldr>

A progress bar informs users about the progress of a lengthy operation.

![](01 progress_bar.png){width=706}

## When to use

Follow the rules for [progress indicators](progress_indicators.md).

## How to use

### Types
Use a determinate progress bar if the process duration is known. Otherwise, use **indeterminate** progress bar:

![](02 determinate_and_ indeterminate.png){width=706}

If an indeterminate process reaches a point where its duration can be determined, switch to a determinate progress bar. For example:

![](04 determinate_to_indeterminate.png){width=706}

### Process name and details

A progress bar can have a process name and process details. For wording, follow the rules for [progress text](progress_text.md).

#### Process name
Process name — the name of the operation being performed.

<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="06 process_name.png" alt="Process name above the bar"/>
    </td>
    <td>
      <p>Place a process name above the progress bar.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="07 process_name_on_the_left.png" alt="Process name on the left"/>
    </td>
    <td>
      <p>If the vertical space is limited, place the process name with a colon on the left.</p>
    </td>
  </tr>
  <tr>
     <td width="378">
         <img src="08 header_for_several_processes.png" alt="Header for several processes"/>
        </td>
        <td>
        <p>If there are several processes in a group, add a bold header.</p>
     </td>
  </tr>
  <tr>
     <td width="378">
         <img src="09 inline_processes.png" alt="Inline process"/>
        </td>
        <td>
        <p>Do not show a process name for inline progress.</p>
     </td>
  </tr>
</table>

#### Process details
Process details — information that shows the user what stage the current operation is at and what it relates to. This is especially important for long-running tasks, as it makes the process more predictable and manageable.

Process details may include:

* The current step, for example, Uploading file 3 of 10.
* The remaining time or percentage completed.
* The name of the file, module, or library being processed, for example, Fetching guava-31.1.jar.
* Any other useful information about the operation’s progress.
<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="11 process_details.png" alt="Process details under the bar"/>
    </td>
    <td>
      <p>Place process details under the progress bar in one line. The length of the comment is limited by the progress bar.</p>
    </td>
  </tr>
</table>

#### In dialog

Use a process name as a dialog header, capitalize the title and remove ellipsis. Process details appear above the progress bar:

![](13 in_dialog.png){width=706}

#### In status bar

Place a process name with an ellipsis before the progress bar:

![](14 in_status_bar.png){width=706}

### Process status

If a process consists of substeps that can fail but do not terminate the process, then use green and red colors to show the intermediate status.
For example, show the status of the running tests:

![](15 process_status.png){width=706}

Do **not** color progress bar to show the final result of the task, use [notifications](notification_types.md).
In case of success, show notification for the user-initiated tasks, in case of failure — for all tasks.

### Process control

#### Cancel or Stop button
Provide the Cancel button in the progress dialog if the process can be interrupted (see [Loading Project dialog](#in-dialog)).
Use the Stop button if interrupting does not return the environment to its previous state.

#### Stop icon
Use the Stop icon when multiple processes are running simultaneously in one dialog or when there isn’t enough space to display individual buttons (e.g., [Status bar](#in-status-bar)):

![](16 process_control.png){width=706}

#### Placement and Behavior
<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="17 stop_button.png" alt="Stop icon on the progress bar"/>
    </td>
    <td>
      <p>Always place the Stop icon on the right next to the progress bar. On hover over the Stop icon, show the "Stop" or "Cancel" comment under the progress bar instead of process details.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="17 pause_button.png" alt="Pause button on the progress bar"/>
    </td>
    <td>
      <p>If the process takes a long time and can prevent the user from performing tasks, provide an option to pause a process using the Pause button or the Pause icon.</p>
    </td>
  </tr>
    <tr>
     <td width="378">
         <img src="18 pause_button.png" alt="Pause under the progress bar with hover effect"/>
        </td>
        <td>
        <p>Replace process details with the "Pause" comment on hover over the Pause icon.</p>
     </td>
    </tr>
    <tr>
     <td width="378">
         <img src="19 resume_button.png" alt="Resume button on the progress bar"/>
        </td>
        <td>
        <p>If a user pauses the process, show "Paused" under the progress bar. Replace the Pause icon with Resume.</p>
     </td>
    </tr>
    <tr>
     <td width="378">
         <img src="20 resume_button.png" alt="Resume under the progress bar with hover effect."/>
        </td>
        <td>
        <p>Show "Resume" under the progress bar and when hovered over the Resume button.</p>
     </td>
    </tr>
</table>

#### Pause Option Recommendations

It is **not** recommended providing an option to pause the process. It is preferable that the process runs in the background and does not interfere with a user.

#### Process Completion
Hide the progress bar as soon as the process completes.

## Sizes and placement

The progress form and sizes are the same in all themes.

![](21 size_and_placement.png){width=706}

![](22 size_and_placement.png){width=706}



