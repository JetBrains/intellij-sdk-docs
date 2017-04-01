---
title: Download Plugin Update API
---

There are two ways to download the plugin version programmatically:

## Download the latest plugin update compatible with a certain product

```
https://plugins.jetbrains.com/pluginManager?action=download&id=<pluginXmlId>&build=<productCode>-<buildNumber>
```

Where

* **pluginXmlId** is specified as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml;

* **productCode** is a two-character product code. Can be blank.

* **buildNumber** is a build number of the IDE (specified in the About Dialog in the product, and in the release notes).

e.g. for [Scala plugin](https://plugins.jetbrains.com/plugin/1347-scala) (`pluginXmlId=org.intellij.scala`) & IntelliJ IDEA 2017.1 (`productCode=IU`, `buildNumber=171.3780.107`):

[https://plugins.jetbrains.com/pluginManager?action=download&id=org.intellij.scala&build=IU-171.3780.107](https://plugins.jetbrains.com/pluginManager?action=download&id=org.intellij.scala&build=IU-171.3780.107)

## Download a specified version of the plugin

```
https://plugins.jetbrains.com/plugin/download?pluginId=<pluginXmlId>&version=<version>
```

Where

* **pluginXmlId** is specified as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml;

* **version** is a version of the plugin update.

e.g. for [Scala plugin](https://plugins.jetbrains.com/plugin/1347-scala) (`pluginXmlId=org.intellij.scala`) & Version `2017.1.15`:

[https://plugins.jetbrains.com/plugin/download?pluginId=org.intellij.scala&version=2017.1.15](https://plugins.jetbrains.com/plugin/download?pluginId=org.intellij.scala&version=2017.1.15)
