---
title: Deploying plugins with Gradle
---

Once you have configured Gradle support, you can automatically build and deploy your plugin to the JetBrains [Plugin Repository](http://plugins.jetbrains.com). To do so, you will need to have already published the plugin to the plugin repository. For detailed information, please see the guide to [publishing a plugin](http://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/publishing_plugin.html).

### 2.0 Add your account credentials

In order to deploy a plugin to the plugin repository, you will first need to supply your JetBrains Account credentials. These are typically stored in the [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties). It is crucial that you do not check these credentials into source control.

Place the following information inside a file called `gradle.properties` under your project's root directory, or inside `GRADLE_HOME/gradle.properties`.

```
intellij.publish.username="YOUR_USERNAME_HERE"
intellij.publish.password="YOUR_PASSWORD_HERE"
```

If you place a `gradle.properties` file in your project's root directory, please ensure that this file is ignored by your version control tool. For example in Git, you can add the following line to your `.gitignore` file:

```
gradle.properties
```

Alternately, you may decide to create a default `gradle.properties` template, and instruct `git` to ignore any future changes to that file. To do so, check in the default template and execute the following command:

```
git update-index --assume-unchanged gradle.properties
```

If your project already has a custom `gradle.properties` file, you may create a custom `*.properties` file, and load it manually. For example:

```
apply from: "/path/to/custom.properties"
```

### 2.1 Add your plugin ID

Inside the `intellij { ... }` portion of your Gradle buildscript, add the following snippet:

```groovy
publish {
    pluginId 'YOUR_PLUGIN_ID'
    // (optional) apply from: "YOUR_CUSTOM_PROPERTIES_FILE.properties"
}
```

Your pluginId can be found in your plugin's URL, ie.: `https://plugins.jetbrains.com/plugin/YOUR_PLUGIN_ID`.

### 2.1 Configure your plugin

The gradle-intellij-plugin provides a number of [configuration options](https://github.com/JetBrains/gradle-intellij-plugin#configuration) for customizing how Gradle builds your plugin. One of the most important is the `version`. By default, if you modify the `version` in your build script, the Gradle plugin will automatically update the `<version>` in your `plugin.xml` file. 
 
 The Gradle plugin will also update the `<idea-version since-build=.../>` values within the `plugin.xml` file to match the `intellij.version`, valid until the last release in the current major version, however you can disable this feature by setting the `intellij.updateSinceUntilBuild` option to `false`.

```groovy
apply plugin: 'org.jetbrains.intellij'

intellij {
    version '15.0.1'
    pluginName 'idear'
    intellij.updateSinceUntilBuild false //Disables updating since-build attribute in plugin.xml
}

group 'com.jetbrains'
version '1.2' // Update me!
```

When you run `gradle runIdea` with a build script containing the above snippet, Gradle will download the appropriate version of IntelliJ IDEA from either a [Snapshot](https://www.jetbrains.com/intellij-repository/snapshots) (time-based) or [Release](https://www.jetbrains.com/idea/help/managing-plugins.html) (version based) repository, configure the plugin sandbox, install your plugin, and launch a new instance of the IDE. This task can be run directly from the command line, without any prior tooling assistance. For best results, you should choose 

For best results, plugin developers should build against a fixed version, rather than `LATEST-TRUNK-SNAPSHOT`. For information about available versions of the IntelliJ Platform, you may consult the following URLs for the most recent updates:

* https://www.jetbrains.com/intellij-repository/releases
* https://www.jetbrains.com/intellij-repository/snapshots

### 2.3 Deploy your plugin

The first step when deploying a plugin is to confirm that it works correctly. You may wish to verify this by [installing your plugin from disk](https://www.jetbrains.com/idea/help/installing-plugin-from-disk.html) on a fresh instance of IntelliJ IDEA Community Edition. Once you are confident the plugin works as intended, make sure the plugin version is updated, as the JetBrains Plugin repository will not accept multiple artifacts with the same version. To deploy a new version of your plugin to the JetBrains plugin repository, execute the following Gradle command:

```bash
gradle publishPlugin
```

Now check that the most recent version of your plugin appears on the [Plugin Repository](https://plugins.jetbrains.com/). If successfully deployed, any users who currently have your plugin installed on an eligible version of the IntelliJ Platform will be notified of a new update available on the following restart.

You may also deploy plugins to a release channel of your choosing, by configuring the `intellij.publish.channel` property. When empty, this will use the default plugin repository, available to all [JetBrains plugin repository](https://plugins.jetbrains.com/) users, however you can publish to an arbitrarily-named channel. When using a non-default release channel, users may need to add a new [custom plugin repository](https://www.jetbrains.com/idea/help/managing-enterprise-plugin-repositories.html) to install your plugin. For example, if you specify `intellij.publish.channel 'canary'`, then users will need to add the `https://plugins.jetbrains.com/plugins/canary/list` repository to install the plugin and receive updates. These channels are treated as separate repositories for all intents and purposes. Popular channel names include:

* `alpha`: https://plugins.jetbrains.com/plugins/alpha/list
* `beta`: https://plugins.jetbrains.com/plugins/beta/list
* `eap`: https://plugins.jetbrains.com/plugins/eap/list

It is also possible to host an [Enterprise Plugin Repository](https://www.jetbrains.com/idea/help/adding-plugins-to-enterprise-repositories.html), in which case the URL structure will depend on your hosting address. For more information, please refer to the [IntelliJ IDEA documentation](https://www.jetbrains.com/idea/help/managing-plugins.html).

[Top](/tutorials/build_system.md)