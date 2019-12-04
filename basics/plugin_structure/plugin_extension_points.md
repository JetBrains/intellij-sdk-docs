---
title: Plugin Extension Points
---

By defining _extension points_ in your plugin, you can allow other plugins to extend the functionality of your plugin.
There are two types of extension points:

 * _Interface_ extension points allow other plugins to extend your plugins with _code_. When you define an interface
   extension point, you specify an interface, and other plugins will provide classes implementing that interface.
   You'll then be able to invoke methods on those interfaces.
 * _Bean_ extension points allow other plugins to extend your plugins with _data_. You specify the fully qualified
   name of an extension class, and other plugins will provide data which will be turned into instances of that class.  

## How to declare extension points

You can declare extensions and extension points in the plugin configuration file `plugin.xml`, within the `<extensions>` and `<extensionPoints>` sections, respectively.

To declare extension points in your plugin, add an `<extensionPoints>` section to your plugin.xml. Then insert a child element `<extensionPoint>` that defines the extension point name and the name of a bean class or an interface that is allowed to extend the plugin functionality in the `name`, `beanClass` and `interface` attributes, respectively.

To clarify this procedure, consider the following sample section of the plugin.xml file:

```xml
<extensionPoints>
  <extensionPoint name="MyExtensionPoint1" beanClass="MyPlugin.MyBeanClass1">
  <extensionPoint name="MyExtensionPoint2" interface="MyPlugin.MyInterface">
</extensionPoints>
```

* The `interface` attribute sets an interface the plugin that contributes to the extension point must implement.
* The `beanClass` attribute sets a bean class that specifies one or several properties annotated with the [`@Attribute`](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) annotation.

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

