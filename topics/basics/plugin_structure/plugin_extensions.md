<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Extensions

<web-summary>
Declaring extensions in plugins to customize the IDE's behavior and functionality using available extension points.
</web-summary>

<link-summary>Extensions are the most common way of customizing functionality in the IDE.</link-summary>

_Extensions_ are the most common way for a plugin to extend the IntelliJ Platform's functionality in a way that is not as straightforward as adding an action to a menu or toolbar.

The following are some of the most common tasks achieved using extensions:

* The <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.toolWindow"/></include> allows plugins to add [tool windows](tool_windows.md) (panels displayed at the sides of the IDE user interface);
* The <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.applicationConfigurable"/></include> and <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.projectConfigurable"/></include> allow plugins to add pages to the [Settings dialog](settings.md);
* [Custom language plugins](custom_language_support.md) use many extension points to extend various language support features in the IDE.

There are more than 1700 extension points available in the platform and the bundled plugins, allowing customizing different parts of the IDE behavior.

## Exploring Available Extensions

### Documentation

- [](intellij_platform_extension_point_list.md)
- [](intellij_community_plugins_extension_point_list.md) (bundled plugins in IntelliJ IDEA)
- [](oss_plugins_extension_point_list.md)

Lists for other IDEs are available under _Product Specific_ (for example, [PhpStorm](php_extension_point_list.md)).

### IntelliJ Platform Explorer

Browse usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

### Code Insight

Alternatively (or when using 3rd party extension points), all available extension points for the specified namespace (`defaultExtensionNs`) can be listed using auto-completion inside the [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) block in <path>[plugin.xml](plugin_configuration_file.md)</path>.
Use <ui-path>View | Quick Documentation</ui-path> in the lookup list to access more information about the extension point and implementation (if applicable).

See [](explore_api.md) for more information and strategies.

## Declaring Extensions

> Auto-completion, Quick Documentation, and other code insight features are available on extension point tags and attributes in <path>plugin.xml</path>.

<procedure title="Declaring Extension">

1. Add an [`<extensions>`](plugin_configuration_file.md#idea-plugin__extensions) element to <path>plugin.xml</path> if it's not yet present there.
   Set the `defaultExtensionNs` attribute to one of the following values:
    * `com.intellij` if the plugin extends the IntelliJ Platform core functionality.
    * `{ID of a plugin}` if the plugin extends the functionality of another plugin (must configure [plugin dependencies](plugin_dependencies.md)).
2. Add a new child element to the `<extensions>` element.
   The child element's name must match the name of the used [extension point](plugin_extension_points.md).
3. Depending on the type of the extension point, do one of the following:
    * If the extension point was declared using the `interface` attribute, set the `implementation` attribute to the name of the class that implements the specified interface.
    * If the extension point was declared using the `beanClass` attribute, set all properties annotated with the [`@Attribute`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) and [`Tag`](%gh-ic%/platform/util/src/com/intellij/util/xmlb/annotations/Tag.java) annotations in the specified bean class.

   See the [](plugin_extension_points.md#declaring-extension-points) section for details.
4. In addition to attributes defined by the extension point, the extension element can specify basic attributes (see the attributes list in [](plugin_configuration_file.md#idea-plugin__extensions__-) section).
5. Implement the extension API as required (see [](#implementing-extension)).

</procedure>

To clarify this procedure, consider the following sample section of the <path>plugin.xml</path> file that defines two extensions designed
to access the <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.appStarter"/></include> and <include from="snippets.topic" element-id="epLink"><var name="ep" value="com.intellij.projectTemplatesFactory"/></include>
extension points in the IntelliJ Platform,
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

- Extension implementation must be stateless. Use explicit [services](plugin_services.md) for managing (runtime) data.
- Avoid any initialization in the constructor, see also notes for [services](plugin_services.md#ctor).
- Do not perform any static initialization. Use inspection <control>Plugin DevKit | Code | Static initialization in extension point implementations</control> (2023.3).
- An extension implementation must not be registered as a [service](plugin_services.md) additionally. Use inspection <control>Plugin DevKit | Code | Extension registered as service/component</control> (2023.3).
- If an extension instance needs to "opt out" in certain scenarios, it can throw [`ExtensionNotApplicableException`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/ExtensionNotApplicableException.java) in its constructor.

When using [Kotlin](using_kotlin.md):

- Do not use `object` but `class` for implementation. [More details](using_kotlin.md#object-vs-class)
- Do not use `companion object` to avoid excessive classloading/initialization when the extension class is loaded.
  Use top-level declarations or objects instead. [More details](using_kotlin.md#companion-object-extensions)

</procedure>

### Extension Properties Code Insight

Several tooling features are available to help configure bean class extension points in <path>plugin.xml</path>.

#### Required Properties

Properties annotated with [`RequiredElement`](%gh-ic%/platform/core-api/src/com/intellij/openapi/extensions/RequiredElement.java) are inserted automatically and validated.

If the given property is allowed to have an explicit empty value, set `allowEmpty` to `true`.

#### Class names

Property names matching the following list will resolve to a fully qualified class name:

- `implementation`
- `className`
- ending with `Class` (case-sensitive)
- `serviceInterface`/`serviceImplementation`

A required parent type can be specified in the [extension point declaration](plugin_extension_points.md) via [`<with>`](plugin_configuration_file.md#idea-plugin__extensionPoints__extensionPoint__with):

```xml
<extensionPoint name="myExtension" beanClass="MyExtensionBean">
  <with
      attribute="psiElementClass"
      implements="com.intellij.psi.PsiElement"/>
</extensionPoint>
```

#### Custom resolve

Property name `language` (or ending in `*Language`) resolves to all present [`Language`](%gh-ic%/platform/core-api/src/com/intellij/lang/Language.java) IDs.

Similarly, `action` and `actionId` (2024.3+) resolve to all registered [`<action>`](plugin_configuration_file.md#idea-plugin__actions__action) IDs.

#### Deprecation/ApiStatus

Properties marked as `@Deprecated` or annotated with any of [`ApiStatus`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Internal`, `@Experimental`, `@ScheduledForRemoval`, or `@Obsolete` will be highlighted accordingly.

#### Enum properties

`Enum` attributes support code insight with _lowerCamelCased_ notation. Note: The `Enum` implementation must not override `toString()`.

#### I18n

Annotating with [`@Nls`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/Nls.java) validates a UI `String` capitalization according to the text property `Capitalization` enum value.
