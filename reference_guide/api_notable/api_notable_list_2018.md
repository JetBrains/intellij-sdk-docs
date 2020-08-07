---
title: Notable Changes in IntelliJ Platform and Plugins API 2018.*
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## 2018.3 

### Notable Changes in IntelliJ Platform 2018.3

ASM Library 7.0
: Updated to 7.0 [Issue](https://youtrack.jetbrains.com/issue/IDEA-191331).

Extendable Registry keys
: Plugins can contribute new keys to _Registry_ dialog via EP `com.intellij.registryKey`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-177378) 

Editor: content between text lines
: Ability to add arbitrary content (preview, debugger information, etc.). [Issue](https://youtrack.jetbrains.com/issue/IDEA-183815)

## 2018.2 

### Notable Changes in IntelliJ Platform 2018.2

_Run Anything_ (**Double CTRL**)
: Allows executing predefined activities using a popup with smart completion, e.g. [Gradle Tasks](https://www.jetbrains.com/help/idea/gradle.html#gradle_tasks), executing Run Configurations, ... see [`RunAnythingProvider`](upsource:///platform/lang-impl/src/com/intellij/ide/actions/runAnything/activity/RunAnythingProvider.java)

JPS: Report exception in IDE
: Ability to report exceptions from build process. [Issue](https://youtrack.jetbrains.com/issue/IDEA-187115)
