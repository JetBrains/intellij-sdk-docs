[//]: # (title: Publishing Plugins with Gradle)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Once you have [configured Gradle support](gradle_guide.md), you can automatically build and deploy your plugin to the [JetBrains Plugins Repository](https://plugins.jetbrains.com).
To automatically deploy a plugin, you need to have _already published the plugin to the plugin repository at least once._
Please see the guide page for manually [publishing a plugin](publishing_plugin.md) for the first time and [Building Distribution](#building-distribution) on obtaining the initial plugin distribution artifact.

 >  Please see [Marketing](marketing.md) for remarks on how to prepare your plugin for optimal presentation.
 >
 {type="tip"}

 >  When adding additional repositories to your Gradle build script, always use HTTPS protocol.
 >
 {type="warning"}

## Building Distribution
For initial upload, manual distribution or local installation, invoke the `buildPlugin` Gradle task to create the plugin distribution.
The resulting ZIP file is located in <path>build/distributions</path> and can then be installed via drag & drop (or using [plugin manager](https://www.jetbrains.com/help/idea/managing-plugins.html#installing-plugins-from-disk))
or uploaded to a [custom plugin repository](update_plugins_format.md).

## Providing Your Hub Permanent Token to Gradle
To deploy a plugin to the JetBrains Plugins Repository, you need to supply your [JetBrains Hub Permanent Token](https://plugins.jetbrains.com/docs/marketplace/plugin-upload.html).

This section describes two options to supply your _Hub Permanent Token_ via Gradle using:
* Environment variables,
* Parameters to the Gradle task.

### Using Environment Variables
Start by defining an environment variable such as:

```bash
export ORG_GRADLE_PROJECT_intellijPublishToken='YOUR_HUB_TOKEN_HERE'
```

 >  On macOS systems, environment variables set in <path>.bash_profile</path> are only visible to processes you run from bash.
Environment variables visible to all processes need to be defined in [Environment.plist](https://developer.apple.com/library/archive/qa/qa1067/_index.html).
 >
 {type="note"}

Now provide the environment variable in the run configuration with which you run the `publishPlugin` task locally.
To do so, create a Gradle run configuration (if not already done), choose your Gradle project, specify the `publishPlugin` task, and then add the environment variable.

```groovy
publishPlugin {
  token = System.getenv("ORG_GRADLE_PROJECT_intellijPublishToken")
}
```

Note that you still need to put some default values (can be empty) in the Gradle properties because otherwise, you will get a compilation error.

### Using Parameters for the Gradle Task
Like using environment variables, you can also pass your token as a parameter to the Gradle task.
For example, you can provide the parameter

```bash
-Dorg.gradle.project.intellijPublishToken=YOUR_HUB_TOKEN_HERE
```
on the command line or by putting it in the arguments of your Gradle run configuration.

Note that also, in this case, you still need to put some default values in your Gradle properties.

## Deploying a Plugin with Gradle
The first step when deploying a plugin is to confirm that it works correctly.
You may wish to verify this by [installing your plugin from disk](https://www.jetbrains.com/help/idea/managing-plugins.html) on a fresh instance of your target IDE(s).

### Signing a Plugin

The Marketplace signing is designed to ensure that plugins are not modified over the course of the publishing and delivery pipeline.
In version `1.x`, the Gradle IntelliJ Plugin provides the `signPlugin` task, which will be executed automatically right before the `publishPlugin`.

For more details on generating a proper certificate and configuring the `signPlugin` task, check the [Plugin Signing](plugin_signing.md) article.

### Publishing a Plugin
Once you are confident, the plugin works as intended, make sure the plugin version is updated, as the JetBrains Plugins Repository won't accept multiple artifacts with the same version.

To deploy a new version of your plugin to the JetBrains Plugins Repository, invoke the `publishPlugin` Gradle task.

Now check the most recent version of your plugin on the [JetBrains Plugins Repository](https://plugins.jetbrains.com/).
If successfully deployed, any users who currently have your plugin installed on an available version of the IntelliJ Platform are notified of a new update available as soon as the update has been verified.

### Specifying a Release Channel
You may also deploy plugins to a release channel of your choosing, by configuring the `publishPlugin.channels` property.
For example:

```groovy
publishPlugin {
  channels = ['beta']
}
```

When empty, this uses the default plugin repository, available to all [JetBrains Marketplace](https://plugins.jetbrains.com/) users.
However, you can publish it to an arbitrarily-named channel.
These non-default release channels are treated as separate repositories.

When using a non-default release channel, users need to configure a new [custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) in their IDE to install your plugin.
For example, if you specify `publishPlugin.channels = ['canary']`, then users need to add the `https://plugins.jetbrains.com/plugins/canary/list` repository to install the plugin and receive updates.

Popular channel names include:
* `alpha`: https://plugins.jetbrains.com/plugins/alpha/list
* `beta`: https://plugins.jetbrains.com/plugins/beta/list
* `eap`: https://plugins.jetbrains.com/plugins/eap/list

More information about the available configuration options is in the [documentation of the IntelliJ Gradle plugin](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#publishing-dsl).
