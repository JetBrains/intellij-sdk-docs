<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle IntelliJ Plugin (1.x)
<primary-label ref="Obsolete"/>

<tldr>

**Current Release**: %gradle-intellij-plugin-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/intellij-platform-gradle-plugin/releases), [Issue Tracker](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues)

</tldr>

<link-summary>Gradle IntelliJ Plugin configures Gradle-based plugin projects for building, testing, verifying, and publishing the plugin.</link-summary>

<snippet id="gradlePluginObsolete">

> Gradle IntelliJ Plugin (1.x) is no longer under active development.
>
> Whenever possible, use [](tools_intellij_platform_gradle_plugin.md) instead.
>
{title="Obsolescence Notice" style="warning"}

</snippet>

The Gradle IntelliJ Plugin is a plugin for the Gradle build system to help configure your environment for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.

This plugin allows you to build plugins for IntelliJ Platform using specified IntelliJ SDK and bundled or third-party plugins.

The plugin provides functionalities like:
- adding extra IntelliJ-specific dependencies
- patching `processResources` tasks to fill some tags (name, version) in <path>[plugin.xml](plugin_configuration_file.md)</path> with appropriate values
- patching compile tasks to instrument code with nullability assertions and form classes made with IntelliJ GUI Designer
- additional build steps that are helpful for developing plugins for the IntelliJ Platform

> Please see also [](tools_gradle_intellij_plugin_faq.md) and [](tools_gradle_intellij_plugin_examples.md).
>
> Before visiting the [Issue Tracker](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues), update both plugin and Gradle to the latest versions.
> Please see [CONTRIBUTING](https://github.com/JetBrains/intellij-platform-gradle-plugin/blob/master/CONTRIBUTING.md) on how to submit feedback and contribute to this project.
>

## Usage

<include from="snippets.topic" element-id="gradlePluginVersion"/>

To enable this plugin in your Gradle-based project, register the plugin in the Gradle build script's `plugins` section:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.intellij") version "%gradle-intellij-plugin-version%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id "org.jetbrains.intellij" version "%gradle-intellij-plugin-version%"
}
```

</tab>
</tabs>

When upgrading to `1.x` version, please make sure to follow the [migration guide](https://lp.jetbrains.com/gradle-intellij-plugin) to adjust your existing build script.

> This project requires Gradle 7.3 or newer.
> However, it is recommended to use the latest Gradle available.
> See [Gradle Installation](https://gradle.org/install/) guide.
>
{title="Minimum Gradle Version"}

### IDE Configuration

Some additional settings are required in the IDE after setting up the plugin.

<control>Gradle JVM</control> must be set to Java 11 in <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path>.
When targeting 2022.3+, Java 17 is required instead (see [details](build_number_ranges.md#platformVersions)).

#### Attaching Sources

> This step is not required when using Gradle IntelliJ Plugin version **1.17.2** or later.
>
{style="tip"}

To attach IntelliJ Platform sources in the IDE when enabled via [](#intellij-extension-downloadsources) some additional settings are required in IDE versions 2023.2 and later.

<tabs>

<tab title="2023.3+">

In <ui-path>Settings | Advanced Settings</ui-path> enable option <control>Download sources</control> in section <ui-path>Build Tools. Gradle</ui-path>.
Then invoke <control>Reload All Gradle Projects</control> action from the <control>Gradle</control> tool window.

</tab>

<tab title="2023.2">

In <ui-path>Settings | Build, Execution, Deployment | Build Tools | Gradle</ui-path> enable <control>Download sources for dependencies</control>.
Then invoke <control>Reload All Gradle Projects</control> action from the <control>Gradle</control> tool window.

</tab>

<tab title="Earlier versions">

No additional IDE settings are required.

</tab>

</tabs>

### Snapshot Release
The Snapshot release is a pre-release version built nightly from the latest main branch – as it is built every day using the same version number, it's not recommended to use it for production builds.

To switch to the snapshot release, point Gradle to the dedicated snapshot repository by adding an entry to the Gradle settings file.

> The current Gradle IntelliJ Plugin Snapshot version is ![GitHub Snapshot Release](https://img.shields.io/nexus/s/org.jetbrains.intellij/org.jetbrains.intellij.gradle.plugin?server=https://oss.sonatype.org&label=)
>
> To make sure you obtain the latest snapshot version, invoke Gradle using `--refresh-dependencies` option.
>
{style="note"}

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

<path>build.gradle.kts</path>

```kotlin
plugins {
  id("org.jetbrains.intellij") version "..."
}
```

<path>settings.gradle.kts</path>

```kotlin
pluginManagement {
  repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    gradlePluginPortal()
  }
}
```
</tab>
<tab title="Groovy" group-key="groovy">

<path>build.gradle</path>

```groovy
plugins {
  id "org.jetbrains.intellij" version "..."
}
```

<path>settings.gradle</path>

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


### Configuration Cache
The Gradle build system brings the [Configuration Cache](https://docs.gradle.org/current/userguide/configuration_cache.html) feature that helps improve the build performance by caching the configuration phase.

The Gradle IntelliJ Plugin is fully compatible with this mechanism and can be utilized by manual enabling of the Configuration Cache feature with the `--configuration-cache` flag, like:

```shell
gradle buildPlugin --configuration-cache
```

or by enabling it in the <path>gradle.properties</path> file:

```
org.gradle.unsafe.configuration-cache = true
```

See [Using the configuration cache](https://docs.gradle.org/current/userguide/configuration_cache.html#config_cache:usage) in the Gradle documentation for more details.


### Multi-Module Project
Sometimes, you may want to split your plugin into multiple modules — i.e., to separate the core plugin code from the code related to other third-party plugin dependencies.
The most common way to do this is to use the [Gradle Multi-Project Build](https://docs.gradle.org/current/userguide/multi_project_builds.html) feature.

This approach allows you to declare dependencies between subprojects, like:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  implementation(project(":shared"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  implementation project(':shared')
}
```

</tab>
</tabs>

