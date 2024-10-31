<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Plugins Targeting IntelliJ Platform-Based IDEs

<link-summary>Configuring plugin projects for targeting IntelliJ Platform-based IDEs other than IntelliJ IDEA.</link-summary>

Plugin projects can target any (custom) IDEs, as long as the products are based on the [IntelliJ Platform](intellij_platform.md).
Such plugins are developed much like plugin projects that target IntelliJ IDEA.

Modifications are required to the project's Gradle build script and <path>[plugin.xml](plugin_configuration_file.md)</path> files, as described below
and on the individual product pages in _Product Specific_.
The Gradle build script is modified to specify the target product, determining the APIs available to the plugin.
The <path>plugin.xml</path> file is modified to declare the plugin's dependencies on plugins and modules.

## IntelliJ Platform Gradle Plugin (2.x)

A dependency on a specific IDE is defined in the Gradle build script using the builtin extensions listed in [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-platforms).
Then, dependencies on required bundled modules and plugins must be defined in the <path>plugin.xml</path> file.
The necessary configuration for the corresponding product can be found under _Product Specific_ (for example, for [WebStorm](webstorm.md)).

See also [](plugin_compatibility.md).

## Gradle IntelliJ Plugin (1.x)
{id="gradle1"}

<primary-label ref="Obsolete"/>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

To create a new Gradle plugin project, follow the tutorial on the [](creating_plugin_project.md) page.
The tutorial produces a skeleton Gradle project suitable to use as a starting point.

