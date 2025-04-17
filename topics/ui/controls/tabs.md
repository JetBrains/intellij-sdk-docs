<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tabs

<link-summary>UI guidelines on using tabs.</link-summary>

<tldr>

**Implementation:** [`JBTabbedPane`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTabbedPane.java)

</tldr>

Tabs help organize related content.

![Tabs](01_Tabs.png){width=706}


For editor tabs, use another component — [`JBEditorTabs`](%gh-ic%/platform/platform-api/src/com/intellij/ui/tabs/impl/JBEditorTabs.kt). It supports extended functionality like icons, closeable, and draggable tabs. Do **not** use this component in dialogs.

In tool windows, tabs are generated automatically. See also [Tool window](tool_window.md).

## When to use

Follow the rules in [Groups of controls](groups_of_controls.md).

## How to use

### Label

Use title capitalization for tab labels. Make the label short, preferably no more than 3 words.
Avoid generic words, such as "General" or "Advanced".
See [Writing short and clear](writing_short.md).

<format color="369650" style="bold">Correct</format>
![Hiding tabs that do not fit](02_How_to_use_correct.png){width=706}

<format color="E55765" style="bold">Incorrect</format>
![Hiding tabs that do not fit](02_How_to_use_incorrect.png){width=706}


###  Tab Order and Layout

Place the most frequently used content in the first tab.

Tabs that do not fit allotted screen space automatically hide under the dropdown component. It is better to add no more than 8 tabs, but this number is not limited.

![Hiding tabs that do not fit](03_How_to_use.png){width=706}

### Unavailable content

Do not remove or disable a tab when its function is unavailable. Explain why a tab’s content is unavailable in the body of the tab.

## Placement

### Position

Always place tabs on top of the content. It is possible to place them at other sides — bottom, left, or right — but such a placement is extremely rare and might confuse users.


### Independent content

Do not place independent content under the tabs. Create separate tabs for such content.

<format color="369650" style="bold">Correct</format>

![Correct placment of independent content](06_Placement﻿_correct.png){width=706}

<format color="E55765" style="bold">Incorrect</format>

![Incorrect placement of independent content](06_Placement﻿_incorrect.png){width=706}

### Controls above tabs
When there are other UI controls above tabs, separate them with a vertical indent.

![UI controls above the tabs](07_Placement﻿_correct.png){width=706}

### Tabs border

Make sure the border of the tab reaches the edges of the area tabs occupy.

<format color="369650" style="bold">Correct</format>

![Correct bottom border](04_Placement﻿_correct.png){width=706}

<format color="E55765" style="bold">Incorrect</format>

![Incorrect bottom border](04_Placement﻿_incorrect.png){width=706}

Do **not** surround the tab content area with a visible border.

![Frame around the tab content](05_Placement﻿_incorrect.png){width=706}
