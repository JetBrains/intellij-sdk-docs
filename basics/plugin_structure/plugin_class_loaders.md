---
title: Plugin Class Loaders
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A separate class loader is used to load the classes of each plugin. This allows each plugin to use a different version of a library, even if the same library is used by the IDE itself or by another plugin.

By default, the main IDE class loader loads classes that were not found in the plugin class loader. However, in the `plugin.xml` file, you may use the `<depends>` element to specify that a [plugin depends](plugin_class_loaders.md) on one or more other plugins. In this case the class loaders of those plugins will be used for classes not found in the current plugin. This allows a plugin to reference classes from other plugins.
