<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Progress Bar

<link-summary>UI guidelines on using progress bars.</link-summary>

<tldr>

**Implementation:** [`JProgressBar`](https://docs.oracle.com/javase/tutorial/uiswing/components/progress.html)

</tldr>

A progress bar informs users about the progress of a lengthy operation.

![](determinate_example.png){width=308}

## When to use

Follow the rules for [progress indicators](progress_indicators.md).

## How to use

### Types

Use **determinate** progress bar, if the process duration is known:

![](determinate_example.png){width=308}

Otherwise, use **indeterminate** progress bar:

![](indeterminate_example.png){width=308}

If an indeterminate process reaches a point where its duration can be determined, switch to a determinate progress bar. For example:

![](progress_bar_indeterminate.png){width=308}

![](progress_bar_determinate.png){width=308}

### Process name and details

A progress bar can have a process name and process details. For wording, follow the rules for [progress text](progress_text.md).

#### Process name

Place a process name above the progress bar:

![](label_above.png){width=308}

If the vertical space is limited, place the process name with a colon on the left:

![](progress_bar_label_left.png){width=308}

If there are several processes in a group, add a bold header. The header is a noun.

![](several_progresses.png){width=308}

Do **not** show a process name for inline processes:

![](progress_bar_tool_window.png){width=605}

#### Process details

Place process details under the progress bar in one line:

![](comment.png){width=308}

The length of the comment is limited by the progress bar:

![](comment_long.png){width=308}

If space is limited, show percentage completed:

![](horizontaly.png){width=342}

#### In dialog

Use a process name as a dialog header, capitalize the title and remove ellipsis. Process details appear above the progress bar:

![](dialog.png){width=544}

#### In status bar

Place a process name under the progress bar in the Status bar:

![](status_bar.png){width=238}

### Process status

If a process consists of substeps that can fail but do not terminate the process, then use green and red colors to show the intermediate status.
For example, show the status of the running tests:

![](progress_color.png){width=390}

Do **not** color progress bar to show the final result of the task, use [notifications](notification_types.md).
In case of success, show notification for the user-initiated tasks, in case of failure — for all tasks.

### Process control

Provide the Cancel button in the progress dialog if the process can be interrupted (see [Loading Project dialog](#in-dialog)).
Use the Stop button if interrupting does not return the environment to its previous state.

Use the Stop icon if there are several processes running at the same time in one dialog or there is not enough space for the button (e.g., [Status bar](#in-status-bar)):

![](tasks_dialog.png){width=700}

Always place the Stop icon on the right next to the progress bar. On hover over the Stop icon, show the "Stop" or "Cancel" comment under the progress bar instead of process details:

![](hover_stop_icon.png){width=332}

If the process takes a long time and can prevent the user from performing tasks, provide an option to pause a process using the Pause button or the Pause icon.
Replace process details with the "Pause" comment on hover over the Pause icon:

![](pause.png){width=331}

It is **not** recommended providing an option to pause the process. It is preferable that the process runs in the background and does not interfere with a user.

If a user pauses the process, show "Paused" under the progress bar.
Replace the Pause icon with Resume, show "Resume" under the progress bar and when hovered over the Resume button:

![](resume.png){width=330}

Hide the progress bar as soon as the process completes.

## Sizes and placement

The progress form and sizes are the same in all themes.

![](progress_bar_sizes.png){width=586}




