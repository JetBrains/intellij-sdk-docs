---
title: Describing Your Plugin in an updatePlugins.xml File
---

When a plugin is deployed to a server - either private or the
[JetBrains Marketplace](https://plugins.jetbrains.com/marketplace) -
an `updatePlugins.xml` file is used to describe the plugin. This 
applies to both the JetBrains Marketplace and [JetBrains Plugin Repository](https://plugins.jetbrains.com/).

The `updatePlugins.xml` description is used by JetBrains IDEs to locate
plugins by attributes such as vendor, functional category, version, etc.
These attributes are displayed by the IDEs to users to help them select or
upgrade plugins.

The syntax of the `updatePlugins.xml` file varies *slightly* depending on
whether it is on a private server or JetBrains Marketplace, but the differences are
small.

### Format of updatePlugins File for JetBrains Marketplace
The format of the `updatePlugins.xml` file is used for both the JetBrains Marketplace and JetBrains
Plugin Repository.

As shown below, not all elements are required, but most are recommended to fully
describe a plugin to the repository and IDE users. 


```xml
<?xml version="1.0" encoding="UTF-8"?>
  <!-- 
    The category element is required to group a plugin by functionality. If more than one
    category is appropriate, use the tags element to list other categories.  
    Plugin categories are listed at the bottom of the pages https://plugins.jetbrains.com/<productName> 
  -->
  <category name='a_category'>
    <!--
      The idea-plugin element is required to identify this plugin for the plugin repository.
      downloads - an integer (required)
      size -  of jar as an integer # of Bytes  (required)
      url -  path to download jar directly  (optional, unless the plugin is hosted elsewhere.)
      date - latest revision date of plugin in Unix aka POSIX time format (required)
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
  </category>
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
    url - path to download jar directly
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