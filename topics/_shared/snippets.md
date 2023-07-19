<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Snippets
{is-library="true"}

<snippet id="missingContent">

> If a topic you are interested in is not covered in the above sections, let us know via the **Was this page helpful?** feedback form below or [other channels](getting_help.topic#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details.
>
{title="Something missing?" style="note"}

</snippet>

<snippet id="subscribeNews">

> [Subscribe to Marketplace Developer News](https://jb.gg/mp-updates) to receive news and announcements.
> Also follow [JBPlatform](https://twitter.com/JBPlatform/) on Twitter and visit [JetBrains Platform Blog](https://blog.jetbrains.com/platform/).
>
{title="Staying up to date"}

</snippet>

<snippet id="jetbrainsProductOpenSourceLicense">

> Qualifying Open Source projects can [apply for free licenses](https://www.jetbrains.com/community/opensource/) of JetBrains products.

</snippet>

<snippet id="jetbrainsIDE_TLDR">

<tldr>

**IDE**: [Homepage](https://www.jetbrains.com/%productID%), [Versions](https://www.jetbrains.com/%productID%/download/other.html)

**Plugins**: [JetBrains Marketplace](https://plugins.jetbrains.com/%marketplaceProductID%)

</tldr>

</snippet>

<snippet id="apiChangesHeader">

Please see [](verifying_plugin_compatibility.md) on how to use Plugin Verifier and IDE inspections to check such problems.

_Early Access Program_ (EAP) releases of upcoming versions are available [here](https://eap.jetbrains.com).

> Changes from API marked with `@Deprecated(forRemoval=true)` or any of [`org.jetbrains.annotations.ApiStatus`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Experimental`, `@ScheduledForRemoval`, or `@Internal` are not listed here, as incompatible changes are to be expected.
>
> For API annotated with `ApiStatus.@Internal`/`@IntellijInternalApi`, see [](api_internal.md) for more details and replacements.
>
{title="Non-listed changes" style="note"}

</snippet>

<snippet id="apiChangesJavaVersion">

> Java 17 is required ([blog post](https://blog.jetbrains.com/platform/2022/08/intellij-project-migrates-to-java-17/)) when targeting 2022.2 or later only.
>
> Java 11 is required ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/)) when targeting 2020.3 and later only.
>
> Please make sure to always upgrade `gradle-intellij-plugin` to the latest version [%gradle-intellij-plugin-version%](https://github.com/jetbrains/gradle-intellij-plugin/releases)
>
{style="note"}

</snippet>

<snippet id="ep_list_legend">

> See [](plugin_extensions.md) on how to declare extensions in your plugin.
>
> See [](plugin_listeners.md) on how to register listeners.
>

## Legend

### Listeners

**Topic** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Listener** links corresponding listener to implement.

### Extension Points

**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is related Extension Point class.

### Tags

See [](verifying_plugin_compatibility.md) for overview of API status.

| Icon                              | Description                         | Details                                                                                                                                                                                                                                                                                                   |
|-----------------------------------|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![Deprecated][deprecated]         | Deprecated API                      | Please see code documentation for replacement                                                                                                                                                                                                                                                             |
| ![Removal][removal]               | Scheduled for Removal API           | Please see code documentation for replacement                                                                                                                                                                                                                                                             |
| ![Obsolete][obsolete]             | Obsolete API                        | Do not use in new code, please see code documentation for replacement ([](verifying_plugin_compatibility.md#obsolete-api))                                                                                                                                                                                |
| ![Experimental API][experimental] | Experimental API                    | Annotated with [`@ApiStatus.Experimental`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), API might be altered or removed without prior notice                                                                                 |
| ![Internal API][internal]         | Internal API                        | Annotated with [`@ApiStatus.Internal`](https://github.com/JetBrains/java-annotations/blob/master/common/src/main/java/org/jetbrains/annotations/ApiStatus.java), must not be used by 3rd party, see [](api_internal.md)                                                                                   |
| ![Project-Level][project-level]   | Project-Level Extension Point/Topic | <p>Can have [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java) as constructor parameter</p><p>Extension Point: Declared with `area="IDEA_PROJECT"`</p><p>Listener: registered in [`<projectListeners>`](plugin_configuration_file.md#idea-plugin__projectListeners)</p> |
| ![Non-Dynamic][non-dynamic]       | Non-Dynamic Extension Point         | Installation/update of plugin requires IDE restart ([Dynamic Plugins](dynamic_plugins.md))                                                                                                                                                                                                                |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
</snippet>
