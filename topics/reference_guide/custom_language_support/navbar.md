<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Navigation Bar

<link-summary>Implementing custom navigation bars.</link-summary>

<tldr>

**Product Help:** [Navigation bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#navigation-bar)

</tldr>

The navigation bar implementation is used to customize and extend the
[navigation bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#navigation-bar)
structure.

The starting point for the navigation bar extension is the
[`NavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/NavBarModelExtension.java)
interface, which is registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.navbar"/></include>.

To reuse the IntelliJ Platform implementation, you can extend one of two classes:

- [`DefaultNavBarExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/DefaultNavBarExtension.java)
- [`StructureAwareNavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/StructureAwareNavBarModelExtension.kt)

## Default Navigation Bar

`DefaultNavBarExtension` is the basic implementation of the navigation bar for any files.
Inherit from this class if you want to create a simple navigation bar where you can change the display of folders or files for your language.

In this case, you probably only need to override the following two methods:

- `getPresentableText()` – returns a string representation of the navigation bar part element passed to it.
- `getIcon()` – returns the icon for the navigation bar part passed to it.

## Structure Aware Navigation Bar

`StructureAwareNavBarModelExtension` is an advanced implementation that provides the ability to display specific file elements (e.g., the name of classes, functions, etc.) in a bar e.g.,
the name of the class at the current caret position.
Inherit from it if you want to add navigation bar support to your language with support for specific file elements.

> Don't forget to implement [](structure_view.md), this is necessary to build a file structure model based on which the navigation bar displays a specific element.
{style="note"}

In this case, you will also need to override the `getLanguage()` in addition to the two methods described earlier, this method returns the language instance for which this extension will work.

The `adjustElement()` method allows you to modify the navigation bar element.
It can be used, for example, when you want to show a class in the navigation bar when the caret is located in a comment that is attached to the class.

You probably won't need to override other methods unless you want to write your own implementation of the entire `NavBarModelExtension` interface.

Note that the `getSuitableClasses()` method on the structure view model class that implements `com.intellij.ide.structureView.TextEditorBasedStructureViewModel` (see [](structure_view.md))
must return all the element types you want to display in the navigation bar.

**Example:** [Custom Language Support Tutorial: Structure Aware Navigation Bar](structure_aware_navbar.md)
