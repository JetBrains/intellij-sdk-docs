---
title: Kotlin for Plugin Developers
---

## 1. Why Kotlin?

Using Kotlin to write plugins for the IntelliJ Platform is very similar to writing plugins in Java. Existing plugin developers can get started by converting boilerplate Java classes to their Kotlin equivalents by using the [J2K compiler](https://kotlinlang.org/docs/tutorials/mixing-java-kotlin-intellij.html#converting-an-existing-java-file-to-kotlin-with-j2k) bundled with the IntelliJ Platform (versions  143.+), and developers can easily mix and match Kotlin classes with their existing Java code.

In addition to [null safety](https://kotlinlang.org/docs/reference/null-safety.html) and [type-safe builders](https://kotlinlang.org/docs/reference/type-safe-builders.html), the Kotlin language offers a number of convenient features for plugin development, which make plugins easier to read and simpler to maintain. Much like [Kotlin for Android](https://kotlinlang.org/docs/tutorials/kotlin-android.html), the IntelliJ Platform makes extensive use of callbacks, which are easy to express as [lambdas](https://kotlinlang.org/docs/reference/lambdas.html) in Kotlin.

Likewise, it is easy to customize the behavior of internal classes in IntelliJ IDEA, with [extensions](https://kotlinlang.org/docs/reference/extensions.html). For example, it is common practice to [guard logging statements](https://www.slf4j.org/faq.html#logging_performance) to avoid the cost of parameter construction, leading to the following ceremony when using the log:

```java
if(logger.isDebugEnabled()) {
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

Now we can directly write `logger.debug { "..." }` to receive all the benefits of lightweight logging, with none of the verbosity. With practice, you will be able to recognize many idioms in the IntelliJ Platform that can be simplified with Kotlin. To learn more about building IntelliJ Platform plugins with Kotlin, this tutorial will help you get started.

## 2. Adding Kotlin Support

Plugins targeting the IntelliJ Platform versions 143 and above are easy to migrate: just start writing Kotlin. The necessary Kotlin plugins and libraries are already bundled with the IDE, requiring no further configuration. For version 142 or below, you will need to install and configure dependencies to the Kotlin runtime (in addition to installing the Kotlin plugin itself, for code assistance and tooling support). For detailed instructions, please refer to the [Kotlin documentation](https://kotlinlang.org/docs/tutorials/getting-started.html).

## 3. Kotlin Gradle Plugin

For plugins already using the [Gradle Build System](build_system.md), or those that require precise control over the Kotlin build process, we recommend using the [kotlin-gradle-plugin](https://kotlinlang.org/docs/reference/using-gradle.html#configuring-dependencies). This [Gradle plugin](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-gradle-plugin-core) greatly simplifies building Kotlin projects in a controlled and reproducible manner.

Your `build.gradle` file may look like so:

```groovy
buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
    }
}

apply plugin: 'org.jetbrains.intellij'
apply plugin: 'kotlin'

intellij {
    version 'LATEST-TRUNK-SNAPSHOT'
    pluginName 'Example'
}

group 'com.example'
version '0.0.1'

repositories {
    mavenCentral()
}
```

Please note that you should **not** include `kotlin-runtime` and `kotlin-stdlib` jars with your plugin because Kotlin guarantees backward- and forward- binary compatibility.

### 3.1. Use Kotlin to write Gradle script

Starting with 4.4, Gradle supports `build.gradle.kts`, an alternative to `build.gradle` written in Kotlin.

There're many good resources for learning how to write build scripts for an IntelliJ plugin with Kotlin script, like [intellij-rust](https://github.com/intellij-rust/intellij-rust/blob/master/build.gradle.kts), [julia-intellij](https://github.com/ice1000/julia-intellij/blob/master/build.gradle.kts), [covscript-intellij](https://github.com/covscript/covscript-intellij/blob/master/build.gradle.kts) or [zig-intellij](https://github.com/ice1000/zig-intellij/blob/master/build.gradle.kts).

`build.gradle.kts` basically looks like:

```kotlin
group = "com.your.company.name"
version = "0.1-SNAPSHOT"

buildscript {
    repositories { mavenCentral() }
    dependencies { classpath(kotlin("gradle-plugin", "1.2.30")) }
}

plugins {
	id("org.jetbrains.intellij") version "0.4.14"
	kotlin("jvm") version "1.2.30"
}

intellij {
    updateSinceUntilBuild = false
    instrumentCode = true
    version = "2017.3"
}
```

## 4. UI in Kotlin

The best way to create user interfaces with Kotlin is to use a [type safe DSL](/user_interface_components/kotlin_ui_dsl.md) for building forms. Using GUI designer with Kotlin is currently [not supported](https://youtrack.jetbrains.com/issue/KT-6660). 

## 5. Handling Kotlin code

If you need to write a plugin that processes Kotlin code, you need to add a dependency on the Kotlin plugin. Please refer
to [Plugin Dependencies](/basics/plugin_structure/plugin_dependencies.md) for information on how to do that.

## 6. Examples

There are a number of [open source Kotlin projects](https://github.com/search?l=Kotlin&q=+intellij&ref=searchresults&type=Repositories) built on the IntelliJ Platform. For a readily available source of up to date examples and applications of the Kotlin language for building developer tools with the IntelliJ Platform, developers may look to these projects for inspiration:

* [intellij-rust](https://github.com/intellij-rust/intellij-rust)
* [haskell-idea-plugin](https://github.com/atsky/haskell-idea-plugin)
* [intellij-markdown](https://github.com/valich/intellij-markdown)
* [IntelliJ-presentation-assistant](https://github.com/chashnikov/IntelliJ-presentation-assistant)
* [leanpub-plugin](https://github.com/hhariri/leanpub-plugin)