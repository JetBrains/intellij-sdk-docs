<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Groups of Controls

<link-summary>Grouping content to help users locate the necessary controls faster.</link-summary>

Group controls to organize content on a screen and help people locate the necessary controls faster.

Use the following controls or layouts:

* [Group headers](group_header.md)
* [Tabs](tabs.md)
* Master-detail layout

## What grouping to choose

Use **group headers** if all rules apply:

> If there are less than 3 controls in all groups, consider separating with [vertical insets](group_header.md#how-to-use).
>
{style="note"}

* Each group contains **no** more than 10 controls

* All groups occupy no more than 2 heights of the dialog in its default size

![](6_01_group_headers.png){width=396}

Use **tabs** if all rules apply:

* One of the groups contains more than 10 controls, or groups occupy more than 2 heights of a dialog
* There are **no** more than 8 groups

![](01_use_tabs.png){width=565}

Use the **master-detail layout** if either of the rules applies:

* There are more than 8 groups
* The number of groups can change

![](02_use_master_detail.png){width=565}

