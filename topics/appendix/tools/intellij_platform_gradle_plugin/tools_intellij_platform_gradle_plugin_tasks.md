<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tasks

<link-summary>IntelliJ Platform Gradle Plugin tasks.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The IntelliJ Platform Gradle Plugin introduces a set of tasks to handle activities of the plugin development for IntelliJ-based IDEs, such as building, verifying, testing, and publishing the plugin archive.

Tasks related to the IntelliJ Platform Gradle Plugin are selectively applied to your project based on specific conditions or criteria defined within the plugin's context.
This means that the plugin intelligently determines which tasks are relevant and necessary based on the current state or configuration of your project.
This contextual application of tasks ensures that the plugin operates efficiently, performing only those actions that are appropriate for the given project environment at any time.

Tasks have dependencies on each other, they inherit from [](tools_intellij_platform_gradle_plugin_task_awares.md) interfaces, respect configuration and build cache,
and can be configured independently.
However, most cases will be covered by the [](tools_intellij_platform_gradle_plugin_extension.md).



## `buildPlugin`
{#buildPlugin}

<link-summary>Builds the plugin and prepares the ZIP archive for testing and deployment.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base)

**Depends on**: [`jarSearchableOptions`](#jarSearchableOptions), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`Zip`][gradle-zip-task]

**Sources**: [`BuildPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildPluginTask.kt)

</tldr>

Builds the plugin and prepares the ZIP archive for testing and deployment.

It takes the output of the [`prepareSandbox`](#prepareSandbox) task containing the built project with all its modules and dependencies, and the output of [`jarSearchableOptions`](#jarSearchableOptions) task.

The produced archive is stored in <path>[buildDirectory]/distributions/[`archiveFile`](#buildPlugin-archiveFile)</path>,
where the name and location of [`archiveFile`](#buildPlugin-archiveFile) can be controlled with properties provided with the
[`Zip`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html) base task.
By default, the `archiveBaseName` is set to the plugin name specified in the <path>plugin.xml</path> file, after it gets patched with the [`patchPluginXml`](#patchPluginXml) task.

<include from="plugin_content.md" element-id="doNotRepackageLibraries"/>


### `archiveFile`
{#buildPlugin-archiveFile}

Specifies the archive file representing the output file produced by the task.

{type="narrow"}
Type
: `RegularFileProperty`



## `buildSearchableOptions`
{#buildSearchableOptions}

<link-summary>Builds the index of UI components (searchable options) for the plugin.</link-summary>

<tldr>

**Depends on**: [`prepareSandbox`](#prepareSandbox)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware)

**Sources**: [`BuildSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildSearchableOptionsTask.kt)

</tldr>

Builds the index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options provided by the plugin's [](settings.md).

If the plugin doesn't implement custom settings, it is recommended to disable this task via [`intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions) flag.

In the case of running the task for the plugin that has [`intellijPlatform.pluginConfiguration.productDescriptor`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor) defined, a warning will be logged regarding potential issues with running headless IDE for paid plugins.
It is possible to mute this warning with the [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) Gradle property.


### `outputDirectory`
{#buildSearchableOptions-outputDirectory}

Specifies the directory where searchable options will be generated.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/searchableOptions</path>


### `showPaidPluginWarning`
{#buildSearchableOptions-showPaidPluginWarning}

Emits a warning when the task is executed by a paid plugin.
Can be disabled with the [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) Gradle property.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) && `productDescriptor` is defined



## `composedJar`
{#composedJar}

<link-summary>Composes a final JAR by combining the base JAR, and instrumented classes, and declared submodules.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`jar`][gradle-jar-task], [`instrumentedJar`](#instrumentedJar)

**Extends**: [`Jar`][gradle-jar-task]

**Sources**: [`ComposedJarTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/ComposedJarTask.kt)

</tldr>

Composes a final JAR by combining the output of base `jar` or [`instrumentedJar`](#instrumentedJar) tasks,
depending on if code instrumentation is enabled with [`intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode).

The final JAR is also combined with plugin modules marked using the [`pluginModule`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) dependencies helper.


### `archiveFile`
{#composedJar-archiveFile}

Specifies the archive file representing the output file produced by the task.

{type="narrow"}
Type
: `RegularFileProperty`



## `generateManifest`
{#generateManifest}

<link-summary>Generates the <path>MANIFEST.MF</path> file with all relevant information about the project configuration.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`InitializeIntelliJPlatformPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InitializeIntelliJPlatformPluginTask.kt)

</tldr>

Generates the <path>MANIFEST.MF</path> file with all relevant information about the project configuration.

To apply the produced manifest file, `JarCompanion.applyPluginManifest` method should be called on a task extending [`Jar`][gradle-jar-task].

This file is bundled into the output JAR files produced by [`composedJar`](#composedJar), [`instrumentedJar`](#instrumentedJar), and [`Jar`][gradle-jar-task] tasks.


### `pluginVersion`
{#generateManifest-pluginVersion}

The IntelliJ Platform Gradle Plugin version.

{type="narrow"}
Type
: `Property<String>`


### `gradleVersion`
{#generateManifest-gradleVersion}

The version of currently used Gradle.

{type="narrow"}
Type
: `Property<String>`


### `productInfo`
{#generateManifest-productInfo}

The [ProductInfo] instance of the current IntelliJ Platform.

{type="narrow"}
Type
: `Property<ProductInfo>`


### `version`
{#generateManifest-version}

Plugin version.

{type="narrow"}
Type
: `Property<String>`


### `generatedManifest`
{#generateManifest-generatedManifest}

Location of the generated <path>MANIFEST.MF</path> file.

{type="narrow"}
Type
: `RegularFileProperty`



## `initializeIntelliJPlatformPlugin`
{#initializeIntelliJPlatformPlugin}

<link-summary>Initializes the IntelliJ Platform Gradle Plugin</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`InitializeIntelliJPlatformPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InitializeIntelliJPlatformPluginTask.kt)

</tldr>

Executes before every other task introduced by IntelliJ Platform Gradle Plugin to prepare it to run.
It is responsible for:

- checking if the project uses IntelliJ Platform Gradle Plugin in the latest available version
- preparing the KotlinX Coroutines Java Agent file to enable coroutines debugging when developing the plugin

The self-update check can be disabled via [`selfUpdateCheck`](tools_intellij_platform_gradle_plugin_gradle_properties.md#selfUpdateCheck) Gradle property.
To make the Coroutines Java Agent available for the task, inherit from [`CoroutinesJavaAgentAware`](tools_intellij_platform_gradle_plugin_task_awares.md#CoroutinesJavaAgentAware).


### `offline`
{#initializeIntelliJPlatformPlugin-offline}

Determines if the operation is running in offline mode and depends on Gradle start parameters.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `StartParameter.isOffline`

See also:
- [StartParameter](https://docs.gradle.org/current/javadoc/org/gradle/StartParameter.html)
- [Command Line Execution Options](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options)


### `selfUpdateCheck`
{#initializeIntelliJPlatformPlugin-selfUpdateCheck}

Represents the property for checking if self-update checks are enabled.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`selfUpdateCheck`](tools_intellij_platform_gradle_plugin_gradle_properties.md#selfUpdateCheck)


### `selfUpdateLock`
{#initializeIntelliJPlatformPlugin-selfUpdateLock}

Represents a lock file used to limit the plugin version checks in time.
If the file is missing and other conditions are met, the version check is performed.

{type="narrow"}
Type
: `RegularFileProperty`


### `coroutinesJavaAgent`
{#initializeIntelliJPlatformPlugin-coroutinesJavaAgent}

Specifies the Java Agent file for the Coroutines library required to enable coroutines debugging.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: <path>[buildDirectory]/tmp/initializeIntelliJPlatformPlugin/coroutines-javaagent.jar</path>


### `pluginVersion`
{#initializeIntelliJPlatformPlugin-pluginVersion}

Represents the current version of the plugin.

{type="narrow"}
Type
: `Property<String>`


### `latestPluginVersion`
{#initializeIntelliJPlatformPlugin-latestPluginVersion}

Represents the latest version of the plugin.

{type="narrow"}
Type
: `Property<String>`


### `module`
{#initializeIntelliJPlatformPlugin-module}

Defines that the current project has only the [](tools_intellij_platform_gradle_plugin_plugins.md#module) applied but no [](tools_intellij_platform_gradle_plugin_plugins.md#platform).

{type="narrow"}
Type
: `Property<String>`



## `instrumentCode`
{#instrumentCode}

<link-summary>Executes the code instrumentation.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`jar`][gradle-jar-task]

**Extends**: [`DefaultTask`][gradle-default-task], [`JavaCompilerAware`](tools_intellij_platform_gradle_plugin_task_awares.md#JavaCompilerAware)

**Sources**: [`InstrumentCodeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InstrumentCodeTask.kt)

</tldr>

Executes the code instrumentation using the Ant tasks provided by the used IntelliJ Platform dependency.
The code instrumentation scans the compiled Java and Kotlin classes for JetBrains Annotations usages to replace them with their relevant functionalities.

The task is controlled with the [`intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode) extension property, enabled by default.
To properly run the instrumentation, it is required to add [`instrumentationTools()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#code-instrumentation) dependencies to the project.
This dependency is available via the [`intellijDependencies()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) repository, which can be added separately or using the [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) helper.

See also:
- [](#instrumentedJar)


### `sourceSetCompileClasspath`
{#instrumentCode-sourceSetCompileClasspath}

Specifies the compile classpath of the project's source set.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `classesDirs`
{#instrumentCode-classesDirs}

Specifies the list of directories with compiled classes.

{type="narrow"}
Type
: `ConfigurableFileCollection`

Default value
: `classesDirs` of the project's source sets.


### `formsDirs`
{#instrumentCode-formsDirs}

Specifies the list of directories with GUI Designer form files.

{type="narrow"}
Type
: `ConfigurableFileCollection`

Default value:
: `.form` files of the project's source sets.


### `sourceDirs`
{#instrumentCode-sourceDirs}

Specifies the location of the source code.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `instrumentationLogs`
{#instrumentCode-instrumentationLogs}

Enables `INFO` logging when running Ant tasks.

{type="narrow"}
Type
: `Property<Boolean>`

Default value:
: `false`


### `outputDirectory`
{#instrumentCode-outputDirectory}

Specifies the output directory for instrumented classes.

{type="narrow"}
Type
: `DirectoryProperty`



## `instrumentedJar`
{#instrumentedJar}

<link-summary>Creates a JAR file with instrumented classes.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: `jar`, [`instrumentCode`](#instrumentCode)

**Extends**: [`Jar`][gradle-jar-task]

</tldr>

Creates a copy of the current module's `jar` task output with instrumented classes added.



## `jarSearchableOptions`
{#jarSearchableOptions}

<link-summary>Creates a JAR file with searchable options to be distributed with the plugin.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildSearchableOptions`](#buildSearchableOptions), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`Jar`][gradle-jar-task]

**Sources**: [`JarSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/JarSearchableOptionsTask.kt)

</tldr>

Creates a JAR file with searchable options to be distributed with the plugin.


### `destinationDirectory`
{#jarSearchableOptions-destinationDirectory}

Specifies the directory where the JAR file will be created.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/libs</path>


### `noSearchableOptionsWarning`
{#jarSearchableOptions-noSearchableOptionsWarning}

Specifies if a warning is emitted when no searchable options are found.
Can be disabled with [`noSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#noSearchableOptionsWarning) Gradle property.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`noSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#noSearchableOptionsWarning)



## `patchPluginXml`
{#patchPluginXml}

<link-summary>Patches <path>plugin.xml</path> file with provided values.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`PatchPluginXmlTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PatchPluginXmlTask.kt)

</tldr>

Patches <path>plugin.xml</path> file with values provided with the [`intelliJPlatform.pluginConfiguration`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration) extension.


### `inputFile`
{#patchPluginXml-inputFile}

Specifies the input <path>plugin.xml</path> file, which by default is picked from the main resource location.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>src/main/resources/META-INF/plugin.xml</path>


### `outputFile`
{#patchPluginXml-outputFile}

Specifies the patched output <path>plugin.xml</path> file, which by default is written to a temporary task-specific directory within the <path>build</path> directory.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/tmp/patchPluginXml/plugin.xml</path>


### `pluginId`
{#patchPluginXml-pluginId}

Specifies a unique plugin identifier, which should be a fully qualified name similar to Java packages and must not collide with the ID of existing plugins.
The ID is a technical value used to identify the plugin in the IDE and [JetBrains Marketplace](https://plugins.jetbrains.com/).
The provided value will be assigned to the [`<id>`](plugin_configuration_file.md#idea-plugin__id) element.

Please use characters, numbers, and `.`/`-`/`_` symbols only and keep it reasonably short.


{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.id`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-id)


### `pluginName`
{#patchPluginXml-pluginName}

Specifies the user-visible plugin name.
It should use Title Case.
The provided value will be assigned to the [`<name>`](plugin_configuration_file.md#idea-plugin__name) element.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name)


### `pluginVersion`
{#patchPluginXml-pluginVersion}

Specifies the plugin version displayed in the <control>Plugins</control> settings dialog and on the [JetBrains Marketplace](https://plugins.jetbrains.com) plugin page.
Plugins uploaded to [JetBrains Marketplace](https://plugins.jetbrains.com) must follow [semantic versioning](https://plugins.jetbrains.com/docs/marketplace/semver.htm).
The provided value will be assigned to the [`<version>`](plugin_configuration_file.md#idea-plugin__version) element.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.version`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-version)


### `pluginDescription`
{#patchPluginXml-pluginDescription}

Specifies the plugin description displayed in the <control>Plugins</control> settings dialog and on the [JetBrains Marketplace](https://plugins.jetbrains.com) plugin page.
Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed.
The description content is automatically wrapped in `<![CDATA[... ]]>`.
The provided value will be assigned to the [`<description>`](plugin_configuration_file.md#idea-plugin__description) element.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.description`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-description)


### `changeNotes`
{#patchPluginXml-changeNotes}

A short summary of new features, bugfixes, and changes provided in this plugin version.
Change notes are displayed on the [JetBrains Marketplace](https://plugins.jetbrains.com) plugin page and in the <control>Plugins</control> settings dialog.
Simple HTML elements, like text formatting, paragraphs, lists, etc., are allowed.

The change notes content is automatically wrapped in `<![CDATA[... ]]>`.
The provided value will be assigned to the [`<change-notes>`](plugin_configuration_file.md#idea-plugin__change-notes) element.

To maintain and generate an up-to-date changelog, try using [Gradle Changelog Plugin](https://github.com/JetBrains/gradle-changelog-plugin).

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.changeNotes`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-changeNotes)


### `productDescriptorCode`
{#patchPluginXml-productDescriptorCode}

The plugin product code used in the JetBrains Sales System.
The code must be agreed with JetBrains in advance and follow [the requirements](https://plugins.jetbrains.com/docs/marketplace/add-required-parameters.html).
The provided value will be assigned to the [`<product-descriptor code="">`](plugin_configuration_file.md#idea-plugin__product-descriptor) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.code`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-code)


### `productDescriptorReleaseDate`
{#patchPluginXml-productDescriptorReleaseDate}

Date of the major version release in the `YYYYMMDD` format.
The provided value will be assigned to the [`<product-descriptor release-date="">`](plugin_configuration_file.md#idea-plugin__product-descriptor) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.releaseDate`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseDate)


### `productDescriptorReleaseVersion`
{#patchPluginXml-productDescriptorReleaseVersion}

Specifies the major version of the plugin in a special number format used for paid plugins on [JetBrains Marketplace](https://plugins.jetbrains.com/docs/marketplace/add-required-parameters.html).
The provided value will be assigned to the [`<product-descriptor release-version="">`](plugin_configuration_file.md#idea-plugin__product-descriptor) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.releaseVersion`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-releaseVersion)


### `productDescriptorOptional`
{#patchPluginXml-productDescriptorOptional}

Specifies the boolean value determining whether the plugin is a [Freemium](https://plugins.jetbrains.com/docs/marketplace/freemium.html) plugin.
The provided value will be assigned to the [`<product-descriptor optional="">`](plugin_configuration_file.md#idea-plugin__product-descriptor) element attribute.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.optional`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-optional)


### `productDescriptorEap`
{#patchPluginXml-productDescriptorEap}

Specifies the boolean value determining whether the plugin is an EAP release.
The provided value will be assigned to the [`<product-descriptor eap="">`](plugin_configuration_file.md#idea-plugin__product-descriptor) element attribute.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.pluginConfiguration.productDescriptor.eap`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor-eap)


### `sinceBuild`
{#patchPluginXml-sinceBuild}

Specifies the lowest IDE version compatible with the plugin.
The provided value will be assigned to the [`<idea-version since-build="..."/>`](plugin_configuration_file.md#idea-plugin__idea-version) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.ideaVersion.sinceBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild)


### `untilBuild`
{#patchPluginXml-untilBuild}

The highest IDE version compatible with the plugin.
The `until-build` attribute can be unset by setting `provider { null }` as a value.
However, if `until-build` is undefined, compatibility with all the IDEs since the version specified by the `since-build` is assumed, which can cause incompatibility errors in future builds.

The provided value will be assigned to the [`<idea-version until-build="..."/>`](plugin_configuration_file.md#idea-plugin__idea-version) element attribute.

The `until-build` attribute can be unset by setting `provider { null }` as a value.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.ideaVersion.untilBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild)


### `vendorName`
{#patchPluginXml-vendorName}

Specifies the vendor name or organization ID (if created) in the <control>Plugins</control> settings dialog and on the [JetBrains Marketplace](https://plugins.jetbrains.com) plugin page.
The provided value will be assigned to the [`<vendor>`](plugin_configuration_file.md#idea-plugin__vendor) element.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-name)


### `vendorEmail`
{#patchPluginXml-vendorEmail}

Specifies the vendor's email address.
The provided value will be assigned to the [`<vendor email="">`](plugin_configuration_file.md#idea-plugin__vendor) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.email`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-email)


### `vendorUrl`
{#patchPluginXml-vendorUrl}

Specifies the link to the vendor's homepage.
The provided value will be assigned to the [`<vendor url="">`](plugin_configuration_file.md#idea-plugin__vendor) element attribute.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.vendor.url`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-vendor-url)



## `prepareJarSearchableOptions`
{#prepareJarSearchableOptions}

<link-summary>Collects the content produced with `buildSearchableOptions` for the `jarSearchableOptions`.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildSearchableOptions`](#buildSearchableOptions), [`composedJar`](#composedJar), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`PrepareJarSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareJarSearchableOptionsTask.kt)

</tldr>

Collects the content produced with `buildSearchableOptions` for the `jarSearchableOptions`.


### `inputDirectory`
{#prepareJarSearchableOptions-inputDirectory}

Specifies the directory where the prepared searchable options are read from.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: [`buildSearchableOptions.outputDirectory`](#buildSearchableOptions-outputDirectory)


### `outputDirectory`
{#prepareJarSearchableOptions-outputDirectory}

Specifies the directory where the filtered content is placed.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/tmp/prepareJarSearchableOptions</path>


### `libContainer`
{#prepareJarSearchableOptions-libContainer}

Specifies the <path>lib</path> directory within the current sandbox.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[prepareSandbox.pluginDirectory]/lib</path>


### `composedJarFile`
{#prepareJarSearchableOptions-composedJarFile}

Specifies the final composed Jar archive with the plugin content.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`composedJar.archiveFile`](#composedJar-archiveFile)



## `prepareSandbox`
{#prepareSandbox}

<link-summary>Prepares a sandbox environment with the plugin and its dependencies installed.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: `jar`, [`instrumentedJar`](#instrumentedJar)

**Extends**: [`Sync`][gradle-jar-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt)

</tldr>

Prepares a sandbox environment with the plugin and its dependencies installed.
The sandbox directory is required by tasks that run IDE and tests in isolation from other instances, like when multiple IntelliJ Platforms are used for testing with [`runIde`](#runIde), [`prepareTest`](#prepareTest), [`testIdeUi`](#testIdeUi), or [`testIdePerformance`](#testIdePerformance) tasks.
The sandbox directory is created within the container configurable with [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

Tasks based on the [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt) are _sandbox producers_ and can be associated with _sandbox consumers_.
To define the consumer task, make it extend from [`SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware) and apply the `consumer.applySandboxFrom(producer)` function.


### `sandboxSuffix`
{#prepareSandbox-sandboxSuffix}

Represents the suffix used i.e., for test-related or custom tasks.

The default suffix is composed of the task name (`prepare[X]Sandbox[_Y]`) to the `-[X][Y]` format.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`SandboxAware.sandboxPluginsDirectory`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware-sandboxPluginsDirectory)


### `defaultDestinationDirectory`
{#prepareSandbox-defaultDestinationDirectory}

Specifies the default sandbox destination directory where plugin files will be copied.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: [`SandboxAware.sandboxPluginsDirectory`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware-sandboxPluginsDirectory)


### `pluginName`
{#prepareSandbox-pluginName}

Specifies the name of the plugin directory in the sandbox.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.projectName`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-projectName)


### `pluginDirectory`
{#prepareSandbox-pluginDirectory}

Specifies the directory where the plugin artifacts are to be placed.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: [`defaultDestinationDirectory`](#prepareSandbox-defaultDestinationDirectory)/[`projectName`](#prepareSandbox-pluginName)


### `disabledPlugins`
{#prepareSandbox-disabledPlugins}

An internal field to hold a list of plugins to be disabled within the current sandbox.
This property is controlled with [`disablePlugin()`](tools_intellij_platform_gradle_plugin_testing_extension.md#plugins) method of [](tools_intellij_platform_gradle_plugin_testing_extension.md).

{type="narrow"}
Type
: `SetProperty<String>`


### `pluginJar`
{#prepareSandbox-pluginJar}

Specifies the output of the [`Jar`][gradle-jar-task] task.
The proper `Jar.archiveFile` picked depends on whether code instrumentation is enabled.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: `Jar.archiveFile`


### `pluginsClasspath`
{#prepareSandbox-pluginsClasspath}

Specifies a list of dependencies on external plugins resolved from the `intellijPlatformPluginsExtracted` configuration added with [Dependencies Extension](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins)

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `runtimeClasspath`
{#prepareSandbox-runtimeClasspath}

Dependencies defined with the `runtimeClasspath` configuration.

{type="narrow"}
Type
: `ConfigurableFileCollection`



## `prepareTest`
{#prepareTest}

<link-summary>Prepares the test task.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`prepareTestSandbox`](#prepareTestSandbox)

**Extends**: [`DefaultTask`][gradle-default-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`PrepareTestTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareTestTask.kt)

</tldr>

Prepares an immutable [`test`](#test) task and provides all necessary dependencies and configurations for a proper testing configuration.



## `prepareTestSandbox`
{#prepareTestSandbox}

<link-summary>Prepares the test task sandbox.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: `jar`, [`instrumentedJar`](#instrumentedJar)

**Extends**: [`Sync`][gradle-jar-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt)

</tldr>

The [`prepareSandbox`](#prepareSandbox) task instance configured to work with the [`test`](#test) task.



## `printBundledPlugins`
{#printBundledPlugins}

<link-summary>Prints the list of bundled plugins available within the currently targeted IntelliJ Platform.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`PrintBundledPluginsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrintBundledPluginsTask.kt)

</tldr>

Prints the list of bundled plugins available within the currently targeted IntelliJ Platform.



## `printProductsReleases`
{#printProductsReleases}

<link-summary>Prints the list of binary product releases that match criteria.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base)

**Extends**: [`DefaultTask`][gradle-default-task], [`ProductReleasesValueSource.FilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesValueSource-FilterParameters)

**Sources**: [`PrintProductsReleasesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrintProductsReleasesTask.kt)

</tldr>

Prints the list of binary product releases that, by default, match the currently selected IntelliJ Platform along with
[`intellijPlatform.pluginConfiguration.ideaVersion.sinceBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild)
and [`intellijPlatform.pluginConfiguration.ideaVersion.untilBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild) properties.

The filter used for retrieving the release list can be customized by using properties provided with
[`ProductReleasesValueSource.FilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesValueSource-FilterParameters).


### `productsReleases`
{#printProductsReleases-productsReleases}

Property that holds the list of product releases to print and which can be used to retrieve the result list.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: The output of `ProductReleasesValueSource` using default configuration

See also:
- [Types: `ProductReleasesValueSource.FilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesValueSource-FilterParameters)



## `publishPlugin`
{#publishPlugin}

<link-summary>Publishes the plugin to the remote plugins repository.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin), [`signPlugin`](#signPlugin)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`PublishPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PublishPluginTask.kt)

</tldr>

Publishes the plugin to the remote plugins repository, such as [JetBrains Marketplace](https://plugins.jetbrains.com).

See also:
- [Uploading a Plugin to JetBrains Marketplace](publishing_plugin.md#uploading-a-plugin-to-jetbrains-marketplace)
- [Publishing Plugin With Gradle](publishing_plugin.md#publishing-plugin-with-gradle)
- [Plugin upload API](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html)


### `archiveFile`
{#publishPlugin-archiveFile}

Specifies the ZIP archive file to be published to the remote repository.
By default, it uses the output [`signPlugin.archiveFile`](#signPlugin-archiveFile) if plugin signing is configured, otherwise the [`buildPlugin.archiveFile`](#buildPlugin-archiveFile).

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`signPlugin.archiveFile`](#signPlugin-archiveFile) or [`buildPlugin.archiveFile`](#buildPlugin-archiveFile)

See also:
- [Extension: `intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing)


### `host`
{#publishPlugin-host}

Specifies the URL host of a plugin repository.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.host`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-host)


### `token`
{#publishPlugin-token}

Specifies the authorization token.

{type="narrow"}
Type
: `Property<String>`

Required
: yes

Default value
: [`intellijPlatform.publishing.token`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-token)


### `channels`
{#publishPlugin-channels}

Specifies a list of [JetBrains Marketplace](https://plugins.jetbrains.com) channel names used as destination for the plugin upload.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: [`intellijPlatform.publishing.channels`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-channels)


### `hidden`
{#publishPlugin-hidden}

Publishes the plugin update and marks it as [hidden](https://plugins.jetbrains.com/docs/marketplace/hidden-plugin.html) to prevent public visibility after approval.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.hidden`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-hidden)


### `ideServices`
{#publishPlugin-ideServices}

Specifies if the IDE Services plugin repository service should be used.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.publishing.ideServices`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-ideServices)



## `runIde`
{#runIde}

<link-summary>Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin loaded.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`patchPluginXml`](#patchPluginXml), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`RunIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/RunIdeTask.kt)

</tldr>

Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin loaded.
It directly extends the [`JavaExec`][gradle-javaexec-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

This task runs against the IntelliJ Platform and plugins specified in project dependencies.
To register a customized task, use [`intelliJPlatformTestingExtension.runIde`](tools_intellij_platform_gradle_plugin_testing_extension.md).



## `runIdeForUiTests`
{#runIdeForUiTests}

<secondary-label ref="unavailable"/>

<link-summary>Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin and Robot Server plugin loaded.</link-summary>

Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin and Robot Server plugin loaded.

This task is not available by default and needs to be registered manually by applying the following code:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runIdeForUiTests by intellijPlatformTesting.runIde.registering {
  task {
    jvmArgumentProviders += CommandLineArgumentProvider {
      listOf(
        "-Drobot-server.port=8082",
        "-Dide.mac.message.dialogs.as.sheets=false",
        "-Djb.privacy.policy.text=<!--999.999-->",
        "-Djb.consents.confirmation.enabled=false",
      )
    }
  }
  plugins {
    robotServerPlugin()
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runIdeForUiTests {
    task {
      jvmArgumentProviders.add({
        [
          "-Drobot-server.port=8082",
          "-Dide.mac.message.dialogs.as.sheets=false",
          "-Djb.privacy.policy.text=<!--999.999-->",
          "-Djb.consents.confirmation.enabled=false",
        ]
      } as CommandLineArgumentProvider)
    }
    plugins {
      robotServerPlugin()
    }
  }
}
```

</tab>
</tabs>


## `setupDependencies`
{#setupDependencies}

<link-summary>Deprecated. A deprecated method for setting up IntelliJ Platform dependencies.</link-summary>

<secondary-label ref="deprecated"/>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`SetupDependenciesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/SetupDependenciesTask.kt)

</tldr>

A deprecated method for setting up IntelliJ Platform dependencies.

The `setupDependencies` task was automatically added to the ["After Sync" Gradle trigger](https://www.jetbrains.com/help/idea/work-with-gradle-tasks.html#config_triggers_gradle) to make the IntelliJ Platform dependency available for IntelliJ IDEA right after the Gradle synchronization.
This method is no longer needed as the dependency on IntelliJ Platform is declared directly in Gradle dependencies.

> It's recommended to remove any references to `setupDependencies` task. See the [Migration](tools_intellij_platform_gradle_plugin_migration.md#setupdependencies) page for more details.
>
{style="warning"}



## `signPlugin`
{#signPlugin}

<link-summary>Signs the plugin distribution ZIP archive with the provided key using the Marketplace ZIP Signer library.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)

**Sources**: [`SignPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/SignPluginTask.kt)

</tldr>

Signs the plugin distribution ZIP archive with the provided key using the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.
To sign the plugin before publishing to [JetBrains Marketplace](https://plugins.jetbrains.com) with the [`signPlugin`](#signPlugin) task,
it is required to provide a certificate chain and a private key with its password using the
[`intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing) extension.

As soon as [`privateKey`](#signPlugin-privateKey) (or [`privateKeyFile`](#signPlugin-privateKeyFile)) and [`certificateChain`](#signPlugin-certificateChain)
(or [`certificateChainFile`](#signPlugin-certificateChainFile) properties are specified,
this task will be executed automatically right before the [`publishPlugin`](#publishPlugin) task.

For more details, see [](plugin_signing.md).


### `archiveFile`
{#signPlugin-archiveFile}

Specifies the unsigned ZIP archive input file.
Corresponds to the `in` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`buildPlugin.archiveFile`](#buildPlugin-archiveFile)


### `signedArchiveFile`
{#signPlugin-signedArchiveFile}

Specifies the signed ZIP archive output file.
Corresponds to the `out` CLI option.

Predefined with the name of the ZIP archive file with `-signed` name suffix attached.
The output file is placed next to the input [`archiveFile`](#signPlugin-archiveFile).

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`signPlugin.archiveFile`](#signPlugin-archiveFile) with `-signed` suffix applied to the name


### `keyStore`
{#signPlugin-keyStore}

Specifies the KeyStore file path.
Corresponds to the `ks` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.keyStore`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-keyStore)


### `keyStorePassword`
{#signPlugin-keyStorePassword}

Specifies the KeyStore password.
Corresponds to the `ks-pass` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.keyStorePassword`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-keyStorePassword)


### `keyStoreKeyAlias`
{#signPlugin-keyStoreKeyAlias}

Specifies the KeyStore key alias.
Corresponds to the `ks-key-alias` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.keyStoreKeyAlias`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-keyStoreKeyAlias)


### `keyStoreType`
{#signPlugin-keyStoreType}

Specifies the KeyStore type.
Corresponds to the `ks-type` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.keyStoreType`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-keyStoreType)


### `keyStoreProviderName`
{#signPlugin-keyStoreProviderName}

Specifies the JCA KeyStore Provider name.
Corresponds to the `ks-provider-name` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.keyStoreProviderName`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-keyStoreProviderName)


### `privateKey`
{#signPlugin-privateKey}

Specifies the encoded private key in the PEM format.
Corresponds to the `key` CLI option.

Takes precedence over the [`privateKeyFile`](#signPlugin-privateKeyFile) property.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.privateKey`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-privateKey)


### `privateKeyFile`
{#signPlugin-privateKeyFile}

Specifies a file with an encoded private key in the PEM format.
Corresponds to the `key-file` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`intellijPlatform.signing.privateKeyFile`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-privateKeyFile)


### `password`
{#signPlugin-password}

Specifies the password required to decrypt the private key.
Corresponds to the `key-pass` CLI option.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.password`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-password)


### `certificateChain`
{#signPlugin-certificateChain}

Specifies a string containing X509 certificates.
The first certificate in the chain will be used as a certificate authority (CA).
This parameter corresponds to the `cert` CLI option.

Takes precedence over the [`certificateChainFile`](#signPlugin-certificateChainFile) property.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.certificateChain`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-certificateChain)


### `certificateChainFile`
{#signPlugin-certificateChainFile}

Specifies the path to the file containing X509 certificates.
The first certificate in the chain will be used as a certificate authority (CA).
Corresponds to the `cert-file` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`intellijPlatform.signing.certificateChainFile`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-certificateChainFile)



## `test`
{#test}

<link-summary>The base Gradle test task preconfigured to run IntelliJ Platform tests.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`prepareTest`](#prepareTest)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware)

**Sources**: [`TestCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/TestCompanion.kt)

</tldr>

The base Gradle `test` task is preconfigured using the [`TestCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/TestCompanion.kt) class to run tests with IntelliJ Platform, sandbox, and all system properties set.

The task itself isn't mutated and a dedicated [`prepareTest`](#prepareTest) task is involved to request for required IntelliJ Platform and sandbox configuration.



## `testIde`
{#testIde}

<secondary-label ref="unavailable"/>

<link-summary>Runs tests using a custom IntelliJ Platform with the developed plugin installed.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`prepareTest`](#prepareTest)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware)

**Sources**: [`TestIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeTask.kt)

</tldr>

> The `testIde` task is not registered by default.
>
{style="warning"}

Runs tests using a custom IntelliJ Platform with the developed plugin installed.
It directly extends the [Test][gradle-test-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

The [`TestIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeTask.kt) is a class used only for handling custom `testIde` tasks.

To register a customized test task, use [`intelliJPlatformTestingExtension.testIde`](tools_intellij_platform_gradle_plugin_testing_extension.md).



## `testIdePerformance`
{#testIdePerformance}

<secondary-label ref="incubating"/>

<link-summary>Runs performance tests on the IntelliJ Platform with the developed plugin installed.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareSandbox`](#prepareSandbox)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`TestIdePerformanceTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdePerformanceTask.kt)

</tldr>

> Not implemented.
>
{style="warning"}



## `testIdeUi`
{#testIdeUi}

<secondary-label ref="incubating"/>

<link-summary>Runs the IDE instance with the developed plugin and Starter framework for UI testing.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`Test`][gradle-test-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`TestIdeUiTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeUiTask.kt)

</tldr>

Runs the IDE instance with the developed plugin and Starter framework for UI testing.


### `archiveFile`
{#testIdeUi-archiveFile}

Specifies the archive file representing the input file to be tested.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`buildPlugin.archiveFile`](#buildPlugin-archiveFile)




## `verifyPlugin`
{#verifyPlugin}

<link-summary>Runs the IntelliJ Plugin Verifier CLI tool to check the binary compatibility with specified IDE builds.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RuntimeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RuntimeAware), [`PluginVerifierAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginVerifierAware)

**Sources**: [`VerifyPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

</tldr>

Runs the [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md) CLI tool to check the binary compatibility with specified IDE builds.

Due to caching, the latest Plugin Verifier release version (%plugin-verifier-version%) might not be picked up by Gradle immediately.
In such cases, [refresh dependencies](https://docs.gradle.org/current/userguide/dependency_caching.html#sec:refreshing-dependencies)
manually.

See also:
- [Extension: `intellijPlatform.pluginVerification`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification)
- [Types: `FailureLevel`](tools_intellij_platform_gradle_plugin_types.md#FailureLevel)
- [Types: `Subsystems`](tools_intellij_platform_gradle_plugin_types.md#Subsystems)
- [Types: `VerificationReportsFormats`](tools_intellij_platform_gradle_plugin_types.md#VerificationReportsFormats)
- [Verifying Plugin Compatibility](verifying_plugin_compatibility.md)
- [IntelliJ Plugin Verifier](https://github.com/JetBrains/intellij-plugin-verifier)


### `ides`
{#verifyPlugin-ides}

Specifies IntelliJ Platform IDEs used by the IntelliJ Plugin Verifier CLI tool for binary plugin verification.
The list of IDEs is managed through the [`intellijPlatform.pluginVerification.ides`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides) extension.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `archiveFile`
{#verifyPlugin-archiveFile}

Specifies the input ZIP archive file of the plugin to verify.
If this parameter is empty, the task will be skipped.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`buildPlugin.archiveFile`](#buildPlugin-archiveFile)


### `externalPrefixes`
{#verifyPlugin-externalPrefixes}

Specifies a list of class prefixes from external libraries.
The Plugin Verifier will not report `No such class` errors for classes in these packages.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: [`intellijPlatform.pluginVerification.externalPrefixes`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-externalPrefixes)


### `failureLevel`
{#verifyPlugin-failureLevel}

Specifies the verification level at which the task fails if any reported issue matches.

{type="narrow"}
Type
: [`ListProperty<FailureLevel>`](tools_intellij_platform_gradle_plugin_types.md#FailureLevel)

Default value
: [`intellijPlatform.pluginVerification.failureLevel`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-failureLevel)


### `freeArgs`
{#verifyPlugin-freeArgs}

Represents a list of free arguments that are passed directly to the IntelliJ Plugin Verifier CLI tool.
These arguments are used in conjunction with those provided by dedicated options.

See [](tools_intellij_platform_gradle_plugin_faq.md#mutePluginVerifierProblems) for sample usage.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: [`intellijPlatform.pluginVerification.freeArgs`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-freeArgs)


### `ignoredProblemsFile`
{#verifyPlugin-ignoredProblemsFile}

Specifies a file that contains a list of problems that will be ignored in a report.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`intellijPlatform.pluginVerification.ignoredProblemsFile`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ignoredProblemsFile)


### `offline`
{#verifyPlugin-offline}

Indicates whether the operation is executed in offline mode.
This depends on the start parameters specified in Gradle.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `StartParameter.isOffline`

See also:
- [StartParameter](https://docs.gradle.org/current/javadoc/org/gradle/StartParameter.html)
- [Command Line Execution Options](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options)


### `subsystemsToCheck`
{#verifyPlugin-subsystemsToCheck}

Specifies which subsystems of the IDE should be checked.

{type="narrow"}
Type
: [`Subsystems`](tools_intellij_platform_gradle_plugin_types.md#Subsystems)

Default value
: [`intellijPlatform.pluginVerification.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-subsystemsToCheck)


### `teamCityOutputFormat`
{#verifyPlugin-teamCityOutputFormat}

Specifies whether to use the TeamCity-compatible output format.
If set to `true`, outputs in a format compatible with [TeamCity](https://www.jetbrains.com/teamcity/), directing the output to stdout.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.pluginVerification.teamCityOutputFormat`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-teamCityOutputFormat)


### `verificationReportsDirectory`
{#verifyPlugin-verificationReportsDirectory}

Specifies the path to the directory where verification reports will be saved.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: [`intellijPlatform.pluginVerification.verificationReportsDirectory`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-verificationReportsDirectory)


### `verificationReportsFormats`
{#verifyPlugin-verificationReportsFormats}

Specifies the output formats of the verification reports.

{type="narrow"}
Type
: [`ListProperty<VerificationReportsFormats>`](tools_intellij_platform_gradle_plugin_types.md#VerificationReportsFormats)

Default value
: [`intellijPlatform.pluginVerification.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-verificationReportsFormats)


## `verifyPluginProjectConfiguration`
{#verifyPluginProjectConfiguration}

<link-summary>Validates the plugin project configuration.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`patchPluginXml`](#patchPluginXml)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware)

**Sources**: [`VerifyPluginProjectConfigurationTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginProjectConfigurationTask.kt)

</tldr>

Validates the plugin project configuration:
- The [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) property can't be lower than the target IntelliJ Platform major version.
- The Java/Kotlin `sourceCompatibility` and `targetCompatibility` properties should be aligned with the Java versions required by [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) and the currently used IntelliJ Platform.
- The Kotlin API version should be aligned with the version required by [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) and the currently used IntelliJ Platform.
- The used IntelliJ Platform version should be higher than `2022.3` (`223.0`).
- The dependency on the [](using_kotlin.md#kotlin-standard-library) should be excluded.
- The Kotlin plugin in version `1.8.20` is not used with IntelliJ Platform Gradle Plugin due to the 'java.lang.OutOfMemoryError: Java heap space' exception.
- The Kotlin Coroutines library should not be added explicitly to the project as it is already provided with the IntelliJ Platform.
- The IntelliJ Platform cache directory should be excluded from the version control system. Add the `.intellijPlatform` entry to the <path>.gitignore</path> file.
- The currently selected Java Runtime is not JetBrains Runtime (JBR).

For more details regarding the Java version used in the specific IntelliJ SDK, see [](build_number_ranges.md).

See also:
- [](build_number_ranges.md)
- [](using_kotlin.md#kotlin-standard-library)
- [](using_kotlin.md#incremental-compilation)


### `reportDirectory`
{#verifyPluginProjectConfiguration-reportDirectory}

Specifies the report directory where the verification result will be stored.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/reports/verifyPluginConfiguration</path>


### `rootProject`
{#verifyPluginProjectConfiguration-rootProject}

Specifies the root project path.

{type="narrow"}
Type
: `Property<File>`

Default value
: <path>[rootProject]</path>


### `intellijPlatformCache`
{#verifyPluginProjectConfiguration-intellijPlatformCache}

Specifies the IntelliJ Platform cache directory.

{type="narrow"}
Type
: `Property<File>`

Default value
: [`intellijPlatform.cachePath`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-cachePath)


### `gitignoreFile`
{#verifyPluginProjectConfiguration-gitignoreFile}

Specifies the `.gitignore` file located in the <path>[rootDirectory]</path>, tracked for content changes.

{type="narrow"}
Type
: `Property<File>`

Default value
: <path>[rootProject]/.gitignore</path>


### `sourceCompatibility`
{#verifyPluginProjectConfiguration-sourceCompatibility}

Specifies the `JavaCompile.sourceCompatibility` property value defined in the build script.

{type="narrow"}
Type
: `Property<String>`

Default value
: `JavaCompile.sourceCompatibility`


### `targetCompatibility`
{#verifyPluginProjectConfiguration-targetCompatibility}

Specifies the `JavaCompile.targetCompatibility` property value defined in the build script.

{type="narrow"}
Type
: `Property<String>`

Default value
: `JavaCompile.targetCompatibility`


### `kotlinPluginAvailable`
{#verifyPluginProjectConfiguration-kotlinPluginAvailable}

Indicates that the Kotlin Gradle Plugin is loaded and available.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: Kotlin Gradle Plugin presence


### `kotlinApiVersion`
{#verifyPluginProjectConfiguration-kotlinApiVersion}

Specifies the `apiVersion` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `compileKotlin.kotlinOptions.apiVersion`


### `kotlinLanguageVersion`
{#verifyPluginProjectConfiguration-kotlinLanguageVersion}

Specifies the `languageVersion` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `compileKotlin.kotlinOptions.languageVersion`


### `kotlinVersion`
{#verifyPluginProjectConfiguration-kotlinVersion}

Specifies the version of Kotlin used in the project.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `kotlin.coreLibrariesVersion`


### `kotlinJvmTarget`
{#verifyPluginProjectConfiguration-kotlinJvmTarget}

Specifies the `jvmTarget` property value of `compileKotlin.kotlinOptions` defined in the build script.

{type="narrow"}
Type
: `Property<String?>`

Default value
: `compileKotlin.kotlinOptions.jvmTarget`


### `kotlinStdlibDefaultDependency`
{#verifyPluginProjectConfiguration-kotlinStdlibDefaultDependency}

Specifies the value of the `kotlin.stdlib.default.dependency` property as defined in the <path>gradle.properties</path> file.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `kotlin.stdlib.default.dependency` Gradle property


### `kotlinxCoroutinesLibraryPresent`
{#verifyPluginProjectConfiguration-kotlinxCoroutinesLibraryPresent}

Indicates whether the Kotlin Coroutines library is explicitly added to the project dependencies.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: The `org.jetbrains.kotlinx:kotlinx-coroutines` dependency presence


### `hasModulePlugin`
{#verifyPluginProjectConfiguration-hasModulePlugin}

Defines if the current module is a main project or imported module, which uses [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: The [](tools_intellij_platform_gradle_plugin_plugins.md#module) plugin presence



## `verifyPluginSignature`
{#verifyPluginSignature}

<link-summary>Validates the signature of the plugin archive file using the Marketplace ZIP Signer library.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginSignatureTask.kt)

</tldr>

Validates the signature of the plugin archive file using the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.

See also:
- [](plugin_signing.md)
- [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer)


### `inputArchiveFile`
{#verifyPluginSignature-inputArchiveFile}

Specifies the input, unsigned ZIP archive file.
This parameter corresponds to the `in` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`signPlugin.signedArchiveFile`](#signPlugin-signedArchiveFile)


### `certificateChain`
{#verifyPluginSignature-certificateChain}

Specifies a string containing X509 certificates.
The first certificate in the chain will be used as a certificate authority (CA).
This parameter corresponds to the `cert` CLI option.

Takes precedence over the [`certificateChainFile`](#verifyPluginSignature-certificateChainFile) property.

{type="narrow"}
Type
: `Property<String>`


### `certificateChainFile`
{#verifyPluginSignature-certificateChainFile}

Specifies the path to the file containing X509 certificates.
The first certificate in the chain will be used as a certificate authority (CA).
This parameter corresponds to the `cert-file` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`signPlugin.certificateChainFile`](#signPlugin-certificateChainFile) or [`signPlugin.certificateChain`](#signPlugin-certificateChain) written to a temporary file



## `verifyPluginStructure`
{#verifyPluginStructure}

<link-summary>Validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin archive structure.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareSandbox`](#prepareSandbox)

**Extends**: [`DefaultTask`][gradle-default-task], [`SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware)

**Sources**: [`VerifyPluginStructureTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginStructureTask.kt)

</tldr>

Validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin archive structure.

See also:
- [](plugin_configuration_file.md)


### `ignoreFailures`
{#verifyPluginStructure-ignoreFailures}

Specifies whether the build should fail when the verifications performed by this task fail.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `ignoreUnacceptableWarnings`
{#verifyPluginStructure-ignoreUnacceptableWarnings}

Specifies whether the build should fail when the verifications performed by this task emit unacceptable warnings.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `ignoreWarnings`
{#verifyPluginStructure-ignoreWarnings}

Specifies whether the build should fail when the verifications performed by this task emit warnings.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `true`


### `pluginDirectory`
{#verifyPluginStructure-pluginDirectory}

Specifies the location of the built plugin file used for verification.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[`prepareSandbox.defaultDestinationDirectory`](#prepareSandbox-defaultDestinationDirectory)/[`intellijPlatform.pluginConfiguration.name`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-name)</path>



<include from="snippets.topic" element-id="missingContent"/>

[gradle-default-task]: https://docs.gradle.org/current/dsl/org.gradle.api.DefaultTask.html
[gradle-jar-task]: https://docs.gradle.org/current/dsl/org.gradle.jvm.tasks.Jar.html
[gradle-javaexec-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html
[gradle-sync-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html
[gradle-test-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html
[gradle-zip-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html
