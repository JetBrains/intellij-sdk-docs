---
layout: editable
title: Plugin Structure
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Plugin+Structure
-->



Plugins are the only supported way to extend IDEA functionality.
A plugin uses API exposed by IDEA or other plugins to implement its functionality.
This document is focused on the plugin system structure and plugin lifecycle.
It doesn't specify any other APIs that may be used by plugins.

The following subjects are covered:
<!--TODO Links from TOC to certain parts of the document-->

* [Plugin Content](plugin_content.html)

* [Plugin Class Loaders](plugin_class_loaders.html)

* [Plugin Components](plugin_components.html)

* [Plugin Extensions and Extension Points](plugin_extensions_and_extension_points.html)

* [Plugin Actions](plugin_actions.html)

* [Plugin Services](plugin_services.html)

* [Plugin Configuration File](plugin_configuration_file.html)

* [Plugin Dependencies](TODO)


# Plugin Dependencies

In your plugin, you may depend on classes from other plugins, either bundled, third-party or your own. 
In order to do so, you need to perform the following two steps:

*  Add the jars of the plugin you're depending on to the classpath of your IntelliJ IDEA SDK.
(**Note**: Don't add the plugin jars as a library: this will fail at runtime because IntelliJ IDEA will load two separate copies of the dependency plugin classes.)

*  Add a <depends> tag to your plugin.xml, adding the ID of the plugin you're depending on as the contents of the tag.
For example: 

```xml
<depends>org.jetbrains.idea.maven</depends>
```

To find out the ID of the plugin you're depending on, locate the META-INF/plugin.xml file inside its jar and check the contents of the <id> tag.
