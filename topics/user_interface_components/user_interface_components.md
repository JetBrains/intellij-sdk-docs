[//]: # (title: User Interface Components)

<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform includes a large number of custom Swing components.
Using those components in your plugins will ensure that your plugin looks and works consistently with the UI of the rest of the IDE, and can often reduce the code size compared to using the default Swing components.

 >  Use [UI Inspector](internal_ui_inspector.md) to locate the underlying Swing component implementation or to inspect an existing UI at runtime.
 >
 {type="tip"}

 >  The recommended way of building UIs on the IntelliJ Platform (2019.2 and later) is using [Kotlin UI DSL](kotlin_ui_dsl.md).
> Using GUI designer with Kotlin is currently [not supported](https://youtrack.jetbrains.com/issue/KT-6660).
 >
 {type="note"}

Please refer to [Writing short and clear](https://jetbrains.design/intellij/text/writing_short/) in IntelliJ Platform UI Guidelines on writing UI-related texts.

The following components are particularly noteworthy:

*  Menus and toolbars are built using the [Action System](basic_action_system.md)
*  [Tool Windows](tool_windows.md)
*  [Dialogs](dialog_wrapper.md)
*  [Popups](popups.md)
*  [Notifications](notifications.md)
*  [File and Class Choosers](file_and_class_choosers.md)
*  [Editor Components](editor_components.md)
*  [List and Tree Controls](lists_and_trees.md)
*  Tables (TableView) (TBD)
*  Drag & Drop Helpers (TBD)
*  [Miscellaneous Swing Components](misc_swing_components.md)
    *  Messages
    *  JBSplitter
    *  JBTabs