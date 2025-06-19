<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Exposing Theme Metadata

<link-summary>Exposing plugin's UI components' customization keys allowing theme developers to change your components look.</link-summary>

All available UI Customization Keys that can be used in [Custom Themes](themes_customize.md) must be defined in a dedicated <path>*.themeMetadata.json</path> file which is registered via <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.themeMetadataProvider"/></include>.

The following minimal sample demonstrates all details required when exposing UI customization keys of your plugin's UI.

<path>/resources/META-INF/plugin.xml</path>:

```xml
<idea-plugin>
  <extensions defaultExtensionNs="com.intellij">
    <themeMetadataProvider path="/META-INF/MyPlugin.themeMetadata.json"/>
  </extensions>
</idea-plugin>
```

<path>/resources/META-INF/MyPlugin.themeMetadata.json</path>:

```json
{
  "name": "My Plugin",
  "fixed": false,
  "ui": [
    {
      "key": "MyComponent.border",
      "description": "The border for my component. Not used anymore.",
      "deprecated": true,
      "source": "com.example.MyComponent"
    },
    {
      [more keys...]
    }
  ]
}
```

### Attributes

* `name` - Human-readable name, e.g., plugin name
* `fixed` - Specifies whether metadata describes external elements, e.g., an UI library. Default:&nbsp;`false`.
* `ui` - Root element listing all customization keys:
  * `key` - Customization key name (see [Key Naming Scheme](#key-naming-scheme))
  * `description` - Description to be shown to Theme authors editing <path>*.theme.json</path> files
  * `deprecated` - `true` when the key is deprecated. It is highly recommended to provide explanation and/or replacement in `description` if available.
  * `source` - Fully qualified name of the underlying UI component implementation, e.g.,&nbsp;`javax.swing.JPasswordField`
  * `since` - The release number when this UI customization key was exposed, e.g.,&nbsp;`2021.1`

> It is highly recommended to always provide a `description` entry, so Theme authors can understand usages.
>

> Do not remove existing keys, but deprecate them instead to help Theme authors upgrade their existing themes.
>

Color keys can be used via [`JBColor.namedColor()`](%gh-ic%/platform/util/ui/src/com/intellij/ui/JBColor.java) providing defaults for Light and Dark theme:

```java
private static final JBColor SECTION_HEADER_FOREGROUND =
    JBColor.namedColor(
      "Plugins.SectionHeader.foreground",
      new JBColor(0x787878,0x999999)
    );
```

Other keys can be obtained via `javax.swing.UIManager#getXXX()` methods.

## Key Naming Scheme

All keys must follow this Naming Pattern:

**`Object[.SubObject].[state][Part]Property`**

![Key Naming Pattern](keys-naming.png){width="735"}

#### Property

| Word              | Use for                                                                                                                                                                                                                                                                        | Example                                                 |
|-------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------------------------------|
| **`foreground`**  | Text color.                                                                                                                                                                                                                                                                    | `Label.foreground`                                      |
| **`background`**  | Background color for objects with text.                                                                                                                                                                                                                                        | `Label.background`                                      |
| **`<part>Color`** | <p>Objects with a single color (do not have foreground/background). Do not use the word "Color" separately, always use with the "part" word. </p><p>_The word "Color" shows that this is a color property. Otherwise, it can be confused with a property of another type._</p> | <p>`Popup.borderColor`</p><p>`Group.separatorColor`</p> |

#### State

| Word                                                       | Use for                                                                                                                                                                | Example                                                                                 |
|------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------|
| ~~**`Active`**~~                                           | Enabled components, default state. Omit this word. The default state does not need explicit naming.                                                                    | `Notification.background`                                                               |
| **`Inactive`**                                             | Enabled components that might be perceived as interactive but are actually not. Example: a tree with visible selection but not in focus. Goes after other state words. | <p>`Tree.inactiveBackground`</p><p>`ToolWindow.HeaderTab.hoverInactiveBackground`</p>   |
| **`Focused`**                                              | The current focused component.                                                                                                                                         | `Button.focusedBorderColor`                                                             |
| **`Selected`**                                             | A selected tab or any other control that has equally meaningful selected and inactive states.                                                                          | `ToolWindow.HeaderTab.selectedBackground`                                               |
| <p>**`Hover`**</p><p>**`Pressed`**</p>                     | An action as indicated in states.                                                                                                                                      | <p>`Link.hoverForeground`</p><p>`Link.pressedForeground`</p>                            |
| <p>**`Error`**</p><p>**`Warning`**</p><p>**`Success`**</p> | Validation states. [See example](validation_errors.md) in UI Guidelines.                                                                                               | <p>`ValidationTooltip.errorBackground`</p><p>`ValidationTooltip.warningBorderColor`</p> |
| **`Disabled`**                                             | Unavailable components.                                                                                                                                                | `Label.disabledForeground`                                                              |

#### Part

A part is an internal element of a component, e.g., an arrow button in a combo box.
Create a separate key for a part if its properties differ from the parent object.

If a part is common among several components, use the same name for it.
Notable examples of common parts:

| Common parts                                  | Use for                                                                                                                                                                | Example                                                                                                                            |
|-----------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| <p>**`Accelerator`**</p><p>**`Shortcut`**</p> | Shortcut foreground.                                                                                                                                                   | <p>`Menu.acceleratorForeground`</p><p>`Editor.shortcutForeground`</p>                                                              |
| **`Border`**                                  | A line around a component.                                                                                                                                             | `NavBar.borderColor`                                                                                                               |
| **`Caret`**                                   | The vertical line that denotes typing place.                                                                                                                           | `TextField.caretForeground`                                                                                                        |
| **`ModifiedItem`**                            | <p>An object that has been modified but not yet saved.</p><p>_Example: change anything in the Settings dialog, the setting group name in the tree becomes blue._</p>   | `Tree.modifiedItemForeground`                                                                                                      |
| **`Focus`**                                   | Wide focus border around a component.                                                                                                                                  | <p>`Component.focusColor`</p><p>_"Component" is a special key that sets common properties for several basic input components._</p> |
| **`Info`**                                    | Secondary labels with additional useful information. Usually appear in gray color to the right or below a regular label.                                               | `CompletionPopup.infoForeground`                                                                                                   |
| **`Icon`**                                    | An icon that is created with a source code (not an image file).                                                                                                        | `Table.sortIconColor`                                                                                                              |
| **`Selection`**                               | <p>The focus place in a component with selectable text. Can be in a typed text or in a list or tree.</p><p>Goes before other state words (for historical reasons).</p> | <p>`TextField.selectionForeground`</p><p>`Tree.selectionInactiveBackground`</p>                                                    |
| **`Separator`**                               | A horizontal or vertical line inside a component. Can be with a label.                                                                                                 | `Menu.separatorColor`                                                                                                              |
| **`Shadow`**                                  | A shadow below a component.                                                                                                                                            | `Button.shadowColor`                                                                                                               |

#### SubObject

Use a subobject when creating keys for one of the following:
* An implementation variation. Usually has a similar set of UI property keys as the parent object. Examples:
  * Default button: `Button.Default.background`
  * Tool window notification: `Notification.ToolWindow.errorBackground`
* An internal smaller component of a complex component with its own UI and behavior. Examples:
  * Tool window tab: `ToolWindow.HeaderTab.inactiveBackground`
  * The hint text at the bottom of a popup: `Popup.Advertiser.background`

#### Gradient Color

If a component has a gradient color, add the words "start" and "end" for the beginning and ending of a gradient. Examples:
* `Button.startBorderColor` / `Button.endBorderColor`
* `SearchMatch.startBackground` / `SearchMatch.endBackground`

#### Capitalization

Capitalize Object and SubObject. Use lowerCamelCase for property.

#### Do Not Use

| Do not use                                | Use instead   |
|-------------------------------------------|---------------|
| `Color` _as a separate word_              | `<Part>Color` |
| `Outline`                                 | `borderColor` |
| `Text`                                    | `Foreground`  |
| `darcula` _and other look-and-feel names_ | _Omit_        |

#### Swing Legacy

Some color keys are not named according to the rules above.
Such keys are inherited from Java Swing and cannot be renamed for compatibility reasons.
Do not use naming patterns from the legacy keys.

Examples of Swing keys:
* `activeCaption`  Correct: `WindowsDialogHeader.background`
* `Button.disabledText` Correct: `Button.disabledForeground`
* `TableHeader.background` Correct: `Table.Header.background`

## IntelliJ Platform Metadata

> This section is relevant for IntelliJ Platform developers only.
>
{style="note"}

Metadata is split up as follows:
* [`IntelliJPlatform.themeMetadata.json`](%gh-ic%/platform/platform-resources/src/themes/metadata/IntelliJPlatform.themeMetadata.json) - all keys from IntelliJ Platform and custom UI components
* [`JDK.themeMetadata.json`](%gh-ic%/platform/platform-resources/src/themes/metadata/JDK.themeMetadata.json) - all keys from Swing components

New keys should be added to <path>IntelliJPlatform.themeMetadata.json</path> only (or corresponding "local" <path>*.themeMetadata.json</path> file of the plugin if applicable).

Please make sure to add a `description` and use `since` and `deprecated` attributes explained in [Attributes](#attributes).
Respect [Key Naming Scheme](#key-naming-scheme) and keep alphabetical ordering of keys.
