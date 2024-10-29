<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Build Number Ranges

<link-summary>Defining product compatibility by specifying plugin since-build and until-build ranges.</link-summary>

Use this reference of build number ranges to specify the correct `since-build` and `until-build` values in your plugin descriptor.

Setting the actual values in <path>[plugin.xml](plugin_configuration_file.md)</path> is managed by the `patchPluginXml` Gradle task
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#patchPluginXml), [1.x](tools_gradle_intellij_plugin.md#tasks-patchpluginxml)).
See [](configuring_plugin_project.md#patching-the-plugin-configuration-file) for further details.

### Build Numbers

Note the following regarding values for `since-build` and `until-build`:

#### Valid Values

Values must represent the [actual build numbers](#build-number-format).
Any made-up numbers must not be used.
Violations are highlighted by [plugin verifier](verifying_plugin_compatibility.md), and such plugins will be rejected on JetBrains Marketplace.

For example, `233.*` is invalid for `since-build`.
Any of `999.*`, `234.*` (maximum is `233.*`) and `223.9999` are invalid for `until-build`.

#### "Open-End" Compatibility

Not specifying the `until-build` attribute means it will include _all_ future builds.
This includes future, yet unreleased versions and possibly new IDEs, which might impact compatibility later.

See [`until-build`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-pluginConfiguration-ideaVersion-untilBuild)
documentation for the necessary Gradle configuration.

#### Branch Version Compatibility

To support all releases for a specific branch (major version), use a dot-star suffix (`.* `) for `until-build`.

For example, `232.*` for all 2023.2.x releases.

> Before publishing, the plugin must be checked using [](verifying_plugin_compatibility.md#plugin-verifier) against the specified version range (and specified compatible products) to ensure binary compatibility.
> Any additional reported errors/warnings should also be fixed when possible.
>
> Plugins hosted on [JetBrains Marketplace](https://plugins.jetbrains.com) are checked automatically.
> According to [Approval Guidelines](https://plugins.jetbrains.com/legal/approval-guidelines), JetBrains will restrict incompatible plugin versions if necessary.
>
{title="Compatibility Enforcement" style="warning"}

### Build Number Format

To denote a release, a multipart build number is used.
It consists of the following parts:

* Product ID (`IC` for IDEA Community, `IU` for IDEA Ultimate, `RM` for RubyMine, `PY` for PyCharm, `PS` for PhpStorm, etc.)
* Branch number (`223`)
* Build number in the branch (`9559`)

Branch numbers are based on the `YYYY.R` [IDE release version numbers](https://blog.jetbrains.com/blog/2016/03/09/jetbrains-toolbox-release-and-versioning-changes/).
The branch number takes the last two digits of the year and the `R` release number.
For example, `231` for 20**23.1**, `232` for 20**23.2**, and `233` for 20**23.3**.

The build number may have multiple components: `IU-162.94.11`, `IU-162.94.11.256.42`.
This gives more flexibility for third-party plugins and IDE developers.
Plugins may specify compatibility versions more precisely (for example, requiring a specific bugfix release); IDE vendors may have build numbers based on a specific IntelliJ Platform version and specify an additional internal version (for example, `256.42` in `XX-162.94.11.256.42`) to allow plugin developers for their IDE to specify compatibility.

Multipart build numbers can also be used in the `since-build` and `until-build` attributes of `idea-version`.
Usually you should omit the product ID and use only the branch number and build number, for example:

<compare type="top-bottom" first-title="Any 213 branch version" second-title="Specific build number">

```xml
<!-- 2021.3, 2021.3.1, 2021.3.2, ... -->
<idea-version since-build="213" until-build="213.*"/>
```

```xml
<!-- 2021.3.3 or later -->
<idea-version since-build="213.7172.25"/>
```

</compare>

> Specific build numbers and their corresponding release version are available via _Previous Releases_ on the corresponding product's download page, for example, [Previous IntelliJ IDEA Releases](https://www.jetbrains.com/idea/download/previous.html).
> For upcoming versions, see [Early Access Program](https://eap.jetbrains.com).
>
> See also ["What versions of IntelliJ-based IDEs are supported?"](https://intellij-support.jetbrains.com/hc/en-us/articles/360019574859-What-versions-of-IntelliJ-based-IDEs-are-supported-) for JetBrains IDE support policy.
>
{style="note" title="Build numbers for products"}

### Targeting Multiple IDE Versions

{id="multipleIDEVersions"}

<include from="configuring_plugin_project.md" element-id="whichPlatformVersion"/>

When supporting multiple major versions, it is strongly recommended to build against the _lowest_ supported version to guarantee backwards-compatibility.
In the case of supporting a range of platform versions with different underlying Java level ([see below](#platformVersions)), it is _required_.

[](verifying_plugin_compatibility.md) discusses tooling support to ensure compatibility with the chosen version range and IDEs.

Consult [Incompatible API Changes](api_changes_list.md) and [Notable API Changes](api_notable.md) for an overview of known breaking and relevant changes across IDE versions.

In some cases, keeping a dedicated branch and corresponding plugin release for each major IDE version might be required due to incompatibilities that cannot be solved in other ways.
The IDE will automatically install only the corresponding compatible build.

### Platform Versions

{id="platformVersions"}

Note that there is no `YY0`.
In the `YYYY.R` versioning scheme, the `R` part starts at 1.

<include from="snippets.md" element-id="apiChangesJavaVersion"/>

<include from="snippets.md" element-id="gradlePluginVersion"/>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

| Branch number                                                   | IntelliJ Platform version               |
|-----------------------------------------------------------------|-----------------------------------------|
| [243](https://github.com/JetBrains/intellij-community/tree/243) | 2024.3                                  |
| [242](https://github.com/JetBrains/intellij-community/tree/242) | 2024.2 **NOTE** Java 21 is now required |
| [241](https://github.com/JetBrains/intellij-community/tree/241) | 2024.1                                  |
| [233](https://github.com/JetBrains/intellij-community/tree/233) | 2023.3                                  |
| [232](https://github.com/JetBrains/intellij-community/tree/232) | 2023.2                                  |
| [231](https://github.com/JetBrains/intellij-community/tree/231) | 2023.1                                  |

#### Earlier Versions

{collapsible="true"}

| Branch number                                                   | IntelliJ Platform version                                                                                                                |
|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| [223](https://github.com/JetBrains/intellij-community/tree/223) | 2022.3 **NOTE** Java 17 is now required ([blog post](https://blog.jetbrains.com/platform/2022/08/intellij-project-migrates-to-java-17/)) |
| [222](https://github.com/JetBrains/intellij-community/tree/222) | 2022.2                                                                                                                                   |
| [221](https://github.com/JetBrains/intellij-community/tree/221) | 2022.1                                                                                                                                   |
| [213](https://github.com/JetBrains/intellij-community/tree/213) | 2021.3                                                                                                                                   |
| [212](https://github.com/JetBrains/intellij-community/tree/212) | 2021.2                                                                                                                                   |
| [211](https://github.com/JetBrains/intellij-community/tree/211) | 2021.1                                                                                                                                   |
| [203](https://github.com/JetBrains/intellij-community/tree/203) | 2020.3 **NOTE** Java 11 is now required ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)) |
| [202](https://github.com/JetBrains/intellij-community/tree/202) | 2020.2                                                                                                                                   |
| [201](https://github.com/JetBrains/intellij-community/tree/201) | 2020.1                                                                                                                                   |
| [193](https://github.com/JetBrains/intellij-community/tree/193) | 2019.3                                                                                                                                   |
| [192](https://github.com/JetBrains/intellij-community/tree/192) | 2019.2                                                                                                                                   |
| [191](https://github.com/JetBrains/intellij-community/tree/191) | 2019.1                                                                                                                                   |

