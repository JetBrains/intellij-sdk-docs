# Tool Window Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tool Windows in IntelliJ SDK Docs][docs:tool_windows]*

## Quickstart

Tool Windows are child windows of the IDE used to display information.
These windows generally have their toolbars (referred to as tool window bars) along the outer edges of the main window containing one or more tool window buttons, which activate panels displayed on the left, bottom, and right sides of the main IDE window.

The current implementation displays a `JPanel` component containing simple icons and information about the actual system date, time, and timezone.
Component is provided by the `MyToolWindow` class through the `getContent()` method invoked inside the `MyToolWindowFactory` implementation.

### Extension Points

| Name                      | Implementation                                  | Extension Point Class |
| ------------------------- | ----------------------------------------------- | --------------------- |
| `com.intellij.toolWindow` | [MyToolWindowFactory][file:MyToolWindowFactory] | `ToolWindowFactory`   |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://www.jetbrains.org/intellij/sdk/docs
[docs:tool_windows]: https://jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extensions.html

[file:MyToolWindowFactory]: ./src/main/java/org/intellij/sdk/toolWindow/MyToolWindowFactory.java
