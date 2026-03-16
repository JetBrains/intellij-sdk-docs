<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tooltip

<link-summary>UI guidelines on using tooltips.</link-summary>

<tldr>

**Implementation:** [`HelpTooltip`](%gh-ic%/platform/platform-api/src/com/intellij/ide/HelpTooltip.java)

</tldr>

A tooltip appears on hovering over a UI element and shows an action name or provides useful information about an action or a setting.

<img src="tooltip_main.png" alt="Tooltip" width="706"/>

## When to use

There are three types of tooltips:

<table border="false" column-width="fixed" style="none">
  <tr>
    <td>
        <img src="tooltip_action.png" alt="Action tooltip" />
    </td>
    <td>
        <strong>Action </strong>
        <p>Shows an action name or label for icons and unlabeled controls such as main toolbar widgets, and a shortcut if available.</p>
        <p>Required: action name</p>
        <p>Optional: shortcut</p>
    </td>
  </tr>
  <tr>
    <td>
        <img src="tooltip_help_action.png" alt="Action help tooltip"/>
    </td>
    <td>
        <strong>Action help </strong>
        <p>Shows help text for icons and unlabeled controls in addition to an action name or label.</p>
        <p>Required: action name, help text</p>
        <p>Optional: shortcut, link</p>
    </td>
  </tr>
  <tr>
    <td>
        <img src="tooltip_help.png" alt="Help tooltip" />
    </td>
    <td>
        <strong>Help</strong>
        <p>Shows help text for all other controls. It is shown on hovering the <a anchor="question-mark-icon-for-help-tooltips">question mark icon</a>.</p>
        <p>Required: help text. Action name or label is not required because it is shown in the UI.</p>
        <p>Optional: shortcut, link.</p>
    </td>
  </tr>
</table>


All icons and unlabeled controls should have an **Action** or an **Action help** tooltip.

Use **Action help** and **Help** tooltips according to the [Context help](context_help.md) rules.

## How to use

