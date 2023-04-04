<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Trusted Project

<link-summary>Preventing executing malicious code in untrusted projects.</link-summary>

<tldr>

**Product Help:** [Project Security](https://www.jetbrains.com/help/idea/project-security.html)

</tldr>

> This API is available in platform releases 2021.2.4/2021.3.1 and later.
>

When a project is opened in the IDE for the first time, the user will be asked whether they trust the project or not.
If the user chooses to preview the project in the safe mode, no potentially dangerous feature can be executed automatically or unexpectedly.

A plugin can check whether the project is trusted via the Kotlin extension method `Project.isTrusted()` or from Java via static method [`TrustedProjects.isTrusted(Project)`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt).

A project opened in the safe mode can become trusted afterward: either the user can click on the <control>Trust Project</control> link in the notification panel shown on top of the editor,
or the state can be changed programmatically, for instance, if the user invokes a dangerous action, and that action proposes to switch to the Trusted mode.

Therefore, a plugin can subscribe to changes of the trusted state via [application-level listener](plugin_listeners.md#defining-application-level-listeners) [`TrustStateListener`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) to switch on a feature that was disabled in the safe mode.
To do it the plugin should implement `TrustStateListener.onProjectTrusted()`.
Or better, use one of [`TrustedProjects.whenProjectTrusted()`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/impl/TrustedProjects.kt) helper methods accepting a lambda, one for Kotlin and another for Java.

## Is the feature dangerous?

Suppose the feature can potentially execute malicious code, and it is not obvious that this code is going to be executed.
In that case, this feature has to be disabled in the safe mode, and enabling it has to be protected via a [confirmation](misc_swing_components.md#messages).

Samples:
* It is not obvious that opening a folder in the IDE can execute Gradle build script, which in turn can call a malicious code located inside the project => the Gradle import is disabled in the safe mode.
* It is obvious that running or debugging the source code can execute malicious code => it is not necessary to wrap this action with a confirmation.
