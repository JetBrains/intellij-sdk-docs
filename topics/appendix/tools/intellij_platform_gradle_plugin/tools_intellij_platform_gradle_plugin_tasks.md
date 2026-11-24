<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Tasks

<link-summary>IntelliJ Platform Gradle Plugin tasks.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

The IntelliJ Platform Gradle Plugin introduces a set of tasks to handle activities of the plugin development for IntelliJ-based IDEs, such as building, verifying, testing, and publishing the plugin archive.

Task availability depends on the applied [plugin](tools_intellij_platform_gradle_plugin_plugins.md).
Some registered tasks are skipped by configuration flags, while custom testing tasks must be registered explicitly.

Since `2.19.0`, public tasks are organized into the `intellij platform`, `intellij platform verification`, and `intellij platform info` groups.
Internal preparation tasks have no group and are hidden from the default task listing.
Run `./gradlew tasks --all` to include them.

Tasks can depend on each other, implement [](tools_intellij_platform_gradle_plugin_task_awares.md), and be configured independently.
Caching is task-specific: interactive IDE runs, tests, and reports are untracked, while archive tasks and other utilities may disable build caching.
However, most cases will be covered by the [](tools_intellij_platform_gradle_plugin_extension.md).

The plugin organizes the tasks it registers into dedicated Gradle groups, visible in the <control>Gradle</control> tool window and in the `gradle tasks` output:

