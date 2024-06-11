<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Deploying a Theme

<link-summary>Building and deploying a theme in IDE.</link-summary>

Before your custom theme plugin can be [uploaded to JetBrains Marketplace](publishing_plugin.md#uploading-a-plugin-to-jetbrains-marketplace) and used by users, it should be packaged and verified in the actual IDE.

The deployment process prepares the plugin artifact that can be installed in IDE.

<procedure title="Deploying Theme Plugin">

1. Build the theme by invoking <ui-path>Build | Build Project</ui-path> or <ui-path>Build | Build Module \$MODULE_NAME\$</ui-path>.
2. Create the deployment artifact by invoking <ui-path>Build | Prepare Plugin Module \$MODULE_NAME\$ for Deployment</ui-path>.

   The resulting theme JAR file will be created in the project or module directory.

   In the case of developing a regular plugin, and it specifies additional dependencies, a&nbsp;ZIP archive is created, including all the plugin libraries.
3. [Install](https://www.jetbrains.com/help/idea/managing-plugins.html#installing-plugins-from-disk) the newly created JAR or ZIP file from disk.
4. Click the <control>Apply</control> button.
5. Select your theme in <ui-path>Settings | Appearance & Behavior | Appearance</ui-path> and apply the changes.

</procedure>
