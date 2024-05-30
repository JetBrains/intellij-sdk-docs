<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<tldr>

**Related:** [](ui_faq.md#text)

</tldr>

# Data Formats

<link-summary>Choosing proper date formats to display data to users.</link-summary>

## Numbers

> If there is a space between the number and the unit, use a non-breaking space.
>
{style="note"}

<table>
<tr>
    <td width="20%"></td>
    <td width="25%"> Examples </td>
    <td width="55%"> When and how to use </td>
</tr>
<tr>
    <td>Numbers</td>
    <td>4,255,165.00</td>
    <td><p>Space is not limited, e.g. <control>Settings</control> dialog:</p>
        <p><img src="numbers.png" width="277" /></p>
    </td>
</tr>
<tr>
    <td>Numbers short</td>
    <td>
        <p>10.5k</p>
        <p>3.3M</p>
    </td>
    <td>Space is limited, e.g. in table or lists.</td>
</tr>
<tr>
    <td>Percentage</td>
    <td>13%</td>
    <td></td>
</tr>
<tr>
    <td>Ratios</td>
    <td>3 of 10</td>
    <td></td>
</tr>
<tr>
    <td>Ratios short</td>
    <td>3/10</td>
    <td>Space is limited, e.g. in table or lists.</td>
</tr>
<tr>
    <td>Currency</td>
    <td>$10</td>
    <td></td>
  </tr>
</table>

## Date and time

By default, the date and time formats are taken from the system settings. The format can be changed on the <ui-path>Settings | Appearance & Behavior | System Settings | Date Formats</ui-path> page.

See the table below to understand in which cases to show date and time.

<table>
<tr>
<td width="20%"></td>
<td width="25%"> Examples </td>
<td width="55%"> When and how to use </td></tr>
<tr>
    <td>Date</td>
    <td>
        <p>Today</p>
        <p>05 Jan</p>
        <p>05 Jan 2017</p>
    </td>
    <td>
        <p>Omit the time for events if the exact time does not matter, e.g., Annotate panel.</p>
        <p>Use <i>Today</i> and <i>Yesterday</i> for the last two days, use exact date for all other days.</p>
        <p>Do <b>not</b> show the year if it’s within the current calendar year.</p>
    </td>
</tr>
<tr>
    <td>Date and time</td>
    <td>
        <p>Today, 14:04</p>
        <p>05 Jan, 14:04</p>
        <p>05 Jan 2019, 14:04</p>
    </td>
    <td>When it is important to know the exact sequence of actions, e.g., Version Control Log.</td>
</tr>
<tr>
    <td>Time</td>
    <td>22:49</td>
    <td>Omit date if the event happened today and it’s clear from the context.</td>
</tr>
<tr>
    <td>Date and day of week</td>
    <td>Tuesday, 30 August</td>
    <td>
        <p>Use as headers in timelines, e.g., Event Log. The day of the week helps to quickly understand how long ago was the event.</p>
        <p>Use <i>Today</i> and <i>Yesterday</i> for the last two days, use exact date for all other days.</p>
    </td>
</tr>
<tr>
    <td>Approximate time</td>
   <td>
       <p>A moment ago</p>
       <p>10 minutes ago</p>
       <p>2 hours ago</p>
    </td>
    <td><p>Use for a single event that happened recently, e.g., in the Status bar:</p>
        <p><img src="approximate.png" width="215" /></p>
        <p>Do <b>not</b> use in tables as it’s harder to compare.</p>
        <p>If more than 12 hours have passed since the event, use Date format.</p>
    </td>
</tr>
</table>

![](vcs_log.png){width=1053 style=block}
*Version Control Log (in IntelliJ IDEA incorrect format is used at the moment)*

![](event_log.png){width=1053 style=block}
*Event Log*

## Duration

<table>
<tr>
<td width="20%"></td>
<td width="25%"> Examples </td>
<td width="55%"> When and how to use </td>
</tr>
<tr>
    <td>Period</td>
    <td>
        <p>10 milliseconds</p>
        <p>10 seconds</p>
        <p>10 minutes</p>
        <p>1 hour</p>
    </td>
    <td>Space is not limited, e.g. <control>Settings</control> dialog:
        <p><img src="period.png" width="242" /></p>
    </td>
</tr>
<tr>
    <td>Time-lapse</td>
    <td>
        <p>1 h 30 m 30 s</p>
        <p>1 h 30 m 30.035 s</p>
        <p>1 h</p>
        <p>1 h 0 m 30 s</p>
        <p>1 m 30 s</p>
        <p>05 Jan 2019, 14:04</p>
    </td>
    <td><p>Use for changing duration. Select units depending on the process type. E.g., Unit tests usually run less than a minute, so show only seconds and milliseconds. Build tasks usually run more than a minute, so show minutes and seconds.</p>
        <p>Omit hours and seconds that do not provide additional data.</p>
</td>
</tr>
<tr>
    <td>Time-lapse condensed <a href="https://en.wikipedia.org/wiki/ISO_8601">ISO 8601 standard</a></td>
    <td>1:30:01.350</td>
    <td>Use when space is limited or to describe a range, e.g. 1:30:01.350 – 1:30:02.350 </td>
</tr>
</table>


![](run.png){width=518}

## Quantities of data

Use decimal numeral system.

<table style="none">
<tr>
    <td width="50%">bits</td>
    <td width="50%">10 bits</td>
</tr>
<tr>
    <td>bytes</td>
    <td>10 B</td>
</tr>
<tr>
    <td>kilobytes</td>
    <td>10 KB</td>
</tr>
<tr>
    <td>megabytes</td>
    <td>10 MB</td>
</tr>
<tr>
    <td>gigabytes</td>
    <td>10 GB</td>
</tr>
<tr>
    <td>terabytes</td>
    <td>10 TB</td>
</tr>
</table>

![](data_formats_tree.png){width=511}

## Units

All numeric values should be given a unit or clearly labeled if they do not have suitable units, e.g. counters.

![](period.png){width=242}

![](no_units.png){width=264}

Select the most convenient units.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="connection_incorrect.png" alt="" width="284" /></td>
        <td><img src="connection_correct.png" alt="" width="259" /></td>
    </tr>
</table>

Place units after the input field.

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="caret_incorrect.png" alt="" width="196" /></td>
        <td><img src="caret_correct.png" alt="" width="242" /></td>
    </tr>
</table>

## Tables

<p>Use the rules below to make it easier to compare numbers in one column.</p>

Right-align numeric values.

![](data_formats_table.png){width=299}

Include the unit in the column header if all values share the same unit. Convert each number to use the same unit.
If it’s not possible, e.g., the scatter of numbers is too large, include the unit with each value.

| <format color="Red" style="bold">Incorrect</format> | <format color="Green" style="bold">Correct</format> |
|-----------------------------------------------------|-----------------------------------------------------|
| ![](table_units_incorrect.png){width="110"}         | ![](table_units_correct.png){width="110"}           |

Be consistent with the precision of the significand.

| <format color="Red" style="bold">Incorrect</format> | <format color="Green" style="bold">Correct</format> |
|-----------------------------------------------------|-----------------------------------------------------|
| ![](precision_incorrect.png){width="110"}           | ![](precision_correct.png){width="110"}             |
