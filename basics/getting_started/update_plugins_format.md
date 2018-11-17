---
title: Publishing a Plugin to an Enterprise Plugin Repository
---

If you intend to use a plugin repository _other than_ the [JetBrains Plugin Repository](https://plugins.jetbrains.com), 
you will need to:
* Upload your plugin JAR/ZIP file to the web server you are using for the enterprise repository.
* Maintain an `updatePlugins.xml` file on that web server describing all the available plugins.
* Add the URL for the enterprise repository to the JetBrains IDE [Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

## Describing Your Plugins in an updatePlugins File
Every enterprise plugin repository must have at least one `updatePlugins.xml` file to describe the latest compatible version 
for every hosted plugin. The description in `updatePlugins.xml` is used by JetBrains IDEs to locate plugins by attributes 
such as vendor, functional category, version, etc. These attributes are displayed by JetBrains IDEs to help users select or upgrade plugins.
The description also tells the JetBrains IDE where to download the plugin itself.

@chkrv - The following description of mapping updatePlugins-*.xml files to unique URLs assumes JetBrains IDEs can't select
the correct file based on the naming convention. Is that correct? 

An enterprise plugin repository's `updatePlugins.xml` file is constructed and maintained by the administrator of
the repository. More than one `updatePlugins.xml` file will be required if consumers of the enterprise repository are using more
than one version of a JetBrains IDE. For example `updatePlugins-182.xml`, `updatePlugins-183.xml` for IntelliJ IDEA 2018.2 and 2018.3 respectively.
Each `updatePlugins-*.xml` file will have a unique, IDE version-specific URL that is added to the JetBrains IDE 
[Repository Settings/Preferences](https://www.jetbrains.com/help/idea/managing-plugins.html#repos). 

### The Format of an updatePlugins File
The overall structure of a `updatePlugins.xml` file is:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<plugin-repository>
  <ff>"category_1"</ff>
  <category name="category_1">
    <!-- One or more plug descriptions -->
    <idea-plugin></idea-plugin>
  </category>
  <ff>"category_2"</ff>
  <category name="category_2">
    <!-- One or more plug descriptions -->
    <idea-plugin></idea-plugin>
  </category>
  <ff>"category_3"</ff>
  <category name="category_3">
    <!-- One or more plug descriptions -->
    <idea-plugin></idea-plugin>
  </category>
  <ff>"category_n"</ff>
  <category name="category_n">
    <!-- One or more plug descriptions  -->
    <idea-plugin></idea-plugin>
  </category>
</plugin-repository>
```

#### Describing Categories of Plugins
Within the `<plugin-repository>` elements is a sequential list of categories of plugins available in the repository. The
valid plugin categories are listed at the bottom of product-specific pages at the 
[JetBrains Plugin Repository](https://plugins.jetbrains.com/idea). For example,
"Administration Tools", "Graphics", "Navigation", etc. 

Each category section begins with the `<ff></ff>` and `<category>` elements, and ends with a `</category>` element. Note:
* The `<ff></ff>` elements only appear at the start of a category.
* The string contained withing the `<ff>` elements must exactly match the `name` attribute of the `<category>` element.
* Categories should only appear if at least one plugin exists in that category.


#### Describing Individual Plugins
Each available plugin is described between `<idea-plugin></idea-plugin>` elements.
As shown below, not all elements and attributes are required because some are specific
to the [JetBrains Plugin Repository](https://plugins.jetbrains.com). 

```xml
<!--
  The idea-plugin element is required to identify this plugin for the plugin repository.
  downloads - an integer describing the number of times this plugin has been downloaded (optional)
  size -  of JAR as an integer # of Bytes  (required)
  url -  full path to download JAR directly  (required)
  date - latest revision date of plugin in Unix aka POSIX time format (optional)
-->
<idea-plugin downloads='3298' size='8454' url='https://download.mycompany.com/mypluginname.jar' date='1429634833000' >
  <!-- 
    The name and id elements are required. The name is displayed on the plugin repository.
    The id is used by JetBrains IDEs to uniquely identify a plugin             
  -->
  <name>My Plugin Name</name>
  <id>fully.qualified.id.of.this.plugin</id>
  <!-- Description is optional. It is displayed on the plugin repository.  -->
  <description><![CDATA[A description of the plugin. Can include html tags.]]></description>
  <!-- The version element is required, and is used to signal upgrade availability to users -->
  <version>major.minor.update</version>
  <!--
    The vendor element is required.
    email - support or general contact (optional)
    url - company address (optional)
  -->
  <vendor email='support.me@mycompany.com' url='https://mycompany.com'>Company name or developer name</vendor>
  <!--
    The idea-version element defines the compatibility of this plugin version with IntelliJ Platform builds.
    It is required. 
    Build numbers are from https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html
    since-build - the earliest IntelliJ Platform build supporting this plugin.  (required)
    until-build - the latest IntelliJ Platform build supporting this plugin.  (optional)
  -->
  <idea-version since-build='131.0' until-build=''/>
  <!-- Change notes are optional but highly recommended. They are displayed on the plugin repository. -->
  <change-notes><![CDATA[Description of plugin changes for user. Can include html tags.]]></change-notes>
  <!-- 
    Required if the plugin depends on other plugins.
    Sets of elements can be repeated for multiple dependencies 
  -->
  <depends>fully.qualified.id.of.required.plugin</depends>
  <!-- 
    Tag elements are optional, but raise the visibility of a plugin.
    Sets of elements can be repeated for multiple tags 
  -->
  <tags>a_category</tags>
  <!-- Rating is optional. Rating has values 0.0 to 5.0  --> 
  <rating>#.#</rating> 
</idea-plugin>
```

### Format of updatePlugins File for Private Repositories
The `updatePlugins.xml` file for private repositories differs only in that the `<plugins>` element is added
and `<plugin>` replaces `<idea-plugin>`. Otherwise, elements permitted between 
`<idea-plugin>` elements are also permitted between `<plugin>` elements. The example
below should be considered the minimum information that should be supplied.

**Note:** Conflicting information from e.g. `<version>` or `<id>`elements causes the respective 
`<plugin>` attribute information to be replaced. To avoid mistakes and confusion, using
these elements is not recommended with `<plugin>`. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- The plugins element is required. -->
<plugins>
  <!-- 
    The plugin element is required.
    id - used by JetBrains IDEs to uniquely identify a plugin
    url - path to download JAR directly
    version - version of this plugin.
  -->
  <plugin id="fully.qualified.id.of.this.plugin" url="https://my_repository.mycompany.com/mypluginname.jar" version="major.minor.update">
    <!--
      The idea-version element defines the compatibility of this plugin version with IntelliJ Platform builds.
      It is required. 
      Build numbers are from https://www.jetbrains.org/intellij/sdk/docs/basics/getting_started/build_number_ranges.html
      since-build - the earliest IntelliJ Platform build supporting this plugin.  (required)
      until-build - the latest IntelliJ Platform build supporting this plugin.  (optional)
    -->
    <idea-version since-build="181.3" until-build="181.*" />
    <!-- Description is optional. It is displayed on the plugin repository.  -->
    <description><![CDATA[A description of the plugin. Can include html tags.]]></description>
  </plugin>
</plugins>

```