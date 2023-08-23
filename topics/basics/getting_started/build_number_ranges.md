# Build Number Ranges

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Defining product compatibility by specifying plugin since-build and until-build ranges.</link-summary>

Use this reference of build number ranges to specify the correct `since-build` and `until-build` values in your plugin descriptor.
Setting the actual values in <path>[plugin.xml](plugin_configuration_file.md)</path> is managed by [`patchPluginXml`](tools_gradle_intellij_plugin.md#tasks-patchpluginxml) Gradle task, see [](configuring_plugin_project.md#patching-the-plugin-configuration-file).

Please note the following regarding values:

- Values must represent the actual build numbers, any made-up numbers must not be used (e.g., `999.*`, `2023.4`, or `2023.9999` are invalid for `until-build`).
- Not specifying `until-build` means it will include _all_ future builds (including future, yet unreleased IDE versions, which might impact compatibility later).
- Dot star suffix (`.* `) can be used in `until-build` to support all releases for the specific branch (i.e., `222.*` for all 2022.2.x releases).

> Compatibility with the specified version range (and compatible products) must always be verified using [](verifying_plugin_compatibility.md#plugin-verifier) to ensure binary compatibility.
>
> Plugins hosted on [JetBrains Marketplace](https://plugins.jetbrains.com) are checked automatically.
> According to [Approval Guidelines](https://plugins.jetbrains.com/legal/approval-guidelines), incompatible plugin versions will be restricted by JetBrains if necessary.
>
{style="warning"}

### Build Number Format

To denote a release, a multipart build number is used.
It consists of the following parts:

* Product ID (`IC` for IDEA Community, `IU` for IDEA Ultimate, `RM` for RubyMine, `PY` for PyCharm, `PS` for PhpStorm, etc.)
* Branch number (`162`)
* Build number in the branch (`94`)

Branch numbers are based on the `YYYY.R` [IDE release version numbers](https://blog.jetbrains.com/blog/2016/03/09/jetbrains-toolbox-release-and-versioning-changes/).
The branch number takes the last two digits of the year and the `R` release number.
For example, `162` for 20*16.2*, `163` for 20*16.3*, etc.

The build number may have multiple components: `IU-162.94.11`, `IU-162.94.11.256.42`.
This gives more flexibility for third-party plugins and IDE developers.
Plugins may specify compatibility versions more precisely (e.g., requiring a specific bugfix release); IDE vendors may have build numbers based on a specific IntelliJ Platform version and specify additional internal version (e.g. `256.42` in `XX-162.94.11.256.42`) to allow plugin developers for their IDE to specify compatibility.

Multipart build numbers can also be used in the `since-build` and `until-build` attributes of `idea-version`.
Usually you should omit the product ID and use only the branch number and build number, for example:

<compare type="top-bottom" first-title="Any 213 branch version" second-title="Specific build number">

```xml
<!-- 2021.3, 2021.3.1, 2021.3.2, ... -->
<idea-version since-build="213" until-build="213.*"/>
```

```xml
<!-- 2021.3.3 or higher -->
<idea-version since-build="213.7172.25"/>
```
</compare>

> Specific build numbers and their corresponding release version are available via _Previous Releases_ on the corresponding product's download page, e.g. [Previous IntelliJ IDEA Releases](https://www.jetbrains.com/idea/download/previous.html).
> For upcoming versions, see [Early Access Program](https://eap.jetbrains.com).
>
> See also [What versions of IntelliJ-based IDEs are supported?](https://intellij-support.jetbrains.com/hc/en-us/articles/360019574859-What-versions-of-IntelliJ-based-IDEs-are-supported-) for JetBrains IDE support policy.
>
{style="note"}

### Targeting Multiple IDE Versions
{id="multipleIDEVersions"}

<include from="configuring_plugin_project.md" element-id="whichPlatformVersion"/>

When supporting multiple major versions, it is strongly recommended to build against the _lowest_ supported version to guarantee backwards-compatibility.
In the case of supporting a range of platform versions with different underlying Java level ([see below](#platformVersions)), it is _required_.

[](verifying_plugin_compatibility.md) discusses tooling support to ensure compatibility with the chosen version range and IDEs.

Consult [Incompatible API Changes](api_changes_list.md) and [Notable API Changes](api_notable.md) for an overview of known breaking and relevant changes across IDE versions.

### IntelliJ Platform Based Products of Recent IDE Versions
{id="platformVersions"}

<include from="tools_gradle_intellij_plugin.md" element-id="gradle_plugin_223_problem"/>

| Branch number                                                   | IntelliJ Platform version                                                                                                                |
|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| [232](https://github.com/JetBrains/intellij-community/tree/232) | 2023.2                                                                                                                                   |
| [231](https://github.com/JetBrains/intellij-community/tree/231) | 2023.1                                                                                                                                   |
| [223](https://github.com/JetBrains/intellij-community/tree/223) | 2022.3                                                                                                                                   |
| [222](https://github.com/JetBrains/intellij-community/tree/222) | 2022.2 **NOTE** Java 17 is now required ([blog post](https://blog.jetbrains.com/platform/2022/08/intellij-project-migrates-to-java-17/)) |
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

Note that there is no `YY0`.
In the `YYYY.R` versioning scheme, the `R` part starts at 1.
