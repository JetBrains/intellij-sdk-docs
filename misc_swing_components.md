---
title: Miscellaneous Swing Components
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/Miscellaneous+Swing+Components
-->

# {{ page.title }}

### Messages

The ```Messages``` class provides a way to show simple message boxes, input dialogs (modal dialogs with a text field) and chooser dialogs (modal dialogs with a combo box). The function of different methods of the class should be clear from their names. When running on Mac OS X, the message boxes shown by the Messages class use native UI.

The ```showCheckboxMessageDialog()``` function provides an easy way to implement a "do not show this again" checkbox on messages.

Note that it is recommended to use non-modal notifications instead of modal message boxes whenever it's appropriate.
Please refer to the [Notifications](notifications.html) topic for more information.

### JBSplitter

The ```JBSplitter``` class is JetBrains' replacement for the standard JSplitPane class.
Unlike some other JetBrains-enhanced Swing components, it's not a drop-in replacement and has a different API.
However, to achieve a consistent user experience, it's recommended to use JBSplitter instead of the standard JSplitPane in your plugins.

To add your components to the splitter, call the ```setFirstComponent``` and ```setSecondComponent``` methods.

JBSplitter supports automatic remembering of the split proportion. In order to enable it, call the ```setSplitterProportionKey``` method and pass the ID under which the proportion will be stored.

### JBTabs

The ```JBTabs``` class is JetBrains' implementation of the tab control, used for editor tabs and a few other components. It has a significantly different look & feel compared to the standard Swing tabs, and looks less native on the Mac OS X platform, so it's up to you to choose which tab control would be more appropriate for your plugin.

