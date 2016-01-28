---
title: Tool Windows
---


## Tool Windows

_Tool windows_ are child windows of **IntelliJ IDEA** used to display information. These windows generally have their own toolbars (referred to as _tool window bars_) along the outer edges of the main window containing one or more _tool window buttons_, which activate panels displayed on the left, bottom and right sides of the main *IntelliJ IDEA* toolwindow. For detailed information about tool windows, please see [IntelliJ IDEA Web Help ](https://www.jetbrains.com/idea/help/tool-windows.html).

Each side contains two tool window groups, the primary and the secondary one, and only one toolwindow from each group can be active at a time.

Each toolwindow can show multiple tabs (or "contents", as they are called in the API).
For example, the Run toolwindow displays a tab for each active run configuration, and the Changes toolwindow displays a fixed set of tabs depending on the version control system used in the project.

There are two main scenarios for the use of tool windows in a plugin.
In the first scenario (used by the Ant and Commander plugins, for example), a toolwindow button is always visible, and the user can activate it and interact with the plugin functionality at any time.
In the second scenario (used by the `Analyze Dependencies` action, for example), the toolwindow is created to show the results of a specific operation, and can be closed by the user after the operation is completed.

In the first scenario, the toolwindow is registered in *plugin.xml* using the `<toolWindow>` extension point.
The extension point attributes specify all the data which is necessary to display the toolwindow button:

*  The ID of the toolwindow (corresponds to the text displayed on the toolwindow button)

*  The anchor, meaning the side of the screen on which the toolwindow is displayed ("left", "right" or "bottom")

*  The "secondary" attribute, specifying whether the toolwindow is displayed in the primary or the secondary group

*  The icon to display on the toolwindow button (13x13 pixels)

In addition to that, you specify the *factory class*  - the name of a class implementing the
[ToolWindowFactory](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java)
interface.
When the user clicks on the toolwindow button, the `createToolWindowContent()` method of the factory class is called, and initializes the UI of the toolwindow.
This procedure ensures that unused toolwindows don't cause any overhead in startup time or memory usage: if a user does not interact with the toolwindow of your plugin, no plugin code will be loaded or executed.

If the toolwindow of your plugin doesn't need to be displayed for all projects, you can also specify the *conditionClass*  attribute - the qualified name of a class implementing the
[Condition\<Project\>](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java)
interface (this can be the same class as the toolwindow factory implementation).
If the condition returns false, the toolwindow will not be displayed.
Note that the condition is evaluated only once when the project is loaded;
if you'd like to show your and hide toolwindow dynamically while the user is working with the project, you need to use the second method for toolwindow registration.

The second method involves simply calling
[ToolWindowManager.registerToolWindow()](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowManager.java)
from your plugin code.
The method has multiple overloads that can be used depending on your task.
If you use an overload that takes a component, the component becomes the first content (tab) displayed in the toolwindow.

Displaying the contents of many toolwindows requires access to the indexes.
Because of that, toolwindows are normally disabled while building indices, unless you pass true as the value of `canWorkInDumbMode` to the `registerToolWindow()` function.

As mentioned previously, toolwindows can contain multiple tabs, or contents.
To manage the contents of a toolwindow, you can call
[ToolWindow.getContentManager()](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindow.java).
To add a tab (content), you first need to create it by calling
[ContentManager.getFactory().createContent()](upsource:///platform/platform-api/src/com/intellij/ui/content/ContentManager.java),
and then to add it to the toolwindow using
[ContentManager.addContent()](upsource:///platform/platform-api/src/com/intellij/ui/content/ContentManager.java).

You can control whether the user is allowed to close tabs either globally or on a per-tab basis.
The former is done by passing the `canCloseContents` parameter to the `registerToolWindow()` function, or by specifying
`canCloseContents="true"` in *plugin.xml*.
If closing tabs is enabled in general, you can disable closing of specific tabs by calling
[Content.setCloseable(false)](upsource:///platform/platform-api/src/com/intellij/ui/content/Content.java).

## How to Create a Tool Window?

The IntelliJ Platform provides the _toolWindow_ [extension point](/basics/plugin_structure/plugin_extensions_and_extension_points.md) that you can use to create and configure your custom tool windows. This extension point is declared using the [ToolWindowEP](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowEP.java) bean class.

To create a tool window, first declare an extension to the _toolWindow_ extension point.

### Creation of Plugin

To create a plugin that displays a custom tool window, perform the following steps:

1. In your plugin project, create a Java class that implements the [ToolWindowFactory](upsource:///platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java)interface.
2. In this class, override the `createToolWindowContent` method. This method specifies the content for your tool window.
3. In the plugin configuration file plugin.xml, create the `<extensions defaultExtensionNs="com.intellij">...</extensions>` section.
4. To this section, add the `<toolWindow>` element, and for this element, set the following attributes declared in the ToolWindowEP bean class:
    - **id** (required): specifies the tool window caption.
    - **anchor** (required): specifies the tool window bar where the tool window button will be displayed. Possible values: "left", "right", "top", "bottom."
    - **secondary** (optional): when true, the tool window button will be shown on the lower part of the tool window bar. Default value is false.
    - **factoryClass** (required): specifies the Java class implementing the ToolWindowFactory interface (see Step 1).
    - **icon** (optional): specifies path to the icon that identifies the tool window, if any.
    - **conditionClass** (optional): specifies a Java class that implements the [Condition](upsource:///platform/util-rt/src/com/intellij/openapi/util/Condition.java) interface. Using this class, you can define conditions to be met to display tool window button. In the Condition class, you should override the value method: if this method returns false, the tool window button is not displayed on tool window bar.

To clarify the above procedure, consider the following fragment of the `plugin.xml` file:

```xml
<extensions defaultExtensionNs="com.intellij">
    <toolWindow id="My Sample Tool Window" icon="/myPackage/icon.png" anchor="right" factoryClass="myPackage.MyToolWindowFactory"/>
</extensions>
```

### Sample Plugin

To clarify how to develop plugins that create tool windows, consider the **toolWindow** sample plugin available in the [code_samples](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/) directory of the SDK documentation. This plugin creates the **Sample Calendar** tool window that displays the system date, time and time zone.

**To run toolWindow plugin**

1. Start **IntelliJ IDEA** and open the **tool_window** project saved into the [code_samples/tool_window](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/tool_window) directory.
2. Ensure that the project settings are valid for your environment. If necessary, modify the project settings.  
To view or modify the project settings, you can open the [Project Structure](https://www.jetbrains.com/idea/help/configuring-project-structure.html).
3. Run the plugin by choosing the **Run | Run** on the main menu.  
If necessary, change the [Run/Debug Configurations](http://www.jetbrains.com/idea/help/run-debug-configuration-plugin.html).

The plugin creates the **Sample Calendar** tool window. When opened, this tool window is similar to the following screen:

![Sample Calendar](img/sample_calendar.png)
