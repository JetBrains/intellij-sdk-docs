<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Context Help

<link-summary>Choosing a proper component for context help.</link-summary>

Use context help to briefly explain how a functionality works if it is not clear from the UI and the application behavior. Provide a full description of the functionality in [product web help](https://www.jetbrains.com/help/idea/).

There are three ways to show context help:

* in a [help tooltip](tooltip.md)
* as [inline help text](inline_help_text.md)
* in an [empty state](empty_state.md)

This article explains when to use the first two. For when to use the empty state, see the [](empty_state.md) topic.

## Inline text or a tooltip

Use **inline help text** in settings dialogs:

* Settings are rarely changed. Users may forget what a setting does when they use it the next time, so it makes sense to provide additional information straight away.
* Settings dialogs are usually not constrained in space. In most cases, it is possible to fit in a help text.

![](09_use_inline_help_text.png){width=600}

Use a **help tooltip** if:

* A dialog is often used. The more often a person uses an interface, the more likely they are to remember what each option does.

  ![](10_use_help_tooltip.png){width=573 style=block}
  *A refactoring dialog is used more often than a settings dialog. A help tooltip is less distracting than an inline help text.*

* There is no space for an inline text.

  ![](11_no_space_in_settings.png){width=734}

* The control that needs explanation is an icon or does not have a label.

  ![](03_action_help_tooltip.png){width=305}

## When to use context help

Explain complex behavior that a short action or a setting name cannot convey clearly.

![](04_question_icon_tooltip.png){width=543}

![](02_text_size.png){width=380}

Explain IDE-specific entities.

![](01_IDE_specific.png){width=300}

Provide input format requirements and examples.

![](02_formatting_example.png){width=478}

Suggest alternative ways.

![](03_alternative_ways.png){width=418}

Warn about possible problems.

![](04_possible_problems.png){width=361}

Explain limitations.

![](05_limitations.png){width=432}

Provide quick navigation to related settings.

![](04_link_internal.png){width=345}

## When not to use

Do not use context help to explain how the user interface works. If you need to explain that, consider redesigning the UI.

<format color="Red" style="bold">Incorrect</format>

![](06_explain_how_ui_works.png){width=418}

Do not explain common actions and entities. Show a regular tooltip with an action name and shortcut in this case.

<table>
  <tr>
    <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
    <td width="50%"><format color="Green" style="bold">Correct</format></td>
  </tr>
  <tr>
    <td><img src="07_explain_obvious_incorrect.png" alt="" width="300" /></td>
    <td><img src="07_explain_obvious_correct.png" alt="" width="145" /></td>
  </tr>
</table>

Do not explain each option. Too many help icons or too much inline text make a dialog cluttered and harder to navigate visually.
If all options need to be explained, consider rewriting the labels to make them clearer.

<format color="Red" style="bold">Incorrect</format>

![](08_explain_all_options.png){width=317}

## How to use

See [Inline help text](inline_help_text.md) and [Tooltip](tooltip.md).

