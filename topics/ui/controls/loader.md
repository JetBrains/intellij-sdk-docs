<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Loader

<link-summary>UI guidelines on using loaders.</link-summary>

<tldr>

**Implementation:** [`AnimatedIcon.Default`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java)

**Related:** [](icons.md#animated-icons)

</tldr>

A loader indicates that an operation is in progress. It consists of a loader icon with a process name or a loader icon alone.

![Loader anatomy](anatomy.png){width=706}


### Implementation {collapsible="true"}

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
JLabel(
    "Loading...",
    AnimatedIcon.Default(),
    SwingConstants.LEFT
)
```
</tab>
<tab title="Java" group-key="java">

```java
new JLabel(
    "Loading...",
    new AnimatedIcon.Default(),
    SwingConstants.LEFT
);
```
</tab>
</tabs>

## When to use

### Field, dropdown, or combobox
If content in a control takes time to load, place a loader on the right side inside the field.

![Using in field, dropdown, or combobox](02.png){width=706}

### Tree or list

If tree nodes load independently and their individual states matter, display loaders instead of node icons.

![Using in tree or list](03.png){width=706}

If icons can’t be replaced, show a process name after a node label.

![Process name after a node label](04.png){width=706}

If a loader applies to the entire content below, use the [Inline progress](progress_bar.md#inline-progress) instead.

### Empty state

Show a loader while content is being loaded.

![Empty state](05.png){width=706}

### Non-instant actions
Display a loader near a button or link while an action is being performed.

![Loader near a button](06.png){width=706}

If an action appears as an icon on a toolbar, or space is limited, replace the action with a loader icon.

![Loader instead of icon](07.png){width=706}

## How to use

### Process name and details
A loader can show a process name and process details.

#### Process name

Always show the name of an operation being performed.
Use the continuous form of the verb that describes the operation. Add an ellipsis at the end to indicate that the process is ongoing.

![Process name](08.png){width=706}

If there is no space for the process name, show it in a tooltip.

![Process name in a tooltip](09.png){width=706}

#### Process details
Optionally show information about the current stage of an operation.
Details make long-running tasks more predictable and manageable.
<br>
Show process details below the process name. The text should be concise and displayed on a single line.

![Process details](10.png){width=706}

Details examples:
* The current step: "Uploading file 3 of 10"
* The current step: "Uploading file 3 of 10"
* Remaining time or percentage completed
* Round the remaining time to one of the following values: 1, 2, 3, 5, 10, 15, 20, or 30 seconds (or minutes). For example, if 7 minutes and 50 seconds remain, show "About 10 minutes left." If less than 5 seconds remain, show "About 5 seconds left."


#### Cancellation action
If a process can be canceled, provide the Cancel action and place it on the right.

![Cancellation action](11.png){width=706}

#### Process completion
Hide the loader as soon as the process completes.





