<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tooltip

<link-summary>UI guidelines on using tooltips.</link-summary>

<tldr>

**Implementation:** [`HelpTooltip`](%gh-ic%/platform/platform-api/src/com/intellij/ide/HelpTooltip.java)

</tldr>

A tooltip shows an action name or provides useful information about an action or a setting.

![](01_top_pic.png){width=174}

## When to use

There are three types of tooltips:

<table style="none">
  <tr>
    <td width="50%">
        <strong>Action </strong>
        <p>Shows an action name or label for icons and unlabeled controls, and a shortcut if available.</p>
    </td>
    <td width="50%">
        <img src="02_regular_tooltip.png" width="218" />
    </td>
  </tr>
  <tr>
    <td>
        <strong>Action help </strong>
        <p>Shows help text for icons and unlabeled controls in addition to an action name or label.</p>
    </td>
    <td>
        <img src="03_action_help_tooltip.png" width="305" />
    </td>
  </tr>
  <tr>
    <td>
        <strong>Help</strong>
        <p>Shows help text for all other controls.</p>
    </td>
    <td>
        <img src="03_help_tooltip.png" width="275" />
    </td>
  </tr>
</table>


All icons and unlabeled controls should have an **Action** or an **Action help** tooltip.

Use **Action help** and **Help** tooltips according to the [Context help](context_help.md) rules.

## How to use

### Required and optional information

Always show the required information in a tooltip:

<table>
  <tr>
  <td width="40%"> Tooltip </td>
  <td width="30%"> Required info </td>
  <td width="30%"> Optional </td></tr>
  <tr>
    <td>
        <p>Action</p>
        <p><img src="08_regular_tooltip.png" width="126"/></p>
    </td>
    <td>
        Action name
    </td>
    <td>
        Shortcut
    </td>
  </tr>
  <tr>
    <td>
        <p>Action help</p>
        <p><img src="08_toolbar_help_tooltip.png" width="300"/></p>
    </td>
    <td>
        <p>Action name</p>
        <p>Help text</p>
    </td>
    <td>
        <p>Shortcut</p>
        <p>Link</p>
    </td>
  </tr>
  <tr>
    <td>
        <p>Help</p>
        <p><img src="08_help_tooltip.png" width="300"/></p>
    </td>
    <td>
        <p>Help text</p>
        <p><format color="#999999">No action name or label because it is already shown in the UI.</format></p>
    </td>
    <td>
        <p>Shortcut</p>
        <p>Link</p>
    </td>
  </tr>
</table>

<!--
<p><strong>Implementation</strong></p>

<p>Action</p>

<code-block lang="java">
new HelpTooltip().setTitle("Commit").setShortcut("⌘K").installOn(component);
</code-block>

<p>Action help</p>

<code-block lang="java">
new HelpTooltip().setTitle("Switch Task").setDescription("Tasks are stored locally only. Connect your issue tracker to link your commits with the corresponding issues.").setShortcut("⌥⇧T").setLink("Managing tasks", ()->{}).installOn(component);
</code-block>

<p>Help</p>

<code-block lang="java">
new HelpTooltip().setDescription("Useful when moving constants (static final fields) to an enum type in cases when the enum type has a constructor with one parameter of the suitable type.").setShortcut("⌘M").setLink("Example", ()->{}).installOn(component);
</code-block>

<p><br/></p>
-->

Show a shortcut if an action or a setting has one. Do not show a single shortcut in a tooltip.

![](09_tooltip_only_incorrect.png){width=230}

Provide a link to a source that can further explain the action or the setting. A link can navigate to a place in the IDE or to an external help article.

| Local link                                  | External link                             |
|---------------------------------------------|-------------------------------------------|
| ![](10_tooltip_local_link.png){width="300"} | ![](10_tooltip_ext_link.png){width="300"} |

Do not show just an action name and a link to a help article. Provide help text so that the user does not need to switch to a web browser.

![](11_link_only_incorrect.png){width=516}

### Text length and formatting

The text width in an action tooltip is not limited. The text width in a help tooltip is limited by 250px.

Show no more than 10 lines of help text. If the text does not fit, leave only the essential information and add a link to a help article.

<format color="Red" style="bold">Incorrect</format>

![](tooltip_long_text_incorrect.png){width=601}

<format color="Green" style="bold">Correct</format>

![](tooltip_long_text_correct.png){width=601}

Text style formatting:

* Avoid using style formatting in the help text. Usually, the text is short and no bold or italics are needed.

  <format color="Red" style="bold">Incorrect</format>

  ![](tooltip_no_styling_incorrect.png){width=461}

  <format color="Green" style="bold">Correct</format>

  ![](tooltip_no_styling_correct.png){width=461}

* Use formatting for code, console commands or parameters. Use HTML tags. Enclosing the text in `<html></html>` tags is not needed.

  ![](tooltip_code_styling.png){width=407 style=block}
  *Editor breadcrumbs*

* Action name, shortcut and link do not allow HTML tagging.

If the help text is longer than 5 lines, separate the text into paragraphs with the `<p>` tag. The `<p>` tag adds vertical space between paragraphs to visually separate them. Do not use the `<br/>` tag as it does not add space.

![](12_tooltip_paragraph.png){width=300}

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

### Writing guidelines

Make the help text [short and descriptive](writing_short.md).

In a help tooltip, do not repeat an action or a setting name in the text.

![](13_tooltip_dont_repeat_setting.png){width=394}

### Question mark icon for help tooltips

Always use the help tooltip with the question mark icon. Without the icon, it is unclear which component has help information.

Always place the question mark icon to the right of the corresponding UI component.

Examples with different controls:

#### Checkbox

![](04_question_icon_tooltip.png){width=543}

#### Tree item

![](05_question_icon_tree.png){width=390}

#### Combo box

![](14_placement_labeled_input.png){width=467}

#### Group header

![](15_placement_group_header.png){width=409}

#### Settings breadcrumbs

![](16_placement_settings_header.png){width=458}

#### Stand-alone button not at the bottom of a dialog

![](07_help_tooltip_button.png){width=476}

**Exception:** do not use the help tooltip with buttons at a dialog’s bottom.
Put the information into the help article that is opened with the question mark button in the bottom left corner.

<format color="Red" style="bold">Incorrect</format>

![](06_help_tooltip_button_incorrect.png){width=372}

## Style

![](tooltip_style.png){width=723}

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
        <p><format color="#999999">If cursor is in the tooltip trigger area</format></p>
    </td>
  </tr>
  <tr>
    <td>
        Action
    </td>
    <td>
        <p>300 milliseconds</p>
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

