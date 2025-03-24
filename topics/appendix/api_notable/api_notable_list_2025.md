<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2025.*

<link-summary>List of known Notable API Changes in 2025.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2025.2

### IntelliJ Platform 2025.2

## 2025.1

### IntelliJ Platform 2025.1

[`ContainerUtil`](%gh-ic%/platform/util/src/com/intellij/util/containers/ContainerUtil.java) using unmodifiable collections
: Methods marked with [`@Unmodifiable`](%gh-java-annotations%/java8/src/main/java/org/jetbrains/annotations/Unmodifiable.java) now really return unmodifiable collections (only in [internal](enabling_internal.md)/test mode for now).
