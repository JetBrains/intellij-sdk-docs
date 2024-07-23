# Extensions

<!-- Copyright 2000-2024 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<link-summary>Extensions are the most common way of customizing functionality in the IDE.</link-summary>

_Extensions_ are the most common way for a plugin to extend the IntelliJ Platform's functionality in a way that is not as straightforward as adding an action to a menu or toolbar.

The following are some of the most common tasks accomplished using extensions:

* The `com.intellij.toolWindow` extension point allows plugins to add [tool windows](tool_windows.md) (panels displayed at the sides of the IDE user interface);
* The `com.intellij.applicationConfigurable` and `com.intellij.projectConfigurable` extension points allow plugins to add pages to the [Settings dialog](settings.md);
* [Custom language plugins](custom_language_support.md) use many extension points to extend various language support features in the IDE.

There are more than 1500 extension points available in the platform and the bundled plugins, allowing customizing different parts of the IDE behavior.

## Exploring Available Extensions

[](intellij_platform_extension_point_list.md) and [](intellij_community_plugins_extension_point_list.md) list all available extension points in IntelliJ Platform and from bundled plugins in IntelliJ IDEA.
Additionally, dedicated Extension Point and Listener Lists specific to IDEs are available under _Product Specific_.
Browse usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

Alternatively (or when using 3rd party extension points), all available extension points for the specified namespace (`defaultExtensionNs`) can be listed using auto-completion inside the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) block in <path>[plugin.xml](plugin_configuration_file.md)</path>.
Use <ui-path>View | Quick Documentation</ui-path> in the lookup list to access more information about the extension point and implementation (if applicable).
See [](explore_api.md) for more information and strategies.

## Declaring Extensions

> Auto-completion, Quick Documentation, and other code insight features are available on extension point tags and attributes in <path>plugin.xml</path>.

<procedure title="Declaring Extension">

1. Add an [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) element to your <path>plugin.xml</path> if it's not yet present there.
   Set the `defaultExtensionNs` attribute to one of the following values:
    * `com.intellij` if your plugin extends the IntelliJ Platform core functionality.
    * `{ID of a plugin}` if your plugin extends the functionality of another plugin (must configure [Plugin Dependencies](plugin_dependencies.md)).
2. Add a new child element to the `<extensions>` element.
   The child element's name must match the name of the extension point you want the extension to access.
3. Depending on the type of the extension point, do one of the following:
    * If the extension point was declared using the `interface` attribute, set the `implementation` attribute to the name of the class that implements the specified interface.
    * If the extension point was declared using the `beanClass` attribute, set all properties annotated with the [`@Attribute`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) and [`Tag`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Tag.java) annotations in the specified bean class.

   See the [](plugin_extension_points.md#declaring-extension-points) section for details.
4. Implement the extension API as required (see [](#implementing-extension)).

</procedure>

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

<procedure id="implementing-extension" title="Implementing Extension">

Please note the following important points:

- Extension implementation must be stateless. Use explicit [](plugin_services.md) for managing (runtime) data.
- Avoid any initialization in the constructor, see also notes for [Services](plugin_services.md#constructor).
- Do not perform any static initialization. Use inspection <control>Plugin DevKit | Code | Static initialization in extension point implementations</control> (2023.3).
- An extension implementation must not be registered as [Service](plugin_services.md) additionally. Use inspection <control>Plugin DevKit | Code | Extension registered as service/component</control> (2023.3).

When using [Kotlin](using_kotlin.md):

- Do not use `object` but `class` for implementation. [More details](using_kotlin.md#object-vs-class)
- Do not use `companion object` to avoid excessive classloading/initialization when the extension class is loaded.
  Use top-level declarations or objects instead. [More details](using_kotlin.md#companion-object-extensions)

</procedure>

### Extension Default Properties

`id`
: Unique ID. Consider prepending ID with the prefix related to the plugin name or ID to not clash with other plugins defining extensions with the same ID, e.g., `com.example.myplugin.myExtension`.

`order`
: Allows ordering all defined extensions using `first`, `last` or `before|after [id]` respectively.

`os`
: Allows restricting an extension to given OS, e.g., `os="windows"` registers the extension on Windows only

If an extension instance needs to "opt out" in certain scenarios, it can throw [`ExtensionNotApplicableException`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/ExtensionNotApplicableException.java) in its constructor.

### Extension Properties Code Insight

Several tooling features are available to help configure bean class extension points in <path>plugin.xml</path>.

Properties annotated with [`RequiredElement`](%gh-ic%/platform/core-api/src/com/intellij/openapi/extensions/RequiredElement.java) are inserted automatically and validated (2019.3 and later).
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

Similarly, `action`/`actionId` (2024.3+) resolves to all registered [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) IDs.

Annotating with [`@Nls`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/Nls.java) validates a UI `String` capitalization according to the text property `Capitalization` enum value (2019.2 and later).

Properties marked as `@Deprecated` or annotated with any of [`ApiStatus`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Internal`, `@Experimental`, `@ScheduledForRemoval`, or `@Obsolete` will be highlighted accordingly.

Attributes with `Enum` type support code insight with _lowerCamelCased_ notation (2020.1 and later). Note: these must not override `toString()`.
