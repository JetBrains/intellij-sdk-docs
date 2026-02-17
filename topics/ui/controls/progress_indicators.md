<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Progress Indicators

<link-summary>UI guidelines on using progress indicators.</link-summary>

<tldr>

**Implementation**: Progress tracking in [Progress API](background_processes.md#tracking-progress) and [Kotlin Coroutines](execution_contexts.topic#progress-reporting)

</tldr>

Progress indicators inform users that an operation is in progress and the application is not frozen.

## When to use

Show for operations that last more than 1 second. Do not show for shorter operations.
Remove the indicator after an operation is completed.

## Types

Two types of progress indicators are used in IntelliJ Platform: progress bar and loader.

![](01.png){width=706}

### Loader
Use a [loader](loader.md) for local or inline operations:

* Loading content in fields, dropdowns, comboboxes, tree, or list nodes
* Loading content in an [empty state](empty_state.md)
* Showing an action in process, like applying a filter or refreshing data


### Progress Bar
Use a [progress bar](progress_bar.md) for background or system operations:

* Updating content in the main window, tool windows or editors
* Showing long-running tasks that need visual tracking

