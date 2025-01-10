<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Split Button

<link-summary>UI guidelines on using split buttons.</link-summary>

<tldr>

**Implementation:** [`JBOptionButton`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBOptionButton.kt)

</tldr>

A split button is a combination of a regular [button](button.topic) and a drop-down button.

![](split_button.png){width=706}

## Anatomy
A split button consists of the main action on the left and a drop-down button that shows a dropdown
with less common actions on the right:

![Split button action anatomy](split_button_anatomy.png){width=706}

## When to use

### More than two related actions
When there are more than two actions related to the main action, and there is not enough space for separate buttons, put these actions into
the dropdown menu:

![Related actions in a split button](split_button_related_actions.png){width=706}
### The second action is dangerous and uncommon
Dangerous actions can destroy users’ data and cannot be easily undone.
To avoid accidental clicks on a dangerous action, you can hide it in the dropdown menu if this action is uncommon, for example <control>Force Push</control>:

![Dangerous action in a split button](split_button_dangerous_action.png){width=706}

An uncommon dangerous action can be the only action in the menu.
<note>Uncommon actions are the actions called in rare cases for specific purposes. For example, the <control>Force Push</control> action, unlike the <control>Commit and Push...</control> action, is available under certain circumstances
and covers rare user scenarios. Thus, it can be considered as uncommon and hidden in the dropdown menu.</note>

## When not to use

### Unrelated actions

If unrelated actions are hidden under the dropdown menu, they are hard to find:

<format color="E55765" style="bold">Incorrect</format>

<img src="split_button_unrelated_actions.png" alt="Unrelated actions" width="706"/>

<format color="369650" style="bold">Correct</format>

<img src="split_button_unrelated_actions_correct.png" alt="Unrelated actions in separate buttons" width="706"/>


### Only one related action

Putting the single action in the dropdown menu doesn't save a lot of space:

<format color="E55765" style="bold">Incorrect</format>

<img src="split_button_one_action.png" alt="One related action" width="706"/>

<format color="369650" style="bold">Correct</format>

<img src="split_button_one_two_buttons.png" alt="Unrelated actions in separate buttons" width="706"/>

<note><control>Exception</control>: If the action is <a anchor="the-second-action-is-dangerous-and-uncommon">dangerous and uncommon</a>, you can put it in the menu.</note>

## How to use

