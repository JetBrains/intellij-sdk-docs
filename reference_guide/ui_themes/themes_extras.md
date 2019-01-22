---
title: Customizing UI Themes - Editor Schemes and Background Images
---

UI Themes can also provide custom color and font settings, as well as custom images for display in the IntelliJ IDEA application window.
 
## Adding a Custom Editor Scheme
IntelliJ IDEA users can set preferences to configure the colors and fonts used in the Editor. 
These custom color and font settings are called _color schemes_, and can be exported and shared.

Use the following procedure to create a color scheme for a UI Theme. 
If needed refer to the [Configuring Colors and Fonts](https://www.jetbrains.com/help/idea/configuring-colors-and-fonts.html) section of IntelliJ IDEA help:
* Create the desired custom color scheme using the IntelliJ IDEA preferences.
* Export the color scheme. Name the file for the custom scheme.
* Once exported, change the file extension from `icls` to `xml`. 

The next step is to add the color scheme to the UI Theme plugin project:
* Place the color scheme XML file in the `resources` folder of the UI Theme plugin project.
* Add a key-value pair to the UI Theme description file for the scheme. 
The `key` is always "editorScheme". The `value` is the name of the file.

The example below adds an editor scheme named "Lightning" to the _Theme Basics_ custom UI Theme:
```json
{
  "name": "Theme Basics",
  "dark": false,
  "author": "IntelliJ Platform SDK",
  "ui": {
  },
  "editorScheme": "/Lightning.xml"
}
```

## Adding a Custom Background Image
IntelliJ IDEA supports setting an image as a background in the application window. 
Users can do this manually in [Preferences](https://www.jetbrains.com/help/idea/setting-background-image.html).

UI Themes support specifying a background image as a key-value pair in the `"background": {}` section of a Theme description file:
* The `image` key uses the file name of the image as the value.
The background image should be in PNG format, and placed in the UI Theme plugin project's resources folder. 
* The `transparency` key uses a `value` of 1-100. 
A `value` of 100 is opaque.
* The `fill` key uses a value of `scale`, meaning to expand the image to fill the space as the window gets resized.
* The `anchor` key uses a value of `center`, meaning to locate the center of the image in the center of the window. 


The following example adds an image of the Austrian countryside to the _Theme Basics_
Theme description file:
```json
{
  "name": "Theme Basics",
  "dark": false,
  "author": "IntelliJ Platform SDK",
  "ui": {
  },
  "background": {
    "image": "/austria.png",
    "transparency": 10,
    "fill": "scale",
    "anchor": "center"
  }
}
```