<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# JetBrains Runtime (JBR)

<link-summary>The IntelliJ Platform Gradle Plugin requires JetBrains Runtime (JBR), which can be provided in multiple ways.</link-summary>

<include from="tools_intellij_platform_gradle_plugin.md" element-id="faq"/>

JetBrains Runtime (JBR) is a custom version of OpenJDK, specifically optimized for IntelliJ-based IDEs. It supports enhanced class redefinition (DCEVM), includes the Java Chromium Embedded Framework (JCEF), and improves aspects such as font rendering and keyboard support, thereby enhancing overall performance.
IDEs built on the IntelliJ Platform rely on JBR for running.
Hence, when developing plugins using the IntelliJ Platform Gradle Plugin, it's essential to have JBR provided.

For the best experience, it is recommended to rely on JBR bundled with the IntelliJ Platform used for development.
If one is not available, i.e., when targeting IntelliJ Platform snapshot or nightly releases, there are other ways available for providing JetBrains Runtime in a required version or variant.

## Bundled (Default)

IDE releases provided with JetBrains CDN (download.jetbrains.com) are OS-specific and contain JetBrains Runtime (JBR) already bundled within the archive.
This is the recommended way for developing a plugin, as it comes in exactly the same version as JetBrains IDEs delivered to users.

To use this approach, no extra steps are required but declare the IntelliJ Platform, see: [](tools_intellij_platform_gradle_plugin_dependencies_extension.md#target-platforms).

## Obtained with IntelliJ Platform from Maven

If the IntelliJ Platform is resolved with the IntelliJ Maven Repository, it has no JetBrains Runtime (JBR) bundled.
In such a case, it is necessary to provide it in the suitable version with the version read from the <path>[intellijPlatform]/dependencies.txt</path> file.

This can be easily achieved by using the `jetbrainsRuntime()` repository helper pointing to [JetBrains Runtime GitHub Releases](https://github.com/JetBrains/JetBrainsRuntime/releases/) and dependency helper with no arguments provided:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
    jetbrainsRuntime()
    // ...
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")

    jetbrainsRuntime()

    // ...
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
    jetbrainsRuntime()
    // ...
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity '%ijPlatform%'

    jetbrainsRuntime()

    // ...
  }
}
```

</tab>
</tabs>


## Declared Explicitly

It is possible to explicitly specify JetBrains Runtime version, variant, or exact build with:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
    jetbrainsRuntime()
    // ...
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("%ijPlatform%")

    jetbrainsRuntime(version, variant, architecture)
    // or
    jetbrainsRuntimeExplicit(explicitVersion)

    // ...
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
repositories {
  mavenCentral()
  intellijPlatform {
    defaultRepositories()
    jetbrainsRuntime()
    // ...
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity '%ijPlatform%'

    jetbrainsRuntime(version, variant, architecture)
    // or
    jetbrainsRuntimeExplicit(explicitVersion)

    // ...
  }
}
```

</tab>
</tabs>


Provided `version`, `variant`, and `architecture` parameters along with the `explicitVersion` are used to resolve the JetBrains Runtime archives published on [GitHub Releases](https://github.com/JetBrains/JetBrainsRuntime/releases/) page.

To correctly understand the pattern, refer to the archive names in format:
```
jbr_[prefix]-[jdk]-[os]-[arch]-b[build].tar.gz
```
e.g.:
```
jbr_jcef-21.0.3-osx-aarch64-b446.1.tar.gz
```
which can be split into the following parts:

- `prefix` = `jcef`
- `jdk` = `21.0.3`
- `os` = `osx`
- `arch` = `aarch64`
- `build` = `446.1`

Note that the `version` parameter is actually a combination of JDK and build numbers in format `[jdk]b[build]`.

To resolve the `jbr_jcef-21.0.3-osx-aarch64-b446.1.tar.gz` archive, you can use:
- `jetbrainsRuntime("21.0.3b446.1", "osx", "aarch64")`
- `jetbrainsRuntime("21.0.3b446.1")` (`os` and `architecture` are by default resolved with the current environment)
- `jetbrainsRuntimeExplicit("jbr_jcef-21.0.3-osx-aarch64-b446.1")`

See the [JetBrains Runtime releases page](https://github.com/JetBrains/JetBrainsRuntime/releases) for the list of available releases.

## Foojay Toolchains Plugin

Since Gradle `8.4`, it is possible to specify JetBrains as a known JVM vendor and instruct Gradle to search for JetBrains Runtime within available toolchains (see [Toolchains for JVM projects](https://docs.gradle.org/current/userguide/toolchains.html)).

Along with the [Foojay Toolchains Plugin](https://github.com/gradle/foojay-toolchains), Gradle can resolve JetBrains Runtime from the remote repository if the suitable JVM is not present:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

<path>build.gradle.kts</path>
```kotlin
kotlin {
  jvmToolchain {
    languageVersion = JavaLanguageVersion.of(17)
    vendor = JvmVendorSpec.JETBRAINS
  }
}
```

<path>settings.gradle.kts</path>
```kotlin
plugins {
  id("org.gradle.toolchains.foojay-resolver-convention") version "..."
}
```

</tab>
<tab title="Groovy" group-key="groovy">

<path>build.gradle</path>
```groovy
java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(17)
    vendor = JvmVendorSpec.JETBRAINS
  }
}
```

<path>settings.gradle</path>
```groovy
plugins {
  id 'org.gradle.toolchains.foojay-resolver-convention' version '...'
}
```

</tab>
</tabs>


Please note that the latest available JetBrains Runtime release is always resolved, which may lead to unexpected behaviors.

## Project SDK

When building or running a project directly from the IDE, it is possible to specify JetBrains Runtime in Project Settings as a Project SDK.

Provided JBR will be eventually resolved and passed to Gradle tasks for running.
However, this setting is ignored when running a project directly from the command line or CI.
