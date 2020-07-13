---
title: Miscellaneous Swing Components
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

### Messages

The
[`Messages`](upsource:///platform/platform-api/src/com/intellij/openapi/ui/Messages.java)
class provides a way to show simple message boxes, input dialogs (modal dialogs with a text field), and chooser dialogs (modal dialogs with a combo box).
The function of different methods of the class should be clear from their names.
When running on macOS, the message boxes shown by the `Messages` class use the native UI.

The `showCheckboxMessageDialog()` function provides an easy way to implement a _Do not show this again_ checkbox on messages.

Note that it is recommended to use non-modal notifications instead of modal message boxes whenever it's appropriate.
Please refer to the [Notifications](notifications.md) topic for more information.

### JBSplitter

The
[`JBSplitter`](upsource:///platform/platform-api/src/com/intellij/ui/JBSplitter.java)
class is JetBrains' replacement for the standard
[`JSplitPane`](https://docs.oracle.com/javase/8/docs/api/javax/swing/JSplitPane.html)
class.
Unlike some other JetBrains-enhanced Swing components, it's not a drop-in replacement and has a different API.
However, to achieve a consistent user experience, it's recommended to use `JBSplitter` instead of the standard `JSplitPane`.

To add components to the splitter, call the `setFirstComponent()` and `setSecondComponent()` methods.

`JBSplitter` supports automatic remembering of the split proportion.
To enable it, call the `setSplitterProportionKey()` method and pass the ID under which the proportion will be stored.

### JBTabs

The
[`JBTabs`](upsource:///platform/platform-api/src/com/intellij/ui/tabs/JBTabs.java)
class is JetBrains' implementation of the tab control, used for editor tabs and a few other components.
It has a significantly different look & feel compared to the standard Swing tabs, and looks less native on the macOS platform, so it's up to the developer to choose which tab control would be more appropriate.


### Toolbars
See [Building UI from Actions](/basics/action_system.md#building-ui-from-actions) on how to create `AnAction`-based toolbars.