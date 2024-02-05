<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Types

<link-summary>IntelliJ Platform Gradle Plugin data types, enums, and constants.</link-summary>

## FailureLevel
{#FailureLevel}

```
org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask.FailureLevel
```

Enum class describing the failure level of the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name                               | Description                                                                         |
|------------------------------------|-------------------------------------------------------------------------------------|
| `COMPATIBILITY_WARNINGS`           | Compatibility warnings detected against the specified IDE version.                  |
| `COMPATIBILITY_PROBLEMS`           | Compatibility problems detected against the specified IDE version.                  |
| `DEPRECATED_API_USAGES`            | Plugin uses API marked as deprecated (`@Deprecated`).                               |
| `SCHEDULED_FOR_REMOVAL_API_USAGES` | Plugin uses API marked as scheduled for removal (`ApiStatus.@ScheduledForRemoval`). |
| `EXPERIMENTAL_API_USAGES`          | Plugin uses API marked as experimental (`ApiStatus.@Experimental`).                 |
| `INTERNAL_API_USAGES`              | Plugin uses API marked as internal (`ApiStatus.@Internal`).                         |
| `OVERRIDE_ONLY_API_USAGES`         | Override-only API is used incorrectly (`ApiStatus.@OverrideOnly`).                  |
| `NON_EXTENDABLE_API_USAGES`        | Non-extendable API is used incorrectly (`ApiStatus.@NonExtendable`).                |
| `PLUGIN_STRUCTURE_WARNINGS`        | The structure of the plugin is not valid.                                           |
| `MISSING_DEPENDENCIES`             | Plugin has some dependencies missing.                                               |
| `INVALID_PLUGIN`                   | Provided plugin artifact is not valid.                                              |
| `NOT_DYNAMIC`                      | Plugin probably cannot be enabled or disabled without IDE restart                   |
| `ALL`                              | Contains all possible options.                                                      |
| `NONE`                             | Contains no option.                                                                 |

See also:
- [Extension: `intellijPlatform.verifyPlugin.failureLevel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin-failureLevel)
- [Tasks: `verifyPlugin.failureLevel`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-failureLevel)


## IntelliJPlatformType
{#IntelliJPlatformType}

```
org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
```

Describes all IntelliJ Platform types available to be used for plugin development, dependency resolution, and plugin verification.

Each entry is composed of a product code name and coordinates used for dependency and binary release resolution.

| Name                    | Code   | Coordinates                                        |
|-------------------------|--------|----------------------------------------------------|
| `AndroidStudio`         | `AI`   | `com.google.android.studio:studio`                 |
| `CLion`                 | `CL`   | `com.jetbrains.intellij.clion:clion`               |
| `Fleet`                 | `FLIJ` | `com.jetbrains.intellij.fleetBackend:fleetBackend` |
| `Gateway`               | `GW`   | `com.jetbrains.intellij.gateway:gateway`           |
| `GoLand`                | `GO`   | `com.jetbrains.intellij.goland:goland`             |
| `IntellijIdeaCommunity` | `IC`   | `com.jetbrains.intellij.idea:ideaIC`               |
| `IntellijIdeaUltimate`  | `IU`   | `com.jetbrains.intellij.idea:ideaIU`               |
| `PhpStorm`              | `PS`   | `com.jetbrains.intellij.phpstorm:phpstorm`         |
| `PyCharmProfessional`   | `PY`   | `com.jetbrains.intellij.pycharm:pycharmPY`         |
| `PyCharmCommunity`      | `PC`   | `com.jetbrains.intellij.pycharm:pycharmPC`         |
| `Rider`                 | `RD`   | `com.jetbrains.intellij.rider:riderRD`             |
| `RustRover`             | `RR`   | `com.jetbrains.intellij.rustrover:RustRover`       |
| `Writerside`            | `WRS`  | `com.jetbrains.intellij.idea:writerside`           |


## ProductInfo
{#ProductInfo}

Represents information about the IntelliJ Platform product.

The information is retrieved from the <path>product-info.json</path> file in the IntelliJ Platform directory.

| Name              | Description                                                              |
|-------------------|--------------------------------------------------------------------------|
| name              | The product's name, like "IntelliJ IDEA".                                |
| version           | The marketing version of the product, like "2023.2".                     |
| versionSuffix     | The suffix of the version, like "EAP".                                   |
| buildNumber       | The build number of the product, like "232.8660.185".                    |
| productCode       | The product code, like "IU".                                             |
| dataDirectoryName | The directory name of the product data.                                  |
| svgIconPath       | The path to the SVG icon of the product.                                 |
| productVendor     | The vendor of the product.                                               |
| launch            | The list of OS- and arch-specific launch configurations for the product. |
| customProperties  | The list of custom properties of the product.                            |
| bundledPlugins    | The list of bundled plugins provided with the current release.           |
| fileExtensions    | The list of file extensions associated with the product.                 |
| modules           | The list of modules of the product.                                      |

### assertSupportedVersion()
{#ProductInfo-assertSupportedVersion}

Asserts that the resolved IntelliJ Platform is supported by checking against the minimal supported IntelliJ Platform version.

If the provided version is lower, an `IllegalArgumentException` is thrown with an appropriate message.

{style="narrow"}
Throws
: `IllegalArgumentException`


## ProductRelease.Channel
{#ProductRelease-Channel}

```
org.jetbrains.intellij.platform.gradle.model.ProductRelease
```

List of available channels used by JetBrains IDEs and Android Studio for describing binary releases.

| Name        | JetBrains IDEs | Android Studio |
|-------------|:--------------:|:--------------:|
| `EAP`       |       X        |                |
| `MILESTONE` |                |       X        |
| `BETA`      |                |       X        |
| `RELEASE`   |       X        |       X        |
| `CANARY`    |                |       X        |
| `PATCH`     |                |       X        |
| `RC`        |                |       X        |
| `PREVIEW`   |                |       X        |

See also:
- [Extension: `intellijPlatform.verifyPlugin.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin-ides)
- [Tasks: `printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases)


## ProductReleasesValueSource.FilterParameters
{#ProductReleasesValueSource-FilterParameters}

```
org.jetbrains.intellij.platform.gradle.provider.ProductReleasesValueSource
```

Interface that provides a clear way to filter binary product releases for the purposes of IntelliJ Plugin Verifier.

| Name         | Description                                                                                          |
|--------------|------------------------------------------------------------------------------------------------------|
| `sinceBuild` | Build number from which the binary IDE releases will be matched.                                     |
| `untilBuild` | Build number until which the binary IDE releases will be matched.                                    |
| `types`      | A list of [`IntelliJPlatformType`](#IntelliJPlatformType) types to match.                            |
| `channels`   | A list of [`ProductRelease.Channel`](#ProductRelease-Channel) types of binary releases to search in. |

See also:
- [Extension: `intellijPlatform.verifyPlugin.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin-ides)
- [Tasks: `printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases)


## Subsystems
{#Subsystems}

```
org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask.Subsystems
```

Specifies which subsystems of IDE should be checked by the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name              | Description                                  |
|-------------------|----------------------------------------------|
| `ALL`             | Verify all code.                             |
| `ANDROID_ONLY`    | Verify only code related to Android support. |
| `WITHOUT_ANDROID` | Exclude problems related to Android support. |

See also:
- [Extension: `intellijPlatform.verifyPlugin.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin-subsystemsToCheck)
- [Tasks: `verifyPlugin.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-subsystemsToCheck)


## VerificationReportsFormats
{#VerificationReportsFormats}

```
org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask.VerificationReportsFormats
```

Enum class describing the type of the results produced by the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name       | Description                    |
|------------|--------------------------------|
| `PLAIN`    | Plain text file.               |
| `HTML`     | HTML formatted output file.    |
| `MARKDOWN` | Markdown file.                 |
| `ALL`      | Contains all possible options. |
| `NONE`     | Contains no option.            |

See also:
- [Extension: `intellijPlatform.verifyPlugin.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-verifyPlugin-verificationReportsFormats)
- [Tasks: `verifyPlugin.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-verificationReportsFormats)


<include from="snippets.md" element-id="missingContent"/>
