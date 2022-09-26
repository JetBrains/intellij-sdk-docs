[//]: # (title: Theme Structure)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

UI Themes are components within [IntelliJ Platform plugins](plugin_structure.md).
The theme plugins should be stand-alone and not combined with other plugin functionality.

UI Themes have several components:
* A required Theme description (JSON) file in the plugin project's <path>resources</path> folder.
* A required `themeProvider` declaration in the plugin's <path>[plugin.xml](plugin_configuration_file.md)</path> file, located in the plugin project's <path>META-INF</path> folder.
* An optional Editor Scheme description (XML) file derived from an exported IDE editor scheme.
  This file is located in the plugin project's <path>resources</path> folder.
* An optional background image file, located in the plugin project's <path>resources</path> folder.
* Optional icon image files, located in the plugin project's <path>resources</path> folder.

![UI Theme Components](theme_components.png)

## Theme Description File

The content of the default file generated with the [Theme wizard](creating_plugin_project.md) is a short set of key–value pairs:

```json
{
  "name": "theme_basics",
  "author": "",
  "dark": false,
  "editorScheme": "/theme_basics.xml",
  "ui": {
  }
}
```

* `name` key matches the first portion of the Theme description <path>$THEME_NAME$.theme.json</path> file name. The value of `name` is displayed in the [Theme](https://www.jetbrains.com/help/idea/settings-appearance.html) <control>Preferences</control> dropdown when the UI Theme's plugin is installed in the IDE.
* `author` - specifies the theme author (empty by default).
* `dark` - determines the base theme (_Light_ or _Darcula_) that is customized.
* `editorScheme` - specifies the editor scheme file that describes fonts and colors used in editors (see [](themes_extras.md#adding-a-custom-editor-scheme) for more details).
* `ui` - allows for overriding the base theme (_Light_ or _Darcula_) properties (see [](themes_customize.md#custom-ui-control-colors) for more details).

The wizard also creates a `themeProvider` declaration in the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) section of the plugin's <path>plugin.xml</path> file.
This declaration binds the theme description file to a theme provider extension using a generated unique `id`.

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

### Completing the Default UI Theme Description File

The default UI Theme definition can be directly edited to add or change some of the values for the default keys, if desired:
The following values can be changed directly in the Theme (<path>*.theme.json</path>) description file:
* The value of the `dark` key can be changed to `true`, which would switch the basis of the Theme to _Darcula_ instead of _Light_.
* The value of the `author` key, which defaults to an empty string, can be set to a `String` literal.

In the case of the `theme_basics` code sample, it is set to "IntelliJ Platform SDK".
