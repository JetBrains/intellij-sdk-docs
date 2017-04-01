---
title: Plugin Repository Maven Interface
---

You can download a plugin update from the [plugin repository](https://plugins.jetbrains.com) using a Maven interface available at `https://plugins.jetbrains.com/maven`.

URL format is the following:

```
https://plugins.jetbrains.com/maven/com/jetbrains/plugins/<plugin_xml_id>/<version>/<plugin_xml_id>-<version>-<channel>.<extension>
```

Where

* **plugin_xml_id** is an unique Plugin XML ID specified on the right of the plugin's individual updade page or in the *plugin.xml*; 

* **version** is a full update version;

* **channel** is a release channel the update is published to (empty channel means default *Stable* channel);

* **extension** is a plugin update package extension (*.jar* or *.zip*).

E.g. to download *[VueJs plugin version 1.0.1](https://plugins.jetbrains.com/plugin/8057-vue-js/update/23034)* you should send request at [https://plugins.jetbrains.com/maven/com/jetbrains/plugins/vue.js/1.0.1/vue.js-1.0.1.jar](https://plugins.jetbrains.com/maven/com/jetbrains/plugins/vue.js/1.0.1/vue.js-1.0.1.jar).

Also, you can use Gradle or Maven to retrieve a plugin as a maven-dependency:

* **groupId** is always **com.jetbrains.plugins**;

* **artifactId** is **pluginXmlId** (pluginXmlId is specified as a *Plugin XML ID* parameter on the right of the plugin's individual update page);

* **classifier** is a **channel** (empty classifier means default *Stable* channel).

**build.gradle**

```
repositories {
  maven { url 'https://plugins.jetbrains.com/maven' }
}
 
dependencies {
  compile 'com.jetbrains.plugins:<plugin_xml_id>:<version>@<extension>'
}
```

(please see parameters description above)
