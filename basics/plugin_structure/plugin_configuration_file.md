---
title: Plugin Configuration File - plugin.xml
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The following is a sample plugin configuration file. This sample showcases and describes all elements that can be used in the `plugin.xml` file.
Additional information about configuring `<actions>` is available in the [Actions](/basics/action_system.md#registering-actions) section in Part II.

Limited HTML elements are allowed within `<description>` and `<change-notes>` elements.
However, content containing HTML elements must be surrounded by `<![CDATA[  ]]>` tags. 
Allowed HTML elements include text formatting, paragraphs, and lists. 

When using Gradle, a number of metadata elements will be provided at build time by [`patchPluginXml` task](/tutorials/build_system/gradle_guide.md#patching-the-plugin-configuration-file).

```xml
<!-- `url` specifies the URL of the plugin homepage (can be opened from "Plugins" settings dialog) -->
<idea-plugin url="https://www.jetbrains.com/idea">

  <!-- Plugin name. It should be short and descriptive and in Title Case. 
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface. -->
  <name>Vss Integration</name>

  <!-- Unique identifier of the plugin. Should be FQN.
       Cannot be changed between the plugin versions.
       If not specified, <name> will be used (not recommended). -->
  <id>com.jetbrains.vssintegration</id>

  <!-- Description of the plugin. 
       Should be short and to the point.
       Start the description with a verb in present simple form such as 
       "integrates", "synchronizes", "adds support for" or "lets you view".
       Don’t use marketing adjectives like “simple”, “lightweight”, or “professional”.
       Don’t repeat the name of the plugin.
       For plugins that add language/platform/framework support, the description MUST specify 
       the version of the corresponding language/platform/framework.
       Don't mention the IDE compatibility. E.g. don't say "Adds support to IntelliJ IDEA for..."
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface. 
       Simple HTML elements can be included between <![CDATA[  ]]> tags.  -->
  <description>Integrates Volume Snapshot Service W10</description>

  <!-- Description of changes in the latest version of the plugin.
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface.
       Simple HTML elements can be included between <![CDATA[  ]]> tags.  -->
  <change-notes>Initial release of the plugin.</change-notes>

  <!-- Plugin version
       Recommended format is BRANCH.BUILD.FIX (MAJOR.MINOR.FIX)
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface.  -->
  <version>1.0.0</version>

  <!-- The vendor of the plugin.
       The optional "url" attribute specifies the URL of the vendor homepage.
       The optional "email" attribute specifies the e-mail address of the vendor.
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface. -->
  <vendor url="https://www.company.com" email="support@company.com">A Company Inc.</vendor>

  <!-- Mandatory dependencies on plugins or modules.
       The FQN module names in <depends> elements are used to determine IDE compatibility for the plugin.
       Include at least the module shown below to indicate compatibility with IntelliJ Platform-based products.
       Also include dependencies on other plugins as needed.
       See "Compatibility with Multiple Products" and "Plugin Dependencies" for more information.  -->
  <depends>com.intellij.modules.platform</depends>
  <depends>com.third.party.plugin</depends>

  <!-- Optional dependency on another plugin.
       If the plugin with the "com.MySecondPlugin" ID is installed, the contents of mysecondplugin.xml
       (the format of this file conforms to the format of plugin.xml) will be loaded. -->
  <depends optional="true" config-file="mysecondplugin.xml">com.MySecondPlugin</depends>

  <!-- Minimum and maximum build of IDE compatible with the plugin -->
  <idea-version since-build="193" until-build="193.*"/>

  <!-- Resource bundle (/messages/MyPluginBundle.properties) to be used
       with `key` attributes in extension points and implicit keys like
       `action.[ActionID].text|description` -->
  <resource-bundle>messages.MyPluginBundle</resource-bundle>

  <!-- Plugin's application components / DEPRECATED - do not use in new plugins 
       See https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_components.html for migration steps
  -->
  <application-components>
    <component>
      <!-- Component's interface class -->
      <interface-class>com.foo.Component1Interface</interface-class>

      <!-- Component's implementation class -->
      <implementation-class>com.foo.impl.Component1Impl</implementation-class>
    </component>
  </application-components>

  <!-- Plugin's project components / DEPRECATED - do not use in new plugins 
       See https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_components.html for migration steps
  -->
  <project-components>
    <component>
      <!-- Interface and implementation classes are the same -->
      <implementation-class>com.foo.Component2</implementation-class>

      <!-- If the "workspace" option is set "true", the component
           saves its state to the .iws file instead of the .ipr file.
           Note that the <option> element is used only if the component
           implements the JDOMExternalizable interface. Otherwise, the
           use of the <option> element takes no effect.  -->
      <option name="workspace" value="true" />

      <!-- If the "loadForDefaultProject" tag is present, the project component is instantiated also for the default project. -->
      <loadForDefaultProject/>
    </component>
  </project-components>

  <!-- Plugin's module components / DEPRECATED - do not use in new plugins
       See https://www.jetbrains.org/intellij/sdk/docs/basics/plugin_structure/plugin_components.html for migration steps
  -->
  <module-components>
    <component>
      <implementation-class>com.foo.Component3</implementation-class>
    </component>
  </module-components>

  <!-- Actions -->
  <actions>
    <action id="VssIntegration.GarbageCollection" class="com.foo.impl.CollectGarbage" text="Collect _Garbage" description="Run garbage collector">
      <keyboard-shortcut first-keystroke="control alt G" second-keystroke="C" keymap="$default"/>
    </action>
  </actions>

  <!-- Extension points defined by the plugin.
       Extension points are registered by a plugin so that other
       plugins can provide this plugin with certain data. 
  -->
  <extensionPoints>
    <extensionPoint name="testExtensionPoint" beanClass="com.foo.impl.MyExtensionBean"/>
  </extensionPoints>

  <!-- Extensions which the plugin adds to extension points
       defined by the IntelliJ Platform or by other plugins.
       The "defaultExtensionNs" attribute must be set to the
       ID of the plugin defining the extension point, or to 
       "com.intellij" if the extension point is defined by the
       IntelliJ Platform. The name of the tag within the <extensions>
       tag matches the name of the extension point, and the
       "implementation" class specifies the name of the class
       added to the extension point. -->
  <extensions defaultExtensionNs="VssIntegration">
    <testExtensionPoint implementation="com.foo.impl.MyExtensionImpl"/>
  </extensions>
  
  <!-- Application-level listeners -->
  <applicationListeners>
    <listener class="com.foo.impl.MyListener" topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
  </applicationListeners>

  <!-- Project-level listeners -->
  <projectListeners>
    <listener class="com.foo.impl.MyToolwindowListener" topic="com.intellij.openapi.wm.ex.ToolWindowManagerListener"/>
  </projectListeners>
</idea-plugin>
```
