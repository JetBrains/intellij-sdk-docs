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

* [Plugin Dependencies](plugin_dependencies.html)