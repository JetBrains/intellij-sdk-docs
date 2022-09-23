[//]: # (title: Creating a Theme Project)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

This documentation page describes a DevKit-based theme project generated with the [New Project Wizard](https://www.jetbrains.com/help/idea/new-project-wizard.html).

## Creating a Theme with New Project Wizard

Before creating a theme project, make sure that [development environment is set up](setting_up_environment.md).

<procedure title="Create IDE Theme" id="create-ide-theme">

Launch the <control>New Project</control> wizard via the <menupath>File | New | Project...</menupath> action and provide the following information:
1. Select the <control>IDE Plugin</control> generator type from the list on the left.
2. Specify the project <control>Name</control> and <control>Location</control>.
3. Choose the <control>Theme</control> option in the project <control>Type</control>.
4. As the <control>JDK</control> select the [configured SDK](setting_up_environment.md#configuring-intellij-platform-plugin-sdk).
5. Click the <control>Create</control> button to generate the project.

</procedure>

### Components of a Wizard-Generated Gradle IntelliJ Platform Theme

For the example `my_theme` created with the steps describes above, the _IDE Plugin_ generator creates the following directory content:

```text
my_theme
├── resources
│   ├── META-INF
│   │   └── plugin.xml
│   │   └── pluginIcon.svg
│   └── theme
│       └── my_theme.theme.json
└── my_theme.iml
```

- <path>META-INF</path> directory with:
  - <path>[plugin.xml](plugin_configuration_file.md)</path> configuration file containing preconfigured theme provider
  - <path>pluginIcon.svg</path> file that is a [plugin logo](plugin_icon_file.md)<br/>
    It is recommended to replace it with a custom icon.
- <path>my_theme.theme.json</path> - a minimal [theme description file](themes_customize.md#introduction-to-ui-theme-description-file-syntax)
- <path>my_theme.iml</path> - [IntelliJ IDEA Module](https://www.jetbrains.com/help/idea/creating-and-managing-modules.html) configuration file
