<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Task Awares

<link-summary>IntelliJ Platform Gradle Plugin task `*Aware` interfaces.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The Task Awares is a set of interfaces that can be applied to custom Gradle tasks and, when registered using the dedicated register method, inject new features or properties with predefined values.

IntelliJ Platform Gradle Plugin supports creating custom tasks which can use `*Aware` interfaces.
Example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAware

abstract class RetrievePluginNameTask : DefaultTask(), PluginAware

val retrievePluginName by tasks.registering(RetrievePluginNameTask::class) {
    val outputFile = layout.buildDirectory.file("pluginName.txt")

    doLast {
        outputFile.get().asFile.writeText(pluginXml.parse { name }.get())
    }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAware

abstract class RetrievePluginNameTask extends DefaultTask implements PluginAware {}

tasks.register('retrievePluginName', RetrievePluginNameTask) {
  def outputFile = layout.buildDirectory.file("pluginName.txt")

  doLast {
    outputFile.get().asFile.writeText(pluginXml.parse { name }.get())
  }
}
```

</tab>
</tabs>


## `AutoReloadAware`
{#AutoReloadAware}

<tldr>

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware)

**Sources**: [`AutoReloadAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/AutoReloadAware.kt)

</tldr>

Provides the possibility to auto-reload plugin when run in the IDE.


### `autoReload`
{#AutoReloadAware-autoReload}

Enables auto-reload of dynamic plugins.
Dynamic plugin will be reloaded automatically when its content is modified.

This allows a much faster development cycle by avoiding a full restart of the development instance after code changes.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.autoReload`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-autoReload)



## `CoroutinesJavaAgentAware`
{#CoroutinesJavaAgentAware}

<tldr>

**Depends on**: [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin)

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`TestableAware`](#TestableAware)

**Sources**: [`CoroutinesJavaAgentAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/CoroutinesJavaAgentAware.kt)

</tldr>

Provides the path to the Java Agent file for the Coroutines library required to enable coroutines debugging.


### `coroutinesJavaAgentFile`
{#CoroutinesJavaAgentAware-coroutinesJavaAgentFile}

The path to the coroutines Java Agent file.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`initializeIntellijPlatformPlugin.coroutinesJavaAgent`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin-coroutinesJavaAgent)



