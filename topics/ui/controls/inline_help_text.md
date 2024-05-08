<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Inline Help Text

<link-summary>UI guidelines on using inline help texts.</link-summary>

Inline help text provides useful information about a setting.

![](01_header_pic.png){width=304}

## When to use

Follow the rules for [context help](context_help.md).

## How to use

### Text length and formatting

Show no more than 5 lines of help text not to clutter the screen. Note that the text width is limited to 70 characters.

![](02_text_size.png){width=380}

Show more than 5 lines only when a text cannot be shortened for legal purposes.

![](03_text_size_long.png){width=396}

<p>Implementation</p>

```java
JCheckBox checkBox = new JCheckBox("Send usage statistics when using EAP versions");
JPanel panel = UI.PanelFactory.panel(checkBox).
   withComment("<p>Help JetBrains improve its products by sending anonymous data about features and plugins used, hardware and software configuration, statistics on types of files, number of files per project, etc.</p>
  <br/>
  <p>Please note that this will not include personal data or any sensitive information, such as source code, file names, etc.
The data sent complies with the <a href=\"https://www.jetbrains.com\">JetBrains Privacy Policy</a></p>").
createPanel();
```

Provide a link to the corresponding help article or to a place in the IDE where the related settings can be found.
Place the link at the end of the text where possible so that it does not disrupt reading.

![](04_link_external.png){width=366 style=block}
*External link*

![](04_link_internal.png){width=345 style=block}
*Local link*

Text style formatting:
* Avoid text highlighting. Usually, the help text is short and no bold or italics are needed.

  ![](inline_text_no_styling.png){width=364}

* Use formatting for code, console commands, or parameters. Use HTML tags. Enclosing text in `<html></html>` tags is not needed.

  ![](inline_text_parameter_styling.png){width=213}

Avoid using brackets.

![](05_no_brackets.png){width=362}

### Writing guidelines

Make help text [short and descriptive](writing_short.md).

Do not repeat the setting name in the help text.

![](06_inline_text_dont_repeat_setting.png){width=481}


## Placement

### Labeled input, button, checkbox, or radio button

Labeled inputs are fields, combo boxes, or text areas.

Place the help text to the right of a labeled input, checkbox, or radio button if all the following applies:
* The space to the right is empty.
* The help text has 1–5 words, not counting articles and prepositions.
* The control label has 1–5 words.

![](07_right_inputs.png){width=433}

```java
JPanel p = UI.PanelFactory.grid().splitColumns().
 add(UI.PanelFactory.panel(new JComboBox<>(new String [] {"Default", "Non default"}))
     .withLabel("Plugin update policy:")
     .withComment("Ignore by Maven 3+")
     .moveCommentRight()).
 add(UI.PanelFactory.panel(new JTextField())
     .withLabel("Thread count:")
     .withComment("-T option")
     .moveCommentRight()).
 createPanel();
```

![](08_right_checkboxes.png){width=438}

```java
JCheckBox cb1 = new JCheckBox("Build project automatically");
JCheckBox cb2 = new JCheckBox("Compile independent modules in parallel");
JPanel p2 = UI.PanelFactory.grid().
 add(UI.PanelFactory.panel(cb1).resizeX(false)
     .withComment("Works while not running / debugging")
     .moveCommentRight()).
 add(UI.PanelFactory.panel(cb2).resizeX(false)
     .withComment("May require larger heap size")
     .moveCommentRight()).
createPanel();
```

![](09_right_button.png){width=309}

Otherwise, place the help text under the UI control.

![](10_under_field.png){width=484}

```java
TextFieldWithBrowseButton tfbb =
    new TextFieldWithBrowseButton(e ->
        System.out.println("JTextField browse button pressed")
    );
JPanel p = UI.PanelFactory.panel(tfbb)
    .withLabel("Default directory:")
    .withComment("Preselected in \"Open ...\" and \"New | Project\" dialogs");
```

If there is no space under the UI control, use the [help tooltip](tooltip.md#question-mark-icon-for-help-tooltips) with the question mark icon for labeled inputs, checkboxes, and radio buttons.
For buttons, use the help tooltip without the icon.


### List, tree or table

If the help text applies to a whole list, tree, or table, place it below the control.

![](11_under_table.png){width=531}

```java
JTable table = createTable(); // Actual table creation
JBScrollPane pane = new JBScrollPane(table);
pane.setPreferredSize(JBUI.size(400, 300));
pane.putClientProperty(UIUtil.KEEP_BORDER_SIDES, SideBorder.ALL);
JPanel panel = UI.PanelFactory.panel(pane).
 withComment("&lt;Project> is content roots of all modules, all immediate descendants<br/>of the projects base directory, and .idea directory contents")
    .resizeY(true)
    .createPanel();
```

If it applies to a single list, tree or table item:
* If the help text has 1–10 words, place it to the right of the item.
*
![](12_tree_inline_help_text.png){width=422}

* If the text is longer than 10 words:

<p>For a list or tree in the master part, place the text into the detail part.</p>

![](13_master-detail_help_text.png){width=673}

If the case with the master-detail layout above does not apply, use the [help tooltip](tooltip.md#question-mark-icon-for-help-tooltips) with the question mark icon.

![](05_question_icon_tree.png){width=390}


### Group of controls

If the help text applies to several UI controls, place it at the bottom of the group.

![](14_under_group.png){width=430}

<p>Implementation</p>

Use [`IdeaTitledBorder`](%gh-ic%/platform/platform-api/src/com/intellij/ui/border/IdeaTitledBorder.java) as the border for panels that need title and possibly the gray line on the right of the title:

```java
String INNER_COMMENT = "<p>By default IntelliJ IDEA uses Gradle to build the project and run the tasks.</p>"+
   "<p>In a pure Java/Kotlin project, building and running by means of IDE might be faster, thanks to optimizations.
    Note, that the IDE doesn't support all Gradle plugins and the project might not be built correctly with some of them.</p>";
 JPanel innerGrid = UI.PanelFactory.grid().splitColumns().
 add(UI.PanelFactory.panel(new JComboBox<>(new String [] {"IntelliJ IDEA", "Gradle"}))
     .resizeX(false).withLabel("Build and run with:")).
 add(UI.PanelFactory.panel(new JComboBox<>(new String [] {"IntelliJ IDEA", "Gradle"}))
     .resizeX(false).withLabel("Run tests with:")).
 createPanel();
JPanel panel = UI.PanelFactory.panel(innerGrid)
    .withComment(INNER_COMMENT).createPanel();
panel.setBorder(IdeBorderFactory.createTitledBorder("Build and Run"));
```

You can find more examples in the [`ComponentPanelTestAction`](%gh-ic%/platform/platform-impl/src/com/intellij/internal/ui/ComponentPanelTestAction.java) implementation
or the <ui-path>Tools | Internal Actions | UI | Show Standard Panels</ui-path> action (available in [internal mode](enabling_internal.md)).

