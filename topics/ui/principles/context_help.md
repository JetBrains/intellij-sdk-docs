<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Context Help

<link-summary>Choosing a proper component for context help.</link-summary>

Use context help to briefly explain how a functionality works if it is not clear from the UI and the application behavior. Provide full description of the functionality in [product web help](https://www.jetbrains.com/help/idea/).

There are three ways to show context help:
* in a [help tooltip](tooltip.md)
* as [inline help text](inline_help_text.md)
* in an [empty state](empty_state.md)

This article explains when to use the first two. For when to use the empty state, see the [](empty_state.md) topic.


## Inline text or a tooltip

Use **inline help text** in settings dialogs:
* Settings are rarely changed. Users may forget what a setting does when they use it the next time, so it makes sense to provide additional information straight away.
* Settings dialogs are usually not constrained in space. In most cases it is possible to fit in a help text.

![](09_use_inline_help_text.png)

Use a **help tooltip** if:
* A dialog is often used. The more often a person uses an interface, the more likely they are to remember what each option does.

![](10_use_help_tooltip.png)
*A refactoring dialog is used more often than a settings dialog. A help tooltip is less distracting than an inline help text.*

* There is no space for an inline text.
![](11_no_space_in_settings.png)

* The control that needs explanation is an icon or does not have a label.
![](03_action_help_tooltip.png)


## When to use context help

Explain complex behavior that a short action or a setting name cannot convey clearly.

![](04_question_icon_tooltip.png)

![](02_text_size.png)

Explain IDE-specific entities.

![](01_IDE_specific.png)

Provide input format requirements and examples.

![](02_formatting_example.png)

Suggest alternative ways.

![](03_alternative_ways.png)

Warn about possible problems.

![](04_possible_problems.png)

Explain limitations.

![](05_limitations.png)

Provide quick navigation to related settings.

![](04_link_internal.png)


## When not to use

Do not use context help to explain how the user interface works. If you need to explain that, consider redesigning the UI.

<p>Incorrect</p>

![](06_explain_how_ui_works.png)

Do not explain common actions and entities. Show a regular tooltip with an action name and shortcut in this case.

| Incorrect                             | Correct                             |
|---------------------------------------|-------------------------------------|
| ![](07_explain_obvious_incorrect.png) | ![](07_explain_obvious_correct.png) |

Do not explain each option. Too many help icons or too much inline text make a dialog cluttered and harder to navigate visually.
If all options need to be explained, consider rewriting the labels to make them clearer.

<p>Incorrect</p>

![](08_explain_all_options.png)

## How to use

See [Inline help text](inline_help_text.md) and [Tooltip](tooltip.md).

