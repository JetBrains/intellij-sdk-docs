[//]: # (title: Configuring Kotlin Support)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->


This page describes developing a plugin code in Kotlin programming language.

> To implement a plugin operating on Kotlin code, configure Kotlin [plugin dependency](plugin_dependencies.md) (`org.jetbrains.kotlin`).
> See also [UAST](uast.md) page for information about how to support multiple JVM-languages, including Kotlin.

## Advantages of Developing a Plugin in Kotlin

Using [Kotlin](https://kotlinlang.org) to write plugins for the IntelliJ Platform is very similar to writing plugins in Java.
Existing plugin developers can get started by converting boilerplate Java classes to their Kotlin equivalents by using the [J2K converter](https://kotlinlang.org/docs/mixing-java-kotlin-intellij.html#converting-an-existing-java-file-to-kotlin-with-j2k) (part of Kotlin plugin), and developers can easily mix and match Kotlin classes with their existing Java code.

In addition to [null safety](https://kotlinlang.org/docs/null-safety.html) and [type-safe builders](https://kotlinlang.org/docs/type-safe-builders.html), the Kotlin language offers many convenient features for plugin development, which make plugins easier to read and simpler to maintain.
Much like [Kotlin for Android](https://kotlinlang.org/docs/android-overview.html), the IntelliJ Platform makes extensive use of callbacks, which are easy to express as [lambdas](https://kotlinlang.org/docs/lambdas.html) in Kotlin.

Likewise, it is easy to customize the behavior of internal classes in IntelliJ IDEA, with [extensions](https://kotlinlang.org/docs/extensions.html).
For example, it is common practice to [guard logging statements](https://www.slf4j.org/faq.html#logging_performance) to avoid the cost of parameter construction, leading to the following ceremony when using the log:

```java
if (logger.isDebugEnabled()) {
  logger.debug("..." + expensiveComputation());
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

Now we can directly write:
```kotlin
logger.debug { "..." + expensiveComputation() }
```
to receive all the benefits of lightweight logging while reducing the code verbosity.

With practice, you will be able to recognize many idioms in the IntelliJ Platform that can be simplified with Kotlin.
To learn more about building IntelliJ Platform plugins with Kotlin, this tutorial will help you get started.

### UI Forms in Kotlin

The IntelliJ Platform provides the [type safe DSL](kotlin_ui_dsl_version_2.md) allowing to UI forms in declarative way.

> Using a GUI designer with Kotlin is currently [not supported](https://youtrack.jetbrains.com/issue/KTIJ-791).
>

## Adding Kotlin Support

> The [IntelliJ Platform Plugin Template](plugin_github_template.md) provides a preconfigured project using Kotlin.
>

IntelliJ IDEA bundles the necessary Kotlin plugin, requiring no further configuration.
For detailed instructions, please refer to the [Kotlin documentation](https://kotlinlang.org/docs/getting-started.html).

### Kotlin Gradle Plugin

Adding Kotlin source files compilation support to the Gradle-based project requires adding and configuring the [Kotlin JVM Gradle plugin](https://kotlinlang.org/docs/gradle.html#targeting-the-jvm).

See the <path>build.gradle.kts</path> from [kotlin_demo](%gh-sdk-samples%/kotlin_demo) sample plugin:

```kotlin
```
{src="kotlin_demo/build.gradle.kts" include-lines="2-"}

### Kotlin Standard Library

Since Kotlin 1.4, a dependency on the standard library `stdlib` is added automatically ([API Docs](https://kotlinlang.org/api/latest/jvm/stdlib/)).
In most cases, it is not necessary to include it in the plugin distribution as the platform already bundles it.

To opt out, add this line in <path>gradle.properties</path>:

```
kotlin.stdlib.default.dependency = false
```

The presence of this Gradle property is checked by the [](tools_gradle_intellij_plugin.md) with the [](tools_gradle_intellij_plugin.md#tasks-verifypluginconfiguration).
If the property is not present, a warning will be reported during the plugin configuration verification, as it is a common problem when Kotlin stdlib gets bundled within the plugin archive.
If it is expected to make Kotlin stdlib present in the final archive, explicitly specify it with `kotlin.stdlib.default.dependency = true`.

If a plugin supports [multiple platform versions](build_number_ranges.md), it must either target the lowest bundled `stdlib` version or provide the specific version in plugin distribution.

| IntelliJ Platform version | Bundled `stdlib` version |
|---------------------------|--------------------------|
| 2022.3                    | 1.7.0                    |
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

### Other Bundled Kotlin Libraries

Please see [Third-Party Software and Licenses](https://www.jetbrains.com/legal/third-party-software/).

## Caution

Plugins *may* use [Kotlin classes](https://kotlinlang.org/docs/classes.html) to implement declarations in the [plugin configuration file](plugin_configuration_file.md).
When registering an extension, the platform uses a dependency injection framework to instantiate these classes.
For this reason, plugins *must not* use [Kotlin objects](https://kotlinlang.org/docs/object-declarations.html) to implement any <path>[plugin.xml](plugin_configuration_file.md)</path> declarations.

## Kotlin Code FAQ

[How to shorten references](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360010025120-Add-new-parameter-into-kotlin-data-class-from-IDEA-plugin?page=1#community_comment_360002950760)

## Example Plugins Implemented in Kotlin

There are many [open-source Kotlin plugins](https://jb.gg/ipe?language=kotlin) built on the IntelliJ Platform.
For a readily available source of up-to-date examples of plugins implemented in Kotlin, developers may look to these projects for inspiration:
* [Presentation Assistant](https://github.com/chashnikov/IntelliJ-presentation-assistant)
* [Rust](https://github.com/intellij-rust/intellij-rust)
* [TeXiFy IDEA](https://github.com/Hannah-Sten/TeXiFy-IDEA)
* [Deno](https://github.com/JetBrains/intellij-plugins/tree/master/Deno)
