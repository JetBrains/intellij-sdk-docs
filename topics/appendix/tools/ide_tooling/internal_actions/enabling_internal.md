[//]: # (title: Enabling Internal Mode)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

There are useful tools, such as the <control>Internal Actions</control> menu, that are only visible if the internal mode is enabled in IntelliJ IDEA.

<procedure title="Configuring Internal Mode">

1. Start IntelliJ IDEA.
2. From the main menu, select <ui-path>Help | Edit Custom Properties...</ui-path>.
  This selection opens IntelliJ IDEA's <path>idea.properties</path> file.
  If it does not exist, IntelliJ IDEA will prompt to create one.
3. Add the line shown below to the <path>idea.properties</path> file:

```
idea.is.internal=true
```
4. Save the <path>idea.properties</path> file and restart IntelliJ IDEA.

5. The Internal Actions menu is now available in <ui-path>Tools | Internal Actions</ui-path>.

</procedure>
