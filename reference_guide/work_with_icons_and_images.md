---
title: Work with Icons and Images
---


Icons and images are used widely by IntelliJ IDEA plugins.
Plugins need icons mostly for actions, custom components renderers, tool windows and so on.
This page describes how to organize your work with icons and images under IntelliJ Platform.

## How to organize and how to use icons?

The best way to deal with icons and other image resources is to put them to a dedicated source root, say *"icons"* or *"resources"*.

![Icons](img/icons1.png)

Then you should use ```getIcon()``` method of ```com.intellij.openapi.util.IconLoader``` class in your code to get access icons.
You can define a class or an interface with icon constants:

```java
public interface DemoPluginIcons {
  Icon STRUCTURE_TOOL_WINDOW = IconLoader.getIcon("/toolWindowStructure.png");
  Icon MY_LANG_FILE_TYPE = IconLoader.getIcon("/myLangFileType.png");
  Icon DEMO_ACTION = IconLoader.getIcon("/demoAction.png");
}
```
and use icons inside plugin.xml as well:

```xml
<action id="DemoPlugin.DemoAction"
    class="com.jetbrains.demoplugin.actions.DemoAction"
    text="Demo Action"
    description="This is just a demo"
    icon="/demoAction.png">
</action>
```

### Image formats and naming notations

IntelliJ IDEA Platform supports Retina displays and has dark look and feel called Darcula.
According this all images should have 4 different representations (in some cases only 2).
Let's take a look on the example above.
We've got three icons:

*  for file type

*  for an action

*  and for a tool window.

All tool windows should have icon size of *13x13*  pixels and all actions should have icon size of *16x16*  pixels (tree nodes, file types, and almost all icons have size 16x16 pixels).
So, every icon should have a copy for Retina devices and Darcula look and feel.

*  **iconName.png** W x H pixels (Will be used on non-Retina devices with white look and feel)

*  **iconName@2x.png** 2\*W x 2\*H pixels (Will be used on Retina devices with white look and feel)

*  **iconName_dark.png** W x H pixels (Will be used on non-Retina devices with Darcula look and feel)

*  **iconName@2x_dark.png** 2\*W x 2\*H pixels (Will be used on Retina devices with Darcula look and feel)


IconLoader will load the icon that matches the best depending on the current&nbsp;environment.
So, always use ```IconLoader.getIcon("/iconName.png")```.
Here are examples of *toolWindowStructure.png* icon representations:


![Tool Window Structure](img/toolWindowStructure.png)
![Tool Window Structure, dark](img/toolWindowStructure_dark.png)
![Tool Window Structure, retina](img/toolWindowStructure@2x.png)
![Tool Window Structure, retina, dark](img/toolWindowStructure@2x_dark.png)

All icon variants should be in the same directory.
In some cases you can skip dark variants if the original icon looks good under Darcula.
Then you should have only two variants: icon.png and icon@2x.png

