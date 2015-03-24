---
title: Tool Windows
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Tool+Windows
-->

# {{ page.title }}

## Tool Windows

Tool windows are panels displayed on the left, bottom and right sides of the main *IntelliJ IDEA* toolwindow.
Each side contains two tool window groups, the primary and the secondary one, and only one toolwindow from each group can be active at a time.

Each toolwindow can show multiple tabs (or "contents", as they are called in the API).
For example, the Run toolwindow displays a tab for each active run configuration, and the Changes toolwindow displays a fixed set of tabs depending on the version control system used in the project.

There are two main scenarios for the use of tool windows in a plugin.
In the first scenario (used by the Ant and Commander plugins, for example), a toolwindow button is always visible, and the user can activate it and interact with the plugin functionality at any time.
In the second scenario (used by the ```Analyze Dependencies``` action, for example), the toolwindow is created to show the results of a specific operation, and can be closed by the user after the operation is completed.

In the first scenario, the toolwindow is registered in *plugin.xml* using the ```<toolWindow>``` extension point.
The extension point attributes specify all the data which is necessary to display the toolwindow button:

*  The ID of the toolwindow (corresponds to the text displayed on the toolwindow button)

*  The anchor, meaning the side of the screen on which the toolwindow is displayed ("left", "right" or "bottom")

*  The "secondary" attribute, specifying whether the toolwindow is displayed in the primary or the secondary group

*  The icon to display on the toolwindow button (13x13 pixels)

In addition to that, you specify the *factory class*  - the name of a class implementing the
[ToolWindowFactory](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowFactory.java)
interface.
When the user clicks on the toolwindow button, the ```createToolWindowContent()``` method of the factory class is called, and initializes the UI of the toolwindow.
This procedure ensures that unused toolwindows don't cause any overhead in startup time or memory usage: if a user does not interact with the toolwindow of your plugin, no plugin code will be loaded or executed.

If the toolwindow of your plugin doesn't need to be displayed for all projects, you can also specify the *conditionClass*  attribute - the qualified name of a class implementing the
[Condition\<Project\>](https://github.com/JetBrains/intellij-community/blob/master/platform/util-rt/src/com/intellij/openapi/util/Condition.java)
interface (this can be the same class as the toolwindow factory implementation).
If the condition returns false, the toolwindow will not be displayed.
Note that the condition is evaluated only once when the project is loaded;
if you'd like to show your and hide toolwindow dynamically while the user is working with the project, you need to use the second method for toolwindow registration.

The second method involves simply calling
[ToolWindowManager.registerToolWindow()](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/wm/ToolWindowManager.java)
from your plugin code.
The method has multiple overloads that can be used depending on your task.
If you use an overload that takes a component, the component becomes the first content (tab) displayed in the toolwindow.

Displaying the contents of many toolwindows requires access to the indexes.
Because of that, toolwindows are normally disabled while building indices, unless you pass true as the value of ```canWorkInDumbMode``` to the ```registerToolWindow()``` function.

As mentioned previously, toolwindows can contain multiple tabs, or contents.
To manage the contents of a toolwindow, you can call
[ToolWindow.getContentManager()](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/wm/ToolWindow.java).
To add a tab (content), you first need to create it by calling
[ContentManager.getFactory().createContent()](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/ui/content/ContentManager.java),
and then to add it to the toolwindow using
[ContentManager.addContent()](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/ui/content/ContentManager.java).

You can control whether the user is allowed to close tabs either globally or on a per-tab basis.
The former is done by passing the ```canCloseContents``` parameter to the ```registerToolWindow()``` function, or by specifying
```canCloseContents="true"``` in *plugin.xml*.
If closing tabs is enabled in general, you can disable closing of specific tabs by calling
[Content.setCloseable(false)](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/ui/content/Content.java).

