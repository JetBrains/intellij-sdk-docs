<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Split Button

<link-summary>UI guidelines on using split buttons.</link-summary>

<tldr>

**Implementation:** [`JBOptionButton`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/JBOptionButton.kt)

</tldr>

The Split button is a button that has two parts — the main action on the left and a control button which shows a dropdown with less common actions on the right.

![](button-and-dropdown-menu.png){width=218}

## When to use

<p>Use the split button:</p>

When more than 2 related actions are possible but the space is limited and/or packed:
For example, it is useful for the Commit actions group in the <control>Commit</control> dialog:

![](button-and-dropdown-menu.png){width=218}

The Split button is not useful in the <control>Replace</control> popup, since not all actions are related.
For example, <control>Open in Find Window</control>
is not related to the main action. Such actions are hard to find in the drop-down menu:

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="not-related-incorrect.png" alt="" width="152"/></td>
        <td><img src="not-related.png" alt="" width="327"/></td>
    </tr>
</table>

In the <control>Adjust Code Style</control> dialog, only 1 related action is possible, and it does not save a lot of space:

<table>
    <tr>
        <td width="50%"><format color="Red" style="bold">Incorrect</format></td>
        <td width="50%"><format color="Green" style="bold">Correct</format></td>
    </tr>
    <tr>
        <td><img src="space-not-limited-incorrect.png" alt="" width="152" /></td>
        <td><img src="space-not-limited.png" alt="" width="234" /></td>
    </tr>
</table>

To hide actions which are dangerous and uncommon. Dangerous means an action can destroy users’ data and cannot be easily undone.
It is less possible to accidentally click an action hidden in a menu.
It is recommended to hide even a single related uncommon dangerous action.
For example, <control>Force Push</control> can override remote commits from other authors and should not be easily available:

![](dangerous.png){width=111}

<p>If an action is dangerous but common, do not hide it under the split button, use simple buttons.

[//]: # (TODO: An action should follow the <a href="dangerous_actions.md">principles for dangerous actions</a> behavior.)
</p>

Do **not** use the Split Button in other cases, use simple [Buttons](button.topic) instead.

## How to use

### Main action

<table style="none">
  <tr>
    <td>Click</td>
    <td>Invoke the main action</td>
  </tr>
</table>

### Control button

<table style="none">
  <tr>
    <td>Click</td>
    <td>
        <ul>
            <li>Show a dropdown menu with secondary actions</li>
            <li>Hide the menu on clicking outside the menu, or on the second click on the right part of the button</li>
        </ul>
    </td>
  </tr>
  <tr>
    <td>Hover</td>
    <td>
        <ul>
            <li>Show a tooltip:
                <img src="tooltip-button.png" width="237" /></li>
            <li>
                The tooltip must not overlap the dropdown menu. Do not show a tooltip under the menu, show it on the opposite side of the button:
                <br/>
                <format color="Green" style="bold">Correct</format>
                <img src="tooltip-correct.png" width="255" />
                <br/>
                <format color="Red" style="bold">Incorrect</format>
                <img src="tooltip-incorrect.png" width="254" />
            </li>
        </ul>
    </td>
  </tr>
</table>

### Dropdown menu

Place actions related to the main button’s action in the dropdown menu.

![](dropdown-menu.png){width=218}

Do **not** duplicate the main action in the dropdown menu, otherwise it is confusing how to trigger the main action — with the button or from the menu.

[//]: # (### Reduce split button to simple action buttons)

[//]: # ()

[//]: # (The Split button can be reduced to simple action buttons which are laid out automatically next to each other. This is controlled by the following option in settings:)

[//]: # (<ui-path>Settings | Appearance & Behavior | Appearance | Merge buttons in dialogs</ui-path>)

[//]: # ()

[//]: # (<p>For example, the <control>Commit</control> button reduced to its components &#40;the option is disabled&#41; looks like the following:</p>)

[//]: # ()

[//]: # (![]&#40;reduced.png&#41;{width=500})

## Keyboard navigation & shortcuts

Trigger the **main** action when the [default](button.topic#default) button shortcut is pressed if the split button is the default one.

Open the dropdown menu with the first menu item selected on <shortcut>Alt+Shift+Enter</shortcut>.

Do **not** show the dropdown menu when the button gains focus.

### Focus on the button

<table style="none">
  <tr>
    <td width="15%">
        <p><shortcut>Enter</shortcut></p>
        <p><shortcut>Ctrl+Enter</shortcut></p>
    </td>
    <td width="85%"><ul><li>Invoke the default button action</li></ul></td>
  </tr>
  <tr>
    <td><shortcut>Space</shortcut></td>
    <td><ul><li>Invoke the main action</li></ul></td>
  </tr>
  <tr>
    <td><shortcut>Arrow Down</shortcut></td>
    <td><ul><li>Show the dropdown menu with secondary actions and move focus to the first item in the menu</li></ul></td>
  </tr>
  <tr>
    <td>
        <p><shortcut>Tab</shortcut></p>
        <p><shortcut>Shift + Tab</shortcut></p>
    </td>
    <td><ul><li>Move focus to the control next to the split button and hide the drop-down menu</li></ul></td>
  </tr>
</table>

### Focus in the drop-down menu

<table style="none">
  <tr>
    <td width="15%">
        <p><shortcut>Enter</shortcut></p>
        <p><shortcut>Space</shortcut></p>
    </td>
    <td width="85%">Invoke the selected action</td>
  </tr>
  <tr>
    <td>
        <p><shortcut>Arrow Down</shortcut></p>
        <p><shortcut>Arrow Up</shortcut></p>
    </td>
    <td>
        <ul>
            <li>Navigate through the elements</li>
            <li>Pressing Up on the top element moves the focus to the bottom element</li>
            <li>Pressing Down on the bottom element moves the focus to the top element</li>
        </ul>
    </td>
  </tr>
  <tr>
    <td><shortcut>Esc</shortcut></td>
    <td>Close the popup and move the focus to the split button</td>
  </tr>
</table>

## Sizes and placement

Follow the rules for the [simple button](button.topic#sizes-and-placement).

### Button

The width of the split button equals to the width of the main button (follow the rules of the [simple button](button.topic)) plus the width of the drop-down button.

| Windows                              | Mac                            | Darcula                            |
|--------------------------------------|--------------------------------|------------------------------------|
| ![](win-button-size.png){width="92"} | ![](mac-sizes.png){width="90"} | ![](darcula-sizes.png){width="94"} |

A different width for the split button makes it easier to understand that this button is different from other buttons in the dialog.

### Drop-down menu

Follow the rules for menus with regard to sizes, colors, fonts and spacing.

Menu item height and spacing between the menu and the button:

![](button-and-dropdown-sizes.png){width=232}

## Style

Increase line height in the dropdown menu to lessen the chance of choosing the wrong menu item by mistake.

Leave 2px around the separator inactive to lessen the chance of choosing the wrong menu item by mistake:

![](selected.png){width=218}

Align the dropdown with the button left border:

![](split_button_alignment.png){width=219}

