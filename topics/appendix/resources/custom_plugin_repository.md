<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Custom Plugin Repository

<link-summary>Creating custom plugin repositories other than JetBrains Marketplace.</link-summary>

<tldr>

**Product Help**: [Repository Settings](https://www.jetbrains.com/help/idea/managing-plugins.html#repos)

</tldr>

> [IDE Provisioner](https://www.jetbrains.com/ide-services/ide-provisioner/) comes with a local built-in repository of IntelliJ Platform plugins
> that allows choosing specific plugins to be approved within an organization.
> It also allows uploading and distributing private plugins inside an organization, as well as any publicly available plugin from the internet.
>
{title="JetBrains IDE Services: IDE Provisioner"}

To use a plugin repository _other than_ the [JetBrains Marketplace](https://plugins.jetbrains.com), perform the following steps:

<procedure title="Setting Up Custom Plugin Repository">

1. Create and maintain an <path>updatePlugins.xml</path><sup>1</sup> file on the HTTPS web server used for the custom repository.
   This file describes all the plugins available in the custom repository and each plugin's download URL.
2. Upload the plugin distribution JAR/ZIP file to an HTTPS web server.
   This can be the same web server used for the custom repository or a different HTTPS web server.
3. Add the URL for the custom repository to the JetBrains IDE [Repository Settings](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

<sup>1</sup> The <path>updatePlugin.xml</path> filename is not fixed and can be different.

</procedure>

> Gradle plugin [IntelliJ plugin uploader](https://github.com/brian-mcnamara/plugin_uploader) can be used to automate deployment.
>
{title="Automating Deployment"}

To avoid collisions between private plugins and those hosted on JetBrains Marketplace, an organization can [reserve plugin IDs](https://plugins.jetbrains.com/docs/marketplace/reserved-plugin-ids.html).

To provide custom authentication, implement [`PluginRepositoryAuthProvider`](%gh-ic%/platform/platform-impl/src/com/intellij/ide/plugins/auth/PluginRepositoryAuthProvider.java)
registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.pluginRepositoryAuthProvider"/></include>.

## Describing Plugins in `updatePlugins.xml` File

Every custom plugin repository must have at least one <path>updatePlugins.xml</path> file to describe every hosted plugin's latest available version.
The description in <path>updatePlugins.xml</path> is used by JetBrains IDEs to locate plugins by attributes such as identifier, IDE version, and plugin version.
These attributes are displayed by JetBrains IDEs to help users select or upgrade plugins.
The description also tells the JetBrains IDE where to download the plugin itself.

A custom plugin repository's <path>updatePlugins.xml</path> file is constructed and maintained by the repository administrator.
More than one <path>updatePlugins.xml</path> file may be required if the custom repository consumers are using more than one version of a JetBrains IDE.
For example, <path>updatePlugins-182.xml</path>, <path>updatePlugins-183.xml</path> for IntelliJ IDEA 2018.2 and 2018.3, respectively.
Each <path>updatePlugins-*.xml</path> file will have a unique URL that is added to the JetBrains IDE [Repository Settings](https://www.jetbrains.com/help/idea/managing-plugins.html#repos).

### Format of `updatePlugins.xml` File

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
   - "id" (required) - used by JetBrains IDEs to uniquely identify
     a plugin. Must match <id> in the plugin.xml file.
   - "url" (required) - URL to download the plugin JAR/ZIP file.
     Must be HTTPS.
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
  <!-- And so on for other plugins... -->
</plugins>
```

**Notes:**

* An <path>updatePlugins.xml</path> file must contain at least one `<plugin>` element.
* A plugin `id` may be listed only once in an <path>updatePlugins.xml</path> file.
* Multiple plugins with the same `id` but different `idea-version` attributes must be split into separate <path>updatePlugins-*.xml</path> files.
  The requesting IDE's version is passed as `build` parameter and can be used for server-side filtering.

### Optional `updatePlugin.xml` Elements

<tldr>

**Reference**: [](plugin_configuration_file.md)

</tldr>

During plugin installation, the IDE reads the plugin JAR/ZIP file and thereafter displays more information about the plugin.
In some cases, additional information included in <path>updatePlugins.xml</path> might help a user select a plugin when [browsing the custom plugin repository](https://www.jetbrains.com/help/idea/managing-plugins.html#repos) before installation.
The decision on what elements should be included in the file depends on the plugins and repository consumers.
It is recommended to avoid adding unnecessary elements as they will have to be synchronized with each plugin's <path>[plugin.xml](plugin_configuration_file.md)</path> file.

The additional candidate elements:

<table>
    <tr>
        <td>
            Element
        </td>
        <td>
            Effects &amp; Requirements
        </td>
    </tr>
    <tr>
        <td>
            <code-block lang="XML">
                &lt;name>
                  My Plugin Name
                &lt;/name>
            </code-block>
        </td>
        <td>
            <p>By default, the name of the plugin JAR/ZIP file is displayed before installation.</p>
            <p>Using the <code>&lt;name></code> element displays the name of the plugin.</p>
            <p>Contents should match the <a href="plugin_configuration_file.md#idea-plugin__name"><code>&lt;name></code></a> element contents in the plugin's <path>plugin.xml</path> file to avoid confusion.</p>
        </td>
    </tr>
    <tr>
        <td>
            <code-block lang="XML">
                &lt;description>
                  My plugin is awesome
                &lt;/description>
            </code-block>
        </td>
        <td>
            <p>By default, no description for the plugin is displayed before installation.</p>
            <p>Using the <code>&lt;description></code> element will cause a description to be displayed before installation.</p>
            <p>Contents should match the <a href="plugin_configuration_file.md#idea-plugin__description"><code>&lt;description></code></a> element contents in the plugin's <path>plugin.xml</path> file to avoid confusion.</p>
            <p>Optionally, an enclosing <code>&lt;![CDATA[ ]]></code> element can be used if the description needs to contain HTML tags.</p>
        </td>
    </tr>
    <tr>
        <td>
            <code-block lang="XML">
                &lt;change-notes>
                  Added cool feature
                &lt;/change-notes>
            </code-block>
        </td>
        <td>
            <p>By default, no change notes for the plugin are displayed before installation.</p>
            <p>Using the <code>&lt;change-notes></code> element will cause a description of changes to be displayed before installation.</p>
            <p>Contents should match the <a href="plugin_configuration_file.md#idea-plugin__change-notes"><code>&lt;change-notes></code></a> element contents in the plugin's <path>plugin.xml</path> file to avoid confusion.</p>
            <p>Optionally, an enclosing <code>&lt;![CDATA[ ]]></code> element can be used if the change notes need to contain HTML tags.</p>
        </td>
    </tr>
    <tr>
        <td>
            <code-block lang="XML">
                &lt;depends>
                  dependency.plugin.id
                &lt;/depends>
            </code-block>
        </td>
        <td>
            <p>Adding a plugin dependency in the <code>&lt;depends></code> element will cause asking a user about enabling the dependency plugin, if it is installed and disabled in the IDE. A plugin can specify multiple <code>&lt;depends></code> elements.</p>
            <p>A plugin entry should include only the dependencies on other plugins that are defined by the <a href="plugin_configuration_file.md#idea-plugin__depends"><code>&lt;depends></code></a> elements in the plugin's <path>plugin.xml</path> file. The <code>optional</code> and <code>config-file</code> attributes are ignored and shouldn't be specified.</p>
        </td>
    </tr>
</table>
