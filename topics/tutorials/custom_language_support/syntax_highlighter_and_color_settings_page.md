<!-- Copyright 2000-2026 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

# 5. Syntax Highlighter and Color Settings Page

<link-summary>Sample implementation of a syntax highlighter and color settings page allowing to edit Simple language syntax element colors.</link-summary>

<tldr>

**Reference**: [](syntax_highlighting_and_error_highlighting.md)

**Code**: [`SimpleSyntaxHighlighter`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighter.java),
[`SimpleSyntaxHighlighterFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighterFactory.java),
[`SimpleColorSettingsPage`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleColorSettingsPage.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

The first level of syntax highlighting is based on the lexer output, and is provided by `SyntaxHighlighter`.
A plugin can also define color settings based on `ColorSettingPage` so the user can configure highlight colors.

## Define a Syntax Highlighter

The [`SimpleSyntaxHighlighter`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighter.java) class extends [`SyntaxHighlighterBase`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterBase.java).
As recommended in [Color Scheme Management](color_scheme_management.md#text-attribute-key-dependency), the Simple Language highlighting text attributes are specified as a dependency on one of standard IntelliJ Platform keys.
For the Simple Language, define only one scheme.

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighter.java" include-symbol="SimpleSyntaxHighlighter"}

### Define a Syntax Highlighter Factory

The factory provides a standard way for the IntelliJ Platform to instantiate the syntax highlighter for Simple Language files.
Here, [`SimpleSyntaxHighlighterFactory`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighterFactory.java)
subclasses [`SyntaxHighlighterFactory`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/fileTypes/SyntaxHighlighterFactory.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleSyntaxHighlighterFactory.java" include-symbol="SimpleSyntaxHighlighterFactory"}

### Register the Syntax Highlighter Factory

Register the factory with the IntelliJ Platform in the plugin configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.lang.syntaxHighlighterFactory"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <lang.syntaxHighlighterFactory
      language="Simple"
      implementationClass="org.intellij.sdk.language.SimpleSyntaxHighlighterFactory"/>
</extensions>
```

### Run the Project with Default Colors

Open the example Simple Language [properties file ](lexer_and_parser_definition.md#run-the-project) (`test.simple`) in the IDE Development Instance.
The colors for Simple Language Key, Separator, and Value highlighting default to the IDE _Language Defaults_ for Keyword, Braces, Operators, and Strings, respectively.

![Syntax highlighter](syntax_highlighter.png)

## Define a Color Settings Page

The color settings page adds the ability for users to customize color settings for the highlighting in Simple Language files.
The [`SimpleColorSettingsPage`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleColorSettingsPage.java)
implements [`ColorSettingsPage`](%gh-ic%/platform/platform-api/src/com/intellij/openapi/options/colors/ColorSettingsPage.java).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleColorSettingsPage.java" include-symbol="SimpleColorSettingsPage"}

It is supported to group related attributes like operators or braces by separating the nodes with `//`, e.g.:

```java
AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[] {
    new AttributesDescriptor("Operators//Plus", MySyntaxHighlighter.PLUS),
    new AttributesDescriptor("Operators//Minus", MySyntaxHighlighter.MINUS),
    new AttributesDescriptor("Operators//Advanced//Sigma", MySyntaxHighlighter.SIGMA),
    new AttributesDescriptor("Operators//Advanced//Pi", MySyntaxHighlighter.PI),
    //...
};
```

### Register the Color Settings Page

Register the Simple Language color settings page with the IntelliJ Platform in the plugin configuration file.
The extension point to use depends on the IntelliJ Platform version the plugin targets.

#### Registering with `com.intellij.colorSettings`
<primary-label ref="2026.3"/>

Starting with 2026.3, register the color settings page using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.colorSettings"/></include>.
The declaration states the name, the ID, and the order of the page.
This lets the IDE build the <ui-path>Settings | Editor | Color Scheme</ui-path> tree without loading the page class.
The IDE creates the `ColorSettingsPage` instance only when the user opens the page.

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettings
      implementation="org.intellij.sdk.language.SimpleColorSettingsPage"
      displayName="Simple"/>
</extensions>
```

For a localized page name, use the `key` and `bundle` attributes instead of `displayName`, for example:

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettings
      implementation="com.example.MyColorSettingsPage"
      bundle="messages.MyBundle"
      key="color.settings.my.language.name"/>
</extensions>
```

The page class still implements `ColorSettingsPage`.
The values in the declaration must match the values that the page class returns, because the IDE uses the declaration in the Settings tree and the class on the page itself.

The [`ColorSettingsPageEP`](%gh-ic-master%/platform/platform-impl/src/com/intellij/application/options/colors/ColorSettingsPageEP.java) bean supports the following attributes:

| Attribute          | Required                        | Description                                                                                                                                                                                                                                                                                                                                                                                                                                                               |
|--------------------|---------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `implementation`   | **yes**                         | FQN of the `ColorSettingsPage` implementation.                                                                                                                                                                                                                                                                                                                                                                                                                            |
| `displayName`      | **yes** [(1)](#attribute-notes) | <p>The non-localized page name. It must match `getDisplayName()`.</p><p>For a localized name, omit `displayName` and use `key` and `bundle`.</p>                                                                                                                                                                                                                                                                                                                          |
| `key` and `bundle` | **yes** [(1)](#attribute-notes) | <p>The [localization](internationalization.md#message-bundles) key and bundle for the page name. The resolved name must match `getDisplayName()`.</p><p>If `bundle` is omitted, the plugin's [`<resource-bundle>`](plugin_configuration_file.md#idea-plugin__resource-bundle) is used.</p>                                                                                                                                                                                |
| `pageId`           | no                              | <p>The ID of the page. The default is the `implementation` value, which is also what `getId()` returns by default.</p><p>Specify it only if the page class overrides `getId()`, and use the same value.</p><p>The common [`id`](plugin_configuration_file.md#idea-plugin__extensions__-) attribute of extensions does not set the page ID.</p>                                                                                                                            |
| `priority`         | no                              | <p>The group of the page in the Color Scheme tree, as a [`DisplayPriority`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/DisplayPriority.java) value, for example, `COMMON_SETTINGS`. The default is `LANGUAGE_SETTINGS`.</p><p>Specify it if the page class implements [`DisplayPrioritySortable`](%gh-ic%/platform/lang-api/src/com/intellij/psi/codeStyle/DisplayPrioritySortable.java), and use the value that `getPriority()` returns.</p>               |
| `keyLanguageIn`    | no                              | <p>A comma-separated list of [platform prefixes](%gh-ic%/platform/core-api/src/com/intellij/util/PlatformUtils.java) of the products in which the page belongs to the `KEY_LANGUAGE_SETTINGS` group, for example, `keyLanguageIn="WebStorm,PhpStorm"`. In all other products, the page uses the `priority` value.</p><p>Use it if `getPriority()` returns a different value depending on the product.</p>                                                                 |
| `groupWeight`      | no                              | <p>The order of the page within its `priority` group. Pages with a higher weight come first. Pages with equal weight are sorted by name. The default is 0.</p><p>Specify it if the page class implements `DisplayPrioritySortable.getWeight()`, and use the same value.</p>                                                                                                                                                                                               |
| `beta`             | no                              | `true` adds a <control>Beta</control> label next to the page name in the Settings tree.                                                                                                                                                                                                                                                                                                                                                                                   |

##### Attribute Notes

(1) Either `displayName` or `key` and `bundle` must be specified, depending on whether the page name is localized.
If the declaration states no name, the IDE reports an error.

#### Registering with `com.intellij.colorSettingsPage`

For versions earlier than 2026.3, register the color settings page using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.colorSettingsPage"/></include>.
The Simple Language sample plugin targets an earlier version, so it uses this extension point:

```xml
<extensions defaultExtensionNs="com.intellij">
  <colorSettingsPage
      implementation="org.intellij.sdk.language.SimpleColorSettingsPage"/>
</extensions>
```

The `com.intellij.colorSettingsPage` extension point is deprecated since 2026.3, but it still works.
The [Plugin DevKit](https://plugins.jetbrains.com/plugin/22851-plugin-devkit) plugin highlights its registrations as deprecated in plugins built against 2026.3 or later.
Keep using it only if the plugin also supports versions earlier than 2026.3:

- If the plugin's [`since-build`](plugin_configuration_file.md#idea-plugin__idea-version) is 263 or later, use only `com.intellij.colorSettings`.
- If a single plugin build also supports earlier versions, keep using `com.intellij.colorSettingsPage` until the lowest supported version is 2026.3.
- Plugins that publish a separate build for each IDE version can switch to `com.intellij.colorSettings` in the build for 2026.3 and later.

Declaring the same class with both extension points also works.
In 2026.3 and later, the `com.intellij.colorSettings` declaration takes precedence, and the IDE ignores the `com.intellij.colorSettingsPage` declaration of the same class.
Earlier versions ignore the unknown `com.intellij.colorSettings` declaration, but the plugin can then no longer be loaded or unloaded [without a restart](dynamic_plugins.md) in these versions.

### Run the Project

[Run](creating_plugin_project.md#running-the-plugin) the plugin.

In the IDE Development Instance, open the Simple Language highlight settings page: <ui-path>Settings | Editor | Color Scheme | Simple</ui-path>.
Each color initially inherits from a <control>Language Defaults</control> value.

![Color Settings Page](color_settings_page.png)
