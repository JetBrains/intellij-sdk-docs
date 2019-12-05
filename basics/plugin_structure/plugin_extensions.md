---
title: Plugin Extensions
redirect_from:
    /basics/plugin_structure/plugin_extensions_and_extension_points.html
---

_Extensions_ are the most common way for a plugin to extend the functionality of the IntelliJ Platform in a way
that is not as straightforward as adding an action to a menu or toolbar. The following are some of the most common
tasks accomplished using extensions:

  * The `com.intellij.toolWindow` extension point allows plugins to add [tool windows](/user_interface_components/tool_windows.md) 
  (panels displayed at the sides of the IDE user interface);
  * The `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` extension points allow plugins to add pages to the
    Settings/Preferences dialog;
  * [Custom language plugins](/reference_guide/custom_language_support.md) use many extension points
    to extend various language support features in the IDE.

There are [more than 1000 extension](#how-to-get-the-extension-points-list) points available in the platform and the bundled plugins, allowing to customize 
different parts of the IDE behavior.

## Declaring Extensions 

> **TIP** Auto-completion is available for all these steps.

1. Add an `<extensions>` element to your plugin.xml if it's not yet present there. Set the `defaultExtensionNs` attribute to one of the following values:
    * `com.intellij`, if your plugin extends the IntelliJ Platform core functionality.
    * `{ID of a plugin}`, if your plugin extends a functionality of another plugin.
2. Add a new child element to the `<extensions>` element. The child element name must match the name of the extension point you want the extension to access.
3. Depending on the type of the extension point, do one of the following:
    * If the extension point was declared using the `interface` attribute, for newly added child element, set the `implementation` attribute to the name of the class that implements the specified interface.
    * If the extension point was declared using the `beanClass` attribute, for newly added child element, set all attributes annotated with the [`@Attribute`](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) annotations in the specified bean class.


To clarify this procedure, consider the following sample section of the `plugin.xml` file that defines two extensions designed to access the `com.intellij.appStarter` and `com.intellij.projectTemplatesFactory` extension points in the *IntelliJ Platform* and one extension to access the `another.plugin.myExtensionPoint` extension point in another plugin `another.plugin`:

```xml
<!-- Declare extensions to access extension points in the IntelliJ Platform.
     These extension points have been declared using "interface".
 -->
  <extensions defaultExtensionNs="com.intellij">
    <appStarter implementation="com.myplugin.MyAppStarter" />
    <projectTemplatesFactory implementation="com.myplugin.MyProjectTemplatesFactory" />
  </extensions>

<!-- Declare extensions to access extension points in a custom plugin "another.plugin"
     The "myExtensionPoint" extension point has been declared using "beanClass" 
     and exposes custom properties "key" and "implementationClass".
-->
  <extensions defaultExtensionNs="another.plugin">
     <myExtensionPoint key="keyValue" implementationClass="com.myplugin.MyExtensionPointImpl" />
  </extensions>
```
### Extension default properties
The following properties are available always:

- `id` - unique ID
- `order` - allows to order all defined extensions using `first`, `last` or `before|after [id]` respectively
- `os` - allows to restrict extension to given OS, e.g., `os="windows"` registers the extension on Windows only 


### Extension properties code insight
Several tooling features are available to help configuring bean class extension points in `plugin.xml`.

Property names matching the following list will resolve to FQN:
- `implementation`
- `className`
- `serviceInterface` / `serviceImplementation`
- ending with `Class` (case-sensitive)

A required parent type can be specified in the extension point declaration via nested `<with>`:
```xml
    <extensionPoint name="myExtension" beanClass="MyExtensionBean">
      <with attribute="psiElementClass" implements="com.intellij.psi.PsiElement"/>
    </extensionPoint>
```

Property name `language` will automatically resolve to all present `Language` IDs.

Specifying `@org.jetbrains.annotations.Nls` verifies capitalization of UI text properties according to given `capitalization` value (2019.2 and later).

## How to get the extension points list?

To get a list of extension points available in the *IntelliJ Platform* core, consult the `<extensionPoints>` section of the following XML configuration files:

* [`LangExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/LangExtensionPoints.xml)
* [`PlatformExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)
* [`VcsExtensionPoints.xml`](upsource:///platform/platform-resources/src/META-INF/VcsExtensionPoints.xml)

