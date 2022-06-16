[//]: # (title: Kotlin for Plugin Developers)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Why Kotlin?

Using [Kotlin](https://kotlinlang.org) to write plugins for the IntelliJ Platform is very similar to writing plugins in Java.
Existing plugin developers can get started by converting boilerplate Java classes to their Kotlin equivalents by using the [J2K compiler](https://kotlinlang.org/docs/mixing-java-kotlin-intellij.html#converting-an-existing-java-file-to-kotlin-with-j2k) bundled with the IntelliJ Platform (versions 143.+), and developers can easily mix and match Kotlin classes with their existing Java code.

In addition to [null safety](https://kotlinlang.org/docs/null-safety.html) and [type-safe builders](https://kotlinlang.org/docs/type-safe-builders.html), the Kotlin language offers many convenient features for plugin development, which make plugins easier to read and simpler to maintain.
Much like [Kotlin for Android](https://kotlinlang.org/docs/android-overview.html), the IntelliJ Platform makes extensive use of callbacks, which are easy to express as [lambdas](https://kotlinlang.org/docs/lambdas.html) in Kotlin.

Likewise, it is easy to customize the behavior of internal classes in IntelliJ IDEA, with [extensions](https://kotlinlang.org/docs/extensions.html).
For example, it is common practice to [guard logging statements](https://www.slf4j.org/faq.html#logging_performance) to avoid the cost of parameter construction, leading to the following ceremony when using the log:

```java
if (logger.isDebugEnabled()) {
  logger.debug("...");
}
```

We can achieve the same result more succinctly in Kotlin, by declaring the following extension method:

```kotlin
inline fun Logger.debug(lazyMessage: () -> String) {
  if (isDebugEnabled) {
    debug(lazyMessage())
  }
}
```

Now we can directly write `logger.debug { "..." }` to receive all the benefits of lightweight logging, with none of the verbosity.
With practice, you will be able to recognize many idioms in the IntelliJ Platform that can be simplified with Kotlin.
To learn more about building IntelliJ Platform plugins with Kotlin, this tutorial will help you get started.

## Adding Kotlin Support

> The [IntelliJ Platform Plugin Template](github_template.md) provides a preconfigured project using Kotlin.
>
> See also [kotlin_demo](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/kotlin_demo) for a minimal sample plugin.
>
{type="tip"}

IntelliJ IDEA bundles the necessary Kotlin plugin, requiring no further configuration.
For detailed instructions, please refer to the [Kotlin documentation](https://kotlinlang.org/docs/getting-started.html).

### Kotlin Standard Library

Since Kotlin 1.4, a dependency on the standard library `stdlib` is added automatically ([API Docs](https://kotlinlang.org/api/latest/jvm/stdlib/)).
In most cases, it is not necessary to include it in the plugin distribution as the platform already bundles it.

To opt out, add this line in <path>gradle.properties</path>:

```properties
kotlin.stdlib.default.dependency = false
```

If a plugin supports [multiple platform versions](build_number_ranges.md), it must either target the lowest bundled `stdlib` version or provide the specific version in plugin distribution.

| IntelliJ Platform version | Bundled `stdlib` version |
|---------------------------|--------------------------|
| 2022.2                    | 1.6.21                   |
| 2022.1                    | 1.6.20                   |
| 2021.3                    | 1.5.10                   |
| 2021.2                    | 1.5.10                   |
| 2021.1                    | 1.4.32                   |
| 2020.3                    | 1.4.0                    |
| 2020.2                    | 1.3.70                   |
| 2020.1                    | 1.3.70                   |
| 2019.3                    | 1.3.31                   |
| 2019.2                    | 1.3.3                    |
| 2019.1                    | 1.3.11                   |

See [Dependency on the standard library](https://kotlinlang.org/docs/gradle.html#dependency-on-the-standard-library) for more details.

> If you need to add Kotlin Standard Library to your **test project** dependencies, see the [](testing_faq.md#how-to-test-a-jvm-language) section.
>
{type="tip"}

### Other Bundled Kotlin Libraries

Please see [Third-Party Software and Licenses](https://www.jetbrains.com/legal/third-party-software/).

## Kotlin Gradle Plugin

Plugins using the [Gradle Build System](gradle_build_system.md) use the [Kotlin JVM Gradle plugin](https://kotlinlang.org/docs/gradle.html#targeting-the-jvm).

See the <path>build.gradle.kts</path> from [kotlin_demo](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/kotlin_demo) sample plugin:

```kotlin
```
{src="kotlin_demo/build.gradle.kts" lines="2-"}

### Use Kotlin for Gradle Build Scripts

Gradle also supports using Kotlin in build scripts: <path>build.gradle.kts</path>.

There are many good resources for learning how to write build scripts for an IntelliJ Platform plugin with Kotlin script, like
[intellij-rust](https://github.com/intellij-rust/intellij-rust/blob/master/build.gradle.kts),
[julia-intellij](https://github.com/JuliaEditorSupport/julia-intellij/blob/master/build.gradle.kts),
[covscript-intellij](https://github.com/covscript/covscript-intellij/blob/master/build.gradle.kts) or
[zig-intellij](https://github.com/ice1000/intellij-zig/blob/master/build.gradle.kts).

Additionally, explore IntelliJ Platform Explorer's [list of open-source plugins](https://jb.gg/ipe?buildSystem=gradle_kts) using Gradle KTS.

## UI in Kotlin

The recommended way to create UI forms with Kotlin is to use a [type safe DSL](kotlin_ui_dsl_version_2.md).
Using a GUI designer with Kotlin is currently [not supported](https://youtrack.jetbrains.com/issue/KTIJ-791).

## Handling Kotlin Code

If a plugin processes Kotlin code (e.g., providing inspections), it needs to add a dependency on the Kotlin plugin (Plugin ID `org.jetbrains.kotlin`) itself.
Please refer to [Plugin Dependencies](plugin_dependencies.md) for more information.

Depending on exact functionality, a plugin can also target [UAST (Unified Abstract Syntax Tree)](uast.md) to support multiple JVM languages, including Java and Kotlin.

### Kotlin Code FAQ

[How to shorten references](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010025120-Add-new-parameter-into-kotlin-data-class-from-IDEA-plugin?page=1#community_comment_360002950760)

## Caution

Plugins *may* use [Kotlin classes](https://kotlinlang.org/docs/classes.html) to implement declarations in the [plugin configuration file](plugin_configuration_file.md).
When registering an extension, the platform uses a dependency injection framework to instantiate these classes.
For this reason, plugins *must not* use [Kotlin objects](https://kotlinlang.org/docs/object-declarations.html) to implement any <path>plugin.xml</path> declarations.

## Examples

There are many [open-source Kotlin plugins](https://jb.gg/ipe?language=kotlin) built on the IntelliJ Platform.
For a readily available source of up to date examples and applications of the Kotlin language for building developer tools with the IntelliJ Platform, developers may look to these projects for inspiration:

* [Presentation Assistant](https://github.com/chashnikov/IntelliJ-presentation-assistant)
* [Rust](https://github.com/intellij-rust/intellij-rust)
* [TeXiFy IDEA](https://github.com/Hannah-Sten/TeXiFy-IDEA)
* [Deno](https://github.com/JetBrains/intellij-plugins/tree/master/Deno)