The best practice is to use the [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) property to specify the target product.
For example, `PY` for PyCharm Professional.
Configuration using an `intellij.type` property is explained in the [](#using-a-product-specific-attribute) section below.

NOTE: Not all products have an `intellij.type` property defined by the [](tools_gradle_intellij_plugin.md), for example, [Android Studio](android_studio.md) and [PhpStorm](phpstorm.md).
Please see their respective pages for configuration and
[](#configuring-gradle-build-script-using-the-intellij-idea-product-attribute) below.

All the Gradle configuration attributes described here are discussed in-depth on the [](configuring_plugin_project.md) and the [](tools_gradle_intellij_plugin.md).

> To target multiple products (for example, IntelliJ IDEA and PyCharm) with the same plugin, see [](plugin_compatibility.md) page.
>
{style="note"}

### Using a Product-Specific Attribute

If the [](tools_gradle_intellij_plugin.md) supports a target product directly, there will be an [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) property defined.
Specifying the target as a product-specific `intellij.type` property has two advantages:

* The APIs available to the plugin will be limited to only what is defined in the target product
  (unless additional plugin dependencies are specified).
* The default [](ide_development_instance.md) for running the plugin will be the target product.

A Gradle build script snippet setting a plugin project to target [PyCharm](pycharm.md) is shown below.
The [](tools_gradle_intellij_plugin.md) will fetch the matching build of PyCharm Professional to define the APIs available, and use that build of PyCharm (and associated JetBrains Runtime) as the Development Instance.
No additional product-specific configuration needs to be set in the Gradle build script:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellij {
  type.set("PY")
  version.set("2019.2.3")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellij {
  type = 'PY'
  version = '2019.2.3'
}
```

</tab>
</tabs>

### Using the IntelliJ IDEA Product Attribute

If the [](tools_gradle_intellij_plugin.md) does not directly support an IntelliJ Platform-based product, the Gradle build script can still be configured to target the desired product.
In this case, the build script is configured to use IntelliJ IDEA (Community or Ultimate Edition) as the basis for the available APIs.
This does have the drawback that APIs not specific to the target product might accidentally be included in the plugin project.
However, testing the plugin project in the target product itself helps to find such mistakes.

Additional configuration must be done to match the version of IntelliJ IDEA to the version of the target product.
Understanding the relationship between build numbers is critical when using this approach to project configuration:

* _targetIDE_ is the (version-specific) IntelliJ Platform-based IDE in which the plugin is intended to run, such as Android Studio or PhpStorm.
* _baseIntelliJPlatformVersion_ is the (version-specific) IntelliJ Platform used in the build of the _targetIDE_.
  The IntelliJ Platform is defined by a specific build of the IntelliJ IDEA Community Edition.
  The Gradle plugin attribute [`intellij.version`](configuring_plugin_project.md#intellij-platform-configuration) is set to be _baseIntelliJPlatformVersion_.

For API compatibility, the IntelliJ Platform version used in the _targetIDE_ dictates the _baseIntelliJPlatformVersion_ used for developing a plugin.

#### Matching Versions of the IntelliJ Platform with the Target IDE Version

The _baseIntelliJPlatformVersion_ used in the _targetIDE_ may not be readily apparent, depending on the product.
See the individual product pages in _Product Specific_ for exceptions.

To find the version of the IntelliJ Platform used to build the _targetIDE_, use the <control>About</control> dialog screen for the _targetIDE_.
Next to <control>Build #</control> is the *BRANCH.BUILD.FIX* version of the _targetIDE_.
In the example shown below, the *BRANCH.BUILD.FIX* version is `192.7142.41`, and the product version is `2019.2.4`.
The version of the IntelliJ Platform used to build this product version is *BRANCH.BUILD*, or `192.7142`

![Example PhpStorm Splash Screen](phpstorm_build.png){width="500"}

If the product version isn't clear on the <control>About</control> screen, consult the individual product pages in _Product Specific_.

The [Other IntelliJ IDEA Versions](https://www.jetbrains.com/idea/download/other.html) page is a way to find build numbers for every product version.
Additional ways include hovering over the version number for a product in [Toolbox App](https://www.jetbrains.com/toolbox-app/) or examining the <control>About</control> screen for IntelliJ IDEA Community.
In this example, IntelliJ IDEA Community Edition (which defines the IntelliJ Platform) for `2019.2.4` is build number `192.7142.36`.
Although the *FIX* versions are different, this is not uncommon between products, and the builds are still compatible.
The *BRANCH* and *BUILD* numbers match, therefore in this PhpStorm example:

* The _targetIDE_ is PhpStorm, build `192.7142.41`,
* The _baseIntelliJPlatformVersion_ (IntelliJ IDEA Community Edition) is build `192.7142.36`

This information is used to configure the plugin project's Gradle build script and <path>plugin.xml</path> file.

#### Configuring Gradle Build Script Using the IntelliJ IDEA Product Attribute

Configuring a Gradle plugin project for using _baseIntelliJPlatformVersion_ requires changing some default settings in the Gradle build script.
Changes need to be made in two places: [`intellij`](tools_gradle_intellij_plugin.md#configuration-intellij-extension) extension and [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task.

The Gradle plugin attributes describing the configuration of the [IntelliJ Platform used to build the plugin project](configuring_plugin_project.md#intellij-platform-configuration) must be explicitly set in the `intellij` task.
The [`intellij.type`](tools_gradle_intellij_plugin.md#intellij-extension-type) is `IU` because although the IntelliJ IDEA Community Edition defines the IntelliJ Platform, the PHP plugin is only compatible with IntelliJ IDEA Ultimate.
The [`intellij.version`](tools_gradle_intellij_plugin.md#intellij-extension-version) is _baseIntelliJPlatformVersion_.

Any [dependencies](configuring_plugin_project.md#plugin-dependencies) on _targetIDE_-specific plugins or modules must be declared in the [`intellij`](tools_gradle_intellij_plugin.md#configuration-intellij-extension) extension.
Use the Gradle plugin attribute [`intellij.plugins`](tools_gradle_intellij_plugin.md#intellij-extension-plugins) to declare a dependency.
See the specific product pages in _Product Specific_ for the _targetIDE_ plugin or module name.

The best practice is to modify the [`runIde`](tools_gradle_intellij_plugin.md#tasks-runide) task to use a local installation of _targetIDE_ as the [](ide_development_instance.md).
Set the [`runIde.ideDir`](tools_gradle_intellij_plugin.md#tasks-runide-idedir) attribute to the (user-specific) absolute path of the _targetIDE_ application.
The exact path format varies by operating system.

This snippet is an example for configuring the Setup and Running DSLs in a Gradle build script specific to developing a plugin for _targetIDE_.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellij {
  // Define IntelliJ Platform against which to build the plugin project.
  type.set("IU")

  // Use the IntelliJ Platform BRANCH.BUILD version matching
  // "targetIDE" (PhpStorm):
  version.set("192.7142.36")   // baseIntelliJPlatformVersion

  // Require the targetIDE plugin or library. Use the stable version
  // compatible with intellij.version and intellij.type specified above:
  plugins.set(listOf("com.jetbrains.php:192.6603.42"))
}

runIde {
  // Absolute path to the installed targetIDE to use as IDE Development
  // Instance (the "Contents" directory is macOS specific):
  ideDir.set(file("/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/PhpStorm/ch-0/192.7142.41/PhpStorm.app/Contents"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellij {
  // Define IntelliJ Platform against which to build the plugin project.
  type = 'IU'

  // Use the IntelliJ Platform BRANCH.BUILD version matching
  // "targetIDE" (PhpStorm):
  version = '192.7142.36'      // baseIntelliJPlatformVersion

  // Require the targetIDE plugin or library. Use the stable version
  // compatible with intellij.version and intellij.type specified above:
  plugins = ['com.jetbrains.php:192.6603.42']
}

runIde {
  // Absolute path to the installed targetIDE to use as IDE Development
  // Instance (the "Contents" directory is macOS specific):
  ideDir = file('/Users/$USERNAME$/Library/Application Support/JetBrains/Toolbox/apps/PhpStorm/ch-0/192.7142.41/PhpStorm.app/Contents')
}
```

</tab>
</tabs>

### Configuring plugin.xml

As discussed on the [](plugin_compatibility.md#declaring-plugin-dependencies) page of this guide, a plugin's dependency on [](plugin_compatibility.md#modules-specific-to-functionality) must be declared in <path>plugin.xml</path>.
When using features (APIs) specific to the target product, a dependency on the target product module must be declared, as shown in the code snippet below.
Otherwise, if only general IntelliJ Platform features (APIs) are used, then a dependency on `com.intellij.modules.platform` must be declared as discussed in [](plugin_compatibility.md).

> In the particular case of a plugin project declaring dependencies only on other plugins, it must also declare a dependency on `com.intellij.modules.platform`.
> Otherwise, the plugin project is considered to be legacy and will only load in IntelliJ IDEA.
>
{style="note"}

Continuing with the example of developing a plugin for PhpStorm:

```xml
<!-- Targeting PhpStorm, so is dependent on the PHP plugin -->
<depends>com.jetbrains.php</depends>
<depends>com.intellij.modules.platform</depends>
```
