<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Types

<link-summary>IntelliJ Platform Gradle Plugin data types, enums, and constants.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

This page lists various types used to configure [](tools_intellij_platform_gradle_plugin_tasks.md).

## `FailureLevel`
{#FailureLevel}

[`VerifyPluginTask.FailureLevel`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

Enum class describing the failure level of the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name                               | Description                                                                         |
|------------------------------------|-------------------------------------------------------------------------------------|
| `COMPATIBILITY_WARNINGS`           | Compatibility warnings detected against the specified IDE version.                  |
| `COMPATIBILITY_PROBLEMS`           | Compatibility problems detected against the specified IDE version.                  |
| `DEPRECATED_API_USAGES`            | Plugin uses API marked as deprecated (`@Deprecated`).                               |
| `SCHEDULED_FOR_REMOVAL_API_USAGES` | Plugin uses API marked as scheduled for removal (`@ApiStatus.ScheduledForRemoval`). |
| `EXPERIMENTAL_API_USAGES`          | Plugin uses API marked as experimental (`@ApiStatus.Experimental`).                 |
| `INTERNAL_API_USAGES`              | Plugin uses API marked as internal (`@ApiStatus.Internal`).                         |
| `OVERRIDE_ONLY_API_USAGES`         | Override-only API is used incorrectly (`@ApiStatus.OverrideOnly`).                  |
| `NON_EXTENDABLE_API_USAGES`        | Non-extendable API is used incorrectly (`@ApiStatus.NonExtendable`).                |
| `PLUGIN_STRUCTURE_WARNINGS`        | The structure of the plugin is not valid.                                           |
| `MISSING_DEPENDENCIES`             | Plugin has some dependencies missing.                                               |
| `INVALID_PLUGIN`                   | Provided plugin artifact is not valid.                                              |
| `NOT_DYNAMIC`                      | Plugin probably cannot be enabled or disabled without IDE restart                   |
| `ALL`                              | Contains all possible options.                                                      |
| `NONE`                             | Contains no option.                                                                 |

See also:
- [Extension: `intellijPlatform.pluginVerification.failureLevel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-failureLevel)
- [Tasks: `verifyPlugin.failureLevel`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-failureLevel)
- [Overview of API Status](verifying_plugin_compatibility.md#ide-support)


## `IntelliJPlatformType`
{#IntelliJPlatformType}

[`IntelliJPlatformType`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/IntelliJPlatformType.kt)

Describes all IntelliJ Platform types available to be used for plugin development, dependency resolution, and plugin verification.

Each entry is composed of a product code and coordinates used for dependency and binary release resolution.

| Name                    | Code   | Artifact Coordinates                               | Binary release |
|-------------------------|--------|----------------------------------------------------|:--------------:|
| `AndroidStudio`         | `AI`   | -                                                  |    &check;     |
| `CLion`                 | `CL`   | `com.jetbrains.intellij.clion:clion`               |    &check;     |
| `DataGrip`              | `DB`   | -                                                  |    &check;     |
| `DataSpell`             | `DS`   | -                                                  |    &check;     |
| `FleetBackend`          | `FLIJ` | `com.jetbrains.intellij.fleetBackend:fleetBackend` |                |
| `Gateway`               | `GW`   | `com.jetbrains.intellij.gateway:gateway`           |    &check;     |
| `GoLand`                | `GO`   | `com.jetbrains.intellij.goland:goland`             |    &check;     |
| `IntellijIdeaCommunity` | `IC`   | `com.jetbrains.intellij.idea:ideaIC`               |    &check;     |
| `IntellijIdeaUltimate`  | `IU`   | `com.jetbrains.intellij.idea:ideaIU`               |    &check;     |
| `MPS`                   | `MPS`  | -                                                  |    &check;     |
| `PhpStorm`              | `PS`   | `com.jetbrains.intellij.phpstorm:phpstorm`         |    &check;     |
| `PyCharmProfessional`   | `PY`   | `com.jetbrains.intellij.pycharm:pycharmPY`         |    &check;     |
| `PyCharmCommunity`      | `PC`   | `com.jetbrains.intellij.pycharm:pycharmPC`         |    &check;     |
| `Rider`                 | `RD`   | `com.jetbrains.intellij.rider:riderRD`             |    &check;     |
| `RubyMine`              | `RM`   | -                                                  |    &check;     |
| `RustRover`             | `RR`   | `com.jetbrains.intellij.rustrover:RustRover`       |    &check;     |
| `WebStorm`              | `WS`   | `com.jetbrains.intellij.webstorm:webstorm`         |    &check;     |


## `PluginBean`
{#PluginBean}

[`PluginBean`](%gh-pv%/intellij-plugin-structure/structure-intellij/src/main/java/com/jetbrains/plugin/structure/intellij/beans/PluginBean.java)

Describes the content of the <path>plugin.xml</path> file.

See also:
- [](plugin_configuration_file.md)

## `ProductInfo`
{#ProductInfo}

[`ProductInfo`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/models/ProductInfo.kt)

Represents information about the IntelliJ Platform product.

The information is retrieved from the <path>product-info.json</path> file in the IntelliJ Platform directory.
See also the corresponding [`product-info.schema.json`](%gh-ic%/platform/buildData/resources/product-info.schema.json).

| Name                | Description                                                              |
|---------------------|--------------------------------------------------------------------------|
| `name`              | The product's name, like "IntelliJ IDEA".                                |
| `version`           | The marketing version of the product, like "2023.2".                     |
| `versionSuffix`     | The suffix of the version, like "EAP".                                   |
| `buildNumber`       | The build number of the product, like "232.8660.185".                    |
| `productCode`       | The product code, like "IU".                                             |
| `dataDirectoryName` | The directory name of the product data.                                  |
| `svgIconPath`       | The path to the SVG icon of the product.                                 |
| `productVendor`     | The vendor of the product.                                               |
| `launch`            | The list of OS- and arch-specific launch configurations for the product. |
| `customProperties`  | The list of custom properties of the product.                            |
| `bundledPlugins`    | The list of bundled plugins provided with the current release.           |
| `fileExtensions`    | The list of file extensions associated with the product.                 |
| `modules`           | The list of modules of the product.                                      |
| `layout`            | The modules and plugins dependencies mapping.                            |

### `validateSupportedVersion()`
{#ProductInfo-validateSupportedVersion}

Validates that the resolved IntelliJ Platform is supported by checking against the minimal supported IntelliJ Platform version.

If the provided version is lower, an `IllegalArgumentException` is thrown with an appropriate message.

{type="narrow"}
Throws
: `IllegalArgumentException`


## `ProductRelease.Channel`
{#ProductRelease-Channel}

[`ProductRelease.Channel`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/models/ProductRelease.kt)

List of available channels used by [JetBrains IDEs](intellij_platform.md#ides-based-on-the-intellij-platform)
and [Android Studio](android_studio.md) for describing binary releases.

| Name        | JetBrains IDEs | Android Studio  |
|-------------|:--------------:|:---------------:|
| `RELEASE`   |    &check;     |     &check;     |
| `EAP`       |    &check;     |                 |
| `MILESTONE` |                |     &check;     |
| `BETA`      |                |     &check;     |
| `CANARY`    |                |     &check;     |
| `PATCH`     |                |     &check;     |
| `RC`        |                |     &check;     |
| `PREVIEW`   |                |     &check;     |

See also:
- [Extension: `intellijPlatform.pluginVerification.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides)
- [Tasks: `printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases)


## `ProductReleasesValueSource.FilterParameters`
{#ProductReleasesValueSource-FilterParameters}

[`ProductReleasesValueSource.FilterParameters`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/providers/ProductReleasesValueSource.kt)

Interface that provides a clear way to filter binary product releases for IntelliJ Plugin Verifier.

| Name         | Description                                                                                          |
|--------------|------------------------------------------------------------------------------------------------------|
| `sinceBuild` | Build number from which the binary IDE releases will be matched.                                     |
| `untilBuild` | Build number until which the binary IDE releases will be matched.                                    |
| `types`      | A list of [`IntelliJPlatformType`](#IntelliJPlatformType) types to match.                            |
| `channels`   | A list of [`ProductRelease.Channel`](#ProductRelease-Channel) types of binary releases to search in. |

See also:
- [Extension: `intellijPlatform.pluginVerification.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides)
- [Tasks: `printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases)
- [Gradle Properties: `productsReleasesAndroidStudioUrl`](tools_intellij_platform_gradle_plugin_gradle_properties.md#productsReleasesAndroidStudioUrl)
- [Gradle Properties: `productsReleasesJetBrainsIdesUrl`](tools_intellij_platform_gradle_plugin_gradle_properties.md#productsReleasesJetBrainsIdesUrl)


## `SplitModeAware.SplitModeTarget`
{#SplitModeAware-SplitModeTarget}

[`SplitModeAware.SplitModeTarget`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SplitModeAware.kt)

Describes a part of the product where the developed plugin can be installed when running in _splitMode_ handled by [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware).

| Name       | Description                                       |
|------------|---------------------------------------------------|
| `BACKEND`  | Install plugin in the backed IDE.                 |
| `FRONTEND` | Install plugin in the frontend IDE.               |
| `BOTH`     | Install plugin in both backend and frontend IDEs. |


## `Subsystems`
{#Subsystems}

[`VerifyPluginTask.Subsystems`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

Specify which subsystems of the IDE should be checked by the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name              | Description                                  |
|-------------------|----------------------------------------------|
| `ALL`             | Verify all code.                             |
| `ANDROID_ONLY`    | Verify only code related to Android support. |
| `WITHOUT_ANDROID` | Exclude problems related to Android support. |

See also:
- [Extension: `intellijPlatform.pluginVerification.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-subsystemsToCheck)
- [Tasks: `verifyPlugin.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-subsystemsToCheck)


## `TestFrameworkType`
{#TestFrameworkType}

[`TestFrameworkType`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/TestFrameworkType.kt)

Allows for adding `test-framework` testing library variants.
See [Dependencies Extension: Testing](tools_intellij_platform_gradle_plugin_dependencies_extension.md#testing).

### Platform Test Frameworks
{#TestFrameworkType-Platform}

Generic test frameworks for the IntelliJ Platform.

| Name       | Coordinates                                                                                                                                                                                                                                                                                                                 |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Platform` | <p>`com.jetbrains.intellij.platform:test-framework`</p><p>**See "Known Issues" below**</p>                                                                                                                                                                                                                                  |
| `JUnit5`   | <p>`com.jetbrains.intellij.platform:test-framework-junit5`</p><p>**See "Known Issues" below**</p>                                                                                                                                                                                                                           |
| `Bundled`  | <p>Adds <path>[platformPath]/lib/testFramework.jar</path> file</p><p>**See "Using Bundled" below**</p>                                                                                                                                                                                                                      |
| `Metrics`  | `com.jetbrains.intellij.tools:ide-metrics-benchmark`<br/> `com.jetbrains.intellij.tools:ide-metrics-collector` <br/> `com.jetbrains.intellij.tools:ide-util-common`                                                                                                                                                         |
| `Starter`  | `com.jetbrains.intellij.tools:ide-starter-squashed` <br/> `com.jetbrains.intellij.tools:ide-starter-junit5` <br/> `com.jetbrains.intellij.tools:ide-starter-driver` <br/> `com.jetbrains.intellij.driver:driver-client` <br/> `com.jetbrains.intellij.driver:driver-sdk` <br/> `com.jetbrains.intellij.driver:driver-model` |

> Known issues related to `Platform` and `JUnit5` Test Frameworks:
> - [](tools_intellij_platform_gradle_plugin_faq.md#missing-opentest4j-dependency-in-test-framework)
> - [](tools_intellij_platform_gradle_plugin_faq.md#junit5-test-framework-refers-to-junit4)
>
{title="Known Issues"}

> The `Bundled` type should not be used unless it is necessary, like in the case of [Rider](rider.md), as its `test-framework` is not published as an artifact.
>
{style="warning" title="Using Bundled"}

### Plugin Test Frameworks
{#TestFrameworkType-Plugin}

Some plugins offer dedicated test frameworks, for example, `Plugin.Java` when using Java/JVM-related functionality.

| Name                | Coordinates                                                   | Reference       |
|---------------------|---------------------------------------------------------------|-----------------|
| `Plugin.Go`         | `com.jetbrains.intellij.go:go-test-framework`                 | [](goland.md)   |
| `Plugin.JavaScript` | `com.jetbrains.intellij.javascript:javascript-test-framework` | [](webstorm.md) |
| `Plugin.Java`       | `com.jetbrains.intellij.java:java-test-framework`             | [](idea.md)     |
| `Plugin.Maven`      | `com.jetbrains.intellij.maven:maven-test-framework`           |                 |
| `Plugin.ReSharper`  | `com.jetbrains.intellij.resharper:resharper-test-framework`   | [](rider.md)    |
| `Plugin.Ruby`       | `com.jetbrains.intellij.idea:ruby-test-framework`             | [](rubymine.md) |

## `VerificationReportsFormats`
{#VerificationReportsFormats}

[`VerifyPluginTask.VerificationReportsFormats`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

Enum class describing the type of the results produced by the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name       | Description                    |
|------------|--------------------------------|
| `PLAIN`    | Plain text file.               |
| `HTML`     | HTML formatted output file.    |
| `MARKDOWN` | Markdown file.                 |
| `ALL`      | Contains all possible options. |
| `NONE`     | Contains no options.           |

See also:
- [Extension: `intellijPlatform.pluginVerification.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-verificationReportsFormats)
- [Tasks: `verifyPlugin.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-verificationReportsFormats)


<include from="snippets.topic" element-id="missingContent"/>