## `IntelliJPlatformVersionAware`
{#IntelliJPlatformVersionAware}

<tldr>

**Inherited by**: [`RuntimeAware`](#RuntimeAware), [`SandboxAware`](#SandboxAware), [`SplitModeAware`](#SplitModeAware), [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin), [`patchPluginXml`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml), [`printBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledPlugins), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance), [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`IntelliJPlatformVersionAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/IntelliJPlatformVersionAware.kt)

</tldr>

Provides a task with the possibility of accessing information about the IntelliJ Platform currently used in the project.

The [`intelliJPlatformConfiguration`](#IntelliJPlatformVersionAware-intelliJPlatformConfiguration) input property receives a dependency added to the `intellijPlatform` configuration, which eventually is resolved and lets to access the IntelliJ Platform details such as [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) or the path to the IntelliJ Platform directory.

It is required to have a dependency on the IntelliJ Platform added to the project with helpers available in [](tools_intellij_platform_gradle_plugin_dependencies_extension.md).



### `intelliJPlatformConfiguration`
{#IntelliJPlatformVersionAware-intelliJPlatformConfiguration}

Holds the `intellijPlatform` configuration with the IntelliJ Platform dependency added.

It should not be directly accessed.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `platformPath`
{#IntelliJPlatformVersionAware-platformPath}

Provides a direct path to the IntelliJ Platform dependency artifact.

{type="narrow"}
Access
: Read-only

Type
: `Path`


### `productInfo`
{#IntelliJPlatformVersionAware-productInfo}

Provides information about the IntelliJ Platform product.

The information is retrieved from the <path>product-info.json</path> file in the IntelliJ Platform directory.

{type="narrow"}
Access
: Read-only

Type
: [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo)


### `validateIntelliJPlatformVersion()`
{#IntelliJPlatformVersionAware-validateIntelliJPlatformVersion}

Validates that the resolved IntelliJ Platform is supported by checking against the minimal supported IntelliJ Platform version.

Invokes [`ProductInfo.validateSupportedVersion()`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo-validateSupportedVersion).

{type="narrow"}
Throws
: `IllegalArgumentException`



## `JavaCompilerAware`
{#JavaCompilerAware}

<tldr>

**Inherited by**: [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode)

**Sources**: [`JavaCompilerAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/JavaCompilerAware.kt)

</tldr>

Provides the dependency on Java Compiler required for the [code instrumentation](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode) to properly configure Ant tasks provided by the IntelliJ Platform.


### `javaCompilerConfiguration`
{#JavaCompilerAware-javaCompilerConfiguration}

Holds the `intellijPlatformJavaCompiler` configuration with the Java Compiler dependency added.

{type="narrow"}
Type
: `ConfigurableFileCollection`



## `KotlinMetadataAware`
{#KotlinMetadataAware}

<tldr>

**Inherited by**: [`generateManifest`](tools_intellij_platform_gradle_plugin_tasks.md#generateManifest), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`KotlinMetadataAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/KotlinMetadataAware.kt)

</tldr>

An interface that provides access to Kotlin-specific metadata for a Gradle project.
The task that inherits from this interface is automatically marked as dependent on the `compileKotlin` task.


### `kotlinPluginAvailable`
{#KotlinMetadataAware-kotlinPluginAvailable}

Indicates that the Kotlin Gradle Plugin is loaded and available.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `kotlinxCoroutinesLibraryPresent`
{#KotlinMetadataAware-kotlinxCoroutinesLibraryPresent}

This variable represents whether the Kotlin Coroutines library is added explicitly to the project dependencies.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `kotlinApiVersion`
{#KotlinMetadataAware-kotlinApiVersion}

The `apiVersion` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `null`


### `kotlinLanguageVersion`
{#KotlinMetadataAware-kotlinLanguageVersion}

The `languageVersion` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `null`


### `kotlinVersion`
{#KotlinMetadataAware-kotlinVersion}

The version of Kotlin used in the project.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `null`


### `kotlinJvmTarget`
{#KotlinMetadataAware-kotlinJvmTarget}

The `jvmTarget` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `null`


### `kotlinStdlibDefaultDependency`
{#KotlinMetadataAware-kotlinStdlibDefaultDependency}

`kotlin.stdlib.default.dependency` property value defined in the `gradle.properties` file.

{type="narrow"}
Type
: `Property<Boolean?>`

Default value
: `null`



## `PluginAware`
{#PluginAware}

<tldr>

**Depends on**: [`patchPluginXml`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml)

**Inherited by**: [`TestableAware`](#TestableAware), [`RunnableIdeAware`](#RunnableIdeAware), [`jarSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#jarSearchableOptions), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`PluginAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/PluginAware.kt)

</tldr>

Provides information about the currently built plugin.

It resolves and parses the final <path>plugin.xml</path> descriptor file, making its details easily accessible.


### `pluginXml`
{#PluginAware-pluginXml}

Holds the path to the patched <path>plugin.xml</path> file.

{type="narrow"}
Type
: `RegularPropertyFile`

Default value
: [`patchPluginXml.outputFile`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-outputFile)


### `parse {}`
{#PluginAware-parse}

The `parse` method provides a possibility for parsing the <path>pluginXml</path> file and direct access to the [`PluginBean`](tools_intellij_platform_gradle_plugin_types.md#PluginBean) object.

Should be used along with the [`pluginXml`](#PluginAware-pluginXml) property like:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
abstract class RetrievePluginNameTask : DefaultTask(), PluginAware

val retrievePluginName by tasks.registering(RetrievePluginNameTask::class) {
    doLast {
        val name = pluginXml.parse { name }.get()
        println("Plugin Name: $name")
    }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
abstract class RetrievePluginNameTask extends DefaultTask implements PluginAware {}

tasks.register('retrievePluginName', RetrievePluginNameTask) {
    doLast {
        def name = pluginXml.parse { name }.get()
        println("Plugin Name: $name")
    }
}
```

</tab>
</tabs>


## `PluginVerifierAware`
{#PluginVerifierAware}

<tldr>

**Inherited by**: [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)

**Sources**: [`PluginVerifierAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/PluginVerifierAware.kt)

</tldr>

Provides the path to the IntelliJ Plugin Verifier executable.

It is required to have a dependency on the IntelliJ Plugin Verifier added to the project with [`intellijPlatform.pluginVerifier()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md) dependencies extension.


### `pluginVerifierExecutable`
{#PluginVerifierAware-pluginVerifierExecutable}

Path to the IntelliJ Plugin Verifier executable.

{type="narrow"}
Type
: `RegularFileProperty`



## `RunnableIdeAware`
{#RunnableIdeAware}

<tldr>

**Depends on**: [`AutoReloadAware`](#AutoReloadAware), [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware), [`PluginAware`](#PluginAware), [`RuntimeAware`](#RuntimeAware), [`SandboxAware`](#SandboxAware)

**Inherited by**: [`buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance), [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi)

**Sources**: [`RunnableIdeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/RunnableIdeAware.kt)

</tldr>

The interface which uses a set of various interfaces required for running a guest IDE.
Inherits from:
- [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware)
- [`PluginAware`](#PluginAware)
- [`RuntimeAware`](#RuntimeAware)
- [`SandboxAware`](#SandboxAware)
- `JavaForkOptions`



## `RuntimeAware`
{#RuntimeAware}

<tldr>

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`TestableAware`](#TestableAware), [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`RuntimeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/RuntimeAware.kt)

</tldr>

Provides access to the Java Runtime (i.e., JetBrains Runtime) resolved with `RuntimeResolver`.


### `runtimeDirectory`
{#RuntimeAware-runtimeDirectory}

Java Runtime parent directory.

{type="narrow"}
Type
: `DirectoryProperty`


### `runtimeArchitecture`
{#RuntimeAware-runtimeArchitecture}

An architecture of the Java Runtime currently used for running Gradle.

{type="narrow"}
Type
: `Property<String>`


### `runtimeMetadata`
{#RuntimeAware-runtimeMetadata}

Metadata object of the Java Runtime currently used for running Gradle.

{type="narrow"}
Type
: `Property<String>`


### `runtimeLauncher`
{#RuntimeAware-runtimeLauncher}

A custom `JavaLauncher` instance configured with the resolved [`runtimeDirectory`](#RuntimeAware-runtimeDirectory).

{type="narrow"}
Type
: `Property<String>`



## `SandboxAware`
{#SandboxAware}

<tldr>

**Depends on**: [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox)

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`SandboxAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SandboxAware.kt)

</tldr>

Provides quick access to the sandbox container and specific directories located within it.

The path to the sandbox container is obtained using the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer) extension property and the type and version of the IntelliJ Platform applied to the project.


### `sandboxSuffix`
{#SandboxAware-sandboxSuffix}

Represents the suffix used i.e., for test-related tasks.

{type="narrow"}
Type
: `Property<String>`


### `sandboxDirectory`
{#SandboxAware-sandboxDirectory}

The directory containing content read and produced by the running IDE.

The directory name depends on the platform type and version currently used for running a task.

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxConfigDirectory`
{#SandboxAware-sandboxConfigDirectory}

A configuration directory located within the [`sandboxDirectory`](#SandboxAware-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxPluginsDirectory`
{#SandboxAware-sandboxPluginsDirectory}

A plugins directory located within the [`sandboxDirectory`](#SandboxAware-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxSystemDirectory`
{#SandboxAware-sandboxSystemDirectory}

A system directory located within the [`sandboxDirectory`](#SandboxAware-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxLogDirectory`
{#SandboxAware-sandboxLogDirectory}

A log directory located within the [`sandboxDirectory`](#SandboxAware-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`


### `applySandboxFrom(TaskProvider)`
{#SandboxAware-applySandboxFrom}

The helper method used for applying sandbox configuration from the sandbox producer (such as [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) or [`prepareTestSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestSandbox) tasks) to sandbox consumers.



## `SigningAware`
{#SigningAware}

<tldr>

**Inherited by**: [`signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin), [`verifyPluginSignature`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginSignature)

**Sources**: [`SigningAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SigningAware.kt)

</tldr>

Provides the path to the Marketplace ZIP Signer executable.


### `zipSignerExecutable`
{#SigningAware-zipSignerExecutable}

Path to the Marketplace ZIP Signer executable.

{type="narrow"}
Type
: `RegularFileProperty`


## `SplitModeAware`
{#SplitModeAware}

<tldr>

**Inherited by**: [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde)

**Sources**: [`SplitModeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SplitModeAware.kt)

</tldr>

When you develop a plugin, you may want to check how it works in remote development mode, when one machine is running the backend part and another is running a frontend part (JetBrains Client) which connects to the backend.

This property allows running the IDE with backend and frontend parts running in separate processes.
The developed plugin is installed in the backend part.

Split Mode requires the IntelliJ Platform in the version `241.14473` or later.


### `splitMode`
{#SplitModeAware-splitMode}

Enables Split Mode when running the IDE.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.splitMode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-splitMode)


### `splitModeTarget`
{#SplitModeAware-splitModeTarget}

Specifies in which part of the product the developed plugin should be installed.

{type="narrow"}
Type
: [`Property<SplitModeTarget>`](tools_intellij_platform_gradle_plugin_types.md#SplitModeAware-SplitModeTarget)

Default value
: [`intellijPlatform.splitModeTarget`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-splitModeTarget)



## `TestableAware`
{#TestableAware}

<tldr>

**Inherited by**: [`prepareTest`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTest)

**Sources**: [`TestableAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/TestableAware.kt)

</tldr>

Interface used to describe tasks used for running tests, such as [`testIdeUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi), [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance), or [`prepareTest`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTest) used for configuring `test` and keeping it immutable.


<include from="snippets.topic" element-id="missingContent"/>
