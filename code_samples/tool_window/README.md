# Tool Window Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tool Windows in IntelliJ SDK Docs][docs:tool_windows]*

## Quickstart

Tool Windows are child windows of the IDE used to display information.
These windows generally have their toolbars (referred to as tool window bars) along the outer edges of the main window containing one or more tool window buttons, which activate panels displayed on the left, bottom, and right sides of the main IDE window.

The current implementation displays a `JPanel` component containing simple icons and information about the actual system date, time, and timezone.
Component is provided by the `CalendarToolWindowFactory.CalendarToolWindowContent` class through the `getContentPanel()` method invoked inside the `CalendarToolWindowFactory` implementation.

### Extension Points

| Name                      | Implementation                                              | Extension Point Class |
|---------------------------|-------------------------------------------------------------|-----------------------|
| `com.intellij.toolWindow` | [CalendarToolWindowFactory][file:CalendarToolWindowFactory] | `ToolWindowFactory`   |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: https://plugins.jetbrains.com/docs/intellij/
[docs:tool_windows]: https://plugins.jetbrains.com/docs/intellij/tool-windows.html
[docs:ep]: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html

[file:CalendarToolWindowFactory]: ./src/main/java/org/intellij/sdk/toolWindow/CalendarToolWindowFactory.java
