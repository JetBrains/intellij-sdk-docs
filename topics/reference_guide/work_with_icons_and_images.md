[//]: # (title: Working with Icons and Images)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Icons and images are used widely by IntelliJ Platform plugins.
Plugins need icons mostly for [actions](basic_action_system.md), custom components renderers, [tool windows](tool_windows.md), and so on.

> Plugin _Logos_, which represent a plugin itself, have different requirements than icons and images used within a plugin.
> For more information see the [](plugin_icon_file.md).
>
{type="tip"}

## Platform vs. Custom Icons

Plugins should reuse existing platform icons whenever possible.
Use [Icons list](https://jetbrains.design/intellij/resources/icons_list/) to browse existing icons.
Platform icons are located in [`AllIcons`](upsource:///platform/util/src/com/intellij/icons/AllIcons.java).
Icons from plugins are located in corresponding `<PLUGIN_NAME>Icons` class (e.g., [`GithubIcons`](upsource:///plugins/github/src/org/jetbrains/plugins/github/GithubIcons.java)).

If custom icons are required, please refer to detailed [design guide](https://jetbrains.design/intellij/principles/icons/).

## How to organize and how to use icons?

> See [Action Basics](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/action_basics) sample plugin as reference.
>
{type="tip"}

The best way to deal with icons and other image resources is to put them to a dedicated source root marked as <control>Resources Root</control>, say <path>icons</path> or <path>resources</path>.

The `getIcon()` method of [`IconLoader`](upsource:///platform/util/ui/src/com/intellij/openapi/util/IconLoader.java) can be used to access the icons.
The path to the icon passed in as argument to `IconLoader.getIcon()` **must** start with leading `/`.

Then define a class/interface in a top-level package called `icons` holding icon constants as static fields:

<tabs>
<tab title="Java">

```java
package icons;

public interface MyIcons {
  Icon Action = IconLoader.getIcon("/icons/myAction.png", MyIcons.class);
  Icon Structure = IconLoader.getIcon("/icons/myStructure.png", MyIcons.class);
  Icon FileType = IconLoader.getIcon("/icons/myFileType.png", MyIcons.class);
}
```

</tab>

<tab title="Kotlin">

When using Kotlin, fields must be annotated with `@JvmField`:

```kotlin
package icons

object MyIcons {
  @JvmField
  val Action = IconLoader.getIcon("/icons/myAction.png", javaClass)
  @JvmField
  val Structure = IconLoader.getIcon("/icons/myStructure.png", javaClass)
  @JvmField
  val FileType = IconLoader.getIcon("/icons/myFileType.png", javaClass)
}
```

</tab>
</tabs>

> Starting with 2021.2, `*Icons` class is not required to be located in `icons` package but can use plugin's package: `icons.MyIcons` &rarr; `my.plugin.MyIcons`.
>
{type="note"}


Use these constants inside <path>plugin.xml</path> when specifying `icon` attribute for `<action>` or extension point, as well in [`@Presentation`](upsource:///platform/analysis-api/src/com/intellij/ide/presentation/Presentation.java) `icon` attribute.
Note that the package name `icons` will be automatically prefixed and must not be specified.

```xml
<actions>
  <action
      id="DemoPlugin.DemoAction"
      icon="MyIcons.Action"
      ... />
</actions>

<extensions defaultExtensionNs="com.intellij">
  <toolWindow
      id="CustomStructure"
      icon="MyIcons.Structure"
      ... />
</extensions>
```

## Image Formats

IntelliJ Platform supports Retina displays and has a bundled dark theme called [Darcula](https://www.jetbrains.com/help/idea/user-interface-themes.html).
Thus, every icon should have a dedicated variant for Retina devices and Darcula theme.
In some cases, you can skip dark variants if the original icon looks good under Darcula.

Required icon sizes depend on the usage as listed in the following table:

| Usage                  | Icon Size (pixels) |
|------------------------|--------------------|
| Node, Action, Filetype | 16x16              |
| Tool window            | 13x13              |
| Editor gutter          | 12x12              |

### SVG Format

> SVG ([Scalable Vector Graphics](https://en.wikipedia.org/wiki/Scalable_Vector_Graphics)) icons are supported since 2018.2.
>
{type="note"}

As SVG icons can be scaled arbitrarily, they provide better results in HiDPI environments or when used in combination with bigger screen fonts (e.g., in presentation mode).

A base size denoting the size (in the user space) of the rendered image in 1x scale should be provided.
The size is set via the `width` and `height` attributes omitting the size units.
If unspecified, it defaults to 16x16 pixels.

A minimal SVG icon file:

```xml
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16">
  <rect width="100%" height="100%" fill="green"/>
</svg>
```

The naming notation used for PNG icons (see below) is still relevant.
However, the `@2x` version of an SVG icon should still provide the same base size.
The icon graphics of such an icon can be expressed in more details via double precision.
If the icon graphics are simple enough so that it renders perfectly in every scale, then the `@2x` version can be omitted.

> For generating the SVG icons suited for the IntelliJ-based IDEs, you may also use the third-party web tool [IntelliJ Icon Generator](https://bjansen.github.io/intellij-icon-generator/).
>
{type="tip"}

### PNG Format
> Please consider using [SVG icons](#svg-format) for optimal results if your plugin targets 2018.2+.
>
{type="note"}

All icon files must be placed in the same directory following this naming pattern (replace <path>.png</path> with <path>.svg</path> for SVG icons):

* <path>iconName.png</path> W x H pixels (Will be used on non-Retina devices with default theme)
* <path>iconName@2x.png</path> 2\*W x 2\*H pixels (Will be used on Retina devices with default theme)
* <path>iconName_dark.png</path> W x H pixels (Will be used on non-Retina devices with Darcula theme)
* <path>iconName@2x_dark.png</path> 2\*W x 2\*H pixels (Will be used on Retina devices with Darcula theme)

The `IconLoader` class will load the icon that matches the best depending on the current environment.

Here are examples of <path>toolWindowStructure.png</path> icon representations:

| Theme/Resolution | File name                                    | Image                                                                   |
|------------------|----------------------------------------------|-------------------------------------------------------------------------|
| Default          | <path>toolWindowStructure.png</path>         | ![Tool Window Structure](toolWindowStructure.png)                       |
| Darcula          | <path>toolWindowStructure_dark.png</path>    | ![Tool Window Structure, dark](toolWindowStructure_dark.png)            |
| Default + Retina | <path>toolWindowStructure@2x.png</path>      | ![Tool Window Structure, retina](toolWindowStructure@2x.png)            |
| Darcula + Retina | <path>toolWindowStructure@2x_dark.png</path> | ![Tool Window Structure, retina, dark](toolWindowStructure@2x_dark.png) |
