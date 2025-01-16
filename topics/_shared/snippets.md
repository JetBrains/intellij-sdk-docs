<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Snippets
{is-library="true"}

<snippet id="missingContent">

> If a topic you are interested in is not covered in the above sections, let us know via the **Was this page helpful?** feedback form below or [other channels](getting_help.topic#problems-with-the-guide).
>
> Please be specific about the topics and reasons for adding them, and leave your email in case we need more details. Thanks for your feedback!
>
{title="Something missing?" style="note"}

</snippet>

<snippet id="subscribeNews">

> [Subscribe to Marketplace Developer News](https://jb.gg/mp-updates) to receive news and announcements.
> Also follow [JBPlatform](https://x.com/JBPlatform/) on X (formerly Twitter) and visit
> [JetBrains Platform Blog](https://blog.jetbrains.com/platform/) and
> [JetBrains Marketplace on LinkedIn](https://www.linkedin.com/showcase/jetbrains-marketplace/).
>
{title="Staying up to date"}

</snippet>

<snippet id="pluginDevKitAvailability">

_Plugin DevKit_ plugin is bundled with IntelliJ IDEA until 2023.2.

> When using IntelliJ IDEA 2023.3 or later, the _Plugin DevKit_ plugin must be installed from JetBrains Marketplace ([Plugin Homepage](https://plugins.jetbrains.com/plugin/22851-plugin-devkit))
> as it is no longer bundled with the IDE.
>
{title="Plugin DevKit Availability" style="warning"}

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

> Changes from API marked with `@Deprecated(forRemoval=true)` or any of [`ApiStatus`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java) `@Experimental`, `@ScheduledForRemoval`, or `@Internal` are not listed here, as incompatible changes are to be expected.
>
> For API annotated with `@ApiStatus.Internal`/`@IntellijInternalApi`, see [](api_internal.md) for more details and replacements.
>
{title="Non-listed changes" style="note"}

</snippet>

<snippet id="apiChangesJavaVersion">


>
> Java version must be set depending on the target platform version.
>
> **2024.2+**: Java 21
>
> **2022.3+**: Java 17 ([blog post](https://blog.jetbrains.com/platform/2022/08/intellij-project-migrates-to-java-17/))
>
> **2020.3+**: Java 11 ([blog post](https://blog.jetbrains.com/platform/2020/09/intellij-project-migrates-to-java-11/))
>
{title="IDE and Java Versions" style="note"}

</snippet>

<snippet id="gradlePluginVersion">

> The Gradle plugin must be chosen depending on the target [platform version](build_number_ranges.md#platformVersions).
>
> 2024.2+
> : **Requires** [](tools_intellij_platform_gradle_plugin.md)
>
> 2022.3+
> :
> **Recommended** [](tools_intellij_platform_gradle_plugin.md),<br/>
> **Requires** [](tools_gradle_intellij_plugin.md) version 1.10.1+ (current: %gradle-intellij-plugin-version%)
>
{style="warning" title="Gradle Plugin"}

</snippet>

<snippet id="ep_list_legend">

## Legend

> See also [](explore_api.md) for more information and strategies.

### Listeners

**Topic** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Listener** links corresponding listener to implement.

See [](plugin_listeners.md) on how to register listeners.

### Extension Points

**Extension Point** searches for usages inside existing implementations of open-source IntelliJ Platform plugins via [IntelliJ Platform Explorer](https://jb.gg/ipe).

**Implementation** is the related Extension Point class.

See [](plugin_extensions.md) on how to declare extensions in your plugin.

### Tags

See [](verifying_plugin_compatibility.md) for overview of API status.

| Icon                                                            | Description                               | Details                                                                                                                                                                                                                                                                                                        |
|-----------------------------------------------------------------|-------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ![Deprecated][deprecated]                                       | Deprecated API                            | See code documentation for replacement.                                                                                                                                                                                                                                                                        |
| ![Removal][removal]                                             | Scheduled for Removal API                 | See code documentation for replacement.                                                                                                                                                                                                                                                                        |
| ![Obsolete][obsolete]                                           | Obsolete API                              | Do not use in new code.<p>See code documentation for replacement ([](verifying_plugin_compatibility.md#obsolete-api)).</p>                                                                                                                                                                                     |
| ![Experimental API][experimental]&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; | Experimental API                          | Annotated with [`@ApiStatus.Experimental`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java).<p>API might be altered or removed without prior notice.</p>                                                                                                                   |
| ![Internal API][internal]                                       | Internal API                              | Annotated with [`@ApiStatus.Internal`](%gh-java-annotations%/common/src/main/java/org/jetbrains/annotations/ApiStatus.java).<p>Must not be used by third party plugins, see [](api_internal.md).</p>                                                                                                           |
| ![Project-Level][project-level]                                 | Project-Level<p>Extension Point/Topic</p> | <p>Can have [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java) as constructor parameter.</p><p>- Extension Point: Declared with `area="IDEA_PROJECT"`</p><p>- Listener: registered in [`<projectListeners>`](plugin_configuration_file.md#idea-plugin__projectListeners)</p> |
| ![Non-Dynamic][non-dynamic]                                     | Non-Dynamic<p>Extension Point</p>         | Installation/update of plugin requires IDE restart ([](dynamic_plugins.md)).                                                                                                                                                                                                                                   |
| ![DumbAware][dumb-aware]                                        | `DumbAware`<p>Extension Point</p>         | Implementations marked with [`DumbAware`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/DumbAware.java) will be processed during [dumb mode](indexing_and_psi_stubs.md#dumb-mode).                                                                                                                |

[deprecated]: https://img.shields.io/badge/-Deprecated-lightgrey?style=flat-square
[removal]: https://img.shields.io/badge/-Removal-red?style=flat-square
[obsolete]: https://img.shields.io/badge/-Obsolete-grey?style=flat-square
[experimental]: https://img.shields.io/badge/-Experimental-violet?style=flat-square
[internal]: https://img.shields.io/badge/-Internal-darkred?style=flat-square
[project-level]: https://img.shields.io/badge/-Project--Level-blue?style=flat-square
[non-dynamic]: https://img.shields.io/badge/-Non--Dynamic-orange?style=flat-square
[dumb-aware]: https://img.shields.io/badge/-DumbAware-darkgreen?style=flat-square

</snippet>

<snippet id="descriptorDocumentationProviderNote">

> Elements described on this page are available in [quick documentation](https://www.jetbrains.com/help/idea/viewing-reference-information.html#inline-quick-documentation) since IntelliJ IDEA 2025.1.
>
> The [Plugin DevKit](https://plugins.jetbrains.com/plugin/22851-plugin-devkit) plugin must be installed and enabled.
>
{style="note"}

</snippet>