- <control>intellij platform</control> – building and running tasks, such as [`buildPlugin`](#buildPlugin), [`runIde`](#runIde), [`signPlugin`](#signPlugin), and [`publishPlugin`](#publishPlugin).
- <control>intellij platform verification</control> – verification tasks, such as [`verifyPlugin`](#verifyPlugin), [`verifyPluginProjectConfiguration`](#verifyPluginProjectConfiguration), [`verifyPluginStructure`](#verifyPluginStructure), and [`verifyPluginSignature`](#verifyPluginSignature).
- <control>intellij platform info</control> – informational tasks that print resolved data, such as [`printBundledPlugins`](#printBundledPlugins), [`printBundledModules`](#printBundledModules), and [`printProductsReleases`](#printProductsReleases).

Intermediate and internal tasks, such as [`prepareSandbox`](#prepareSandbox) or [`instrumentCode`](#instrumentCode), are not assigned to any group and appear under Gradle's other tasks.



## `buildPlugin`
{#buildPlugin}

<link-summary>Builds the plugin and prepares the ZIP archive for testing and deployment.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`jarSearchableOptions`](#jarSearchableOptions), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`Zip`][gradle-zip-task]

**Sources**: [`BuildPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildPluginTask.kt)

</tldr>

Builds the plugin and prepares the ZIP archive for testing and deployment.

It takes the output of the [`prepareSandbox`](#prepareSandbox) task containing the built project with all its modules and dependencies, and the output of [`jarSearchableOptions`](#jarSearchableOptions) task.

The produced archive is stored in <path>[buildDirectory]/distributions/[`archiveFile`](#buildPlugin-archiveFile)</path>,
where the name and location of [`archiveFile`](#buildPlugin-archiveFile) can be controlled with properties provided with the
[`Zip`](https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html) base task.
By default, `archiveBaseName` is set from [`intellijPlatform.projectName`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-projectName), not the display name in <path>plugin.xml</path>.

<include from="plugin_content.md" element-id="doNotRepackageLibraries"/>


### `archiveFile`
{#buildPlugin-archiveFile}

Specifies the archive file representing the output file produced by the task.

{type="narrow"}
Type
: `RegularFileProperty`



## `buildPluginVariants`
{#buildPluginVariants}

<link-summary>Builds all OS- and architecture-specific plugin distributions.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: The six [`buildPluginVariants_<os>_<arch>`](#buildPluginVariants-variant-tasks) tasks when native variants are enabled

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`BuildPluginVariantsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildPluginVariantsTask.kt)

</tldr>

Builds the fixed matrix of Linux, macOS, and Windows plugin distributions for `x86_64` and `arm64` architectures.
Available since `2.19.0`.
The task runs its variant tasks only when [`intellijPlatform.nativeVariants.enabled`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants-enabled) is `true`.
It does not change the base archive produced by [`buildPlugin`](#buildPlugin).

Each variant archive is written to <path>[buildDirectory]/distributions</path> with the `-<os>-<arch>` classifier, for example, <path>myPlugin-1.0.0-linux-arm64.zip</path>.
Its plugin descriptor version receives the same suffix and declares the corresponding `com.intellij.modules.os.<os>` and `com.intellij.modules.arch.<arch>` dependencies.
Files configured for that target in [`intellijPlatform.nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants) are copied into the plugin distribution.
The prepared variant JAR and target-specific files take precedence over duplicate paths from the base sandbox.


### `buildPluginVariants_<os>_<arch>`
{#buildPluginVariants-variant-tasks}

The following [`BuildPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildPluginTask.kt) instances produce the individual archives:

| Target          | Task                                      | Archive classifier  |
|-----------------|-------------------------------------------|---------------------|
| Linux x86_64    | `buildPluginVariants_linux_x86_64`        | `linux-x86_64`      |
| Linux arm64     | `buildPluginVariants_linux_arm64`         | `linux-arm64`       |
| macOS x86_64    | `buildPluginVariants_mac_x86_64`          | `mac-x86_64`        |
| macOS arm64     | `buildPluginVariants_mac_arm64`           | `mac-arm64`         |
| Windows x86_64  | `buildPluginVariants_windows_x86_64`      | `windows-x86_64`    |
| Windows arm64   | `buildPluginVariants_windows_arm64`       | `windows-arm64`     |

All six tasks are registered, even when no native files are configured for a target, and are skipped while native variants are disabled.


### `archiveFiles`
{#buildPluginVariants-archiveFiles}

The variant ZIP archives produced by the six target tasks.
The collection declares all six output paths even when native variants are disabled; a path in the collection does not establish that the archive has been built.

{type="narrow"}
Type
: `ConfigurableFileCollection`



## `buildSearchableOptions`
{#buildSearchableOptions}

<link-summary>Builds the index of UI components (searchable options) for the plugin.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareSandbox`](#prepareSandbox)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware)

**Sources**: [`BuildSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/BuildSearchableOptionsTask.kt)

</tldr>

Builds the index of UI components (searchable options) for the plugin.
This task runs a headless IDE instance to collect all the available options provided by the plugin's [](settings.md).

The task is skipped automatically when the main plugin descriptor and plugin module descriptors don't declare `Configurable` extension points.
Use the [`forceBuildSearchableOptions`](tools_intellij_platform_gradle_plugin_gradle_properties.md#forceBuildSearchableOptions) Gradle property to force execution even when descriptor analysis would skip it.
Without forcing, execution also requires [`intellijPlatform.buildSearchableOptions`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-buildSearchableOptions) to be enabled.
The force property takes precedence over both descriptor detection and that extension flag.
The same condition controls [`prepareJarSearchableOptions`](#prepareJarSearchableOptions) and [`jarSearchableOptions`](#jarSearchableOptions).

In the case of running the task for the plugin that has [`intellijPlatform.pluginConfiguration.productDescriptor`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-productDescriptor) defined, a warning will be logged regarding potential issues with running headless IDE for paid plugins.
It is possible to mute this warning with the [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) Gradle property.


### `outputDirectory`
{#buildSearchableOptions-outputDirectory}

Specifies the directory where searchable options will be generated.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/tmp/buildSearchableOptions</path>


### `showPaidPluginWarning`
{#buildSearchableOptions-showPaidPluginWarning}

Emits a warning when the task is executed by a paid plugin.
Can be disabled with the [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) Gradle property.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`paidPluginSearchableOptionsWarning`](tools_intellij_platform_gradle_plugin_gradle_properties.md#paidPluginSearchableOptionsWarning) && `productDescriptor` is defined


### `pluginXmlService`
{#buildSearchableOptions-pluginXmlService}

Shared descriptor-parsing service supplied by the plugin.
This is task infrastructure, not a user configuration option.

{type="narrow"}
Type
: `Property<PluginXmlService>`


## `cleanSandbox`
{#cleanSandbox}

<link-summary>Cleans sandbox data produced for the current Gradle project.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Extends**: [`Delete`][gradle-delete-task]

**Sources**: [`CleanSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/CleanSandboxTask.kt)

</tldr>

Cleans the sandbox directory created by [`prepareSandbox`](#prepareSandbox) for the current project.
This removes all suffixed sandboxes under the selected product/version directory, but not the entire sandbox container or other product/version directories.



## `composedJar`
{#composedJar}

<link-summary>Composes a final JAR by combining the base JAR, and instrumented classes, and declared submodules.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: The selected [`jar`][gradle-jar-task] or [`instrumentedJar`](#instrumentedJar), and composed plugin modules

**Extends**: [`Jar`][gradle-jar-task], [`ModuleAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ModuleAware)

**Sources**: [`ComposedJarTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/ComposedJarTask.kt)

</tldr>

Composes a final JAR by combining the output of base `jar` or [`instrumentedJar`](#instrumentedJar) tasks,
depending on if code instrumentation is enabled with [`intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode).

The final JAR is also combined with plugin modules marked using the [`pluginComposedModule`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) dependencies helper.
For module projects, the archive base name combines the root project name and the dot-separated project path, and the archive version is empty.
For distribution projects, the base name is `project.name` and the version comes from `intellijPlatform.pluginConfiguration.version`.


### `archiveFile`
{#composedJar-archiveFile}

Specifies the archive file representing the output file produced by the task.

{type="narrow"}
Type
: `RegularFileProperty`



## `dumpBundledModules`
{#dumpBundledModules}

<link-summary>Writes bundled module IDs and names to a file for Plugin DevKit.</link-summary>

<tldr>

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`DumpBundledModulesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/DumpBundledModulesTask.kt)

</tldr>

Writes the selected IDE's bundled modules as sorted, distinct, tab-separated ID/name pairs, one per line.
This ungrouped Plugin DevKit utility always runs and only rewrites the output when its content changes.
It uses the same cached IDE layout index as [`printBundledModules`](#printBundledModules).

### `outputFile`
{#dumpBundledModules-outputFile}

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/tmp/dumpBundledModules/bundled-modules.txt</path>

### `ideLayoutIndexCacheDirectory`
{#dumpBundledModules-ideLayoutIndexCacheDirectory}

The `DirectoryProperty` for cached layout-index snapshots, conventionally shared with dependency resolution and the corresponding print task.


## `dumpBundledPlugins`
{#dumpBundledPlugins}

<link-summary>Writes bundled plugin IDs and names to a file for Plugin DevKit.</link-summary>

<tldr>

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`DumpBundledPluginsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/DumpBundledPluginsTask.kt)

</tldr>

Writes sorted, distinct, tab-separated plugin ID/name pairs, one per line, using the same index as [`printBundledPlugins`](#printBundledPlugins).
Like `dumpBundledModules`, this utility is ungrouped, always runs, and only rewrites changed content.

### `outputFile`
{#dumpBundledPlugins-outputFile}

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/tmp/dumpBundledPlugins/bundled-plugins.txt</path>

### `ideLayoutIndexCacheDirectory`
{#dumpBundledPlugins-ideLayoutIndexCacheDirectory}

The `DirectoryProperty` for cached layout-index snapshots shared with dependency resolution and the corresponding print task.


## `dumpProductsReleases`
{#dumpProductsReleases}

<link-summary>Writes known IntelliJ Platform product releases to a file for Plugin DevKit.</link-summary>

<tldr>

**Extends**: [`DefaultTask`][gradle-default-task], [`ProductReleasesServiceAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProductReleasesServiceAware)

**Sources**: [`DumpProductsReleasesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/DumpProductsReleasesTask.kt)

</tldr>

Writes sorted, distinct, tab-separated product code, version, and channel values, one release per line.
It includes all product types and channels from the minimum supported platform build, rather than using the current plugin's compatibility bounds or selecting only the latest releases.
This ungrouped, untracked utility only rewrites changed content.

### `outputFile`
{#dumpProductsReleases-outputFile}

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/tmp/dumpProductsReleases/product-releases.txt</path>


## `generateManifest`
{#generateManifest}

<link-summary>Generates the <path>MANIFEST.MF</path> file with all relevant information about the project configuration.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Extends**: [`DefaultTask`][gradle-default-task], [`KotlinMetadataAware`](tools_intellij_platform_gradle_plugin_task_awares.md#KotlinMetadataAware)

**Sources**: [`GenerateManifestTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/GenerateManifestTask.kt)

</tldr>

Generates the <path>MANIFEST.MF</path> file with all relevant information about the project configuration.

The manifest contains `Created-By`, `Version`, `Build-JVM`, `Build-OS`, `Build-Plugin`, `Build-Plugin-Version`,
`Platform-Type`, `Platform-Version`, `Platform-Build`, `Kotlin-Available`, `Kotlin-Stdlib-Bundled`, and `Kotlin-Version` entries.

To apply the produced manifest file to an additional [`Jar`][gradle-jar-task] task, call `JarCompanion.applyPluginManifest()`.

This file is bundled into the output JAR files produced by [`composedJar`](#composedJar), [`instrumentedJar`](#instrumentedJar), and [`Jar`][gradle-jar-task] tasks.


### `pluginVersion`
{#generateManifest-pluginVersion}

The IntelliJ Platform Gradle Plugin version.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`initializeIntelliJPlatformPlugin.pluginVersion`](#initializeIntelliJPlatformPlugin-pluginVersion)


### `gradleVersion`
{#generateManifest-gradleVersion}

The version of currently used Gradle.

{type="narrow"}
Type
: `Property<String>`

Default value
: The current Gradle version


### `platformType`
{#generateManifest-platformType}

The product code of the current IntelliJ Platform, for example, `IC` or `IU`.

{type="narrow"}
Type
: `Property<String>`

Default value
: The selected IntelliJ Platform product code


### `platformVersion`
{#generateManifest-platformVersion}

The marketing version of the current IntelliJ Platform, for example, `2025.3`.

{type="narrow"}
Type
: `Property<String>`

Default value
: The selected IntelliJ Platform product version


### `platformBuild`
{#generateManifest-platformBuild}

The build number of the current IntelliJ Platform, for example, `253.12345.67`.

{type="narrow"}
Type
: `Property<String>`

Default value
: The selected IntelliJ Platform build number


### `version`
{#generateManifest-version}

Plugin version.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.version`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-version)


### `generatedManifest`
{#generateManifest-generatedManifest}

Location of the generated <path>MANIFEST.MF</path> file.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/tmp/generateManifest/MANIFEST.MF</path>


## `generateLexer`
{#generateLexer}

<link-summary>Generates a lexer with JFlex for IntelliJ Platform GrammarKit projects.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`JavaExec`][gradle-javaexec-task]

**Sources**: [`GenerateLexerTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/GenerateLexerTask.kt)

</tldr>

Generates a lexer from a `.flex` definition file using JFlex.
Available since `2.12.0`.


### `sourceFile`
{#generateLexer-sourceFile}

The source Flex file used to generate the lexer.

{type="narrow"}
Type
: `RegularFileProperty`


### `targetRootOutputDir`
{#generateLexer-targetRootOutputDir}

The root output directory for the generated lexer.
The lexer file is created under a subdirectory matching [`packageName`](#generateLexer-packageName), unless `packageName` is empty.
When the legacy [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated) is not used and [`pathToClass`](#generateLexer-pathToClass) is not set, the task exclusively owns this directory, declares the entire directory as its output, and can be passed directly to `sourceSets.main.java.srcDir(tasks.generateLexer)` so Gradle infers the task dependency.
Set `pathToClass` when this directory is shared with other generators.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/generated/sources/grammarkit-lexer/java/main</path>


### `pathToClass`
{#generateLexer-pathToClass}

The generated lexer class location relative to [`targetRootOutputDir`](#generateLexer-targetRootOutputDir), or relative to the legacy [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated) when that property is used.
Setting this property switches the task to shared-root mode: only this file is declared as an output and removed during cleanup, leaving other files under the root untouched.

The value must include the generated filename and any package directories, for example, `com/example/MyLexer.java`.

{type="narrow"}
Type
: `Property<String>`

Required
: no


### `packageName`
{#generateLexer-packageName}

The Java package where the lexer file is generated.
By default, the task tries to detect the package declaration from [`sourceFile`](#generateLexer-sourceFile).
Set this property to an empty string (`""`) to generate the lexer in the root output directory.

{type="narrow"}
Type
: `Property<String>`

Default value
: Detected from [`sourceFile`](#generateLexer-sourceFile), or empty string when no package declaration is found


### `targetOutputDir`
{#generateLexer-targetOutputDir-deprecated}

<secondary-label ref="deprecated"/>

The legacy output directory for the generated lexer.
When this property is set, it takes precedence over [`targetRootOutputDir`](#generateLexer-targetRootOutputDir), the Java file is created directly below this directory, and [`packageName`](#generateLexer-packageName) is ignored.

Stale files in this directory are not deleted unless [`purgeOldFiles`](#generateLexer-purgeOldFiles) is explicitly set to `true`.

{type="narrow"}
Type
: `DirectoryProperty`


### `skeleton`
{#generateLexer-skeleton}

Optional path to the skeleton file passed with the `--skel` option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: Not set; no `--skel` option is passed unless configured


### `purgeOldFiles`
{#generateLexer-purgeOldFiles}

Purges previously generated lexer output before generating the lexer.
When cleanup is enabled and [`pathToClass`](#generateLexer-pathToClass) is not set, the task removes the entire active output directory: [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated) when present, otherwise [`targetRootOutputDir`](#generateLexer-targetRootOutputDir).
When `pathToClass` is set, only that generated file in the active output directory is removed so the root can be shared safely.
When the deprecated [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated) is used, old files are not purged unless this property is explicitly set to `true`.
Set this property to `false` to disable cleanup.

{type="narrow"}
Type
: `Property<Boolean>`


### `targetFile(...)`
{#generateLexer-targetFile}

<secondary-label ref="deprecated"/>

Legacy helper methods returning the expected lexer file below [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated).
Use [`pathToClass`](#generateLexer-pathToClass) instead.
With [`targetRootOutputDir`](#generateLexer-targetRootOutputDir), include the package directories in `pathToClass`.
With the legacy [`targetOutputDir`](#generateLexer-targetOutputDir-deprecated), `pathToClass` is relative to that directory and [`packageName`](#generateLexer-packageName) is ignored.


## `generateParser`
{#generateParser}

<link-summary>Generates a parser with GrammarKit for IntelliJ Platform projects.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`JavaExec`][gradle-javaexec-task]

**Sources**: [`GenerateParserTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/GenerateParserTask.kt)

</tldr>

Generates parser and PSI files from a `.bnf` definition file using GrammarKit.
Available since `2.12.0`.


### `sourceFile`
{#generateParser-sourceFile}

The source BNF file used to generate the parser.

{type="narrow"}
Type
: `RegularFileProperty`


### `targetRootOutputDir`
{#generateParser-targetRootOutputDir}

The root output directory for generated parser and PSI files.
When [`pathToParser`](#generateParser-pathToParser) and [`pathToPsiRoot`](#generateParser-pathToPsiRoot) are not set, the task exclusively owns this directory, declares the entire directory as its output, and can be passed directly to `sourceSets.main.java.srcDir(tasks.generateParser)` so Gradle infers the task dependency.
Set both path properties when this directory is shared with other generators.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/generated/sources/grammarkit-parser/java/main</path>


### `pathToParser`
{#generateParser-pathToParser}

The generated parser class location relative to [`targetRootOutputDir`](#generateParser-targetRootOutputDir).
Setting this property with [`pathToPsiRoot`](#generateParser-pathToPsiRoot) switches the task to shared-root mode, in which only the configured parser file and PSI directory are declared as outputs and removed during cleanup.
If this property is set, [`pathToPsiRoot`](#generateParser-pathToPsiRoot) must also be set.

{type="narrow"}
Type
: `Property<String>`


### `pathToPsiRoot`
{#generateParser-pathToPsiRoot}

The generated PSI root location relative to [`targetRootOutputDir`](#generateParser-targetRootOutputDir).
Setting this property with [`pathToParser`](#generateParser-pathToParser) switches the task to shared-root mode, in which only the configured parser file and PSI directory are declared as outputs and removed during cleanup.
If this property is set, [`pathToParser`](#generateParser-pathToParser) must also be set.

{type="narrow"}
Type
: `Property<String>`


### `parserFile()`
{#generateParser-parserFile}

Returns the parser file below [`targetRootOutputDir`](#generateParser-targetRootOutputDir) computed from [`pathToParser`](#generateParser-pathToParser).


### `psiDir()`
{#generateParser-psiDir}

Returns the PSI directory below [`targetRootOutputDir`](#generateParser-targetRootOutputDir) computed from [`pathToPsiRoot`](#generateParser-pathToPsiRoot).


### `purgeOldFiles`
{#generateParser-purgeOldFiles}

Purges previously generated parser and PSI files before generation.
With no explicit paths, the task owns and removes the entire [`targetRootOutputDir`](#generateParser-targetRootOutputDir).
When both [`pathToParser`](#generateParser-pathToParser) and [`pathToPsiRoot`](#generateParser-pathToPsiRoot) are set, only the configured parser file and PSI directory are removed so the root can be shared safely.
Set this property to `false` to disable cleanup.

{type="narrow"}
Type
: `Property<Boolean>`


## `generateSplitModeRunConfigurations`
{#generateSplitModeRunConfigurations}

<link-summary>Generates shared IntelliJ IDEA run configurations for split-mode frontend and backend runs.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`GenerateSplitModeRunConfigurationsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/GenerateSplitModeRunConfigurationsTask.kt)

</tldr>

Generates shared IntelliJ IDEA run configurations for split-mode frontend and backend runs, plus a compound run configuration that starts both.
Available since `2.14.0`.
The generated Gradle run configurations invoke [`runIdeBackend`](#runIdeBackend) and [`runIdeFrontend`](#runIdeFrontend) with `--purge-old-log-directories`.
The compound configuration starts those two tasks, not the [`runIdeSplitMode`](#runIdeSplitMode) aggregate task.
Execution rewrites the three configured output files.

This utility task is intentionally ungrouped, so it can be invoked directly without adding another entry to the standard Gradle task listing.


### `projectPath`
{#generateSplitModeRunConfigurations-projectPath}

The Gradle project path used to qualify generated task paths.

{type="narrow"}
Type
: `Property<String>`

Default value
: Current project path


### `backendConfigurationFile`
{#generateSplitModeRunConfigurations-backendConfigurationFile}

The generated backend run configuration file.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[projectDirectory]/.run/runIdeBackend.run.xml</path>


### `frontendConfigurationFile`
{#generateSplitModeRunConfigurations-frontendConfigurationFile}

The generated frontend run configuration file.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[projectDirectory]/.run/runIdeFrontend.run.xml</path>


### `compoundConfigurationFile`
{#generateSplitModeRunConfigurations-compoundConfigurationFile}

The generated compound run configuration file.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[projectDirectory]/.run/runIdeSplitMode.run.xml</path>



## `initializeIntelliJPlatformPlugin`
{#initializeIntelliJPlatformPlugin}

<link-summary>Initializes the IntelliJ Platform Gradle Plugin</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base), [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`DefaultTask`][gradle-default-task], [`ModuleAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ModuleAware)

**Sources**: [`InitializeIntelliJPlatformPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InitializeIntelliJPlatformPluginTask.kt)

</tldr>

Checks whether a newer IntelliJ Platform Gradle Plugin version is available.
Task preconfiguration adds this dependency to aware tasks in non-module projects; it is not a dependency of every Gradle task or the metadata-only JSON reports.
The check is skipped for module projects, in offline mode, when disabled, or when the lock file already records today's date.
It warns about an available update but does not update the build configuration.

The self-update check can be disabled via [`selfUpdateCheck`](tools_intellij_platform_gradle_plugin_gradle_properties.md#selfUpdateCheck) Gradle property.


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

Records the date of the last successful version check, limiting checks to once per day.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[intellijPlatform.caching.path]/self-update.lock</path>


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
: `Property<Boolean>`



## `instrumentCode`
{#instrumentCode}

<link-summary>Executes the code instrumentation.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: The source set's `classes` task

**Extends**: [`DefaultTask`][gradle-default-task], [`JavaCompilerAware`](tools_intellij_platform_gradle_plugin_task_awares.md#JavaCompilerAware)

**Sources**: [`InstrumentCodeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InstrumentCodeTask.kt)

</tldr>

Executes the code instrumentation using the Ant tasks provided by the used IntelliJ Platform dependency.
The code instrumentation scans the compiled Java and Kotlin classes for JetBrains Annotations usages to replace them with their relevant functionalities.
It also compiles GUI Designer forms and writes instrumented copies without modifying the original class directories.

The task is controlled with the [`intellijPlatform.instrumentCode`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-instrumentCode) extension property, enabled by default.
To properly run the instrumentation, a Java Compiler dependency must be available.
This is applied by default by the plugin; the former `instrumentationTools()` helper was removed and calling it is no longer necessary.
The dependency can still be configured manually via [`javaCompiler()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#code-instrumentation) if needed.
This dependency is resolved via the [`intellijDependencies()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#additional-repositories) repository, which can be added separately or using the [`defaultRepositories()`](tools_intellij_platform_gradle_plugin_repositories_extension.md#default-repositories) helper.

See also:
- [](#instrumentedJar)


### `sourceSetCompileClasspath`
{#instrumentCode-sourceSetCompileClasspath}

Specifies the compile classpath of the project's source set.

{type="narrow"}
Type
: `ConfigurableFileCollection`

Default value
: The source set's `compileClasspath`


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

Default value
: The source set's `allJava.srcDirs`


### `instrumentationLogs`
{#instrumentCode-instrumentationLogs}

Enables `INFO` logging when running Ant tasks.

{type="narrow"}
Type
: `Property<Boolean>`

Default value:
: `true` when Gradle's log level is `INFO`; otherwise `false`


### `outputDirectory`
{#instrumentCode-outputDirectory}

Specifies the output directory for instrumented classes.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/instrumented/[taskName]</path>


## `instrumentTestCode`
{#instrumentTestCode}

The [`InstrumentCodeTask`](#instrumentCode) instance for the `test` source set.
It depends on `testClasses` and writes to <path>[buildDirectory]/instrumented/instrumentTestCode</path>.
The ordinary [`test`](#test) and custom [`testIde`](#testIde) tasks use its output before the original test classes.
Instrumentation is controlled by the same `intellijPlatform.instrumentCode` flag.
Registration also creates corresponding `instrument<SourceSet>Code` tasks for the other source sets present at registration time.



## `instrumentedJar`
{#instrumentedJar}

<link-summary>Creates a JAR file with instrumented classes.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: `jar`, [`instrumentCode`](#instrumentCode)

**Extends**: [`Jar`][gradle-jar-task]

**Sources**: [`InstrumentedJarTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/InstrumentedJarTask.kt)

</tldr>

Creates a copy of the current module's `jar` task output with instrumented classes added.
The archive classifier defaults to `instrumented`, and instrumented classes take precedence over base JAR entries.



## JSON Reporting Tasks
{#json-reports}

Available since `2.20.0`.

The following non-interactive tasks write project and SDK observations for people, CI, and command-line agents.

> In `2.20.0`, collecting UI-test dependency declarations for configuration reports can trigger IDE resolution even in declaration-only modes.
> This specific declaration-resolution issue is fixed in `2.20.1`.
>
{style="note"}

They are also available in module projects.
Parse the JSON file, not Gradle's console output.

| Task | Default file under <path>[buildDirectory]/reports/intellij-platform</path> | Observation cost |
|------|-------------------------------------------------------------------------|------------------|
| [`intellijPlatformInfo`](#intellijPlatformInfo) | <path>info.json</path> | Metadata; optional IDE/plugin resolution |
| [`intellijPlatformProjectGraph`](#intellijPlatformProjectGraph) | <path>projectgraph.json</path> | Metadata |
| [`intellijPlatformDistribution`](#intellijPlatformDistribution) | <path>distribution.json</path> | Metadata; optional existing-archive inspection |
| [`intellijPlatformDependencyGraph`](#intellijPlatformDependencyGraph) | <path>dependencygraph.json</path> | Metadata; optional dependency resolution |
| [`intellijPlatformTestMatrix`](#intellijPlatformTestMatrix) | <path>testmatrix.json</path> | Metadata |
| [`intellijPlatformCompatibilityMatrix`](#intellijPlatformCompatibilityMatrix) | <path>compatibilitymatrix.json</path> | Metadata |
| [`intellijPlatformDoctor`](#intellijPlatformDoctor) | <path>doctor.json</path> | Selected IDE and patched descriptor |
| [`intellijPlatformDiscover`](#intellijPlatformDiscover) | <path>discover.json</path> | Selected IDE for catalogs; release listings for releases |
| [`intellijPlatformSdk`](#intellijPlatformSdk) | <path>sdk.json</path> | Selected IDE and main compilation classpath |

These tasks do not compile sources, prepare sandboxes, run tests or IDEs, sign, publish, or edit project sources.
Doctor can run `patchPluginXml`, which writes under <path>build</path>.
Resolution can download metadata or artifacts and write caches; Gradle's `--offline` is respected.
Metadata-only modes deliberately leave lazy dependency and compiler settings unresolved rather than acquiring an IDE or JDK to fill missing fields.

All reports use the version-1 envelope defined by [`AgentReportTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/AgentReportTask.kt):

```json
{
  "schemaVersion": 1,
  "reportType": "info",
  "gradlePluginVersion": "…",
  "projectPath": ":plugin",
  "status": "complete",
  "data": {},
  "issues": [],
  "unavailable": []
}
```

Reports are UTF-8 JSON with deterministic ordering, explicit nulls, and no timestamps.
They are written atomically, always execute, are not restored from the build cache, and support the configuration cache.
Consumers must ignore unknown additive fields within a schema version.

`status` distinguishes `complete`, `partial`, and `failed` observations.
Each `unavailable` entry identifies an observation, a state (`notRequested`, `notResolved`, or `unavailable`), and a reason.
Intentionally unrequested resolution is not an operational failure.
Findings in `issues` have stable IDs, severity, a message, suppression state, and optional settings, fixes, documentation, and diagnostic evidence.
Configured targets and existing report directories are never evidence that verification succeeded.

Handled operational failures can write a report before failing Gradle; always check the Gradle result as well as the JSON.
A report is not guaranteed after invalid options, build-script or plugin-application failures, uncaught failures, or an unwritable destination.
For automation, use an invocation-specific `--output=<path>` that does not already exist, and consume it only after confirming that the invocation wrote it.
An existing file at a reused output path can be left over from an earlier invocation, even when the current build fails.
See [](tools_intellij_platform_gradle_plugin_recipes.md#diagnose-gradle-configuration) for a diagnosis workflow.
Reports exclude arbitrary environment variables, credentials, publishing tokens, and signing configuration, but paths can identify the local machine and should be reviewed before sharing.
See [CLI Reports](%gh-ijpgp%/CLI_REPORTS.md) for the full data contract.

### Common Properties
{#json-reports-properties}

| Property | Type | Convention or purpose |
|----------|------|-----------------------|
| `outputFile` | `RegularFileProperty` | The report path in the table above; `--output=<path>` overrides it, relative to the current project |
| `projectPath` | `Property<String>` | Current Gradle project path |
| `pluginVersion` | `Property<String>` | Current IntelliJ Platform Gradle Plugin version |

All nine tasks extend `AgentReportTask`.
The Info, Project Graph, Distribution, Dependency Graph, Test Matrix, and Compatibility Matrix types also expose a `snapshot: Property<String>` containing plugin-supplied serialized configuration metadata.
Snapshots and other infrastructure properties below are populated by the plugin and should not be edited as report inputs.

Replace `<invocation-id>` with a unique value for each invocation:

```bash
./gradlew :plugin:intellijPlatformInfo --output="build/reports/<invocation-id>/project context.json"
```


## `intellijPlatformCompatibilityMatrix`
{#intellijPlatformCompatibilityMatrix}

<link-summary>Reports declared compatibility bounds and configured targets without verification.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformCompatibilityMatrixTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformCompatibilityMatrixTask.kt)

</tldr>

Reports raw descriptor bounds, configured development/test/verifier targets, mapping-based Java/Kotlin requirements, and native-variant restrictions.
It does not resolve IDEs, run Plugin Verifier, consume verification results, or infer success from report directories.
Raw bounds can differ from effective DSL and patch-task overrides.
Targets remain `untested` and `verifiedSuccess` is empty.
Only safely observable declarations are included; local, lazy, and recommended verifier selections can remain unresolved.

Uses the [common report properties](#json-reports-properties), including the plugin-supplied `snapshot`.


## `intellijPlatformDependencyGraph`
{#intellijPlatformDependencyGraph}

<link-summary>Reports declared dependencies, with opt-in resolution of components and edges.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformDependencyGraphTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformDependencyGraphTask.kt)

</tldr>

Separates declared dependencies, constraints, exclusions, and configuration roles from resolved components and relationships.
By default, it does not realize deferred declarations or resolve configurations.
With `--resolve`, it queries Gradle's resolution result for resolvable configurations, including selected variants, selection reasons, and unresolved edges.
This can fetch metadata; resolution callbacks may perform additional work, but the report does not download artifact files merely to describe the graph.

| Property | Type | Default / command-line option |
|----------|------|-------------------------------|
| `resolve` | `Property<Boolean>` | `false`; `--resolve` |
| `configurationName` | `Property<String>` | Empty selects all configurations; `--configuration=<exact-name>` |
| `dependencyFilter` | `Property<String>` | Empty selects all dependencies; `--dependency=<case-sensitive-substring>` |

Dependency filtering changes presentation, not resolution or conflict selection.
Matching edges, their endpoints, and the graph root are retained, but not necessarily every ancestor path.
Filtering cannot hide a resolution failure.
An unknown configuration, an explicitly selected non-resolvable configuration with `--resolve`, or failed resolution writes a failed report before failing the task.

```bash
./gradlew :plugin:intellijPlatformDependencyGraph --resolve --configuration=runtimeClasspath
```


## `intellijPlatformDiscover`
{#intellijPlatformDiscover}

<link-summary>Discovers bundled plugins, modules, or product releases as a filtered JSON catalog.</link-summary>

<tldr>

**Extends**: `AgentReportTask`, [`ProductReleasesServiceAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProductReleasesServiceAware), [`ProductReleasesFilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesFilterParameters)

**Sources**: [`IntelliJPlatformDiscoverTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformDiscoverTask.kt)

</tldr>

Returns all matching entries in deterministic order; consumers can slice the result locally.
Plugin/module discovery resolves the selected IDE and exposes IDs, names, aliases, archive paths, original files, and dependency references.
Duplicate IDs or aliases remain distinct entries.
References are report-local, and catalog membership does not establish compatibility.

| Property | Type | Default / command-line option |
|----------|------|-------------------------------|
| `kind` | `Property<String>` | `plugins`; `--kind=plugins`, `--kind=modules`, or `--kind=releases` |
| `query` | `Property<String>` | Empty; `--query=<text>` performs a case-insensitive substring match |
| `sinceBuild` | `Property<String>` | Unset; `--since-build=<build-or-version>` |
| `untilBuild` | `Property<String>` | Unset; `--until-build=<build-or-version>` supports a trailing wildcard |

Release discovery uses the release-filter `types` and `channels` properties, defaulting to all product codes and channels.
The `type(String)` and `channel(String)` option methods accept comma-separated values through `--type=IU,PS` and `--channel=RELEASE,EAP`.
They reject unsupported product codes or channels.
Queries match catalog IDs/names/aliases or release notation, version, build, and channel.

`--kind=releases` uses cached product listings and does not resolve an IDE.
An offline cache miss or any other empty release result produces an empty, `partial` report without failing the task; an empty plugin/module match is a successful empty catalog.
Requested discovery failures write a failed report before failing Gradle.

Infrastructure properties are `platformFiles: ConfigurableFileCollection` (empty for release discovery) and `ideLayoutIndexCacheDirectory: DirectoryProperty` (shared layout-index cache).

```bash
./gradlew :plugin:intellijPlatformDiscover --kind=releases --type=IU --channel=RELEASE --since-build=251 --until-build=251.*
```


## `intellijPlatformDistribution`
{#intellijPlatformDistribution}

<link-summary>Reports packaging declarations and optionally inspects an existing plugin ZIP.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformDistributionTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformDistributionTask.kt)

</tldr>

Separates declared packaging configuration, descriptor relationships, planned paths, and native overlays from observed existing files.
It does not resolve runtime dependencies, patch descriptors, compile, or build archives.
An existing patched descriptor can be read, but its freshness is not established.
Lazy providers, unresolved files, and arbitrary user copy specifications are not treated as an effective complete distribution plan.

| Property | Type | Default / command-line option |
|----------|------|-------------------------------|
| `inspect` | `Property<Boolean>` | `false`; `--inspect` inspects the existing configured `buildPlugin` ZIP |
| `archivePath` | `Property<String>` | Unset; `--archive=<existing-zip>` selects a project-relative or absolute path and implies inspection |

Inspection does not add a dependency on `buildPlugin`.
It inventories ZIP/JAR entries and descriptors without extracting them, and compares only observable planned paths, not bytes or archive freshness.
Inspection is bounded by entry, descriptor, XML, and expanded nested-JAR size limits; it is not complete archive validation.
Missing, malformed, unsafe, or oversized requested archives produce a failed report and task failure.
A successful inspection can still leave the report `partial`.

```bash
./gradlew :plugin:intellijPlatformDistribution --archive="build/distributions/plugin.zip" --offline
```


## `intellijPlatformDoctor`
{#intellijPlatformDoctor}

<link-summary>Reports actionable configuration diagnostics against the resolved IDE.</link-summary>

<tldr>

**Extends**: `AgentReportTask`, [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`KotlinMetadataAware`](tools_intellij_platform_gradle_plugin_task_awares.md#KotlinMetadataAware), [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware)

**Sources**: [`IntelliJPlatformDoctorTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformDoctorTask.kt)

</tldr>

Resolves the selected IDE and reads the effective patched descriptor without compiling.
It shares the configuration-rule evaluator with [`verifyPluginProjectConfiguration`](#verifyPluginProjectConfiguration), but explicitly leaves the runtime-vendor check `notRequested` to avoid acquiring a runtime.
Module projects skip descriptor checks.

Findings include stable IDs, affected settings, suggested fixes, observed/expected values, evidence, provenance, applicable IDE descriptions, and a qualified rerun command where available.
Locations identify known files or effective settings, not invented source lines.
Diagnostic category (`warning`, `incompatibility`, or `blockingConfigurationError`) is distinct from severity.

| Property | Type | Convention / command-line option |
|----------|------|----------------------------------|
| `failOn` | `Property<String>` | `none`; `--fail-on=none`, `--fail-on=warning`, or `--fail-on=error` |
| `mutedMessages` | `ListProperty<String>` | `verifyPluginProjectConfigurationMutedMessages` Gradle property |
| `sourceCompatibility` | `Property<String>` | `compileJava.options.release`, falling back to `compileJava.sourceCompatibility` |
| `targetCompatibility` | `Property<String>` | `compileJava.options.release`, falling back to `compileJava.targetCompatibility` |
| `cacheIgnored` | `Property<Boolean>` | Whether the existing default root cache is mentioned in the existing root <path>.gitignore</path>; unset when inapplicable |

Findings do not fail the task by default.
The requested `failOn` threshold is applied to unsuppressed findings after writing the report.
Muted findings remain in JSON with `suppressed: true`; categories do not change this policy.
No fixes are applied automatically.


## `intellijPlatformInfo`
{#intellijPlatformInfo}

<link-summary>Reports project context and available workflows without resolving an IDE by default.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformInfoTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformInfoTask.kt)

</tldr>

Reports project/plugin identity, module status, directly observable requested IDE identity, Java/Kotlin settings, allowlisted switches, dependency declarations, and project-qualified commands.
Compiler settings distinguish declared constants from unresolved effective values, and raw selectors such as `latest` remain selectors rather than triggering release lookup.
The `capabilities` entries describe task prerequisites and potential execution costs, including custom tests and native-variant builds; they do not prove execution.

### `resolve`
{#intellijPlatformInfo-resolve}

Enables resolution of IDE and plugin artifacts, adding `resolvedIde` and `resolvedPlugins` to the report.
This can download artifacts and respects `--offline`.
Failed requested resolution writes a failed report and then fails the task.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

Command-line option
: `--resolve`

The plugin supplies `platformFiles` and `pluginFiles` as `ConfigurableFileCollection` infrastructure properties, empty unless resolution is requested.


## `intellijPlatformProjectGraph`
{#intellijPlatformProjectGraph}

<link-summary>Reports current-build projects, source roots, and declared project dependencies.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformProjectGraphTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformProjectGraphTask.kt)

</tldr>

Reports projects, build files, applied plugin classes, source/resource roots, and project-dependency edges without resolving dependencies or compiling.
Configuration names distinguish ordinary libraries, plugin modules, and composed modules.
Project and source-set roles are inferred conventions, not authoritative declarations.
Included builds are identified where observable, but their project models and external plugin origins are not traversed.

Uses the [common report properties](#json-reports-properties), including the plugin-supplied `snapshot`.
Use the qualified root task path to avoid Gradle's unqualified multi-project task selection:

```bash
./gradlew :intellijPlatformProjectGraph --offline
```


## `intellijPlatformSdk`
{#intellijPlatformSdk}

<link-summary>Maps the selected SDK's owners, archives, compilation classpath, and local sources.</link-summary>

<tldr>

**Extends**: `AgentReportTask`, [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`IntelliJPlatformSdkTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformSdkTask.kt)

</tldr>

Resolves the selected IDE and effective main compilation classpath without compiling sources.
The report includes the absolute SDK root and target identity, bundled plugin/module owners, SDK-relative archives, and classpath entries classified as `sdk`, `project`, or `external`.
SDK classpath entries reference their owners using report-local refs.
Sources are existing archives found at <path>src.zip</path>, <path>sources.zip</path>, and <path>lib/src</path>, not inferred from `downloadSources` and not downloaded by this task.

### `owner`
{#intellijPlatformSdk-owner}

Filters displayed owners by exact ID or alias, without filtering classpath entries or changing their full-catalog ownership refs.
Refs can therefore refer to owners omitted from a filtered report; obtain an unfiltered report to dereference them all.
An unknown owner produces an empty owner list.

{type="narrow"}
Type
: `Property<String>`

Command-line option
: `--owner=<id-or-alias>`

Infrastructure properties are `compileClasspath: ListProperty<String>` (observed file paths), `rootProjectDirectory: DirectoryProperty`, and `ideLayoutIndexCacheDirectory: DirectoryProperty` (shared layout-index cache).
An unresolved compilation classpath writes a `partial` report before failing the task.
Missing local source archives make the report `partial` without themselves failing it.


## `intellijPlatformTestMatrix`
{#intellijPlatformTestMatrix}

<link-summary>Reports configured JVM, IDE, UI, and performance test workflows without running them.</link-summary>

<tldr>

**Sources**: [`IntelliJPlatformTestMatrixTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/agent/IntelliJPlatformTestMatrixTask.kt)

</tldr>

Lists configured tasks, frameworks, conventional source-set associations, prerequisites, result locations, and qualified execution/filter commands where observable.
Ordinary JVM, IDE, Starter UI, and performance workflows are distinguished along with their potential execution costs.
It does not run tests, prepare sandboxes, resolve test classpaths or runtimes, or infer results from existing directories.
Source-set associations and output paths can remain unavailable, and all execution statuses remain `untested`.

Uses the [common report properties](#json-reports-properties), including the plugin-supplied `snapshot`.


## `jar`
{#jar}

The standard Gradle [`Jar`][gradle-jar-task] task is configured with `archiveBaseName` from `intellijPlatform.projectName` and the classifier `base`.
It depends on [`generateManifest`](#generateManifest), includes the generated manifest, and excludes <path>classpath.index</path> files.
This base archive is an input to [`instrumentedJar`](#instrumentedJar) and [`composedJar`](#composedJar), not the final plugin distribution.

**Sources**: [`JarCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/JarCompanion.kt)


## `jarSearchableOptions`
{#jarSearchableOptions}

<link-summary>Creates a JAR file with searchable options to be distributed with the plugin.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareJarSearchableOptions`](#prepareJarSearchableOptions)

**Extends**: [`Jar`][gradle-jar-task]

**Sources**: [`JarSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/JarSearchableOptionsTask.kt)

</tldr>

Creates a JAR file with searchable options to be distributed with the plugin.
The archive classifier defaults to `searchableOptions`.
The task and its outgoing artifact are enabled by the same condition as [`buildSearchableOptions`](#buildSearchableOptions).


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
The check recognizes both <path>*.searchableOptions.xml</path> and <path>*-searchableOptions.json</path> files in the JAR inputs.
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

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`PatchPluginXmlTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PatchPluginXmlTask.kt)

</tldr>

Patches <path>plugin.xml</path> file with values provided with the [`intelliJPlatform.pluginConfiguration`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration) extension.
Only configured values replace descriptor content, except an absent `untilBuild`, which removes the `until-build` attribute.
Missing elements are created when a configured value needs them.
Replacing a different, non-empty element or attribute value emits a warning.
Blank or `unspecified` project versions do not overwrite the descriptor's version.
The patched descriptor is included by `processResources` under <path>META-INF</path>.


### `inputFile`
{#patchPluginXml-inputFile}

Specifies the input <path>plugin.xml</path> file.
The convention selects the first existing <path>META-INF/plugin.xml</path> in the main source set's resource directories, which can differ from the standard location below.
If no input file is configured or found by the convention, the task is skipped.

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

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildSearchableOptions`](#buildSearchableOptions), [`prepareSandbox`](#prepareSandbox)

**Extends**: [`DefaultTask`][gradle-default-task], [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware)

**Sources**: [`PrepareJarSearchableOptionsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareJarSearchableOptionsTask.kt)

</tldr>

Collects the content produced with `buildSearchableOptions` for the `jarSearchableOptions`.
The task filters searchable option files using the main plugin descriptor when it exists and any searchable option descriptors found in plugin module projects.
This also supports composed multi-module builds where the root project doesn't define its own <path>plugin.xml</path>.
XML files ending in <path>.searchableOptions.xml</path> are retained only when the corresponding JAR exists in `libContainer`, and are placed under <path>search</path>.
JSON files ending in <path>-searchableOptions.json</path> are matched against plugin IDs and content-module names, and placed at the JAR root.
The task uses the same enablement condition as [`buildSearchableOptions`](#buildSearchableOptions).


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


### `searchableOptionsDescriptors`
{#prepareJarSearchableOptions-searchableOptionsDescriptors}

Plugin descriptor files used to match generated searchable options to plugin IDs and module names.
The default value includes descriptors from the main source set and from module projects attached through plugin module dependencies.

{type="narrow"}
Type
: `ConfigurableFileCollection`

### `pluginXmlService`
{#prepareJarSearchableOptions-pluginXmlService}

Shared descriptor-parsing service supplied by the plugin, not a user configuration option.

{type="narrow"}
Type
: `Property<PluginXmlService>`



## `preparePluginVariant_<os>_<arch>`
{#preparePluginVariant}

<link-summary>Creates an OS- and architecture-specific plugin JAR.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`composedJar`](#composedJar)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`PreparePluginVariantTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PreparePluginVariantTask.kt)

</tldr>

The six `preparePluginVariant_<os>_<arch>` tasks create the plugin JARs consumed by the corresponding [`buildPluginVariants_<os>_<arch>`](#buildPluginVariants-variant-tasks) tasks.
Available since `2.19.0`.
They are registered for the same Linux, macOS, and Windows `x86_64`/`arm64` matrix and run only when [`intellijPlatform.nativeVariants.enabled`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants-enabled) is `true`.

Before creating a variant JAR, each task validates the effective [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) value.
The value must resolve to `261` (IntelliJ Platform 2026.1) or later because the injected operating-system and architecture module dependencies are unavailable in earlier platform versions.

Each task copies the shared [`composedJar`](#composedJar), appends `-<os>-<arch>` to the version in <path>META-INF/plugin.xml</path>, and adds the matching operating-system and architecture module dependencies.
The resulting JAR is stored under <path>[buildDirectory]/intermediates/pluginVariants/[os]-[arch]</path>.
The task clears its output directory before creating the variant; do not share that directory with other outputs.


### `inputJar`
{#preparePluginVariant-inputJar}

The shared composed plugin JAR used as the source of the variant.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`composedJar.archiveFile`](#composedJar-archiveFile)


### `pluginVersion`
{#preparePluginVariant-pluginVersion}

The plugin version written to the variant's <path>META-INF/plugin.xml</path> file.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.pluginConfiguration.version`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-version) with the `-<os>-<arch>` suffix


### `operatingSystem`
{#preparePluginVariant-operatingSystem}

The target operating-system identifier used for the `com.intellij.modules.os.<os>` dependency.
The version written to the descriptor is controlled independently by [`pluginVersion`](#preparePluginVariant-pluginVersion).

{type="narrow"}
Type
: `Property<String>`

Default value
: `linux`, `mac`, or `windows`, configured from the fixed variant for each task registered by the plugin


### `architecture`
{#preparePluginVariant-architecture}

The target architecture identifier used for the `com.intellij.modules.arch.<arch>` dependency.
The version written to the descriptor is controlled independently by [`pluginVersion`](#preparePluginVariant-pluginVersion).

{type="narrow"}
Type
: `Property<String>`

Default value
: `x86_64` or `arm64`, configured from the fixed variant for each task registered by the plugin


### `outputDirectory`
{#preparePluginVariant-outputDirectory}

The directory containing the prepared variant JAR.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: <path>[buildDirectory]/intermediates/pluginVariants/[os]-[arch]</path>


## `prepareSandbox`
{#prepareSandbox}

<link-summary>Prepares a sandbox environment with the plugin and its dependencies installed.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`composedJar`](#composedJar)

**Extends**: [`Sync`][gradle-sync-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt)

</tldr>

Prepares a sandbox environment with the plugin and its dependencies installed.
The sandbox isolates IDE and test instances used by [`runIde`](#runIde), [`prepareTest`](#prepareTest), and [`testIdePerformance`](#testIdePerformance).
The sandbox directory is created within the container configurable with [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

Tasks based on the [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt) are _sandbox producers_ and can be associated with _sandbox consumers_.
To define a consumer task, implement [`SandboxAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware) and call `applySandboxFrom()` with the producer's `TaskProvider`.
The producer copies runtime dependencies into <path>lib</path>, plugin modules into <path>lib/modules</path>, and external plugin dependencies into the sandbox plugins directory.
It also disables IDE update checks and writes the disabled-plugin list.

When native variants are enabled, native-aware sandbox producers use the variant matching the current operating system and architecture.
For those sandboxes, the matching [`preparePluginVariant_<os>_<arch>`](#preparePluginVariant) output replaces the base plugin JAR, and files configured for that target in [`intellijPlatform.nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants) are overlaid onto the sandbox plugin directory.
The base `prepareSandbox` task remains platform-independent and supplies the base [`buildPlugin`](#buildPlugin) archive; custom testing sandboxes other than `intellijPlatformTesting.runIde` also remain platform-independent.


### Native-Aware Sandbox Tasks
{#prepareSandbox-runIde}

The plugin enables the current host variant for these sandbox producers:

| Sandbox task                              | Consumer                                                                                  |
|-------------------------------------------|-------------------------------------------------------------------------------------------|
| `prepareSandbox_runIde`                   | [`runIde`](#runIde)                                                                       |
| `prepareSandbox_runIdeBackend`            | [`runIdeBackend`](#runIdeBackend) and [`runIdeSplitMode`](#runIdeSplitMode)                 |
| `prepareSandbox_runIdeFrontend`           | [`runIdeFrontend`](#runIdeFrontend) and [`runIdeSplitMode`](#runIdeSplitMode)               |
| [`prepareTestSandbox`](#prepareTestSandbox) | Ordinary Gradle [`test`](#test)                                                         |
| [`prepareTestIdePerformanceSandbox`](#prepareTestIdePerformanceSandbox) | Standard [`testIdePerformance`](#testIdePerformance)                  |
| `prepareSandbox_<customRunIdeTask>`        | Entries registered with `intellijPlatformTesting.runIde`                                  |


### `sandboxSuffix`
{#prepareSandbox-sandboxSuffix}

Represents the suffix used i.e., for test-related or custom tasks.

The suffix is derived from the task name: `prepareTestSandbox` produces `-test`, while `prepareSandbox_runIde` produces `_runIde`.
It is appended to the `config`, `plugins`, `system`, and `log` directory names under the product/version sandbox directory.

{type="narrow"}
Type
: `Property<String>`

Default value
: Derived from the task name, for example `""`, `-test`, or `-testIdePerformance`


### `defaultDestinationDirectory`
{#prepareSandbox-defaultDestinationDirectory}

Specifies the default sandbox destination directory where plugin files will be copied.

{type="narrow"}
Type
: `DirectoryProperty`

Default value
: [`SandboxAware.sandboxPluginsDirectory`](tools_intellij_platform_gradle_plugin_task_awares.md#SandboxAware-sandboxPluginsDirectory), or [`SplitModeAware.sandboxPluginsFrontendDirectory`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware-sandboxPluginsFrontendDirectory) when [`pluginInstallationTarget`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware-pluginInstallationTarget) is `FRONTEND`


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
: [`defaultDestinationDirectory`](#prepareSandbox-defaultDestinationDirectory)/[`pluginName`](#prepareSandbox-pluginName)


### `disabledPlugins`
{#prepareSandbox-disabledPlugins}

An internal field to hold a list of plugins to be disabled within the current sandbox.
This property is controlled with the `disablePlugin()` and `disablePlugins()` methods of [](tools_intellij_platform_gradle_plugin_testing_extension.md#plugins).

{type="narrow"}
Type
: `SetProperty<String>`


### `subscriptionKey`
{#prepareSandbox-subscriptionKey}

<secondary-label ref="incubating"/>

Specifies the IntelliJ IDEA subscription key file used in this sandbox.
Available since `2.19.0`.
The key file is copied to <path>config/idea.key</path>, and `com.intellij.modules.ultimate` is removed from <path>config/disabled_plugins.txt</path>.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`intellijPlatform.subscriptionKey`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-subscriptionKey)


### `pluginJar`
{#prepareSandbox-pluginJar}

Specifies the output archive copied into the sandbox as the main plugin artifact.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`composedJar.archiveFile`](#composedJar-archiveFile)


### `pluginsClasspath`
{#prepareSandbox-pluginsClasspath}

Specifies the external plugin dependency files supplied by the task's IntelliJ Platform plugin configuration.

{type="narrow"}
Type
: `ConfigurableFileCollection`


### `runtimeClasspath`
{#prepareSandbox-runtimeClasspath}

Dependencies copied into the sandbox plugin's <path>lib</path> directory.
Since `2.19.0`, regular sandboxes use `intellijPlatformSandboxRuntimeClasspath`, while test sandboxes use `intellijPlatformTestSandboxRuntimeClasspath`.
These dedicated configurations allow [sandbox-only dependency exclusions](tools_intellij_platform_gradle_plugin_dependencies_extension.md#sandbox-runtime-classpaths) without changing the project's compile or test classpaths.
Plugin modules and composed modules are subtracted from this collection because they are packaged separately.

{type="narrow"}
Type
: `ConfigurableFileCollection`



## `prepareTest`
{#prepareTest}

<link-summary>Prepares the test task.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`prepareTestSandbox`](#prepareTestSandbox)

**Extends**: [`DefaultTask`][gradle-default-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`PrepareTestTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareTestTask.kt)

</tldr>

Holds the runtime, classpath, and sandbox capabilities used to configure the ordinary [`test`](#test) task without changing its task type.
The `test` task depends on this preparation task.



## `prepareTestSandbox`
{#prepareTestSandbox}

<link-summary>Prepares the test task sandbox.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`composedJar`](#composedJar)

**Extends**: [`Sync`][gradle-sync-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt)

</tldr>

The [`prepareSandbox`](#prepareSandbox) task instance configured to work with the [`test`](#test) task.



## `prepareTestIdePerformanceSandbox`
{#prepareTestIdePerformanceSandbox}

<link-summary>Prepares the sandbox used by the `testIdePerformance` task.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`composedJar`](#composedJar)

**Extends**: [`Sync`][gradle-sync-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware)

**Sources**: [`PrepareSandboxTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrepareSandboxTask.kt)

</tldr>

The [`prepareSandbox`](#prepareSandbox) task instance configured to work with the [`testIdePerformance`](#testIdePerformance) task.


## `printBundledModules`
{#printBundledModules}

<link-summary>Prints the list of bundled modules available within the currently targeted IntelliJ Platform.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base), [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`PrintBundledModulesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrintBundledModulesTask.kt)

</tldr>

Prints the list of bundled modules available within the currently targeted IntelliJ Platform.
The output starts with `Bundled modules for <name> <version> (<build>):`, followed by sorted, unique module IDs, one per line.
The task uses the same cached IDE layout index as bundled module dependency resolution, so module IDs and aliases are resolved consistently.


### `ideLayoutIndexService`
{#printBundledModules-ideLayoutIndexService}

Shared service used to resolve the cached IDE layout index for the currently targeted IDE.
This is an internal implementation property, not a supported task configuration API.

{type="narrow"}
Type
: `Property<IdeLayoutIndexService>`


### `ideLayoutIndexCacheDirectory`
{#printBundledModules-ideLayoutIndexCacheDirectory}

On-disk cache location for layout-index snapshots derived from extracted IDE distributions.

{type="narrow"}
Type
: `DirectoryProperty`


## `printBundledPlugins`
{#printBundledPlugins}

<link-summary>Prints the list of bundled plugins available within the currently targeted IntelliJ Platform.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base), [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`PrintBundledPluginsTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrintBundledPluginsTask.kt)

</tldr>

Prints the list of bundled plugins available within the currently targeted IntelliJ Platform.
The task uses the same cached IDE layout index as bundled plugin dependency resolution.


### `ideLayoutIndexService`
{#printBundledPlugins-ideLayoutIndexService}

Shared service used to resolve the cached IDE layout index for the currently targeted IDE.
This is an internal implementation property, not a supported task configuration API.

{type="narrow"}
Type
: `Property<IdeLayoutIndexService>`


### `ideLayoutIndexCacheDirectory`
{#printBundledPlugins-ideLayoutIndexCacheDirectory}

On-disk cache location for layout-index snapshots derived from extracted IDE distributions.

{type="narrow"}
Type
: `DirectoryProperty`



## `printProductsReleases`
{#printProductsReleases}

<link-summary>Prints the list of binary product releases that match criteria.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module), [](tools_intellij_platform_gradle_plugin_plugins.md#base), [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit)

**Extends**: [`DefaultTask`][gradle-default-task], [`ProductReleasesServiceAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProductReleasesServiceAware), [`ProductReleasesFilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesFilterParameters)

**Sources**: [`PrintProductsReleasesTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PrintProductsReleasesTask.kt)

</tldr>

Prints the list of binary product releases that, by default, match the currently selected IntelliJ Platform along with
[`intellijPlatform.pluginConfiguration.ideaVersion.sinceBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-sinceBuild)
and [`intellijPlatform.pluginConfiguration.ideaVersion.untilBuild`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild) properties.

The filter used for retrieving the release list can be customized by using properties provided with
[`ProductReleasesFilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesFilterParameters).
`types` defaults to the current product, `channels` to all release channels, and `sinceBuild`/`untilBuild` to the plugin's configured compatibility bounds.
The result contains the latest releases selected from the matching listings, not every known release.


### `productsReleases`
{#printProductsReleases-productsReleases}

Property that holds the list of product releases to print and which can be used to retrieve the result list.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: Dependency notations selected by `ProductReleasesService` using the task's filters

See also:
- [Types: `ProductReleasesFilterParameters`](tools_intellij_platform_gradle_plugin_types.md#ProductReleasesFilterParameters)



## `processResources`
{#processResources}

The standard Gradle `processResources` task includes the [`patchPluginXml`](#patchPluginXml) output under <path>META-INF</path>, retaining the patched descriptor when the original resource has the same path.
The output provider supplies the patch-task dependency.

**Sources**: [`ProcessResourcesCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/ProcessResourcesCompanion.kt)


## `publishPlugin`
{#publishPlugin}

<link-summary>Publishes the plugin to the remote plugins repository.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPluginVariants`](#buildPluginVariants) when native variants are enabled; otherwise [`buildPlugin`](#buildPlugin) and [`signPlugin`](#signPlugin)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`PublishPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/PublishPluginTask.kt)

</tldr>

Publishes one or more plugin archives to the remote plugins repository, such as [JetBrains Marketplace](https://plugins.jetbrains.com).
When [`intellijPlatform.nativeVariants.enabled`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants-enabled) is `true`, all six archives produced by [`buildPluginVariants`](#buildPluginVariants) are uploaded.
Native-variant archives are not automatically signed by `publishPlugin`.
Publishing is skipped with Gradle's `--offline` flag.
Each archive is uploaded to every configured channel; the literal channel name `default` selects the repository's default channel.

See also:
- [Uploading a Plugin to JetBrains Marketplace](publishing_plugin.md#uploading-a-plugin-to-jetbrains-marketplace)
- [Publishing Plugin With Gradle](publishing_plugin.md#publishing-plugin-with-gradle)
- [Plugin upload API](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html)


### `archiveFiles`
{#publishPlugin-archiveFiles}

Specifies the ZIP archive files to publish to the remote repository.
Configure the plural `archiveFiles` collection, not a singular `archiveFile` property.
With native variants enabled, the collection contains all [`buildPluginVariants.archiveFiles`](#buildPluginVariants-archiveFiles).
Otherwise, the current selection uses `signPlugin.didWork`: `true` selects [`signPlugin.signedArchiveFile`](#signPlugin-signedArchiveFile), and `false` selects [`buildPlugin.archiveFile`](#buildPlugin-archiveFile).
This includes signing tasks skipped as up-to-date; an existing signed archive alone does not select it.

{type="narrow"}
Type
: `ConfigurableFileCollection`

Default value
: [`buildPluginVariants.archiveFiles`](#buildPluginVariants-archiveFiles) when native variants are enabled; otherwise [`signPlugin.signedArchiveFile`](#signPlugin-signedArchiveFile) or [`buildPlugin.archiveFile`](#buildPlugin-archiveFile)

See also:
- [Extension: `intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing)
- [Extension: `intellijPlatform.nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants)


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
: `Property<Boolean>`

Default value
: [`intellijPlatform.publishing.hidden`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-hidden)


### `ideServices`
{#publishPlugin-ideServices}

Specifies if the IDE Services plugin repository service should be used.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.publishing.ideServices`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-ideServices)



## `runIde`
{#runIde}

<link-summary>Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin loaded.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`patchPluginXml`](#patchPluginXml), [`prepareSandbox_runIde`](#prepareSandbox-runIde)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware), [`ComposeHotReloadAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ComposeHotReloadAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`RunIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/RunIdeTask.kt)

</tldr>

Runs the IDE instance using the currently selected IntelliJ Platform with the built plugin loaded.
It directly extends the [`JavaExec`][gradle-javaexec-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

This task runs against the IntelliJ Platform and plugins specified in project dependencies.
To register a customized task, use [`intellijPlatformTesting.runIde`](tools_intellij_platform_gradle_plugin_testing_extension.md).

When [`splitMode`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware-splitMode) is enabled, the task starts the IDE in Split Mode.
Use [`runIdeSplitMode`](#runIdeSplitMode) to manage both processes in one Gradle invocation, or [`runIdeBackend`](#runIdeBackend) and [`runIdeFrontend`](#runIdeFrontend) to launch them separately.
Shared IDE run configurations can be generated with [`generateSplitModeRunConfigurations`](#generateSplitModeRunConfigurations).
Direct `args` are not supported when Split Mode is enabled; use `argumentProviders` instead.

On launch, the task logs the path to the sandbox log file (<path>idea.log</path> in the sandbox log directory) at the lifecycle level, prefixed with `IDE logs:`.
In Split Mode, the backend and frontend processes report their paths separately, prefixed with `IDE logs (backend):` and `IDE logs (frontend):`.


### `executionMode`
{#runIde-executionMode}

Selects the launch profile used by the task.

{type="narrow"}
Type
: `Property<RunIdeTask.ExecutionMode>`

Default value
: `STANDARD`

Values
: `STANDARD`, `SPLIT_MODE_BACKEND`, `SPLIT_MODE_FRONTEND`


### `splitModeServerPort`
{#runIde-splitModeServerPort}

The backend server port used by split-mode backend and frontend tasks.
Since `2.17.0`, setting it to `0` chooses and logs a random free port in `5990..6989`, with an OS-assigned ephemeral port as a fallback.
The resolved value remains stable for that execution.

{type="narrow"}
Type
: `Property<Int>`

Default value
: `5990`


### `execOperations`
{#runIde-execOperations}

Gradle's injected `ExecOperations` service used to launch managed processes.
This is task infrastructure, not a user configuration option.


### `splitModeFrontendJoinLink`
{#runIde-splitModeFrontendJoinLink}

An advanced override for the join link passed to the frontend process when [`runIdeFrontend`](#runIdeFrontend) is launched directly.
Usually, setting [`splitModeServerPort`](#runIde-splitModeServerPort) and starting [`runIdeBackend`](#runIdeBackend) first is sufficient.

{type="narrow"}
Type
: `Property<String>`


### `purgeOldLogDirectories`
{#runIde-purgeOldLogDirectories}

Removes stale sandbox log directories before launching the IDE.
Available since `2.15.0`.
This is useful for split-mode run configurations where frontend and backend logs are attached to the IDE run configuration.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

Command-line option
: `--purge-old-log-directories`


### `splitModeFrontendBootstrapClasspath`
{#runIde-splitModeFrontendBootstrapClasspath}

Bootstrap files used for direct frontend launches.
The plugin derives them from the distribution's `thinClient` launch metadata, falling back to <path>lib/platform-loader.jar</path>.

{type="narrow"}
Type
: `ConfigurableFileCollection`


## `runIdeBackend`
{#runIdeBackend}

<link-summary>Runs the IDE backend process in Split Mode.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`patchPluginXml`](#patchPluginXml), [`prepareSandbox_runIdeBackend`](#prepareSandbox-runIde)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware), [`ComposeHotReloadAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ComposeHotReloadAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`RunIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/RunIdeTask.kt)

</tldr>

Runs the IDE backend process in Split Mode.
Available since `2.14.0`.
The task writes the frontend join link to [`splitModeFrontendJoinLinkFile`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware-splitModeFrontendJoinLinkFile), which is then consumed by [`runIdeFrontend`](#runIdeFrontend).
It launches `serverMode -p <port>` and defaults to installing the plugin in the backend sandbox.
The backend PID is recorded on a best-effort basis and removed when the process exits.
A recorded live IDE process causes an immediate failure; an occupied port without that evidence only produces a warning.
Backend restart requests using exit code `88` are relaunched with the original Gradle JVM configuration, up to ten restarts.
The split-mode launchers use fixed development credentials and disable connection timeouts; they are not production remote-development server configurations.

Passing arguments directly with `args` is not supported for split-mode backend tasks.
Use `argumentProviders` instead.


## `runIdeFrontend`
{#runIdeFrontend}

<link-summary>Runs the JetBrains Client frontend process in Split Mode.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`patchPluginXml`](#patchPluginXml), [`prepareSandbox_runIdeFrontend`](#prepareSandbox-runIde)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`SplitModeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SplitModeAware), [`PluginInstallationTargetAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginInstallationTargetAware), [`ComposeHotReloadAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ComposeHotReloadAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`RunIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/RunIdeTask.kt)

</tldr>

Runs the JetBrains Client frontend process in Split Mode.
Available since `2.14.0`.
The task waits for the join link written by [`runIdeBackend`](#runIdeBackend), or uses [`splitModeFrontendJoinLink`](#runIde-splitModeFrontendJoinLink) when it is configured explicitly.
Without an explicit link, it waits up to 30 seconds for an available backend and then fails.
It launches `thinClient` from the full IDE distribution and defaults to installing the plugin in the frontend sandbox.
When JVM debugging is enabled and its default port is unchanged, the frontend uses port `5007` rather than the backend's `5005`.
Compose Hot Reload is not enabled for this process.

Passing arguments directly with `args` is not supported for split-mode frontend tasks.
Use `argumentProviders` instead.


## `runIdeSplitMode`
{#runIdeSplitMode}

<link-summary>Runs the IDE backend and JetBrains Client frontend in one Gradle invocation.</link-summary>

<tldr>

**Available in:** [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareSandbox_runIdeBackend`](#prepareSandbox-runIde), [`prepareSandbox_runIdeFrontend`](#prepareSandbox-runIde)

**Extends**: [`RunIdeTask`](#runIde)

**Sources**: [`RunIdeSplitModeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/RunIdeSplitModeTask.kt)

</tldr>

Starts the backend, waits up to 30 seconds for its join link, then starts the frontend using the separate frontend sandbox.
Available since `2.17.0`.
It uses the [`runIde`](#runIde) properties and enables Split Mode by default.
The backend launch is captured before frontend reconfiguration so backend restarts preserve its original settings.
After the frontend finishes successfully, the task waits for the backend to exit.
Exit code `193` is accepted as an expected shutdown only after the relevant process has launched.

Frontend JVM debugging is disabled in this aggregate task; use [`runIdeFrontend`](#runIdeFrontend) for frontend debugging.
Compose Hot Reload applies only to the backend.

```bash
./gradlew runIdeSplitMode --purge-old-log-directories
```



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

## `signPlugin`
{#signPlugin}

<link-summary>Signs the plugin distribution ZIP archive with the provided key using the Marketplace ZIP Signer library.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)

**Sources**: [`SignPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/SignPluginTask.kt)

</tldr>

Signs the plugin distribution ZIP archive with the provided key using the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.
Configure either a KeyStore or a PEM private key and certificate chain through [`intellijPlatform.signing`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing).
A KeyStore takes precedence and does not require separate PEM credentials.
For PEM signing, the private-key password is optional and is needed only for an encrypted key.
Inline private-key and certificate-chain values accept PEM text or Base64-encoded content and take precedence over their file properties.

For the standard archive produced by [`buildPlugin`](#buildPlugin), as soon as [`privateKey`](#signPlugin-privateKey) (or [`privateKeyFile`](#signPlugin-privateKeyFile)) and [`certificateChain`](#signPlugin-certificateChain)
(or [`certificateChainFile`](#signPlugin-certificateChainFile)) are specified,
this task is executed automatically before [`publishPlugin`](#publishPlugin).
Configuring [`keyStore`](#signPlugin-keyStore) also enables signing.
With neither a KeyStore nor a complete PEM key/certificate pair, the task is skipped.

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
: `RegularFileProperty`

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

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`prepareTest`](#prepareTest)

**Extends**: [`Test`][gradle-test-task]

**Sources**: [`TestCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/TestCompanion.kt)

</tldr>

The base Gradle `test` task is preconfigured using [`TestCompanion`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/companion/TestCompanion.kt) to run tests with IntelliJ Platform, sandbox, and all system properties set.

The task retains Gradle's `Test` type and uses [`prepareTest`](#prepareTest) for IntelliJ Platform and sandbox capabilities.

Since `2.20.0`, bundled plugins declared in the target IDE's <path>product-info.json</path> are added to the test classpath automatically, subject to [`testIdeBundledPluginsClasspathExcludes`](tools_intellij_platform_gradle_plugin_gradle_properties.md#testIdeBundledPluginsClasspathExcludes), class-clash detection, and transitive exclusions.
Explicitly declared plugins and modules, mandatory dependencies of the tested plugin and its plugin dependencies, and their dependencies are retained despite exclusions.
Required content-module descriptors are considered, including descriptors in split JARs under <path>lib/modules</path>.
Use `testBundledPlugin()` to declare a plugin explicitly when necessary, and `--info` to inspect exclusion reasons.
The former `testIdeBundledPluginsClasspathEnabled` switch is no longer used.

The classpath starts with instrumented tests and the tested plugin's libraries before other plugin libraries and the platform classpath.
Only JARs from plugin library directories are included.
The test JVM receives `GRADLE_USER_HOME` and an `idea.home.path` pointing to the selected IDE.



## `testIde`
{#testIde}

<link-summary>Runs tests using a custom IntelliJ Platform with the developed plugin installed.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Extends**: [`Test`][gradle-test-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`TestIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeTask.kt)

</tldr>

Runs tests using a custom IntelliJ Platform with the developed plugin installed.
It directly extends the [Test][gradle-test-task] Gradle task, which allows for an extensive configuration (system properties, memory management, etc.).

The [`TestIdeTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeTask.kt) is a class used only for handling custom `testIde` tasks.
No task named `testIde` is registered by default.

To register an additional customized test task, use [`intellijPlatformTesting.testIde`](tools_intellij_platform_gradle_plugin_testing_extension.md).

The classpath ordering and bundled-plugin selection described for [`test`](#test) also apply to these tasks.



## `testIdePerformance`
{#testIdePerformance}

<secondary-label ref="incubating"/>

<link-summary>Runs performance tests on the IntelliJ Platform with the developed plugin installed.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareTestIdePerformanceSandbox`](#prepareTestIdePerformanceSandbox)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RunnableIdeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RunnableIdeAware), [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware), [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware)

**Sources**: [`TestIdePerformanceTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdePerformanceTask.kt)

</tldr>

Runs performance tests on the IDE with the developed plugin installed.

This task runs against the IntelliJ Platform and plugins specified in project dependencies.
To register an additional customized task, use [`intellijPlatformTesting.testIdePerformance`](tools_intellij_platform_gradle_plugin_testing_extension.md).
The implementation is incubating and requires explicit values for `testDataDirectory`, `artifactsDirectory`, and `profilerName`; none has an active convention.
It runs one IDE process for each top-level <path>.ijperf</path> script and stores artifacts under a directory named after that script.
If a script's asserted timeout is exceeded, the task fails after collecting the failing results.


### `testDataDirectory`
{#testIdePerformance-testDataDirectory}

Path to the directory with test projects and `.ijperf` files.

{type="narrow"}
Type
: `DirectoryProperty`


### `artifactsDirectory`
{#testIdePerformance-artifactsDirectory}

Path to the directory where performance test artifacts, such as IDE logs, snapshots, and screenshots, are stored.
Create this directory before execution: the current property is annotated as an input directory.

{type="narrow"}
Type
: `DirectoryProperty`


### `profilerName`
{#testIdePerformance-profilerName}

Name of the profiler used during execution.

{type="narrow"}
Type
: `Property<ProfilerName>`

Required
: yes; for example, set `ProfilerName.ASYNC` or `ProfilerName.YOURKIT`



## `testUi`
{#testIdeUi}

<secondary-label ref="incubating"/>

<link-summary>Runs the IDE instance with the developed plugin and Starter framework for UI testing.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin) through the default `archiveFile`

**Extends**: [`Test`][gradle-test-task], [`TestableAware`](tools_intellij_platform_gradle_plugin_task_awares.md#TestableAware)

**Sources**: [`TestIdeUiTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/TestIdeUiTask.kt)

</tldr>

Runs the IDE instance with the developed plugin and Starter framework for UI testing.
Since `2.20.0`, the registered task name is `testUi`, not `testIdeUi`; the task type remains `TestIdeUiTask`.
It creates or reuses a source set matching the task name: <path>src/testUi</path> for the standard task and <path>src/extraUi</path> for a custom `extraUi` task.
Dependencies use that source set's configurations, such as `testUiImplementation` and `testUiRuntimeOnly`.

Starter and Driver dependencies are supplied automatically with the standard IDE-provided exclusions.
Compilation includes the IntelliJ Platform classpath; the host test JVM appends the selected IDE's classpath after the source set's runtime classpath.
JUnit Platform is enabled without filtering engines.
If Starter brings the Vintage engine, add JUnit 4 explicitly to the source set's runtime-only configuration because it is excluded from Starter's dependency graph.
Explicit `classpath` and `testClassesDirs` overrides remain supported.

The test JVM receives `path.to.build.plugin` pointing to `archiveFile`, and defaults `ide.test.version` to the selected product version unless explicitly set.
This task is not attached to `check`, `test`, or `build`; invoke it explicitly.

```bash
./gradlew testUi
```

> In `2.20.0`, combining UI testing with Checkstyle can cause task-ordering issues.
> See [#2287](https://github.com/JetBrains/intellij-platform-gradle-plugin/issues/2287) for details.
>
{style="note"}


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

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`buildPlugin`](#buildPlugin)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`RuntimeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RuntimeAware), [`PluginVerifierAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginVerifierAware), [`ProblemsAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProblemsAware), [`ProductReleasesServiceAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProductReleasesServiceAware)

**Sources**: [`VerifyPluginTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginTask.kt)

</tldr>

Runs the [IntelliJ Plugin Verifier](verifying_plugin_compatibility.md) CLI tool to check the binary compatibility with specified IDE builds.
At least one resolved target IDE is required.
The pass/fail decision reads per-plugin <path>verification-verdict.txt</path> reports rather than depending on console formatting, including TeamCity output.
If no readable verdict is produced, the task fails rather than reporting success.
Findings are also reported through Gradle's Problems API.

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
An unset input is treated as no source (`@SkipWhenEmpty`), not as successful verification.
A configured archive must exist; the task checks this before invoking the CLI.

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
: [`Property<Subsystems>`](tools_intellij_platform_gradle_plugin_types.md#Subsystems)

Default value
: [`intellijPlatform.pluginVerification.subsystemsToCheck`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-subsystemsToCheck)


### `teamCityOutputFormat`
{#verifyPlugin-teamCityOutputFormat}

Specifies whether to use the TeamCity-compatible output format.
If set to `true`, outputs in a format compatible with [TeamCity](https://www.jetbrains.com/teamcity/), directing the output to stdout.

The [`NOT_DYNAMIC`](tools_intellij_platform_gradle_plugin_types.md#FailureLevel) failure level can't be combined with `teamCityOutputFormat = true` (also when selected via [`FailureLevel.ALL`](tools_intellij_platform_gradle_plugin_types.md#FailureLevel)), as the dynamic-plugin eligibility status is neither persisted in the report files nor emitted as a TeamCity service message.
The [`verifyPlugin`](#verifyPlugin) task fails fast with a configuration error if both are set.

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
When `failureLevel` contains `NOT_DYNAMIC`, `MARKDOWN` is requested additionally because the verifier persists dynamic-plugin status and its reasons in that report.
For older CLI versions without that Markdown section, the task falls back to the plain console heading for dynamic status.
Use a CLI supporting the Markdown dynamic-status section for format-independent `NOT_DYNAMIC` detection.

{type="narrow"}
Type
: [`ListProperty<VerificationReportsFormats>`](tools_intellij_platform_gradle_plugin_types.md#VerificationReportsFormats)

Default value
: [`intellijPlatform.pluginVerification.verificationReportsFormats`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-verificationReportsFormats)


### `listIdes`
{#verifyPlugin-listIdes}

Lists the IDEs that would be used for verification without running the verification itself.
This skips the verifier invocation, not Gradle task dependencies: target IDE resolution and archive production can still occur.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`

Command-line option
: `--list-ides`


### `useBundledRuntime`
{#verifyPlugin-useBundledRuntime}

Uses each verified IDE's bundled JetBrains Runtime for resolving plugin classes.
Available since `2.19.0`.
When enabled, the CLI receives no `-runtime-dir`; the task's resolved runtime is supplied through `JAVA_HOME` only as a fallback for IDEs without a bundled runtime.
Set this to `false` to force the task's `runtimeDirectory` for every target IDE via `-runtime-dir`.
This controls verification class resolution, not the JVM running Gradle.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: [`intellijPlatform.pluginVerification.useBundledRuntime`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-useBundledRuntime) (`true`)


### `problemsReportFile`
{#verifyPlugin-problemsReportFile}

Default path of the Problems API HTML report file produced for verification failures.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/reports/problems/problems-report.html</path>


## `verifyPluginProjectConfiguration`
{#verifyPluginProjectConfiguration}

<link-summary>Validates the plugin project configuration.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform), [](tools_intellij_platform_gradle_plugin_plugins.md#module)

**Depends on**: [`patchPluginXml`](#patchPluginXml)

**Extends**: [`DefaultTask`][gradle-default-task], [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), [`KotlinMetadataAware`](tools_intellij_platform_gradle_plugin_task_awares.md#KotlinMetadataAware), [`RuntimeAware`](tools_intellij_platform_gradle_plugin_task_awares.md#RuntimeAware), [`PluginAware`](tools_intellij_platform_gradle_plugin_task_awares.md#PluginAware), [`ModuleAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ModuleAware), [`ProblemsAware`](tools_intellij_platform_gradle_plugin_task_awares.md#ProblemsAware)

**Sources**: [`VerifyPluginProjectConfigurationTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginProjectConfigurationTask.kt)

</tldr>

Validates the plugin project configuration:
- The effective plugin descriptor must be available for non-module projects, and `since-build` must not contain a wildcard.
- The [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) property can't be lower than the target IntelliJ Platform major version.
- The `until-build` property should be removed for IntelliJ Platform `2024.3+` (`243+`).
- The Java/Kotlin `sourceCompatibility` and `targetCompatibility` properties should be aligned with the Java versions required by [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) and the currently used IntelliJ Platform.
- The Kotlin API version should be aligned with the version required by [`patchPluginXml.sinceBuild`](#patchPluginXml-sinceBuild) and the currently used IntelliJ Platform.
- The Kotlin language version should not be lower than the version bundled with the target platform, and IntelliJ Platform 2025.1+ requires Kotlin 2.0.0 or later.
- The used IntelliJ Platform version should be `2023.3` (`233`) or higher.
- The dependency on the [](using_kotlin.md#kotlin-standard-library) should be excluded.
- The Kotlin Coroutines library should not be added explicitly to the project as it is already provided with the IntelliJ Platform.
- For non-module projects, the default <path>[rootProject]/.intellijPlatform</path> cache directory is checked for a `.intellijPlatform` entry in <path>.gitignore</path> only when both exist. Custom cache locations are not checked.
- The selected Java Runtime should be JetBrains Runtime (JBR).

These diagnostics are warnings, not verification failures.
Descriptor checks do not apply to module projects.
The task uses the same configuration-rule evaluator as [`intellijPlatformDoctor`](#intellijPlatformDoctor), but additionally checks the runtime vendor.

For more details regarding the Java version used in the specific IntelliJ SDK, see [](build_number_ranges.md).

See also:
- [](build_number_ranges.md)
- [](using_kotlin.md#kotlin-standard-library)
- [](using_kotlin.md#incremental-compilation)


### `rootDirectory`
{#verifyPluginProjectConfiguration-rootDirectory}

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
: `DirectoryProperty`

Default value
: [`intellijPlatform.caching.path`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-caching-path)


### `gitignoreFile`
{#verifyPluginProjectConfiguration-gitignoreFile}

Specifies the `.gitignore` file located in the <path>[rootDirectory]</path>, tracked for content changes.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[rootProject]/.gitignore</path> if it exists; otherwise unset


### `sourceCompatibility`
{#verifyPluginProjectConfiguration-sourceCompatibility}

Effective Java source level used for diagnostics, preferring `compileJava.options.release` over `compileJava.sourceCompatibility`.

{type="narrow"}
Type
: `Property<String>`

Default value
: `compileJava.options.release`, or `compileJava.sourceCompatibility` when no release is configured


### `targetCompatibility`
{#verifyPluginProjectConfiguration-targetCompatibility}

Effective Java target level used for diagnostics, preferring `compileJava.options.release` over `compileJava.targetCompatibility`.

{type="narrow"}
Type
: `Property<String>`

Default value
: `compileJava.options.release`, or `compileJava.targetCompatibility` when no release is configured


### `mutedMessages`
{#verifyPluginProjectConfiguration-mutedMessages}

List of message patterns to mute during verification.
Each pattern is matched against the message text using a case-sensitive `contains` check.
This filters the aggregate console warning; the structured Problems API findings are still reported.

{type="narrow"}
Type
: `ListProperty<String>`

Default value
: [`verifyPluginProjectConfigurationMutedMessages`](tools_intellij_platform_gradle_plugin_gradle_properties.md#verifyPluginProjectConfigurationMutedMessages)


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

The Kotlin compilation `apiVersion` supplied by [`KotlinMetadataAware`](tools_intellij_platform_gradle_plugin_task_awares.md#KotlinMetadataAware).

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured Kotlin compilation option, if available


### `kotlinLanguageVersion`
{#verifyPluginProjectConfiguration-kotlinLanguageVersion}

The Kotlin compilation `languageVersion`.

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured Kotlin compilation option, if available


### `kotlinVersion`
{#verifyPluginProjectConfiguration-kotlinVersion}

Specifies the version of Kotlin used in the project.

{type="narrow"}
Type
: `Property<String>`

Default value
: `kotlin.coreLibrariesVersion`


### `kotlinJvmTarget`
{#verifyPluginProjectConfiguration-kotlinJvmTarget}

The Kotlin compilation `jvmTarget`.

{type="narrow"}
Type
: `Property<String>`

Default value
: The configured Kotlin compilation option, if available


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

Indicates whether the resolved composed-JAR dependency graph includes a Kotlin Coroutines component, including transitively.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: An `org.jetbrains.kotlinx` component with a module name starting with `kotlinx-coroutines` is present


### `pluginXmlService`
{#verifyPluginProjectConfiguration-pluginXmlService}

Shared descriptor-parsing service supplied by the plugin, not a user configuration option.

{type="narrow"}
Type
: `Property<PluginXmlService>`


## `verifyPluginSignature`
{#verifyPluginSignature}

<link-summary>Validates the signature of the plugin archive file using the Marketplace ZIP Signer library.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Extends**: [`JavaExec`][gradle-javaexec-task], [`SigningAware`](tools_intellij_platform_gradle_plugin_task_awares.md#SigningAware)

**Sources**: [`VerifyPluginSignatureTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginSignatureTask.kt)

</tldr>

Validates the signature of the plugin archive file using the [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer) library.
The default input is a conventional file path, not a task-output dependency on `signPlugin`.
Run signing first, or wire the provider explicitly, especially when customizing archive names or locations:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks.verifyPluginSignature {
    inputArchiveFile.set(tasks.signPlugin.flatMap { it.signedArchiveFile })
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks.named('verifyPluginSignature') {
  inputArchiveFile.set(tasks.named('signPlugin').flatMap { it.signedArchiveFile })
}
```

</tab>
</tabs>

See also:
- [](plugin_signing.md)
- [Marketplace ZIP Signer](https://github.com/JetBrains/marketplace-zip-signer)


### `inputArchiveFile`
{#verifyPluginSignature-inputArchiveFile}

Specifies the signed ZIP archive file to verify.
This parameter corresponds to the `in` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: <path>[buildDirectory]/distributions/[intellijPlatform.projectName]-[intellijPlatform.pluginConfiguration.version]-signed.zip</path>


### `certificateChain`
{#verifyPluginSignature-certificateChain}

Specifies a string containing X509 certificates.
This parameter corresponds to the `cert` CLI option.

Takes precedence over the [`certificateChainFile`](#verifyPluginSignature-certificateChainFile) property.
The content is written unchanged to a temporary PEM file and passed with `-cert`; unlike signing, this task does not decode Base64 content.

{type="narrow"}
Type
: `Property<String>`

Default value
: [`intellijPlatform.signing.certificateChain`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-certificateChain)


### `certificateChainFile`
{#verifyPluginSignature-certificateChainFile}

Specifies the path to the file containing X509 certificates.
The file path is passed with the `cert` CLI option.

{type="narrow"}
Type
: `RegularFileProperty`

Default value
: [`intellijPlatform.signing.certificateChainFile`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-signing-certificateChainFile)



## `verifyPluginStructure`
{#verifyPluginStructure}

<link-summary>Validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin archive structure.</link-summary>

<tldr>

**Available in**: [](tools_intellij_platform_gradle_plugin_plugins.md#platform)

**Depends on**: [`prepareSandbox`](#prepareSandbox)

**Extends**: [`DefaultTask`][gradle-default-task]

**Sources**: [`VerifyPluginStructureTask`](%gh-ijpgp%/src/main/kotlin/org/jetbrains/intellij/platform/gradle/tasks/VerifyPluginStructureTask.kt)

</tldr>

Validates completeness and contents of <path>plugin.xml</path> descriptors as well as plugin archive structure.

See also:
- [](plugin_configuration_file.md)


### `ignoreFailures`
{#verifyPluginStructure-ignoreFailures}

When `true`, prevents verification findings from failing the build.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `ignoreUnacceptableWarnings`
{#verifyPluginStructure-ignoreUnacceptableWarnings}

When `true`, tolerates unacceptable warnings; when `false`, they fail the build unless `ignoreFailures` is enabled.

{type="narrow"}
Type
: `Property<Boolean>`

Default value
: `false`


### `ignoreWarnings`
{#verifyPluginStructure-ignoreWarnings}

When `true`, tolerates ordinary warnings; when `false`, they fail the build unless `ignoreFailures` is enabled.

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
: [`prepareSandbox.pluginDirectory`](#prepareSandbox-pluginDirectory)



[gradle-default-task]: https://docs.gradle.org/current/dsl/org.gradle.api.DefaultTask.html
[gradle-delete-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Delete.html
[gradle-jar-task]: https://docs.gradle.org/current/dsl/org.gradle.jvm.tasks.Jar.html
[gradle-javaexec-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.JavaExec.html
[gradle-sync-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.Sync.html
[gradle-test-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.testing.Test.html
[gradle-zip-task]: https://docs.gradle.org/current/dsl/org.gradle.api.tasks.bundling.Zip.html