[//]: # (### Required and optional information)

[//]: # ()
[//]: # (Always show the required information in a tooltip:)

[//]: # ()
[//]: # (<table>)

[//]: # (  <tr>)

[//]: # (  <td width="40%"> Tooltip </td>)

[//]: # (  <td width="30%"> Required info </td>)

[//]: # (  <td width="30%"> Optional </td></tr>)

[//]: # (  <tr>)

[//]: # (    <td>)

[//]: # (        <p>Action</p>)

[//]: # (        <p><img src="08_regular_tooltip.png" width="126"/></p>)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        Action name)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        Shortcut)

[//]: # (    </td>)

[//]: # (  </tr>)

[//]: # (  <tr>)

[//]: # (    <td>)

[//]: # (        <p>Action help</p>)

[//]: # (        <p><img src="08_toolbar_help_tooltip.png" width="300"/></p>)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        <p>Action name</p>)

[//]: # (        <p>Help text</p>)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        <p>Shortcut</p>)

[//]: # (        <p>Link</p>)

[//]: # (    </td>)

[//]: # (  </tr>)

[//]: # (  <tr>)

[//]: # (    <td>)

[//]: # (        <p>Help</p>)

[//]: # (        <p><img src="08_help_tooltip.png" width="300"/></p>)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        <p>Help text</p>)

[//]: # (        <p><format color="#999999">No action name or label because it is already shown in the UI.</format></p>)

[//]: # (    </td>)

[//]: # (    <td>)

[//]: # (        <p>Shortcut</p>)

[//]: # (        <p>Link</p>)

[//]: # (    </td>)

[//]: # (  </tr>)

[//]: # (</table>)

### Shortcut
* Always show a shortcut if an action or a setting has one.
* Do not show a single shortcut in a tooltip:

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_shortcut_correct.png" alt="A tooltip with a shortcut" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_shortcut_incorrect.png" alt="A tooltip with a shortcut but without the action name" width="378"/></td>
    </tr>
</table>

### Link

* Provide a link to a source that can further explain the action or the setting. A link can navigate to a place in the IDE or to an external help article:

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <strong>Internal link</strong>
            <img src="tooltip_internal_link.png" alt="A tooltip with an internal link" width="378"/>
        </td>
         <td>
            <strong>External link</strong>
            <img src="tooltip_external_link.png" alt="A tooltip with an external link" width="378"/>
        </td>
    </tr>
</table>

* Do not show just an action name and a link to a help article. Provide help text so that the user does not need to switch to a web browser:

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_link_correct.png" alt="A tooltip with a link and help text" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_link_incorrect.png" alt="A tooltip with a link but without help text" width="378"/></td>
    </tr>
</table>

### Text length

The text width in an action tooltip is not limited. The text width in a help tooltip is limited by 250px.

Show no more than 10 lines of help text. If the text does not fit, leave only the essential information and add a link to a help article.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_text_length_correct.png" alt="A tooltip with a long help text" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_text_length_incorrect.png" alt="A tooltip with a too long help text" width="378"/></td>
    </tr>
</table>


If the help text is longer than 5 lines, separate the text into paragraphs with the `<p>` tag. The `<p>` tag adds vertical space between paragraphs to visually separate them. Do not use the `<br/>` tag as it does not add space.
<chapter collapsible="true" expanded="false" title="Implementation">
<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val LONG_TEXT = """A new overloading method will be created with
    the new parameter added to this method definition.<p>
    The method with the old signature will be kept and the call
    to the new method will be added to it. The necessary value
    or expression will be passed to the new method call."""
HelpTooltip().setDescription(LONG_TEXT).installOn(component)
```

</tab>
<tab title="Java" group-key="java">

```java
String LONG_TEXT = "A new overloading method will be created with " +
    "the new parameter added to this method definition.<p>" +
    "The method with the old signature will be kept and the call " +
    "to the new method will be added to it. The necessary value " +
    "or expression will be passed to the new method call.";
new HelpTooltip().setDescription(LONG_TEXT).installOn(component);
```

</tab>
</tabs>
</chapter>

### Text style formatting

Avoid using style formatting in the help text. Usually, the text is short and no bold or italics are needed.
<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_text_formatting_correct.png" alt="Text without formatting" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_text_formatting_incorrect.png" alt="Text with formatting" width="378"/></td>
    </tr>
</table>

[//]: # ()
[//]: # (* Use formatting for code, console commands or parameters. Use HTML tags. Enclosing the text in `<html></html>` tags is not needed.)

[//]: # ()
[//]: # (  ![]&#40;tooltip_code_styling.png&#41;{width=407 style=block})

[//]: # (  *Editor breadcrumbs*)

[//]: # ()
[//]: # (* Action name, shortcut and link do not allow HTML tagging.)

### Writing guidelines

* Make the help text [short and descriptive](writing_short.md).

* In a help tooltip, do not repeat an action or a setting name in the text:

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_writing_correct.png" alt="Text is not repeated" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_writing_incorrect.png" alt="Text repetition in the checkbox and the tooltip" width="378"/></td>
    </tr>
</table>

### Question mark icon for help tooltips

Always use the help tooltip with the question mark icon. Without the icon, it is unclear which component has help information.

Always place the question mark icon to the right of the corresponding UI component.

Examples with different controls:

#### Checkbox

<img src="tooltip_checkbox.png" alt="Checkbox" width="706"/>

#### Tree item

<img src="tooltip_tree.png" alt="Tree item" width="706"/>

#### Labeled input

<img src="tooltip_labeled_input.png" alt="Labeled input" width="706"/>


#### Group header

<img src="tooltip_group_header.png" alt="Group header" width="706"/>


#### Settings breadcrumbs

<img src="tooltip_settings_header.png" alt="Settings breadcrumbs" width="706"/>

#### Button

<img src="tooltip_button.png" alt="Button" width="706"/>


**Exception**: do not use the help tooltip with buttons at a dialog’s bottom.
Put the information into the help article that is opened with the question mark button in the bottom left corner.

<table style="none" border="false" column-width="fixed">
    <tr>
        <td>
            <format color="369650" style="bold">Correct</format>
            <img src="tooltip_footer_correct.png" alt="Button in the dialog footer" width="378"/>
        </td>
         <td>
            <format color="E55765" style="bold">Incorrect</format>
            <img src="tooltip_footer_incorrect.png" alt="Button in the dialog footer with the incorrect help icon" width="378"/></td>
    </tr>
</table>

[//]: # (## Style)

[//]: # ()
[//]: # (![]&#40;tooltip_style.png&#41;{width=723})

## Built-in behavior

All tooltips appear on hover (not on click), including the help tooltip with the question mark icon.

All tooltips are hidden when the mouse cursor leaves the area that triggers the tooltip.

If the mouse cursor stays in the tooltip trigger area, tooltips are also hidden after a timeout specified in the table below.

<table>
  <tr>
    <td width="20%">
        <strong>Tooltip</strong>
    </td>
    <td width="40%">
        <strong>Appears after</strong>
    </td>
    <td width="40%">
        <p><strong>Hides after</strong></p>
        <p><format color="#999999">If the cursor is in the tooltip trigger area</format></p>
    </td>
  </tr>
  <tr>
    <td>
        Action
    </td>
    <td>
        <p>500 milliseconds</p>
        <p><format color="#999999">ide.tooltip.initialReshowDelay registry key</format></p>
    </td>
    <td>
        <p>10 seconds</p>
        <p><format color="#999999">ide.helptooltip.regular.dismissDelay</format></p>
    </td>
  </tr>
  <tr>
    <td>
        Action help
    </td>
    <td>
    </td>
    <td>
        <p>30 seconds</p>
        <p><format color="#999999">ide.helptooltip.full.dismissDelay</format></p>
    </td>
  </tr>
  <tr>
    <td>
        Help
    </td>
    <td>
    </td>
    <td>
        Never hides
    </td>
  </tr>
</table>


If a tooltip has a link, it is possible to move the mouse cursor over the tooltip. The tooltip does not close automatically when the cursor is over it.

Tooltips are positioned automatically depending on the mouse cursor location.

