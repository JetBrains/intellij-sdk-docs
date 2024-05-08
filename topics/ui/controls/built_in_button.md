<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Built-In Button

<link-summary>UI guidelines on using built-in buttons.</link-summary>

A built-in button is an icon placed inside an input control.

![](input_browse.png){width=250}


## How to use

Place the built-in button inside the input control. Do **not** place the built-in button on the right of a control:

![](outside.png){width=250}

To place a button inside a text field, use [`ExtendableTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/fields/ExtendableTextField.java) and
its `addExtension()` method.

The shortcut for a built-in button is <shortcut>Shift+Enter</shortcut>.


## Types

### Browse
A browse button opens a dialog with the disk, a tree view or a table of values.
Use a control with the browse icon for a file/folder path selected from the disk.

![](input_browse.png){width=250}

An input field with browse button: [`TextFieldWithBrowseButton`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/TextFieldWithBrowseButton.java)

A combo box with browse button:

```java
ExtendableTextComponent.Extension browseExtension =
  ExtendableTextComponent.Extension.create(
    AllIcons.General.OpenDisk, AllIcons.General.OpenDiskHover,
    "Open file", () -> System.out.println("Browse file clicked")
  );
ComboBox<String> eComboBox = new ComboBox<>(STRING_VALUES);
eComboBox.setEditable(true);
eComboBox.setEditor(new BasicComboBoxEditor() {
  @Override
  protected JTextField createEditorComponent() {
    ExtendableTextField ecbEditor = new ExtendableTextField();
    ecbEditor.addExtension(browseExtension);
    ecbEditor.setBorder(null);
    return ecbEditor;
  }
});
```

Do **not** place the button on the right of the control.

![](browse_buttons.png){width=250}

### Expand field
If the input text can be long and place is constrained, use a built-in button to expand the control ([`ExtendableTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/fields/ExtendableTextField.java)):

![](expandable_1.png){width=332}

![](expandable_2.png){width=582}

Do **not** use the Show Viewer button instead.

![](input_expand.png){width=357}


### List values
Use a control with the table icon to select from the list of classes, methods or environment variables:

![](input_table.png){width=250}

Use a combo box instead of the Variables button. This icon works as a combo box.

![](variables_combobox.png){width=514}


### Add value
The Plus button works the same way as the Browse button.
The only difference is that the selected value is added, instead of overwriting the existing one.
Place the plus icon inside the control.
![](plus.png){width=250}

### Copy, Info

| ![](copy_button.png){width="57"}                                                                                      | ![](info_button.png){width="57"}                                                                             |
|-----------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| Do not use the Copy button, the content can be selected and copied using the Cmd/Ctrl+C shortcut or the context menu. | Do not use the info button to open an external link. Use <a href="context_help.md">context help</a> instead. |
{style=none}
