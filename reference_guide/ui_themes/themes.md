---
title: Custom UI Themes
---

Custom UI Themes give designers control of the appearance of built-in IntelliJ IDEA UI elements. Custom 
UI Themes are distinct from [creating new UI elements](/user_interface_components/user_interface_components.md)
for a plugin. 

Creating a custom UI Theme is a process of choosing a base IntelliJ Theme (_Light_ or _Darcula_) 
then changing aspects of the base Theme definition. Custom UI Themes can:
* substitute icons,
* change the colors of icons and UI controls,
* alter the borders and insets of UI controls,
* provide custom editor schemes,
* add background images.

## The Structure of a UI Theme
UI Themes are components within IntelliJ Platform plugins. Themes can be stand-alone plugins 
or combined with other functionality in a plugin. 

UI Themes have several principal parts:
* A Theme description (JSON) file in the plugin project's source code folder. 
* A `themeProvider` declaration in the plugin's `plugin.xml` file.
* An optional Editor Scheme description (XML) file derived from an exported IntelliJ editor scheme.
This file is declared in the Theme description file and located in a plugin project's resources folder. 

(Screenshot image of project window highlighting each component)

## Custom UI Theme Workflow
Creating a UI Theme follows this general sequence:
* [Start with a Plugin Project](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/creating_plugin_project.html)
* [Add UI Theme components to the plugin](#creating-a-ui-theme-with-the-devkit-theme-wizard)
  by using the DevKit UI Theme Wizard.
* [Customize the UI Theme](themes_customize.md) by adding data objects to the Theme description (JSON) file.
* [Add an Editor Scheme or Background Image](themes_extras.md) to the plugin.
* [Build and test](/basics/getting_started/running_and_debugging_a_plugin.md) the UI Theme plugin.
* [Deploy the UI Theme plugin](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/deploying_plugin.html)
* [Publish the UI Theme plugin](https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html) 


## Creating Custom UI Themes

A UI Theme component is
added to a plugin using the DevKit UI Theme Wizard.

### Creating a UI Theme with the DevKit Theme Wizard
To add a UI Theme to a plugin use the DevKit Wizard. While a plugin project is 
open in IntelliJ, select the _**New | Plugin DevKit | Theme**_ action. This Wizard can
be used for both DevKit and Gradle-based plugins.

(Image of menu selecting plugin wizard.)

The Wizard then prompts for the name of the new Theme, and the basis for the Theme:

(Image of Create New Theme dialog box)

The name of the new Theme does not have to match the name of the plugin. The checkbox 
indicates the basis for the Theme. Checking _Dark theme_ means basing the custom Theme on
_Darcula_. Leaving the box unchecked means basing the custom Theme on the default IntelliJ _Light_ Theme. 

Clicking the _OK_ button creates a default Theme description file 
with the name `[themeName].theme.json` in the plugin project's `src` directory. 
In this example, the file is named `theme_basics.theme.json`. 
The content of the default file is a short set of key–value pairs:  
```json
{
 "name": "theme_basics",
 "dark": false,
 "author": "",
 "ui": {
 }
}
```

The `value` of the `name` key defaults to match the name of the first portion 
of the Theme description file. The `value` of `name` is displayed in the 
[Theme](https://www.jetbrains.com/help/idea/settings-appearance.html)  _Preferences_ dropdown 
when the UI Theme's plugin is installed in IntelliJ. The value of the `author` key is 
by default empty. The `ui` section will be addressed in 
[Customizing UI Control Colors](themes_customize.md#Customizing-UI-Control-Colors). 

The Wizard also creates a `themeProvider` declaration in the `<extensions>` 
section of the plugin's `plugin.xml` file. This declaration binds the 
Theme description file to a theme provider extension `id`.
```xml
  <extensions defaultExtensionNs="com.intellij">
    <themeProvider id="eb9b7461-397b-4b98-a422-224fc0a74564" path="/theme_basics.theme.json"/>
  </extensions>
```

At this point, the UI Theme `theme_basics` is a valid UI Theme. Its plugin can 
be built and tested in IntelliJ, giving the user the opportunity to select _theme_basics_
in the [Theme](https://www.jetbrains.com/help/idea/settings-appearance.html) _Preferences_ 
dropdown. At this point the custom Theme looks exactly like the 
IntelliJ default `Light` Theme, but it is a valid Custom Theme.

### Completing the Default UI Theme Description File
The default UI Theme definition can be directly edited to add or change some of the values
for the default keys, if desired:
* The following values can be changed directly in the Theme (`*.theme.json`) description file:
  * The value of the `name` key can be independent of the name of the UI Theme (`*.theme.json`)
    description file. In the case of this code sample, it is `Theme Basics`.
  * The value of the `dark` key can be changed to `true`, which would switch the 
    basis of the Theme to _Darcula_ instead of _Light_.
  * The value of the `author` key, which defaults to an empty string, can be 
    set to a `String` literal. In the case of this example it is set to "IntelliJ Platform SDK".
* The name of the Theme description file, e.g. `theme_basics.theme.json` can be changed, 
  as long as the `path` key of the `<themeProvider>` element (in `plugin.xml`) 
  matches the new file name.



<br>
<br>

**TODO:**
* capture images for page.
* Document additional syntax from High Contrast theme json file?

**TO RESOLVE:**
* Manual search methodology to find UI control keys in the codebase (given what's known from Laf tool)
* URLs for Light, Darcula, and High Contrast themes in the IntelliJ-community codebase on Github.
* CAN UI Themes be delivered with other functionality?
* How is the Theme GUID assigned by the DevKit Wiz? Could a theme author create the theme.json file and manually add the plugin.xml themeProvider element?
* Does this work with Gradle plugins?
* Can one plugin deliver multiple themes?


<br>
<br>

**INCLUDE OR DISCARD?**

## Productivity Tips
Before discussing UI Theme customization of UI Themes, here are some suggestions to help speed
the development and testing of a first UI Theme.
 
### Development
Begin simply. Add detail and complexity in small, individually tested steps:
* Get started with a plugin dedicated to a UI Theme without other plugin functionality.
* Begin by customizing colors; these are the easiest to test.
* Then add complexity such as icon substitution, an editor scheme, or a background image. Add these last
  because they introduce requirements for synchronizing filenames, formats, and file locations.

### Testing
Test frequently as you customize a UI Theme. Take advantage of testing within the editor
as well as testing the plugin.

#### UI Theme Description File Syntax Checking in Editor
There are several ways to verify that the UI Theme description file has valid syntax while making changes:
* A check mark is displayed in the upper right corner of the editor window. If
a green check mark turns to red, then there is a syntax error. Often it can be as little as
a misplaced comma.
* The green _run arrow icon_ in the margin to the left of the `name` key will 
change IntelliJ to use the UI Theme being developed in the editor. However, changing the
UI Theme back to a default requires resetting the _Appearance_ preference.

#### Test the Plugin
Testing the plugin containing a custom UI screen
* When IntelliJ completes starting-up, verify the custom Theme is available
via the preferences.
* Select the custom Theme in preferences and verify the Theme is applied.

