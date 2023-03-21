# Working with Icons and Images

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Adding, organizing, and working with IntelliJ Platform and custom icons and images.</link-summary>

<tldr>

**Code**: [`AllIcons`](%gh-ic%/platform/util/ui/src/com/intellij/icons/AllIcons.java)

**Platform UI Guidelines:** [Icons list](https://jetbrains.design/intellij/resources/icons_list/), [Icons](https://jetbrains.design/intellij/principles/icons/)

</tldr>

Icons and images are used widely by IntelliJ Platform plugins.
Plugins need icons mostly for [](basic_action_system.md), custom component renderers, [](tool_windows.md), and so on.

> Plugin _Logos_, which represent a plugin itself, have different requirements than icons and images used within a plugin.
> For more information, see the [](plugin_icon_file.md).
>

## Platform vs. Custom Icons

Plugins should reuse existing platform icons whenever possible.
Use [Icons list](https://jetbrains.design/intellij/resources/icons_list/) to browse existing icons.
Platform icons are located in [`AllIcons`](%gh-ic%/platform/util/ui/src/com/intellij/icons/AllIcons.java).
Icons from plugins are located in corresponding `<PLUGIN_NAME>Icons` class (e.g., [`GithubIcons`](%gh-ic%/plugins/github/gen/org/jetbrains/plugins/github/GithubIcons.java)).

If custom icons are required, please refer to detailed [design guide](https://jetbrains.design/intellij/principles/icons/).
To generate SVG icons suited for the IntelliJ-based IDEs, also consider third-party web tool [IntelliJ Icon Generator](https://bjansen.github.io/intellij-icon-generator/).

## How to organize and how to use icons?

> See [Action Basics](%gh-sdk-samples%/action_basics) sample plugin as a reference.
>

In the case of a Gradle-based project, icons should be placed in the <path>resources</path> folder.
If the project is DevKit-based, the recommended approach is to put icons to a dedicated [source root](https://www.jetbrains.com/help/idea/content-roots.html) marked as <control>Resources Root</control>, say <path>icons</path> or <path>resources</path>.

The `getIcon()` method of [`IconLoader`](%gh-ic%/platform/util/ui/src/com/intellij/openapi/util/IconLoader.kt) can be used to access the icons.
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
{style="note"}

Use these constants inside <path>[plugin.xml](plugin_configuration_file.md)</path> when specifying `icon` attribute for [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) or extension point, as well in [`@Presentation`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/Presentation.java) `icon` attribute.
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
{style="note"}

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

### PNG Format

> Use [SVG icons](#svg-format) for optimal results if your plugin targets 2018.2+.
>
{style="note"}

All icon files must be placed in the same directory following this naming pattern (replace <path>.png</path> with <path>.svg</path> for SVG icons):

| Theme/Resolution | File name pattern                 | Size        |
|------------------|-----------------------------------|-------------|
| Default          | <path>iconName.png</path>         | W x H       |
| Darcula          | <path>iconName_dark.png</path>    | W x H       |
| Default + Retina | <path>iconName@2x.png</path>      | 2\*W x 2\*H |
| Darcula + Retina | <path>iconName@2x_dark.png</path> | 2\*W x 2\*H |

The `IconLoader` class will load the icon that matches the best depending on the current environment.

Here are examples of <path>toolWindowStructure.png</path> icon representations:

| Theme/Resolution | File name                                    | Image                                                                   |
|------------------|----------------------------------------------|-------------------------------------------------------------------------|
| Default          | <path>toolWindowStructure.png</path>         | ![Tool Window Structure](toolWindowStructure.png)                       |
| Darcula          | <path>toolWindowStructure_dark.png</path>    | ![Tool Window Structure, dark](toolWindowStructure_dark.png)            |
| Default + Retina | <path>toolWindowStructure@2x.png</path>      | ![Tool Window Structure, retina](toolWindowStructure@2x.png)            |
| Darcula + Retina | <path>toolWindowStructure@2x_dark.png</path> | ![Tool Window Structure, retina, dark](toolWindowStructure@2x_dark.png) |

## Animated Icons

Animated icons are a way to show that plugin is now performing some long-time action.
For example, when plugin is loading some data.

Any animated icon is a set of frames that loop with some delay.

To create a new animated icon, use the
[`AnimatedIcon`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java).
If you want to create an icon where frames follow each other with the same delay, use a constructor that accepts a delay and icons:

```java
AnimatedIcon icon = new AnimatedIcon(500,
          AllIcons.Ide.Macro.Recording_1,
          AllIcons.Ide.Macro.Recording_2);
```

To create an icon from frames with different delays, use `AnimatedIcon.Frame`.
Each frame represents an icon, and a delay until the next frame.

If you want to show somewhere that there is a long process, you can use the predefined `AnimatedIcon.Default` loader icon.
This icon has a larger `AnimatedIcon.Big` version.

## Icon Tooltips

Register resource bundle via `com.intellij.iconDescriptionBundle` extension point to provide tooltips automatically for all [`SimpleColoredComponent`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SimpleColoredComponent.java) renderers.

Create `icon.<icon-path>.tooltip` key in given resource bundle, where `<icon-path>` is the icon path with leading slash and `.svg` removed and slashes replaced with dots
(e.g., `/nodes/class.svg` &rarr; `icon.nodes.class.tooltip`).

## Mapping New UI Icons

> This feature is available since 2022.3.

To fully support [New UI](https://www.jetbrains.com/help/idea/new-ui.html), the plugin must provide additional dedicated icons and mapping information.
This allows supporting both UI variants at the same time — whichever the user chooses to use.

<procedure title="Setup">

1. Create new <path>expUi</path> folder in your icon root folder ([Reference](#how-to-organize-and-how-to-use-icons)).
2. Copy all icons for _New UI_ in this folder.
3. Create empty <path>$PluginName$IconMappings.json</path> mapping file in the resources root folder.
4. Register <path>$PluginName$IconMappings.json</path> file in <path>plugin.xml</path> via `com.intellij.iconMapper` extension point.

</procedure>

> Sample setup from Maven plugin:
>
> - Icon resources root folder: [`images`](%gh-ic%/plugins/maven/src/main/resources/images)
> - Mapping file: [`MavenIconMappings.json`](%gh-ic%/plugins/maven/src/main/resources/MavenIconMappings.json)
> - Extension point registration (`<iconMapper mappingFile="MavenIconMappings.json"/>`): [`plugin.xml`](%gh-ic%/plugins/maven/src/main/resources/META-INF/plugin.xml)

### Mapping Entries

All _New UI_ icons must be mapped in the <path>$PluginName$IconMappings.json</path> mapping file.

For each _New UI_ icon, add a mapping entry inside `expui` block.
Each folder starts a new block containing all its entries (see linked `MavenIconMappins.json` sample from above).

In this sample, the icon root folder is named <path>icons</path>:
```json
{
  "icons": {
    "expui": {
      "folderName": {
        "expUiIcon1.svg": "icons/icon1.svg",
        "expUiIcon2.svg": "icons/icon2.svg",
      },
      "anotherFolder": {
        "expUiAnotherIcon.svg": "images/anotherIcon.svg"
      }
    }
  }
}
```

If one new icon replaces several old icons, use JSON list format. Example from [`PlatformIconMappings.json`](%gh-ic%/platform/icons/src/PlatformIconMappings.json):

```json
    "vcs.svg": [
      "toolwindows/toolWindowChanges.svg",
      "vcs/branch.svg"
    ]
```
