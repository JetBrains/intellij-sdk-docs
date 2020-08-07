# Tool Window Sample [![JetBrains IntelliJ Platform SDK Docs](https://jb.gg/badges/docs.svg)][docs]
*Reference: [Tool Windows in IntelliJ SDK Docs][docs:tool_windows]*

## Quickstart

Tool windows are child windows of the IDE used to display information. These windows generally have their own toolbars
(referred to as tool window bars) along the outer edges of the main window containing one or more tool window buttons,
which activate panels displayed on the left, bottom, and right sides of the main IDE window.

When the plugin is built and run in the IntelliJ IDE (ref. [Running a Simple Gradle-Based IntelliJ Platform Plugin][docs_run]),
it registers a `ToolWindowFactory` extension, which adds a new Tool Window component to te one of the IDE window's sides.

After clicking on the tool window button, that has been placed on the right side, IDE invokes the `createToolWindowContent`
method specified in the `MyToolWindowFactory` class.

```java
public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow)
```

Within the function, it is possible to access the current `ToolWindow` instance, which allows to pass any kind
of the `JComponent` to the `ContentManager`:

```java
ContentFactory contentFactory = ContentFactory.SERVICE.getInstance();
Content content = contentFactory.createContent(myComponent, "", false);
toolWindow.getContentManager().addContent(content);
```

Current implementation displays a `JPanel` component containing simple icons and information regarding the actual
system date, time, and timezone. Component is provided by the `MyToolWindow` class through the `getContent()` method
invoked inside the `MyToolWindowFactory` implementation. 

## Structure

Tool Window Sample
plugin depends on the [IntelliJ Platform SDK][docs] and [Gradle][docs:gradle] as a build system.

The main plugin definition file is stored in the [plugin.xml][file:plugin.xml] file, which is created accordingly
to the [Plugin Configuration File documentation][docs:plugin.xml]. It describes definitions of the actions, extensions,
or listeners provided by the plugin.

### Extension Points

| Name       | Implementation Class                            | Interface                                  |
| ---------- | ----------------------------------------------- | ------------------------------------------ |
| toolWindow | [MyToolWindowFactory][file:MyToolWindowFactory] | [ToolWindowFactory][sdk:ToolWindowFactory] |

*Reference: [Plugin Extension Points in IntelliJ SDK Docs][docs:ep]*


[docs]: http://www.jetbrains.org/intellij/sdk/docs
[docs:actions]: https://www.jetbrains.org/intellij/sdk/docs/basics/action_system.html
[docs:tool_windows]: https://jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html
[docs:ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs:gradle]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system.html
[docs:plugin.xml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs:listeners]: https://jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_listeners.html

[file:plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[file:MyToolWindowFactory]: ./src/main/java/org/intellij/sdk/toolWindow/MyToolWindowFactory.java

[sdk:ToolWindowFactory]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java
