---
layout: general
title: Plugin Class Loaders
---

To load classes of each plugin, IDEA uses a separate class loader.
This allows each plugin to use a different version of a library, even if the same library is used by IDEA itself or by another plugin.

By default, the main IDEA class loader loads classes that were not found in the plugin class loader.
However, in the plugin.xml file, one can use the ```<depends>``` element to specify that a plugin depends on one or more other plugins.
In this case, the class loaders of those plugins will be used for classes not found in the current plugin.
This allows a plugin to reference classes from other plugins.