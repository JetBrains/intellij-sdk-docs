---
title: Publishing a Plugin to a Custom Plugin Repository
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

If you intend to use a plugin repository _other than_ the [JetBrains Plugins Repository](https://plugins.jetbrains.com), 
you will need to:
* Create and maintain an `updatePlugins.xml` file on the HTTPS web server you are using for your custom repository. 
  This file describes all the plugins available in your custom repository and each plugin's download URL.
* Upload your plugin JAR/ZIP file to an HTTPS web server. This can be the same web server you are using for the custom repository
  or a different HTTPS web server.
* Add the URL for the custom repository to the JetBrains IDE [Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

## Describing Your Plugins in updatePlugins.xml File
Every custom plugin repository must have at least one `updatePlugins.xml` file to describe the latest available version 
for every hosted plugin. The description in `updatePlugins.xml` is used by JetBrains IDEs to locate plugins by attributes 
such as id, IDE version, and plugin version. These attributes are displayed by JetBrains IDEs to help users select or upgrade plugins.
The description also tells the JetBrains IDE where to download the plugin itself.

A custom plugin repository's `updatePlugins.xml` file is constructed and maintained by the administrator of
the repository. More than one `updatePlugins.xml` file may be required if consumers of the custom repository are using more
than one version of a JetBrains IDE. For example `updatePlugins-182.xml`, `updatePlugins-183.xml` for IntelliJ IDEA 2018.2 and 2018.3 respectively.
Each `updatePlugins-*.xml` file will have a unique URL that is added to the JetBrains IDE 
[Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos). 

### Format of updatePlugins.xml File
The format of an `updatePlugins.xml` file is simply a list of sequential elements that describe each plugin:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- 
  The <plugins> element contains the description of the plugins available at this repository. Required. 
-->
<plugins>
  <!-- 
    Each <plugin> element describes one plugin in the repository. Required.
    id - used by JetBrains IDEs to uniquely identify a plugin. Required. Must match <id> in plugin.xml
    url - path to download the plugin JAR/ZIP file. Required. Must be HTTPS
    version - version of this plugin. Required. Must match <version> in plugin.xml
  -->
  <plugin id="fully.qualified.id.of.this.plugin" url="https://www.mycompany.com/my_repository/mypluginname.jar" version="major.minor.update">
    <!--
      The <idea-version> element must match the same element in plugin.xml. Required.
    -->
    <idea-version since-build="181.3" until-build="191.*" />
  </plugin>
  <plugin id="id.of.different.plugin" url="https://www.otherserver.com/other_repository/differentplugin.jar" version="major.minor">
    <idea-version since-build="181.3" until-build="191.*" />
  </plugin>
  <plugin>
    <!-- And so on for other plugins... -->
  </plugin>
</plugins>
```

**Note:** 
* An `updatePlugins.xml` file must contain at least one set of `<plugin></plugin>` elements.  
* A plugin `id` may be listed only once in an `updatePlugins.xml` file.  
* Multiple plugins with the same `id` but different `idea-version` attributes must be split into separate `updatePlugins-*.xml` files. 

### Optional updatePlugin.xml Elements
Can additional elements be added to `updatePlugins.xml`? Yes, but it's advisable only if needed. The additional elements will have
to be synchronized with each plugin's `plugin.xml` file.

During plugin installation the JetBrains IDE reads the plugin JAR/ZIP file, and thereafter displays more information about the plugin.
What additional information might help a user select a plugin when 
[browsing the custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos)
before installation? The answer depends on the plugins and repository consumers. Here are the candidate elements:

| Element                                                      |  Effects & Requirements     |
|:-------------------------------------------------------------|:----------------------------| 
| `<name>`<br>My Plugin Name<br>`</name>`                      | By default the name of the plugin JAR/ZIP file is displayed before installation. <br>Using the `<name>` element displays the name of the plugin. <br>Contents should match the `<name>` element contents in the plugins's `plugin.xml` file to avoid confusion. |
| `<description>`<br>My plugin is awesome<br>`</description>`  | By default no description for the plugin is displayed before installation. <br>Using the `<description>` element will cause a description to be displayed before installation. <br>Contents should match the `<description>` element contents in the plugins's `plugin.xml` file to avoid confusion. <br>Optionally, an enclosing `<![CDATA[ ]]>` element can be used if the description needs to contain HTML tags. |
| `<change-notes>`<br>Added cool feature<br>`</change-notes>`  | By default no change notes for the plugin are displayed before installation. <br>Using the `<change-notes>` element will cause a description of changes to be displayed before installation. <br>Contents should match the `<change-notes>` element contents in the plugin's `plugin.xml` file to avoid confusion. <br>Optionally, an enclosing `<![CDATA[ ]]>` element can be used if the change notes need to contain HTML tags. |

