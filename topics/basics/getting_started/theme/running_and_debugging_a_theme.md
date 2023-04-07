<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Running and Debugging a Theme

<link-summary>Running and debugging a theme in the actual IDE instance.</link-summary>

In most cases, the results of theme plugin can be previewed by running the <control>Preview Theme</control> action in the [theme description file](themes_customize.md) editor.
Sometimes, when more advanced styling options are implemented, the previewing theme may not be enough to see all the changes.
This page explains how to run or debug the theme plugin in the IDE configured as a part of [project SDK](setting_up_theme_environment.md).

## Create Plugin Run Configuration

To run and debug a plugin directly from a theme plugin project, a _Plugin_ run configuration must be added.

> For more information about creating Run Configurations, refer to the [Run/Debug Configuration](https://www.jetbrains.com/help/idea/run-debug-configuration.html) section in IntelliJ IDEA Web Help.
>

<procedure title="Add Plugin Run Configuration">

1. Go to <ui-path>Run | Edit Configurations...</ui-path>.
2. Click the <control>Add New Configuration...</control> button (<control>+</control>) and select the <control>Plugin</control> type.
3. Provide the configuration <control>Name</control>, e.g., _Run Theme_.
4. Ensure that <control>Use classpath of module</control> specifies the current theme plugin module.
5. Click the <control>Apply</control> button.


</procedure>

See the [](ide_development_instance.md) section for information about advanced run configuration settings.
If additional settings, like system property, are needed, see [Run/Debug Configuration: Plugin](https://www.jetbrains.com/idea/help/run-debug-configuration-plugin.html) explaining how to configure them.

## Run/Debug Theme Plugin

To run the theme in the IDE development instance, choose <ui-path>Run | Run...</ui-path> and select the created run configuration.
Debugging a theme is similar, but instead of <control>Run...</control>, select the <control>Debug...</control> action.
