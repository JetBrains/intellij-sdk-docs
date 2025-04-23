<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Working with Icons

<link-summary>Adding, organizing, and working with IntelliJ Platform and custom icons.</link-summary>

<tldr>

**Code**: [`AllIcons`](%gh-ic%/platform/util/ui/src/com/intellij/icons/AllIcons.java)

**UI Guidelines:** [Icons list](https://intellij-icons.jetbrains.design), [](icons_style.md)

**See also**: [UI FAQ - Icons](ui_faq.md#icons)

</tldr>

Icons are used widely by IntelliJ Platform plugins.
Plugins need icons mostly for [actions](action_system.md), custom component renderers, [](tool_windows.md), etc.

> A plugin _logo_, which represents the plugin itself, has different requirements than icons used within plugins.
> For more information, see the [](plugin_icon_file.md) section.

## Platform vs. Custom Icons

Plugins should reuse existing platform icons whenever possible.

Use the [Icons list](https://intellij-icons.jetbrains.design) to browse existing icons.
Platform icons are located in [`AllIcons`](%gh-ic%/platform/util/ui/src/com/intellij/icons/AllIcons.java).
Icons from plugins are located in the corresponding `<PLUGIN_NAME>Icons` class (e.g., [`GithubIcons`](%gh-ic%/plugins/github/github-core/gen/org/jetbrains/plugins/github/GithubIcons.java)).

If custom icons are required, refer to detailed [design guide](icons_style.md).

## Organizing Icons

> See [Action Basics](%gh-sdk-samples-master%/action_basics) sample plugin as a reference.

In the case of a Gradle-based project, icons should be placed in the <path>resources</path> directory.
If the project is DevKit-based, the recommended approach is to put icons to a dedicated [source root](https://www.jetbrains.com/help/idea/content-roots.html) marked as <control>Resources Root</control>, e.g., <path>icons</path> or <path>resources</path>.

If the icons are referenced only in <path>[plugin.xml](plugin_configuration_file.md)</path> attributes or elements, or in the [`@Presentation`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/Presentation.java) `icon` attribute, then they can be [referenced](#using-icons) by paths.
In case the icons are referenced from the code and/or XML many times, it's convenient to organize them in an [icon holder class](#icons-class).

### Icons Class

Define a class/interface in a top-level package called `icons` holding icon constants as static fields:

<tabs>
<tab title="Java">

```java
package icons;

public interface MyIcons {
  Icon Action = IconLoader.getIcon("/icons/action.svg", MyIcons.class);
  Icon ToolWindow = IconLoader.getIcon("/icons/toolWindow.svg", MyIcons.class);
}
```

</tab>

<tab title="Kotlin">

When using Kotlin, fields must be annotated with `@JvmField`:

```kotlin
package icons

object MyIcons {
  @JvmField
  val Action = IconLoader.getIcon("/icons/action.svg", javaClass)
  @JvmField
  val ToolWindow = IconLoader.getIcon("/icons/toolWindow.svg", javaClass)
}
```

</tab>
</tabs>

The `getIcon()` method of [`IconLoader`](%gh-ic%/platform/util/ui/src/com/intellij/openapi/util/IconLoader.kt) can be used to access the icons.
The path to the icon passed in as argument to `IconLoader.getIcon()` **must** start with leading `/`.

> Starting with 2021.2, `*Icons` class is not required to be located in `icons` package but can use plugin's package: `icons.MyIcons` &rarr; `com.example.plugin.MyIcons`.
>
{style="note"}

## Using Icons

Icons defined inside <path>plugin.xml</path> with `icon` attribute for [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) or extension point, as well in `@Presentation`'s `icon` attribute, can be referenced in two ways:
- by icon file path
- by icon constant in the icon holder class

To reference an icon by path, provide the path relative to the resources directory, e.g., for icons located in <path>my-plugin/src/main/resources/icons</path> directory:

```xml
<actions>
  <action icon="/icons/myAction.svg" ... />
</actions>

<extensions defaultExtensionNs="com.intellij">
  <toolWindow icon="/icons/myToolWindow.svg" ... />
</extensions>
```

In the case of icon holder class, reference the icon constants.
Note that if the class is located in the top-level `icons` package, name `icons` will be automatically prefixed and must not be specified.
In case of placing the class in a custom package, the full package name must be provided, e.g.:

```xml
<actions>
  <!-- referencing icons from class in top-level 'icons' package -->
  <action icon="MyIcons.MyAction" ... />
</actions>

<extensions defaultExtensionNs="com.intellij">
  <!-- referencing icons from custom package -->
  <toolWindow icon="com.example.plugin.MyIcons.MyToolWindow" ... />
</extensions>
```

## Icon Formats

IntelliJ Platform supports Retina displays and has a bundled dark theme called [Darcula](https://www.jetbrains.com/help/idea/user-interface-themes.html).
Thus, every icon should have a dedicated variant for Retina devices and Darcula theme.
In some cases, you can skip dark variants if the original icon looks good under Darcula.

Required icon sizes depend on the usage as listed in the following table:

| Usage                  | Icon Size (pixels) |
|------------------------|--------------------|
| Node, Action, Filetype | 16x16              |
| Tool window            | 13x13              |
| Editor gutter          | 12x12              |
| Editor gutter (New UI) | 14x14              |

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

### PNG Format (Deprecated)

> Use [SVG icons](#svg-format) for if your plugin targets 2018.2+.
>
{style="warning"}

All icon files must be placed in the same directory following this naming pattern (replace <path>.png</path> with <path>.svg</path> for SVG icons):

| Theme/Resolution | File name pattern                 | Size        |
|------------------|-----------------------------------|-------------|
| Default          | <path>iconName.png</path>         | W x H       |
| Darcula          | <path>iconName_dark.png</path>    | W x H       |
| Default + Retina | <path>iconName@2x.png</path>      | 2\*W x 2\*H |
| Darcula + Retina | <path>iconName@2x_dark.png</path> | 2\*W x 2\*H |

The `IconLoader` class will load the icon that matches the best depending on the current environment.

Here are examples of <path>toolWindowStructure.png</path> icon representations:

| Theme/Resolution | File name                                    | Icon                                                                    |
|------------------|----------------------------------------------|-------------------------------------------------------------------------|
| Default          | <path>toolWindowStructure.png</path>         | ![Tool Window Structure](toolWindowStructure.png)                       |
| Darcula          | <path>toolWindowStructure_dark.png</path>    | ![Tool Window Structure, dark](toolWindowStructure_dark.png)            |
| Default + Retina | <path>toolWindowStructure@2x.png</path>      | ![Tool Window Structure, retina](toolWindowStructure@2x.png)            |
| Darcula + Retina | <path>toolWindowStructure@2x_dark.png</path> | ![Tool Window Structure, retina, dark](toolWindowStructure@2x_dark.png) |

## Animated Icons

Animated icons are a way to show that a plugin is now performing some long-time action, e.g., when the plugin is loading some data.

Any animated icon is a set of frames that loop with a delay.

To create a new animated icon, use the
[`AnimatedIcon`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java).
To create an icon where frames follow each other with the same delay, use a constructor that accepts a delay and icons:

```java
AnimatedIcon icon = new AnimatedIcon(
    500,
    AllIcons.Ide.Macro.Recording_1,
    AllIcons.Ide.Macro.Recording_2);
```

To create an icon from frames with different delays, use `AnimatedIcon.Frame`.
Each frame represents an icon, and a delay until the next frame.

Use the predefined `AnimatedIcon.Default` loader icon to indicate a long process.
This icon has a larger `AnimatedIcon.Big` version.

> Add `true` [`AnimatedIcon.ANIMATION_IN_RENDERER_ALLOWED`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java) client property to list, table, and tree components to repaint animated icons automatically.
> See `ANIMATION_IN_RENDERER_ALLOWED`'s Javadoc for details.

## Icon Tooltips

Register a resource bundle via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconDescriptionBundle"/></include> to provide tooltips automatically for all [`SimpleColoredComponent`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SimpleColoredComponent.java) renderers.

Create `icon.<icon-path>.tooltip` key in a resource bundle, where `<icon-path>` is the icon path with leading slash and `.svg` removed and slashes replaced with dots (e.g., `/nodes/class.svg` &rarr; `icon.nodes.class.tooltip`).

## New UI Icons
<primary-label ref="2022.3"/>

> See [](UI_kit.md) for guidelines and overview.

To fully support the [New UI](https://www.jetbrains.com/help/idea/new-ui.html), the plugin must provide additional dedicated icons and mapping information.
This allows supporting both UI variants at the same time, depending on what the user has selected.

<procedure title="Setup">

1. Create a new <path>expui</path> directory in the icon root directory ([Reference](#organizing-icons)).
2. Copy all icons for the New UI into this directory.
3. Create an empty <path>\$PluginName\$IconMappings.json</path> mapping file in the resources directory.
4. Register <path>\$PluginName\$IconMappings.json</path> in <path>plugin.xml</path> via the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconMapper"/></include>.

</procedure>

> Sample setup from Maven plugin:
>
> - Icon resources root directory: [`images`](%gh-ic%/plugins/maven/src/main/resources/images)
> - Mapping file: [`MavenIconMappings.json`](%gh-ic%/plugins/maven/src/main/resources/MavenIconMappings.json)
> - Extension point registration (`<iconMapper mappingFile="MavenIconMappings.json"/>`): [`plugin.xml`](%gh-ic%/plugins/maven/src/main/resources/META-INF/plugin.xml)

### Mapping Entries

All New UI icons must be mapped in the <path>\$PluginName\$IconMappings.json</path> mapping file.

For each New UI icon, add a mapping entry inside `expui` block.
Each directory starts a new block containing all its entries (see linked `MavenIconMappings.json` sample from above).

In this example, the icon root directory is named <path>icons</path>:
```json
{
  "icons": {
    "expui": {
      "dirName": {
        "icon1.svg": "icons/icon1.svg",
        "icon2.svg": "icons/icon2.svg"
      },
      "anotherDir": {
        "anotherIcon.svg": "images/anotherIcon.svg"
      }
    }
  }
}
```

If one new icon replaces several old icons, use a JSON list.
Example from [`PlatformIconMappings.json`](%gh-ic%/platform/icons/src/PlatformIconMappings.json):

```json
"vcs.svg": [
  "toolwindows/toolWindowChanges.svg",
  "vcs/branch.svg"
]
```

### New UI Tool Window Icons

The New UI uses _outlined_ icons for tool windows that have a size of 20x20 pixels and 16x16 pixels in
[compact mode](https://www.jetbrains.com/help/idea/new-ui.html#compact-mode).
Plugin developers who want to provide all necessary variants of their tool window icons
use the following suffix scheme for their icon filename, here referred to as <path>iconToolWindow</path>:

- <path>iconToolWindow.svg</path>: a 16x16 pixels _compact mode_ variant of the icon for the light theme.
- <path>iconToolWindow_dark.svg</path>: a 16x16 pixels _compact mode_ variant of the icon for the dark theme.
- <path>iconToolWindow@20x20.svg</path>: a 20x20 pixels variant of the icon for the light theme.
- <path>iconToolWindow@20x20_dark.svg</path>: a 20x20 pixels variant of the icon for the dark theme.

### New UI Icon Colors

To work as expected, the New UI requires specific colors for icon content.
This is necessary for situations where tool window buttons are active, during which the background is highlighted.
To enhance contrast, the IntelliJ Platform dynamically alters the icon's content color to white.

Hence, for the creation of light and dark mode variants, plugin authors must use the following
prescribed colors within their icons:

| Theme | Color Code                                  |
|-------|---------------------------------------------|
| Light | `#6C707E` <format color="6C707E">▆</format> |
| Dark  | `#CED0D6` <format color="CED0D6">▆</format> |

> Various online resources, like the UI Guidelines
> [here](tool_window.md) and
> [here](icons_style.md#grid-and-size),
> will be updated soon and currently don't include information about the New UI.
>
{style="note"}
