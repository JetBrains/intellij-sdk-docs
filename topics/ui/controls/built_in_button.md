<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Built-In Button

<link-summary>UI guidelines on using built-in buttons.</link-summary>

A built-in button is an icon placed inside an input control.

![Built-in-button](built_in_button.png){width=706}

<chapter level="3" title="Implementation" id="implenementation_expand" collapsible="true" default-state="collapsed">

To place a button inside a text field, use [`ExtendableTextField`](%gh-ic%/platform/platform-api/src/com/intellij/ui/components/fields/ExtendableTextField.java) and
its `addExtension()` method.
</chapter>

## When to use

Use a built-in button instead of adding a separate [button](button.topic) on the right where possible to save space and declutter the interface:
<table style="none" border="false">
<tr>
<td><format color="369650" style="bold">Correct</format>
<img src="built_in_button_browse_correct.png" alt="Browse button" width="378"/></td>
<td><format color="E55765" style="bold">Incorrect</format>
<img src="built_in_button_browse_incorrect.png" alt="Incorrect browse button" width="378"/></td>
</tr>
</table>


### Browse
Use the **Browse** icon to select a single file or a folder from the disc.

<img src="built_in_button_browse.png" alt="Browse" width="706"/>

[//]: # (An input field with browse button: [`TextFieldWithBrowseButton`]&#40;%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/TextFieldWithBrowseButton.java&#41;)

<chapter collapsible="true" title="Implementation" id="implenementation_browse" default-state="collapsed">
<p>A combo box with the <control>Browse</control> button:</p>
<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val browseExtension = ExtendableTextComponent.Extension.create(
    AllIcons.General.OpenDisk,
    AllIcons.General.OpenDiskHover,
    "Open file",
    { System.out.println("Browse file clicked") }
)
val extComboBox = ComboBox<String>(STRING_VALUES)
extComboBox.setEditable(true)
extComboBox.setEditor(object : BasicComboBoxEditor() {
  override fun createEditorComponent(): JTextField {
    val ecbEditor = ExtendableTextField()
    ecbEditor.addExtension(browseExtension)
    ecbEditor.setBorder(null)
    return ecbEditor
  }
})
```

</tab>
<tab title="Java" group-key="java">

```java
ExtendableTextComponent.Extension browseExtension =
  ExtendableTextComponent.Extension.create(
    AllIcons.General.OpenDisk,
    AllIcons.General.OpenDiskHover,
    "Open file",
    () -> System.out.println("Browse file clicked")
  );
ComboBox<String> extComboBox = new ComboBox<>(STRING_VALUES);
extComboBox.setEditable(true);
extComboBox.setEditor(new BasicComboBoxEditor() {
  @Override
  protected JTextField createEditorComponent() {
    ExtendableTextField ecbEditor = new ExtendableTextField();
    ecbEditor.addExtension(browseExtension);
    ecbEditor.setBorder(null);
    return ecbEditor;
  }
});
```

</tab>
</tabs>
</chapter>


### Expand a field

If the input text can be long and the place is constrained,
use an input field with the **Expand** button:

![Collapsed built-in button](built_in_button_collapsed.png){width=706}

Expanded field:

![Expanded built-in button](built_in_button_expanded.png){width=706}

### List values

Use the **List** icon to select a value from the list of objects such as classes, methods, or environment variables:

![List of values](built_in_button_list.png){width=706}

### Add value
Use the **Add** button to select multiple values, such as files, variables, folder paths. The selected values will be added to the existing list.

![Add a value to the field](built_in_button_add.png){width=706}


## When not to use

### Cut, Copy, Paste
Do not use the **Copy**, **Paste** or **Cut** button. Instead, make sure these actions are available on pressing <shortcut>Cmd/Ctrl+X</shortuct>, <shortcut>Cmd/Ctrl+C</shortuct>, and <shortcut>Cmd/Ctrl+V</shortuct> shortcuts or  from the context menu.
<table style="none" border="false">
<tr>
<td>
    <format color="369650" style="bold">Correct</format>
    <img src="built_in_button_copy_correct.png" alt="Browse button" width="378"/>
</td>
<td>
    <format color="E55765" style="bold">Incorrect</format>
    <img src="built_in_button_copy_incorrect.png" alt="Incorrect browse button" width="378"/>
</td>
</tr>
</table>

### Help, Info
Do not use the **Help** or **Info** buttons to open an external link or a hint. Use the [context help](context_help.md) instead.

<table style="none" border="false">
<tr>
<td><format color="369650" style="bold">Correct</format>
<img src="built_in_button_help_correct.png" alt="Context help " width="378"/></td>
<td><format color="E55765" style="bold">Incorrect</format>
<img src="built_in_button_help_incorrect.png" alt="Incorrect browse button" width="378"/></td>
</tr>
</table>

## Shortcut
The shortcut for a built-in button is <shortcut>Shift+Enter</shortcut>.
The shortcut activates only when the input field with the button is focused.
