---
title: Group header
codename: TitledSeparator
category: Controls
---
<tldr>TitledSeparator</tldr>

A group header clearly labels a group of UI controls.

![](01_group_header.png)


## When to use

Follow the rules in [Groups of controls](groups_of_controls.md).

Do **not** use group headers when each group contains 3 controls and less. Separate with [vertical insets](layout.md#organize-with-insets) instead.

<p> Incorrect </p>

![](6_03_group_incorrect.png)
*Each group has less than 3 controls. Group headers only add noise in this case because control labels are enough to understand their purpose.*

<p> Correct </p>

![](6_03_group_correct.png)


## How to use

Use title capitalization for the label.

Make the label short, preferably no more than 4 words. Avoid generic words, such as "General" or “Options”. See [Writing short and clear](writing_short.md).

If a group contains rarely used or advanced settings, use a **collapsible group header** and collapse it by default.

![](02_collapsed_header.png)




