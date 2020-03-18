[IntelliJ Platform SDK Code Samples](../README.md)

# Tool Window

## Quickstart

Tool windows are child windows of the IDE used to display information. These windows generally have their own toolbars
(referred to as tool window bars) along the outer edges of the main window containing one or more tool window buttons,
which activate panels displayed on the left, bottom, and right sides of the main IDE window.

See the [Tool Windows][docs_tool_windows] documentation page for more information.

## Structure

The plugin was developed using the [IntelliJ Platform SDK][docs_sdk].

The main file is [plugin.xml][plugin.xml], which is created accordingly to the [Plugin Configuration File documentation][docs_pluginxml].
It describes definitions of the actions, extensions, or listeners provided by the plugin:

### Extension Points

| Name | Implementation Class | Interface |
| ---- | -------------------- | --------- |
| toolWindow | [MyToolWindowFactory][toolWindow_implementation] | [ToolWindowFactory][toolWindow_interface] |

[Extension Points documentation][docs_ep]

## Function

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


[plugin.xml]: ./src/main/resources/META-INF/plugin.xml
[docs_tool_windows]: https://www.jetbrains.org/intellij/sdk/docs/user_interface_components/tool_windows.html
[docs_pluginxml]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_configuration_file.html
[docs_sdk]: https://www.jetbrains.org/intellij/sdk/docs/intro/about.html
[docs_ep]: https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_extension_points.html
[docs_run]: https://www.jetbrains.org/intellij/sdk/docs/tutorials/build_system/prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin

[toolWindow_implementation]: ./src/main/java/org/intellij/sdk/toolWindow/MyToolWindowFactory.java
[toolWindow_interface]: https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java
