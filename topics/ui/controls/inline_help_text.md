<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Inline Help Text

<link-summary>UI guidelines on using inline help texts.</link-summary>

Inline help texts provide useful information about settings:

<img src="context_help_inline_text.png" width="706" alt="Inline help text"/>

<chapter title="When to use" id="when-to-use">
<p>Follow the rules for <a href="context_help.md">context help</a>.</p>
</chapter>

<chapter title="How to use" id="how_to_use">

<chapter title="Text length" id="text_length">
Show no more than five lines of help text to not clutter the screen.
Text width is limited to 70 characters.

![](inline_text_height.png){width=706}

Show more than five lines only when a text cannot be shortened for legal purposes.

![](inline_text_legal.png){width=706}

<chapter title="Implementation"  id="implementation_legal" collapsible="true" default-state="collapsed">

```kotlin
panel {
  row {
    checkBox("Send usage statistics when using EAP versions")
      .comment("""
        <p>Help JetBrains improve its products by sending anonymous
        data about features and plugins used, hardware and software
        configuration, statistics on types of files, number of files
        per project, etc.</p>
        <br/>
        <p>Please note that this will not include personal data or
        any sensitive information, such as source code, file names,
        etc. The data sent complies with the
        <a href=\"https://www.jetbrains.com\">
        JetBrains Privacy Policy</a></p>
        """.trimIndent()
      )
  }
}
```
</chapter>

</chapter>
<chapter title="Links" id="links">

Provide a [link](link.md) to the corresponding help article or to a place in the IDE where the related settings can be found.
Place the link at the end of the text where possible so that it does not disrupt reading.

<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <control>Internal link</control>
      <img src="inline_text_internal_link.png" alt="Internal link"/>
    </td>
    <td width="378">
      <control>External link</control>
      <img src="inline_text_external_link.png" alt="External link"/>
    </td>
  </tr>
</table>
</chapter>

<chapter title="Text style formatting" id="text_style_formatting">

<chapter title="Avoid highlighting" id="avoid_highlighting">
Avoid text highlighting. Usually, the help text is short, and no highlighting in bold or italics is needed:
<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="inline_text_formatting_correct.png" alt="Avoid highlighting: correct"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="inline_text_formatting_incorrect.png" alt="Avoid highlighting: incorrect"/>
    </td>
  </tr>
</table>
</chapter>

