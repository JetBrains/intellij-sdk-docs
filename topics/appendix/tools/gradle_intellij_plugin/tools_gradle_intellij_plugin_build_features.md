[//]: # (title: Gradle IntelliJ Plugin – Build Features)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

With the Gradle IntelliJ Plugin releases, new features are introduced that require additional research, collecting more feedback from developers, or should be enabled or disabled under particular conditions.
Build Features are an implementation of the feature flags concept and let you control some behaviors of the Gradle IntelliJ Plugin.
To enable or disable a particular feature, add the Project property to the <path>gradle.properties</path> file, like:

```properties
org.jetbrains.intellij.buildFeature.buildFeatureName=false
```


## noSearchableOptionsWarning

> Available since the upcoming release
>
{type="warning"}

When the [`buildSearchableOptions`](tools_gradle_intellij_plugin.md#buildsearchableoptions-task) doesn't produce any results, e.g., when the plugin doesn't implement any [Settings](settings.md), a warning is shown to suggest [disabling the task](tools_gradle_intellij_plugin_faq.md#how-to-disable-building-searchable-options) for better performance.

{style="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.buildFeature.buildSearchableOptions=false
```


## paidPluginSearchableOptionsWarning

> Available since the upcoming release
>
> {type="warning"}

Due to IDE limitations, it is impossible to run the IDE in headless mode to collect searchable options for a paid plugin.
As paid plugins require providing a valid license and presenting a UI dialog, it is impossible to handle such a case, and the task will fail.
This feature flag displays the given warning when the task is run by a paid plugin.

{style="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.buildFeature.paidPluginSearchableOptionsWarning=false
```


## selfUpdateCheck

With this feature, it is possible to check if the currently used Gradle IntelliJ Plugin is outdated and if there's a new release available.
The plugin performs an update check on every run asking the GitHub Releases page for the redirection URL to the latest version with `HEAD` HTTP request: `https://github.com/jetbrains/gradle-intellij-plugin/releases/latest`
If the current version is outdated, the plugin will emit a warning presenting the current and the latest versions.

Feature respects the Gradle [`--offline`](https://docs.gradle.org/current/userguide/command_line_interface.html#sec:command_line_execution_options) mode.

{style="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.buildFeature.selfUpdateCheck=false
```


## useDependencyFirstResolutionStrategy

> Available since the upcoming release
>
{type="warning"}

Set the `ResolutionStrategy.SortOrder.DEPENDENCY_FIRST` resolution strategy for `compileClasspath` and `testCompileClasspath` Gradle configurations.
This behaviour allows for making the dependencies specified in the project's Gradle configuration considered before the dependencies added by the Gradle IntelliJ Plugin.
See [JetBrains/gradle-intellij-plugin#656](https://github.com/JetBrains/gradle-intellij-plugin/issues/656) for more details.

{style="narrow"}
Default value
: `true`

Example
:
```properties
org.jetbrains.intellij.buildFeature.useDependencyFirstResolutionStrategy=false
```
