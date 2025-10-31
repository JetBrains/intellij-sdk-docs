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

If an indeterminate process reaches a point where its duration can be determined, switch to a determinate progress bar:

![](04 determinate_to_indeterminate.png){width=706}

### Process name and details

A progress bar can have a process name and process details.

#### Process name
Show the name of the operation being performed above the progress bar:
![](05_process name.png){width=706}

If the vertical space is limited, place the process name with a colon on the left:
![](07 process_name_on_the_left.png){width=706}

#### Process details
Show information about the current stage of an operation.
Details make long-running tasks more predictable and manageable.</br>
Show the process details below the progress bar on a single line. The length of the process details is limited by the width of the progress bar.
![](05_process details.png){width=706}

Details example:
* The current step: Uploading file 3 of 10
* The name of a file, module, or library: Fetching guava-31.1.jar
* Remaining time or percentage completed.

For wording, follow the rules for [progress text](progress_text.md).

### Process controls

Place process controls on the right next to the progress bar. On hover over the icon, show the name of the control under the progress bar instead of process details.
![](17 stop_button.png){width=706}

#### Cancel or Stop button
Add a Cancel button if the process can be safely interrupted. If interrupting the process does not restore the system to its previous state, name the button Stop.
![](17 cancel_button.png){width=706}

#### Pause button

If the process takes a long time and can prevent the user from performing tasks, provide an option to pause a process.

> It is recommended to run the process in the background so it doesn’t interrupt the user, instead of offering a pause option.
>
{style="note"}

<table style="none" border="false">
  <tr>
    <td width="378">
      <img src="18 pause_button.png" alt="Pause button"/>
    </td>
    <td>
      <p>Replace process details with the "Pause" comment on hover over the Pause icon..</p>
    </td>
  </tr>
  <tr>
     <td width="378">
         <img src="19 resume_button.png" alt="Process on pause"/>
        </td>
        <td>
        <p>If a user pauses the process, show "Paused" under the progress bar. Replace the Pause icon with Resume.</p>
     </td>
  </tr>
  <tr>
     <td width="378">
         <img src="20 resume_button.png" alt="Resume button"/>
        </td>
        <td>
        <p>Show "Resume" under the progress bar and when hovered over the Resume button.</p>
     </td>
  </tr>
</table>

### Placement

If you need to use the progress bar among other controls, place it close to the control that starts the process.
![](23 placement.png){width=706}

#### In dialog or popup

> Place the progress bar in a modal dialog if the IDE cannot function properly until  the process is completed.
>
{style="note"}

Use a process name as a dialog or popup header, capitalize the title and remove the ellipsis. Process details appear above the progress bar.
For a single progress bar, use a button to cancel or pause the process instead of an icon:
![](13 in_dialog.png){width=706}


For several processes in a group, add a common header and use icons for process controls:
![](16 process_control.png){width=706}

#### Inline progress

Use inline progress to indicate that content is being updated.
The user can continue working with the content, but should see that a background process is in progress.</br>
Do not show a process name for inline progress:
![](09 inline_processes.png){width=706}

#### Process status

If a process consists of substeps that can fail but do not terminate the process, then use green and red colors to show the intermediate status.
For example, show the status of the running tests:
![](15 process_status.png){width=706}

#### Process complition

Hide the progress bar as soon as the process complets.

