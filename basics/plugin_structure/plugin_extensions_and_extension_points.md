---
title: Plugin Extensions and Extension Points
---

The *IntelliJ Platform* provides the concept of _extensions_ and _extension points_ that allows a plugin to interact with other plugins or with the IDE itself.

## Extension points

If you want your plugin to allow other plugins to extend its functionality, in the plugin, you must declare one or several _extension points_.  Each extension point defines a class or an interface that is allowed to access this point.

## Extensions

If you want your plugin to extend the functionality of other plugins or the *IntelliJ Platform*, you must declare one or several _extensions_.

## How to declare extensions and extension points

You can declare extensions and extension points in the plugin configuration file `plugin.xml`, within the `<extensions>` and `<extensionPoints>` sections, respectively.

**To declare an extension point**

In the `<extensionPoints>` section, insert a child element `<extensionPoint>` that defines the extension point name and the name of a bean class or an interface that is allowed to extend the plugin functionality in the `name`, `beanClass` and `interface` attributes, respectively.

To clarify this procedure, consider the following sample section of the plugin.xml file:

```xml
<extensionPoints>
  <extensionPoint name="MyExtensionPoint1" beanClass="MyPlugin.MyBeanClass1">
  <extensionPoint name="MyExtensionPoint2" interface="MyPlugin.MyInterface">
</extensionPoints>
```

* The `interface` attribute sets an interface the plugin that contributes to the extension point must implement.
* The `beanClass` attribute sets a bean class that specifies one or several properties annotated with the [@Attribute](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) annotation.

The plugin that contributes to the extension point will read those properties from the `plugin.xml` file.

To clarify this, consider the following sample `MyBeanClass1` bean class used in the above `plugin.xml` file:

```java
public class MyBeanClass1 extends AbstractExtensionPointBean {
  @Attribute("key")
  public String key;

  @Attribute("implementationClass")
  public String implementationClass;

  public String getKey() {
    return key;
  }

  public String getClass() {
    return implementationClass;
  }
}
```

To declare an extension designed to access the `MyExtensionPoint1` extension point, your `plugin.xml` file must contain the `<MyExtensionPoint1>` tag with the `key` and `implementationClass` attributes set to appropriate values (see sample below).

**To declare an extension**

> **TIP** Auto-completion is available for all these steps.

1. For the `<extensions>` element, set the `defaultExtensionNs` attribute to one of the following values:
    * `com.intellij`, if your plugin extends the IntelliJ Platform core functionality.
    * `{ID of a plugin}`, if your plugin extends a functionality of another plugin.
2. Add a new child element to the `<extensions>` element. The child element name must match the name of the extension point you want the extension to access.
3. Depending on the type of the extension point, do one of the following:
    * If the extension point was declared using the `interface` attribute, for newly added child element, set the `implementation` attribute to the name of the class that implements the specified interface.
    * If the extension point was declared using the `beanClass` attribute, for newly added child element, set all attributes annotated with the [@Attribute](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) annotations in the specified bean class.


To clarify this procedure, consider the following sample section of the `plugin.xml` file that defines two extensions designed to access the `appStarter` and `applicationConfigurable` extension points in the *IntelliJ Platform* and one extension to access the `MyExtensionPoint1` extension point in a test plugin:

```xml
<!-- Declare extensions to access extension points in the IntelliJ Platform.
     These extension points have been declared using the "interface" attribute.
 -->
  <extensions defaultExtensionNs="com.intellij">
    <appStarter implementation="MyTestPackage.MyTestExtension1" />
    <applicationConfigurable implementation="MyTestPackage.MyTestExtension2" />
  </extensions>

<!-- Declare extensions to access extension points in a custom plugin
     The MyExtensionPoint1 extension point has been declared using *beanClass* attribute.
-->
  <extensions defaultExtensionNs="MyPluginID">
     <MyExtensionPoint1 key="keyValue" implementationClass="MyTestPackage.MyClassImpl"></MyExtensionPoint1>
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

