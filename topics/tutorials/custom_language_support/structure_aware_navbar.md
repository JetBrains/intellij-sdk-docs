[//]: # ("title: 15. Structure Aware Navigation Bar")

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<include src="language_and_filetype.md" include-id="custom_language_tutorial_header"></include>

Structure aware navbar allows displaying specific file elements in the
[navigation bar](https://www.jetbrains.com/help/idea/guided-tour-around-the-user-interface.html#navigation-bar),
depending on the location of the caret in it.
For example, in Java this is used to display the class and method in which the caret is currently located.

**Reference**: [](navbar.md)

## Define a SimpleStructureAwareNavbar

The simple structure aware navbar implements
[`StructureAwareNavBarModelExtension`](upsource:///platform/lang-impl/src/com/intellij/ide/navigationToolbar/StructureAwareNavBarModelExtension.kt).

```java
```
{src="simple_language_plugin/src/main/java/org/intellij/sdk/language/SimpleStructureAwareNavbar.java"}

In the `adjustElement()` method, if the current caret is located in a comment, then we check if this comment is located just before a property and then return this property.
This way, when the user selects a comment, the property name will be displayed in the navigation bar.

## Register the SimpleStructureAwareNavbar

The `SimpleStructureAwareNavbar` implementation is registered with the IntelliJ Platform in the plugin
configuration file using the `com.intellij.navbar` extension point.

```xml
<extensions defaultExtensionNs="com.intellij">
  <navbar implementation="org.intellij.sdk.language.SimpleStructureAwareNavbar"/>
</extensions>
```

## Run the Project

Run the project by using the Gradle
[runIde task](https://plugins.jetbrains.com/docs/intellij/gradle-prerequisites.html#running-a-simple-gradle-based-intellij-platform-plugin).

Open the <path>test.simple</path> file and position the caret on any property.
The navigation bar displays the name and icon of this property.

![Structure Aware Navbar](structure_aware_navbar.png)
