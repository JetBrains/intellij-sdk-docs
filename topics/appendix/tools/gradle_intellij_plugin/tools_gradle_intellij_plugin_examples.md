<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Gradle IntelliJ Plugin (1.x) – Usage Examples
<primary-label ref="Obsolete"/>

<link-summary>Usage Examples of plugins using Gradle IntelliJ Plugin (1.x).</link-summary>

<include from="tools_gradle_intellij_plugin.md" element-id="gradlePluginObsolete"/>

The JetBrains Marketplace platform provides the [IntelliJ Platform Explorer](https://jb.gg/ipe) – a search tool for browsing Extension Points inside existing implementations of open-source IntelliJ Platform plugins.

One of its features is the possibility of filtering the plugins by those that utilize [Gradle](https://jb.gg/ipe?buildSystem=gradle) or [Gradle KTS](https://jb.gg/ipe?buildSystem=gradle_kts) build scripts.

As examples of using this plugin, you can also check out the following projects:

- [Erlang plugin](https://github.com/ignatov/intellij-erlang) and its [TeamCity build configuration](https://teamcity.jetbrains.com/project.html?projectId=IntellijIdeaPlugins_Erlang&tab=projectOverview)
- [Perl5 plugin](https://github.com/hurricup/Perl5-IDEA)
- [Bamboo Soy plugin](https://github.com/google/bamboo-soy) and its [Travis configuration file](https://github.com/google/bamboo-soy/blob/master/.travis.yml)
- [AceJump plugin](https://github.com/johnlindquist/AceJump)
- [EmberJS plugin](https://github.com/Turbo87/intellij-emberjs)
- [Robot plugin](https://github.com/AmailP/robot-plugin)
- [SQLDelight Android Studio Plugin](https://github.com/square/sqldelight/tree/master/sqldelight-idea-plugin)
- [idear plugin](https://github.com/breandan/idear)
- [SonarLint plugin](https://github.com/SonarSource/sonar-intellij)
- [IdeaVim plugin](https://github.com/JetBrains/ideavim) and its [TeamCity build configuration](https://teamcity.jetbrains.com/project.html?projectId=IdeaVim&guest=1)
- [Adb Idea](https://github.com/pbreault/adb-idea) is configured to build and run against stable, beta or preview (canary) releases of Android Studio
- [Gerrit](https://github.com/uwolfer/gerrit-intellij-plugin) uses Travis CI inclusive automated publishing of releases to GitHub and JetBrains plugin repository (triggered by version tag creation)
- [.ignore](https://github.com/JetBrains/idea-gitignore)
- [Minecraft Development](https://github.com/minecraft-dev/MinecraftDev)
    - Mixes Java, Kotlin, and Groovy code
    - Uses [Grammar-Kit]
    - Uses a Kotlin version not bundled with IntelliJ IDEA
- [Unity 3D plugin](https://github.com/JetBrains/resharper-unity) for JetBrains Rider
- [AEM Tools plugin](https://github.com/aemtools/aemtools) for Adobe Experience Manager integration
    - Fully written in Kotlin
    - Uses template language
- [F# plugin](https://github.com/JetBrains/resharper-fsharp/tree/net222/rider-fsharp) for JetBrains Rider
- [Rainbow Brackets](https://github.com/izhangzhihao/intellij-rainbow-brackets)
    - Fully written in Kotlin
    - Uses other IntelliJ IDEA plugins as test dependencies
    - Circle CI configuration file & Travis CI configuration file
    - Auto submit anonymous feedback as GitHub issues
- [Requirements](https://github.com/meanmail-dev/requirements)
    - Fully written in Kotlin
    - Uses other IntelliJ IDEA plugins as test dependencies
    - Uses [Grammar-Kit]
    - Uses a Kotlin version not bundled with IntelliJ IDEA
- [EduTools](https://github.com/JetBrains/educational-plugin)
    - Mixes Java and Kotlin code

[Grammar-Kit]: https://github.com/JetBrains/Grammar-Kit
