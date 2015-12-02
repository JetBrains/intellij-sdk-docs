---
title: Deploying plugins with Gradle
---

Once you have configured Gradle support in your plugin, you can automatically build and deploy your plugin to the JetBrains [plugin repository](http://plugins.jetbrains.com). To do so at this time, you will need to have already published plugin page.

### Add your account credentials

In order to publish a plugin on the plugin repository, you will first need to supply your JetBrains account credentials to the plugin repository. These are typically stored in the [Gradle properties](https://docs.gradle.org/current/userguide/build_environment.html#sec:gradle_configuration_properties). It is crucial that you do not check these credentials into source control.

Place the following information in a file called `gradle.properties` under your project's root directory, or inside `GRADLE_HOME/gradle.properties`.

```
intellij.publish.username="YOUR_USERNAME_HERE"
intellij.publish.password="YOUR_PASSWORD_HERE"
```

### Add your plugin ID

Inside the `intellij { ... }` portion of your Gradle buildscript, add the following snippet:

```groovy
publish {
    pluginId 'YOUR_PLUGIN_ID'
    apply from: "gradle.properties"
}
```

Your pluginId can be found in your plugin's URL, ie. https://plugins.jetbrains.com/plugin/YOUR_PLUGIN_ID.

### Deploy your plugin

To deploy a new version of your plugin to the JetBrains plugin repository, execute the following Gradle command:

```bash
gradle publishPlugin
```

Now check your plugin URL to verify the plugin has been updated.

[Top](/tutorials/build_system.md)