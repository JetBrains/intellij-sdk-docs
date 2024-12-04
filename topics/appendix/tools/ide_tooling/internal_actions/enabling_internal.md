<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Enabling Internal Mode

<link-summary>Enabling the Internal Mode enables actions and tools useful in plugin development and diagnosing issues.</link-summary>

There are useful tools, such as the <control>Internal Actions</control> menu, that are only visible if the internal mode is enabled in the IDE.

<procedure title="Configuring Internal Mode">

1. Start the IDE.
2. From the main menu, select <ui-path>Help | Edit Custom Properties...</ui-path>.
   This selection opens the <path>idea.properties</path> file.
   If it does not exist, the IDE will prompt to create one.
3. Add the line shown below to the <path>idea.properties</path> file:
   ```properties
   idea.is.internal=true
   ```

4. Save the <path>idea.properties</path> file and restart the IDE.

5. The Internal Actions menu is now available in <ui-path>Tools | Internal Actions</ui-path>.

</procedure>
