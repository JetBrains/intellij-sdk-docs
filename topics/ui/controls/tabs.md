<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tabs

<link-summary>UI guidelines on using tabs.</link-summary>

<tldr>

**Implementation:** [`JBTabbedPane`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTabbedPane.java)

</tldr>

Tabs organize content by grouping similar UI controls.

![](01_Tabs.png){width=706}

## Tabs in Code Editors
In code editors, tabs are created with another component — [`JBEditorTabs`](%gh-ic%/platform/platform-api/src/com/intellij/ui/tabs/impl/JBEditorTabs.kt). It supports extended functionality like icons, closeable, and draggable tabs. Do **not** use this component in dialogs.

In tool windows, tabs are generated automatically. See also [Tool window](tool_window.md).

## When to use

Follow the rules in [Groups of controls](groups_of_controls.md).

## How to use

### Label

Use title capitalization for tab labels. Make the label short, preferably no more than 3 words.
Avoid generic words, such as "General" or "Advanced".
See [Writing short and clear](writing_short.md).

<table style="none" border="false">
  <tr>
<td width="50%">
      <format color="Green" style="bold">Correct</format><img src="02_How_to_use_correct.png" alt="A correct example of a tab label"/>
    </td>
    <td width="50%">
      <format color="Red" style="bold">Incorrect</format><img src="02_How_to_use_incorrect.png" alt="An incorrect example of a tab label"/>
    </td>
  </tr>
</table>

###  Organization and Accessibility

Place the most frequently used content in the first tab.

Tabs that do not fit allotted screen space automatically hide under the dropdown component.
(It is better to add no more than 8 tabs, but this number is not limited.)

![](03_How_to_use.png){width=706}

### Unavailable content

Do not remove or disable a tab when its function is unavailable. Explain why a tab’s content is unavailable in the body of the tab.

## Placement

### Position

Always place tabs on top. It is possible to place them at other sides of the content — bottom, left, or right — but such a placement is extremely rare and might confuse users.


### Independent content

Do not place independent content under the tabs. Create separate tabs for such content.

<format color="369650" style="bold">Correct</format>

![](06_Placement﻿_correct.png){width=706}

<format color="E55765" style="bold">Incorrect</format>

![](06_Placement﻿_incorrect.png){width=706}

When there are other UI controls above tabs, separate them with a vertical indent.

![](07_Placement﻿_correct.png){width=706}

### Bottom border

Make sure the border of the tab reaches the edges of the area tabs occupy.

<format color="369650" style="bold">Correct</format>

![](04_Placement﻿_correct.png){width=706}

<format color="E55765" style="bold">Incorrect</format>

![](04_Placement﻿_incorrect.png){width=706}

Do **not** surround the tab content area with a visible border.

![](05_Placement﻿_incorrect.png){width=706}
