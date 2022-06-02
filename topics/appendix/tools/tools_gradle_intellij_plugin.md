[//]: # (title: Gradle IntelliJ Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Gradle IntelliJ Plugin is a plugin for the Gradle build system to help configuring your environment for building and testing plugins for IntelliJ-based IDEs.

> Current Gradle IntelliJ Plugin version is [![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-intellij-plugin.svg?style=flat-square)](https://github.com/jetbrains/gradle-intellij-plugin/releases)
>
{type="note"}

This plugin allows you to build plugins for IntelliJ Platform using specified IntelliJ SDK and bundled or third-party plugins.

The plugin adds extra IntelliJ-specific dependencies, patches `processResources` tasks to fill some tags (name, version) in <path>[plugin.xml](plugin_configuration_file.md)</path> with appropriate values, patches compile tasks to instrument code with nullability assertions and forms classes made with IntelliJ GUI Designer and provides some build steps which might be helpful while developing plugins for IntelliJ platform.


## Usage
To enable this plugin in your Gradle-based project, add the following entry to the `plugins` section:

<tabs>
<tab title="Kotlin">

```kotlin
plugins {
    id("org.jetbrains.intellij") version "..."
}
```

</tab>
<tab title="Groovy">

```groovy
plugins {
    id "org.jetbrains.intellij" version "..."
}
```

</tab>
</tabs>

When upgrading to `1.x` version, please make sure to follow [migration guide](https://lp.jetbrains.com/gradle-intellij-plugin) to adjust your existing build script.

> Gradle JVM should be set to `Java 11` (see <path>Settings/Preferences | Build, Execution</path>, <path>Deployment | Build Tools | Gradle</path>)
>
{type="tip"}

> This project requires `Gradle 6.7` or newer, however it is recommended to use the latest Gradle available.
> Update it with:
> ```Bash
> ./gradlew wrapper --gradle-version=VERSION
> ```
>
{type="tip"}

### Snapshot Release
The Snapshot release is a pre-release version built nightly from the latest main branch.
To use it, it is required to point Gradle to the dedicated snapshot repository by adding an entry to the Gradle settings file.

> Current Gradle IntelliJ Plugin Snapshot version is [![GitHub Snapshot Release](https://img.shields.io/nexus/s/org.jetbrains.intellij/org.jetbrains.intellij.gradle.plugin?server=https://oss.sonatype.org)](https://github.com/jetbrains/gradle-intellij-plugin/releases)
>
{type="note"}

<tabs>
<tab title="Kotlin">

`build.gradle.kts`
```kotlin
plugins {
    id("org.jetbrains.intellij") version "..."
}
```

`settings.gradle.kts`
```kotlin
pluginManagement {
    repositories {
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        gradlePluginPortal()
    }
}
```
</tab>
<tab title="Groovy">

`build.gradle`
```groovy
plugins {
    id "org.jetbrains.intellij" version "..."
}
```

`settings.gradle`
```groovy
pluginManagement {
    repositories {
        maven {
            url 'https://oss.sonatype.org/content/repositories/snapshots/'
        }
        gradlePluginPortal()
    }
}
```

</tab>
</tabs>


## IntelliJ Extension
After the Gradle IntelliJ Plugin is applied, the `intellij` extension can be used to configure the plugin and common settings of the provided tasks.

It is mandatory to specify at least the `intellij.version` property.

**Example:**


<tabs>
<tab title="Kotlin">

```kotlin
intellij {
    version.set("2022.1.1")
    type.set("IU")
    plugins.set(listOf("com.jetbrains.php:221.5787.21"))
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
    version = "2022.1.1"
    type = "IU"
    plugins = ["com.jetbrains.php:221.5787.21"]
}
```

</tab>
</tabs>


### version
All available JetBrains IDEs versions can be found at [IntelliJ Artifacts](intellij_artifacts.md) page.

The version of the IntelliJ Platform IDE that will be used to build the plugin.
Please see [Plugin Compatibility](plugin_compatibility.md) topic in SDK docs for more details.

**Required**

**Type:** `String`

**Acceptable values:**
- version number: `2022.1.1` or `IC-2022.1.1`
- build number: `221.5080.210` or `IC-221.5080.210`
- snapshot: `221-EAP-SNAPSHOT` or `LATEST-EAP-SNAPSHOT`

> The _version number_ format is the most common option for specifying the version of the IntelliJ Platform.
> Other formats should be used only when your plugin relies on specific parts of the targeted IDE or early-adopting EAP releases.
>
{type="tip"}


### type
The type of the IntelliJ-based IDE distribution.
The type may also be specified as a prefix of the value for the `intellij.version` property instead.

**Type:** `String`

**Default value:** `IC`

**Acceptable values:**
- `IC` - [IntelliJ IDEA Community Edition](idea.md)
- `IU` - [IntelliJ IDEA Ultimate Edition](idea.md)
- `CL` - [CLion](clion.md)
- `PY` - [PyCharm Professional Edition](pycharm.md)
- `PC` - [PyCharm Community Edition](pycharm.md)
- `RD` - [Rider](rider.md)
- `GO` - [GoLand](goland.md)
- `JPS` - JPS-only
- `GW` - Gateway


### pluginName
The name of the generated ZIP archive distribution: `/build/distributions/PluginName-1.0.0.zip`.

**Type:** `String`

**Default value:** `${project.name}`


### localPath
The path to the locally installed IDE distribution that should be used to build the plugin.
Using the `localPath` allows to build the plugin using IDE that is not available in the [IntelliJ Platform Artifacts Repositories](intellij_artifacts.md).

**Type:** `String`

**Default value:** `null`

**Samples:**
- `C:\Users\user\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\211.7142.45`
- `/Applications/Android Studio 4.2 Preview.app/Contents`
- `/home/user/idea-IC-181.4445.78`

> `intellij.version` and `intellij.localPath` must not be specified at the same time.
>
{type="warning"}


### localSourcesPath
The path to local archive with IDE sources. Used for resolving source files of the locally installed IDE distribution when [`intellij.localPath`](#localpath) is specified.

**Type:** `String`

**Default value:** `null`


### plugins
The list of bundled IDE plugins and plugins from [JetBrains Marketplace](https://plugins.jetbrains.com) or configured [`intellij.pluginsRepositories`][#pluginsrepositories].

Please see [Plugin Dependencies](plugin_dependencies.md) for more details.

Notes:
- For plugins from [JetBrains Marketplace](https://plugins.jetbrains.com), use format `pluginId:version`.
- For bundled plugins, version should be omitted: e.g. `org.intellij.groovy`.
- For subprojects, use project reference `project(':subproject')`.
- If you need to refer plugin's classes from your project, you also have to define a dependency in your <path>[plugin.xml](plugin_configuration_file.md)</path> file.

**Type:** `List<Any>`

**Default value:** `[]`

**Acceptable values:**
- `org.plugin.id:version[@channel]` format, `String` type:
  - `org.intellij.plugins.markdown:8.5.0`
  - `org.intellij.scala:2017.2.638@nightly`
- `bundledPluginName` format, `String` type:
  - `android`
  - `Groovy`
- `project(...)` format, `Project` type:
  - `project(":projectName")`
  - `project(":plugin-subproject")`


### updateSinceUntilBuild
Defines if the <path>[plugin.xml](plugin_configuration_file.md)</path> should be patched with the values of `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` properties.

**Type:** `Boolean`

**Default value:** `true`


### sameSinceUntilBuild
Patches <path>[plugin.xml](plugin_configuration_file.md)</path> with the `patchPluginXml.untilBuild` with the value of `patchPluginXml.sinceBuild` used with `*` wildcard, like `sinceBuild.*`, e.g., `221.*`.

Notes:
- Useful for building plugins against EAP IDE builds.
- If `patchPluginXml.untilBuild` has a value set, then `intellij.sameSinceUntilBuild` is ignored.

**Type:** `Boolean`

**Default value:** `false`


### instrumentCode
Instrument Java classes with [nullability](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html) assertions and compile forms created by [IntelliJ GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html.

**Type:** `Boolean`

**Default value:** `true`


### sandboxDir
The path of [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) that is used for running IDE with developed plugin.

**Type:** `String`

**Default value:** `${project.buildDir}/idea-sandbox`


### intellijRepository
The IntelliJ-based IDE distributions repository URL.

**Type:** `String`

**Default value:** `https://cache-redirector.jetbrains.com/www.jetbrains.com/intellij-repository`


### pluginsRepositories
Configures repositories for downloading plugin dependencies.

**Type:** `PluginsRespositoryConfiguration`

**Default value:** `pluginsRepositories { marketplace() }`

**Acceptable values:**
- `marketplace()` - use Maven repository with plugins listed in [JetBrains Marketplace](https://plugins.jetbrains.com)
- `maven(repositoryUrl)` - use custom Maven repository with plugins
- `maven { repositoryUrl }` - use custom Maven repository with plugins where you can configure additional parameters (credentials, authentication and etc.)
- `custom(pluginsXmlUrl)` - use [custom plugin repository](update_plugins_format.md)


### jreRepository
URL of repository for downloading [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).

**Type:** `String`

**Default value:** `null`


### ideaDependencyCachePath
Path to the directory where IDE dependency cache is stored.
If not set, the dependency will be extracted next to the downloaded ZIP archive in [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home) directory.

**Type:** `String`

**Default value:** `null`


### downloadSources
Download IntelliJ Platform sources.
It is enabled by default if the `CI` environment variable is not set – which is present on Continuous Integration environments, like GitHub Actions, TeamCity, and others.

**Type:** `Boolean`

**Default value:** `!System.getenv().containsKey("CI")`


### configureDefaultDependencies
If enabled, automatically configures the default IntelliJ Platform dependencies in the current project.
Otherwise, the `DependenciesUtils.intellij`,`DependenciesUtils.intellijPlugin`, and `DependenciesUtils.intellijPlugins` functions could be used for an explicit configuration.

**Type:** `Boolean`

**Default value:** `true`


### extraDependencies
Configure extra dependency artifacts from the IntelliJ repository.
The dependencies on them could be configured only explicitly using the `DependenciesUtils.intellijExtra` function in the `dependencies` block.

**Type:** `List<String>`

**Default value:** `[]`


## buildPlugin Task
Assembles plugin and prepares ZIP archive for [deployment](deployment.md).

### archiveBaseName
The base name of the ZIP archive.

This task is preconfigured automatically and takes the output artifacts of `prepareSandbox` and `jarSearchableOptions` tasks as an input.

**Type:** `String`

**Default value:** `${prepareSandboxTask.pluginName}`


## buildSearchableOptions Task
Builds an index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options provided by the plugin's [settings](settings.md).

Note, that this is a `runIde`-based task with predefined arguments and all properties of the `runIde` task are also applied to `buildSearchableOptions` tasks.

> If your plugin doesn't implement custom settings, you may [disable it](#how-to-disable-building-searchable-options).
>
{type="tip"}


### outputDir
**Type:** `File`

**Default value:** `build/searchableOptions`


## downloadRobotServerPlugin Task
Downloads `robot-server` plugin.
The `robot-server` plugin is required for running the UI tests using the [`runIdeForUiTests`](#runideforuitests-task) task.


### version
The version of the Robot Server Plugin to download.

**Type:** `String`

**Default value:** `LATEST`


### pluginArchive
The archive with the Robot Server Plugin, downloaded by default to the [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home).

**Type:** `File`

**Default value:** [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


### outputDir
Location of the extracted archive.

**Type:** `File`

**Default value:** `build/robotServerPlugin`


## instrumentCode Task
The following attributes help you to tune instrumenting behaviour in `instrumentCode { ... }` block.

### compilerVersion
A version of instrumenting compiler.
It's used in cases when targeting non-IntelliJ IDEA IDEs (e.g. CLion or Rider).

**Type:** `String`

**Default value:** Build number of the IDE dependency


## jarSearchableOptions Task
Creates a JAR file with searchable options to be distributed with the plugin.


### outputDir
The output directory where the JAR file will be created.

**Type:** `String`

Default value: `build/searchableOptions`


### pluginName
The name of the plugin.

**Type:** `String`

Default value: `intellij.pluginName`


### sandboxDir
The sandbox output directory.

**Type:** `String`

Default value: `${prepareSandbox.outputDir}`


## listProductsReleases Task
List all available IntelliJ-based IDE releases with their updates.
The result list is used for testing the plugin with Plugin Verifier using the [`runPluginVerifier`](#runpluginverifier-task) task.

Plugin Verifier requires a list of the IDEs that will be used for verifying your plugin build against.
The availability of the releases may change in time, i.e., due to security issues in one version – which will be later removed and replaced with an updated IDE release.

With the `listProductsReleases` task, it is possible to list the currently available IDEs matching given conditions, like platform types, since/until release versions.
Such a list is fetched from the remote updates file: `https://www.jetbrains.com/updates/updates.xml`, parsed and filtered considering the specified `type`, `sinceVersion`, `untilVersion` properties.

The result list is stored within the `outputFile`, which is used as a source for the Plugin Verifier if the `runPluginVerifier` task has no `ideVersions` property specified, the output of the `listProductsReleases` is used.


### updatesFile
Path to the products releases update file.
By default, falls back to the Maven cache.

**Type:** `List<String>`

**Default value:** _Maven cache_


### types
List of types of IDEs that will be listed in results.

**Type:** `String`

**Default value:** `intellij.type`


### sinceVersion
Lower boundary of the listed results in product marketing version format, like `2020.2.1`.
Takes precedence over `sinceBuild` property.

**Type:** `String`

**Default value:** `intellij.version`


### untilBuild
Upper boundary of the listed results in product marketing version format, like `2020.2.1`.
Takes precedence over `untilBuild` property.

**Type:** `String`

**Default value:** `null`


### sinceBuild
Lower boundary of the listed results in build number format, like `192`.

**Type:** `String`

**Default value:** `intellij.version`


### untilBuild
Upper boundary of the listed results in build number format, like `192`.

**Type:** `String`

**Default value:** `null`


### releaseChannels
Release channels that product updates will be filtered with.

**Type:** `Channel`

**Default value:** `EnumSet.allOf(ListProductsReleasesTask.Channel)`


### outputFile
Path to the file, where the output list will be stored.

**Type:** `File`

**Default value:** `File("${project.buildDir}/listProductsReleases.txt")`


### androidStudioUpdatePath
For [Android Studio releases](android_studio_releases_list.md), a separated storage for the updates is used.

**Type:** `String`

**Default value:** `https://raw.githubusercontent.com/JetBrains/intellij-sdk-docs/main/topics/_generated/android_studio_releases.xml`


## patchPluginXml Task
Patches <path>[plugin.xml](plugin_configuration_file.md)</path> files with values provided to the task.

> To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).
>
{type="tip"}


### destinationDir
The directory where the patched plugin.xml will be written.

**Type:** `String`

**Default value:** `${project.buildDir}/patchedPluginXmlFiles`


### pluginXmlFiles
The list of <path>[plugin.xml](plugin_configuration_file.md)</path> files to patch.

**Type:** `List<File>`

**Default value:** auto-discovered from the project


### pluginDescription
The description of the plugin – will be set to the `<description>` tag.

**Type:** `String`

**Default value:** `null`


### sinceBuild
The lower bound of the version range to be patched – will be set for the `since-build` attribute of the `<idea-version>` tag.

**Type:** `String`

**Default value:** `intellij.version` in `Branch.Build.Fix` format


### untilBuild
The upper bound of the version range to be patched – will be set for the `until-build` attribute of the `<idea-version>` tag.

**Type:** `String`

**Default value:** `intellij.version` in `Branch.Build.*` format


### version
The version of the plugin – will be set for the `<version>` tag.

**Type:** `String`

**Default value:** `${project.version}`


### changeNotes
The change notes of the plugin – will be set for the `<change-notes>` tag.

**Type:** `String`

**Default value:** `null`


### pluginId
The ID of the plugin – will be set for the `<id>` tag.

**Type:** `String`

**Default value:** `null`


## prepareSandbox Task
Prepares sandbox directory with installed plugin and its dependencies.


### pluginName
The name of the plugin.

**Type:** `String`

**Default value:** `${intellij.pluginName}`


### configDir
The directory with the plugin configuration.

**Type:** `String`

**Default value:** `${intellij.pluginName}/config`


### pluginJar
The input plugin JAR file used to prepare the sandbox.

**Type:** `File`

**Default value:** `jar` task output


### librariesToIgnore
Libraries that will be ignored when preparing the sandbox.
By default, excludes all libraries that are a part of the `setupDependenciesTask.idea` dependency.

**Type:** `List<File>`

**Default value:** `org.jetbrains.intellij.tasks.SetupDependenciesTask.idea.get().jarFiles`


### pluginDependencies
List of dependencies of the current plugin.

**Type:** `List<PluginDependency>`

**Default value:** `org.jetbrains.intellij.IntelliJPluginExtension.getPluginDependenciesList`


## prepareTestingSandbox Task
Prepares sandbox directory with installed plugin and its dependencies for testing purposes.

See [`prepareSandbox` Task](#preparesandbox-task).


## prepareUiTestingSandbox Task
Prepares sandbox directory with installed plugin and its dependencies for UI testing purposes.

See [`prepareSandbox` Task](#preparesandbox-task).


## publishPlugin Task
Publishes plugin to the remote [JetBrains Marketplace](https://plugins.jetbrains.com) repository.

The following attributes are a part of the Publishing DSL `publishPlugin { ... }` in which allows Gradle to upload plugin to [JetBrains Marketplace](https://plugins.jetbrains.com).
Note that you need to [upload the plugin](publishing_plugin.md) to the repository at least once manually (to specify options like the license, repository URL etc.) before uploads through Gradle can be used.

See the instruction on [how to generate authentication token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).

See [Publishing Plugins with Gradle](deployment.md) tutorial for step-by-step instructions.


### token
Authentication token.

**Required**

**Type:** `String`

**Default value:** `null`


### channels
List of channel names to upload plugin to.

**Type:** `List<String>`

**Default value:** `["default"]`


### host
URL host of a plugin repository.

**Type:** `String`

**Default value:** [JetBrains Marketplace](https://plugins.jetbrains.com)


### distributionFile
ZIP file of plugin to upload.

**Type:** `File`

**Default value:** output of the [`buildPlugin`](#buildplugin-task) task


### toolboxEnterprise
Specifies if the Toolbox Enterprise plugin repository service should be used.
This feature is still in the incubating phase and is not yet available for public use.

**Type:** `Boolean`

**Default value:** `false`


## runIde Task
Runs the IDE instance with the developed plugin installed.

`RunIde` tasks extend the [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) Gradle task – all properties available in the `JavaExec` as well as the following ones can be used to configure the `runIde` taks.


### ideDir
The IDEA dependency sources path.
Configured automatically with the `SetupDependenciesTask.idea` dependency.

**Type:** `File`

**Default value:** `setupDependenciesTask.idea`


### jbrVersion
Custom JBR version to use for running the IDE.

**Type:** `String`

**Default value:** `null`

**Accepted values:**
- `8u112b752.4`
- `8u202b1483.24`
- `11_0_2b159`

> For more information about JBR versions and variants, see [Using a JetBrains Runtime for the Development Instance](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
>
{type="note"}

### jbrVariant
JetBrains Runtime variant to use when running the IDE with the plugin.

**Type:** `String`

**Default value:** `null`


### pluginsDir
Path to the `plugins` directory within the sandbox prepared with the `PrepareSandboxTask`.
Provided to the `idea.plugins.path` system property.

**Type:** `Directory`

**Default value:** `prepareSandboxTask.destinationDir`


### autoReloadPlugins
Enables auto-reload of dynamic plugins.
Dynamic plugins will be reloaded automatically when their JARs are modified.
This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.
Enabled by default in 2020.2 and higher.

See [Enabling Auto-Reload](ide_development_instance.md#enabling-auto-reload) for more details.

**Type:** `Boolean`

**Default value:** `true`


## runIdeForUiTests Task
Runs the IDE instance with the developed plugin and robot-server installed and ready for UI testing.

See [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) project.

See [`runIde`](#runide-task) task for more details.


## runIdePerformanceTest Task
Runs performance tests on the IDE with the developed plugin installed.

`RunIdePerformanceTest` task extends the `RunIdeBase` task, all configuration attributes of `JavaExec` and `RunIde` task can be used in `RunIdePerformanceTest` as well.
See [runIde Task](#runide-task) for more details.

Currently, the task is under adaptation, more documentation will be added in the future.

### testDataDir
Path to directory with test projects and '.ijperf' files.

**Type:** `String`

**Default value:** `null`


### artifactsDir
Path to directory where performance test artifacts (IDE logs, snapshots, screenshots, etc.) will be stored.
If the directory doesn't exist, it will be created.

**Type:** `String`

**Default value:** `null`


### profilerName
Name of the profiler which will be used during execution.

**Type:** `ProfilerName`

**Default value:** `ProfilerName.ASYNC`

**Acceptable values:**
- `ProfilerName.ASYNC`
- `ProfilerName.YOURKIT`


## runPluginVerifier Task
Runs the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool to check the binary compatibility with specified [IDE builds](api_changes_list.md).

Plugin Verifier DSL `runPluginVerifier { ... }` allows to define the list of IDEs used for the verification, as well as explicit tool version and any of the available [options](https://github.com/JetBrains/intellij-plugin-verifier#common-options) by proxifying them to the Verifier CLI.

> For more details, examples or issues reporting, go to the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) repository.
>
{type="tip"}

> To run Plugin Verifier in [`-offline`](https://github.com/JetBrains/intellij-plugin-verifier/pull/58) mode, set the Gradle [`offline` start parameter](https://docs.gradle.org/current/javadoc/org/gradle/StartParameter.html#setOffline-boolean-).
>
{type="tip"}


### ideVersions
IDEs to check, in `intellij.version` format, i.e.: `["IC-2019.3.5", "PS-2019.3.2"]`.
Check the available build versions on [IntelliJ Platform Builds list](https://jb.gg/intellij-platform-builds-list).

**Type:** `List<String>`

**Default value:** output of the [`listProductsReleases`](#listproductsreleases-task) task


### verifierVersion
IntelliJ Plugin Verifier version, by default uses the latest available.
Do not change unless absolutely required.

**Type:** `String`

**Default value:** `latest`


### verifierPath
IntelliJ Plugin Verifier local path to the pre-downloaded JAR file.
If set, `verifierVersion` is ignored.

**Type:** `String`

**Default value:** path to the JAR file resolved using the `verifierVersion` property


### localPaths
A list of the paths to locally installed IDE distributions that should be used for verification in addition to those specified in `ideVersions`.

**Type:** `List<File>`

**Default value:** `[]`


### distributionFile
JAR or ZIP file of the plugin to verify.
If empty, the task will be skipped.

**Type:** `File`

**Default value:** output of the `buildPlugin` task


### failureLevel
Defines the verification level at which task should fail if any reported issue will match.
Can be set as `FailureLevel` enum or `EnumSet<FailureLevel>`.

**Type:** `FailureLevel`

**Default value:** `FailureLevel.COMPATIBILITY_PROBLEMS`

**Accepted values:**
- `FailureLevel.COMPATIBILITY_WARNINGS` - Compatibility warnings
- `FailureLevel.COMPATIBILITY_PROBLEMS` - Compatibility problems
- `FailureLevel.DEPRECATED_API_USAGES` - Deprecated API usages
- `FailureLevel.EXPERIMENTAL_API_USAGES` - Experimental API usages
- `FailureLevel.INTERNAL_API_USAGES` - Internal API usages
- `FailureLevel.OVERRIDE_ONLY_API_USAGES` - Override-only API usages
- `FailureLevel.NON_EXTENDABLE_API_USAGES` - Non-extendable API usages
- `FailureLevel.PLUGIN_STRUCTURE_WARNINGS` - Plugin structure warnings
- `FailureLevel.MISSING_DEPENDENCIES` - Missing dependencies
- `FailureLevel.INVALID_PLUGIN` - The following files specified for the verification are not valid plugins
- `FailureLevel.NOT_DYNAMIC` - Plugin cannot be loaded/unloaded without IDE restart
- `FailureLevel.ALL` - All of above
- `FailureLevel.NONE` - None of above


### verificationReportsDir
The path to directory where verification reports will be saved.

**Type:** `String`

**Default value:** `${project.buildDir}/reports/pluginVerifier`


### downloadDir
The path to directory where IDEs used for the verification will be downloaded.

**Type:** `String`

**Default value:** `System.getProperty("plugin.verifier.home.dir")/ides` or `System.getProperty("user.home")/.pluginVerifier/ides` or system temporary directory.


### jbrVersion
Custom JBR version to use for running the IDE.

**Type:** `String`

**Default value:** `null`

**Accepted values:**
- `8u112b752.4`
- `8u202b1483.24`
- `11_0_2b159`

> For more information about JBR versions and variants, see [Using a JetBrains Runtime for the Development Instance](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
>
{type="note"}


### jbrVariant
JetBrains Runtime variant to use when running the IDE with the plugin.

**Type:** `String`

**Default value:** `null`


### runtimeDir
The path to directory containing JVM runtime, overrides `jbrVersion`.

**Type:** `String`

**Default value:** `null`


### externalPrefixes
The list of classes prefixes from the external libraries.
The Plugin Verifier will not report `No such class` for classes of these packages.

**Type:** `List<String>`

**Default value:** `[]`


### teamCityOutputFormat
A flag that controls the output format - if set to `true`, the [TeamCity Tests Format](https://www.jetbrains.com/help/teamcity/service-messages.html) – the TeamCity compatible output will be returned to stdout.

**Type:** `Boolean`

**Default value:** `false`


### subsystemsToCheck
Specifies which subsystems of IDE should be checked.

**Type:** `String`

**Default value:** `all`

**Acceptable values:**
- `all`
- `android-only`
- `without-android`


## setupDependencies Task
Setups required dependencies for building and running project.

This task exposes the `idea` property which contains a reference to the resolved `idea` dependency.


## signPlugin Task
Signs the ZIP archive with the provided key using [marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer) library.

To sign the plugin before publishing to [JetBrains Marketplace](https://plugins.jetbrains.com) with the `signPlugin` task, it is required to provide a certificate chain and a private key with its password using `signPlugin { ... }` Plugin Signing DSL.

As soon as `privateKey` (or `privateKeyFile`) and `certificateChain` (or `certificateChainFile`) properties are specified, task will be executed automatically right before the [`publishPlugin`](#publishplugin-task) task.

For more details, see [Plugin Signing](plugin_signing.md) article.

### certificateChain
A string containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

**Type:** `String`

**Default value:** `null`


### certificateChainFile
A file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert-file` CLI option.

**Type:** `File`

**Default value:** `null`


### privateKey
Encoded private key in PEM format.
Refers to `key` CLI option.

**Type:** `String`

**Default value:** `null`


### privateKeyFile
A file with encoded private key in PEM format.
Refers to `key-file` CLI option.

**Type:** `File`

**Default value:** `null`


### password
Password required to decrypt the private key.
Refers to `key-pass` CLI option.

**Type:** `String`

**Default value:** `null`


### cliVersion
Returns the version of [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) that will be used.

**Type:** `String`

**Default value:** `LATEST`


### cliPath
Path to [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) file.
Takes precedence over `cliPath`.

**Type:** `String`

**Default value:** `null`


### keyStore
KeyStore file path.
Refers to `ks` CLI option.

**Type:** `String`

**Default value:** `null`


### keyStorePassword
KeyStore password.

**Type:** `String`

**Default value:** `null`


### keyStoreKeyAlias
KeyStore key alias.
Refers to `ks-key-alias` CLI option.

**Type:** `String`

**Default value:** `null`


### keyStoreType
KeyStore type.

**Type:** `String`

**Default value:** `null`


### keyStoreProviderName
JCA KeyStore Provider name.
Refers to `ks-provider-name` CLI option.

**Type:** `String`

**Default value:** `null`


### inputArchiveFile
Input, unsigned ZIP archive file.
Refers to `in` CLI option.

Provided by the [`buildPlugin`](#buildplugin-task) task.

### outputArchiveFile
Output, signed ZIP archive file.
Refers to `out` CLI option.

Predefined with the name of the ZIP archive file with `-signed` name suffix attached.

**Type:** `File`


## verifyPlugin Task
Validates completeness and contents of <path>[plugin.xml](plugin_configuration_file.md)</path> descriptors as well as plugin archive structure.


### ignoreFailures
Specifies whether the build should fail when the verifications performed by this task fail.

**Type**: `Boolean`

**Default value:** `false`


### ignoreWarnings
Specifies whether the build should fail when the verifications performed by this task emit warnings.

**Type**: `Boolean`

**Default value:** `true`


### pluginDir
The location of the built plugin file which will be used for verification.

**Type**: `File`

**Default value:** `${prepareSandboxTask.destinationDir}/${prepareSandboxTask.pluginName}`



## Build Features
With the Gradle IntelliJ Plugin releases, new features are introduced that require additional research, collecting more feedback from developers, or should be enabled or disabled under particular conditions.
Build Features are an implementation of the feature flags concept and let you control some behaviors of the Gradle IntelliJ Plugin.
To enable or disable a particular feature, add the Project property to the <path>gradle.properties</path> file, like:

```properties
org.jetbrains.intellij.buildFeature.buildFeatureName=false
```

### selfUpdateCheck
Check if the currently used Gradle IntelliJ Plugin is outdated.

**Default value:** `true`



## Frequently Asked Questions

### How to modify JVM arguments of runIde task
`runIde` task is a [Java Exec](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) task and can be modified according to the documentation.

To add some JVM arguments while launching the IDE, configure `runIde` task as follows:

```
runIde {
  jvmArgs '-DmyProperty=value'
}
```

### How to modify system properties of runIde task
Using the [very same task documentation](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html), configure `runIde` task:

```
runIde {
  systemProperty('name', 'value')
}
```

### How to disable automatic reload of dynamic plugins
Configure `runIde` task as follows:

```
runIde {
    autoReloadPlugins = false
}
```

### How to disable building searchable options
Building searchable options can be disabled as a task:

```
buildSearchableOptions.enabled = false
```

### How disabling building searchable options affects the plugin
As a result of disabling building searchable options, the configurables that your plugin provides
won't be searchable in the Settings dialog.

### How to Debug
Running Gradle tasks from IntelliJ IDEA produces a Gradle run configuration which can be run in debug mode just as any other run configuration:

![Debug Gradle run configuration](https://cloud.githubusercontent.com/assets/140920/9789780/ca31d9f2-57da-11e5-804b-087b06a6eda9.png)

### How do I add my a custom file inside plugin distribution
`prepareSandbox` task is a [Sync](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html) task and can be modified accordingly. Something like following should work:

```
prepareSandbox {
  from('yourFile') {
    into "${intellij.pluginName.get()}/lib/"
  }
}
```

### How to configure logging
The most convenient way to see the logs of running IDE is to add a tab to Run tool window displaying the content of `idea.log` file:

![Logs](https://intellij-support.jetbrains.com/hc/user_images/GazJhC54rML33MBauVXrww.png)

To do this, you need to add the log file in Gradle run configuration settings:

![Gradle run configuration](https://intellij-support.jetbrains.com/hc/user_images/qPiO-BjDP_fSIPKJ5VePJA.png)
