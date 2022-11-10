[//]: # (title: GoLand Plugin Development)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

[GoLand](https://www.jetbrains.com/go/) is an IntelliJ Platform-based product.
Plugin projects for GoLand can be developed using IntelliJ IDEA with the [](tools_gradle_intellij_plugin.md).

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.
>

## Configuring Plugin Projects Targeting GoLand

<tabs>

<tab title="GoLand IDE">

The configuration of targeting GoLand IDE follows the methods described in [Configuring Plugin Projects Using a Product-Specific Attribute](dev_alternate_products.md#configuring-plugin-projects-using-a-product-specific-attribute).

Starting with 2020.2, it's possible to configure `GO` for `intellij.type` in the Gradle build script.

<tabs>
<tab title="Kotlin">

```kotlin
intellij {
  version.set("2020.3")
  type.set("GO")
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
  version = '2020.3'
  type = 'GO'
}
```

</tab>
</tabs>


</tab>

<tab title="Using Plugin">

The configuration of GoLand plugin projects follows the methods described in [Configuring Plugin Projects using the IntelliJ IDEA Product Attribute](dev_alternate_products.md#configuring-plugin-projects-using-the-intellij-idea-product-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.
To see how these attributes appear in a similar Gradle build script for PhpStorm, see [](dev_alternate_products.md#configuring-gradle-build-script-using-the-intellij-idea-product-attribute).

The Go plugin version is explicitly declared because it isn't bundled with IntelliJ IDEA Ultimate Edition.
Select a [version](https://plugins.jetbrains.com/plugin/9568-go/versions) of the Go plugin compatible with the IntelliJ Idea Ultimate version.

| `gradle-intellij-plugin` Attribute                                               | Attribute Value                                                                                                                                                                                                |
|----------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)       | `IU` for IntelliJ IDEA Ultimate. The Go plugin isn't compatible with IntelliJ IDEA Community Edition.                                                                                                          |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) | Set to the same `IU` BRANCH.BUILD as the GoLand target version, e.g. `193.5233.102`.                                                                                                                           |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) | `org.jetbrains.plugins.go:193.5233.102.83` for the Go plugin.<br/>See below for Go plugin version information.                                                                                                 |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)            | Path to locally installed target version of GoLand. For example, on macOS:<br/><path>/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/Goland/ch-0/193.5233.112/GoLand.app/Contents</path>. |

</tab>

</tabs>

The dependency on the Go plugin APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `com.intellij.modules.goland`.
The <path>plugin.xml</path> file must also declare a dependency on `com.intellij.modules.platform` as explained in [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).
The dependency declaration is illustrated in the <path>plugin.xml</path> snippet below:


<tabs>
<tab title="2020.2 and later">

```xml
<!-- Requires the GoLand module -->
<depends>com.intellij.modules.goland</depends>
<!-- Requires the platform module to distinguish it from a legacy plugin -->
<depends>com.intellij.modules.platform</depends>
```

</tab>
<tab title="Pre-2020.2">

```xml
<!-- Requires the Go module -->
<depends>com.intellij.modules.go</depends>
<!-- Requires the platform module to distinguish it from a legacy plugin -->
<depends>com.intellij.modules.platform</depends>
```

</tab>
</tabs>

### Targeting IDEs Other Than GoLand

Depending on the `com.intellij.modules.goland` allows a plugin to be installed in the GoLand IDE only.
However, the Go plugin can be installed in [IntelliJ IDEA Ultimate](https://www.jetbrains.com/idea/) and potentially other IDEs.
To make the plugin compatible with GoLand and other IDEs supporting the Go language consider depending on:
* `org.jetbrains.plugins.go` - The plugin will be loaded only when the Go plugin is actually installed in the running IDE.
* `com.intellij.modules.go-capable` - The plugin will be loaded in IDEs that are capable of installing the Go plugin.
  Note that the Go plugin doesn't have to be actually installed when this module is present.


## Available GoLand APIs

> See [](goland_extension_point_list.md) for the complete list.
>
{type="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the library <path>intellij-go-$version$.jar</path>, where `$version$` corresponds to the version of the Go plugin.
Test your plugin with any version of GoLand you intend to support.

## GoLand Test Framework

Please see [this issue](https://github.com/JetBrains/gradle-intellij-plugin/issues/477#issuecomment-845022914) for required additional dependency setup.

## Open Source Plugins for GoLand

When learning new APIs, it is helpful to have some representative projects for reference:
* [Go Method Generator](https://github.com/pkondratev/Intellij-go-method-generator)
* [Go Builder Generator](https://github.com/OddCN/go-builder-generator-idea-plugin)
