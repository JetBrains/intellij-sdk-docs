[//]: # (title: Verifying Plugin Compatibility)

<excerpt>Tooling for ensuring compatibility</excerpt>

Please see [](api_changes_list.md) for known breaking changes.
For API annotated with `ApiStatus.@Internal`, see [](api_internal.md) for more details and replacements.

## Plugin Verifier

Compatibility with newer IDEs can easily be verified for plugins hosted on the [JetBrains Marketplace](https://plugins.jetbrains.com) using the built-in [Plugin Verifier](https://blog.jetbrains.com/platform/2018/07/plugins-repository-now-integrates-with-the-plugin-verification-tool/).

Integration in [Gradle build](configuring_plugin_project.md) is available using the [`runPluginVerifier`](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) task, please see [Gradle IntelliJ Plugin - Plugin Verifier](tools_gradle_intellij_plugin.md#tasks-runpluginverifier) for details.

You can easily integrate it within your CI by running this task as another quality check step.
Check the IntelliJ Platform Plugin Template [GitHub workflow configuration file](https://github.com/JetBrains/intellij-platform-plugin-template/blob/main/.github/workflows/build.yml) as sample.

If your plugin is hosted on GitHub and you are _not_ using Gradle, consider using third-party GitHub Actions [IntelliJ Platform Plugin Verifier](https://github.com/marketplace/actions/intellij-platform-plugin-verifier) or [IntelliJ Plugin Verifier](https://github.com/marketplace/actions/intellij-plugin-verifier).

For non-public plugins [intellij-plugin-verifier](https://github.com/JetBrains/intellij-plugin-verifier) can be used standalone as well.

## IDE Support

Consider using the following [IDE inspections](https://www.jetbrains.com/help/idea/code-inspection.html) to get additional alerts about code that uses unstable API features:

- <control>JVM languages | Unstable API Usage</control>
- <control>JVM languages | Unstable type is used in signature</control>

Usage of [Extension Points](plugin_extensions.md) which are deprecated or annotated with [`org.jetbrains.annotations.ApiStatus`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Experimental`/`@Internal` is highlighted in <path>[plugin.xml](plugin_configuration_file.md)</path> files.

