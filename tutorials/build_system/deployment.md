---
title: Publishing Plugins with Gradle
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

Once you have configured Gradle support, you can automatically build and deploy your plugin to the [JetBrains Plugins Repository](https://plugins.jetbrains.com). 
To automatically deploy a plugin, you need to have _already published the plugin to the plugin repository at least once._ 
Please see the guide page for manually [publishing a plugin](../../basics/getting_started/publishing_plugin.md) for the first time.

> **WARNING** When adding additional repositories to your Gradle build script, make sure to always use HTTPS protocol.

* bullet list
{:toc}

## Building Distribution
For manual distribution or local installation, invoke `gradle buildPlugin` target to create the plugin distribution.
The resulting JAR/ZIP is located in `build/distributions` and can then be [installed](https://www.jetbrains.com/help/idea/managing-plugins.html#installing-plugins-from-disk) either manually or uploaded to a [custom plugin repository](/basics/getting_started/update_plugins_format.md).

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

> **NOTE** On macOS systems, environment variables defined in `.bash_profile` are only visible to processes you run from bash. 
Environment variables visible to all processes need to be defined in [Environment.plist](https://developer.apple.com/library/archive/qa/qa1067/_index.html)

Now provide the environment variable in the run configuration with which you run the `publishPlugin` task locally. 
To do so, create a Gradle run configuration (if not already done), choose your Gradle project, specify the `publishPlugin` task, and then add the environment variable. 

```groovy
publishPlugin {
  token = System.getenv("ORG_GRADLE_PROJECT_intellijPublishToken")
}
```

Note that you still need to put some default values (can be empty) in the Gradle properties because otherwise, you will get a compilation error.

### Using Parameters for the Gradle Task
Similar to using environment variables, you can also pass your token as a parameter to the Gradle task.
For example, you can to provide the parameter `-Dorg.gradle.project.intellijPublishToken=YOUR_HUB_TOKEN_HERE` on the command line or by putting it in the arguments of your Gradle run configuration.

Note that also, in this case, you still need to put some default values in your Gradle properties.


## Deploying a Plugin with Gradle
The first step when deploying a plugin is to confirm that it works correctly. 
You may wish to verify this by [installing your plugin from disk](https://www.jetbrains.com/help/idea/managing-plugins.html) on a fresh instance of your target IDE(s). 

### Publishing a Plugin
Once you are confident the plugin works as intended, make sure the plugin version is updated, as the JetBrains Plugins Repository won't accept multiple artifacts with the same version. 

To deploy a new version of your plugin to the JetBrains Plugins Repository, execute the following Gradle command:  

```bash
gradle publishPlugin
```

Now check the most recent version of your plugin appears on the [JetBrains Plugins Repository](https://plugins.jetbrains.com/). 
If successfully deployed, any users who currently have your plugin installed on an eligible version of the IntelliJ Platform are notified of a new update available as soon as the update has been verified.

### Specifying a Release Channel
You may also deploy plugins to a release channel of your choosing, by configuring the `publishPlugin.channels` property. 
For example:

```groovy
publishPlugin {
    channels 'beta'
}
```

When empty, this uses the default plugin repository, available to all [JetBrains Plugins Repository](https://plugins.jetbrains.com/) users. 
However, you can publish to an arbitrarily-named channel. 
These non-default release channels are treated as separate repositories. 

When using a non-default release channel, users need to configure a new [custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) in their IDE to install your plugin. 
For example, if you specify `publishPlugin.channels 'canary'`, then users need to add the `https://plugins.jetbrains.com/plugins/canary/list` repository to install the plugin and receive updates. 

Popular channel names include:
* `alpha`: https://plugins.jetbrains.com/plugins/alpha/list
* `beta`: https://plugins.jetbrains.com/plugins/beta/list
* `eap`: https://plugins.jetbrains.com/plugins/eap/list

More information about the available configuration options is in the [documentation of the intellij gradle plugin](https://github.com/JetBrains/gradle-intellij-plugin/blob/master/README.md#publishing-dsl).
