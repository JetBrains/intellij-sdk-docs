[//]: # (title: Publishing a Plugin to a Custom Plugin Repository)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

If you intend to use a plugin repository _other than_ the [JetBrains Marketplace](https://plugins.jetbrains.com), you will need to:

<procedure>

1. Create and maintain an <path>updatePlugins.xml</path> file on the HTTPS web server you are using for your custom repository.
   This file describes all the plugins available in your custom repository and each plugin's download URL.
2. Upload your plugin JAR/ZIP file to an HTTPS web server.
   This can be the same web server you are using for the custom repository or a different HTTPS web server.
3. Add the URL for the custom repository to the JetBrains IDE [Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

</procedure>

> Gradle plugin [IntelliJ plugin uploader](https://github.com/brian-mcnamara/plugin_uploader) can be used to automate deployment.
>
{type="tip"}

## Describing Your Plugins in updatePlugins.xml File

Every custom plugin repository must have at least one <path>updatePlugins.xml</path> file to describe every hosted plugin's latest available version.
The description in <path>updatePlugins.xml</path> is used by JetBrains IDEs to locate plugins by attributes such as id, IDE version, and plugin version.
These attributes are displayed by JetBrains IDEs to help users select or upgrade plugins.
The description also tells the JetBrains IDE where to download the plugin itself.

A custom plugin repository's <path>updatePlugins.xml</path> file is constructed and maintained by the repository administrator.
More than one <path>updatePlugins.xml</path> file may be required if the custom repository consumers are using more than one version of a JetBrains IDE.
For example, <path>updatePlugins-182.xml</path>, <path>updatePlugins-183.xml</path> for IntelliJ IDEA 2018.2 and 2018.3, respectively.
Each <path>updatePlugins-*.xml</path> file will have a unique URL that is added to the JetBrains IDE [Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

### Format of updatePlugins.xml File

The format of an <path>updatePlugins.xml</path> file is simply a list of sequential elements that describe each plugin:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!--
The <plugins> element (required) contains the description of the plugins
available at this repository.
-->
<plugins>
  <!--
  Each <plugin> element (required) describes one plugin in the repository.
  Attributes:
   - "id" (required) - used by JetBrains IDEs to uniquely identify a plugin.
     Must match <id> in the plugin.xml file.
   - "url" (required) - URL to download the plugin JAR/ZIP file. Must be HTTPS.
   - "version" (required) - version of this plugin. Must match <version>
     in the plugin.xml file.
  -->
  <plugin
      id="fully.qualified.id.of.this.plugin"
      url="https://mycompany.example.com/my_repo/my_plugin.jar"
      version="major.minor.update">
    <!--
    The <idea-version> element (required) must match the same element
    in the plugin.xml file.
    -->
    <idea-version since-build="181.3" until-build="191.*"/>
  </plugin>
  <plugin
      id="id.of.different.plugin"
      url="https://othercompany.example.com/other_repo/other_plugin.jar"
      version="major.minor">
    <idea-version since-build="181.3" until-build="191.*"/>
  </plugin>

  <plugin>
    <!-- And so on for other plugins... -->
  </plugin>
</plugins>
```

**Note:**

* An <path>updatePlugins.xml</path> file must contain at least one set of `<plugin></plugin>` elements.
* A plugin `id` may be listed only once in an <path>updatePlugins.xml</path> file.
* Multiple plugins with the same `id` but different `idea-version` attributes must be split into separate <path>updatePlugins-*.xml</path> files.
  The requesting IDE's version is passed as `build` parameter and can be used for server-side filtering.

### Optional updatePlugin.xml Elements

Can additional elements be added to <path>updatePlugins.xml</path>?
Yes, but it's advisable only if needed.
The additional elements will have to be synchronized with each plugin's <path>plugin.xml</path> file.

During plugin installation, the IDE reads the plugin JAR/ZIP file, and thereafter displays more information about the plugin.
What additional information might help a user select a plugin when [browsing the custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) before installation?
The answer depends on the plugins and repository consumers.
Here are the candidate elements:

| Element                                                       | Effects & Requirements                                                                                                                                                                                                                                                                                                                                                                                                                           |
|:--------------------------------------------------------------|:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `<name>`<br/>My Plugin Name<br/>`</name>`                     | By default the name of the plugin JAR/ZIP file is displayed before installation. <br/>Using the `<name>` element displays the name of the plugin. <br/>Contents should match the `<name>` element contents in the plugins's <path>plugin.xml</path> file to avoid confusion.                                                                                                                                                                     |
| `<description>`<br/>My plugin is awesome<br/>`</description>` | By default no description for the plugin is displayed before installation. <br/>Using the `<description>` element will cause a description to be displayed before installation. <br/>Contents should match the `<description>` element contents in the plugins's <path>plugin.xml</path> file to avoid confusion. <br/>Optionally, an enclosing `<![CDATA[ ]]>` element can be used if the description needs to contain HTML tags.               |
| `<change-notes>`<br/>Added cool feature<br/>`</change-notes>` | By default no change notes for the plugin are displayed before installation. <br/>Using the `<change-notes>` element will cause a description of changes to be displayed before installation. <br/>Contents should match the `<change-notes>` element contents in the plugin's <path>plugin.xml</path> file to avoid confusion. <br/>Optionally, an enclosing `<![CDATA[ ]]>` element can be used if the change notes need to contain HTML tags. |