### Main action
Refer to the [regular button rules](button.topic#how-to-use).

### Dropdown button
#### Click

<list>
<li>Show the dropdown menu with secondary actions.</li>
<li>Hide the menu when clicking outside the menu or on the second dropdown button click.</li>
</list>

#### Hover
Show a tooltip:

<img src="split_button_tooltip_on_hover.png" width="706" alt="Tooltip on hover"/>

### Dropdown menu

Place related actions into the dropdown menu. Do not duplicate the main action in the dropdown menu, otherwise it is confusing how to trigger the main action — with the button or from the menu.

<format color="E55765" style="bold">Incorrect</format>

![Duplicated actions in dropdown menu](split_button_duplicated_action.png){width=706}

<format color="369650" style="bold">Correct</format>

![Unique actions in dropdown menu](split_button_related_actions.png){width=706}

If there is a dangerous action among other actions in the dropdown, add the line separators above and below it to lessen the chance of calling this action by mistake:

![Dangerous action in the dropdown menu](split_button_dangerous_action_separator.png){width=706}

[//]: # (### Reduce split button to simple action buttons)

[//]: # ()

[//]: # (The Split button can be reduced to simple action buttons which are laid out automatically next to each other. This is controlled by the following option in settings:)

[//]: # (<ui-path>Settings | Appearance & Behavior | Appearance | Merge buttons in dialogs</ui-path>)

[//]: # ()

[//]: # (<p>For example, the <control>Commit</control> button reduced to its components &#40;the option is disabled&#41; looks like the following:</p>)

[//]: # ()

[//]: # (![]&#40;reduced.png&#41;{width=500})

## Keyboard navigation & shortcuts

* Trigger the main action when the [default](button.topic#default) button shortcut is pressed if the split button is the default one.

* Open the dropdown menu with the first menu item selected on <shortcut>Alt+Shift+Enter</shortcut>.

* Do not show the dropdown menu when the button gains focus.

### Focus on the button

<table style="none">

[//]: # (  <tr>)

[//]: # (    <td width="15%">)

[//]: # (        <p><shortcut>Enter</shortcut></p>)

[//]: # (        <p><shortcut>Ctrl+Enter</shortcut></p>)

[//]: # (    </td>)

[//]: # (    <td width="85%">Invoke the default button action.</td>)

[//]: # (  </tr>)
  <tr>
    <td><shortcut>Space</shortcut></td>
    <td>Invoke the main action.</td>
  </tr>
  <tr>
    <td><shortcut>Arrow Down</shortcut></td>
    <td>Show the dropdown menu with secondary actions and move focus to the first item in the menu.</td>
  </tr>
  <tr>
    <td>
        <p><shortcut>Tab</shortcut></p>
        <p><shortcut>Shift + Tab</shortcut></p>
    </td>
    <td>Move focus to the control next to the split button and hide the dropdown menu.</td>
  </tr>
</table>

### Focus on the dropdown menu

<table style="none">
  <tr>
    <td width="15%">
        <p><shortcut>Enter</shortcut></p>
        <p><shortcut>Space</shortcut></p>
    </td>
    <td width="85%">Invoke the selected action.</td>
  </tr>
  <tr>
    <td>
        <p><shortcut>Arrow Down</shortcut></p>
        <p><shortcut>Arrow Up</shortcut></p>
    </td>
    <td>
        <ul>
            <li>Navigate through the elements.</li>
            <li>Pressing <shortcut>Arrow Down</shortcut> on the top element moves the focus to the bottom element.</li>
            <li>Pressing <shortcut>Arrow Up</shortcut> on the bottom element moves the focus to the top element.</li>
        </ul>
    </td>
  </tr>
  <tr>
    <td><shortcut>Esc</shortcut></td>
    <td>Close the dropdown menu and move the focus to the split button.</td>
  </tr>
</table>

[//]: # (## Sizes and placement)

[//]: # ()
[//]: # (Follow the rules for the [simple button]&#40;button.topic&#41;.)

[//]: # ()
[//]: # (### Button)

[//]: # ()
[//]: # (The width of the split button equals to the width of the main button &#40;follow the rules of the [simple button]&#40;button.topic&#41;&#41; plus the width of the dropdown button.)

[//]: # ()
[//]: # (| Windows                              | Mac                            | Darcula                            |)

[//]: # (|--------------------------------------|--------------------------------|------------------------------------|)

[//]: # (| ![]&#40;win-button-size.png&#41;{width="92"} | ![]&#40;mac-sizes.png&#41;{width="90"} | ![]&#40;darcula-sizes.png&#41;{width="94"} |)

[//]: # ()
[//]: # (A different width for the split button makes it easier to understand that this button is different from other buttons in the dialog.)

[//]: # ()
[//]: # (### Menu)

[//]: # ()
[//]: # (Follow the rules for menus with regard to sizes, colors, fonts and spacing.)

[//]: # ()
[//]: # (Menu item height and spacing between the menu and the button:)

[//]: # ()
[//]: # (![]&#40;button-and-dropdown-sizes.png&#41;{width=232})

[//]: # ()
[//]: # (## Style)

[//]: # ()
[//]: # (Increase line height in the dropdown menuto lessen the chance of choosing the wrong menu item by mistake.)

[//]: # ()
[//]: # (Leave 2px around the separator inactive to lessen the chance of choosing the wrong menu item by mistake:)

[//]: # ()
[//]: # (![]&#40;selected.png&#41;{width=218})

[//]: # ()
[//]: # (Align the dropdown with the button left border:)

[//]: # ()
[//]: # (![]&#40;split_button_alignment.png&#41;{width=219})

