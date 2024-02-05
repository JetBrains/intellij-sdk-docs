<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Task Awares

<link-summary>IntelliJ Platform Gradle Plugin task `*Aware` interfaces.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="EAP_Status"/>

The Task Awares is a set of interfaces that can be applied to custom Gradle tasks and, when registered using the dedicated register method, inject new features or properties with predefined values.


## CoroutinesJavaAgentAware
{#CoroutinesJavaAgentAware}

The interface provides the path to the Java Agent file for the Coroutines library required to enable coroutines debugging.

Inherited also by [`RunnableIdeAware`](#RunnableIdeAware).

### coroutinesJavaAgentFile
{#CoroutinesJavaAgentAware-coroutinesJavaAgentFile}

The path to the coroutines Java Agent file.

{style="narrow"}
Type
: `RegularFileProperty`

Default value
: [`initializeIntellijPlatformPlugin.coroutinesJavaAgent`](tools_intellij_platform_gradle_plugin_tasks.md#initializeIntelliJPlatformPlugin-coroutinesJavaAgent)


## CustomIntelliJPlatformVersionAware
{#CustomIntelliJPlatformVersionAware}

By default, the project with the IntelliJ Platform Gradle Plugin applied required the presence of the IntelliJ Platform, referred to later by various tasks, configurations, and extensions.

The custom IntelliJ Platform concept allows using another version, i.e., to run a guest IDE or tests against it.

When applying this interface to the task, custom configurations to hold new dependencies defined by [`type`](#CustomIntelliJPlatformVersionAware-type) and [`version`](#CustomIntelliJPlatformVersionAware-version) (or [`localPath`](#CustomIntelliJPlatformVersionAware-localPath), if referring to the local IntelliJ Platform instance) are created, as well as a dedicated [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) task.

Configurations, as well as the task preparing sandbox for running and testing the custom IntelliJ Platform (if required), have a random suffix applied to avoid collisions.


### type
{#CustomIntelliJPlatformVersionAware-type}

An input property to configure the type of the custom IntelliJ Platform.

By default, it refers to the IntelliJ Platform type used by the current project.

{style="narrow"}
Type
: [`IntelliJPlatformType`](tools_intellij_platform_gradle_plugin_types.md#IntelliJPlatformType)


### version
{#CustomIntelliJPlatformVersionAware-version}

An input property to configure the version of the custom IntelliJ Platform.

By default, it refers to the IntelliJ Platform version used by the current project.

{style="narrow"}
Type
: `Property<String>`


### localPath
{#CustomIntelliJPlatformVersionAware-localPath}

An input property to define the path to the local IntelliJ Platform instance to configure the version of the custom IntelliJ Platform.

The local path precedes the IntelliJ Platform resolution using the [`type`](#CustomIntelliJPlatformVersionAware-type) and [`version`](#CustomIntelliJPlatformVersionAware-version) properties.

{style="narrow"}
Type
: `DirectoryProperty`


## IntelliJPlatformVersionAware
{#IntelliJPlatformVersionAware}

This interface provides tasks a possibility for accessing information about the IntelliJ Platform currently used in the project.

The [`intelliJPlatformConfiguration`](#IntelliJPlatformVersionAware-intelliJPlatformConfiguration) input property receives a dependency added to the `intellijPlatform` configuration, which eventually is resolved and lets to access the IntelliJ Platform details such as [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo) or the path to the IntelliJ Platform directory.

It is required to have a dependency on the IntelliJ Platform added to the project with helpers available in [](tools_intellij_platform_gradle_plugin_dependencies_extension.md).


### intelliJPlatformConfiguration
{#IntelliJPlatformVersionAware-intelliJPlatformConfiguration}

Holds the `intellijPlatform` configuration with the IntelliJ Platform dependency added.

It should not be directly accessed.

{style="narrow"}
Type
: `ConfigurableFileCollection`


### platformPath
{#IntelliJPlatformVersionAware-platformPath}

Provides a direct path to the IntelliJ Platform dependency artifact.

{style="narrow"}
Type
: `Path`

### productInfo
{#IntelliJPlatformVersionAware-productInfo}

Provides information about the IntelliJ Platform product.

The information is retrieved from the <path>product-info.json</path> file in the IntelliJ Platform directory.

{style="narrow"}
Type
: [`ProductInfo`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo)


### assertIntelliJPlatformSupportedVersion()
{#IntelliJPlatformVersionAware-assertIntelliJPlatformSupportedVersion}

Asserts that the resolved IntelliJ Platform is supported by checking against the minimal supported IntelliJ Platform version.

Invokes [`ProductInfo.assertSupportedVersion()`](tools_intellij_platform_gradle_plugin_types.md#ProductInfo-assertSupportedVersion).

{style="narrow"}
Throws
: `IllegalArgumentException`


## PluginVerifierAware
{#PluginVerifierAware}

The interface provides the path to the IntelliJ Plugin Verifier executable.

It is required to have a dependency on the IntelliJ Plugin Verifier added to the project with [`intellijPlatform.pluginVerifier()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md) dependencies extension.


### pluginVerifierExecutable
{#PluginVerifierAware-pluginVerifierExecutable}

Path to the IntelliJ Plugin Verifier executable.

{style="narrow"}
Type
: `RegularFileProperty`


## RunnableIdeAware
{#RunnableIdeAware}

The interface which utilizes a set of various interfaces required for running a guest IDE.  Inherits from:
- [`CoroutinesJavaAgentAware`](#CoroutinesJavaAgentAware)
- [`RuntimeAware`](#RuntimeAware)
- [`SandboxAware`](#SandboxAware)
- `JavaForkOptions`


## RuntimeAware
{#RuntimeAware}

This interface provides access to the Java Runtime (i.e., JetBrains Runtime) resolved with `RuntimeResolver`.

### runtimeDirectory
{#RuntimeAware-runtimeDirectory}

Java Runtime parent directory.

{style="narrow"}
Type
: `DirectoryProperty`


### runtimeExecutable
{#RuntimeAware-runtimeExecutable}

Path to the Java Runtime executable.

{style="narrow"}
Type
: `RegularFileProperty`


### runtimeArch
{#RuntimeAware-runtimeArch}

An architecture of the Java Runtime currently used for running Gradle.

{style="narrow"}
Type
: `Property<String>`



## SandboxAware
{#SandboxAware}

The interface provides quick access to the sandbox container and specific directories located within it.

The path to the sandbox container is obtained using the [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer) extension property and the type and version of the IntelliJ Platform applied to the project.

Paths respect custom IntelliJ Platform when combined with [`CustomIntelliJPlatformVersionAware`](#CustomIntelliJPlatformVersionAware).


### sandboxSuffix
{#SandboxAware-sandboxSuffix}

Represents the suffix used i.e., for test-related tasks.

{style="narrow"}
Type
: `Property<String>`


### sandboxContainerDirectory
{#SandboxAware-sandboxContainerDirectory}

The container for all sandbox-related directories.

The directory name depends on the platform type and version currently used for running a task.

{style="narrow"}
Type
: `DirectoryProperty`


### sandboxConfigDirectory
{#SandboxAware-sandboxConfigDirectory}

A configuration directory located within the [`sandboxContainerDirectory`](#SandboxAware-sandboxContainerDirectory).

{style="narrow"}
Type
: `DirectoryProperty`


### sandboxPluginsDirectory
{#SandboxAware-sandboxPluginsDirectory}

A plugins directory located within the [`sandboxContainerDirectory`](#SandboxAware-sandboxContainerDirectory).

{style="narrow"}
Type
: `DirectoryProperty`


### sandboxSystemDirectory
{#SandboxAware-sandboxSystemDirectory}

A system directory located within the [`sandboxContainerDirectory`](#SandboxAware-sandboxContainerDirectory).

{style="narrow"}
Type
: `DirectoryProperty`


### sandboxLogDirectory
{#SandboxAware-sandboxLogDirectory}

A log directory located within the [`sandboxContainerDirectory`](#SandboxAware-sandboxContainerDirectory).

{style="narrow"}
Type
: `DirectoryProperty`


## SigningAware
{#SigningAware}

The interface provides the path to the Marketplace ZIP Signer executable.

It is required to have a dependency on the Marketplace ZIP Signer added to the project with [`intellijPlatform.zipSigner()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md) dependencies extension.


### zipSignerExecutable
{#SigningAware-zipSignerExecutable}

Path to the Marketplace ZIP Signer executable.

{style="narrow"}
Type
: `RegularFileProperty`


<include from="snippets.md" element-id="missingContent"/>
