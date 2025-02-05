<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Context Help

<link-summary>Choosing a proper component for context help.</link-summary>

Use context help to briefly explain how a functionality works if it is not clear from the UI or the application behavior. A full description of the functionality should be provided in [product web help](https://www.jetbrains.com/help/idea/).

Context help can be shown as an [inline help text](inline_help_text.md), in a [help tooltip](tooltip.md), and in an [empty state](empty_state.md).

## Inline help text
Use an [inline help text](inline_help_text.md) in settings dialogs that are not constrained by space and are not frequently used. Since settings are rarely changed, users may forget their purpose, so displaying the information immediately makes sense.

<img src="context_help_inline_text.png" alt="Context help in an inline text" width="706"/>

## Tooltip

Use a [tooltip](tooltip.md):
- If the space is not enough for showing the inline help text.
- In frequently used dialogs, tool windows, or popups. The more often a person uses an interface, the more likely they are to remember what each option does.
- For icons or other controls that do not have a label.

<img src="context_help_tooltip.png" alt="Context help in a tooltip" width="706"/>


## Empty state

Fill in [empty states](empty_state.md) of tool windows, tables, trees, and other containers with:
- a reason why the data is missing
- an action that can fix it
- a link to a corresponding article in the web help.

<img src="empty_state_structure.png" alt="Empty state of a tool window" width="706"/>


## When to use

Below you will find rules for [tooltips](tooltip.md) and [inline texts](inline_help_text.md).
<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <img src="context_help_explain.png" alt="Explain a setting"/>
    </td>
    <td>
        <p>Explain complex behavior that a short action or a setting name cannot convey clearly.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_input_format.png" alt="Input format requirements"/>
    </td>
    <td>
        <p>Provide input format requirements and examples.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_alternatives.png" alt="Suggest alternative ways"/>
    </td>
    <td>
        <p>Suggest alternative ways.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_input_warning.png" alt="Warn about possible issues"/>
    </td>
    <td>
        <p>Warn about possible issues.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_limitation.png" alt="Explain limitations"/>
    </td>
    <td>
        <p>Explain limitations.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_navigation.png" alt="Provide quick navigation to related settings"/>
    </td>
    <td>
        <p>Provide quick navigation to related settings.</p>
    </td>
  </tr>
  <tr>
    <td width="378">
      <img src="context_help_terms.png" alt="Explain IDE-specific entities"/>
    </td>
    <td>
        <p>Explain IDE-specific entities.</p>
    </td>
  </tr>
</table>

## When not to use

### Do not explain UI
Do not use context help to explain how the user interface works. If you need to explain that, consider redesigning the UI.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="context_help_explain_ui_correct.png" alt="Don't explain how UI elements should work"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="context_help_explain_ui_incorrect.png" alt="Incorrect UI requires additional explanation"/>
    </td>
  </tr>
</table>

### Do not explain common actions
Do not explain common actions and entities. Show a regular tooltip with an action name and shortcut in this case.
<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="context_help_explain_simple_correct.png" alt="Don't explain common actions"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="context_help_explain_simple_incorrect.png" alt="Common actions are explained in the tooltip"/>
    </td>
  </tr>
</table>

### Do not clutter UI with context help
Do not explain each option. Too many help icons or too much inline text make a dialog cluttered and harder to navigate visually.
If all options need to be explained, consider rewriting the labels to make them clearer.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="context_help_explain_too_many_correct.png" alt="Don't explain every option"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="context_help_explain_too_many_incorrect.png" alt="Every option has its tooltip"/>
    </td>
  </tr>
</table>

## How to use

See [Inline help text](inline_help_text.md), [Tooltip](tooltip.md), and [](empty_state.md).

