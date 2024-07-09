<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tabs

<link-summary>UI guidelines on using tabs.</link-summary>

<tldr>

**Implementation:** [`JBTabbedPane`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBTabbedPane.java)

</tldr>

Tabs organize content in dialogs by grouping similar UI controls.

![](01_tabs_example.png){width=322}

In code editors, tabs are created with another component — [`JBEditorTabs`](%gh-ic%/platform/platform-api/src/com/intellij/ui/tabs/impl/JBEditorTabs.kt). It supports extended functionality like icons, closeable, and draggable tabs. Do **not** use this component in dialogs.

In tool windows, tabs are generated automatically. See also [Tool window](tool_window.md).

## When to use

Follow the rules in [Groups of controls](groups_of_controls.md).

## How to use

Use title capitalization for tab labels.

Make the label short, preferably no more than 3 words.
Avoid generic words, such as "General" or "Advanced".
See [Writing short and clear](writing_short.md).

![](02_naming.png){width=284}

Place the most frequently used content in the first tab.
Tabs that do not fit allotted screen space automatically hide under the dropdown component.
(It is better to add no more than 8 tabs, but this number is not limited.)

![](03_hidden_tabs.png){width=533}

Always place tabs on top. It is possible to place them at other sides of the content — bottom, left, or right — but such a placement is extremely rare and might confuse users.

Do not remove or disable a tab when its function is unavailable. Explain why a tab’s content is unavailable in the body of the tab.

## Placement

Make sure the border of the tab reaches the edges of the area tabs occupy.

<format color="Red" style="bold">Incorrect</format>

![](04_layout_border_incorrect.png){width=595}

<format color="Green" style="bold">Correct</format>

![](04_layout_border_correct.png){width=595}

Do **not** surround the tab content area with a visible border.

<format color="Red" style="bold">Incorrect</format>

![](05_bordered.png){width=595}

Avoid placing independent content groups under the tabs' area.

<format color="Red" style="bold">Incorrect</format>

![](06_layout_content_under.png){width=595}

When there are other UI controls above tabs, separate them with a vertical indent.

![](07_inset.png){width=595}

