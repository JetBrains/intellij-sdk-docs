---
title: Notable Changes in IntelliJ Platform and Plugins API 2019.*
---

## Notable Changes in IntelliJ Platform 2019.2

`com.intellij.util.Query.forEach`
: Defaults to thread-safe to prevent problems with clients using unsynchronized collections.

## Notable Changes in IntelliJ Platform 2019.1

`@org.jetbrains.annotations.ApiStatus.AvailableSince`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically (replacing `@since` Javadoc).

`@org.jetbrains.annotations.ApiStatus.ScheduledForRemoval`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically. This allows highlighting of API which has been removed in newer platform versions.

`PsiReferenceProvider` assert underlying element
: Assert references are created for the given underlying `PsiElement`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-203954)

`CachedValue` more strict assertions
: Enabled in tests and EAP/internal mode, see `CachedValueStabilityChecker` Javadoc.