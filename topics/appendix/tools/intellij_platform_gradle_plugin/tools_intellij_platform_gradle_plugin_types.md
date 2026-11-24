<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Types

<link-summary>IntelliJ Platform Gradle Plugin data types, enums, and constants.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

This page lists types used to configure dependencies, extensions, and [](tools_intellij_platform_gradle_plugin_tasks.md).

## `Coordinates`
{#Coordinates}

[`Coordinates`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/models/Coordinates.kt)

Represents Maven coordinates without a version.
Used by [`platformDependency()` and `testPlatformDependency()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#tools).

| Property     | Type     | Description                         |
|--------------|----------|-------------------------------------|
| `groupId`    | `String` | Maven group ID.                    |
| `artifactId` | `String` | Maven artifact ID.                 |

Construct a value with `Coordinates(groupId, artifactId)`.
Its `toString()` representation is `groupId:artifactId`.

## `CustomPluginRepositoryType`
{#CustomPluginRepositoryType}

[`CustomPluginRepositoryType`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/CustomPluginRepositoryType.kt)

A Kotlin type alias for `com.jetbrains.plugin.structure.intellij.repository.CustomPluginRepositoryListingType`,
used to select the listing format for a [custom plugin repository](tools_intellij_platform_gradle_plugin_repositories_extension.md#plugin-repositories).
Listing types include `SIMPLE` and `PLUGIN_REPOSITORY`.

The alias does not define a separate Java class.
In Groovy, import and use `com.jetbrains.plugin.structure.intellij.repository.CustomPluginRepositoryListingType` directly instead of the Kotlin alias.

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
| `NOT_DYNAMIC`                      | Plugin probably cannot be enabled or disabled without IDE restart.                  |
| `ALL`                              | EnumSet constant containing all possible options.                                   |
| `NONE`                             | EnumSet constant containing no options.                                             |

`NOT_DYNAMIC` is detected only from the plain console output and can't be combined with [`verifyPlugin.teamCityOutputFormat = true`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-teamCityOutputFormat) (also when selected via `ALL`); the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task fails fast if both are set.

See also:
- [Extension: `intellijPlatform.pluginVerification.failureLevel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-failureLevel)
- [Tasks: `verifyPlugin.failureLevel`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-failureLevel)
- [Overview of API Status](verifying_plugin_compatibility.md#ide-support)


## `IntelliJPlatformDependencyConfiguration`
{#IntelliJPlatformDependencyConfiguration}

[`IntelliJPlatformDependencyConfiguration`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/extensions/IntelliJPlatformDependencyConfiguration.kt)

Configures a target IntelliJ Platform dependency.
It is accepted by `create(configuration)` and passed to the target-platform helpers' configuration callbacks.
Its `type`, `version`, `productMode`, `useInstaller`, and `useCache` properties support direct-value and provider assignments.
See [Dependency Configuration Parameters](tools_intellij_platform_gradle_plugin_dependencies_extension.md#dependency-configuration-parameters) for their types and defaults.

## `IntelliJPlatformType`
{#IntelliJPlatformType}

[`IntelliJPlatformType`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/IntelliJPlatformType.kt)

Describes all IntelliJ Platform types available to be used for plugin development, dependency resolution, and plugin verification.

Each entry exposes `code: String`, `maven: Coordinates?`, and `installer: Coordinates?`.
A missing coordinate means that distribution format is not available for the product.
The installer column below indicates whether installer coordinates are declared, not whether every version is available.

| Name                    | Code   | Maven Coordinates                                  | Installer coordinates |
|-------------------------|--------|----------------------------------------------------|:--------------:|
| `AndroidStudio`         | `AI`   | -                                                  |    &check;     |
| `Aqua`                  | `QA`   | -                                                  |    &check;     |
| `CLion`                 | `CL`   | `com.jetbrains.intellij.clion:clion`               |    &check;     |
| `DataGrip`              | `DB`   | -                                                  |    &check;     |
| `DataSpell`             | `DS`   | -                                                  |    &check;     |
| `FleetBackend`          | `FLIJ` | `com.jetbrains.intellij.fleetBackend:fleetBackend` |                |
| `Gateway`               | `GW`   | `com.jetbrains.intellij.gateway:gateway`           |    &check;     |
| `GoLand`                | `GO`   | `com.jetbrains.intellij.goland:goland`             |    &check;     |
| `IntellijIdeaCommunity` | `IC`   | `com.jetbrains.intellij.idea:ideaIC`               |    &check;     |
| `IntellijIdeaUltimate`  | `IU`   | `com.jetbrains.intellij.idea:ideaIU`               |    &check;     |
| `IntellijIdea`          | `IU`   | `com.jetbrains.intellij.idea:idea`                 |    &check;     |
| `JetBrainsClient`       | `JBC`  | -                                                  |    &check;     |
| `MPS`                   | `MPS`  | -                                                  |    &check;     |
| `PhpStorm`              | `PS`   | `com.jetbrains.intellij.phpstorm:phpstorm`         |    &check;     |
| `PyCharm`               | `PY`   | `com.jetbrains.intellij.pycharm:pycharm`           |    &check;     |
| `PyCharmProfessional`   | `PY`   | `com.jetbrains.intellij.pycharm:pycharmPY`         |    &check;     |
| `PyCharmCommunity`      | `PC`   | `com.jetbrains.intellij.pycharm:pycharmPC`         |    &check;     |
| `Rider`                 | `RD`   | `com.jetbrains.intellij.rider:riderRD`             |    &check;     |
| `RubyMine`              | `RM`   | `com.jetbrains.intellij.rubymine:rubymine`         |    &check;     |
| `RustRover`             | `RR`   | `com.jetbrains.intellij.rustrover:RustRover`       |    &check;     |
| `WebStorm`              | `WS`   | `com.jetbrains.intellij.webstorm:webstorm`         |    &check;     |
| `Writerside`            | `WRS`  | `com.jetbrains.intellij.idea:writerside`           |    &check;     |

The unified `IntellijIdea` and `PyCharm` types are available since `2.9.0` and `2.10.0`, respectively.

Notes:
- `Aqua` (`QA`) and `Writerside` (`WRS`) are deprecated and no longer available as target IntelliJ Platform products.
- `IntellijIdeaCommunity` (`IC`) and `PyCharmCommunity` (`PC`) are available only for versions earlier than 2025.3 (build 253). For 2025.3+ use `IntellijIdea` (`IU`) and `PyCharm` (`PY`).
- `IntellijIdeaUltimate` (`IU`) and `PyCharmProfessional` (`PY`) are legacy types for versions earlier than 2025.3 (build 253). For 2025.3+ use `IntellijIdea` (`IU`) and `PyCharm` (`PY`).

### Product Code Conversion
{#IntelliJPlatformType-conversion}

`IntelliJPlatformType.fromCode(code)` converts a product code to an enum entry and throws `IllegalArgumentException` for an unknown code.
For the shared codes `IU` and `PY`, it returns `IntellijIdea` and `PyCharm`, respectively.

`IntelliJPlatformType.fromCode(code, version)` also considers the marketing version or build number.
For versions before 2025.3 (build 253), it maps `IU` to `IntellijIdeaUltimate` and `PY` to `PyCharmProfessional`.
For newer versions, it returns the unified product types.
The target-platform dependency helpers use this version-aware conversion for both string codes and enum arguments, except when the version is `"latest"`.

The public `toIntelliJPlatformType(version)` extension accepts a `String` or `IntelliJPlatformType` value.
`IntelliJPlatformType.validateVersion(version)` rejects Community product versions at or above the unified-product boundary.
`IntelliJPlatformType.toString()` returns the product code, not the enum entry name.

## `ProductMode`
{#ProductMode}

[`ProductMode`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/ProductMode.kt)

Describes the requested product mode, configured on the target platform dependency.

| Name       | Description                                                                                                                                                                                                                          |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `MONOLITH` | Indicates that this process performs all necessary tasks to provide smart features itself. This is the default mode for all IDEs.                                                                                                    |
| `FRONTEND` | Indicates that this process doesn't perform heavy tasks like code analysis, and takes necessary information from another process. Currently used by JetBrains Client connected to a remote development host or Code With Me session. |
| `BACKEND`  | An IDE running as a remote development host.                                                                                                                                                                                        |

`FRONTEND` resolves a JetBrains Client installer instead of the requested product's IDE distribution.
`BACKEND` retains the requested product's distribution.
Both non-`MONOLITH` modes require a managed local IDE path, even when the dependency's `useCache` property is `false`.

See also:
- [Dependencies Extension: Dependency Configuration Parameters](tools_intellij_platform_gradle_plugin_dependencies_extension.md#dependency-configuration-parameters)
- [Types: SplitModeAware.PluginInstallationTarget](tools_intellij_platform_gradle_plugin_types.md#SplitModeAware-PluginInstallationTarget)

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
| `envVarBaseName`    | Base name used for product-specific environment variables.               |
| `dataDirectoryName` | The directory name of the product data.                                  |
| `minRequiredJavaVersion` | The minimum Java version required by the product, when specified.  |
| `svgIconPath`       | The path to the SVG icon of the product.                                 |
| `productVendor`     | The vendor of the product.                                               |
| `launch`            | The list of OS- and arch-specific launch configurations for the product. |
| `customProperties`  | The list of custom properties of the product.                            |
| `bundledPlugins`    | The list of bundled plugins provided with the current release.           |
| `fileExtensions`    | The list of file extensions associated with the product.                 |
| `modules`           | The list of modules of the product.                                      |
| `layout`            | The modules and plugins dependencies mapping.                            |

Public Kotlin extension properties also expose `fullVersion` (`productCode-buildNumber`), the version-aware `type` (`IntelliJPlatformType`),
and `frontendSplitRootModule` (the product-specific frontend split module ID).

Launch entries use `ProductInfo.Launch.OS` values `Linux`, `Windows`, and `macOS`.
Layout entries use `ProductInfo.LayoutItemKind` values `plugin`, `pluginAlias`, `productModuleV2`, and `moduleV2`.

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

| Name        | Description                |
|-------------|----------------------------|
| `RELEASE`   | Stable release.            |
| `EAP`       | Early Access Program.      |
| `MILESTONE` | Milestone release.         |
| `BETA`      | Beta release.              |
| `CANARY`    | Canary release.            |
| `PATCH`     | Patch release.             |
| `RC`        | Release candidate.         |
| `PREVIEW`   | Preview release.           |

Channel availability depends on the product's release metadata.

See also:
- [Extension: `intellijPlatform.pluginVerification.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides)
- [Tasks: `printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases)


## `ProductReleasesFilterParameters`
{#ProductReleasesFilterParameters}

[`ProductReleasesFilterParameters`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/providers/ProductReleasesFilterParameters.kt)

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


## `RequestedIntelliJPlatform`
{#RequestedIntelliJPlatform}

[`RequestedIntelliJPlatform`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/services/RequestedIntelliJPlatformsService.kt)

Represents a requested IntelliJ Platform build with its type, version, and installation/cache preferences.
Used internally by the Gradle plugin to propagate platform settings across configurations for development, dependency resolution, and plugin verification.

| Name           | Description                                                                       |
|----------------|-----------------------------------------------------------------------------------|
| `type`         | IntelliJ Platform type. See [`IntelliJPlatformType`](#IntelliJPlatformType).      |
| `version`      | Version of the IntelliJ Platform to use (for example, "2024.2", "241.15989.150"). |
| `useInstaller` | Whether the installer distribution of the platform should be used.                |
| `useCache`     | Whether a custom cache directory should be used when resolving the platform.      |
| `productMode`  | Desired product mode. See [`ProductMode`](#ProductMode).                          |
| `isNightly`    | Whether the request is for a nightly platform build. Used in [dependency version selection](tools_intellij_platform_gradle_plugin_dependencies_extension.md#dependency-version-selection). |

## `SplitModeAware.PluginInstallationTarget` (formerly `SplitModeTarget`)
{#SplitModeAware-PluginInstallationTarget}

[`SplitModeAware.PluginInstallationTarget`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SplitModeAware.kt)

Describes a part of the product where the developed plugin can be installed when running in _splitMode_ handled by [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware).

| Name       | Description                                       |
|------------|---------------------------------------------------|
| `BACKEND`  | Install plugin in the backend IDE.                |
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

`TestFrameworkType` is a sealed class whose variants are Kotlin singleton objects, not enum entries.
Each variant exposes its artifact `coordinates`.
In Groovy, access a variant through its `INSTANCE` field, for example, `TestFrameworkType.JUnit5CodeInsight.INSTANCE`.

### Platform Test Frameworks
{#TestFrameworkType-Platform}

Generic test frameworks for the IntelliJ Platform.

| Name       | Coordinates                                                                                                                                                                                                                                                                                                                 |
|------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Platform` | <p>`com.jetbrains.intellij.platform:test-framework`</p><p>**See "Known Issues" below**</p>                                                                                                                                                                                                                                  |
| `JUnit5`   | <p>`com.jetbrains.intellij.platform:test-framework-junit5`</p><p>**See "Known Issues" below**</p>                                                                                                                                                                                                                           |
| `JUnit5CodeInsight` | `com.jetbrains.intellij.platform:test-framework-junit5-code-insight`                                                                                                                                                                                                                                       |
| `JUnit5Eel` | `com.jetbrains.intellij.platform:test-framework-junit5-eel`                                                                                                                                                                                                                                                        |
| `JUnit5Highlighting` | `com.jetbrains.intellij.platform:test-framework-junit5-highlighting`                                                                                                                                                                                                                                       |
| `JUnit5ProjectStructure` | `com.jetbrains.intellij.platform:test-framework-junit5-project-structure`                                                                                                                                                                                                                                 |
| `Bundled`  | <p>Adds <path>[platformPath]/lib/testFramework.jar</path> file</p><p>**See "Using Bundled" below**</p>                                                                                                                                                                                                                      |
| `Metrics`  | `com.jetbrains.intellij.tools:ide-metrics-benchmark`<br/> `com.jetbrains.intellij.tools:ide-metrics-collector` <br/> `com.jetbrains.intellij.tools:ide-util-common`                                                                                                                                                         |
| `Starter`  | `com.jetbrains.intellij.tools:ide-starter-squashed` <br/> `com.jetbrains.intellij.tools:ide-starter-junit5` <br/> `com.jetbrains.intellij.tools:ide-starter-driver` <br/> `com.jetbrains.intellij.driver:driver-client` <br/> `com.jetbrains.intellij.driver:driver-sdk` <br/> `com.jetbrains.intellij.driver:driver-model` |

`JUnit5CodeInsight` is available since `2.19.0`.
`JUnit5Eel`, `JUnit5Highlighting`, and `JUnit5ProjectStructure` are available since `2.20.0`.

Since `2.18.0`, `Starter` also adds product-specific artifacts for target IDE builds starting at `262` (2026.2) when used through `testFramework()`.
These use the group `com.jetbrains.intellij.tools` and artifact IDs with the `ide-starter-product-` prefix and the following suffixes:

- `idea-ultimate`, `idea-community`, `pycharm`, `pycharm-community`
- `goland`, `rider`, `webstorm`, `phpstorm`, `rubymine`
- `clion`, `datagrip`, `rustrover`, `android-studio`, `gateway`

For earlier builds, these product-specific artifacts are omitted; the six artifacts listed in the table are retained.

> Known issues related to `Platform` and `JUnit5` Test Frameworks:
> - [](tools_intellij_platform_gradle_plugin_faq.md#missing-opentest4j-dependency-in-test-framework)
> - [](tools_intellij_platform_gradle_plugin_faq.md#junit5-test-framework-refers-to-junit4)
>
{title="Known Issues"}

> Use `Bundled` only when tests specifically require the <path>lib/testFramework.jar</path> shipped with the target IDE and no published `TestFrameworkType` variant is suitable.
> Prefer `Platform` or one of the published `Plugin.*` variants when available.
>
{style="warning" title="Using Bundled"}

### UI Utility Test Frameworks
{#TestFrameworkType-UiUtil}

Available since `2.17.0`.

Utility modules for tests that use the UI Driver framework.
These APIs may be unstable and can change without notice.

| Name              | Coordinates                                                     |
|-------------------|-----------------------------------------------------------------|
| `UiUtil.Debugger` | `com.jetbrains.intellij.debugger:debugger-ui-test-util`         |
| `UiUtil.Jupyter`  | `com.jetbrains.intellij.jupyter:jupyter-ui-test-util`           |

### Plugin Test Frameworks
{#TestFrameworkType-Plugin}

Some plugins offer dedicated test frameworks, for example, `Plugin.Java` when using Java/JVM-related functionality.
`Plugin.Kotlin` is available since `2.17.0`.

| Name                    | Coordinates                                                               | Reference       |
|-------------------------|---------------------------------------------------------------------------|-----------------|
| `Plugin.CLion`          | `com.jetbrains.intellij.clion:clion-merged-test-framework`                | [](clion.md)    |
| `Plugin.CSS`            | `com.jetbrains.intellij.css:css-test-framework`                           | [](webstorm.md) |
| `Plugin.Debugger`       | `com.jetbrains.intellij.platform:debugger-test-framework`                 |                 |
| `Plugin.Execution`      | `com.jetbrains.intellij.platform:execution-test-framework`                |                 |
| `Plugin.ExternalSystem` | `com.jetbrains.intellij.platform:external-system-test-framework`          |                 |
| `Plugin.Go`             | `com.jetbrains.intellij.go:go-test-framework`                             | [](goland.md)   |
| `Plugin.IJent`          | `com.jetbrains.intellij.platform:ijent-test-framework`                    |                 |
| `Plugin.Ruby`           | `com.jetbrains.intellij.idea:ruby-test-framework`                         | [](rubymine.md) |
| `Plugin.Java`           | `com.jetbrains.intellij.java:java-test-framework`                         | [](idea.md)     |
| `Plugin.JavaScript`     | `com.jetbrains.intellij.javascript:javascript-test-framework`             | [](webstorm.md) |
| `Plugin.Jupyter`        | `com.jetbrains.intellij.jupyter:jupyter-test-framework`                   |                 |
| `Plugin.Kotlin`         | `com.jetbrains.intellij.kotlin:kotlin-base-test-framework`                |                 |
| `Plugin.LSP`            | `com.jetbrains.intellij.platform:lsp-test-framework`                      |                 |
| `Plugin.Maven`          | `com.jetbrains.intellij.maven:maven-test-framework`                       |                 |
| `Plugin.MLCompletion`   | `com.jetbrains.intellij.ml:ml-llm-completion-test-framework`              |                 |
| `Plugin.MLCompletionCloud` | `com.jetbrains.intellij.ml:ml-llm-completion-cloud-test-framework`     |                 |
| `Plugin.MLNextEdits`    | `com.jetbrains.intellij.ml:ml-llm-next-edits-test-framework`              |                 |
| `Plugin.NavBar`         | `com.jetbrains.intellij.platform:navbar-test-framework`                   |                 |
| `Plugin.Notebooks`      | `com.jetbrains.intellij.notebooks:notebooks-visualization-test-framework` |                 |
| `Plugin.PolySymbols`    | `com.jetbrains.intellij.platform:poly-symbols-test-framework`             |                 |
| `Plugin.Python`         | `com.jetbrains.intellij.python:python-community-test-framework`           | [](pycharm.md)  |
| `Plugin.Qodana`         | `com.jetbrains.intellij.qodana:qodana-test-framework`                     |                 |
| `Plugin.RDClient`       | `com.jetbrains.intellij.rd:rd-client-test-framework`                      |                 |
| `Plugin.ReSharper`      | `com.jetbrains.intellij.resharper:resharper-test-framework`               | [](rider.md)    |
| `Plugin.Rider`          | `com.jetbrains.intellij.rider:rider-test-framework`                       | [](rider.md)    |
| `Plugin.Statistics`     | `com.jetbrains.intellij.platform:statistics-test-framework`               |                 |
| `Plugin.UAST`           | `com.jetbrains.intellij.platform:uast-test-framework`                     |                 |
| `Plugin.VCS`            | `com.jetbrains.intellij.platform:vcs-test-framework`                      |                 |
| `Plugin.XML`            | `com.jetbrains.intellij.xml:xml-test-framework`                           |                 |
| `Plugin.WebSymbols`     | `com.jetbrains.intellij.platform:web-symbols-test-framework`              |                 |

## `Variant`
{#Variant}

[`Variant`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/Variant.kt)

A data class describing a native target with `os: String` and `arch: String` properties.
It is not an enum.
The public `org.jetbrains.intellij.platform.gradle.variants` list contains these six combinations:

| `os`      | `arch`              |
|-----------|---------------------|
| `linux`   | `x86_64`, `arm64` |
| `mac`     | `x86_64`, `arm64` |
| `windows` | `x86_64`, `arm64` |

Native target identifiers use `mac`, not `macOS`, and `arm64`, not `aarch64`.
See [`intellijPlatform.nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants) for configuring target-specific files.

## `VerificationReportsFormats`
{#VerificationReportsFormats}

[`VerifyPluginTask.VerificationReportsFormats`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

Enum class describing the type of the results produced by the IntelliJ Plugin Verifier CLI tool run with the [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

| Name       | Description                    |
|------------|--------------------------------|
| `PLAIN`    | Plain text file.               |
| `HTML`     | HTML formatted output file.    |
| `MARKDOWN` | Markdown file.                 |
| `ALL`      | EnumSet constant containing all possible options. |
| `NONE`     | EnumSet constant containing no options.           |

See also:
- [Extension: `intellijPlatform.pluginVerification.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-verificationReportsFormats)
- [Tasks: `verifyPlugin.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin-verificationReportsFormats)


<include from="snippets.topic" element-id="missingContent"/>
