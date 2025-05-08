<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Working with Icons

<link-summary>Adding, organizing, and working with IntelliJ Platform and custom icons.</link-summary>

<tldr>

**Code**: [`AllIcons`](%gh-ic%/platform/util/ui/src/com/intellij/icons/AllIcons.java)

**UI Guidelines:** [Icons list](https://intellij-icons.jetbrains.design), [](icons_style.md)

**See also**: [User Interface FAQ - Icons](ui_faq.md#icons)

</tldr>

Icons are used widely by IntelliJ Platform plugins.
Plugins need icons mostly for [actions](action_system.md), custom component renderers, [](tool_windows.md), etc.

> A [](plugin_icon_file.md), which represents the plugin itself, has different requirements than icons used within plugins.

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

If the icons are referenced only in <path>[plugin.xml](plugin_configuration_file.md)</path> or via [`@Presentation`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/Presentation.java) `icon` attribute, then they can be [referenced by paths](#by-path).
In case the icons are referenced from the code and/or XML many times, it's convenient to organize them in an [icon holder class](#icons-class).

### Icons Class

> Starting with 2021.2, the `*Icons` class is not required to be located in the `icons` package but can use the plugin's package:
>
> `icons.MyIcons` &rarr; `com.example.plugin.MyIcons`.
>
{style="note"}

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

## Using Icons

Icons defined inside <path>plugin.xml</path> with `icon` attribute for [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) or [extension point](plugin_extension_points.md),
as well as in [`@Presentation`](%gh-ic%/platform/analysis-api/src/com/intellij/ide/presentation/Presentation.java) `icon` attribute, can be referenced in two ways.

### By Path

To reference an icon by path, provide the path relative to the resources directory, e.g., for icons located in <path>my-plugin/src/main/resources/icons</path> directory:

```xml
<actions>
  <action icon="/icons/myAction.svg" ... />
</actions>

<extensions defaultExtensionNs="com.intellij">
  <toolWindow icon="/icons/myToolWindow.svg" ... />
</extensions>
```

### By Icons Class

In the case of [icon holder class](#icons-class), reference the icon constants.
Note that if the class is located in the top-level `icons` package, the `icons` package name will be automatically prefixed and must not be specified.
In case of placing the class in a custom package, the full package name must be provided, e.g.:

```xml
<actions>
  <!-- referencing icons from 'MyIcons' class
       in top-level 'icons' package -->
  <action icon="MyIcons.MyAction" ... />
</actions>

<extensions defaultExtensionNs="com.intellij">
  <!-- referencing icons from 'MyIcons' in custom package -->
  <toolWindow icon="com.example.plugin.MyIcons.MyToolWindow" ... />
</extensions>
```

## Icon Files

The IntelliJ Platform supports HiDPI displays and comes with a bundled [dark theme](https://www.jetbrains.com/help/idea/user-interface-themes.html).
Thus, every icon should have a dedicated variant for dark theme and optionally for [HiDPI](#hidpi-version).
If the original icon works well enough in dark theme, a dark variant is not required.

The platform will load the best matching icon variant (if available) depending on the current environment.

### Icon Sizes

Required icon sizes depend on the usage as listed in the following table:

| Usage                                                                       | Icon Size                                                       |
|-----------------------------------------------------------------------------|-----------------------------------------------------------------|
| Node, Action, Filetype                                                      | 16&times;16                                                     |
| Tool window                                                                 | 13&times;13                                                     |
| Tool window for [New UI](https://www.jetbrains.com/help/idea/new-ui.html)   | 20&times;20 and 16&times;16 (see [](#new-ui-tool-window-icons)) |
| Editor gutter                                                               | 12&times;12                                                     |
| Editor gutter for [New UI](https://www.jetbrains.com/help/idea/new-ui.html) | 14&times;14                                                     |

As SVG icons can be scaled arbitrarily, they provide good results in HiDPI environments or when used in combination with
bigger screen fonts (e.g., in [Presentation Mode](https://www.jetbrains.com/help/idea/ide-viewing-modes.html)).

A base size denoting the size (in the user space) of the rendered image in 1x scale should be provided.
The size is set via the `width` and `height` attributes omitting the size units.
If unspecified, it defaults to 16&times;16 pixels.

A minimal SVG icon file:

```xml
<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16">
  <rect width="100%" height="100%" fill="green"/>
</svg>
```
#### HiDPI Version

The icon graphics of such an icon can be expressed in more details via double precision.
If the icon graphics are simple enough so that it renders perfectly in every scale, then the HiDPI version can be omitted.
The HiDPI version should still provide the same base size.

### Filenames

All icon files must be placed in the same directory following this naming pattern:

| Theme/Resolution                | Filename                          | Icon Size                   |
|---------------------------------|-----------------------------------|-----------------------------|
| Light                           | <path>iconName.svg</path>         | W&times;H                   |
| Dark                            | <path>iconName_dark.svg</path>    | W&times;H                   |
| Light + [HiDPI](#hidpi-version) | <path>iconName@2x.svg</path>      | 2&times;W &times; 2&times;H |
| Dark + [HiDPI](#hidpi-version)  | <path>iconName@2x_dark.svg</path> | 2&times;W &times; 2&times;H |

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

The New UI uses _outlined_ icons for tool windows that have a size of 20&times;20 pixels and 16&times;16 pixels in
[Compact Mode](https://www.jetbrains.com/help/idea/new-ui.html#compact-mode).
To provide all necessary variants of a tool window icon use the following filename scheme:

| Theme/Mode           | Filename                                   | Icon Size   |
|----------------------|--------------------------------------------|-------------|
| Light                | <path>toolWindowIcon@20x20.svg</path>      | 20&times;20 |
| Dark                 | <path>toolWindowIcon@20x20_dark.svg</path> | 20&times;20 |
| Light + Compact Mode | <path>toolWindowIcon.svg</path>            | 16&times;16 |
| Dark + Compact Mode  | <path>toolWindowIcon_dark.svg</path>       | 16&times;16 |

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

## Animated Icons

<tldr>

**UI Guidelines:** [](loader.md)

</tldr>

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

> Set [`AnimatedIcon.ANIMATION_IN_RENDERER_ALLOWED`](%gh-ic%/platform/ide-core/src/com/intellij/ui/AnimatedIcon.java) client property to `true` for list, table,
> and tree components to repaint animated icons automatically.
> See its Javadoc for details.

#### Progress Icon

Use the predefined `AnimatedIcon.Default` loader icon to indicate a long process.
This icon has a larger `AnimatedIcon.Big` version.

Alternatively, use [`AsyncProcessIcon`](%gh-ic%/platform/platform-api/src/com/intellij/util/ui/AsyncProcessIcon.java).

## Icon Tooltips

Register a resource bundle via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.iconDescriptionBundle"/></include> to provide tooltips automatically for all [`SimpleColoredComponent`](%gh-ic%/platform/platform-api/src/com/intellij/ui/SimpleColoredComponent.java) renderers.

Create `icon.<icon-path>.tooltip` key in the resource bundle, where `<icon-path>` is the icon path with leading slash and `.svg` removed and slashes replaced with dots:

<path>/nodes/class.svg</path> &rarr; `icon.nodes.class.tooltip`