[//]: # (* Use formatting for code, console commands, or parameters. Use HTML tags. Enclosing text in `<html></html>` tags is not needed.)

[//]: # (  ![]&#40;inline_text_parameter_styling.png&#41;{width=213})

<chapter title="Avoid brackets" id="avoid_brackets">
Avoid using brackets in control labels and place this information in the inline text instead:
<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="inline_text_brackets_correct.png" alt="Avoid brackets: correct"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="inline_text_brackets_incorrect.png" alt="Avoid brackets: incorrect"/>
    </td>
  </tr>
</table>
</chapter>
</chapter>

<chapter title="Writing guidelines" id="writing_guidelines">

- Make help text [short and descriptive](writing_short.md).

- Do not repeat the setting name in the help text:

<table style="none" border="false" column-width="fixed">
  <tr>
    <td width="378">
      <format color="369650" style="bold">Correct</format>
      <img src="inline_text_repetition_correct.png" alt="Avoid brackets: correct"/>
    </td>
    <td width="378">
      <format color="E55765" style="bold">Incorrect</format>
      <img src="inline_text_repetition_incorrect.png" alt="Avoid brackets: incorrect"/>
    </td>
  </tr>
</table>
</chapter>
</chapter>

<chapter title="Placement" id="placement">

<chapter title="Labeled inputs, checkboxes, radio buttons, and buttons" id="small_ui_controls">

Place the help text to the right of labeled inputs (fields, combo boxes, or text areas), checkboxes, buttons, or radio buttons if all the following applies:
* The space to the right is empty.
* The help text has 1–5 words, not counting articles and prepositions.
* The control label has 1–5 words.
<tabs>
<tab title="Labeled inputs" id="labeled_input_inline_text">
<img src="inline_text_on_the_right_input_field.png" width="706" alt="Input fields with inline texts on the right"/>
<chapter level="4" title="Implementation" id="labeled_input_inline_text_implementation" collapsible="true" default-state="collapsed">

```kotlin
panel {
  row("Plugin update policy:") {
      comboBox(listOf("Default", "Non default"))
      comment("Ignore by Maven 3+")
  }
  row("Thread count:") {
      textField()
      comment("-T option")
  }
}
```
</chapter>
</tab>

<tab title="Checkboxes" id="checkboxes">

<img src="inline_text_on_the_right_checkbox.png" width="706" alt="Checkboxes with inline texts on the right"/>

<chapter level="4" collapsible="true" default-state="collapsed" title="Implementation">

```kotlin
panel {
  row {
    checkBox("Build project automatically")
    comment("Works while not running / debugging")
  }
  row {
    checkBox("Compile independent modules in parallel")
    comment("May require larger heap size")
  }
}
```
</chapter>
</tab>

<tab title="Buttons" id="buttons">
 <img src="inline_text_on_the_right_button.png" width="706" alt="Button with inline texts on the right"/>
</tab>
</tabs>

In other cases, place the help text under the UI controls:

<img src="inline_text_on_the_bottom.png" width="706" alt="Input filed with inline text on the bottom"/>

<chapter level="4" title="Implementation" id="implementation_under_input_filed" collapsible="true" default-state="collapsed">

```kotlin
panel {
  row("Default directory:") {
    textFieldWithBrowseButton()
      .comment(
        "Preselected in Open and New | Project dialogs"
      )
  }
}
```
</chapter>

If there is no space under the UI control, use the [help tooltip](tooltip.md#question-mark-icon-for-help-tooltips) with the question mark icon for labeled inputs, checkboxes, and radio buttons.
For buttons, use the help tooltip without the icon.
</chapter>

<chapter title="Trees, lists, and tables" id="trees_lists_tables">
<chapter title="Text applies to the whole component" id="whole_component">
If the help text applies to a whole list, tree, or table, place it below the control.

<img src="inline_text_under_table.png" width="706" alt="Inline text under the table"/>

<chapter title="Implementation" id="implementation_table" collapsible="true" default-state="collapsed">

```kotlin

import javax.swing.JTable

panel {
  row {
    cell(createTable()) // Actual table creation
      .align(Align.FILL)
      .comment("""
        &lt;Project> is content roots of all modules,
        all immediate descendants<br/>of the projects base
        directory, and .idea directory contents
        """.trimIndent()
      )
  }.resizableRow()
}
```
</chapter>
</chapter>
<chapter title="Text applies to a single item" id="single_item">
If the help text applies to a single list, tree, or table item, its location will depend on its length.
<chapter title="Short texts" id="short_texts">
Place short texts (1–10 words) to the right of the item:

<img src="inline_text_on_the_right_tree.png" width="706" alt="Inline text under the table"/>
</chapter>
<chapter title="Long texts" id="long_texts">

* Place longer texts (more than 10 words) into the detail part of master-detail layouts:

<img src="inline_text_on_the_bottom_master_detail.png" width="706" alt="Inline text on the bottom of the master detail layout"/>

* For other cases, use the [help tooltip](tooltip.md#question-mark-icon-for-help-tooltips) with the question mark icon:

<img src="tooltip_list.png" width="706" alt="Inline text on the bottom of the master detail layout"/>

</chapter>
</chapter>
</chapter>

<chapter title="Group of controls" id="group_of_controls">

If the help text applies to several UI controls, place it at the bottom of the group.

<img src="inline_text_group.png" width="706" alt="Inline text for a group of controls"/>

<chapter title="Implementation" id="implementation_group" collapsible="true" default-state="collapsed">

Use [`Panel.group()`](%gh-ic%/platform/platform-impl/src/com/intellij/ui/dsl/builder/Panel.kt) as the border for panels that need title and possibly the gray line on the right of the title:

```kotlin
panel {
  group("Build and Run") {
    row("Build and run with:") {
      comboBox(listOf("IntelliJ IDEA", "Gradle"))
    }
    row("Run tests with:") {
      comboBox(listOf("IntelliJ IDEA", "Gradle"))
    }
    row {
      comment("""
        <p>By default IntelliJ IDEA uses Gradle to build the project
        and run the tasks.</p>
        <p>In a pure Java/Kotlin project, building and running
        by means of IDE might be faster, thanks to optimizations.
        Note, that the IDE doesn't support all Gradle plugins and
        the project might not be built correctly with some of them.</p>
        """.trimIndent()
      )
    }
  }
  group("Gradle") {
    row("Use Gradle from:") {
      comboBox(gradleModel)
    }
  }
}
```

You can find more examples by invoking the **Tools | Internal Actions | UI | Kotlin UI DSL | UI DSL Showcase** action (available in [internal mode](enabling_internal.md) and clicking the **View source** links on specific pages.

</chapter>
</chapter>
</chapter>

