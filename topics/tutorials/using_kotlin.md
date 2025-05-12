<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Configuring Kotlin Support

<link-summary>Advantages and required configuration for developing a plugin in Kotlin.</link-summary>

<tldr>

**Homepage**: [Kotlin](https://kotlinlang.org)

**Project Template**: [](plugin_github_template.md)

</tldr>

<link-summary>Developing plugins using or targeting the Kotlin programming language.</link-summary>

This page describes developing plugins using the [Kotlin](https://kotlinlang.org) programming language.

> To implement a plugin _operating_ on Kotlin code ([PSI](psi.md)) in the IDE, see [](idea.md#kotlin-plugin).
>
{title="Operating on Kotlin code"}

## Advantages of Developing a Plugin in Kotlin

Using Kotlin to write plugins for the IntelliJ Platform is very similar to writing plugins in Java.
Existing Java classes can be converted to their Kotlin equivalents by using the [J2K converter](https://kotlinlang.org/docs/mixing-java-kotlin-intellij.html#converting-an-existing-java-file-to-kotlin-with-j2k) (part of the Kotlin plugin).

In addition to [null safety](https://kotlinlang.org/docs/null-safety.html), [type-safe builders](https://kotlinlang.org/docs/type-safe-builders.html), and [](kotlin_coroutines.md)
the Kotlin language offers many convenient features for plugin development,
which make plugins easier to read and simpler to maintain.
Much like [Kotlin for Android](https://kotlinlang.org/docs/android-overview.html), the IntelliJ Platform makes extensive use of callbacks, which are straightforward to express as [lambdas](https://kotlinlang.org/docs/lambdas.html) in Kotlin.

Kotlin classes can be mixed in a project with existing Java code.
This might come in handy when certain APIs require the use of mentioned Kotlin Coroutines.

### Adding Extensions

Likewise, it is possible to customize the behavior of internal classes in the IntelliJ Platform using [extensions](https://kotlinlang.org/docs/extensions.html).
For example, it is common practice to [guard logging statements](https://www.slf4j.org/faq.html#logging_performance) to avoid the cost of parameter construction, leading to the following ceremony when using the log:

```java
if (logger.isDebugEnabled()) {
  logger.debug("..."+expensiveComputation());
}
```

We can achieve the same result more succinctly in Kotlin by declaring the following extension method:

```kotlin
inline fun Logger.debug(lazyMessage: () -> String) {
  if (isDebugEnabled) {
    debug(lazyMessage())
  }
}
```

Now we can directly write:

```kotlin
logger.debug { "..." + expensiveComputation() }
```

to receive all the benefits of lightweight logging while reducing the code verbosity.

With practice, you will be able to recognize many idioms in the IntelliJ Platform that can be simplified with Kotlin.

### UI Forms in Kotlin

The IntelliJ Platform provides a [type-safe DSL](kotlin_ui_dsl_version_2.md) to build UI forms declaratively.

> Using _UI Designer_ plugin with Kotlin is [not supported](https://youtrack.jetbrains.com/issue/KTIJ-791).
>

### Kotlin Coroutines

[](kotlin_coroutines.md) are a lightweight and convenient alternative to threads with many [advantages](kotlin_coroutines.md#coroutines-advantages).

## Adding Kotlin Support

> The [](plugin_github_template.md) provides a preconfigured project using Kotlin.

IntelliJ IDEA bundles the necessary Kotlin IDE plugin, requiring no further configuration.
For detailed instructions, refer to the [Kotlin documentation](https://kotlinlang.org/docs/getting-started.html).

Using Kotlin 2.x is recommended for plugins targeting 2024.3 or later and required for 2025.1 or later.

### Kotlin Gradle Plugin

Adding Kotlin source files compilation support to a Gradle-based project requires adding and configuring the [Kotlin JVM Gradle plugin](https://kotlinlang.org/docs/gradle.html#targeting-the-jvm).

### Kotlin Standard Library (`stdlib`)

{id="kotlin-standard-library"}

A dependency on the standard library `stdlib` is added automatically ([API Docs](https://kotlinlang.org/api/latest/jvm/stdlib/)).
In nearly all cases, it is _not necessary_ to include it in the plugin distribution as the platform already bundles it.

To opt out, add this line in <path>gradle.properties</path>:

```properties
kotlin.stdlib.default.dependency = false
```

To bundle `stdlib` in the plugin distribution, set the property to `true` explicitly.

#### Gradle Check

The presence of this Gradle property is checked with the corresponding Gradle task:

<tabs>
<tab title="IntelliJ Platform Gradle Plugin (2.x)">

[`verifyPlugin`](tools_intellij_platform_gradle_plugin_tasks.md#verifyPluginProjectConfiguration) task

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)">

[`verifyPluginConfiguration`](tools_gradle_intellij_plugin.md#tasks-verifypluginconfiguration) task

</tab>
</tabs>

If the property is not present, a warning will be reported during the plugin configuration verification.

#### `stdlib` – Miscellaneous

If a plugin supports [multiple platform versions](build_number_ranges.md), it must either target the lowest bundled `stdlib` version (see [table below](#bundled-stdlib-versions))
or the specific version must be [provided in plugin distribution](plugin_content.md#plugin-with-dependencies).

See [Dependency on the standard library](https://kotlinlang.org/docs/gradle.html#dependency-on-the-standard-library) for more details.

> If you need to add the Kotlin Standard Library to your **test project** dependencies, see the [](testing_faq.md#how-to-test-a-jvm-language) section.
>
{title="Adding stdlib in tests"}

#### Bundled `stdlib` Versions

| IntelliJ Platform version (latest update) | Bundled `stdlib` version |
|-------------------------------------------|--------------------------|
| 2025.2                                    | 2.1.20                   |
| 2025.1                                    | 2.1.10                   |
| 2024.3                                    | 2.0.21                   |
| 2024.2                                    | 1.9.24                   |
| 2024.1                                    | 1.9.22                   |

#### Earlier Versions

{collapsible="true"}

| IntelliJ Platform version (latest update) | Bundled _stdlib_ version |
|-------------------------------------------|--------------------------|
| 2023.3                                    | 1.9.21                   |
| 2023.2                                    | 1.8.20                   |
| 2023.1                                    | 1.8.0                    |
| 2022.3                                    | 1.7.22                   |
| 2022.2                                    | 1.6.21                   |
| 2022.1                                    | 1.6.10                   |

See [here](https://www.jetbrains.com/legal/third-party-software/) for earlier versions.

### Kotlin Coroutines Libraries (`kotlinx.coroutines`)

{id="coroutinesLibraries"}

> Plugins must always use the bundled library from the target IDE and not provide their own version.
>
{style="warning" title="Use Bundled Library"}

Make sure it is not added via transitive dependencies,
see [View and Debug Dependencies](https://docs.gradle.org/current/userguide/viewing_debugging_dependencies.html) in the Gradle user guide.

See [](kotlin_coroutines.md) on how to use them in plugins.

#### Bundled `kotlinx-coroutines` Versions

| IntelliJ Platform version (latest update) | Bundled `kotlinx-coroutines` version |
|-------------------------------------------|--------------------------------------|
| 2025.2                                    | 1.10.1-intellij-4                    |
| 2025.1                                    | 1.8.0-intellij-13                    |
| 2024.3                                    | 1.8.0-intellij-11                    |
| 2024.2                                    | 1.8.0-intellij-9 (*)                 |
| 2024.1                                    | 1.7.3                                |

(*) Since 2024.2, a custom [fork](https://github.com/JetBrains/intellij-deps-kotlinx.coroutines) with additional patches is bundled.

### Other Bundled Kotlin Libraries

In general, it is strongly advised to always use the bundled library version.

See [Third-Party Software and Licenses](https://www.jetbrains.com/legal/third-party-software/) for an overview of all bundled libraries.

### Incremental compilation

The Kotlin Gradle plugin supports [incremental compilation](https://kotlinlang.org/docs/gradle-compilation-and-caches.html#incremental-compilation), which allows tracking changes in the source files, so the compiler handles only updated code.

<tabs>

<tab title="Kotlin 1.9.0 and later">

No action is required.

Remove the additional `kotlin.incremental.useClasspathSnapshot=false` property in <path>gradle.properties</path> if present.

</tab>

<tab title="Kotlin 1.8.20">

> Consider using Kotlin 1.9.0 or later where this issue has been resolved.

Kotlin `1.8.20` has a [new incremental compilation approach](https://kotlinlang.org/docs/gradle-compilation-and-caches.html#a-new-approach-to-incremental-compilation) which is enabled by default.
Unfortunately, it is not compatible with the IntelliJ Platform when reading large JAR files (like <path>app.jar</path> or <path>3rd-party-rt.jar</path>),
leading to an `Out of Memory` exception:

```
Execution failed for task ':compileKotlin'.
> Failed to transform app.jar to match attributes {artifactType=classpath-entry-snapshot, org.gradle.libraryelements=jar, org.gradle.usage=java-runtime}.
   > Execution failed for ClasspathEntrySnapshotTransform: .../lib/app.jar.
      > Java heap space
```

To avoid this exception, add the following line to <path>gradle.properties</path>:

```
kotlin.incremental.useClasspathSnapshot=false
```

</tab>
</tabs>

## Plugin Implementation Notes

### Do not use `object` but `class`

{id="object-vs-class"}

Plugins *may* use [Kotlin classes](https://kotlinlang.org/docs/classes.html) (`class` keyword) to implement declarations in the [plugin configuration file](plugin_configuration_file.md).
When registering an [extension](plugin_extensions.md), the platform uses a dependency injection framework to instantiate these classes at runtime.
For this reason, plugins *must not* use [Kotlin objects](https://kotlinlang.org/docs/object-declarations.html#object-declarations-overview) (`object` keyword)
to implement any <path>[plugin.xml](plugin_configuration_file.md)</path> declarations.
Managing the lifecycle of extensions is the platform's responsibility, and instantiating these classes as Kotlin singletons may cause issues.

A notable exception is [`FileType`](%gh-ic%/platform/core-api/src/com/intellij/openapi/fileTypes/FileType.java)
(<include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.fileType"/></include>), see also the inspection descriptions below.

Problems are highlighted via these inspections (2023.2):

- <control>Plugin DevKit | Code | Kotlin object registered as extension</control> for Kotlin code
- <control>Plugin DevKit | Plugin descriptor | Extension class is a Kotlin object</control> for <path>plugin.xml</path>

### Do not use `companion object` in extensions

{id="companion-object-extensions"}

A Kotlin `companion object` is always created once you try to load its containing class, and [extension point implementations](plugin_extensions.md) are supposed to be cheap to create.
To avoid unnecessary classloading (and thus slowdown in IDE startup), `companion object` in extensions must only contain simple constants or [logger](ide_infrastructure.md#logging).
Anything else must be a top-level declaration or stored in an `object`.

Use inspection <control>Plugin DevKit | Code | Companion object in extensions</control> to highlight such problems (2023.3).

## Kotlin Code FAQ

### Testing K2 Mode

See [Testing in K2 Locally](https://kotlin.github.io/analysis-api/testing-in-k2-locally.html).

### Analysis API
<primary-label ref="2024.2"/>

The Analysis API is a powerful library for analyzing code in Kotlin.
Built on top of the Kotlin PSI syntax tree, it provides access to various semantic information, including reference targets, expression types, declaration scopes, diagnostics, and more.

See [Kotlin Analysis API Documentation](https://kotlin.github.io/analysis-api/index_md.html) for details.

### Miscellaneous

[How to shorten references](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010025120-Add-new-parameter-into-kotlin-data-class-from-IDEA-plugin?page=1#community_comment_360002950760)

## Example Plugins Implemented in Kotlin

Browse all [open-source Kotlin plugins](https://jb.gg/ipe?language=kotlin) built on the IntelliJ Platform.
