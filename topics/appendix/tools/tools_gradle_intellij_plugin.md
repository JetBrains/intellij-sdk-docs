[//]: # (title: Gradle IntelliJ Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The Gradle IntelliJ Plugin is a plugin for the Gradle build system to help configuring your environment for building and testing plugins for IntelliJ-based IDEs.

> Current Gradle IntelliJ Plugin version is: **%GradleIntelliJPluginVersion%**
>
{type="note"}


When upgrading to `1.x` version, please make sure to follow [migration guide](https://lp.jetbrains.com/gradle-intellij-plugin) to adjust your existing build script.

This plugin allows you to build plugins for IntelliJ Platform using specified IntelliJ SDK and bundled or third-party plugins.

The plugin adds extra IntelliJ-specific dependencies, patches `processResources` tasks to fill some tags (name, version) in `plugin.xml` with appropriate values, patches compile tasks to instrument code with nullability assertions and forms classes made with IntelliJ GUI Designer and provides some build steps which might be helpful while developing plugins for IntelliJ platform.

> Gradle JVM should be set to `Java 11` (see <path>Settings/Preferences | Build, Execution</path>, <path>Deployment | Build Tools | Gradle</path>)
>
{type="tip"}

> This project requires `Gradle 6.7` or newer, however it is recommended to use the latest Gradle available. Update it with:
> ```Bash
> ./gradlew wrapper --gradle-version=VERSION
> ```
>
{type="tip"}


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

### Snapshot Release

The Snapshot release is a pre-release version built nightly from the latest main branch.
To use it, it is required to point Gradle to the dedicated snapshot repository by adding an entry to the Gradle settings file.

> Current Gradle IntelliJ Plugin Snapshot version is: **%GradleIntelliJPluginSnapshotVersion%**
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
    type.set("IC")
    pluginName.set("My Plugin")
    localPath.set("/Applications/Android Studio 4.2 Preview.app/Contents")
    plugins.set(listOf("com.jetbrains.php:192.6603.42"))
    updateSinceUntilBuild.set(true)
    sameSinceUntilBuild.set(false)
    instrumentCode.set(true)
    sandboxDir.set("${project.buildDir}/idea-sandbox")
    intellijRepository.set("https://cache-redirector.jetbrains.com/www.jetbrains.com/intellij-repository")
    pluginsRepositories {
        marketplace()
    }
    jreRepository.set(null)
    ideaDependencyCachePath.set("${project.buildDir}/idea-dependency-cache")
    downloadSources.set(true)
    configureDefaultDependencies.set(true)
    extraDependencies.set(listOf("jps-build-test"))
    localSourcesPath.set("${project.buildDir}/idea-sources")
}
```

</tab>
<tab title="Groovy">

```groovy
intellij {
    version = "2022.1.1"
    type = "IC"
    pluginName = "My Plugin"
    localPath = "/Applications/Android Studio 4.2 Preview.app/Contents"
    plugins = ["com.jetbrains.php:192.6603.42"]
    updateSinceUntilBuild = true
    sameSinceUntilBuild = false
    instrumentCode = true
    sandboxDir = "${project.buildDir}/idea-sandbox"
    intellijRepository = "https://cache-redirector.jetbrains.com/www.jetbrains.com/intellij-repository"
    pluginsRepositories {
        marketplace()
    }
    jreRepository = null
    ideaDependencyCachePath = "${project.buildDir}/idea-dependency-cache"
    downloadSources = true
    configureDefaultDependencies = true
    extraDependencies = ["jps-build-test"]
    localSourcesPath = "${project.buildDir}/idea-sources"
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


### type
The type of the IntelliJ-based IDE distribution.
The type may also be specified as a prefix of the value for the `intellij.version` property.

**Type:** `String`

**Default value:** `IC`

**Acceptable values:**
- `IC` - IntelliJ IDEA Community Edition
- `IU` - IntelliJ IDEA Ultimate Edition
- `CL` - CLion
- `PY` - PyCharm Professional Edition
- `PC` - PyCharm Community Edition
- `RD` - Rider
- `GO` - GoLand
- `JPS` - JPS-only
- `GW` - Gateway


### pluginName
The name of the generated ZIP archive distribution: `/build/distributions/PluginName-1.0.0.zip`.

**Type:** `String`

**Default value:** `${project.name}`


### localPath
The path to the locally installed IDE distribution that should be used to build the plugin.

**Type:** `String`

**Default value:** `null`

**Acceptable values:**
- `C:\Users\user\AppData\Local\JetBrains\Toolbox\apps\IDEA-U\ch-0\211.7142.45`
- `/Applications/Android Studio 4.2 Preview.app/Contents`
- `/home/user/idea-IC-181.4445.78`

> `intellij.version` and `intellij.localPath` should not be specified at the same time.
>
{type="warning"}


### plugins
The list of bundled IDE plugins and plugins from the [JetBrains Marketplace](https://plugins.jetbrains.com) or configured `intellij.pluginsRepositories`.

Please see [Plugin Dependencies](plugin_dependencies.md) for more details.

Notes:
- For plugins from JetBrains Plugin Repository, use format `pluginId:version`.
- For bundled plugins, version should be omitted: e.g. `org.intellij.groovy`.
- For subprojects, use project reference `project(':subproject')`.
- If you need to refer plugin's classes from your project, you also have to define a dependency in your `plugin.xml` file.

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
Defines if the `plugin.xml` should be patched with the values of `patchPluginXml.sinceBuild` and `patchPluginXml.untilBuild` properties.

**Type:** `Boolean`

**Default value:** `true`


### sameSinceUntilBuild
Patches `plugin.xml` with the `patchPluginXml.untilBuild` with the value of `patchPluginXml.sinceBuild` used with `*` wildcard, like `sinceBuild.*`, e.g., `220.*`.

Notes:
- Useful for building plugins against EAP IDE builds.
- If `patchPluginXml.untilBuild` has a value set, then `intellij.sameSinceUntilBuild` is ignored.

**Type:** `Boolean`

**Default value:** `false`


### instrumentCode
Instrument Java classes with nullability assertions and compile forms created by IntelliJ GUI Designer.

**Type:** `Boolean`

**Default value:** `true`


### sandboxDir
The path of sandbox directory that is used for running IDE with developed plugin.

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
- `marketplace()` - use Maven repository with plugins listed in the JetBrains Marketplace
- `maven(repositoryUrl)` - use custom Maven repository with plugins
- `maven { repositoryUrl }` - use custom Maven repository with plugins where you can configure additional parameters (credentials, authentication and etc.)
- `custom(pluginsXmlUrl)` - use custom plugin repository


### jreRepository
URL of repository for downloading JetBrains Runtime.

**Type:** `String`

**Default value:** `null`


### ideaDependencyCachePath
Path to the directory where IntelliJ IDEA dependency cache is stored.
If not set, the dependency will be extracted next to the downloaded ZIP archive in Gradle cache directory.

**Type:** `String`

**Default value:** `null`


### downloadSources

Download IntelliJ Platform sources.
Enabled by default if not on `CI` environment.

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

### localSourcesPath

The path to local archive with IDE sources.

**Type:** `String`

**Default value:** `null`



## Tasks
The Gradle IntelliJ Plugin provides a number of tasks that let you configure, build, test, and run your plugin locally or on CI.


### buildPlugin
Assembles plugin and prepares ZIP archive for deployment.


### buildSearchableOptions
Builds an index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options.

Note, that this is a `runIde`-based task with predefined arguments and all properties of the `runIde` task are also applied to `buildSearchableOptions` tasks.


### downloadRobotServerPlugin
Downloads `robot-server` plugin.
The `robot-server` plugin is required for running the UI tests using the [`runIdeForUiTests`](#runideforuitests) task.


### jarSearchableOptions
Creates a JAR file with searchable options to be distributed with the plugin.


### listProductsReleases
List all available IntelliJ-based IDE releases with their updates.
The result list is used for testing the plugin with Plugin Verifier using the [`runPluginVerifier`](#runpluginverifier) task.


### patchPluginXml
Patches `plugin.xml` files with values provided to the task.


### prepareSandbox
Prepares sandbox directory with installed plugin and its dependencies.


### prepareTestingSandbox
Prepares sandbox directory with installed plugin and its dependencies for testing purposes.


### prepareUiTestingSandbox
Prepares sandbox directory with installed plugin and its dependencies for UI testing purposes.


### publishPlugin
Publishes plugin to the remote Marketplace repository.


### runIde
Runs the IDE instance with the developed plugin installed.


### runIdeForUiTests
Runs the IDE instance with the developed plugin and robot-server installed and ready for UI testing.

See [intellij-ui-test-robot](https://github.com/JetBrains/intellij-ui-test-robot) project.


### runIdePerformanceTest
Runs performance tests on the IDE with the developed plugin installed.


### runPluginVerifier
Runs the [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier) tool to check the binary compatibility with specified IDE builds.


### setupDependencies
Setups required dependencies for building and running project.


### signPlugin
Signs the ZIP archive with the provided key using [marketplace-zip-signer](https://github.com/JetBrains/marketplace-zip-signer) library.


### verifyPlugin
Validates completeness and contents of `plugin.xml` descriptors as well as plugin archive structure.

