---
title: Plugin Developers List API
---

This API endpoint provides a list of all plugin developers for a plugin by specified *pluginId* or *pluginXmlId*:
 
```
https://plugins.jetbrains.com/plugin/developers?xmlId=<pluginXmlId>
```
or
```
https://plugins.jetbrains.com/plugin/developers?pluginId=<pluginId>
```

Where

* **pluginXmlId** is specified as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml;

* **pluginId** is a numeric ID of the plugin, can be retrieved from the plugin repository URL. e.g. [Scala](https://plugins.jetbrains.com/plugin/1347-scala) plugin ID is *1347*.

e.g. for [Scala Plugin](https://plugins.jetbrains.com/plugin/1347-scala):

[https://plugins.jetbrains.com/plugin/developers?xmlId=org.intellij.scala](https://plugins.jetbrains.com/plugin/developers?xmlId=org.intellij.scala)
or
[https://plugins.jetbrains.com/plugin/developers?pluginId=1347](https://plugins.jetbrains.com/plugin/developers?pluginId=1347)
