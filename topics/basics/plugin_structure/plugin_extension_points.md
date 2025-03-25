<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Extension Points

<web-summary>
Extend a plugin's functionality by defining extension points for other plugins.
</web-summary>

<link-summary>Allowing extending a plugin's functionality by other plugins.</link-summary>

> See [Plugin Extensions](plugin_extensions.md) for _using_ extension points in your plugin.
>
{style="note"}

By defining _extension points_ in your plugin, you can allow other plugins to extend your plugin's functionality.
There are two types of extension points:

* _Interface_ extension points allow other plugins to extend your plugins with _code_.
  When defining an interface extension point, specify an interface, and other plugins will provide classes implementing that interface.
  The providing plugin can then invoke methods on this interface.
  In most cases, the interface can be annotated with `@ApiStatus.OverrideOnly` (see [](verifying_plugin_compatibility.md#override-only-api)).
* _Bean_ extension points allow other plugins to extend a plugin with _data_.
  Specify the fully qualified name of an extension class, and other plugins will provide data that will be turned into instances of that class.

<procedure title="Declaring Extension Point" id="declaring-extension-points">

1. Add an [`<extensionPoints>`](plugin_configuration_file.md#idea-plugin__extensionPoints) section to <path>plugin.xml</path>, if it's not yet present there.
2. Add a child element [`<extensionPoint>`](plugin_configuration_file.md#idea-plugin__extensionPoints__extensionPoint).
3. Specify the extension point name with the `name` or `qualifiedName` attribute __*__.
4. Depending on the extension point type, specify the `interface` or `beanClass` attribute __*__.
5. If required, specify the `area` attribute __*__.

See the [](#example).

__*__ _see the **Attributes** section for [`<extensionPoint>`](plugin_configuration_file.md#idea-plugin__extensionPoints__extensionPoint) for details_

</procedure>

The plugin that contributes to the extension point will read the specified properties from the <path>plugin.xml</path> file.

If extension implementations are filtered according to [dumb mode](indexing_and_psi_stubs.md#dumb-mode), the base class should be
marked with [`PossiblyDumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/PossiblyDumbAware.java) to highlight this.
Use [`DumbService.getDumbAwareExtensions()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbService.kt) to retrieve dumb-aware implementations.

Base classes for extensions requiring a key:

- [`LanguageExtension`](%gh-ic%/platform/core-api/src/com/intellij/lang/LanguageExtension.java)
- [`FileTypeExtension`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileTypeExtension.java)
- [`ClassExtension`](%gh-ic%/platform/core-api/src/com/intellij/openapi/util/ClassExtension.java)
- [`KeyedExtensionCollector`](%gh-ic%/platform/core-api/src/com/intellij/openapi/util/KeyedExtensionCollector.java)

> See the [](bundling_plugin_openapi_sources.md) section explaining how to expose extension points sources to other plugins.
>
{style="note"}

## Example

Consider example extension points declarations:

<path>myPlugin/META-INF/plugin.xml</path>

```xml
<idea-plugin>
  <id>my.plugin</id>

  <extensionPoints>
    <extensionPoint
        name="myExtensionPoint1"
        interface="com.example.MyInterface"/>

    <extensionPoint
        name="myExtensionPoint2"
        beanClass="com.example.MyBeanClass"/>
  </extensionPoints>

</idea-plugin>
```

The `com.example.MyBeanClass` bean class used in the above `plugin.xml` file is implemented as follows:

<path>myPlugin/src/com/myplugin/MyBeanClass.java</path>

```java
public final class MyBeanClass extends AbstractExtensionPointBean {

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

For the above extension points, their usage in _anotherPlugin_ would look like this (see also [Declaring Extensions](plugin_extensions.md#declaring-extensions)):

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
            implementationClass="com.example.MyImplementation"/>

    <myExtensionPoint2
            implementation="another.MyInterfaceImpl"/>
  </extension>

</idea-plugin>
```

## Using Extension Points

To refer to all registered extension instances at runtime, declare an [`ExtensionPointName`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/ExtensionPointName.kt) with private visibility passing in the fully qualified name matching its [declaration in plugin.xml](#declaring-extension-points).
If needed, provide a public method to query registered extensions (Sample: [`TestSourcesFilter.isTestSources()`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/TestSourcesFilter.java)).

<path>myPlugin/src/com/myplugin/MyExtensionUsingService.java</path>

```java
@Service
public final class MyExtensionUsingService {

  private static final ExtensionPointName<MyBeanClass> EP_NAME =
      ExtensionPointName.create("my.plugin.myExtensionPoint1");

  public void useRegisteredExtensions() {
    for (MyBeanClass extension : EP_NAME.getExtensionList()) {
      String key = extension.getKey();
      String clazz = extension.getClass();
      // ...
    }
  }

}
```

A gutter icon for the `ExtensionPointName` declaration allows navigating to the corresponding [`<extensionPoint>`](plugin_configuration_file.md#idea-plugin__extensionPoints__extensionPoint) declaration in <path>plugin.xml</path>.
Code insight is available for the extension point name String literal (2022.3).

### Error Handling

When processing extension implementations or registrations, there might be errors, compatibility and configuration issues.
Use [`PluginException`](%gh-ic%/platform/core-api/src/com/intellij/diagnostic/PluginException.java) to log and correctly attribute the causing plugin for
[builtin error reporting](ide_infrastructure.md#error-reporting).

To report use of a deprecated API, use `PluginException.reportDeprecatedUsage()` methods.

**Examples:**
- [`CompositeFoldingBuilder.assertSameFile()`](%gh-ic%/platform/core-api/src/com/intellij/lang/folding/CompositeFoldingBuilder.java)
- [`InspectionProfileEntry.getDisplayName()`](%gh-ic%/platform/analysis-api/src/com/intellij/codeInspection/InspectionProfileEntry.java)

## Dynamic Extension Points
<primary-label ref="2020.1"/>

To support [Dynamic Plugins](dynamic_plugins.md), an extension point must adhere to specific usage rules:

- extensions are enumerated on every use, and extension instances are not stored anywhere
- alternatively, an [`ExtensionPointListener`](%gh-ic%/platform/extensions/src/com/intellij/openapi/extensions/ExtensionPointListener.kt) can perform any necessary updates of data structures (register via `ExtensionPointName.addExtensionPointListener()`)

Extension points matching these conditions can then be marked as _dynamic_ by adding `dynamic="true"` in their declaration:

```xml
<extensionPoints>
  <extensionPoint
          name="myDynamicExtensionPoint"
          beanClass="com.example.MyBeanClass"
          dynamic="true"/>
</extensionPoints>
```

All non-dynamic extension points are highlighted via <control>Plugin DevKit | Plugin descriptor | Plugin.xml dynamic plugin verification</control> inspection.
