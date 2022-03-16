[//]: # (title: Extension Points)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

> See [Plugin Extensions](plugin_extensions.md) for _using_ extension points in your plugin.
>
{type="note"}

By defining _extension points_ in your plugin, you can allow other plugins to extend your plugin's functionality.
There are two types of extension points:

* _Interface_ extension points allow other plugins to extend your plugins with _code_.
  When you define an interface extension point, you specify an interface, and other plugins will provide classes implementing that interface.
  You'll then be able to invoke methods on those interfaces.
* _Bean_ extension points allow other plugins to extend your plugins with _data_.
  You specify the fully qualified name of an extension class, and other plugins will provide data that will be turned into instances of that class.

## Declaring Extension Points

You can declare extensions and extension points in the plugin configuration file <path>plugin.xml</path>, within the `<extensions>` and `<extensionPoints>` sections.

To declare extension points in your plugin, add an `<extensionPoints>` section to your <path>plugin.xml</path>.
Then insert a child element `<extensionPoint>` that defines the extension point name and the name of a bean class or an interface that is allowed to extend the plugin functionality in the `name`, `beanClass` and `interface` attributes, respectively.

<path>myPlugin/META-INF/plugin.xml</path>

```xml
<idea-plugin>
  <id>my.plugin</id>

  <extensionPoints>
    <extensionPoint
        name="myExtensionPoint1"
        beanClass="com.example.MyBeanClass"/>

    <extensionPoint
        name="myExtensionPoint2"
        interface="com.example.MyInterface"/>
  </extensionPoints>

</idea-plugin>
```

The `name` attribute assigns a unique name for this extension point.
Its fully qualified name required in [Using Extension Points](#using-extension-points) is built by prefixing `<id>` + `.`: `my.plugin.myExtensionPoint1` and `my.plugin.myExtensionPoint2`.

The `beanClass` attribute sets a bean class that specifies one or several properties annotated with the [`@Attribute`](upsource:///platform/util/src/com/intellij/util/xmlb/annotations/Attribute.java) annotation.
Note that bean classes do not follow the JavaBean standard.

The `interface` attribute sets an interface the plugin that contributes to the extension point must implement.

The `area` attribute determines the scope in which the extension will be instantiated.
As extensions should be stateless, it is **not** recommended to use non-default.
Must be one of `IDEA_APPLICATION` for Application (default), `IDEA_PROJECT` for Project, or `IDEA_MODULE` for Module scope.

The plugin that contributes to the extension point will read those properties from the <path>plugin.xml</path> file.

### Sample

To clarify this, consider the following sample `MyBeanClass` bean class used in the above <path>plugin.xml</path> file:

<path>myPlugin/src/com/myplugin/MyBeanClass.java</path>

```java
public class MyBeanClass extends AbstractExtensionPointBean {

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

> See [Extension properties code insight](plugin_extensions.md#extension-properties-code-insight) on how to provide smart completion/validation.
>
{type="tip"}

For above extension points usage in _anotherPlugin_ would look like this (see also [Declaring Extensions](plugin_extensions.md#declaring-extensions)):

<path>anotherPlugin/META-INF/plugin.xml</path>

```xml
<idea-plugin>
  <id>another.plugin</id>

  <!-- Declare dependency on plugin defining extension point: -->
  <depends>my.plugin</depends>

  <!-- Use "my.plugin" namespace: -->
  <extensions defaultExtensionNs="my.plugin">
    <myExtensionPoint1
        key="someKey"
        implementationClass="another.some.implementation.class"/>

    <myExtensionPoint2
        implementation="another.MyInterfaceImpl"/>
  </extension>

</idea-plugin>
```

## Using Extension Points
To refer to all registered extension instances at runtime, declare an [`ExtensionPointName`](upsource:///platform/extensions/src/com/intellij/openapi/extensions/ExtensionPointName.java) passing in the fully-qualified name matching its [declaration in plugin.xml](#declaring-extension-points).

<path>myPlugin/src/com/myplugin/MyExtensionUsingService.java</path>

```java
public class MyExtensionUsingService {

  private static final ExtensionPointName<MyBeanClass> EP_NAME =
    ExtensionPointName.create("my.plugin.myExtensionPoint1");

  public void useExtensions() {
    for (MyBeanClass extension : EP_NAME.getExtensionList()) {
      String key = extension.getKey();
      String clazz = extension.getClass();
      // ...
    }
  }
}
```

A gutter icon for the `ExtensionPointName` declaration allows navigating to the corresponding `<extensionPoint>` declaration in <path>plugin.xml</path>.

## Dynamic Extension Points
To support [Dynamic Plugins](dynamic_plugins.md) (2020.1 and later), an extension point must adhere to specific usage rules:

- extensions are enumerated on every use and extensions instances are not stored anywhere
- alternatively, an [`ExtensionPointListener`](upsource:///platform/extensions/src/com/intellij/openapi/extensions/ExtensionPointListener.java) can perform necessary updates of data structures (register via `ExtensionPointName.addExtensionPointListener()`)

Extension points matching these conditions can then be marked as _dynamic_ by adding `dynamic="true"` in their declaration:

```xml
<extensionPoints>
  <extensionPoint
      name="myDynamicExtensionPoint"
      beanClass="com.example.MyBeanClass"
      dynamic="true"/>
</extensionPoints>
```

> All non-dynamic extension points are highlighted via <control>Plugin DevKit | Plugin descriptor | Plugin.xml dynamic plugin verification</control> inspection available in IntelliJ IDEA 2020.1 or later.
> Previous versions also highlight the `dynamic` attribute as "experimental".
