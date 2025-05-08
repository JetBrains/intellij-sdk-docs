<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Icon Button

<link-summary>UI guidelines on using icon buttons.</link-summary>

<tldr>

**Implementation:** [`ActionButton`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionButton.java)

</tldr>

A toolbar icon button is an icon that appears on a toolbar.

<img src="icon_button_example.png" alt="Icon button" width="200"/>

## Types

<p>There are three types of toolbar icon buttons:</p>

An action button triggers an action immediately on clicking it, e.g., the Open button.

A toggle button switches the state on clicking it, e.g., a button to show and hide warnings in the output tree.

![](toggle.png){width=143}

A drop-down button has an arrow icon in the bottom right corner and opens a menu with actions or checkboxes.

[//]: # (TODO: Use [menu list]&#40;menu_list.md&#41; guidelines for drop-down buttons.)

![](drop-down.png){width=284}

## When to use

Follow the rules for [toolbar](toolbar.md#what-items-to-add-on-toolbar).

## How to use

Provide a recognizable icon. Use an [existing icon](https://intellij-icons.jetbrains.design) or create a new one using [icon guidelines](icons_style.md).

Provide a short and descriptive name for a toolbar icon button. Show a tooltip with the button name on mouse hover.
Include a shortcut if there is one. See [Context help](context_help.md) for details.

![](tooltip.png){width=163}

Highlight a toolbar icon button on mouse hover. Highlight a toolbar icon button with a brighter color on clicking it.

* Toggle buttons remain highlighted when they are in the switched on mode. Toggled on buttons do not change color on hover.

* Drop-down buttons remain highlighted while the menu is opened.

![](states.png){width=183}

If an action is not available in this context, disable the corresponding button and gray out the icon.
For toolbar drop-down buttons, disable the arrow icon as well.
Do **not** highlight a disabled icon on mouse hover.

## Sizes and placement

Icons size is 16&times;16px, icon selection is 22&times;22px.

For guidelines on the space between toolbar icon buttons see [Toolbar](toolbar.md).

<!--
## Style

<table>
 <col width="50%">
 <tr>
     <td> Hovered </td>
     <td> ActionButton.hoverBackground<br/>
          ActionButton.hoverBorderColor
     </td>
 </tr>
 <tr>
     <td> Background </td>
     <td> ActionButton.pressedBackground<br/>
          ActionButton.pressedBorderColor
     </td>
 </tr>
</table>
-->
