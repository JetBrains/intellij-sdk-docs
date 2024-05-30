<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Group Header

<link-summary>UI guidelines on using group headers.</link-summary>

<tldr>

**Implementation:** [`TitledSeparator`](%gh-ic%/platform/platform-api/src/com/intellij/ui/TitledSeparator.java)

</tldr>

A group header clearly labels a group of UI controls.

![](01_group_header.png){width=455}

## When to use

Follow the rules in [Groups of controls](groups_of_controls.md).

Do **not** use group headers when each group contains 3 controls and less. Separate with [vertical insets](layout.md#organize-with-insets) instead.

<format color="Red" style="bold">Incorrect</format>

![](6_03_group_incorrect.png){width=569 style=block}
*Each group has less than 3 controls. Group headers only add noise in this case because control labels are enough to understand their purpose.*

<format color="Green" style="bold">Correct</format>

![](6_03_group_correct.png){width=569}

## How to use

Use title capitalization for the label.

Make the label short, preferably no more than 4 words. Avoid generic words, such as "General" or "Options". See [Writing short and clear](writing_short.md).

If a group contains rarely used or advanced settings, use a **collapsible group header** and collapse it by default.

![](02_collapsed_header.png){width=455}




