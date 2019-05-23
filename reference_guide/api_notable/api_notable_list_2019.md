---
title: Notable Changes in IntelliJ Platform and Plugins API 2019.*
---

# 2019.2 

## Notable Changes in IntelliJ Platform 2019.2

Unbundled plugins in IntelliJ IDEA
: Several plugins for no longer actively maintained technology have been moved to a [separate repository](https://github.com/JetBrains/intellij-obsolete-plugins/). If your plugin depends on them, users will need to install them from the [JetBrains plugin repository](https://plugins.jetbrains.com).

`@org.jetbrains.annotations.ApiStatus.NonExtendable`
: Indicates that the annotated API class, interface or method must not be extended, implemented or overridden by external plugins but can be only obtained or instantiated (for classes and interfaces), or called (for methods).

`@org.jetbrains.annotations.ApiStatus.OverrideOnly`
: Indicates that the annotated method is part of SPI (Service Provider Interface), which is intended to be only implemented or overridden but never called by external plugins.

`com.intellij.util.Query.forEach`
: Defaults to thread-safe to prevent problems with clients using unsynchronized collections.

`com.intellij.openapi.projectRoots.SdkType#getInvalidHomeMessage`
: Returns dedicated message when invalid SDK path was chosen (e.g., JRE instead of JDK). 


# 2019.1 

## Notable Changes in IntelliJ Platform 2019.1

`@org.jetbrains.annotations.ApiStatus.AvailableSince`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically (replacing `@since` Javadoc).

`@org.jetbrains.annotations.ApiStatus.ScheduledForRemoval`
: External annotations for IntelliJ Platform are generated and attached to plugin projects automatically. This allows highlighting of API which has been removed in newer platform versions.

`@org.jetbrains.annotations.ApiStatus.Internal`
: Indicates that the annotated element must not be considered as a public API. Do not use outside of IntelliJ Platform. [Issue](https://youtrack.jetbrains.com/issue/IDEA-211175)

`PsiReferenceProvider` assert underlying element
: Assert references are created for the given underlying `PsiElement`. [Issue](https://youtrack.jetbrains.com/issue/IDEA-203954)

`CachedValue` more strict assertions
: Enabled in tests and EAP/internal mode, see `CachedValueStabilityChecker` Javadoc.
