[//]: # (title: Required Experience)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

The IntelliJ Platform is a JVM application, implemented mostly in Java and [Kotlin](https://kotlinlang.org).
At this time, it's not possible to develop plugins for the IntelliJ Platform in non-JVM languages.
Developing a plugin for the IntelliJ Platform requires knowledge and experience with the following technologies and concepts:
- Java, Kotlin, or other JVM language, and its standard and 3rd-party libraries
- [Gradle](https://gradle.org/) or a similar build system (e.g., Maven)
- [Swing](https://en.wikipedia.org/wiki/Swing_(Java)) for building user interfaces
- [Java Concurrency Model](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html)
- experience with IntelliJ Platform-based IDE (e.g., [IntelliJ IDEA](https://www.jetbrains.com/idea/))

Please keep in mind that the IntelliJ Platform is a large project, and while we are doing our best to cover as many topics as possible, it is not possible to include every feature and use-case in the documentation.
Developing a plugin will sometimes require digging into the [Platform code](https://github.com/JetBrains/intellij-community) and analyzing the [example implementations in other plugins](https://jb.gg/ipe).

It's highly recommended to get familiar with the [](explore_api.md) section before you start the plugin implementation.


> In some cases, implementing an actual IntelliJ Platform plugin might not be necessary, as [alternative solutions](plugin_alternatives.md) exist.
>
{type="tip"}
