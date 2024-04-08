<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# File and Class Choosers

<link-summary>Overview of APIs allowing to create file and class choosers.</link-summary>

## File Choosers

### Via Dialog
To let a user choose a file, directory or multiple files, use the [`FileChooser.chooseFiles()`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/fileChooser/FileChooser.java) method.
This method has multiple overloads.
The best method to use is the one which returns void and takes a callback receiving the list of selected files as a parameter.
This is the only overload which will display a native file open dialog on macOS.

The [`FileChooserDescriptor`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileChooser/FileChooserDescriptor.java) class allows you to control which files can be selected.
The constructor parameters specify whether files and/or directories can be selected, and whether multiple selection is allowed (see [`FileChooserDescriptorFactory`](%gh-ic%/platform/ide-core/src/com/intellij/openapi/fileChooser/FileChooserDescriptorFactory.java) for common variants).

For more fine-grained control over the allowed selection, you can overload the `isFileSelectable()` method.
You can also customize the presentation of files by overriding `getIcon()`, `getName()` and `getComment()` methods.
Note that the native macOS file chooser does not support most of the customizations, so if you rely on them, you need to use an overload of `chooseFiles()` which displays the standard IntelliJ Platform dialog.

### Via Text Field

A very common way of using file choosers is to use a text field for entering the path with an ellipsis button (<control>...</control>) for showing the file chooser.
To create such a control, use the [`TextFieldWithBrowseButton`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/TextFieldWithBrowseButton.java) component and call the `addBrowseFolderListener()` method on it to set up the file chooser.
As an added bonus, this will enable filename completion when entering paths in the text box.

### Via Tree

An alternative UI for selecting files, which works best when the most common way of selecting a file is by typing its name, is available through the
[`TreeFileChooserFactory`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/TreeFileChooserFactory.java) class.

The dialog shown by this API has two tabs:

*  One shows the project structure
*  Another shows a list of files similar to the one used by the <ui-path>Navigate | File</ui-path> popup.

To show the dialog, call `showDialog()` on the chooser returned from `createFileChooser()`, and then call `getSelectedFile()` to retrieve the user's selection.

## Class and Package Choosers

If you want to offer the user a possibility to select a Java class, you can use the [`TreeClassChooserFactory`](%gh-ic%/java/openapi/src/com/intellij/ide/util/TreeClassChooserFactory.java) class.
Its different methods allow you to specify the scope from which the classes are taken, to restrict the choice to descendants of a specific class or implementations of an interface, and to include or exclude inner classes from the list.

For choosing a Java package, you can use the [`PackageChooserDialog`](%gh-ic%/java/java-impl/src/com/intellij/ide/util/PackageChooserDialog.java) class.

> To use Java-specific components, an explicit dependency on the Java plugin is required.
> See [](plugin_compatibility.md#java) for details.
>
{style="note"}
