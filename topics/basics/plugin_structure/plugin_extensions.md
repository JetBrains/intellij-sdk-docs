[//]: # (title: Extensions)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt>Extensions are the most common way of customizing functionality in the IDE.</excerpt>

_Extensions_ are the most common way for a plugin to extend the IntelliJ Platform's functionality in a way that is not as straightforward as adding an action to a menu or toolbar.

The following are some of the most common tasks accomplished using extensions:

* The `com.intellij.toolWindow` extension point allows plugins to add [tool windows](tool_windows.md) (panels displayed at the sides of the IDE user interface);
* The `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` extension points allow plugins to add pages to the [Settings/Preferences dialog](settings.md);
* [Custom language plugins](custom_language_support.md) use many extension points to extend various language support features in the IDE.

There are more than 1000 extension points available in the platform and the bundled plugins, allowing customizing different parts of the IDE behavior.

## Exploring Available Extensions

[](extension_point_list.md) lists all available extension points in IntelliJ Platform and from bundled plugins in IntelliJ IDEA.
Additionally, dedicated Extension Point and Listener Lists specific to IDEs are available under _Part VIII — Product Specific_.
Browse usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

Alternatively (or when using 3rd party extension points), all available extension points for the specified namespace (`defaultExtensionNs`) can be listed using auto-completion inside the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) block in <path>[plugin.xml](plugin_configuration_file.md)</path>.
Use <menupath>View | Quick Documentation</menupath> in the lookup list to access more information about the extension point and implementation (if applicable).
See [](explore_api.md) for more information and strategies.

## Declaring Extensions

> Auto-completion, Quick Documentation, and other code insight features are available on extension point tags and attributes.
>
{type="tip"}

1. Add an [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) element to your <path>plugin.xml</path> if it's not yet present there.
   Set the `defaultExtensionNs` attribute to one of the following values:
   * `com.intellij`, if your plugin extends the IntelliJ Platform core functionality.
   * `{ID of a plugin}`, if your plugin extends the functionality of another plugin (must configure [Plugin Dependencies](plugin_dependencies.md)).
2. Add a new child element to the `<extensions>` element.
   The child element's name must match the name of the extension point you want the extension to access.
3. Depending on the type of the extension point, do one of the following:
   * If the extension point was declared using the `interface` attribute, set the `implementation` attribute to the name of the class that implements the specified interface.
   * If the extension point was declared using the `beanClass` attribute, set all properties annotated with the [`@Attribute`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) and [`Tag`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Tag.java) annotations in the specified bean class.

   See the [](plugin_extension_points.md#declaring-extension-points) section for details.

   **NB:** When using Kotlin, use `class` and never `object` for all implementations ([more details](kotlin.md#caution)).

To clarify this procedure, consider the following sample section of the <path>plugin.xml</path> file that defines two extensions designed to access the `com.intellij.appStarter` and `com.intellij.projectTemplatesFactory` extension points in the IntelliJ Platform,
and one extension to access the `another.plugin.myExtensionPoint` extension point in another plugin `another.plugin`:

```xml
<!--
  Declare extensions to access extension points in the IntelliJ Platform.
  These extension points have been declared using "interface".
 -->
<extensions defaultExtensionNs="com.intellij">
  <appStarter
      implementation="com.example.MyAppStarter"/>
  <projectTemplatesFactory
      implementation="com.example.MyProjectTemplatesFactory"/>
</extensions>

<!--
  Declare extensions to access extension points in a custom plugin "another.plugin".
  The "myExtensionPoint" extension point has been declared using "beanClass"
  and exposes custom properties "key" and "implementationClass".
-->
<extensions defaultExtensionNs="another.plugin">
  <myExtensionPoint
      key="keyValue"
      implementationClass="com.example.MyExtensionPointImpl"/>
</extensions>
```

### Extension Default Properties

The following properties are available always:

* `id` - unique ID. Consider prepending ID with the prefix related to the plugin name or ID to not clash with other plugins defining extensions with the same ID, e.g., `com.example.myplugin.myExtension`.
* `order` - allows ordering all defined extensions using `first`, `last` or `before|after [id]` respectively
* `os` - allows restricting an extension to given OS, e.g., `os="windows"` registers the extension on Windows only

If an extension instance needs to "opt out" in certain scenarios, it can throw [`ExtensionNotApplicableException`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/ExtensionNotApplicableException.java) in its constructor.

### Extension Properties Code Insight

Several tooling features are available to help configure bean class extension points in <path>plugin.xml</path>.

Properties annotated with [`@RequiredElement`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/RequiredElement.java) are inserted automatically and validated (2019.3 and later).
If the given property is allowed to have an explicit empty value, set `allowEmpty` to `true` (2020.3 and later).

Property names matching the following list will resolve to fully qualified class name:
- `implementation`
- `className`
- `serviceInterface` / `serviceImplementation`
- ending with `Class` (case-sensitive)

A required parent type can be specified in the extension point declaration via nested [`<with>`](plugin_configuration_file.md#idea-plugin__extensionPoints__extensionPoint__with):

```xml
<extensionPoint name="myExtension" beanClass="MyExtensionBean">
  <with
      attribute="psiElementClass"
      implements="com.intellij.psi.PsiElement"/>
</extensionPoint>
```

Property name `language` (or ending in `*Language`, 2020.2+) resolves to all present `Language` IDs.

Similarly, `action` resolves to all registered [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) IDs.

Specifying `@org.jetbrains.annotations.Nls` validates a UI `String` capitalization according to the text property `Capitalization` enum value (2019.2 and later).

Attributes with `Enum` type support code insight with _lowerCamelCased_ notation (2020.1 and later).
