[//]: # (title: Gradle IntelliJ Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Gradle IntelliJ Plugin is a plugin for the Gradle build system to help configuring your environment for building, testing, verifying, and publishing plugins for IntelliJ-based IDEs.

> Current Gradle IntelliJ Plugin version is ![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-intellij-plugin.svg?style=flat-square)
>
{type="note"}

This plugin allows you to build plugins for IntelliJ Platform using specified IntelliJ SDK and bundled or third-party plugins.

The plugin provides the functionalities like:
- adding extra IntelliJ-specific dependencies
- patching `processResources` tasks to fill some tags (name, version) in <path>[plugin.xml](plugin_configuration_file.md)</path> with appropriate values
- patching compile tasks to instrument code with nullability assertions and form classes made with IntelliJ GUI Designer
- additional build steps which might be helpful while developing plugins for IntelliJ platform

> Please see also [](tools_gradle_intellij_plugin_faq.md) and [](tools_gradle_intellij_plugin_examples.md).
>
> Before visiting the [Issue Tracker](https://github.com/JetBrains/gradle-intellij-plugin/issues), update both plugin and Gradle to the latest versions.
> Please see [CONTRIBUTING](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/CONTRIBUTING.md) on how to submit feedback and contribute to this project.
>
{type="tip"}

## Usage
To enable this plugin in your Gradle-based project, register the plugin in the Gradle build script's `plugins` section:

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

When upgrading to `1.x` version, please make sure to follow the [migration guide](https://lp.jetbrains.com/gradle-intellij-plugin) to adjust your existing build script.

> <control>Gradle JVM</control> must be set to Java 11 in <path>Settings/Preferences | Build, Execution, Deployment | Build Tools | Gradle</path>.
>
{type="tip"}

> This project requires Gradle 6.7.1 or newer. However, it is highly recommended to always use the latest available Gradle version.
> Update it with:
> ```Bash
> ./gradlew wrapper --gradle-version=VERSION
> ```
>
> See also: [Gradle Installation](https://gradle.org/install/) guide.
{type="tip"}

### Snapshot Release
The Snapshot release is a pre-release version built nightly from the latest main branch – as it is built every day using the same version number, it's not recommended to use it for production builds.

For switching to the snapshot release, point Gradle to the dedicated snapshot repository by adding an entry to the Gradle settings file.

> The current Gradle IntelliJ Plugin Snapshot version is ![GitHub Snapshot Release](https://img.shields.io/nexus/s/org.jetbrains.intellij/org.jetbrains.intellij.gradle.plugin?server=https://oss.sonatype.org)
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

It is mandatory to specify at least the [`intellij.version`](#intellij-extension-version) property.

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
{id="intellij-extension-version"}

All available JetBrains IDEs versions can be found at [IntelliJ Artifacts](intellij_artifacts.md) page.

The version of the IntelliJ Platform IDE that will be used to build the plugin.
Please see [](plugin_compatibility.md) for more details.

{style="narrow"}
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
>
{type="tip"}


### type
{id="intellij-extension-type"}

The type of the IntelliJ-based IDE distribution.
The type may also be specified as a prefix of the value for the [`intellij.version`](#intellij-extension-version) property instead.

{style="narrow"}
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
- `RD` - [Rider](rider.md)
- `GO` - [GoLand](goland.md)
- `JPS` - JPS-only
- `GW` - Gateway

To build against IDEs not supported directly by `type`, please see their corresponding page in _Part VIII — Product Specific_.

### pluginName
{id="intellij-extension-pluginname"}

The plugin name part used in the generated ZIP distribution: <path>build/distributions/PluginName-1.0.0.zip</path> and name of the plugin directory in the sandbox directory.

{style="narrow"}
Type
: `String`

Default value
: `${project.name}`


### localPath
{id="intellij-extension-localpath"}

The path to the locally installed IDE distribution that should be used to build the plugin.
Using the `intellij.localPath` allows to build the plugin using an IDE that is not available in [](intellij_artifacts.md).

{style="narrow"}
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
{type="warning"}


### localSourcesPath
{id="intellij-extension-localsourcespath"}

The path to local archive with IDE sources.
Used for resolving source files of the locally installed IDE distribution when [`intellij.localPath`](#intellij-extension-localpath) is specified.

{style="narrow"}
Type
: `String`

Default value
: `null`


### plugins
{id="intellij-extension-plugins"}

The list of bundled IDE plugins and plugins from [JetBrains Marketplace](https://plugins.jetbrains.com) or configured [`intellij.pluginsRepositories`](#intellij-extension-pluginsrepositories).

Please see [](plugin_dependencies.md) for more details.

Notes:
- For plugins from [JetBrains Marketplace](https://plugins.jetbrains.com), use format `pluginId:version`.
- For bundled plugins, version must be omitted: e.g. `org.intellij.groovy`.
- For subprojects, use project reference `project(':subproject')`.
- If you need to refer plugin's classes from your project, you also have to define a dependency in your <path>plugin.xml</path> file, see [](plugin_dependencies.md).

{style="narrow"}
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
  - `project(":projectName")`
  - `project(":plugin-subproject")`


### updateSinceUntilBuild
{id="intellij-extension-updatesinceuntilbuild"}

Enables patching <path>[plugin.xml](plugin_configuration_file.md)</path> with the values of [`patchPluginXml.sinceBuild`](#patchpluginxml-task-sincebuild) and [`patchPluginXml.untilBuild`](#patchpluginxml-task-untilbuild) properties.

{style="narrow"}
Type
: `Boolean`

Default value
: `true`


### sameSinceUntilBuild
{id="intellij-extension-samesinceuntilbuild"}

Enables patching <path>[plugin.xml](plugin_configuration_file.md)</path> with the [`patchPluginXml.untilBuild`](#patchpluginxml-task-untilbuild) using value of [`patchPluginXml.sinceBuild`](#patchpluginxml-task-sincebuild) with `*` wildcard, like `sinceBuild.*`, e.g., `221.*`.

Notes:
- Useful for building plugins against EAP builds.
- If [`patchPluginXml.untilBuild`](#patchpluginxml-task-untilbuild) has a value set, then [`intellij.sameSinceUntilBuild`](#intellij-extension-samesinceuntilbuild) is ignored.

{style="narrow"}
Type
: `Boolean`

Default value
: `false`


### instrumentCode
{id="intellij-extension-instrumentcode"}

Enables the instrumentation of Java classes with [nullability](https://www.jetbrains.com/help/idea/nullable-and-notnull-annotations.html) assertions and compilation of forms created by [IntelliJ GUI Designer](https://www.jetbrains.com/help/idea/gui-designer-basics.html).

{style="narrow"}
Type
: `Boolean`

Default value
: `true`


### sandboxDir
{id="intellij-extension-sandboxdir"}

The path of [sandbox directory](ide_development_instance.md#the-development-instance-sandbox-directory) that is used for running IDE with developed plugin.

{style="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/idea-sandbox`


### intellijRepository
{id="intellij-extension-intellijrepository"}

The IntelliJ-based IDE distributions repository URL.

{style="narrow"}
Type
: `String`

Default value
: `https://cache-redirector.jetbrains.com/www.jetbrains.com/intellij-repository`


### pluginsRepositories
{id="intellij-extension-pluginsrepositories"}

Configures repositories for downloading plugin dependencies.

{style="narrow"}
Type
: `PluginsRespositoryConfiguration`

Default value
: `pluginsRepositories { marketplace() }`

Acceptable values
:
- `marketplace()` - use Maven repository with plugins listed in [JetBrains Marketplace](https://plugins.jetbrains.com)
- `maven(repositoryUrl)` - use custom Maven repository with plugins
- `maven { repositoryUrl }` - use custom Maven repository with plugins where you can configure additional parameters (credentials, authentication and etc.)
- `custom(pluginsXmlUrl)` - use [custom plugin repository](update_plugins_format.md)


### jreRepository
{id="intellij-extension-jrerepository"}

URL of repository for downloading [JetBrains Runtime](ide_development_instance.md#using-a-jetbrains-runtime-for-the-development-instance).

{style="narrow"}
Type
: `String`

Default value
: `null`


### ideaDependencyCachePath
{id="intellij-extension-ideadependencycachepath"}

Path to the directory where IDE dependency cache is stored.
If not set, the dependency will be extracted next to the downloaded ZIP archive in [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home) directory.

{style="narrow"}
Type
: `String`

Default value
: `null`


### downloadSources
{id="intellij-extension-downloadsources"}

Enables downloading the IntelliJ Platform sources.
It is enabled by default if the `CI` environment variable is not set – which is present on Continuous Integration environments, like GitHub Actions, TeamCity, and others.

{style="narrow"}
Type
: `Boolean`

Default value
: `!System.getenv().containsKey("CI")`


### configureDefaultDependencies
{id="intellij-extension-configuredefaultdependencies"}

Enables configuration of the default IntelliJ Platform dependencies in the current project.
Otherwise, the `DependenciesUtils.intellij()`, `DependenciesUtils.intellijPlugin()`, and `DependenciesUtils.intellijPlugins()` functions could be used for an explicit configuration.

{style="narrow"}
Type
: `Boolean`

Default value
: `true`


### extraDependencies
{id="intellij-extension-extradependencies"}

Configures extra dependency artifacts from the IntelliJ repository.
The dependencies on them could be configured only explicitly using the `DependenciesUtils.intellijExtra()` function in the `dependencies` block.

{style="narrow"}
Type
: `List<String>`

Default value
: `[]`


## buildPlugin Task
Assembles a plugin and prepares ZIP archive for [deployment](deployment.md).

### archiveBaseName
{id="buildplugin-task-archivebasename"}

The base name of the ZIP archive.

This task is preconfigured automatically and takes the output artifacts of [`prepareSandbox`](#preparesandbox-task) and [`jarSearchableOptions`](#jarsearchableoptions-task) tasks as an input.

{style="narrow"}
Type
: `String`

Default value
: `${prepareSandboxTask.pluginName}`


## buildSearchableOptions Task
Builds an index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options provided by the plugin's [](settings.md).

Note, that this is a [`runIde`](#runide-task)-based task with predefined arguments and all properties of the [`runIde`](#runide-task) task are also applied to [`buildSearchableOptions`](#buildsearchableoptions-task) tasks.

> If your plugin doesn't implement custom settings, it is recommended to [disable it](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options).
> See also [`noSearchableOptionsWarning`](#build-features-nosearchableoptionswarning) build feature.
>
{type="tip"}


### outputDir
{id="buildsearchableoptions-task-outputdir"}

{style="narrow"}
Type
: `File`

Default value
: `build/searchableOptions`


## downloadRobotServerPlugin Task
Downloads `robot-server` plugin.
The `robot-server` plugin is required for running the UI tests using the [`runIdeForUiTests`](#runideforuitests-task) task.


### version
{id="downloadrobotserverplugin-task-version"}

The version of the Robot Server Plugin to download.

{style="narrow"}
Type
: `String`

Default value
: `LATEST`


### pluginArchive
{id="downloadrobotserverplugin-task-pluginarchive"}

The Robot Server Plugin archive, downloaded by default to the [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home).

{style="narrow"}
Type
: `File`

Default value
: [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


### outputDir
{id="downloadrobotserverplugin-task-outputdir"}

Location of the extracted archive.

{style="narrow"}
Type
: `File`

Default value
: `build/robotServerPlugin`


## instrumentCode Task
The following attributes help you to tune instrumenting behaviour in `instrumentCode { ... }` block.


### compilerVersion
{id="instrumentcode-task-compilerversion"}

A version of instrumenting compiler.
It's used in cases when targeting non-IntelliJ IDEA IDEs (e.g. [CLion](clion.md) or [Rider](rider.md)).

{style="narrow"}
Type
: `String`

Default value
: Build number of the IDE dependency


## jarSearchableOptions Task
Creates a JAR file with searchable options to be distributed with the plugin.


### outputDir
{id="jarsearchableoptions-task-outputdir"}

The output directory where the JAR file will be created.

{style="narrow"}
Type
: `String`

Default value
: `build/searchableOptions`


### pluginName
{id="jarsearchableoptions-task-pluginname"}

The name of the plugin.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.pluginName`](#intellij-extension-pluginname)


### sandboxDir
{id="jarsearchableoptions-task-sandboxdir"}

The sandbox output directory.

{style="narrow"}
Type
: `String`

Default value
: [`prepareSandbox.outputDir`](#preparesandbox-task)


## listProductsReleases Task
List all available IntelliJ-based IDE releases with their updates.
The result list is used for testing the plugin with Plugin Verifier using the [`runPluginVerifier`](#runpluginverifier-task) task.

Plugin Verifier requires a list of the IDEs that will be used for verifying your plugin build against.
The availability of the releases may change in time, i.e., due to security issues in one version – which will be later removed and replaced with an updated IDE release.

With the [`listProductsReleases`](#listproductsreleases-task) task, it is possible to list the currently available IDEs matching given conditions, like platform types, since/until release versions.
Such a list is fetched from the remote updates file: `https://www.jetbrains.com/updates/updates.xml`, parsed and filtered considering the specified [`listProductsReleases.types`](#listproductsreleases-task-types), [`listProductsReleases.sinceVersion`](#listproductsreleases-task-sinceversion), [`listProductsReleases.untilVersion`](#listproductsreleases-task-untilversion) (or [`listProductsReleases.sinceBuild`](#listproductsreleases-task-sincebuild), [`listProductsReleases.untilBuild`](#listproductsreleases-task-untilbuild)) properties.

The result list is stored within the [`listProductsReleases.outputFile`](#listproductsreleases-task-outputfile), which is used as a source for the Plugin Verifier if the [`runPluginVerifier`](#runpluginverifier-task) task has no [`runPluginVerifier.ideVersions`](#runpluginverifier-task-ideversions) property specified, the output of the [`listProductsReleases`](#listproductsreleases-task) task is used.


### updatesFile
{id="listproductsreleases-task-updatesfile"}

Path to the products releases update file.

{style="narrow"}
Type
: `List<String>`

Default value
: [Gradle cache](https://docs.gradle.org/current/userguide/directory_layout.html#dir:gradle_user_home)


### types
{id="listproductsreleases-task-types"}

List of types of IDEs that will be listed in results.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.type`](#intellij-extension-type)


### sinceVersion
{id="listproductsreleases-task-sinceversion"}

Lower boundary of the listed results in product marketing version format, e.g., `2020.2.1`.
It takes precedence over [`listProductsReleases.sinceBuild`](#listproductsreleases-task-sincebuild) property.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version)


### untilVersion
{id="listproductsreleases-task-untilversion"}

Upper boundary of the listed results in product marketing version format, e.g., `2020.2.1`.
It takes precedence over [`listProductsReleases.untilBuild`](#listproductsreleases-task-untilbuild) property.

{style="narrow"}
Type
: `String`

Default value
: `null`


### sinceBuild
{id="listproductsreleases-task-sincebuild"}

Lower boundary of the listed results in build number format, like `192`.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version)


### untilBuild
{id="listproductsreleases-task-untilbuild"}

Upper boundary of the listed results in build number format, like `192`.

{style="narrow"}
Type
: `String`

Default value
: `null`


### releaseChannels
{id="listproductsreleases-task-releasechannels"}

Release channels that product updates will be filtered with.

{style="narrow"}
Type
: `Channel`

Default value
: `EnumSet.allOf(ListProductsReleasesTask.Channel)`


### outputFile
{id="listproductsreleases-task-outputfile"}

Path to the file, where the output list will be stored.

{style="narrow"}
Type
: `File`

Default value
: `File("${project.buildDir}/listProductsReleases.txt")`


### androidStudioUpdatePath
{id="listproductsreleases-task-androidstudioupdatepath"}

For [Android Studio releases](android_studio_releases_list.md), a separated storage for the updates is used.

{style="narrow"}
Type
: `String`

Default value
: `https://raw.githubusercontent.com/JetBrains/intellij-sdk-docs/main/topics/_generated/android_studio_releases.xml`


## patchPluginXml Task
Patches <path>[plugin.xml](plugin_configuration_file.md)</path> files with values provided to the task.

> To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).
>
{type="tip"}


### destinationDir
{id="patchpluginxml-task-destinationdir"}

The directory where the patched <path>[plugin.xml](plugin_configuration_file.md)</path> will be written.

{style="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/patchedPluginXmlFiles`


### pluginXmlFiles
{id="patchpluginxml-task-pluginxmlfiles"}

The list of <path>[plugin.xml](plugin_configuration_file.md)</path> files to patch.

{style="narrow"}
Type
: `List<File>`

Default value
: auto-discovered from the project


### pluginDescription
{id="patchpluginxml-task-plugindescription"}

The description of the plugin used in the `<description>` tag.

{style="narrow"}
Type
: `String`

Default value
: `null`


### sinceBuild
{id="patchpluginxml-task-sincebuild"}

The lower bound of the version range to be patched used in the `since-build` attribute of the `<idea-version>` tag.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version) in `Branch.Build.Fix` format


### untilBuild
{id="patchpluginxml-task-untilbuild"}

The upper bound of the version range to be patched used in the `until-build` attribute of the `<idea-version>` tag.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.version`](#intellij-extension-version) in `Branch.Build.*` format


### version
{id="patchpluginxml-task-version"}

The version of the plugin used in the `<version>` tag.

{style="narrow"}
Type
: `String`

Default value
: `${project.version}`


### changeNotes
{id="patchpluginxml-task-changenotes"}

The change notes of the plugin used in the `<change-notes>` tag.

{style="narrow"}
Type
: `String`

Default value
: `null`


### pluginId
{id="patchpluginxml-task-pluginid"}

The ID of the plugin used in the `<id>` tag.

{style="narrow"}
Type
: `String`

Default value
: `null`


## prepareSandbox Task
Prepares sandbox directory with installed plugin and its dependencies.


### pluginName
{id="preparesandbox-task-pluginname"}

The name of the plugin.

{style="narrow"}
Type
: `String`

Default value
: [`intellij.pluginName`](#intellij-extension-pluginname)


### configDir
{id="preparesandbox-task-configdir"}

The directory with the plugin configuration.

{style="narrow"}
Type
: `String`

Default value
: `${intellij.pluginName}/config`


### pluginJar
{id="preparesandbox-task-pluginjar"}

The input plugin JAR file used to prepare the sandbox.

{style="narrow"}
Type
: `File`

Default value
: output of the `jar` task


### librariesToIgnore
{id="preparesandbox-task-librariestoignore"}

Libraries that will be ignored when preparing the sandbox.
By default, excludes all libraries that are a part of the [`setupDependenciesTask.idea`](#setupdependencies-task-idea) dependency.

{style="narrow"}
Type
: `List<File>`

Default value
: `org.jetbrains.intellij.tasks.SetupDependenciesTask.idea.get().jarFiles`


### pluginDependencies
{id="preparesandbox-task-plugindependencies"}

List of dependencies of the current plugin.

{style="narrow"}
Type
: `List<PluginDependency>`

Default value
: `org.jetbrains.intellij.IntelliJPluginExtension.getPluginDependenciesList`


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

See [](deployment.md) tutorial for step-by-step instructions.


### token
{id="publishplugin-task-token"}

Authentication token.

**Required**

{style="narrow"}
Type
: `String`

Default value
: `null`


### channels
{id="publishplugin-task-channels"}

List of channel names to upload plugin to.

{style="narrow"}
Type
: `List<String>`

Default value
: `["default"]`


### host
{id="publishplugin-task-host"}

URL host of a plugin repository.

{style="narrow"}
Type
: `String`

Default value
: [JetBrains Marketplace](https://plugins.jetbrains.com)


### distributionFile
{id="publishplugin-task-distributionfile"}

ZIP file of plugin to upload.

{style="narrow"}
Type
: `File`

Default value
: output of the [`buildPlugin`](#buildplugin-task) task


### toolboxEnterprise
{id="publishplugin-task-toolboxenterprise"}

Specifies if the Toolbox Enterprise plugin repository service should be used.
This feature is still in the incubating phase and is not yet available for public use.

{style="narrow"}
Type
: `Boolean`

Default value
: `false`


## runIde Task
Runs the IDE instance with the developed plugin installed.

`runIde` tasks extend the [`JavaExec`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html) Gradle task – all properties available in the `JavaExec` as well as the following ones can be used to configure the `runIde` task.


### ideDir
{id="runide-task-idedir"}

The IDE dependency sources path.
Configured automatically with the [`setupDependencies.idea`](#setupdependencies-task-idea) dependency.

{style="narrow"}
Type
: `File`

Default value
: [`setupDependencies.idea`](#setupdependencies-task-idea)


### jbrVersion
{id="runide-task-jbrversion"}

Custom JetBrains Runtime (JBR) version to use for running the IDE.

{style="narrow"}
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
{type="note"}

### jbrVariant
{id="runide-task-jbrvariant"}

JetBrains Runtime (JBR) variant to use when running the IDE with the plugin.

{style="narrow"}
Type
: `String`

Default value
: `null`


### pluginsDir
{id="runide-task-pluginsdir"}

Path to the `plugins` directory within the sandbox prepared with the [`prepareSandbox`](#preparesandbox-task) task.
Provided to the `idea.plugins.path` system property.

{style="narrow"}
Type
: `Directory`

Default value
: [`prepareSandbox.destinationDir`](#preparesandbox-task)


### autoReloadPlugins
{id="runide-task-autoreloadplugins"}

Enables auto-reload of dynamic plugins.
Dynamic plugins will be reloaded automatically when their JARs are modified.
This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.
Enabled by default in 2020.2 and higher.

See [Enabling Auto-Reload](ide_development_instance.md#enabling-auto-reload) for more details.

{style="narrow"}
Type
: `Boolean`

Default value
: `true`


## runIdeForUiTests Task
Runs the IDE instance with the developed plugin and robot-server installed and ready for UI testing.

See [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) project.

See [`runIde`](#runide-task) task for more details.


## runIdePerformanceTest Task
Runs performance tests on the IDE with the developed plugin installed.

The `runIdePerformanceTest` task extends the `RunIdeBase` task, so all configuration attributes of `JavaExec` and [`runIde`](#runide-task) tasks can be used in the `runIdePerformanceTest` as well.
See [`runIde`](#runide-task) task for more details.

Currently, the task is under adaptation; more documentation will be added in the future.

### testDataDir
{id="runideperformancetest-task-testdatadir"}

Path to directory with test projects and <path>.ijperf</path> files.

{style="narrow"}
Type
: `String`

Default value
: `null`


### artifactsDir
{id="runideperformancetest-task-artifactsdir"}

Path to directory where performance test artifacts (IDE logs, snapshots, screenshots, etc.) will be stored.
If the directory doesn't exist, it will be created.

{style="narrow"}
Type
: `String`

Default value
: `null`


### profilerName
{id="runideperformancetest-task-profilername"}

Name of the profiler which will be used during execution.

{style="narrow"}
Type
: `ProfilerName`

Default value
: `ProfilerName.ASYNC`

Acceptable values
:
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
{id="runpluginverifier-task-ideversions"}

IDEs to check, in [`intellij.version`](#intellij-extension-version) format, i.e.: `["IC-2019.3.5", "PS-2019.3.2"]`.
Check the available build versions on [IntelliJ Platform Builds list](https://jb.gg/intellij-platform-builds-list).

{style="narrow"}
Type
: `List<String>`

Default value
: output of the [`listProductsReleases`](#listproductsreleases-task) task


### verifierVersion
{id="runpluginverifier-task-verifierversion"}

IntelliJ Plugin Verifier version.
Do not change unless absolutely required.

{style="narrow"}
Type
: `String`

Default value
: `LATEST`


### verifierPath
{id="runpluginverifier-task-verifierpath"}

Local path to the pre-downloaded IntelliJ Plugin Verifier JAR file.
If set, [`runPluginVerifier.verifierVersion`](#runpluginverifier-task-verifierversion) is ignored.

{style="narrow"}
Type
: `String`

Default value
: path to the JAR file resolved using the [`runPluginVerifier.verifierVersion`](#runpluginverifier-task-verifierversion) property


### localPaths
{id="runpluginverifier-task-localpaths"}

A list of the paths to locally installed IDE distributions that should be used for verification in addition to those specified in [`runPluginVerifier.ideVersions`](#runpluginverifier-task-ideversions).

{style="narrow"}
Type
: `List<File>`

Default value
: `[]`


### distributionFile
{id="runpluginverifier-task-distributionfile"}

ZIP file of the plugin to verify.
If empty, the task will be skipped.

{style="narrow"}
Type
: `File`

Default value
: output of the `buildPlugin` task


### failureLevel
{id="runpluginverifier-task-failurelevel"}

Defines the verification level at which the task should fail if any reported issue matches.
Can be set as `FailureLevel` enum or `EnumSet<FailureLevel>`.

{style="narrow"}
Type
: `org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel`

Default value
: `FailureLevel.COMPATIBILITY_PROBLEMS`

Accepted values
:
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
- `FailureLevel.ALL` - All of the above
- `FailureLevel.NONE` - None of above


### verificationReportsDir
{id="runpluginverifier-task-verificationreportsdir"}

The path to directory where verification reports will be saved.

{style="narrow"}
Type
: `String`

Default value
: `${project.buildDir}/reports/pluginVerifier`


### downloadDir
{id="runpluginverifier-task-downloaddir"}

The path to directory where IDEs used for the verification will be downloaded.

{style="narrow"}
Type
: `String`

Default value
: `System.getProperty("plugin.verifier.home.dir")/ides` or `System.getProperty("user.home")/.pluginVerifier/ides` or system temporary directory.


### jbrVersion
{id="runpluginverifier-task-jbrversion"}

Custom JetBrains Runtime (JBR) version to use for running the verification.

{style="narrow"}
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
{type="note"}


### jbrVariant
{id="runpluginverifier-task-jbrvariant"}

JetBrains Runtime (JBR) variant to use when running the verification.

{style="narrow"}
Type
: `String`

Default value
: `null`


### runtimeDir
{id="runpluginverifier-task-runtimedir"}

The path to directory containing JVM runtime, overrides [`runPluginVerifier.jbrVersion`](#runpluginverifier-task-jbrversion).

{style="narrow"}
Type
: `String`

Default value
: `null`


### externalPrefixes
{id="runpluginverifier-task-externalprefixes"}

The list of classes prefixes from the external libraries.
The Plugin Verifier will not report `No such class` for classes of these packages.

{style="narrow"}
Type
: `List<String>`

Default value
: `[]`


### teamCityOutputFormat
{id="runpluginverifier-task-teamcityoutputformat"}

A flag that controls the output format - if set to `true`, the [TeamCity Tests Format](https://www.jetbrains.com/help/teamcity/service-messages.html) – the TeamCity compatible output will be returned to stdout.

{style="narrow"}
Type
: `Boolean`

Default value
: `false`


### subsystemsToCheck
{id="runpluginverifier-task-subsystemstocheck"}

Specifies which subsystems of IDE should be checked.

{style="narrow"}
Type
: `String`

Default value
: `all`

Acceptable values
:
- `all`
- `android-only`
- `without-android`


## setupDependencies Task
Setups required dependencies for building and running project.
This task is automatically added to the ["After Sync" Gradle trigger](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html#config_triggers_gradle) to make the IntelliJ SDK dependency available for IntelliJ IDEA right after the Gradle synchronization.

> After removing the Gradle IntelliJ Plugin from your project, the `Task 'setupDependencies' not found in root project` exception may occur.
> See [Frequently Asked Questions](tools_gradle_intellij_plugin_faq.md#task-setupdependencies-not-found-in-root-project) for more details.
>
{type="warning"}


### idea
{id="setupdependencies-task-idea"}

This task exposes the `setupDependencies.idea` property which contains a reference to the resolved IDE dependency used for building the plugin.

This property can be referred in Gradle configuration to access IDE dependency classpath.


## signPlugin Task
Signs the ZIP archive with the provided key using [marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer) library.

To sign the plugin before publishing to [JetBrains Marketplace](https://plugins.jetbrains.com) with the [`signPlugin`](#signplugin-task) task, it is required to provide a certificate chain and a private key with its password using `signPlugin { ... }` Plugin Signing DSL.

As soon as [`signPlugin.privateKey`](#signplugin-task-privatekey) (or [`signPlugin.privateKeyFile`](#signplugin-task-privatekeyfile)) and [`signPlugin.certificateChain`](#signplugin-task-certificatechain) (or [`signPlugin.certificateChainFile`](#signplugin-task-certificatechainfile)) properties are specified, the task will be executed automatically right before the [`publishPlugin`](#publishplugin-task) task.

For more details, see [Plugin Signing](plugin_signing.md) article.

### certificateChain
{id="signplugin-task-certificatechain"}

A string containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### certificateChainFile
{id="signplugin-task-certificatechainfile"}

A file containing X509 certificates.
The first certificate from the chain will be used as a certificate authority (CA).
Refers to `cert-file` CLI option.

{style="narrow"}
Type
: `File`

Default value
: `null`


### privateKey
{id="signplugin-task-privatekey"}

Encoded private key in PEM format.
Refers to `key` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### privateKeyFile
{id="signplugin-task-privatekeyfile"}

A file with encoded private key in PEM format.
Refers to `key-file` CLI option.

{style="narrow"}
Type
: `File`

Default value
: `null`


### password
{id="signplugin-task-password"}

Password required to decrypt the private key.
Refers to `key-pass` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### cliVersion
{id="signplugin-task-cliversion"}

Returns the version of [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) that will be used.

{style="narrow"}
Type
: `String`

Default value
: `LATEST`


### cliPath
{id="signplugin-task-clipath"}

Path to [JetBrains Marketplace ZIP Signer CLI](https://github.com/JetBrains/marketplace-zip-signer) file.
Takes precedence over [`signPlugin.cliVersion`](#signplugin-task-cliversion).

{style="narrow"}
Type
: `String`

Default value
: `null`


### keyStore
{id="signplugin-task-keystore"}

KeyStore file path.
Refers to `ks` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### keyStorePassword
{id="signplugin-task-keystorepassword"}

KeyStore password.

{style="narrow"}
Type
: `String`

Default value
: `null`


### keyStoreKeyAlias
{id="signplugin-task-keystorekeyalias"}

KeyStore key alias.
Refers to `ks-key-alias` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### keyStoreType
{id="signplugin-task-keystoretype"}

KeyStore type.

{style="narrow"}
Type
: `String`

Default value
: `null`


### keyStoreProviderName
{id="signplugin-task-keystoreprovidername"}

JCA KeyStore Provider name.
Refers to `ks-provider-name` CLI option.

{style="narrow"}
Type
: `String`

Default value
: `null`


### inputArchiveFile
{id="signplugin-task-inputarchivefile"}

Input, unsigned ZIP archive file.
Refers to `in` CLI option.

Provided by the [`buildPlugin`](#buildplugin-task) task.

### outputArchiveFile
{id="signplugin-task-outputarchivefile"}

Output, signed ZIP archive file.
Refers to `out` CLI option.

Predefined with the name of the ZIP archive file with `-signed` name suffix attached.

{style="narrow"}
Type
: `File`


## verifyPlugin Task
Validates completeness and contents of <path>[plugin.xml](plugin_configuration_file.md)</path> descriptors as well as plugin archive structure.


### ignoreFailures
{id="verifyplugin-task-ignorefailures"}

Specifies whether the build should fail when the verifications performed by this task fail.

{style="narrow"}
Type
: `Boolean`

Default value
: `false`


### ignoreWarnings
{id="verifyplugin-task-ignorewarnings"}

Specifies whether the build should fail when the verifications performed by this task emit warnings.

{style="narrow"}
Type
: `Boolean`

Default value
: `true`


### pluginDir
{id="verifyplugin-task-plugindir"}

The location of the built plugin file which will be used for verification.

{style="narrow"}
Type
: `File`

Default value
: `${prepareSandboxTask.destinationDir}/${prepareSandboxTask.pluginName}`



## Build Features
With ongoing Gradle IntelliJ Plugin releases, new features are introduced that require additional research, collecting more feedback from developers, or should be enabled or disabled under particular conditions.
Build Features are an implementation of the feature flags concept and let you control some behaviors of the Gradle IntelliJ Plugin.

To enable or disable a particular feature, add a Project property to the <path>gradle.properties</path> file with the following pattern:

```properties
org.jetbrains.intellij.buildFeature.<buildFeatureName>=<true|false>
```

E.g., to disable the [selfUpdateCheck](#build-features-selfupdatecheck) feature, add this line:

```properties
org.jetbrains.intellij.buildFeature.selfUpdateCheck=false
```


### noSearchableOptionsWarning
{id="build-features-nosearchableoptionswarning"}

> Available since the upcoming release
>
{type="warning"}

When the [`buildSearchableOptions`](#buildsearchableoptions-task) doesn't produce any results, e.g., when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest [disabling the task](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options) for better performance.

{style="narrow"}
Default value
: `true`


### paidPluginSearchableOptionsWarning
{id="build-features-paidpluginsearchableoptionswarning"}

> Available since the upcoming release
>
{type="warning"}

Due to IDE limitations, it is impossible to run the IDE in headless mode to collect searchable options for a paid plugin.
As paid plugins require providing a valid license and presenting a UI dialog, it is impossible to handle such a case, and the task will fail.
This feature flag displays the given warning when the task is run by a paid plugin.

{style="narrow"}
Default value
: `true`


### selfUpdateCheck
{id="build-features-selfupdatecheck"}

Check if the currently used Gradle IntelliJ Plugin is outdated.

{style="narrow"}
Default value
: `true`


### useDependencyFirstResolutionStrategy
{id="build-features-usedependencyfirstresolutionstrategy"}

> Available since the upcoming release
>
{type="warning"}

Set the `ResolutionStrategy.SortOrder.DEPENDENCY_FIRST` resolution strategy for `compileClasspath` and `testCompileClasspath` Gradle configurations.
This behaviour allows for making the dependencies specified in the project's Gradle configuration considered before the dependencies added by the Gradle IntelliJ Plugin.
See [JetBrains/gradle-intellij-plugin#656](https://github.com/JetBrains/gradle-intellij-plugin/issues/656) for more details.

{style="narrow"}
Default value
: `true`
