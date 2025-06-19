<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle Grammar-Kit Plugin

<link-summary>Gradle Grammar-Kit Plugin automates generating custom language lexers and parsers when using Grammar-Kit.</link-summary>

<tldr>

**Current Release**: %gradle-grammar-kit-plugin-version%

**GitHub**: [Releases & Changelog](https://github.com/JetBrains/gradle-grammar-kit-plugin/releases), [Issue Tracker](https://github.com/JetBrains/gradle-grammar-kit-plugin/issues)

</tldr>

The [Gradle Grammar-Kit Plugin](https://github.com/JetBrains/gradle-grammar-kit-plugin) automates generating lexers and parsers to support building [custom language](custom_language_support.md) plugins for IntelliJ-based IDEs when using [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit).

> The plugin does not support two-pass generation. Therefore, it does not support method mixins.
>
{style="note" title="Known Limitations"}

> Please see [CONTRIBUTING](https://github.com/JetBrains/gradle-grammar-kit-plugin/blob/master/CONTRIBUTING.md) on how to submit feedback and contribute to this project.
>
> Before visiting the [Issue Tracker](https://github.com/JetBrains/gradle-grammar-kit-plugin/issues), update both plugin and Gradle to the latest versions.

## Usage
To enable this plugin in your Gradle-based project, register the plugin in the Gradle build script's `plugins` section:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
plugins {
  id("org.jetbrains.grammarkit") version "%gradle-grammar-kit-plugin-version%"
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
plugins {
  id "org.jetbrains.grammarkit" version "%gradle-grammar-kit-plugin-version%"
}
```

</tab>
</tabs>

> This project requires Gradle 7.4 or newer, however, it is recommended to use the latest Gradle available.
> See [Gradle Installation](https://gradle.org/install/) guide.
>
{title="Minimum Gradle Version"}

## Configuration

See also [](#usage-examples) below.

### Grammar-Kit Extension
After the Gradle Grammar-Kit Plugin is applied, the `grammarKit` extension can be used to configure the plugin and common settings of the provided tasks.

> In most cases, explicit configuration can be omitted.
>
{style="tip"}

**Example:**

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
grammarKit {
  jflexRelease.set("1.7.0-1")
  grammarKitRelease.set("2021.1.2")
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
grammarKit {
  jflexRelease = "1.7.0-1"
  grammarKitRelease = "2021.1.2"
}
```

</tab>
</tabs>


#### grammarKitRelease
{#grammar-kit-extension-grammarkitrelease}

The release version of the [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit) to use.

{type="narrow"}
Type
: `String`

Default value
: `2022.3.2`


#### jflexRelease
{#grammar-kit-extension-jflexrelease}

The version of the IntelliJ-patched JFlex, a [fork of JFlex](https://github.com/JetBrains/intellij-deps-jflex) lexer generator for IntelliJ Platform API.

{type="narrow"}
Type
: `String`

Default value
: `1.9.2`


#### intellijRelease
{#grammar-kit-extension-intellijrelease}

An optional IntelliJ IDEA version to build the classpath for [`GenerateParser`](#generateparser-task) and [`GenerateLexer`](#generatelexer-task) tasks.

If provided, [`grammarKitRelease`](#grammar-kit-extension-grammarkitrelease) and [`jflexRelease`](#grammar-kit-extension-jflexrelease) properties are ignored as both dependencies will be provided from the given IntelliJ IDEA release.

{type="narrow"}
Type
: `String`

Default value
: `null`


## Tasks

### generateLexer
{#generatelexer-task}

The `generateLexer` task generates a lexer for the given grammar.

The following sections describe task configuration options.
See also [](#grammar-kit-extension) for common configuration.

#### sourceFile
{#tasks-generatelexer-source}

The source <path>.*flex</path> file to generate the lexer from.

{type="narrow"}
Required
: yes

Type
: `String`


#### targetOutputDir
{#tasks-generatelexer-targetOutputDir}

The path to the target directory for the generated lexer.

{type="narrow"}
Required
: yes

Type
: `String`



#### skeleton
{#tasks-generatelexer-skeleton}

An optional path to the skeleton file to use for the generated lexer.
The path will be provided as `--skel` option.
By default, it uses the <path>[idea-flex.skeleton](https://raw.github.com/JetBrains/intellij-community/master/tools/lexer/idea-flex.skeleton)</path> skeleton file.

{type="narrow"}
Type
: `String`

Default
: `null`


#### purgeOldFiles
{#tasks-generatelexer-purgeoldfiles}

Purge old files from the target directory before generating the lexer.

{type="narrow"}
Type
: `Boolean`

Default
: `false`


### generateParser
{#generateparser-task}

The `generateParser` task generates a parser for the given grammar.

The following sections describe task configuration options.
See also [](#grammar-kit-extension) for common configuration.

#### sourceFile
{#tasks-generateparser-source}

The source <path>.bnf</path> file to generate the parser from.

{type="narrow"}
Required
: yes

Type
: `String`


#### targetRootOutputDir
{#tasks-generateparser-targetrootOutputDir}

The path to the target directory for the generated parser.

{type="narrow"}
Type
: `String`

Default
: `null`


#### pathToParser
{#tasks-generateparser-pathtoparser}

The location of the generated parser class, relative to the [`targetRootOutputDir`](#tasks-generateparser-targetrootOutputDir).

{type="narrow"}
Required
: yes

Type
: `String`


#### pathToPsiRoot
{#tasks-generateparser-pathtopsiroot}

The location of the generated PSI files, relative to the [`targetRootOutputDir`](#tasks-generateparser-targetrootOutputDir).

{type="narrow"}
Required
: yes

Type
: `String`


#### purgeOldFiles
{#tasks-generateparser-purgeoldfiles}

Purge old files from the target directory before generating the parser.

{type="narrow"}
Type
: `Boolean`

Default
: `false`


## Usage Examples

* [CSV Editor](https://github.com/SeeSharpSoft/intellij-csv-validator/blob/main/build.gradle.kts)
* [Perl5 plugin](https://github.com/Camelcade/Perl5-IDEA/blob/master/plugin/core/build.gradle.kts)
* [OSS Plugins on GitHub](https://github.com/search?q=+org.jetbrains.grammarkit+language%3AGradle++NOT+is%3Aarchived+&type=code)

## Links

* [Grammar-Kit](https://github.com/JetBrains/Grammar-Kit)
* [IntelliJ-patched JFlex Sources](https://github.com/JetBrains/intellij-deps-jflex)
* [IntelliJ-patched JFlex](https://cache-redirector.jetbrains.com/intellij-dependencies/org/jetbrains/intellij/deps/jflex/jflex/)
