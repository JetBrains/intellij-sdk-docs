<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Notable Changes in IntelliJ Platform and Plugins API 2025.*

<link-summary>List of known Notable API Changes in 2025.*</link-summary>

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

<include from="snippets.topic" element-id="gradlePluginVersion"/>

## 2025.3

### IntelliJ Platform 2025.3

Search Everywhere: New API compatible with Remote Development
: The Search Everywhere architecture has been redesigned to support remote development by separating data/logic from UI presentation, enabling results to be serialized and transmitted across process boundaries.
  New API components:
- [`SeItemsProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProvider.kt) replaces `SearchEverywhereContributor` as the main interface for providing search results. It can run on both backend and frontend, and returns items with serializable presentation data.
- [`SeItemsProviderFactory`](%gh-ic%/platform/searchEverywhere/shared/src/SeItemsProviderFactory.kt) is the new extension point for registering result sources.
- [`SeTab`](%gh-ic%/platform/searchEverywhere/frontend/src/SeTab.kt) and [`SeTabFactory`](%gh-ic%/platform/searchEverywhere/frontend/src/SeTabFactory.kt) represent frontend-only tab abstractions for the Search Everywhere dialog.
- [`SeLegacyItemPresentationProvider`](%gh-ic%/platform/searchEverywhere/shared/src/SeLegacyItemPresentationProvider.kt) provides a temporary adapter for migrating existing `SearchEverywhereContributor` implementations.

  Existing plugins continue working in local (monolith) environments via adapters. The new API is enabled by default in remote development. Migration is recommended now; the old API will be deprecated in 2026.2.
  See also [blog post](https://blog.jetbrains.com/platform/2025/12/major-architectural-update-introducing-the-new-search-everywhere-api-built-for-remote-development/) for details.

## 2025.2

### IntelliJ Platform 2025.2

## 2025.1

### IntelliJ Platform 2025.1

[`ContainerUtil`](%gh-ic%/platform/util/src/com/intellij/util/containers/ContainerUtil.java) using unmodifiable collections
: Methods marked with [`@Unmodifiable`](%gh-java-annotations%/java8/src/main/java/org/jetbrains/annotations/Unmodifiable.java) now really return unmodifiable collections (only in [internal](enabling_internal.md)/test mode for now).
