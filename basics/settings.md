---
title: Settings
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Introduction to Settings
Settings are but one application of the IntelliJ Platform [Persistence Model](/basics/persistence.md).
A Settings implementation can be visualized as three classes:
* `Configurable` - the Controller for Settings.
  This class interacts with the IntelliJ Platform to initialize, provide, and read a Swing form in the Settings dialog.
  The Configurable updates Settings changes to the State class. 
* `Component` - the View for Settings.
  This class provides the Swing form and handles Settings display and changes by the user.
  The form is initialized by, and final values are read by the Configurable.
* `State` - the Model for Settings.
  This class persists the value of settings that differ from the defaults.
  Changes to new values are read by the Configurable from the Component and written to this State.

Settings are declared using Extension Points in `plugin.xml`.

Separating the functionality into three classes for clarity is perhaps simplistic.
Many IntelliJ Platform Settings implement everything in fewer classes.

The IntelliJ Platform code sample `settings` is a useful reference.
For more information, see:
* [Settings Guide](/reference_guide/settings_ref.md) for information about Settings Extension Points and implementations.
* Settings Tutorial

