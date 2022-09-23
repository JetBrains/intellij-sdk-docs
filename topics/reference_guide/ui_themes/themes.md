[//]: # (title: Creating Custom UI Themes)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

## The Structure of a UI Theme

UI Themes are components within IntelliJ Platform plugins.
The theme plugins should be stand-alone and not combined with other plugin functionality.
This approach provides the best user experience because it avoids an IDE restart when installing a UI Theme plugin.

UI Themes have several components:
* A required Theme description (JSON) file in the plugin project's <path>resources</path> folder.
* A required `themeProvider` declaration in the plugin's <path>[plugin.xml](plugin_configuration_file.md)</path> file, located in the plugin project's <path>META-INF</path> folder.
* An optional Editor Scheme description (XML) file derived from an exported IDE editor scheme.
  This file is located in the plugin project's <path>resources</path> folder.
* An optional background image file, located in the plugin project's <path>resources</path> folder.
* Optional icon image files, located in the plugin project's <path>resources</path> folder.

![UI Theme Components](theme_components.png)

[//]: # (FIXME)

### Theme Description File Content

The content of the default file is a short set of key–value pairs:

```json
{
  "name": "theme_basics",
  "dark": false,
  "author": "",
  "editorScheme": "/theme_basics.xml",
  "ui": {
  }
}
```

The value of the `name` key matches the first portion of the Theme description <path>$THEME_NAME$.theme.json</path> file name.
The value of `name` is displayed in the [Theme](https://www.jetbrains.com/help/idea/settings-appearance.html)  _Preferences_ dropdown when the UI Theme's plugin is installed in the IDE.
The value of the `author` key is by default empty.
The `editorScheme` section will be addressed in [Adding a Custom Editor Scheme](themes_extras.md#adding-a-custom-editor-scheme)
The `ui` section will be addressed in [Customizing UI Control Colors](themes_customize.md#customizing-ui-controls).

The Wizard also creates a `themeProvider` declaration in the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) section of the plugin's <path>plugin.xml</path> file.
This declaration binds the Theme description file to a theme provider extension using a generated unique `id`.

```xml
<extensions defaultExtensionNs="com.intellij">
  <themeProvider
      id="eb9b7461-397b-4b98-a422-224fc0a74564"
      path="/theme_basics.theme.json"/>
</extensions>
```

> Do not modify or re-use an existing value of the generated `id` attribute.
>
{type="warning"}

At this point, the UI Theme `theme_basics` is a valid UI Theme.
Its plugin can be built and tested in IntelliJ Platform-based IDEs, giving the user the opportunity to select _theme_basics_ in the [Theme](https://www.jetbrains.com/help/idea/settings-appearance.html) _Preferences_ dropdown.
The custom Theme looks exactly like the IDE default `Light` Theme, but it is a valid custom Theme.

### Completing the Default UI Theme Description File

The default UI Theme definition can be directly edited to add or change some of the values for the default keys, if desired:
The following values can be changed directly in the Theme (<path>*.theme.json</path>) description file:
* The value of the `dark` key can be changed to `true`, which would switch the basis of the Theme to _Darcula_ instead of _Light_.
* The value of the `author` key, which defaults to an empty string, can be set to a `String` literal.

In the case of the `theme_basics` code sample, it is set to "IntelliJ Platform SDK".
