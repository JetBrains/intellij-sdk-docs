# Publishing a Plugin

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Publishing a plugin to JetBrains Marketplace manually or with Gradle IntelliJ Plugin.</link-summary>

When your plugin is ready, you can publish it to a [JetBrains Marketplace](https://plugins.jetbrains.com) plugin repository so that other users can install it in IDE.
The first plugin publication, even when a project uses the Gradle setup, must be [uploaded manually](#uploading-a-plugin-to-jetbrains-marketplace).

Before publishing a plugin, ensure it follows all recommendations from [](plugin_user_experience.md).
For an optimal presentation, see the guidelines from [Plugin Overview page](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html).
The _Busy Plugin Developers. Episode 2_ discusses [5 tips for optimizing JetBrains Marketplace plugin page](https://youtu.be/oB1GA9JeeiY?t=52) in more detail.
See also [](marketing.md) about widgets and badges.

> If you plan to publish your plugin to a repository _other than_ the [JetBrains Marketplace](https://plugins.jetbrains.com), please refer to the [](custom_plugin_repository.md) documentation.

> If your plugin ships with [additional libraries](plugin_content.md#plugin-with-dependencies), do not repackage them into the main plugin archive.
> Otherwise, [Plugin Verifier](verifying_plugin_compatibility.md) will yield false positives for unresolved classes and methods.
>
{style="warning"}

## Uploading a Plugin to JetBrains Marketplace

Before publishing your plugin, make sure it is signed.
For more details on generating a proper certificate and configuring the signing process, check the [Plugin Signing](plugin_signing.md) article.

<procedure title="Creating JetBrains Account">

To upload your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com), you must log in with your personal JetBrains Account.

1. Open the [JetBrains Account Center](https://account.jetbrains.com) and click <control>Create Account</control>.
2. Fill in all fields in the <control>Create JetBrains Account</control> form that opens and click <control>Register</control>.

</procedure>

<procedure title="Uploading plugin">

To upload your plugin to JetBrains Marketplace:

1. [Log in to JetBrains Marketplace](https://plugins.jetbrains.com/author/me) with your personal JetBrains account.
2. On your Profile page that opens, click <control>Add new plugin</control>.
3. Fill in the <control>Add new plugin</control> form that opens and click the <control>Add the plugin</control> button to upload your plugin.

See also [Marketplace Docs](https://plugins.jetbrains.com/docs/marketplace/uploading-a-new-plugin.html).

</procedure>

### Uploading a New Version

New versions can be uploaded manually on the plugin's detail page, see [Marketplace Docs](https://plugins.jetbrains.com/docs/marketplace/plugin-updates.html) for details.

## Publishing Plugin With Gradle

Once you have [configured Gradle support](configuring_plugin_project.md), and [uploaded the plugin](#uploading-a-plugin-to-jetbrains-marketplace) to the plugin repository at least once, you can automatically build and deploy your plugin to the [JetBrains Marketplace](https://plugins.jetbrains.com).

### Building Distribution

For initial upload, manual distribution or local installation, invoke the [`buildPlugin`](tools_gradle_intellij_plugin.md#tasks-buildplugin) Gradle task to create the plugin distribution.
If you configured you project to rely on [](plugin_signing.md), use the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task instead.

The resulting ZIP file is located in <path>build/distributions</path> and can then be installed via drag & drop (or using [plugin manager](https://www.jetbrains.com/help/idea/managing-plugins.html#installing-plugins-from-disk))
or uploaded to a [](custom_plugin_repository.md).

### Providing Your Personal Access Token to Gradle

To deploy a plugin to the JetBrains Marketplace, you need to supply your Personal Access Token, which you can find on your profile page, in [My Tokens](https://plugins.jetbrains.com/author/me/tokens) section.

To create a new token, provide its name and click the <control>Generate Token</control> button.
A new token will be created and displayed right below.

> Copy it before you close this page and keep it in a secure location.
> This is the only time the token is visible.
>
{style="note"}

This section describes two options to supply your _Personal Access Token_ via Gradle using:
* Environment variables,
* Parameters to the Gradle task.

#### Using Environment Variables

Start by defining an environment variable such as:

```bash
export ORG_GRADLE_PROJECT_intellijPublishToken='YOUR_TOKEN'
```

> On macOS systems, environment variables set in <path>.bash_profile</path> are only visible to processes you run from bash.
> Environment variables visible to all processes need to be defined in [Environment.plist](https://developer.apple.com/library/archive/qa/qa1067/_index.html).
>
{style="note"}

Now provide the environment variable in the run configuration with which you run the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) task locally.
To do so, create a Gradle run configuration (if not already done), choose your Gradle project, specify the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) task, and then add the environment variable.

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
publishPlugin {
  token.set(System.getenv("ORG_GRADLE_PROJECT_intellijPublishToken"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
publishPlugin {
  token = System.getenv("ORG_GRADLE_PROJECT_intellijPublishToken")
}
```

</tab>
</tabs>


Note that you still need to put some default values (can be empty) in the Gradle properties because otherwise, you will get a compilation error.

#### Using Parameters for the Gradle Task

Like using environment variables, you can also pass your token as a parameter to the Gradle task.
For example, you can provide the parameter

```bash
-Dorg.gradle.project.intellijPublishToken=YOUR_TOKEN
```
on the command line or by putting it in the arguments of your Gradle run configuration.

Note that also, in this case, you still need to put some default values in your Gradle properties.

### Deploying a Plugin with Gradle

The first step when deploying a plugin is to confirm that it works correctly.
You may wish to verify this by [installing your plugin from disk](https://www.jetbrains.com/help/idea/managing-plugins.html) on a fresh instance of your target IDE(s).

#### Signing a Plugin

The Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
In version `1.x`, the Gradle IntelliJ Plugin provides the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, which will be executed automatically right before the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin).

For more details on generating a proper certificate and configuring the [`signPlugin`](tools_gradle_intellij_plugin.md#tasks-signplugin) task, check the [Plugin Signing](plugin_signing.md) article.

#### Publishing a Plugin

Once you are confident the plugin works as intended, make sure the plugin version is updated, as the JetBrains Marketplace won't accept multiple artifacts with the same version.

To deploy a new version of your plugin to the JetBrains Marketplace, invoke the [`publishPlugin`](tools_gradle_intellij_plugin.md#tasks-publishplugin) Gradle task.

Now check the most recent version of your plugin on the [JetBrains Marketplace](https://plugins.jetbrains.com/).
If successfully deployed, any users who currently have your plugin installed on an available version of the IntelliJ Platform are notified of a new update available as soon as the update has been verified.

### Specifying a Release Channel

You may also deploy plugins to a release channel of your choosing, by configuring the [`publishPlugin.channels`](tools_gradle_intellij_plugin.md#tasks-publishplugin-channels) property.
For example:

<tabs group="languages">
<tab title="Kotlin" group-key="kotlin">

```kotlin
publishPlugin {
  channels.set(listOf("beta"))
}
```

</tab>
<tab title="Groovy" group-key="groovy">

```groovy
publishPlugin {
  channels = ['beta']
}
```

</tab>
</tabs>

When empty, this uses the default plugin repository, available to all [JetBrains Marketplace](https://plugins.jetbrains.com/) users.
However, you can publish it to an arbitrarily-named channel.
These non-default release channels are treated as separate repositories.

When using a non-default release channel, users need to configure a new [custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) in their IDE to install your plugin.
For example, if you specify `publishPlugin.channels = ['canary']`, then users need to add the `https://plugins.jetbrains.com/plugins/canary/list` repository to install the plugin and receive updates.

Popular channel names include:
* `alpha`: https://plugins.jetbrains.com/plugins/alpha/list
* `beta`: https://plugins.jetbrains.com/plugins/beta/list
* `eap`: https://plugins.jetbrains.com/plugins/eap/list

More information about the available configuration options is in the [documentation of the IntelliJ Gradle Plugin](tools_gradle_intellij_plugin.md#tasks-publishplugin).
