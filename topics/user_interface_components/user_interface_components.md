[//]: # (title: User Interface Components)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The IntelliJ Platform includes a large number of custom Swing components.
Using those components in your plugins will ensure that your plugin looks and works consistently with the UI of the rest of the IDE, and can often reduce the code size compared to using the default Swing components.

> Use [UI Inspector](internal_ui_inspector.md) to locate the underlying Swing component implementation or to inspect an existing UI at runtime.
>
{type="tip"}

> It is recommended to build UI forms like dialogs or settings pages by using the [Kotlin UI DSL](kotlin_ui_dsl_version_2.md) (IntelliJ Platform 2021.3+).
>
> Using GUI designer with Kotlin is currently not supported ([Issue](https://youtrack.jetbrains.com/issue/KTIJ-791)).
>
{type="note"}

Please refer to [Writing short and clear](https://jetbrains.design/intellij/text/writing_short/) in IntelliJ Platform UI Guidelines on writing UI-related texts.

> See [UI Kit](https://jetbrains.design/intellij/resources/UI_kit/) when using [Figma](https://www.figma.com) to design UI.
>
{type="tip"}


The following components are particularly noteworthy:

*  Menus and toolbars are built using the [](basic_action_system.md)
*  [](tool_windows.md)
*  [](dialog_wrapper.md)
*  [](popups.md)
*  [](notifications.md)
*  [](file_and_class_choosers.md)
*  [](editor_components.md)
*  [](lists_and_trees.md)
*  Tables (TableView) (TBD)
*  Drag & Drop Helpers (TBD)
*  [](misc_swing_components.md)
    *  Messages
    *  JBSplitter
    *  JBTabs

> If a topic you are interested in is not covered in the above sections, let us know via the "**Was this page helpful?**" feedback form below or [other channels](getting_help.md#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{type="note"}
