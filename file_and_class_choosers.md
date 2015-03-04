---
title: File and Class Choosers
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/File+and+Class+Choosers
-->

# {{ page.title }}

## File Choosers

To let a user choose a file, directory or multiple files, use the ```FileChooser.chooseFiles()``` method. This method has multiple overloads; the best method to use is the one which returns void and takes a callback receiving the list of selected files as a parameter. This is the only overload which will display a native file open dialog on Mac OS X.

The FileChooserDescriptor class allows you to control which files can be selected. The constructor parameters specify whether files and/or directories can be selected, and whether multiple selection is allowed. For more fine-grained control over the allowed selection, you can overload the ```isFileSelectable()``` method. You can also customize the presentation of files by overloading getIcon(), getName() and getComment() methods on FileChooserDescriptor. Note that the native Mac OS X file chooser does not support most of the customizations, so if you rely on them, you need to use an overload of ```chooseFiles()``` which displays the standard IntelliJ IDEA dialog.

A very common way of using file choosers is to use a text field for entering the path with an ellipsis button ("...") for showing the file chooser. To create such a control, use the ```TextFieldWithBrowseButton``` component and call the ```addBrowseFolderListener()``` method on it to set up the file chooser. As an added bonus, this will enable filename completion when entering paths in the text box.

An alternative UI for selecting files, which works best when the most common way of selecting a file is by typing its name, is available through the ```TreeFileChooserFactory``` class. The dialog shown by this API has two tabs: one shows the project structure and another shows a list of files similar to the one used by the "Goto File" popup. To show the dialog, call ```showDialog()``` on the chooser returned from ```createFileChooser()```, and then call ```getSelectedFile``` to retrieve the user's selection.

## Class and Package Choosers

If you want to offer the user a possibility to select a Java class, you can use the ```TreeClassChooserFactory``` class. Its different methods allow you to specify the scope from which the classes are taken, to restrict the choice to descendants of a specific class or implementations of an interface, and to include or exclude inner classes from the list.

For choosing a Java package, you can use the ```PackageChooserDialog``` class.

