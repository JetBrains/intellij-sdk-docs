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

If your project already has a custom `gradle.properties` file, you can create a custom `*.properties` file, and load it manually.


### 2.1 Add your plugin ID

Inside the `intellij { ... }` portion of your Gradle buildscript, add the following snippet:

```groovy
publish {
    pluginId 'YOUR_PLUGIN_ID'
    // (optional) apply from: "YOUR_CUSTOM_PROPERTIES_FILE.properties"
}
```

Your pluginId can be found in your plugin's URL, ie.: `https://plugins.jetbrains.com/plugin/YOUR_PLUGIN_ID`.

### 2.2 Deploy your plugin

To deploy a new version of your plugin to the JetBrains plugin repository, execute the following Gradle command:

```bash
gradle publishPlugin
```

Now check your plugin URL on the [Plugin Repository](https://plugins.jetbrains.com/) to verify the plugin has been updated.

[Top](/tutorials/build_system.md)