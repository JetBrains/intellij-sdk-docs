<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# 15. Structure Aware Navigation Bar

<link-summary>Sample implementation of structure-aware navigation bar displaying the breadcrumbs including the current file location and Simple language element.</link-summary>

<tldr>

**Reference**: [](navbar.md)

**Code**: [`SimpleStructureAwareNavbar`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureAwareNavbar.java)

</tldr>

<include from="language_and_filetype.md" element-id="custom_language_tutorial_header"></include>

Structure-aware navbar allows displaying specific file elements in the
[navigation bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#navigation-bar),
depending on the location of the caret in it.
For example, in Java this is used to display the class and method in which the caret is currently located.

## Define a Structure-Aware Navbar

The [`SimpleStructureAwareNavbar`](%gh-sdk-samples-master%/simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureAwareNavbar.java) implements
[`StructureAwareNavBarModelExtension`](%gh-ic%/platform/lang-impl/src/com/intellij/ide/navigationToolbar/StructureAwareNavBarModelExtension.kt).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureAwareNavbar.java" include-symbol="SimpleStructureAwareNavbar"}

## Register the Structure-Aware Navbar

The `SimpleStructureAwareNavbar` implementation is registered with the IntelliJ Platform in the plugin
configuration file using the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.navbar"/></include>.

```xml
<extensions defaultExtensionNs="com.intellij">
  <navbar implementation="org.intellij.sdk.language.SimpleStructureAwareNavbar"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle [`runIde`](creating_plugin_project.md#running-a-plugin-with-the-runide-gradle-task) task.

Open the <path>test.simple</path> file and position the caret on any property.
The navigation bar displays the name and icon of this property.

![Structure Aware Navbar](structure_aware_navbar.png)
