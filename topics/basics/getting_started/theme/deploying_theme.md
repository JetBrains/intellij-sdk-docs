[//]: # (title: Deploying a Theme)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<excerpt>Building and deploying a theme in IDE.</excerpt>

Before your custom theme plugin can be [uploaded to JetBrains Marketplace](publishing_plugin.md#uploading-a-plugin-to-jetbrains-marketplace) and used by users, it should be packaged and verified in the actual IDE.

The deployment process prepares the plugin artifact that can be installed in IDE.

<procedure title="Deploying Theme Plugin">

1. Build the theme by invoking <menupath>Build | Build Project</menupath> or <menupath>Build | Build Module $MODULE_NAME$</menupath>.
2. Create the deployment artifact by invoking <menupath>Build | Prepare Plugin Module $MODULE_NAME$ for Deployment</menupath>.<br/>
   The resulting theme JAR file will be created in the project or module directory.<br/>
   In the case of developing a regular plugin, and it specifies additional dependencies, a&nbsp;ZIP archive is created, including all the plugin libraries.
3. [Install](https://www.jetbrains.com/help/idea/managing-plugins.html#installing-plugins-from-disk) the newly created JAR or ZIP file from disk.
4. Click the <control>Apply</control> button.
5. Select your theme in <menupath>Preferences | Appearance & Behavior | Appearance</menupath> and apply the changes.

</procedure>
