<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Publishing a Plugin

<link-summary>Publishing a plugin to JetBrains Marketplace manually or with the Gradle plugin.</link-summary>

When a plugin is ready, it can be published to the [JetBrains Marketplace](https://plugins.jetbrains.com) plugin repository so that other users can install it in their IDE.

> When publishing a plugin to a repository _other than_ the [JetBrains Marketplace](https://plugins.jetbrains.com), refer to the [](custom_plugin_repository.md) documentation.

The first plugin publication must always be [uploaded manually](#uploading-a-plugin-to-jetbrains-marketplace).

<procedure title="Before Publishing Checklist">

Before publishing a plugin, make sure it:

- follows all recommendations from [](plugin_user_experience.md)
- follows all requirements from [Plugin Overview page](https://plugins.jetbrains.com/docs/marketplace/best-practices-for-listing.html)

The webinar _Busy Plugin Developers. Episode 2_ discusses [5 tips for optimizing JetBrains Marketplace plugin page](https://youtu.be/oB1GA9JeeiY?t=52) in more detail.

See also [](marketing.md) about widgets and badges.

</procedure>

<include from="plugin_content.md" element-id="doNotRepackageLibraries"/>

## Uploading a Plugin to JetBrains Marketplace

Before publishing a plugin, make sure it is signed.
For more details on generating a proper certificate and configuring the signing process, check the [Plugin Signing](plugin_signing.md) article.

<procedure title="Creating a JetBrains Account">

To upload a plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com), log in with your personal JetBrains Account.

1. Open the [JetBrains Account Center](https://account.jetbrains.com) and click <control>Create Account</control>.
2. Fill in all fields in the <control>Create JetBrains Account</control> form that opens and click <control>Register</control>.

</procedure>

<procedure title="Uploading plugin">

To upload a plugin to [JetBrains Marketplace](https://plugins.jetbrains.com):

1. [Log in to JetBrains Marketplace](https://plugins.jetbrains.com/author/me) with your personal JetBrains account.
2. On the Profile page that opens, click <control>Add new plugin</control>.
3. Fill in the <control>Add new plugin</control> form that opens and click the <control>Add the plugin</control> button to upload the plugin.

See also [Marketplace Docs](https://plugins.jetbrains.com/docs/marketplace/uploading-a-new-plugin.html).

</procedure>

### Uploading a New Version

New versions can be uploaded manually on the plugin's detail page, see [Marketplace Docs](https://plugins.jetbrains.com/docs/marketplace/plugin-updates.html) for details.
See [](#deploying-a-plugin-with-gradle) on how to publish new versions using Gradle.

## Publishing Plugin With Gradle

Once [Gradle support](creating_plugin_project.md) has been configured, and the plugin has been [uploaded manually](#uploading-a-plugin-to-jetbrains-marketplace) to the plugin repository at least once,
it can be built and deployed to the [JetBrains Marketplace](https://plugins.jetbrains.com) automatically using dedicated Gradle tasks.

> In the following sections
> - **2.x** refers to [](tools_intellij_platform_gradle_plugin.md)
> - **1.x** refers to [](tools_gradle_intellij_plugin.md)
>

### Building Distribution

For the initial upload, manual distribution, or local installation, invoke the `buildPlugin` Gradle task
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#buildPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-buildplugin)) to create the plugin distribution.
If the project is configured to rely on [](plugin_signing.md), use the `signPlugin` task instead
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin)).

The resulting ZIP file is located in <path>build/distributions</path> and can then be installed in the IDE via [<ui-path>Install Plugin from Disk...</ui-path>](https://www.jetbrains.com/help/idea/managing-plugins.html#install_plugin_from_disk) action
or uploaded to a [](custom_plugin_repository.md).

### Providing Your Personal Access Token to Gradle

To deploy a plugin to the JetBrains Marketplace, supply the Personal Access Token, which can be found on your profile page in the [My Tokens](https://plugins.jetbrains.com/author/me/tokens) section.

To create a new token, provide its name and click the <control>Generate Token</control> button.
A new token will be created and displayed right below.

> Copy it before closing this page and keep it in a secure location.
> This is the only time the token is visible.
>
{style="warning"}

This section describes two options to supply the _Personal Access Token_ via Gradle using:

* Environment variables,
* Parameters to the Gradle task.

#### Using Environment Variables

Start by defining an environment variable such as:

```bash
export ORG_GRADLE_PROJECT_intellijPlatformPublishingToken='YOUR_TOKEN'
```

> On macOS systems, environment variables set in <path>.bash_profile</path> are only visible to processes run from bash.
> Environment variables visible to all processes need to be defined in [Environment.plist](https://developer.apple.com/library/archive/qa/qa1067/_index.html).
>
{style="note" title="macOS Note"}

Now provide the environment variable in the run configuration for running the `publishPlugin` task locally
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-publishplugin)).
To do so, create a Gradle run configuration (if not already done), select the Gradle project, specify the
`publishPlugin` task, and then add the environment variable.

<tabs group="gradlePluginVersion">

<tab title="IntelliJ Platform Gradle Plugin (2.x)" group-key="2.x">

```kotlin
intellijPlatform {
  publishing {
    token = providers.gradleProperty("intellijPlatformPublishingToken")
  }
}
```

</tab>

<tab title="Gradle IntelliJ Plugin (1.x)" group-key="1.x">

```kotlin
tasks {
  publishPlugin {
    token = providers.gradleProperty("intellijPlatformPublishingToken")
  }
}
```

</tab>
</tabs>

Note that it's still required to put some default values (can be empty) in the Gradle properties. Otherwise, there can be a compilation error.

#### Using Parameters for the Gradle Task

Like using environment variables, the token can also be passed as a parameter to the Gradle task.
For example, provide the parameter on the command line or by putting it in the arguments of the Gradle run configuration.

```bash
-PintellijPlatformPublishingToken=YOUR_TOKEN
```

Note that in this case also, it's still required to put some default values (can be empty) in the Gradle properties

### Deploying a Plugin with Gradle

The first step when deploying a plugin is to confirm that it works correctly.
Verify this by [installing the plugin from disk](https://www.jetbrains.com/help/idea/managing-plugins.html) in a fresh instance of the target IDE(s).

#### Signing a Plugin

The Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
The `signPlugin` Gradle task
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#signPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-signplugin)),
will be executed automatically right before the `publishPlugin` task
(Reference: [2.x](tools_intellij_platform_gradle_plugin_tasks.md#publishPlugin), [1.x](tools_gradle_intellij_plugin.md#tasks-publishplugin)).

For more details on generating a proper certificate and configuring the `signPlugin` task, see [](plugin_signing.md).

#### Publishing a Plugin

Once the plugin works as intended, make sure the plugin version is updated, as the JetBrains Marketplace won't accept multiple artifacts with the same version.

To deploy a new version of the plugin to the JetBrains Marketplace, invoke the `publishPlugin` Gradle task.

Now check the most recent version of the plugin on the [JetBrains Marketplace](https://plugins.jetbrains.com/).
If successfully deployed, any users who currently have this plugin installed on an available version of the IntelliJ Platform are notified of a new update available as soon as the update has been verified.

### Specifying a Release Channel

<tabs group="gradlePluginVersion">

<tab title="IntelliJ Platform Gradle Plugin (2.x)" group-key="2.x">

It's possible to deploy plugins to a chosen release channel by configuring the [`intellijPlatform.publishing.channels`](tools_intellij_platform_gradle_plugin_extension.md#intellijPlatform-publishing-channels) extension property.


<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
intellijPlatform {
  publishing {
    channels = listOf("beta")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
intellijPlatform {
  publishing {
    channels = ['beta']
  }
}
```

</tab>
</tabs>

</tab>
<tab title="Gradle IntelliJ Plugin (1.x)" group-key="1.x">

It's possible to deploy plugins to a chosen release channel by configuring the [`publishPlugin.channels`](tools_gradle_intellij_plugin.md#tasks-publishplugin-channels) task property.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
tasks {
  publishPlugin {
    channels = listOf("beta")
  }
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
tasks {
  publishPlugin {
    channels = ['beta']
  }
}
```

</tab>
</tabs>

</tab>
</tabs>

When empty, this uses the default plugin repository, available to all [JetBrains Marketplace](https://plugins.jetbrains.com/) users.
However, it's possible to publish it to an arbitrarily named channel.
These non-default release channels are treated as separate repositories.

When using a non-default release channel, users need to configure a new [custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) in their IDE to install the plugin.

For example, when specifying `'canary'` as channel name, users will need to add the `https://plugins.jetbrains.com/plugins/canary/list` repository to install the plugin and receive updates.

Popular channel names include:

* `alpha`: https://plugins.jetbrains.com/plugins/alpha/list
* `beta`: https://plugins.jetbrains.com/plugins/beta/list
* `eap`: https://plugins.jetbrains.com/plugins/eap/list
