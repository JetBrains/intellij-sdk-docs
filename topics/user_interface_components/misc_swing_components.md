<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Miscellaneous Swing Components

<link-summary>Overview of useful Swing components provided by IntelliJ Platform.</link-summary>

### `Messages`

[`Messages`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/ui/Messages.java) provides a way to show simple
message boxes, input dialogs (modal dialogs with a text field), and chooser dialogs (modal dialogs with a combo box).
On macOS, the message boxes use the native UI.

The `showCheckboxMessageDialog()` function provides an easy way to implement a <control>Do not show this again</control> checkbox on messages.

> It is recommended to use non-modal notifications instead of modal message boxes whenever appropriate.
> Refer to the [](notifications.md) topic for more information.
>
{title="Avoid Modal Dialogs"}

### `JBSplitter`

[`JBSplitter`](%gh-ic%/platform/platform-api/src/com/intellij/ui/JBSplitter.java) is a replacement for the standard
[Swing `JSplitPane`](https://docs.oracle.com/en/java/javase/24/docs/api/java.desktop/javax/swing/JSplitPane.html) class.
Unlike some other JetBrains-enhanced Swing components, it is not a drop-in replacement and has a different API.
However, to achieve a consistent user experience, it is recommended to use `JBSplitter` instead of the standard `JSplitPane`.

To add components to the splitter, call the `setFirstComponent()` and `setSecondComponent()` methods.

`JBSplitter` supports automatic remembering of the split proportion.
To enable it, call the `setSplitterProportionKey()` method and pass the unique ID under which the proportion will be stored.

### `JBTabs`

[`JBTabs`](%gh-ic%/platform/platform-api/src/com/intellij/ui/tabs/JBTabs.java) is an implementation of the tab control, used for editor tabs and a few other components.
It has a significantly different look & feel compared to the standard Swing tabs and looks less native on the macOS platform,
so it's up to the developer to choose which tab control would be more appropriate.

### Toolbars

See [](toolbar.md) in the UI Guidelines for an overview.

[](action_system.md#buildingToolbarPopupMenu) covers creating `AnAction`-based toolbars.
