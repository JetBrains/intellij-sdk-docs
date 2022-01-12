[//]: # (title: Enabling Internal Mode)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

There are useful tools, such as the Internal Actions menu, that are only visible if internal mode is enabled in IntelliJ IDEA.

<procedure title="Configuring Internal Mode">

1. Start IntelliJ IDEA.
2. From the main menu, select <menupath>Help | Edit Custom Properties</menupath>.
  This selection opens IntelliJ IDEA's <path>idea.properties</path> file.
  If it does not exist, IntelliJ IDEA will prompt to create one.
3. Add the line shown below to the <path>idea.properties</path> file:

```properties
idea.is.internal=true
```
4. Save the <path>idea.properties</path> file and restart IntelliJ IDEA.

5. The Internal Actions menu is now available in <menupath>Tools | Internal Actions</menupath>.

</procedure>
