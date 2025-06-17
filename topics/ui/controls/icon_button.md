<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Icon Button

<link-summary>UI guidelines on using icon buttons.</link-summary>

<tldr>

**Implementation:** [`ActionButton`](%gh-ic%/platform/platform-impl/src/com/intellij/openapi/actionSystem/impl/ActionButton.java)

</tldr>

<table style="none" border="false" column-width="fixed">
    <tr>
        <td><img src="toolbar_icon_button.png" alt="Using links instead of buttons" width="378"/></td>
        <td>
            A toolbar icon button contains only an icon. There are three types of icon buttons:
            <list>
                <li> An <b>action</b> triggers immediately.</li>
                <li> A <b>toggle</b> switches between on and off states.</li>
                <li> A <b>drop-down</b> opens a menu.</li>
            </list>
        </td>
    </tr>
</table>

## When to use

Follow the guidelines for [toolbar](toolbar.md#what-items-to-add-on-toolbar).

## How to use

### Icon
Use an [existing icon](https://intellij-icons.jetbrains.design) or create a new one using [icon guidelines](icons_style.md).

### Tooltip
<table style="none" border="false" column-width="fixed">
    <tr>
        <td><img src="toolbar_icon_button_tooltip.png" alt="Using links instead of buttons" width="378"/></td>
        <td>Always provide a tooltip for an icon button. Include a shortcut if there is one. See <a href="tooltip.md">Tooltip</a> for details.</td>
    </tr>
</table>

### States
<table style="none" border="false" column-width="fixed">
    <tr>
        <td><img src="toolbar_icon_button_states.png" alt="Using links instead of buttons" width="378"/></td>
        <td>When an action is not available in a particular context, disable the icon button.</td>
    </tr>
</table>

### Filter badge
<img src="toolbar_icon_button_badge.png" alt="Using links instead of buttons" width="706"/>

For filter actions, show a badge over an icon when a non-default option is selected. The badge helps to see if the content is in its default state or filtered.

### Search option icon
<img src="toolbar_icon_button_search_option.png" alt="Using links instead of buttons" width="706"/>

Search option icons have a more noticeable pressed state to allow quickly seeing what affects the search results. See [Search options](search_field.md#search-options) for details.
