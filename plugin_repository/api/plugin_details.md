---
title: Plugin Details API
---

This API endpoint provides individual plugin details by specified *pluginId* or *pluginXmlId*:
 
```
https://plugins.jetbrains.com/plugins/list?pluginId=<pluginXmlId>
```
or
```
https://plugins.jetbrains.com/plugins/list?pluginId=<pluginId>
```

Where

* **pluginXmlId** is specified as a *Plugin XML ID* parameter on the right of the plugin's individual update page and in the plugin.xml;

* **pluginId** is a numeric ID of the plugin, can be retrieved from the plugin repository URL. e.g. [Scala](https://plugins.jetbrains.com/plugin/1347-scala) plugin ID is *1347*.

e.g. for [Scala Plugin](https://plugins.jetbrains.com/plugin/1347-scala):

[https://plugins.jetbrains.com/plugins/list?pluginId=org.intellij.scala](https://plugins.jetbrains.com/plugins/list?pluginId=org.intellij.scala)
or
[https://plugins.jetbrains.com/plugins/list?pluginId=1347](https://plugins.jetbrains.com/plugins/list?pluginId=1347)
