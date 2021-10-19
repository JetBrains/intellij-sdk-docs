[//]: # (title: Plugin Configuration File)

<!-- Copyright 2000-2021 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The following is a sample plugin configuration file.
This sample showcases and describes all elements that can be used in the <path>plugin.xml</path> file.
Additional information about configuring `<actions>` is available in the [Actions](basic_action_system.md#registering-actions) section in Part II.

Limited HTML elements are allowed within `<description>` and `<change-notes>` elements.
However, content containing HTML elements must be surrounded by `<![CDATA[  ]]>` tags.
Allowed HTML elements include text formatting, paragraphs, and lists.

When using Gradle, a number of metadata elements will be provided at build time by [`patchPluginXml` task](gradle_guide.md#patching-the-plugin-configuration-file).

```xml
<!-- An optional `url` attribute specifies the link to the plugin homepage. Displayed on the Plugin Page. -->
<idea-plugin url="https://plugins.jetbrains.com">

  <!-- Unique identifier of the plugin. It should be FQN. It cannot be changed between the plugin versions.
       If not specified, <name> will be used (not recommended). -->
  <id>org.jetbrains.plugins.template</id>

  <!-- Public plugin name should be written in Title Case. Guidelines: https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-name -->
  <name>Plugin Template</name>

  <!-- Plugin version. It is recommended to use the SemVer approach: https://semver.org
       Displayed in the "Plugins" settings dialog and the plugin repository Web interface. -->
  <version>1.0.0</version>

  <!-- A displayed Vendor name or Organization ID (if you have one created. The optional `URL` attribute specifies
       the link to the vendor’s homepage.The optional `email` attribute specifies the vendor’s e-mail address.
       Displayed on the Plugins Page. -->
  <vendor url="https://plugins.jetbrains.com" email="marketplace@jetbrains.com">JetBrains</vendor>

  <!-- IMPORTANT: This tag should not be used in case of free plugins.
       If you decide to make your plugin paid, you will need to define the parameters in the <product-descriptor> tag.
       You can also enable free functionality in a paid plugin. Learn more in a guide to selling plugin:
       https://plugins.jetbrains.com/build-and-market -->
  <product-descriptor code="PLUGINTEMPLATE" release-date="20210901" release-version="20211" optional="true"/>

  <!-- Minimum and maximum build version of IDE compatible with the plugin. -->
  <idea-version since-build="193" until-build="193.*"/>

  <!-- Description of the plugin displayed on the Plugin Page and IDE Plugin Manager.
       Simple HTML elements ( text formatting, paragraphs, and lists) can be added inside of <![CDATA[ ]]> tag.
       Guidelines: https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-description -->
  <description>
  <![CDATA[
    Provides a boilerplate template for easier plugin creation. <br/>
    Speed up the setup phase of plugin development for both new and experienced developers.
  ]]>
  </description>

  <!-- Short summary of new features and bugfixes in the latest plugin version.
       Displayed on the Plugin Page and IDE Plugin Manager. Simple HTML elements can be included between <![CDATA[  ]]> tags. -->
  <change-notes>Initial release of the plugin.</change-notes>

  <!-- Product and plugin compatibility requirements. Read more: https://plugins.jetbrains.com/docs/intellij/plugin-compatibility.html -->
  <depends>com.intellij.modules.platform</depends>
  <depends>com.third.party.plugin</depends>

  <!-- Optional dependency on another plugin. If the plugin with the "com.MySecondPlugin" ID is installed,
       the contents of mysecondplugin.xml (the format of this file conforms to the format of plugin.xml) will be loaded. -->
  <depends optional="true" config-file="mysecondplugin.xml">com.MySecondPlugin</depends>

  <!-- Resource bundle (/messages/MyPluginBundle.properties) to be used with `key` attributes in extension points
       and implicit keys like `action.[ActionID].text|description`. -->
  <resource-bundle>messages.MyPluginBundle</resource-bundle>

  <!-- Extension points defined by the plugin. Extension points are registered by a plugin so that other plugins can provide
       this plugin with certain data. Read more: https://plugins.jetbrains.com/docs/intellij/plugin-extension-points.html -->
  <extensionPoints>
    <extensionPoint name="testExtensionPoint" beanClass="com.foo.impl.MyExtensionBean"/>
    <applicationService serviceImplementation="com.foo.impl.MyApplicationService"/>
    <projectService serviceImplementation="com.foo.impl.MyProjectService"/>
  </extensionPoints>
  
  <!-- Application-level listeners, see: https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html#defining-application-level-listeners -->
  <applicationListeners>
    <listener class="com.foo.impl.MyListener" topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
  </applicationListeners>

  <!-- Project-level listeners, see: https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html#defining-project-level-listeners -->
  <projectListeners>
    <listener class="com.foo.impl.MyToolwindowListener" topic="com.intellij.openapi.wm.ex.ToolWindowManagerListener"/>
  </projectListeners>

  <!-- Actions, see: https://plugins.jetbrains.com/docs/intellij/basic-action-system.html -->
  <actions>
    <action id="VssIntegration.GarbageCollection" class="com.foo.impl.CollectGarbage" text="Collect _Garbage" description="Run garbage collector">
      <keyboard-shortcut first-keystroke="control alt G" second-keystroke="C" keymap="$default"/>
    </action>
  </actions>

  <!-- Custom extensions declaration. Read more: https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html#declaring-extensions -->
  <extensions defaultExtensionNs="VssIntegration">
    <myExtensionPoint implementation="com.foo.impl.MyExtensionImpl"/>
  </extensions>
  
  
  <!-- DEPRECATED: Plugin's application components / do not use in new plugins.
       See https://plugins.jetbrains.com/docs/intellij/plugin-components.html for migration steps. -->
  <application-components>
    <component>
      <!-- Component's interface class -->
      <interface-class>com.foo.Component1Interface</interface-class>

      <!-- Component's implementation class -->
      <implementation-class>com.foo.impl.Component1Impl</implementation-class>
    </component>
  </application-components>

  <!-- DEPRECATED: Plugin's project components - do not use in new plugins.
       See https://plugins.jetbrains.com/docs/intellij/plugin-components.html for migration steps. -->
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

  <!-- DEPRECATED: Plugin's module components - do not use in new plugins.
       See https://plugins.jetbrains.com/docs/intellij/plugin-components.html for migration steps. -->
  <module-components>
    <component>
      <implementation-class>com.foo.Component3</implementation-class>
    </component>
  </module-components>
</idea-plugin>
```
