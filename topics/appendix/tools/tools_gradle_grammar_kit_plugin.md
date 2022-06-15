[//]: # (title: Gradle Grammar-Kit Plugin)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This Gradle plugin automates generating lexers and parsers to support the custom language development of plugins for IntelliJ-based IDEs when using Grammar-Kit.

> Current Gradle Grammar-Kit Plugin version is ![GitHub Release](https://img.shields.io/github/release/jetbrains/gradle-grammar-kit-plugin.svg?style=flat-square)
>
{type="note"}

> The plugin does not support two-pass generation. Therefore, it does not support method mixins.
>
{type="tip"}

## Usage
To enable this plugin in your Gradle-based project, register the plugin in the Gradle build script's `plugins` section:

<tabs>
<tab title="Kotlin">

```kotlin
plugins {
  id("org.jetbrains.grammarkit") version "..."
}
```

</tab>
<tab title="Groovy">

```groovy
plugins {
  id "org.jetbrains.grammarkit" version "..."
}
```

</tab>
</tabs>

> This project requires `Gradle 6.7.1` or newer, however it is recommended to use the latest Gradle available.
> Update it with:
> ```Bash
> ./gradlew wrapper --gradle-version=VERSION
> ```
>
> See also: [Gradle Installation](https://gradle.org/install/) guide.
>
{type="tip"}

> Please see [CONTRIBUTING](https://github.com/JetBrains/gradle-grammar-kit-plugin/blob/master/CONTRIBUTING.md) on how to submit feedback and contribute to this project.
>
> Before visiting the [Issue Tracker](https://github.com/JetBrains/gradle-grammar-kit-plugin/issues), update both plugin and Gradle to the latest versions.
>
{type="tip"}


## Grammar-Kit Extension
After the Gradle Grammar-Kit Plugin is applied, the `grammarKit` extension can be used to configure the plugin and common settings of the provided tasks.

**Example:**


<tabs>
<tab title="Kotlin">

```kotlin
grammarKit {
  jflexRelease.set("1.7.0-1")
  grammarKitRelease.set("2021.1.2")
  intellijRelease.set("203.7717.81")
}
```

</tab>
<tab title="Groovy">

```groovy
grammarKit {
  jflexRelease = "1.7.0-1"
  grammarKitRelease = "2021.1.2"
  intellijRelease = "203.7717.81"
}
```

</tab>
</tabs>


### grammarKitRelease
{id="grammar-kit-extension-grammarkitrelease"}

The release version of the [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) to use.

{style="narrow"}
Type
: `String`

Default value
: `2021.1.2`


### jflexRelease
{id="grammar-kit-extension-jflexrelease"}

The version of the IntelliJ-patched JFlex, a [fork of JFlex](https://github.com/JetBrains/intellij-deps-jflex) lexer generator for IntelliJ Platform API.

{style="narrow"}
Type
: `String`

Default value
: `1.7.0-1`


### intellijRelease
{id="grammar-kit-extension-intellijrelease"}

An optional IntelliJ version to build the classpath for [`GenerateParser`](#) and [`GenerateLexer`](#) tasks.

If provided, [`grammarKitRelease`](#grammar-kit-extension-grammarkitrelease) and [`jflexRelease`](#grammar-kit-extension-jflexrelease) properties are ignored as both dependencies will be provided from the given IntelliJ IDEA release.

{style="narrow"}
Type
: `String`

Default value
: `null`


## generateLexer Task
{id="generatelexer-task"}

The `generateLexer` task generates a lexer for the given grammar.
The task is configured using common [`grammarKit`](#grammar-kit-extension) extension.


### source
{id="generatelexer-task-source"}

The source Flex file to generate the lexer from.

{style="narrow"}
Required
: `true`

Type
: `String`


### targetDir
{id="generatelexer-task-targetdir"}

The path to the target directory for the generated lexer.

{style="narrow"}
Required
: `true`

Type
: `String`


### targetClass
{id="generatelexer-task-targetclass"}

The Java file name where the generated lexer will be written.

{style="narrow"}
Required
: `true`

Type
: `String`


### skeleton
{id="generatelexer-task-skeleton"}

An optional path to the skeleton file to use for the generated lexer.
The path will be provided as `--skel` option.
By default, it uses the <path>[idea-flex.skeleton](https://raw.github.com/JetBrains/intellij-community/master/tools/lexer/idea-flex.skeleton)</path> skeleton file.

{style="narrow"}
Type
: `String`

Default
: `null`


### purgeOldFiles
{id="generatelexer-task-purgeoldfiles"}

Purge old files from the target directory before generating the lexer.

{style="narrow"}
Type
: `Boolean`

Default
: `false`


## generateParser Task
{id="generateparser-task"}

The `generateParser` task generates a parser for the given grammar.
The task is configured using common [`grammarKit`](#grammar-kit-extension) extension.


### source
{id="generateparser-task-source"}

The source BNF file to generate the parser from.

{style="narrow"}
Required
: `true`

Type
: `String`


### targetRoot
{id="generateparser-task-targetroot"}

The path to the target directory for the generated parser.

{style="narrow"}
Type
: `String`

Default
: `null`


### pathToParser
{id="generateparser-task-pathtoparser"}

The location of the generated parser class, relative to the [`targetRoot`](#generateparser-task-targetroot).

{style="narrow"}
Required
: `true`

Type
: `String`


### pathToPsiRoot
{id="generateparser-task-pathtopsiroot"}

The location of the generated PSI files, relative to the [`targetRoot`](#generateparser-task-targetroot).

{style="narrow"}
Required
: `true`

Type
: `String`


### purgeOldFiles
{id="generateparser-task-purgeoldfiles"}

Purge old files from the target directory before generating the parser.

{style="narrow"}
Type
: `Boolean`

Default
: `false`


## Useful Resources

* [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit)
* [IntelliJ-patched JFlex Sources](https://github.com/JetBrains/intellij-deps-jflex)
* [IntelliJ-patched JFlex](https://cache-redirector.jetbrains.com/intellij-dependencies/org/jetbrains/intellij/deps/jflex/jflex/)

### Usage Examples

* [Perl5 plugin](https://github.com/Camelcade/Perl5-IDEA/blob/master/build.gradle)
* [Rust plugin](https://github.com/intellij-rust/intellij-rust/blob/master/build.gradle.kts)
* [Bamboo Soy plugin](https://github.com/google/bamboo-soy/blob/master/build.gradle)
