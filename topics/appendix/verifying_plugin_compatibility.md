# Verifying Plugin Compatibility

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Tooling for ensuring compatibility.</link-summary>

Please see [](api_changes_list.md) for known breaking changes.

For API annotated with `ApiStatus.Internal`, see [](api_internal.md) for more details and replacements.

## Plugin Verifier

Compatibility with newer IDEs can easily be verified for plugins hosted on the [JetBrains Marketplace](https://plugins.jetbrains.com) using the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/).

Integration in [Gradle build](configuring_plugin_project.md) is available using the [`runPluginVerifier`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) task, please see [Gradle IntelliJ Plugin - Plugin Verifier](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) for details.

You can easily integrate it in Continuous Integration (CI) environments by running this task as another quality check step.
Check the IntelliJ Platform Plugin Template [GitHub workflow configuration file](https://github.com/JetBrains/intellij-platform-plugin-template/blob/main/.github/workflows/build.yml) as sample.

If your plugin is hosted on GitHub and you are _not_ using Gradle, consider using third-party GitHub Actions [IntelliJ Platform Plugin Verifier](https://github.com/marketplace/actions/intellij-platform-plugin-verifier) or [IntelliJ Plugin Verifier](https://github.com/marketplace/actions/intellij-plugin-verifier).

In other cases, [intellij-plugin-verifier](https://github.com/JetBrains/intellij-plugin-verifier) can be used standalone as well.

## IDE Support

The status of an API is marked using various annotations defined in [`ApiStatus`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), please see their javadoc for more details.
Use highlighting available via dedicated [IDE inspections](https://www.jetbrains.com/help/idea/code-inspection.html) as noted below to prevent problems as early as possible.

### Unstable API

- `ApiStatus.@Experimental` is considered unstable and may break or be removed.
- `ApiStatus.@Internal` should not be used by plugins, see [](api_internal.md) for more details and replacements.
- `ApiStatus.@ScheduledForRemoval` denotes API that will be removed in a future version.

Inspection: <control>JVM languages | Unstable API Usage</control> and <control>JVM languages | Unstable type is used in signature</control>

### Obsolete API

API annotated with `ApiStatus.@Obsolete` has been replaced with a better alternative and must not be used for new code.

Inspection: <control>Plugin DevKit | Code | Usages of ApiStatus.@Obsolete</control> (2023.1)

### Non-Extendable API

API annotated with `ApiStatus.@NonExtendable` must not be extended, implemented or overridden.

Inspection: <control>JVM languages | Class, interface, or method should not be extended</control>

### Override-Only API

API annotated with `ApiStatus.@OverrideOnly` must not be called directly by the client.

Inspection: <control>JVM languages | Method can only be overridden</control>

### plugin.xml

Usage of [Extension Points](plugin_extensions.md) which are deprecated or annotated with `ApiStatus.@Experimental` or `ApiStatus.@Internal` is also highlighted in <path>[plugin.xml](plugin_configuration_file.md)</path> files.

Inspection: <control>Plugin DevKit | Plugin descriptor | Plugin.xml validity</control>

### API Compatibility

A plugin might specify a [compatibility range](build_number_ranges.md) including releases where some API is not available.
Under the hood, it uses an artifact containing generated data via `ApiStatus.@AvailableSince`, which is automatically attached to the project.

NOTE: If values are not specified directly in [<path>plugin.xml</path>](plugin_configuration_file.md) (e.g., when providing values via [](tools_gradle_intellij_plugin.md#tasks-patchpluginxml) Gradle task), they must be set explicitly in the inspection's settings.

Inspection: <control>Plugin DevKit | Code | Usage of IntelliJ API not available in older IDEs</control>
