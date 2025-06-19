<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Verifying Plugin Compatibility

<web-summary>
Verifying plugin binary compatibility using Plugin Verifier and inspections to ensure the plugin works correctly with the latest IDE versions.
</web-summary>

<link-summary>Tooling for ensuring compatibility.</link-summary>

See [](api_changes_list.md) for known breaking changes.

For API annotated with [`@ApiStatus.Internal`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), see [](api_internal.md) for more details and replacements.

See also [](build_number_ranges.md#multipleIDEVersions).

## Plugin Verifier

<tldr id="tldr">

**Current Release**: %plugin-verifier-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/intellij-plugin-verifier/releases), [Issue Tracker](https://youtrack.jetbrains.com/issues/MP?q=%23%7BPlugin%20Verifier%7D%20)

**JetBrains Platform Forum**: [Plugin Verifier](https://platform.jetbrains.com/c/intellij-platform/plugin-verifier/7) category

</tldr>

Plugin Verifier checks the binary compatibility between IntelliJ-based IDE builds and IntelliJ Platform plugins.

### Using with Gradle

Integration in [Gradle build](creating_plugin_project.md) is available out-of-the box via a dedicated Gradle task.

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

Use [`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPlugin) task.

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)">

Use [`runPluginVerifier`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) task.

</tab>
</tabs>

Integration with Continuous Integration (CI) environments is as simple as running the corresponding Gradle task as another quality check step.
See the IntelliJ Platform Plugin Template [GitHub workflow configuration file](https://github.com/JetBrains/intellij-platform-plugin-template/blob/main/.github/workflows/build.yml) as a reference.

#### Navigation in the IDE
<primary-label ref="2023.3"/>

Reported places are highlighted and linked to the plugin's source code in the <control>Gradle</control> tool window output.

### JetBrains Marketplace

Compatibility with newer IDEs can easily be verified for plugins hosted on the [JetBrains Marketplace](https://plugins.jetbrains.com) using the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/).

### Other Usages

If a plugin is hosted on GitHub and the project is _not_ using Gradle, consider using third-party GitHub Actions [IntelliJ Platform Plugin Verifier](https://github.com/marketplace/actions/intellij-platform-plugin-verifier) or [IntelliJ Plugin Verifier](https://github.com/marketplace/actions/intellij-plugin-verifier).

In all other cases, [intellij-plugin-verifier](https://github.com/JetBrains/intellij-plugin-verifier) can be used standalone as well.

## IDE Support

The status of an API is marked using various annotations defined in [`ApiStatus`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), please see their doc for more details.
Use highlighting available via dedicated [IDE inspections](https://www.jetbrains.com/help/idea/code-inspection.html) as noted below to prevent problems as early as possible.

### Unstable API

- `@ApiStatus.Experimental` is considered unstable and may break or be removed.
- `@ApiStatus.Internal` must not be used by plugins, see [](api_internal.md) for more details and replacements.
- `@ApiStatus.ScheduledForRemoval` denotes an API that will be removed in a future version.

Inspection: <control>JVM languages | Unstable API Usage</control> and <control>JVM languages | Unstable type is used in signature</control>

### Obsolete API
<primary-label ref="2023.1"/>

API annotated with `@ApiStatus.Obsolete` has been replaced with a better alternative and must not be used for new code.

Inspection: <control>Plugin DevKit | Code | Usages of ApiStatus.@Obsolete</control>

### Non-Extendable API

API annotated with `@ApiStatus.NonExtendable` must not be extended, implemented, or overridden.

Inspection: <control>JVM languages | Class, interface, or method should not be extended</control>

### Override-Only API

API annotated with `@ApiStatus.OverrideOnly` must not be called directly by the client.

Inspection: <control>JVM languages | Method can only be overridden</control>

### plugin.xml

Usage of [Extension Points](plugin_extensions.md) which are deprecated or annotated with `@ApiStatus.Experimental` or `@ApiStatus.Internal` is also highlighted in <path>[plugin.xml](plugin_configuration_file.md)</path> files.

Inspection: <control>Plugin DevKit | Plugin descriptor | Plugin.xml validity</control>

### API Compatibility

A plugin might specify a [compatibility range](build_number_ranges.md) including releases where some API is not available.
Under the hood, it uses an artifact containing generated data via `@ApiStatus.AvailableSince`, which is automatically attached to the project.

Inspection: <control>Plugin DevKit | Code | Usage of IntelliJ API not available in older IDEs</control>

> If values are not specified directly in [<path>plugin.xml</path>](plugin_configuration_file.md) (e.g., when providing values via `patchPluginXml` Gradle task (Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml), [1.x](tools_gradle_intellij_plugin.md#tasks-patchpluginxml))), they must be set explicitly in the inspection's settings.
