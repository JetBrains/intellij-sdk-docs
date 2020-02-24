---
title: Running and Debugging a Plugin
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

It's possible to run and debug a plugin directly from the *IntelliJ IDEA*. You need a configured special profile (a *Plugin* Run/Debug configuration) that specifies the plugin module, VM parameters and other specific options. When you run such profile, it launches the IDE with your plugin installed.

See [IDE Development Instances](/basics/ide_development_instance.md) for more information about configuration and advanced settings.

For information on how to change the Run/Debug configuration profile, refer to [Run/Debug Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) and [Run/Debug Configuration: Plugin](https://www.jetbrains.com/idea/help/run-debug-configuration-plugin.html) in *IntelliJ IDEA* Web Help.

Using *IntelliJ IDEA*'s debugger, you can find out the origin of the run-time errors and exceptions.

**To debug a plugin**

*  Select **Run \| Debug** in the main menu, or press <kbd>Shift</kbd> + <kbd>F9</kbd>.

**To run a plugin**

*  Select **Run \| Run** in the main menu, or press <kbd>Shift</kbd> + <kbd>F10</kbd>.
