[//]: # (title: Build Number Ranges)
<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Use this reference of build number ranges to specify the correct `since-build` and `until-build` values in your plugin descriptor.

Setting the actual values in <path>plugin.xml</path> is usually managed by `patchPluginXml` Gradle task, see [Patching the Plugin Configuration File](gradle_guide.md#patching-the-plugin-configuration-file) for details.

 > Compatibility with the specified version range (and compatible products) must always be verified using [Plugin Verifier](api_changes_list.md#verifying-compatibility) to ensure binary compatibility.
 >
 > Plugins hosted on [JetBrains Plugins Repository](https://plugins.jetbrains.com) are checked automatically. According to [Approval Guidelines](https://plugins.jetbrains.com/legal/approval-guidelines), incompatible plugin versions will be restricted by JetBrains if necessary.
 >
 {type="warning"}

Starting with IntelliJ IDEA 9 beta, a multi-part build number is used, such as `IU-162.94`.

The number consists of the following parts:

* Product ID (`IC` for IDEA Community, `IU` for IDEA Ultimate, `RM` for RubyMine, `PY` for PyCharm, etc.)
* Branch number (`162`)
* Build number in the branch (`94`)

Since version 2016.2 of the IntelliJ Platform, branch numbers are based on the `YYYY.R` [IDE release version numbers](https://blog.jetbrains.com/blog/2016/03/09/jetbrains-toolbox-release-and-versioning-changes/).
The branch number takes the last two digits of the year and the `R` release number.
For example, `162` for 2016.2, `163` for 2016.3, `171` for 2017.1.
In this scheme, `IU-162.94` corresponds to the 2016.2 release.

Starting with 2016.2, the build number may also have multiple components: `IU-162.94`, `IU-162.94.11`, `IU-162.94.11.256.42`.
This gives more flexibility for third-party plugins and IDE developers.
Plugins may specify compatibility versions more precisely; IDE vendors may have build numbers based on a specific IntelliJ Platform version and specify additional internal version (e.g. `256.42` in `XX-162.94.11.256.42`) to allow plugin developers for their IDE to specify compatibility.

Multi-part build numbers can also be used in the `since-build` and `until-build` attributes of `idea-version`.
Usually you should omit the product ID and use only the branch number and build number, for example:

```xml
<idea-version since-build="94.539"/>
<idea-version since-build="162.539.11"/>
<idea-version until-build="162"/> <!-- any build until 162, not inclusive!-->
<idea-version since-build="162" until-build="162.*"/> <!-- any 162-based version, 162.94, 162.94.11, etc.-->
```

 >  Specific build numbers and their corresponding release version are available via _Previous Releases_ on the corresponding product's download page, e.g. [Previous IntelliJ IDEA Releases](https://www.jetbrains.com/idea/download/previous.html).
 >  See also [What versions of IntelliJ-based IDEs are supported?](https://intellij-support.jetbrains.com/hc/en-us/articles/360019574859-What-versions-of-IntelliJ-based-IDEs-are-supported-) for JetBrains IDE support policy.
 >
 {type="note"}

### IntelliJ Platform Based Products of Recent IDE Versions

 >  Which versions should your plugin support? We've collected some insights based on download statistics in [Statistics: Product Versions in Use](https://plugins.jetbrains.com/docs/marketplace/product-versions-in-use-statistics.html).
 >
 {type="tip"}

| Branch number                                                   | IntelliJ Platform version                                                                                                                |
|-----------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------|
| [213](https://github.com/JetBrains/intellij-community/tree/213) | 2021.3                                                                                                                                   |
| [212](https://github.com/JetBrains/intellij-community/tree/212) | 2021.2                                                                                                                                   |
| [211](https://github.com/JetBrains/intellij-community/tree/211) | 2021.1                                                                                                                                   |
| [203](https://github.com/JetBrains/intellij-community/tree/203) | 2020.3 **NOTE** Java 11 is now required ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)) |
| [202](https://github.com/JetBrains/intellij-community/tree/202) | 2020.2                                                                                                                                   |
| [201](https://github.com/JetBrains/intellij-community/tree/201) | 2020.1                                                                                                                                   |
| [193](https://github.com/JetBrains/intellij-community/tree/193) | 2019.3                                                                                                                                   |
| [192](https://github.com/JetBrains/intellij-community/tree/192) | 2019.2                                                                                                                                   |
| [191](https://github.com/JetBrains/intellij-community/tree/191) | 2019.1                                                                                                                                   |
| [183](https://github.com/JetBrains/intellij-community/tree/183) | 2018.3                                                                                                                                   |
| [182](https://github.com/JetBrains/intellij-community/tree/182) | 2018.2                                                                                                                                   |
| [181](https://github.com/JetBrains/intellij-community/tree/181) | 2018.1                                                                                                                                   |
| [173](https://github.com/JetBrains/intellij-community/tree/173) | 2017.3                                                                                                                                   |
| [172](https://github.com/JetBrains/intellij-community/tree/172) | 2017.2                                                                                                                                   |
| [171](https://github.com/JetBrains/intellij-community/tree/171) | 2017.1                                                                                                                                   |
| [163](https://github.com/JetBrains/intellij-community/tree/163) | 2016.3                                                                                                                                   |
| [162](https://github.com/JetBrains/intellij-community/tree/162) | 2016.2                                                                                                                                   |

Note that there is no `170`.
In the `YYYY.R` versioning scheme, the `R` part starts at 1.

### IntelliJ Platform Based Products of Pre-2016.2 IDE Versions

| Branch number                                                   | Product version                                                                                                                        |
|-----------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------|
| [145](https://github.com/JetBrains/intellij-community/tree/145) | IntelliJ IDEA 2016.1, AppCode 2016.1, CLion 2016.1, DataGrip 2016.1, PhpStorm 2016.1, PyCharm 2016.1, RubyMine 2016.1, WebStorm 2016.1 |
| [143](https://github.com/JetBrains/intellij-community/tree/143) | IntelliJ IDEA 15, AppCode 3.3, CLion 1.2, DataGrip 1.0, PhpStorm 10, PyCharm 5, RubyMine 8, WebStorm 11                                |
| [141](https://github.com/JetBrains/intellij-community/tree/141) | IntelliJ IDEA 14.1, Android Studio 1.3, PyCharm 4.1, WebStorm 10                                                                       |
| [139](https://github.com/JetBrains/intellij-community/tree/139) | IntelliJ IDEA 14, PhpStorm 8, PyCharm 4, RubyMine 7, WebStorm 9                                                                        |
| [135](https://github.com/JetBrains/intellij-community/tree/135) | IntelliJ IDEA 13.1, WebStorm 8                                                                                                         |
| [133](https://github.com/JetBrains/intellij-community/tree/133) | IntelliJ IDEA 13, PhpStorm 7, PyCharm 3.1, WebStorm 7                                                                                  |
| [131](https://github.com/JetBrains/intellij-community/tree/131) | PhpStorm 7, PyCharm 3.0, WebStorm 7                                                                                                    |
| [129](https://github.com/JetBrains/intellij-community/tree/129) | IntelliJ IDEA 12.1, bug-fix updates for PyCharm 2.7 and PhpStorm/WebStorm 6.0                                                          |
| [127](https://github.com/JetBrains/intellij-community/tree/127) | AppCode 2.0, PhpStorm/WebStorm 6.0                                                                                                     |
| [125](https://github.com/JetBrains/intellij-community/tree/125) | PyCharm 2.7, RubyMine 5.0                                                                                                              |
| [123](https://github.com/JetBrains/intellij-community/tree/123) | IntelliJ IDEA 12                                                                                                                       |
| [121](https://github.com/JetBrains/intellij-community/tree/121) | AppCode 1.6, PyCharm 2.6, PhpStorm/WebStorm 5.0                                                                                        |
| [119](https://github.com/JetBrains/intellij-community/tree/119) | RubyMine 4.5                                                                                                                           |
| [117](https://github.com/JetBrains/intellij-community/tree/117) | IntelliJ IDEA 11.1, AppCode 1.5, PyCharm 2.5, RubyMine 4.0.x, PhpStorm/WebStorm 4.0                                                    |
| 111                                                             | IntelliJ IDEA 11.0                                                                                                                     |
| 107                                                             | IntelliJ IDEA 10.5                                                                                                                     |
| 103                                                             | IntelliJ IDEA 10.0.2+                                                                                                                  |
| 99                                                              | IntelliJ IDEA 10.0+                                                                                                                    |
| 95                                                              | IntelliJ IDEA 9.0.2+                                                                                                                   |
| 93                                                              | IntelliJ IDEA 9.0                                                                                                                      |
