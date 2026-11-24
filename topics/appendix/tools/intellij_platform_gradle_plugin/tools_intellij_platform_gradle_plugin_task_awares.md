<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Task Awares

<link-summary>IntelliJ Platform Gradle Plugin task `*Aware` interfaces.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

Task Awares are interfaces that add IntelliJ Platform capabilities and conventions to Gradle tasks.
They extend the [`IntelliJPlatformAware`](#IntelliJPlatformAware) marker and can be used with standard Gradle task registration when the IntelliJ Platform Gradle Plugin is applied.
Implementing an interface does not execute its associated tools; dependencies and conventions depend on the capability.
In particular, sandbox consumers must be associated with a producer using [`applySandboxFrom()`](#SandboxAware-applySandboxFrom).

IntelliJ Platform Gradle Plugin supports creating custom tasks which can use `*Aware` interfaces.
Example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAware
import org.jetbrains.intellij.platform.gradle.tasks.aware.parse

abstract class RetrievePluginNameTask : DefaultTask(), PluginAware {
    @TaskAction
    fun retrievePluginName() {
        pluginXml.parse { name }.orNull?.let(::println)
    }
}

tasks.register<RetrievePluginNameTask>("retrievePluginName")
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAware
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAwareKt

abstract class RetrievePluginNameTask extends DefaultTask implements PluginAware {
  @TaskAction
  void retrievePluginName() {
    if (pluginXml.present) {
      println(PluginAwareKt.parse(pluginXml.get()) { bean -> bean.name })
    }
  }
}

tasks.register('retrievePluginName', RetrievePluginNameTask)
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



## `ComposeHotReloadAware`
{#ComposeHotReloadAware}

<primary-label ref="gradle-plugin-2.11.0"/>

<tldr>

**Depends on**: [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) in non-module projects

**Inherited by**: [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend), [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend), [`runIdeSplitMode`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeSplitMode)

**Sources**: [`ComposeHotReloadAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/ComposeHotReloadAware.kt)

</tldr>

Provides the path to the Java Agent file for the Compose Hot Reload agent required to enable hot reloading of Compose UIs.


### `composeHotReload`
{#ComposeHotReloadAware-composeHotReload}

Enables auto-reload of Compose UIs after code changes for the task.
The agent is not attached to split-mode frontend processes, even when this property is enabled.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

Command-line option
: `--compose-hot-reload`

### `composeHotReloadAgentConfiguration`
{#ComposeHotReloadAware-composeHotReloadAgentConfiguration}

Holds Compose Hot Reload agent JAR dependency configuration.
When hot reload is enabled, this collection must resolve to exactly one agent JAR.
The launch adds `-XX:+AllowEnhancedClassRedefinition` and `-javaagent:<agent path>`; the selected runtime must support enhanced class redefinition.


{type="narrow"}
Type
: `ConfigurableFileCollection`


## `CoroutinesJavaAgentAware`
{#CoroutinesJavaAgentAware}

<tldr>

**Depends on**: [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin) in non-module projects

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
: Resolved automatically and stored under [`intellijPlatform.caching.path`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-path)



## `IntelliJPlatformAware`
{#IntelliJPlatformAware}

<tldr>

**Sources**: [`IntelliJPlatformAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/IntelliJPlatformAware.kt)

</tldr>

Marker for tasks requiring IntelliJ Platform Gradle Plugin preconfiguration.
It declares no properties or methods.
The capability interfaces on this page extend it directly or transitively; [`SandboxStructure`](#SandboxStructure) alone does not.


## `IntelliJPlatformVersionAware`
{#IntelliJPlatformVersionAware}

<tldr>

**Inherited by**: [`RuntimeAware`](#RuntimeAware), [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware), [`SplitModeAware`](#SplitModeAware), [`patchPluginXml`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml), [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox), [`printBundledModules`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledModules), [`printBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#printBundledPlugins), [`dumpBundledModules`](tools_intellij_platform_gradle_plugin_tasks.md#dumpBundledModules), [`dumpBundledPlugins`](tools_intellij_platform_gradle_plugin_tasks.md#dumpBundledPlugins), [`intellijPlatformDoctor`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDoctor), [`intellijPlatformSdk`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformSdk)

**Sources**: [`IntelliJPlatformVersionAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/IntelliJPlatformVersionAware.kt)

</tldr>

Provides a task with the possibility of accessing information about the IntelliJ Platform currently used in the project.

The [`intelliJPlatformConfiguration`](#IntelliJPlatformVersionAware-intelliJPlatformConfiguration) input property receives the task's IntelliJ Platform dependency files.
Resolving them provides details such as [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) and the IntelliJ Platform directory.

It is required to have a dependency on the IntelliJ Platform added to the project with helpers available in [](tools_intellij_platform_gradle_plugin_dependencies_extension.md).



### `intelliJPlatformConfiguration`
{#IntelliJPlatformVersionAware-intelliJPlatformConfiguration}

Holds the selected IntelliJ Platform dependency files.

It should not be directly accessed.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `intelliJPlatformPluginConfiguration`
{#IntelliJPlatformVersionAware-intelliJPlatformPluginConfiguration}

Holds the task's optional IntelliJ Platform plugin dependency files.
It should not be directly accessed.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `intelliJPlatformTestRuntimeFixClasspathConfiguration`
{#IntelliJPlatformVersionAware-intelliJPlatformTestRuntimeFixClasspathConfiguration}

Holds the `intellijPlatformTestRuntimeFixClasspath` configuration with the custom IntelliJ Platform core dependency required for running tests.
It is populated during [`TestableAware`](#TestableAware) preconfiguration, not for every platform-aware task.
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

**Inherited by**: [`instrumentCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentCode), [`instrumentTestCode`](tools_intellij_platform_gradle_plugin_tasks.md#instrumentTestCode)

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

**Inherited by**: [`generateManifest`](tools_intellij_platform_gradle_plugin_tasks.md#generateManifest), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration), [`intellijPlatformDoctor`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDoctor)

**Sources**: [`KotlinMetadataAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/KotlinMetadataAware.kt)

</tldr>

Provides access to Kotlin-specific metadata from the Kotlin Gradle Plugin and the `compileKotlin` task.
For Kotlin Gradle Plugin 1.8.0 and later, compilation options are read from `compilerOptions`; older versions use `kotlinOptions`.
Optional metadata properties can be unset; their types do not have nullable type parameters.


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

Indicates whether the resolved composed-JAR dependency graph contains an `org.jetbrains.kotlinx` component whose module name starts with `kotlinx-coroutines`.
This includes transitive dependencies.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: Detected from the composed-JAR dependency graph


### `kotlinApiVersion`
{#KotlinMetadataAware-kotlinApiVersion}

The `apiVersion` value of the Kotlin compilation options.

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured compilation option, if available


### `kotlinLanguageVersion`
{#KotlinMetadataAware-kotlinLanguageVersion}

The `languageVersion` value of the Kotlin compilation options.

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured compilation option, if available


### `kotlinVersion`
{#KotlinMetadataAware-kotlinVersion}

The version of Kotlin used in the project.

{type="narrow"}
Type
: `Property<String>`

Default value
: `kotlin.coreLibrariesVersion` when the Kotlin Gradle Plugin is applied


### `kotlinJvmTarget`
{#KotlinMetadataAware-kotlinJvmTarget}

The `jvmTarget` value of the Kotlin compilation options.

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured compilation option, if available


### `kotlinStdlibDefaultDependency`
{#KotlinMetadataAware-kotlinStdlibDefaultDependency}

`kotlin.stdlib.default.dependency` property value defined in the `gradle.properties` file.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: The Gradle property value, if specified and the Kotlin Gradle Plugin is applied



## `ModuleAware`
{#ModuleAware}

<tldr>

**Inherited by**: [`PluginAware`](#PluginAware), [`composedJar`](tools_intellij_platform_gradle_plugin_tasks.md#composedJar), [`initializeIntelliJPlatformPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin)

**Sources**: [`ModuleAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/ModuleAware.kt)

</tldr>

Provides information about whether the current task is executed in an IntelliJ Platform plugin module context.

### `module`
{#ModuleAware-module}

Indicates if the current project represents an IntelliJ Platform plugin module.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `PluginManager.isModule`

Notes:
- The IntelliJ Platform Gradle Plugin wires this property automatically for tasks implementing ModuleAware during task preconfiguration.
- This value is typically not set manually; consume it in the task to branch logic.



## `PluginAware`
{#PluginAware}

<tldr>

**Depends on**: [`patchPluginXml`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml) for non-module projects

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`prepareJarSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#prepareJarSearchableOptions), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration), [`intellijPlatformDoctor`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDoctor)

**Sources**: [`PluginAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/PluginAware.kt)

</tldr>

Provides information about the currently built plugin.

It resolves and parses the final <path>plugin.xml</path> descriptor file, making its details easily accessible.


### `pluginXml`
{#PluginAware-pluginXml}

Holds the path to the patched <path>plugin.xml</path> file.
The convention is unset for module projects, so consumers must handle an absent descriptor.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`patchPluginXml.outputFile`](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml-outputFile) for non-module projects


### `parse {}`
{#PluginAware-parse}

Kotlin extension functions provide access to the parsed [`PluginBean`](tools_intellij_platform_gradle_plugin_types.md#PluginBean).
The `Path` and `RegularFile` overloads return the block's result directly; the `Provider<RegularFile>` overload returns a mapped `Provider<T>`.
Import `org.jetbrains.intellij.platform.gradle.tasks.aware.parse` in Kotlin.
Groovy can call the generated static methods on `PluginAwareKt` with an explicit bean parameter.

For example, inside a task action with a present [`pluginXml`](#PluginAware-pluginXml):

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val name = pluginXml.parse { name }.get()
println("Plugin Name: $name")
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.aware.PluginAwareKt

def name = PluginAwareKt.parse(pluginXml.get()) { bean -> bean.name }
println("Plugin Name: $name")
```

</tab>
</tabs>


## `PluginInstallationTargetAware`
{#PluginInstallationTargetAware}

<primary-label ref="gradle-plugin-2.14.0"/>

<tldr>

**Inherited by**: [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox), [`prepareTestSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestSandbox), [`prepareTestIdePerformanceSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestIdePerformanceSandbox), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend), [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend), [`runIdeSplitMode`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeSplitMode)

**Sources**: [`PluginInstallationTargetAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/PluginInstallationTargetAware.kt)

</tldr>

Provides the plugin installation-target property used by sandbox producers and split-mode launch tasks.

### `pluginInstallationTarget`
{#PluginInstallationTargetAware-pluginInstallationTarget}

Specifies in which part of the product the developed plugin should be installed when running in Split Mode.
The values are `BACKEND`, `FRONTEND`, and `BOTH`.
`BOTH` uses a shared plugin directory for the two processes rather than making two copies.

Sandbox producers normally obtain the convention from [`intellijPlatform.pluginInstallationTarget`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginInstallationTarget).
The producers for [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend) and [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend) instead default to `BACKEND` and `FRONTEND`, respectively.
[`SandboxAware`](#SandboxAware) consumers inherit the convention from their producer through [`applySandboxFrom()`](#SandboxAware-applySandboxFrom).

{type="narrow"}
Type
: [`Property<PluginInstallationTarget>`](tools_intellij_platform_gradle_plugin_types.md#SplitModeAware-PluginInstallationTarget)

Default value
: Task-specific convention; the effective fallback is `BACKEND`


## `PluginVerifierAware`
{#PluginVerifierAware}

<tldr>

**Inherited by**: [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin)

**Sources**: [`PluginVerifierAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/PluginVerifierAware.kt)

</tldr>

Provides the path to the IntelliJ Plugin Verifier executable.

Supply the CLI through [`intellijPlatform.pluginVerifier()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md) or [`intellijPlatform.pluginVerification.cliPath`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-cliPath).


### `pluginVerifierExecutable`
{#PluginVerifierAware-pluginVerifierExecutable}

Path to the IntelliJ Plugin Verifier executable.

{type="narrow"}
Type
: `RegularFileProperty`



## `ProblemsAware`
{#ProblemsAware}

<tldr>

**Inherited by**: [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`ProblemsAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/ProblemsAware.kt)

</tldr>

Provides access to Gradle's public `org.gradle.api.problems.Problems` API for structured issue reporting.
This Gradle API is incubating.

### `problems`
{#ProblemsAware-problems}

Provides the injected Gradle Problems API instance used by the task.

{type="narrow"}
Type
: `Problems`


## `ProductReleasesServiceAware`
{#ProductReleasesServiceAware}

<tldr>

**Inherited by**: [`printProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#printProductsReleases), [`dumpProductsReleases`](tools_intellij_platform_gradle_plugin_tasks.md#dumpProductsReleases), [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin), [`intellijPlatformDiscover`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDiscover)

**Sources**: [`ProductReleasesServiceAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/ProductReleasesServiceAware.kt)

</tldr>

Provides the shared product-release listing service.
Using this capability does not itself resolve an IDE distribution.

### `productReleasesService`
{#ProductReleasesServiceAware-productReleasesService}

The shared service supplied during task preconfiguration.

{type="narrow"}
Type
: `Property<ProductReleasesService>`


## `RunnableIdeAware`
{#RunnableIdeAware}

<tldr>

**Extends**: [`AutoReloadAware`](#AutoReloadAware), [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware), [`PluginAware`](#PluginAware), [`RuntimeAware`](#RuntimeAware), [`SandboxAware`](#SandboxAware), `JavaForkOptions`

**Inherited by**: [`buildSearchableOptions`](tools_intellij_platform_gradle_plugin_tasks.md#buildSearchableOptions), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend), [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend), [`runIdeSplitMode`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeSplitMode), [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance)

**Sources**: [`RunnableIdeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/RunnableIdeAware.kt)

</tldr>

The interface which uses a set of various interfaces required for running a guest IDE.
Inherits from:
- [`AutoReloadAware`](#AutoReloadAware)
- [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware)
- [`PluginAware`](#PluginAware)
- [`RuntimeAware`](#RuntimeAware)
- [`SandboxAware`](#SandboxAware)
- `JavaForkOptions`

Preconfiguration enables assertions and supplies IDE, sandbox, and required-plugin JVM arguments.
For `JavaExec` tasks, it also sets the IDE main class, bootstrap classpath, and resolved Java launcher.
Explicit `maxHeapSize`, `minHeapSize`, `-Xmx`, and `-Xms` settings override the corresponding distribution VM options.
Split-mode launch profiles use frontend-specific bootstrap and VM options and only require the plugin on its selected installation side.



## `RuntimeAware`
{#RuntimeAware}

<tldr>

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`TestableAware`](#TestableAware), [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin), [`verifyPluginProjectConfiguration`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration)

**Sources**: [`RuntimeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/RuntimeAware.kt)

</tldr>

Provides access to the Java Runtime (i.e., JetBrains Runtime) resolved with `JavaRuntimePathResolver`.


### `jetbrainsRuntimeConfiguration`
{#RuntimeAware-jetbrainsRuntimeConfiguration}

Holds the JetBrains Runtime dependency files.
It should not be directly accessed.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `runtimeDirectory`
{#RuntimeAware-runtimeDirectory}

Java Runtime parent directory.

{type="narrow"}
Type
: `DirectoryProperty`


### `runtimeArchitecture`
{#RuntimeAware-runtimeArchitecture}

An architecture of the Java Runtime currently used for running Gradle.
Initialized from the `os.arch` system property.

{type="narrow"}
Type
: `Property<String>`


### `runtimeMetadata`
{#RuntimeAware-runtimeMetadata}

Metadata obtained by running the Java executable in the resolved [`runtimeDirectory`](#RuntimeAware-runtimeDirectory).
This describes the selected runtime, not necessarily the JVM running Gradle.

{type="narrow"}
Type
: `MapProperty<String, String>`


### `runtimeLauncher`
{#RuntimeAware-runtimeLauncher}

A custom `JavaLauncher` instance configured with the resolved [`runtimeDirectory`](#RuntimeAware-runtimeDirectory).

{type="narrow"}
Type
: `Property<JavaLauncher>`



## `SandboxStructure`
{#SandboxStructure}

<tldr>

**Inherited by**: [`SandboxAware`](#SandboxAware), [`SplitModeAware`](#SplitModeAware)

**Sources**: [`SandboxStructure`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SandboxStructure.kt)

</tldr>

Defines common sandbox directories and flags used by tasks interacting with IDE sandboxes.

### `sandboxDirectory`
{#SandboxStructure-sandboxDirectory}

The directory containing content read and produced by the running IDE.
Sandbox producers conventionally use <path>[intellijPlatform.sandboxContainer]/[project.name]/[productCode]-[productVersion]</path>.

{type="narrow"}
Type
: `DirectoryProperty`

### `sandboxConfigDirectory`
{#SandboxStructure-sandboxConfigDirectory}

A configuration directory located within the [`sandboxDirectory`](#SandboxStructure-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`

### `sandboxPluginsDirectory`
{#SandboxStructure-sandboxPluginsDirectory}

A plugins directory located within the [`sandboxDirectory`](#SandboxStructure-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`

### `sandboxSystemDirectory`
{#SandboxStructure-sandboxSystemDirectory}

A system directory located within the [`sandboxDirectory`](#SandboxStructure-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`

### `sandboxLogDirectory`
{#SandboxStructure-sandboxLogDirectory}

A log directory located within the [`sandboxDirectory`](#SandboxStructure-sandboxDirectory).

{type="narrow"}
Type
: `DirectoryProperty`

### `testSandbox`
{#SandboxStructure-testSandbox}

Defines if the current sandbox is related to testing.

{type="narrow"}
Type
: `Property<Boolean>`


## `SandboxAware`
{#SandboxAware}

<tldr>

**Depends on**: The producer passed to [`applySandboxFrom()`](#SandboxAware-applySandboxFrom)

**Inherited by**: [`RunnableIdeAware`](#RunnableIdeAware), [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`SandboxAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SandboxAware.kt)

</tldr>

Provides quick access to the sandbox container and specific directories located within it.

The path to the sandbox container is obtained using the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer) extension property and the type and version of the IntelliJ Platform applied to the project.


### Sandbox Suffix
{#SandboxAware-sandboxSuffix}

`SandboxAware` does not declare a `sandboxSuffix` property.
Configure [`PrepareSandboxTask.sandboxSuffix`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox-sandboxSuffix) on the producer instead.


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
It adds a dependency on the producer and conventions for the sandbox directories and `testSandbox`.
For consumers implementing the split-mode capabilities, it also transfers the split-mode and installation-target conventions.
Values are finalized on read, so configure the producer before the consumer reads them.


### `applyFrontendSandboxFrom(TaskProvider)`
{#SandboxAware-applyFrontendSandboxFrom}

Calls [`applySandboxFrom()`](#SandboxAware-applySandboxFrom), then replaces the config, plugins, system, and log directory conventions with the producer's frontend directories.
For the plugins directory, it uses the shared backend directory when the effective installation target is `BOTH`.
This is used by split-mode frontend consumers such as [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend).



## `SigningAware`
{#SigningAware}

<tldr>

**Inherited by**: [`signPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin), [`verifyPluginSignature`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginSignature)

**Sources**: [`SigningAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SigningAware.kt)

</tldr>

Provides the path to the Marketplace ZIP Signer executable.
Supply it through `intellijPlatform.zipSigner()` or [`intellijPlatform.signing.cliPath`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-cliPath).


### `zipSignerExecutable`
{#SigningAware-zipSignerExecutable}

Path to the Marketplace ZIP Signer executable.

{type="narrow"}
Type
: `RegularFileProperty`


## `SplitModeAware`
{#SplitModeAware}

<tldr>

**Inherited by**: [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox), [`prepareTestSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestSandbox), [`prepareTestIdePerformanceSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTestIdePerformanceSandbox), [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde), [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend), [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend), [`runIdeSplitMode`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeSplitMode)

**Sources**: [`SplitModeAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/SplitModeAware.kt)

</tldr>

Supports checking a plugin in [Split Mode](split_mode_and_remote_development.md), with the IDE backend and JetBrains Client frontend in separate processes.
Use [`PluginInstallationTargetAware.pluginInstallationTarget`](#PluginInstallationTargetAware-pluginInstallationTarget) to select where the developed plugin is installed.

[Split Mode](split_mode_and_remote_development.md) requires the IntelliJ Platform in the version `241.14473` or later.


### `splitMode`
{#SplitModeAware-splitMode}

Enables Split Mode when running the IDE.
[`SandboxAware`](#SandboxAware) consumers inherit this convention from their producer.
The sandbox producers for [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend) and [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend) enable Split Mode by default.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: Task-specific; normally [`intellijPlatform.splitMode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-splitMode) for sandbox producers


### `pluginInstallationTarget`
{#SplitModeAware-pluginInstallationTarget}

Declared by [`PluginInstallationTargetAware`](#PluginInstallationTargetAware), not `SplitModeAware` itself.
See [`pluginInstallationTarget`](#PluginInstallationTargetAware-pluginInstallationTarget) for its values and task-specific conventions.

### `splitModeTarget`
{#SplitModeAware-splitModeTarget}

<secondary-label ref="deprecated"/>

Compatibility alias for [`pluginInstallationTarget`](#PluginInstallationTargetAware-pluginInstallationTarget).
Its values use the deprecated `SplitModeAware.SplitModeTarget` enum, with `BACKEND`, `FRONTEND`, and `BOTH` entries.
Use `SplitModeAware.PluginInstallationTarget` with the replacement property.

{type="narrow"}
Type
: `Property<SplitModeAware.SplitModeTarget>`

### `sandboxConfigFrontendDirectory`
{#SplitModeAware-sandboxConfigFrontendDirectory}

A frontend configuration directory located within the sandbox.

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxPluginsFrontendDirectory`
{#SplitModeAware-sandboxPluginsFrontendDirectory}

A frontend plugins directory located within the sandbox.

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxSystemFrontendDirectory`
{#SplitModeAware-sandboxSystemFrontendDirectory}

A frontend system directory located within the sandbox.

{type="narrow"}
Type
: `DirectoryProperty`


### `sandboxLogFrontendDirectory`
{#SplitModeAware-sandboxLogFrontendDirectory}

A frontend log directory located within the sandbox.

{type="narrow"}
Type
: `DirectoryProperty`


### `splitModeFrontendProperties`
{#SplitModeAware-splitModeFrontendProperties}

Path to a properties file used to configure the frontend process when the IDE is started in Split Mode.

{type="narrow"}
Type
: `Provider<RegularFile>`

Default value
: <path>[sandboxDirectory]/frontend.properties</path>


### `splitModeFrontendJoinLinkFile`
{#SplitModeAware-splitModeFrontendJoinLinkFile}

Path to a file storing the current split-mode frontend join link.
It is written by [`runIdeBackend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeBackend) and consumed by [`runIdeFrontend`](tools_intellij_platform_gradle_plugin_tasks.md#runIdeFrontend) when both are launched separately.

{type="narrow"}
Type
: `Provider<RegularFile>`

Default value
: <path>[sandboxDirectory]/split-mode-frontend.join.link</path>


### `splitModeBackendPidFile`
{#SplitModeAware-splitModeBackendPidFile}

Path used to record the running backend's OS process identifier on a best-effort basis.
The backend launch checks it for a live IDE process before starting and removes it when the managed process exits.

{type="narrow"}
Type
: `Provider<RegularFile>`

Default value
: <path>[sandboxDirectory]/split-mode-backend.pid</path>


### `splitModeFrontendPidFile`
{#SplitModeAware-splitModeFrontendPidFile}

Provides the frontend PID-file path.
The current frontend launch implementation does not populate this file; it must not be used as evidence that the frontend is running.

{type="narrow"}
Type
: `Provider<RegularFile>`

Default value
: <path>[sandboxDirectory]/split-mode-frontend.pid</path>


### `effectivePluginInstallationTarget`
{#SplitModeAware-effectivePluginInstallationTarget}

Resolves the effective plugin installation target.
The [`pluginInstallationTarget`](#PluginInstallationTargetAware-pluginInstallationTarget) property takes precedence over the deprecated `splitModeTarget` alias when available.
If neither property has a value, the effective target is `BACKEND`.

{type="narrow"}
Type
: `Provider<PluginInstallationTarget>`


### `validateSplitModeSupport()`
{#SplitModeAware-validateSplitModeSupport}

Validates that the resolved IntelliJ Platform supports Split Mode.

{type="narrow"}
Throws
: `IllegalArgumentException`



## `TestableAware`
{#TestableAware}

<tldr>

**Inherited by**: [`prepareTest`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTest), [`testIde`](tools_intellij_platform_gradle_plugin_tasks.md#testIde), [`testUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi), [`testIdePerformance`](tools_intellij_platform_gradle_plugin_tasks.md#testIdePerformance)

**Sources**: [`TestableAware`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/aware/TestableAware.kt)

</tldr>

Provides the runtime, coroutines-agent, sandbox, and classpath capabilities used by test tasks.
It inherits from [`RuntimeAware`](#RuntimeAware), [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware), and [`SandboxAware`](#SandboxAware), but not [`PluginAware`](#PluginAware) or [`RunnableIdeAware`](#RunnableIdeAware).
The ordinary `test` task obtains these capabilities through [`prepareTest`](tools_intellij_platform_gradle_plugin_tasks.md#prepareTest).

### `intellijPlatformClasspathConfiguration`
{#TestableAware-intellijPlatformClasspathConfiguration}

Holds the `intellijPlatformClasspath` configuration.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `intellijPlatformTestClasspathConfiguration`
{#TestableAware-intellijPlatformTestClasspathConfiguration}

Holds the `intellijPlatformTestClasspath` configuration.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `intellijPlatformTestRuntimeClasspathConfiguration`
{#TestableAware-intellijPlatformTestRuntimeClasspathConfiguration}

Holds the `intellijPlatformTestRuntimeClasspath` configuration.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `pluginDirectory`
{#TestableAware-pluginDirectory}

Specifies the directory where the plugin artifacts are placed for testing.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[sandboxPluginsDirectory]/[intellijPlatform.projectName]</path>