Because the Gradle IntelliJ Plugin introduces the code instrumentation, and the default `implementation` configuration is not compatible with it, you need to specify the `instrumentedJar` configuration explicitly to refer to the instrumented JAR file produced by the plugin instead of the default JAR file:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  implementation(project(":shared", "instrumentedJar"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  implementation project(path: ':shared', configuration: 'instrumentedJar')
}
```

</tab>
</tabs>

## Configuration

### IntelliJ Extension
{#configuration-intellij-extension}

After the Gradle IntelliJ Plugin is applied, the `intellij` extension can be used to configure the plugin and common settings of the provided tasks.

It is mandatory to specify at least the [`intellij.version`](#intellij-extension-version) property.

**Example:**


<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellij {
  version.set("2022.1.1")
  type.set("IU")
  plugins.set(listOf("com.jetbrains.php:221.5787.21"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellij {
  version = "2022.1.1"
  type = "IU"
  plugins = ["com.jetbrains.php:221.5787.21"]
}
```

</tab>
</tabs>


#### `version`
{#intellij-extension-version}

All available JetBrains IDEs versions can be found in the repositories described on the [](intellij_artifacts.md) page.

The version of the IntelliJ Platform IDE that will be used to build the plugin.
Please see [](plugin_compatibility.md) and [](build_number_ranges.md) for more details.

{type="narrow"}
Required
: `true`

Type
: `String`

Acceptable values
:
- version number: `2022.1.1` or `IC-2022.1.1`
- build number: `221.5080.210` or `IC-221.5080.210`
- snapshot: `221-EAP-SNAPSHOT` or `LATEST-EAP-SNAPSHOT`

> The _version number_ format is the most common option for specifying the version of the IntelliJ Platform.
> Other formats should be used only when your plugin relies on specific parts of the targeted IDE or early-adopting EAP releases.


#### `type`
{#intellij-extension-type}

The type of the IntelliJ-based IDE distribution.
The type may also be specified as a prefix of the value for the [`intellij.version`](#intellij-extension-version) property instead.

{type="narrow"}
Type
: `String`

Default value
: `IC`

Acceptable values
:
- `IC` - [IntelliJ IDEA Community Edition](idea.md)
- `IU` - [IntelliJ IDEA Ultimate Edition](idea.md)
- `CL` - [CLion](clion.md)
- `PY` - [PyCharm Professional Edition](pycharm.md)
- `PC` - [PyCharm Community Edition](pycharm.md)
- `PS` - [PhpStorm](phpstorm.md)
- `RD` - [Rider](rider.md)
- `GO` - [GoLand](goland.md)
- `AI` - [Android Studio](android_studio.md)
- `RR` - [Rust Rover](https://www.jetbrains.com/rust/)
- `JPS` - JPS-only
- `GW` - Gateway

To build against IDEs not supported directly by `type`, please see their corresponding page in _Product Specific_.

#### `pluginName`
{#intellij-extension-pluginname}

The plugin name part used in the generated ZIP distribution: <path>build/distributions/PluginName-1.0.0.zip</path>, and the name of the plugin directory in the sandbox directory.

{type="narrow"}
Type
: `String`

Default value
: `${project.name}`


#### `localPath`
{#intellij-extension-localpath}

The path to the locally installed IDE distribution that should be used to build the plugin.
Using `intellij.localPath` allows building the plugin using an IDE that is not available in [](intellij_artifacts.md).

{type="narrow"}
Type
: `String`

Default value
: `null`

Samples
:
- Windows: <path>C:\Users\user\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\211.7142.45</path>
- macOS: <path>/Applications/Android Studio 4.2 Preview.app/Contents</path> (note <path>/Contents</path> suffix)
- Linux: <path>/home/user/idea-IC-181.4445.78</path>

> `intellij.version` and `intellij.localPath` must not be specified at the same time.
>
{style="warning"}


#### `localSourcesPath`
{#intellij-extension-localsourcespath}

The path to local archive with IDE sources.
Used for resolving source files of the locally installed IDE distribution when [`intellij.localPath`](#intellij-extension-localpath) is specified.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `plugins`
{#intellij-extension-plugins}

The list of bundled IDE plugins and plugins from [JetBrains Marketplace](https://plugins.jetbrains.com) or configured [`intellij.pluginsRepositories`](#intellij-extension-pluginsrepositories).

Please see [](plugin_dependencies.md) for more details.

Notes:
- For plugins from [JetBrains Marketplace](https://plugins.jetbrains.com), use the `pluginId:version` format, like `org.intellij.plugins.markdown:231.8109.126`.
  The version of your choice must be compatible with the version of the IDE you're building against.
- For bundled plugins, use the plugin ID with the version part omitted, like: `org.intellij.groovy`.
- For subprojects, use project reference `project(':subproject', 'instrumentedJar')`.
- For plugin built locally, pass the path to the <path>lib/</path> directory of the extracted plugin archive, like: `file("/path/to/plugin/lib/")` or `file("/projects/plugin-name/build/idea-sandbox/plugins/plugin-name/lib/")`.
  See [](tools_gradle_intellij_plugin_faq.md#how-to-add-a-dependency-on-a-plugin-available-in-the-file-system).
- If you need to refer plugin's classes from your project, you also have to define a dependency in your <path>[plugin.xml](plugin_configuration_file.md)</path> file, see [](plugin_dependencies.md).

{type="narrow"}
Type
: `List<Any>`

Default value
: `[]`

Acceptable values
:
  - `org.plugin.id:version[@channel]` format, `String` type:
    - `org.intellij.plugins.markdown:8.5.0`
    - `org.intellij.scala:2017.2.638@nightly`
  - `bundledPluginId` format, `String` type:
    - `org.intellij.groovy`
  - `project(...)` format, `Project` type:
    - `project(":projectName", "instrumentedJar")`
    - `project(":plugin-subproject", "instrumentedJar")`
  - `file(...)` format, `File` type:
    - `file("/path/to/plugin/lib/")`
    - `file("/projects/plugin-name/build/idea-sandbox/plugins/plugin-name/lib/")`


#### `updateSinceUntilBuild`
{#intellij-extension-updatesinceuntilbuild}

Enables patching <path>[plugin.xml](plugin_configuration_file.md)</path> with the values of [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) and [`patchPluginXml.untilBuild`](#tasks-patchpluginxml-untilbuild) properties.

{type="narrow"}
Type
: `Boolean`

Default value
: `true`


#### `sameSinceUntilBuild`
{#intellij-extension-samesinceuntilbuild}

Enables patching <path>[plugin.xml](plugin_configuration_file.md)</path> with the [`patchPluginXml.untilBuild`](#tasks-patchpluginxml-untilbuild) using value of [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) with `*` wildcard, like `sinceBuild.*`, e.g., `221.*`.

Notes:
- Useful for building plugins against EAP builds.
- If [`patchPluginXml.untilBuild`](#tasks-patchpluginxml-untilbuild) has a value set, then [`intellij.sameSinceUntilBuild`](#intellij-extension-samesinceuntilbuild) is ignored.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


#### `instrumentCode`
{#intellij-extension-instrumentcode}

Enables the instrumentation of Java classes with [nullability](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html) assertions and compilation of forms created by [IntelliJ GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html).

{type="narrow"}
Type
: `Boolean`

Default value
: `true`


#### `sandboxDir`
{#intellij-extension-sandboxdir}

The path of [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) that is used for running IDE with developed plugin.

{type="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/idea-sandbox`


#### `intellijRepository`
{#intellij-extension-intellijrepository}

The IntelliJ-based IDE distributions repository URL.

{type="narrow"}
Type
: `String`

Default value
: `https://cache-redirector.jetbrains.com/www.jetbrains.com/intellij-repository`


#### `pluginsRepositories`
{#intellij-extension-pluginsrepositories}

Configures repositories for downloading plugin dependencies.
See [Maven Interface](https://plugins.jetbrains.com/docs/marketplace/maven-interface.html) for details on Maven repository format.

{type="narrow"}
Type
: `PluginsRepositoryConfiguration`

Default value
: `pluginsRepositories { marketplace() }`

Acceptable values
:
- `marketplace()` - use Maven repository with plugins listed in [JetBrains Marketplace](https://plugins.jetbrains.com)
- `maven(repositoryUrl)` - use custom Maven repository with plugins
- `maven { repositoryUrl }` - use custom Maven repository with plugins where you can configure additional parameters (credentials, authentication, etc.)
- `custom(pluginsXmlUrl)` - use [](custom_plugin_repository.md)


#### `jreRepository`
{#intellij-extension-jrerepository}

URL of repository for downloading [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `ideaDependencyCachePath`
{#intellij-extension-ideadependencycachepath}

Path to the directory where the IDE dependency cache is stored.
If not set, the dependency will be extracted next to the downloaded ZIP archive in [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home) directory.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `downloadSources`
{#intellij-extension-downloadsources}

Enables downloading the IntelliJ Platform sources.
It is enabled by default if the `CI` environment variable is not set – which is present in Continuous Integration environments, like GitHub Actions, TeamCity, and others.

{type="narrow"}
Type
: `Boolean`

Default value
: `!System.getenv().containsKey("CI")`


#### `configureDefaultDependencies`
{#intellij-extension-configuredefaultdependencies}

Enables configuration of the default IntelliJ Platform dependencies in the current project.
Otherwise, the `DependenciesUtils.intellij()`, `DependenciesUtils.intellijPlugin()`, and `DependenciesUtils.intellijPlugins()` functions could be used for an explicit configuration.

{type="narrow"}
Type
: `Boolean`

Default value
: `true`


#### `extraDependencies`
{#intellij-extension-extradependencies}

Configure extra dependency artifacts from the IntelliJ repository.
The dependencies on them could be configured only explicitly using the `DependenciesUtils.intellijExtra()` function in the `dependencies` block.

{type="narrow"}
Type
: `List<String>`

Default value
: `[]`


#### `pluginDependencies`
{#intellij-extension-plugindependencies}

List of dependencies on external plugins.

{type="narrow"}
Type
: `List<PluginDependency>`

Default value
: `[]`


## Tasks


### `buildPlugin`
{#tasks-buildplugin}

Assembles a plugin and prepares ZIP archive for [deployment](publishing_plugin.md).

`buildPlugin` task extends the [`Zip`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html) Gradle task.

<include from="plugin_content.md" element-id="doNotRepackageLibraries"/>

#### `archiveBaseName`
{#tasks-buildplugin-archivebasename}

The base name of the ZIP archive.

This task is preconfigured automatically and takes the output artifacts of [`prepareSandbox`](#tasks-preparesandbox) and [`jarSearchableOptions`](#tasks-jarsearchableoptions) tasks as an input.

{type="narrow"}
Type
: `String`

Default value
: `${prepareSandboxTask.pluginName}`


### `buildSearchableOptions`
{#tasks-buildsearchableoptions}

Builds an index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options provided by the plugin's [](settings.md).

Note, this is a [`runIde`](#tasks-runide)-based task with predefined arguments and all properties of the [`runIde`](#tasks-runide) task are also applied to [`buildSearchableOptions`](#tasks-buildsearchableoptions) tasks.

> If your plugin doesn't implement custom settings, it is recommended to [disable it](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options).
> See also [`noSearchableOptionsWarning`](tools_gradle_intellij_plugin_build_features.md#nosearchableoptionswarning) build feature.
>


#### `outputDir`
{#tasks-buildsearchableoptions-outputdir}

{type="narrow"}
Type
: `File`

Default value
: `build/searchableOptions`


### `classpathIndexCleanup`
{#tasks-classpathindexcleanup}

Remove `classpath.index` files that are created by the `PathClassLoader`.
This loader, due to the implementation bug, ignores the `idea.classpath.index.enabled=false` flag and as a workaround, files have to be removed manually.

Task is enabled if [`intellij.version`](#intellij-extension-version) is set to `2022.1` or higher.

#### `classpathIndexFiles`
{#tasks-classpathindexcleanup-classpathindexfiles}

The list of `classpath.index` files to be removed.

{type="narrow"}
Type
: `ConfigurableFileCollection`

Default value:
: List of `classpath.index` files resolved with `sourceSets` configuration


### `downloadRobotServerPlugin`
{#tasks-downloadrobotserverplugin}

Download the `robot-server` plugin.
The `robot-server` plugin is required for running the UI tests using the [`runIdeForUiTests`](#tasks-runideforuitests) task.


#### `version`
{#tasks-downloadrobotserverplugin-version}

The version of the Robot Server Plugin to download.

{type="narrow"}
Type
: `String`

Default value
: `LATEST`


#### `pluginArchive`
{#tasks-downloadrobotserverplugin-pluginarchive}

The Robot Server Plugin archive, downloaded by default to the [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home).

{type="narrow"}
Type
: `File`

Default value
: [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


#### `outputDir`
{#tasks-downloadrobotserverplugin-outputdir}

Location of the extracted archive.

{type="narrow"}
Type
: `File`

Default value
: `build/robotServerPlugin`


### `downloadZipSignerTask`
{#tasks-downloadzipsignertask}

Resolves and downloads Marketplace ZIP Signer CLI tool used by the [`signPlugin`](#tasks-signplugin) task.


#### `version`
{#tasks-downloadzipsignertask-version}

Version of the ZIP Signer CLI tool to download.

{type="narrow"}
Type
: `String`

Default value
: `LATEST`


#### `cliPath`
{#tasks-downloadzipsignertask-clipath}

Path to the ZIP Signer CLI tool.

{type="narrow"}
Type
: `File`

Default value
: [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


#### `cli`
{#tasks-downloadzipsignertask-cli}

The output of the ZIP Signer CLI tool.

{type="narrow"}
Type
: `File`

Default value
: [`cliPath`](#tasks-downloadzipsignertask-clipath)


### `initializeIntelliJPlugin`
{#tasks-initializeintellijplugin}

Initializes the Gradle IntelliJ Plugin and performs various checks, like if the plugin is up-to-date.


### `instrumentCode`
{#tasks-instrumentcode}

The following attributes help you to tune instrumenting behavior in the `instrumentCode { ... }` block.


#### `ideaDependency`
{#tasks-instrumentcode-ideadependency}

The dependency on IntelliJ IDEA.

{type="narrow"}
Type
: `IdeaDependency`

Default value
: [`intellij.ideaDependency`](#tasks-setupdependencies-idea)


#### `javac2`
{#tasks-instrumentcode-javac2}

Path to the <path>javac2.jar</path> file of IntelliJ IDEA.

{type="narrow"}
Type
: `File`

Default value
: <path>lib/javac2.jar</path> resolved in [`instrumentCode.ideaDependency`](#tasks-instrumentcode-ideadependency)


#### `compilerVersion`
{#tasks-instrumentcode-compilerversion}

A version of instrumenting compiler.
It's used in cases when targeting non-IntelliJ IDEA IDEs (e.g., [CLion](clion.md) or [Rider](rider.md)).

{type="narrow"}
Type
: `String`

Default value
: Build number of the IDE dependency


#### `classesDirs`
{#tasks-instrumentcode-classesdirs}

The list of directories with compiled classes.

{type="narrow"}
Type
: `FileCollection`

Default value
: `sourceSets.[].output.classesDirs`


#### `formDirs`
{#tasks-instrumentcode-formdirs}

The list of directories with GUI Designer form files.

{type="narrow"}
Type
: `FileCollection`

Default value
: `.form` files of the project's source sets.


#### `outputDir`
{#tasks-instrumentcode-outputdir}

The output directory for instrumented classes.

{type="narrow"}
Type
: `File`

Default value
: [`setupInstrumentCode.instrumentedDir`](#tasks-setupinstrumentcode-instrumenteddir)


#### `compilerClassPathFromMaven`
{#tasks-instrumentcode-compilerclasspathfrommaven}

The classpath for Java instrumentation compiler.

{type="narrow"}
Type
: `FileCollection`


### `instrumentedJar`
{#tasks-instrumentedjar}

Create a JAR file with instrumented classes.


### `jarSearchableOptions`
{#tasks-jarsearchableoptions}

Create a JAR file with searchable options to be distributed with the plugin.


#### `outputDir`
{#tasks-jarsearchableoptions-outputdir}

The output directory where the JAR file will be created.

{type="narrow"}
Type
: `String`

Default value
: `build/searchableOptions`


#### `pluginName`
{#tasks-jarsearchableoptions-pluginname}

The name of the plugin.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.pluginName`](#intellij-extension-pluginname)


#### `sandboxDir`
{#tasks-jarsearchableoptions-sandboxdir}

The sandbox output directory.

{type="narrow"}
Type
: `String`

Default value
: [`prepareSandbox.outputDir`](#tasks-preparesandbox)


### `listBundledPlugins`
{#tasks-listbundledplugins}

Lists all IDs of plugins bundled within the currently targeted IDE.
This can be used to determine Plugin ID for setting up [](plugin_dependencies.md).

See also [](#tasks-printBundledPlugins).


#### `ideDir`
{#tasks-listbundledplugins-idedir}

The IDE dependency sources path.
Configured automatically with the [`setupDependencies.idea`](#tasks-setupdependencies-idea) dependency.

{type="narrow"}
Type
: `File`

Default value
: [`setupDependencies.idea`](#tasks-setupdependencies-idea)


#### `outputFile`
{#tasks-listbundledplugins-outputfile}

Path to the file, where the output list will be stored.

{type="narrow"}
Type
: `File`

Default value
: `File("${project.buildDir}/listBundledPlugins.txt")`


### `listProductsReleases`
{#tasks-listproductsreleases}

List all available IntelliJ-based IDE releases with their updates.
The result list is used for testing the plugin with Plugin Verifier using the [`runPluginVerifier`](#tasks-runpluginverifier) task.

Plugin Verifier requires a list of the IDEs that will be used for verifying your plugin build against.
The availability of the releases may change in time, i.e., due to security issues in one version – which will be later removed and replaced with an updated IDE release.

With the [`listProductsReleases`](#tasks-listproductsreleases) task, it is possible to list the currently available IDEs matching given conditions, like platform types, since/until release versions.
Such a list is fetched from the remote updates file: `https://www.jetbrains.com/updates/updates.xml`, parsed and filtered considering the specified [`listProductsReleases.types`](#tasks-listproductsreleases-types), [`listProductsReleases.sinceVersion`](#tasks-listproductsreleases-sinceversion), [`listProductsReleases.untilVersion`](#tasks-listproductsreleases-untilversion) (or [`listProductsReleases.sinceBuild`](#tasks-listproductsreleases-sincebuild), [`listProductsReleases.untilBuild`](#tasks-listproductsreleases-untilbuild)) properties.

The result list is stored within the [`listProductsReleases.outputFile`](#tasks-listproductsreleases-outputfile), which is used as a source for the Plugin Verifier if the [`runPluginVerifier`](#tasks-runpluginverifier) task has no [`runPluginVerifier.ideVersions`](#tasks-runpluginverifier-ideversions) property specified, the output of the [`listProductsReleases`](#tasks-listproductsreleases) task is used.

See also [](#tasks-printproductsreleases).


#### `productsReleasesUpdateFiles`
{#tasks-listproductsreleases-productsreleasesupdatefiles}

Path to the products releases update files. By default, one is downloaded from `IntelliJPluginConstants.IDEA_PRODUCTS_RELEASES_URL`.

{type="narrow"}
Type
: `FileCollection`

Default value
: [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


#### `types`
{#tasks-listproductsreleases-types}

List of types of IDEs that will be listed in results.

{type="narrow"}
Type
: `List<String>`

Default value
: [`intellij.type`](#intellij-extension-type)


#### `sinceVersion`
{#tasks-listproductsreleases-sinceversion}

Lower boundary of the listed results in product marketing version format, e.g., `2020.2.1`.
It takes precedence over the [`listProductsReleases.sinceBuild`](#tasks-listproductsreleases-sincebuild) property.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version)


#### `untilVersion`
{#tasks-listproductsreleases-untilversion}

Upper boundary of the listed results in product marketing version format, e.g., `2020.2.1`.
It takes precedence over the [`listProductsReleases.untilBuild`](#tasks-listproductsreleases-untilbuild) property.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `sinceBuild`
{#tasks-listproductsreleases-sincebuild}

Lower boundary of the listed results in build number format, like `192`.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version)


#### `untilBuild`
{#tasks-listproductsreleases-untilbuild}

Upper boundary of the listed results in build number format, like `192`.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `releaseChannels`
{#tasks-listproductsreleases-releasechannels}

Release channels that product updates will be filtered with.

{type="narrow"}
Type
: `Channel`

Default value
: `EnumSet.allOf(ListProductsReleasesTask.Channel)`


#### `outputFile`
{#tasks-listproductsreleases-outputfile}

Path to the file, where the output list will be stored.

{type="narrow"}
Type
: `File`

Default value
: `File("${project.buildDir}/listProductsReleases.txt")`


#### `androidStudioUpdatePath`
{#tasks-listproductsreleases-androidstudioupdatepath}

For [Android Studio releases](android_studio_releases_list.md), a separated storage for the updates is used.

{type="narrow"}
Type
: `String`

Default value
: `https://raw.githubusercontent.com/JetBrains/intellij-sdk-docs/main/topics/_generated/android_studio_releases.xml`


### `patchPluginXml`
{#tasks-patchpluginxml}

Patches <path>[plugin.xml](plugin_configuration_file.md)</path> files with values provided to the task.

> To maintain and generate an up-to-date changelog, try using the [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).
>
{style="note"}

#### `destinationDir`
{#tasks-patchpluginxml-destinationdir}

The directory where the patched <path>[plugin.xml](plugin_configuration_file.md)</path> will be written.

{type="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/patchedPluginXmlFiles`


#### `pluginXmlFiles`
{#tasks-patchpluginxml-pluginxmlfiles}

The list of <path>[plugin.xml](plugin_configuration_file.md)</path> files to patch.

{type="narrow"}
Type
: `List<File>`

Default value
: auto-discovered from the project


#### `pluginDescription`
{#tasks-patchpluginxml-plugindescription}

The description of the plugin used in the [`<description>`](plugin_configuration_file.md#idea-plugin__description) tag.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `sinceBuild`
{#tasks-patchpluginxml-sincebuild}

The lower bound of the [version range](build_number_ranges.md) to be patched used in the `since-build` attribute of the [`<idea-version>`](plugin_configuration_file.md#idea-plugin__idea-version) tag.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version) in `Branch.Build.Fix` format


#### `untilBuild`
{#tasks-patchpluginxml-untilbuild}

The upper bound of the [version range](build_number_ranges.md) to be patched used in the `until-build` attribute of the [`<idea-version>`](plugin_configuration_file.md#idea-plugin__idea-version) tag.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version) in `Branch.Build.*` format


#### `version`
{#tasks-patchpluginxml-version}

The version of the plugin used in the [`<version>`](plugin_configuration_file.md#idea-plugin__version) tag.

{type="narrow"}
Type
: `String`

Default value
: `${project.version}`


#### `changeNotes`
{#tasks-patchpluginxml-changenotes}

The change notes of the plugin used in the [`<change-notes>`](plugin_configuration_file.md#idea-plugin__change-notes) tag.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `pluginId`
{#tasks-patchpluginxml-pluginid}

The ID of the plugin used in the [`<id>`](plugin_configuration_file.md#idea-plugin__id) tag.

{type="narrow"}
Type
: `String`

Default value
: `null`


### `prepareSandbox`
{#tasks-preparesandbox}

Prepares the sandbox directory with the installed plugin and its dependencies.


#### `pluginName`
{#tasks-preparesandbox-pluginname}

The name of the plugin.

{type="narrow"}
Type
: `String`

Default value
: [`intellij.pluginName`](#intellij-extension-pluginname)


#### `configDir`
{#tasks-preparesandbox-configdir}

The directory with the plugin configuration.

{type="narrow"}
Type
: `String`

Default value
: `${intellij.pluginName}/config`


#### `pluginJar`
{#tasks-preparesandbox-pluginjar}

The input plugin JAR file used to prepare the sandbox.

{type="narrow"}
Type
: `File`

Default value
: output of the `jar` task


#### `librariesToIgnore`
{#tasks-preparesandbox-librariestoignore}

Libraries that will be ignored when preparing the sandbox.
By default, it excludes all libraries that are a part of the [`setupDependenciesTask.idea`](#tasks-setupdependencies-idea) dependency.

{type="narrow"}
Type
: `List<File>`

Default value
: `org.jetbrains.intellij.tasks.SetupDependenciesTask.idea.get().jarFiles`


#### `pluginDependencies`
{#tasks-preparesandbox-plugindependencies}

List of dependencies on external plugins.

{type="narrow"}
Type
: `List<PluginDependency>`

Default value
: `org.jetbrains.intellij.IntelliJPluginExtension.getPluginDependenciesList`


### `prepareTestingSandbox`
{#tasks-preparetestingsandbox}

Prepares the sandbox directory with the installed plugin and its dependencies for testing purposes.

See [`prepareSandbox` Task](#tasks-preparesandbox).


### `prepareUiTestingSandbox`
{#tasks-prepareuitestingsandbox}

Prepares the sandbox directory with the installed plugin and its dependencies for UI testing purposes.

See [`prepareSandbox` Task](#tasks-preparesandbox).


### `printProductsReleases`
{#tasks-printproductsreleases}

Print the output produced by the [`listProductsReleases`](#tasks-listproductsreleases) task.


#### `inputFile`
{#tasks-printproductsreleases-inputfile}

Output file provided by the [`listProductsReleases`](#tasks-listproductsreleases) task.


### `printBundledPlugins`
{#tasks-printBundledPlugins}

Print the output produced by the [`listBundledPlugins`](#tasks-listbundledplugins) task.


#### `inputFile`
{#tasks-printBundledPlugins-inputfile}

Output file provided by the [`listBundledPlugins`](#tasks-listbundledplugins) task.


### `publishPlugin`
{#tasks-publishplugin}

Publishes plugin to the remote [JetBrains Marketplace](https://plugins.jetbrains.com) repository.

The following attributes are a part of the Publishing DSL `publishPlugin { ... }` in which allows Gradle to upload plugin to [JetBrains Marketplace](https://plugins.jetbrains.com).
Note that you need to [upload the plugin](publishing_plugin.md#uploading-a-plugin-to-jetbrains-marketplace) to the repository at least once manually (to specify options like the license, repository URL, etc.) before uploads through Gradle can be used.

See the instruction on [how to generate authentication token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).

See the [](publishing_plugin.md#publishing-plugin-with-gradle) tutorial for step-by-step instructions.


#### `token`
{#tasks-publishplugin-token}

Authentication token.

**Required**

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `channels`
{#tasks-publishplugin-channels}

List of channel names to upload plugin to.

{type="narrow"}
Type
: `List<String>`

Default value
: `["default"]`

#### `hidden`
{#tasks-publishplugin-hidden}

Mark the release as hidden to prevent public release after approval.
See [Hidden release](https://plugins.jetbrains.com/docs/marketplace/hidden-plugin.html) in JetBrains Marketplace docs.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


#### `host`
{#tasks-publishplugin-host}

URL host of a plugin repository.

{type="narrow"}
Type
: `String`

Default value
: [JetBrains Marketplace](https://plugins.jetbrains.com)


#### `distributionFile`
{#tasks-publishplugin-distributionfile}

ZIP file of plugin to upload.

{type="narrow"}
Type
: `File`

Default value
: output of the [`buildPlugin`](#tasks-buildplugin) task


#### `ideServices`
{#tasks-publishplugin-ideServices}

Specifies if the IDE Services plugin repository service should be used.
This feature is still in the incubating phase and is not yet available for public use.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


### `runIde`
{#tasks-runide}

Run the IDE instance with the developed plugin installed.

`runIde` task extends the [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) Gradle task – all properties available in the `JavaExec` as well as the following ones can be used to configure the `runIde` task.

#### `ideDir`
{#tasks-runide-idedir}

The IDE dependency sources path.
Configured automatically with the [`setupDependencies.idea`](#tasks-setupdependencies-idea) dependency.

{type="narrow"}
Type
: `File`

Default value
: [`setupDependencies.idea`](#tasks-setupdependencies-idea)


#### `jbrVersion`
{#tasks-runide-jbrversion}

Custom JetBrains Runtime (JBR) version to use for running the IDE.

{type="narrow"}
Type
: `String`

Default value
: `null`

Accepted values
:
- `8u112b752.4`
- `8u202b1483.24`
- `11_0_2b159`

> For more information about JBR versions and variants, see [](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
>
{style="note"}


#### `jbrVariant`
{#tasks-runide-jbrvariant}

JetBrains Runtime (JBR) variant to use when running the IDE with the plugin.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `jbrArch`
{#tasks-runide-jbrarch}

JetBrains Runtime architecture.
By default, it's resolved based on the current OS and JRE architecture.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `pluginsDir`
{#tasks-runide-pluginsdir}

Path to the `plugins` directory within the sandbox prepared with the [`prepareSandbox`](#tasks-preparesandbox) task.
Provided to the `idea.plugins.path` system property.

{type="narrow"}
Type
: `Directory`

Default value
: [`prepareSandbox.destinationDir`](#tasks-preparesandbox)


#### `autoReloadPlugins`
{#tasks-runide-autoreloadplugins}

Enables auto-reload of dynamic plugins.
Dynamic plugins will be reloaded automatically when their JARs are modified.
This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.
Enabled by default in 2020.2 and higher.

See [Enabling Auto-Reload](ide_development_instance.md#enabling-auto-reload) for more details.

{type="narrow"}
Type
: `Boolean`

Default value
: `true`


### `runIdeForUiTests`
{#tasks-runideforuitests}

Run the IDE instance with the developed plugin and robot-server installed and ready for UI testing.

See [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) project.

See [`runIde`](#tasks-runide) task for more details.


### `runIdePerformanceTest`
{#tasks-runideperformancetest}

Run performance tests on the IDE with the developed plugin installed.

The `runIdePerformanceTest` task extends the `RunIdeBase` task, so all configuration attributes of `JavaExec` and [`runIde`](#tasks-runide) tasks can be used in the `runIdePerformanceTest` as well.
See [`runIde`](#tasks-runide) task for more details.

Currently, the task is under adaptation; more documentation will be added in the future.

#### `testDataDir`
{#tasks-runideperformancetest-testdatadir}

Path to directory with test projects and <path>.ijperf</path> files.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `artifactsDir`
{#tasks-runideperformancetest-artifactsdir}

Path to the directory where performance test artifacts (IDE logs, snapshots, screenshots, etc.) will be stored.
If the directory doesn't exist, it will be created.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `profilerName`
{#tasks-runideperformancetest-profilername}

Name of the profiler which will be used during execution.

{type="narrow"}
Type
: `ProfilerName`

Default value
: `ProfilerName.ASYNC`

Acceptable values
:
- `ProfilerName.ASYNC`
- `ProfilerName.YOURKIT`


### `runPluginVerifier`
{#tasks-runpluginverifier}

Run the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool to check the binary compatibility with specified IDE builds (see also [](verifying_plugin_compatibility.md)).

Plugin Verifier DSL `runPluginVerifier { ... }` allows to define the list of IDEs used for the verification, as well as explicit tool version and any of the available [options](https://github.com/JetBrains/intellij-plugin-verifier#common-options) by proxifying them to the Verifier CLI.

> For more details, examples or issues reporting, go to the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) repository.
>

> To run Plugin Verifier in [`-offline`](https://github.com/JetBrains/intellij-plugin-verifier/pull/58) mode, set the Gradle [`offline` start parameter](https://docs.gradle.org/current/javadoc/org/gradle/StartParameter.html#setOffline-boolean-).
>


#### `ideVersions`
{#tasks-runpluginverifier-ideversions}

The IDEs to be checked in [`intellij.version`](#intellij-extension-version) format, i.e.: `["IC-2019.3.5", "PS-2019.3.2"]`.
Check the available build versions on [IntelliJ Platform Builds list](https://jb.gg/intellij-platform-builds-list).

{type="narrow"}
Type
: `List<String>`

Default value
: output of the [`listProductsReleases`](#tasks-listproductsreleases) task


#### `verifierVersion`
{#tasks-runpluginverifier-verifierversion}

IntelliJ Plugin Verifier version.
Do not change unless absolutely required.

{type="narrow"}
Type
: `String`

Default value
: `LATEST`


#### `verifierPath`
{#tasks-runpluginverifier-verifierpath}

Local path to the pre-downloaded IntelliJ Plugin Verifier JAR file.
If set, [`runPluginVerifier.verifierVersion`](#tasks-runpluginverifier-verifierversion) is ignored.

{type="narrow"}
Type
: `String`

Default value
: path to the JAR file resolved using the [`runPluginVerifier.verifierVersion`](#tasks-runpluginverifier-verifierversion) property


#### `localPaths`
{#tasks-runpluginverifier-localpaths}

A list of the paths to locally installed IDE distributions that should be used for verification in addition to those specified in [`runPluginVerifier.ideVersions`](#tasks-runpluginverifier-ideversions).

{type="narrow"}
Type
: `List<File>`

Default value
: `[]`


#### `distributionFile`
{#tasks-runpluginverifier-distributionfile}

ZIP file of the plugin to verify.
If empty, the task will be skipped.

{type="narrow"}
Type
: `File`

Default value
: output of the `buildPlugin` task


#### `failureLevel`
{#tasks-runpluginverifier-failurelevel}

Defines the verification level at which the task should fail if any reported issue matches.
Can be set as `FailureLevel` enum or `EnumSet<FailureLevel>`.

{type="narrow"}
Type
: `org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel`

Default value
: `FailureLevel.COMPATIBILITY_PROBLEMS`

Accepted values
:
- `FailureLevel.COMPATIBILITY_WARNINGS` - Compatibility warnings detected against the specified IDE version.
- `FailureLevel.COMPATIBILITY_PROBLEMS` - Compatibility problems detected against the specified IDE version.
- `FailureLevel.DEPRECATED_API_USAGES` - Plugin uses API marked as deprecated (`@Deprecated`).
- `FailureLevel.SCHEDULED_FOR_REMOVAL_API_USAGES` - Plugin uses API marked as scheduled for removal (`@ApiStatus.ScheduledForRemoval`).
- `FailureLevel.EXPERIMENTAL_API_USAGES` - Plugin uses API marked as experimental (`@ApiStatus.Experimental`).
- `FailureLevel.INTERNAL_API_USAGES` - Plugin uses API marked as internal (`@ApiStatus.Internal`).
- `FailureLevel.OVERRIDE_ONLY_API_USAGES` - Override-only API is used incorrectly (`@ApiStatus.OverrideOnly`).
- `FailureLevel.NON_EXTENDABLE_API_USAGES` - Non-extendable API is used incorrectly (`@ApiStatus.NonExtendable`).
- `FailureLevel.PLUGIN_STRUCTURE_WARNINGS` - The structure of the plugin is not valid.
- `FailureLevel.MISSING_DEPENDENCIES` - Plugin has some dependencies missing.
- `FailureLevel.INVALID_PLUGIN` - "Provided plugin artifact is not valid."
- `FailureLevel.NOT_DYNAMIC` - "Plugin cannot be loaded/unloaded without IDE restart."
- `FailureLevel.ALL` - All of the above
- `FailureLevel.NONE` - None of the above


#### `verificationReportsDir`
{#tasks-runpluginverifier-verificationreportsdir}

The path to the directory where verification reports will be saved.

{type="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/reports/pluginVerifier`

#### `verificationReportsFormats`
{#tasks-runpluginverifier-verificationreportsformats}

The output formats of the verification reports that will be emitted.

{type="narrow"}
Type
: `List<String>`

Default value
: `["plain", "html"]`

Acceptable values
:
- `plain` (console output)
- `html` (HTML format)
- `markdown` (Markdown format)

#### `downloadDir`
{#tasks-runpluginverifier-downloaddir}

The path to the directory where IDEs used for the verification will be downloaded.
By default, it relies on the `plugin.verifier.home.dir` system property and falls back to the `XDG_CACHE_HOME` environment variable – see [XDG Base Directory](https://wiki.archlinux.org/title/XDG_Base_Directory) for more details.

{type="narrow"}
Type
: `String`

Default value
: `System.getProperty("plugin.verifier.home.dir")/ides`, `System.getenv("XDG_CACHE_HOME")/pluginVerifier/ides`, `System.getProperty("user.home")/.cache/pluginVerifier/ides` or system temporary directory.


#### `jbrVersion`
{#tasks-runpluginverifier-jbrversion}

Custom JetBrains Runtime (JBR) version to use for running the verification.

{type="narrow"}
Type
: `String`

Default value
: `null`

Acceptable values
:
- `8u112b752.4`
- `8u202b1483.24`
- `11_0_2b159`

> For more information about JBR versions and variants, see [](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).
>
{style="note"}


#### `jbrVariant`
{#tasks-runpluginverifier-jbrvariant}

JetBrains Runtime (JBR) variant to use when running the verification.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `jbrArch`
{#tasks-runpluginverifier-jbrarch}

JetBrains Runtime architecture.
By default, it's resolved based on the current OS and JRE architecture.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `runtimeDir`
{#tasks-runpluginverifier-runtimedir}

The path to the directory containing the JVM runtime. Overrides [`runPluginVerifier.jbrVersion`](#tasks-runpluginverifier-jbrversion).

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `externalPrefixes`
{#tasks-runpluginverifier-externalprefixes}

The list of class prefixes from the external libraries.
The Plugin Verifier will not report `No such class` for classes of these packages.

{type="narrow"}
Type
: `List<String>`

Default value
: `[]`

#### `ignoredProblems`
{#tasks-runpluginverifier-ignoredproblems}

A file that contains a list of problems that will be ignored in the verification report.
It must contain lines in form `<plugin_xml_id>:<plugin_version>:<problem_description_regexp_pattern>`.

{type="narrow"}
Type
: `File`

Default value
: `null`

#### `teamCityOutputFormat`
{#tasks-runpluginverifier-teamcityoutputformat}

A flag that controls the output format - if set to `true`, the [TeamCity Tests Format](https://www.jetbrains.com/help/teamcity/service-messages.html) – the TeamCity compatible output will be returned to stdout.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


#### `subsystemsToCheck`
{#tasks-runpluginverifier-subsystemstocheck}

Specify which subsystems of the IDE should be checked.

{type="narrow"}
Type
: `String`

Default value
: `all`

Acceptable values
:
- `all`
- `android-only`
- `without-android`

#### `freeArgs`
{#tasks-runpluginverifier-freeArgs}

Arbitrary command line arguments that are passed to the IntelliJ Plugin Verifier as is in addition to the arguments provided by the Plugin Verifier DSL.

Arguments that require a value must be provided in the separate elements of the list.

{type="narrow"}
Type
: `List<String>`

Default value
: `[]`

Acceptable values
:
- `["-team-city"]` as a switch
- `["-suppress-internal-api-usages", "jetbrains-plugins"]` as an argument with a value

### `setupDependencies`
{#tasks-setupdependencies}

Setup required dependencies for building and running the project.
This task is automatically added to the ["After Sync" Gradle trigger](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html#config_triggers_gradle) to make the IntelliJ SDK dependency available for IntelliJ IDEA right after the Gradle synchronization.

> After removing the Gradle IntelliJ Plugin from your project, the `Task 'setupDependencies' not found in root project` exception may occur.
> See [Frequently Asked Questions](tools_gradle_intellij_plugin_faq.md#task-setupdependencies-not-found-in-root-project) for more details.
>
{style="warning"}


#### `idea`
{#tasks-setupdependencies-idea}

This task exposes the `setupDependencies.idea` property which contains a reference to the resolved IDE dependency used for building the plugin.

This property can be referred in Gradle configuration to access IDE dependency classpath.


### `setupInstrumentCode`

Prepares code instrumentation tasks.


#### `instrumentationEnabled`
{#tasks-setupinstrumentcode-instrumentationenabled}

A flag that controls whether code instrumentation is enabled.

{type="narrow"}
Type
: `Boolean`

Default value
: [`intellij.instrumentCode`](#intellij-extension-instrumentcode)


#### `instrumentedDir`
{#tasks-setupinstrumentcode-instrumenteddir}

The path to the directory where instrumented classes will be saved.

{type="narrow"}
Type
: `Directory`

Default value
: `${project.buildDir}/instrumented`



### `signPlugin`
{#tasks-signplugin}

Sign the ZIP archive with the provided key using the [marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer) library.

To sign the plugin before publishing to [JetBrains Marketplace](https://plugins.jetbrains.com) with the [`signPlugin`](#tasks-signplugin) task, it is required to provide a certificate chain and a private key with its password using `signPlugin { ... }` Plugin Signing DSL.

As soon as [`signPlugin.privateKey`](#tasks-signplugin-privatekey) (or [`signPlugin.privateKeyFile`](#tasks-signplugin-privatekeyfile)) and [`signPlugin.certificateChain`](#tasks-signplugin-certificatechain) (or [`signPlugin.certificateChainFile`](#tasks-signplugin-certificatechainfile)) properties are specified, the task will be executed automatically right before the [`publishPlugin`](#tasks-publishplugin) task.

For more details, see [Plugin Signing](plugin_signing.md) article.

#### `certificateChain`
{#tasks-signplugin-certificatechain}

A string containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

> This property accepts value provided as a plain text or base64-encoded string.
>
{style="note"}

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `certificateChainFile`
{#tasks-signplugin-certificatechainfile}

A file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert-file` CLI option.

{type="narrow"}
Type
: `File`

Default value
: `null`


#### `privateKey`
{#tasks-signplugin-privatekey}

Encoded private key in PEM format.
Refers to `key` CLI option.

> This property accepts value provided as a plain text or base64-encoded string.
>
{style="note"}

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `privateKeyFile`
{#tasks-signplugin-privatekeyfile}

A file with the encoded private key in PEM format.
Refers to `key-file` CLI option.

{type="narrow"}
Type
: `File`

Default value
: `null`


#### `password`
{#tasks-signplugin-password}

Password required to decrypt the private key.
Refers to `key-pass` CLI option.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `cliVersion`
{#tasks-signplugin-cliversion}

Returns the version of [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) that will be used.

{type="narrow"}
Type
: `String`

Default value
: `LATEST`


#### `cliPath`
{#tasks-signplugin-clipath}

Path to [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) file.
Takes precedence over [`signPlugin.cliVersion`](#tasks-signplugin-cliversion).

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `keyStore`
{#tasks-signplugin-keystore}

KeyStore file path.
Refers to `ks` CLI option.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `keyStorePassword`
{#tasks-signplugin-keystorepassword}

KeyStore password.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `keyStoreKeyAlias`
{#tasks-signplugin-keystorekeyalias}

KeyStore key alias.
Refers to `ks-key-alias` CLI option.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `keyStoreType`
{#tasks-signplugin-keystoretype}

KeyStore type.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `keyStoreProviderName`
{#tasks-signplugin-keystoreprovidername}

JCA KeyStore Provider name.
Refers to `ks-provider-name` CLI option.

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `inputArchiveFile`
{#tasks-signplugin-inputarchivefile}

Input, unsigned ZIP archive file.
Refers to `in` CLI option.

Provided by the [`buildPlugin`](#tasks-buildplugin) task.

#### `outputArchiveFile`
{#tasks-signplugin-outputarchivefile}

Output, signed ZIP archive file.
Refers to `out` CLI option.

Predefined with the name of the ZIP archive file with `-signed` name suffix attached.

{type="narrow"}
Type
: `File`


### `verifyPlugin`
{#tasks-verifyplugin}

Validates completeness and contents of <path>[plugin.xml](plugin_configuration_file.md)</path> descriptors as well as plugin archive structure.


#### `ignoreFailures`
{#tasks-verifyplugin-ignorefailures}

Specify whether the build should fail when the verifications performed by this task fail.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


#### `ignoreWarnings`
{#tasks-verifyplugin-ignorewarnings}

Specify whether the build should fail when the verifications performed by this task emit warnings.

{type="narrow"}
Type
: `Boolean`

Default value
: `true`


#### `ignoreUnacceptableWarnings`
{#tasks-verifyplugin-ignoreunacceptablewarnings}

Specify whether the build should fail when the verifications performed by this task emit unacceptable warnings.

{type="narrow"}
Type
: `Boolean`

Default value
: `false`


#### `pluginDir`
{#tasks-verifyplugin-plugindir}

The location of the built plugin file which will be used for verification.

{type="narrow"}
Type
: `File`

Default value
: `${prepareSandboxTask.destinationDir}/${prepareSandboxTask.pluginName}`


### `verifyPluginConfiguration`
{#tasks-verifypluginconfiguration}

Validates the plugin project configuration:

- The [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) property can't be lower than the major version of the currently used IntelliJ SDK set with the [`intellij.version`](#intellij-extension-version).
- The `sourceCompatibility` property of the Java configuration can't be lower than the Java version used for assembling the IntelliJ SDK specified by the [`intellij.version`](#intellij-extension-version).
- The `targetCompatibility` property of the Java configuration can't be higher than the Java version required for running IDE in the version specified by the [`intellij.version`](#intellij-extension-version) or [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) properties.
- The `kotlinJvmTarget` property of the Kotlin configuration (if used) can't be higher than the Java version required for running IDE in the version specified by the [`intellij.version`](#intellij-extension-version) or [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) properties.
- The `kotlinLanguageVersion` property of the Kotlin configuration (if used) can't be lower than the Kotlin bundled with IDE in the version specified by the [`intellij.version`](#intellij-extension-version) or [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) properties.
- The `kotlinApiVersion` property of the Kotlin configuration (if used) can't be higher than the Kotlin bundled with IDE in the version specified by the [`intellij.version`](#intellij-extension-version) or [`patchPluginXml.sinceBuild`](#tasks-patchpluginxml-sincebuild) properties.

> For more details regarding the Java version used in the specific IntelliJ SDK, see [](build_number_ranges.md).

- The dependency on the Kotlin Standard Library (stdlib) is automatically added when using the Gradle Kotlin plugin and may conflict with the version provided with the IntelliJ Platform.

> Read more about controlling this behavior on [](using_kotlin.md#kotlin-standard-library).

- An old default [`runPluginVerifier.downloadDir`](#tasks-runpluginverifier-downloaddir) path contains downloaded IDEs, but another default is in use. Links to the [FAQ section](tools_gradle_intellij_plugin_faq.md#the-plugin-verifier-download-directory-is-set-to-but-downloaded-ides-were-also-found-in)


### `verifyPluginSignature`
{#tasks-verifypluginsignature}

Validates the signature of the plugin archive file using the [marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer) library.

For more details, see [Plugin Signing](plugin_signing.md) article.

#### `certificateChain`
{#tasks-verifypluginsignature-certificatechain}

> Currently unavailable — please use [`verifyPluginSignature.certificateChainFile`](#tasks-verifypluginsignature-certificatechainfile) instead.
>
{style="note"}

{type="narrow"}
Type
: `String`

Default value
: `null`


#### `certificateChainFile`
{#tasks-verifypluginsignature-certificatechainfile}

A file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

By default, the certificate chain file is set to the value of the [`signPlugin.certificateChainFile`](#tasks-signplugin-certificatechainfile) property.
If absent, the [`signPlugin.certificateChain`](#tasks-signplugin-certificatechain) property is used instead, but due to the CLI tool limitations, a temporary file is created and the certificate chain is written to it.

{type="narrow"}
Type
: `File`

Default value
: [`signPlugin.certificateChainFile`](#tasks-signplugin-certificatechainfile)


#### `inputArchiveFile`
{#tasks-verifypluginsignature-inputarchivefile}

Input, signed ZIP archive file.
Refers to `in` CLI option.

Provided by the [`signPlugin`](#tasks-signplugin) task.
