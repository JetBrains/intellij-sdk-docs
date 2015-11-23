---
title: Plugin Dependencies
---

In your plugin, you may depend on classes from other plugins, either bundled, third-party or your own. In order to do so, you need to perform the following two steps:

* Add the jars of the plugin you're depending on to the classpath of your *IntelliJ Platform SDK*.

> **warning** Do not add the plugin jars as a library: this will fail at runtime because IntelliJ Platform will load two separate copies of the dependency plugin classes.

* Add a `<depends>` tag to your plugin.xml, adding the ID of the plugin you're depending on as the contents of the tag.
For example:

```xml
<depends>org.jetbrains.idea.maven</depends>
```

To find out the ID of the plugin you're depending on, locate the `META-INF/plugin.xml` file inside its jar and check the contents of the `<id>` tag.
