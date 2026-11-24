<!-- Copyright 2000-2026 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Recipes

<link-summary>Recipes for solving particular tasks with IntelliJ Platform Gradle Plugin</link-summary>

## Diagnose Gradle configuration
{#diagnose-gradle-configuration}

<primary-label ref="gradle-plugin-2.20.0"/>

The [JSON reporting tasks](tools_intellij_platform_gradle_plugin_tasks.md#json-reports) provide project metadata and diagnostics without compiling or launching an IDE.
Start with [`intellijPlatformInfo`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformInfo), which reports the declared setup and available commands without resolving an IDE by default.

For automation, use a fresh output directory for each invocation.
The following examples use a POSIX shell and `jq` to read the JSON files:

```bash
mkdir -p build/reports/intellij-platform || exit 1
report_dir=$(mktemp -d "$PWD/build/reports/intellij-platform/info.XXXXXX") || exit 1
./gradlew intellijPlatformInfo --output="$report_dir/info.json" &&
  jq '.data' "$report_dir/info.json"
```

In a multi-project build, qualify the task, for example `:plugin:intellijPlatformInfo`.
An absolute `--output` path avoids ambiguity because relative output paths are resolved against the task's project.

If the declared settings do not explain the problem, run [`intellijPlatformDoctor`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDoctor).
Doctor resolves the target IDE and patches the plugin descriptor, so it can download the IDE and dependencies.
It reports suggested fixes but does not apply them.

```bash
mkdir -p build/reports/intellij-platform || exit 1
report_dir=$(mktemp -d "$PWD/build/reports/intellij-platform/doctor.XXXXXX") || exit 1
gradle_status=0
./gradlew intellijPlatformDoctor --fail-on=error \
  --output="$report_dir/doctor.json" || gradle_status=$?
if test -f "$report_dir/doctor.json"; then
  jq '{status, issues: [.issues[] | select(.suppressed == false)], unavailable}' \
    "$report_dir/doctor.json" || exit 1
fi
exit "$gradle_status"
```

Doctor writes the report before failing on findings selected by `--fail-on`.
Configuration errors, invalid options, or an unwritable destination can prevent a report from being written.
Keep the Gradle exit status, check whether the new file exists, and inspect its `status`, `issues`, and `unavailable` fields; the presence of a report alone does not mean the command succeeded.
Do not read a previous run's default report after a failed invocation or parse Gradle's console output as JSON.

For further investigation:

- Use [`intellijPlatformDiscover`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformDiscover) to find bundled plugin or module IDs, or query candidate IDE releases.
- Use [`intellijPlatformSdk`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformSdk) to locate resolved SDK archives, sources, and classpath owners.
- Use [`intellijPlatformCompatibilityMatrix`](tools_intellij_platform_gradle_plugin_tasks.md#intellijPlatformCompatibilityMatrix) to inspect declared compatibility and configured verification targets, not proof of successful verification.

Review reports before sharing them: SDK and project paths can identify the local machine.

## Run UI tests in CI
{#run-ui-tests-in-ci}

<primary-label ref="gradle-plugin-2.20.0"/>

The main plugin provides the Starter-based [`testUi`](tools_intellij_platform_gradle_plugin_tasks.md#testIdeUi) task and a separate `testUi` source set.
Place UI tests in <path>src/testUi/java</path> or, with the Kotlin JVM plugin, <path>src/testUi/kotlin</path>, and resources in <path>src/testUi/resources</path>.
The task uses JUnit Platform and adds the Starter test-framework dependencies automatically.
See [](integration_tests_intro.md) for writing Starter tests.

Keep UI execution in a separate CI job with the display and operating-system dependencies required by the tests.
The task is not part of `check`, so invoke it explicitly:

```bash
./gradlew testUi
```

To run a smaller suite, use Gradle's test filter:

```bash
./gradlew testUi --tests 'com.example.ui.SmokeTest'
```

Collect <path>build/reports/tests/testUi/</path> and <path>build/test-results/testUi/</path> as CI artifacts, including when tests fail.
Keep the usual `check` and `verifyPlugin` jobs separate from this IDE-based execution.
The verifier's [automatic IDE selection](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginVerification-ides) remains useful for tracking recommended targets, but the selected matrix can change as the release catalog changes.

For additional IDE targets, register named entries in [`intellijPlatformTesting.testIdeUi`](tools_intellij_platform_gradle_plugin_testing_extension.md#intellijPlatformTesting).
The container name remains `testIdeUi`; it is not the name of the built-in execution task.

## Run a custom task with a customized sandbox location

To run a custom task with a different sandbox directory, configure its [`prepareSandboxTask`](tools_intellij_platform_gradle_plugin_testing_extension.md#preparesandboxtask) sandbox producer.
To relocate all sandboxes instead, set [`intellijPlatform.sandboxContainer`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-sandboxContainer).

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runWithCustomSandbox by intellijPlatformTesting.runIde.registering {
  // ...

  prepareSandboxTask {
    sandboxDirectory = project.layout.buildDirectory.dir("custom-sandbox")
    sandboxSuffix = ""
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runWithCustomSandbox {
    // ...

    prepareSandboxTask {
      sandboxDirectory = project.layout.buildDirectory.dir('custom-sandbox')
      sandboxSuffix = ''
    }
  }
}
```

</tab>
</tabs>


This will result in the following sandbox structure:

```text
build/
├── custom-sandbox
│   ├── config
│   ├── log
│   ├── plugins
│   └── system
...
```

## Access IntelliJ Platform from any Gradle task

With [](tools_intellij_platform_gradle_plugin_task_awares.md) it is possible to enhance any Gradle task with features provided with the IntelliJ Platform Gradle Plugin.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.aware.IntelliJPlatformVersionAware

abstract class MyTask : DefaultTask(), IntelliJPlatformVersionAware

val myTask by tasks.registering(MyTask::class) {
  doLast {
    println("platformPath = \n${platformPath}")
    println("productInfo.buildNumber = ${productInfo.buildNumber}")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.aware.IntelliJPlatformVersionAware

abstract class MyTask extends DefaultTask implements IntelliJPlatformVersionAware {}

tasks.register('myTask', MyTask) {
  doLast {
    println("platformPath = \n${platformPath}")
    println("productInfo.buildNumber = ${productInfo.buildNumber}")
  }
}
```

</tab>
</tabs>


As soon as the registered task inherits from a `*Aware` interface, such as [`IntelliJPlatformVersionAware`](tools_intellij_platform_gradle_plugin_task_awares.md#IntelliJPlatformVersionAware), all the related information will be injected during the configuration phase.


## Bundle additional files with the plugin

Additional files can be bundled by adding them to the plugin directory when preparing the sandbox:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  prepareSandbox {
    from(layout.projectDirectory.dir("extraFiles")) {
      into(pluginName.map { "$it/extra" })
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.PrepareSandboxTask

tasks.named('prepareSandbox', PrepareSandboxTask) { task ->
  from(layout.projectDirectory.dir('extraFiles')) {
    into(task.pluginName.map { "$it/extra" })
  }
}
```

</tab>
</tabs>


To apply that to the custom task, use the [`prepareSandboxTask`](tools_intellij_platform_gradle_plugin_testing_extension.md#preparesandboxtask) reference:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runWithCustomSandbox by intellijPlatformTesting.runIde.registering {
  // ...

  prepareSandboxTask {
    from(...) {
      into(...)
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runWithCustomSandbox {
    // ...

    prepareSandboxTask {
      from(...)
      into(...)
    }
  }
}
```

</tab>
</tabs>


It is possible to apply that to all [`PrepareSandboxTask`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) with:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.PrepareSandboxTask

tasks {
  withType<PrepareSandboxTask>().configureEach {
    from(...) {
      into(...)
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.PrepareSandboxTask

tasks.withType(PrepareSandboxTask).configureEach {
  from(...) {
    into(...)
  }
}
```

</tab>
</tabs>


## Adjust IDE configuration by altering sandbox files

When starting the IDE locally with [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task or running tests, the IDE creates and updates configuration files within the sandbox directory.
Those files may be related to trusted paths, macros, recent projects, custom plugins, color schemes, etc.
By default, sandboxes are stored under <path>.intellijPlatform/sandbox/</path>, outside the build directory, and survive the `clean` task.
Use [`cleanSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#cleanSandbox) to remove them.

To recreate configuration files after cleaning a sandbox, customize the [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) task.
The following example marks the project located in the <path>\$USER_HOME$/IdeaProjects/GradleProject</path> directory as trusted, like when the user clicks on the _Trust Project_ dialog when opening it for the first time.

```kotlin
import org.jetbrains.intellij.platform.gradle.tasks.PrepareSandboxTask

tasks.withType<PrepareSandboxTask>().configureEach {
  doLast {
    val trustedPathsFile = sandboxConfigDirectory.file("options/trusted-paths.xml").get().asFile
    trustedPathsFile.parentFile.mkdirs()

    trustedPathsFile.writeText(
      """
            <application>
              <component name="Trusted.Paths">
                <option name="TRUSTED_PROJECT_PATHS">
                  <map>
                    <entry key="${'$'}USER_HOME$/IdeaProjects/GradleProject" value="true" />
                  </map>
                </option>
              </component>
            </application>
            """.trimIndent()
    )
  }
}
```


## ProGuard configuration

To configure [ProGuard](https://github.com/Guardsquare/proguard), intercept the [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) task and replace the plugin Jar archive produced by the [`composedJar`](tools_intellij_platform_gradle_plugin_tasks.md#composedJar) task with the obfuscated/minified file.

First, define the ProGuard Jar archive location and store it inside the <path>build/libs/</path> directory.
Pass the output ProGuard location to the `outjars()` helper and use it as a new sandbox input for [`prepareSandbox.pluginJar`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox-pluginJar).

Finally, pass the [`composedJar.archiveFile`](tools_intellij_platform_gradle_plugin_tasks.md#composedJar-archiveFile) to the `injars()` helper.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
import proguard.gradle.ProGuardTask

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("com.guardsquare:proguard-gradle:7.5.0")
  }
}

tasks {
  val proguardJar = layout.buildDirectory.file("libs/$name-$version-proguard.jar")

  val proguard by registering(ProGuardTask::class) {
    injars(composedJar.flatMap { it.archiveFile })
    outjars(proguardJar)

    // ...
  }

  prepareSandbox {
    pluginJar = proguardJar
    dependsOn(proguard)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import proguard.gradle.ProGuardTask

buildscript {
  repositories {
    mavenCentral()
  }
  dependencies {
    classpath("com.guardsquare:proguard-gradle:7.5.0")
  }
}

def proguardJar = layout.buildDirectory.file("libs/$name-$version-proguard.jar")

tasks.register('proguard', ProGuardTask) {
  it.injars(composedJar.archiveFile)
  outjars(proguardJar)

  // ...
}

prepareSandbox {
  pluginJar = proguardJar
  dependsOn(proguard)
}
```

</tab>
</tabs>


## Run the IDE with a default argument provided

When running the IDE from the command line, you can pass an argument, like a path to the project or file, to open it automatically.
This is also possible when using the [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) task:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  runIde {
    argumentProviders += CommandLineArgumentProvider {
      listOf("/path/to/the/project")
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
runIde {
  argumentProviders.add({
    ['/path/to/the/project']
  } as CommandLineArgumentProvider)
}
```

</tab>
</tabs>


## Run the IDE in LightEdit mode

Pass the IntelliJ IDEA `-e` or `--edit` argument to [`runIde`](tools_intellij_platform_gradle_plugin_tasks.md#runIde) to open a file in LightEdit mode:

```bash
./gradlew runIde --args='-e /absolute/path/to/file'
```

Omit the file path to open an empty LightEdit window. To keep this setup as a separate reusable task, register a custom `runIde` task:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
val runIdeLightEdit by intellijPlatformTesting.runIde.registering {
  task {
    args("-e", project.layout.projectDirectory.file("README.md").asFile.absolutePath)
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatformTesting.runIde {
  runIdeLightEdit {
    task {
      args '-e', project.layout.projectDirectory.file('README.md').asFile.absolutePath
    }
  }
}
```

</tab>
</tabs>


## Resolve plugin from JetBrains Marketplace in the latest compatible version

Use the built-in [`compatiblePlugin()` or `compatiblePlugins()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugins) helpers to select a JetBrains Marketplace plugin version compatible with the target IntelliJ Platform.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    intellijIdea("%ijPlatform%")
    compatiblePlugin("org.intellij.scala")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    intellijIdea('%ijPlatform%')
    compatiblePlugin('org.intellij.scala')
  }
}
```

</tab>
</tabs>

The selected version can change as compatible updates are published.
For reproducible builds, resolve a compatible version and then pin it explicitly with `plugin(id, version)`.

## Exclude conflicting libraries
{#exclude-conflicting-libraries}

<primary-label ref="gradle-plugin-2.19.0"/>

A plugin dependency's configuration block can exclude libraries bundled by that plugin.
Use this when a dependency ships a library that conflicts with the version required by the project:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    plugin("com.example.dependency", "1.0.0") {
      excludeBundledLibrary("legacy-*.jar")
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    plugin('com.example.dependency', '1.0.0', '') {
      excludeBundledLibrary('legacy-*.jar')
    }
  }
}
```

</tab>
</tabs>

Replace the example plugin ID, version, and pattern with the dependency being configured.
Patterns are relative to its <path>lib/</path> and <path>lib/modules/</path> directories.
They affect both classpaths and sandbox installations, and exclusions for the same plugin ID are combined across the build.
The Groovy ID/version overload needs the explicit empty channel argument before the closure.
See [Bundled Library Exclusions](tools_intellij_platform_gradle_plugin_dependencies_extension.md#plugin-library-exclusions) for the full behavior.

This differs from excluding a Maven dependency from the project's own sandbox runtime classpath.
For that case, configure `intellijPlatformSandboxRuntimeClasspath`:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
configurations.named("intellijPlatformSandboxRuntimeClasspath") {
  exclude(group = "com.example", module = "library-provided-by-ide")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
configurations.named('intellijPlatformSandboxRuntimeClasspath') {
  exclude group: 'com.example', module: 'library-provided-by-ide'
}
```

</tab>
</tabs>

The test sandbox runtime configuration inherits these exclusions.
See [`prepareSandbox`](tools_intellij_platform_gradle_plugin_tasks.md#prepareSandbox) for sandbox runtime configuration details.
Only exclude a library when the target IDE or another dependency supplies the required API; exclusions do not check binary compatibility.

## Provide a subscription key to sandbox IDEs
{#provide-a-subscription-key}

<primary-label ref="gradle-plugin-2.19.0"/>

[`intellijPlatform.subscriptionKey`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-subscriptionKey) supplies a key file to sandbox preparation tasks:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  subscriptionKey = layout.projectDirectory.file("local/subscription.key")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  subscriptionKey = layout.projectDirectory.file('local/subscription.key')
}
```

</tab>
</tabs>

Keep the key file out of version control.
For CI, mount it as a secret file and supply its path through the [`subscriptionKey` Gradle property](tools_intellij_platform_gradle_plugin_gradle_properties.md#subscriptionKey), rather than hard-coding it in the build script:

```bash
./gradlew prepareTestSandbox \
  -Porg.jetbrains.intellij.platform.subscriptionKey=/run/secrets/subscription.key
```

The property provides the default when no extension or task override is set; omit the explicit file assignment above when using it.
Sandbox preparation copies the key to <path>idea.key</path> in the corresponding sandbox configuration directory.
Treat prepared sandboxes as sensitive too, and do not upload the key with CI artifacts or include it in the plugin distribution.

## Build and run native variants
{#build-and-run-native-variants}

<primary-label ref="gradle-plugin-2.19.0"/>

[`nativeVariants`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-nativeVariants) can overlay OS- and architecture-specific files onto a shared plugin.
This feature requires IntelliJ Platform `2026.1` or later and `since-build` of at least `261`.
Keep common files in the normal plugin layout and configure each target's additional files separately:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  pluginConfiguration.ideaVersion.sinceBuild = "261"
  nativeVariants {
    enabled = true
    linux.x86_64.from(file("native/linux-x86_64"))
    linux.arm64.from(file("native/linux-arm64"))
    mac.x86_64.from(file("native/mac-x86_64"))
    mac.arm64.from(file("native/mac-arm64"))
    windows.x86_64.from(file("native/windows-x86_64"))
    windows.arm64.from(file("native/windows-arm64"))
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  pluginConfiguration.ideaVersion.sinceBuild = '261'
  nativeVariants {
    enabled = true
    linux.x86_64.from(file('native/linux-x86_64'))
    linux.arm64.from(file('native/linux-arm64'))
    mac.x86_64.from(file('native/mac-x86_64'))
    mac.arm64.from(file('native/mac-arm64'))
    windows.x86_64.from(file('native/windows-x86_64'))
    windows.arm64.from(file('native/windows-arm64'))
  }
}
```

</tab>
</tabs>

Paths inside each source directory are relative to the plugin root.
For example, <path>native/linux-arm64/bin/helper</path> becomes <path>bin/helper</path> in that variant.
Supply the binaries for every target being distributed; the plugin packages them but does not cross-compile native code.

For local development, `runIde` and custom `intellijPlatformTesting.runIde` entries select the current host's overlay automatically.
The shared base sandbox and `buildPlugin` archive remain platform-independent.
Custom test entries do not select a native overlay automatically.

Build the six platform archives explicitly with:

```bash
./gradlew buildPluginVariants
```

The [`buildPluginVariants`](tools_intellij_platform_gradle_plugin_tasks.md#buildPluginVariants) task writes classified ZIP files to <path>build/distributions/</path>.
Each variant has a rewritten descriptor with an OS/architecture-specific version and dependencies.
Inspect the matching archive when checking a release; the base `buildPlugin` archive does not contain these overlays.

## Try the latest IDE installer
{#try-the-latest-ide-installer}

<primary-label ref="gradle-plugin-2.19.0"/>

The `"latest"` selector resolves the newest available installer for the selected product:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
dependencies {
  intellijPlatform {
    intellijIdea("latest") {
      useInstaller = true
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
dependencies {
  intellijPlatform {
    intellijIdea('latest') {
      useInstaller = true
    }
  }
}
```

</tab>
</tabs>

This is useful for a separate forward-compatibility check, not a reproducible baseline.
Selection spans release channels, so the newest build can be an EAP.
Maven distributions do not support `"latest"`.
Pin a concrete version for the normal development target, and see [Target Versions](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-versions-latest) for offline behavior.


## Generate a lexer and parser with Grammar-Kit

<primary-label ref="gradle-plugin-2.12.0"/>

The [](tools_intellij_platform_gradle_plugin_plugins.md#grammarkit) sub-plugin adds the [`generateLexer`](tools_intellij_platform_gradle_plugin_tasks.md#generateLexer) and [`generateParser`](tools_intellij_platform_gradle_plugin_tasks.md#generateParser) tasks for generating a [custom language](custom_language_support.md) lexer and parser from [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) sources.
It is not applied by the main plugin, so apply it explicitly next to `org.jetbrains.intellij.platform`:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

<path>build.gradle.kts</path>
```kotlin
plugins {
  id("org.jetbrains.intellij.platform") version "%intellij-platform-gradle-plugin-version%"
  id("org.jetbrains.intellij.platform.grammarkit") version "%intellij-platform-gradle-plugin-version%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

<path>build.gradle</path>
```groovy
plugins {
  id 'org.jetbrains.intellij.platform' version '%intellij-platform-gradle-plugin-version%'
  id 'org.jetbrains.intellij.platform.grammarkit' version '%intellij-platform-gradle-plugin-version%'
}
```

</tab>
</tabs>


Point the tasks at the grammar sources and add their output to the `main` source set so compilation triggers generation:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  generateLexer {
    sourceFile = file("src/main/grammar/Simple.flex")
  }

  generateParser {
    sourceFile = file("src/main/grammar/Simple.bnf")
  }
}

sourceSets {
  main {
    java {
      srcDir(tasks.generateLexer)
      srcDir(tasks.generateParser)
    }
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
import org.jetbrains.intellij.platform.gradle.tasks.GenerateLexerTask
import org.jetbrains.intellij.platform.gradle.tasks.GenerateParserTask

tasks.named('generateLexer', GenerateLexerTask) {
  sourceFile = file('src/main/grammar/Simple.flex')
}

tasks.named('generateParser', GenerateParserTask) {
  sourceFile = file('src/main/grammar/Simple.bnf')
}

sourceSets {
  main {
    java {
      srcDir(tasks.named('generateLexer'))
      srcDir(tasks.named('generateParser'))
    }
  }
}
```

</tab>
</tabs>


By default each task writes to its own directory below <path>[buildDirectory]/generated/sources/</path>, owns that directory, and declares it as a task output.
Passing the task to `srcDir()` registers the generated sources and makes the compilation task depend on generation, so no explicit `dependsOn` is needed.

The Grammar-Kit and JFlex versions are provided automatically.
Override them with the [`grammarKit()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#grammar-and-parser-generation) and [`jflex()`](tools_intellij_platform_gradle_plugin_dependencies_extension.md#grammar-and-parser-generation) dependency helpers:

```kotlin
dependencies {
  intellijPlatform {
    grammarKit("2023.3.4")
    jflex("1.10.17")
  }
}
```

To generate into a directory shared with handwritten code (for example <path>src/main/gen</path>), set [`targetRootOutputDir`](tools_intellij_platform_gradle_plugin_tasks.md#generateParser-targetRootOutputDir) together with the explicit output paths so that only the generated files are purged:

```kotlin
tasks {
  generateParser {
    sourceFile = file("src/main/grammar/Simple.bnf")
    targetRootOutputDir = file("src/main/gen")
    pathToParser = "org/intellij/sdk/language/parser/SimpleParser.java"
    pathToPsiRoot = "org/intellij/sdk/language/psi"
    purgeOldFiles = true
  }
}
```

> For projects built with the IntelliJ Platform Gradle Plugin, this replaces the standalone [](tools_gradle_grammar_kit_plugin.md) (`org.jetbrains.grammarkit`).
> Do not apply both plugins in the same project.
>
{style="note"}
