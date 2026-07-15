<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# CLion Plugin Development

<link-summary>Introduction to developing plugins for CLion.</link-summary>

<var name="productID" value="clion"/>
<var name="marketplaceProductID" value="clion"/>
<include from="snippets.topic" element-id="jetbrainsIDE_TLDR"/>

[CLion](https://www.jetbrains.com/clion/) is an IntelliJ Platform-based product.
Plugin projects for CLion can be developed using [IntelliJ IDEA](idea.md).

> CLion is free for non-commercial use

> Since CLion 2025.3, CLion Nova — the C/C++ language engine based on the ReSharper C++ engine — is enabled by default.
> Since CLion 2026.2, the previous _Classic_ engine is no longer bundled with the IDE distribution.
> If your plugin uses the classic C/C++ language APIs (`OCFileType`, `OCLanguage`, `OCFile`, and other `com.jetbrains.cidr.lang` classes), see [C/C++ Language Engine: Nova and Classic](#language-engine) for the required changes.
>
{style="warning"}

## CLion Plugin Setup

### Gradle Build Script

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Define a dependency using [`clion()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md), see _Versions_ link on top of this page for all available versions.
See [](tools_intellij_platform_gradle_plugin.md#dependenciesLocalPlatform) for using a local installation.

A dependency on the bundled `com.intellij.clion` plugin must be added using the [`bundledPlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helper.

Minimum <path>build.gradle.kts</path> setup:

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    clion("<versionNumber>")
    bundledPlugin("com.intellij.clion")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)">

> When targeting 2020.3, see this [migration guide](https://blog.jetbrains.com/clion/2020/12/migration-guide-for-plugins-2020-3/).
>
{style="warning"}

The configuration of CLion plugin projects follows the methods described in [Configuring Plugin Projects using a Product-Specific Attribute](dev_alternate_products.md#using-a-product-specific-attribute), and [Configuring the plugin.xml File](dev_alternate_products.md#configuring-pluginxml).

The table below summarizes the [](tools_gradle_intellij_plugin.md) attributes to set in the plugin project's Gradle build script.
Click on an entry in the table's *Attribute* column to go to the documentation about that attribute.

| `gradle-intellij-plugin` Attribute                                                               | Attribute Value                                                                |
|--------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------|
| [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type)                       | `CL` for the product CLion.                                                    |
| [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version)                 | Set to the targeted CLion version, e.g. `2019.3.1`.                            |
| [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins)                 | No specific declaration is needed.                                             |
| [`intellij.downloadSources`](tools_gradle_intellij_plugin.md#intellij-extension-downloadsources) | `false` is required because no public source code is available.                |
| [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir)                           | Not needed; the Development Instance will automatically match `intellij.type`. |

</tab>
</tabs>

### plugin.xml

The dependency on the CLion APIs must be declared in the <path>[plugin.xml](plugin_configuration_file.md)</path> file.
As described in [Modules Specific to Functionality](plugin_compatibility.md#modules-specific-to-functionality) table, the [`<depends>`](plugin_configuration_file.md#idea-plugin__depends) tags must declare `com.intellij.modules.clion` module dependency,
or `com.intellij.clion` plugin dependency when targeting only versions 2020.3+.

## C/C++ Language Engine: Nova and Classic {id="language-engine"}

CLion provides two C/C++ language engines:

* **Nova** – the current engine based on the ReSharper C++ engine.
  It is enabled by default since CLion 2025.3 and is the only engine bundled with the IDE since CLion 2026.2.
* **Classic** – the original in-IDE engine, backed by the `com.intellij.cidr.lang` plugin and the `com.jetbrains.cidr.lang` APIs.
  Since CLion 2026.2, it is no longer bundled and is distributed only as a separate JetBrains Marketplace plugin.

> The Classic engine plugin is provided as a temporary compatibility measure for existing plugins and will be removed in a future release.
> Migrate to the Nova APIs where possible.
>
{style="warning"}

### Using the Classic C/C++ Language APIs

The classic C/C++ language support — including `OCFileType`, `OCLanguage`, `OCFile`, and the rest of the `com.jetbrains.cidr.lang` package — is available in the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin (ID `com.intellij.cidr.lang`).

As this plugin is no longer bundled, declare a dependency on its Marketplace version using the [`plugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#non-bundled-plugin) function:

```kotlin

dependencies {
  intellijPlatform {
    clion("<versionNumber>")
    bundledPlugin("com.intellij.clion")

    // C/C++ Language Support via Classic Engine (unbundled since 2026.2)
    plugin("com.intellij.cidr.lang", "<pluginVersion>")
  }
}
```

> The [`compatiblePlugin()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#compatible-plugins) helper can be used instead to resolve a version compatible with the targeted CLion automatically.
>
{style="tip"}

The `com.intellij.modules.cidr.lang` module dependency in the <path>[plugin.xml](plugin_configuration_file.md)</path> file stays unchanged:

```xml
<depends>com.intellij.modules.cidr.lang</depends>
```

### Migrating to Nova

Nova represents C/C++ files with different classes located in the `com.jetbrains.rider.cpp.fileType` package:

| Classic (`com.jetbrains.cidr.lang`) | Nova (`com.jetbrains.rider.cpp.fileType`) |
|-------------------------------------|-------------------------------------------|
| `OCFileType`                        | `CppFileType`                             |
| `OCLanguage`                        | `CppLanguage`                             |
| `psi.OCFile`                        | `psi.CppFile`                             |

Nova performs C/C++ code analysis in a separate backend process and does not expose a frontend PSI model.
Apart from the file type and language above (and basic traversal via `com.jetbrains.rider.cpp.fileType.psi.CppElement`), there is no replacement for the Classic frontend APIs, including:

* PSI elements — `com.jetbrains.cidr.lang.psi.OCFile`, `com.jetbrains.cidr.lang.psi.OCIncludeDirective`, `com.jetbrains.cidr.lang.psi.OCPragma`, and the rest of the `com.jetbrains.cidr.lang.psi` package
* symbols — the `com.jetbrains.cidr.lang.symbols` package
* types — `com.jetbrains.cidr.lang.types.OCType`
* resolve and references — the `com.jetbrains.cidr.lang.resolve` package

> Plugins relying on these APIs cannot be fully ported to Nova.
> Keep depending on the [C/C++ Language Support via Classic Engine](https://plugins.jetbrains.com/plugin/32355-c-c--language-support-via-classic-engine) plugin if these APIs are required.
>
{style="note"}

If your plugin cannot be migrated to CLion Nova yet, follow and vote for [CPP-36085](https://youtrack.jetbrains.com/issue/CPP-36085) to track improvements to Classic-to-Nova plugin migration.

## Available CLion APIs

> See [](clion_extension_point_list.md) for the complete list.
>
{style="note"}

Use the [Exploring APIs as a Consumer](plugin_compatibility.md#exploring-apis-as-a-consumer) process to identify the JAR files under the External Library `Gradle:com.jetbrains:clion:<version>`.
Test your plugin with versions of CLion you intend to support.

## Open Source Plugins for CLion

When learning new APIs, it is helpful to have some representative projects for reference:

* [C/C++ Coverage](https://github.com/zero9178/C-Cpp-Coverage-for-CLion)
* [C/C++ Single File Execution](https://github.com/corochann/SingleFileExecutionPlugin)

## Getting Help

<include from="snippets.topic" element-id="implementationSupport"></include>
