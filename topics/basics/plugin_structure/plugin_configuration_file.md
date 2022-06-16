[//]: # (title: Plugin Configuration File)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

The following is a sample plugin configuration file.
This sample showcases and describes all elements that can be used in the <path>plugin.xml</path> file.
Additional information about configuring `<actions>` is available in the [Actions](basic_action_system.md#registering-actions) section in Part II.

Limited HTML elements are allowed within `<description>` and `<change-notes>` elements.
However, content containing HTML elements must be surrounded by `<![CDATA[  ]]>` tags.
Allowed HTML elements include text formatting, paragraphs, and lists.

When using Gradle, a number of metadata elements will be provided at build time by [`patchPluginXml` task](gradle_guide.md#patching-the-plugin-configuration-file).

> Please make sure to follow the guidelines from [Plugin Overview page](https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html) for an optimal presentation of your plugin on JetBrains Marketplace.
>
> This webinar also discusses _5 tips for optimizing JetBrains Marketplace plugin page_
> <video href="oB1GA9JeeiY" title="Busy plugin developers series. Episode 2" width="300"/>
>
> See also [](marketing.md) about widgets and badges.
>
{type="tip"}

```xml
<!--
An optional "url" attribute specifies the link to the plugin homepage.
It is displayed on the plugin page in the Marketplace.
-->
<idea-plugin url="https://example.com/my-plugin-site">

  <!--
  Unique identifier of the plugin. It should be a fully qualified name
  including namespace to not collide with existing plugins. It cannot be
  changed between the plugin versions. If not specified, <name> will be
  used (not recommended).
  -->
  <id>com.example.myplugin</id>

  <!-- Public plugin name. It should use Title Cases. Guidelines:
  https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-name
  -->
  <name>My Framework Support</name>

  <!--
  Plugin version. Plugins uploaded to the Marketplace must follow
  the semantic versioning: https://plugins.jetbrains.com/docs/marketplace/semver.html.
  It is displayed in the "Plugins" settings dialog and in the Marketplace
  plugin page.
  -->
  <version>1.0.0</version>

  <!--
  Vendor name or Organization ID (if you have one created).
  Attributes:
   - "url" (optional) - specifies the link to the vendor's homepage
   - "email" (optional) - specifies the vendor's email address
  Displayed on the Plugins Page.
  -->
  <vendor
      url="https://plugins.jetbrains.com/my-company"
      email="contact@example.com">My Company</vendor>

  <!--
  IMPORTANT: This tag should not be used in free plugins.
  If you decide to make your plugin paid, you will need to define
  the parameters in the <product-descriptor> tag.
  You can also enable free functionality in a paid plugin. Learn more in
  a guide to selling plugin: https://plugins.jetbrains.com/build-and-market
  -->
  <product-descriptor
      code="PMYPLUGIN"
      release-date="20210901"
      release-version="20211"
      optional="true"/>

  <!-- Minimum and maximum IDE build versions compatible with the plugin. -->
  <idea-version since-build="193" until-build="193.*"/>

  <!--
  Plugin description displayed on the Marketplace plugin page and in
  the IDE Plugin Manager.
  Simple HTML elements (text formatting, paragraphs, lists, etc.) can be
  added inside of <![CDATA[ ]]> tag. Guidelines:
  https://plugins.jetbrains.com/docs/marketplace/plugin-overview-page.html#plugin-description
  -->
  <description>
  <![CDATA[
    Provides support for <a href="https://example.com/my-framework">My
    Framework</a>.
    <p>Includes support for:
    <ul>
      <li>code completion</li>
      <li>references</li>
      <li>refactoring</li>
    </ul>
    </p>
  ]]>
  </description>

  <!--
  Short summary of new features and bugfixes in the latest plugin version.
  Displayed on the Marketplace plugin page and in the IDE Plugin Manager.
  Simple HTML elements can be included between <![CDATA[  ]]> tags.
  -->
  <change-notes>Initial release of the plugin.</change-notes>

  <!--
  Product and plugin compatibility requirements. Read more:
  https://plugins.jetbrains.com/docs/intellij/plugin-compatibility.html
  -->
  <depends>com.intellij.modules.platform</depends>
  <depends>com.example.third-party-plugin</depends>

  <!--
  Optional dependency on another plugin. If the plugin with the
  "com.example.my-second-plugin" ID is installed, the contents
  of "mysecondplugin.xml" (the format of this file conforms to
  the format of plugin.xml) will be loaded.
  -->
  <depends
      optional="true"
      config-file="mysecondplugin.xml">com.example.my-second-plugin</depends>

  <!--
  Resource bundle (/messages/MyPluginBundle.properties) to be used with
  "key" attributes in extension points and implicit keys like
  "action.[ActionID].text|description".
  -->
  <resource-bundle>messages.MyPluginBundle</resource-bundle>

  <!--
  Extension points defined by the plugin. Extension points are registered
  by a plugin so that other plugins can provide this plugin with certain
  data. Read more:
  https://plugins.jetbrains.com/docs/intellij/plugin-extension-points.html
  -->
  <extensionPoints>
    <extensionPoint
        name="testExtensionPoint"
        beanClass="com.example.impl.MyExtensionBean"/>
    <applicationService
        serviceImplementation="com.example.impl.MyApplicationService"/>
    <projectService
        serviceImplementation="com.example.impl.MyProjectService"/>
  </extensionPoints>

  <!--
  Application-level listeners. For more information, see:
  https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html#defining-application-level-listeners
  -->
  <applicationListeners>
    <listener
        class="com.example.impl.MyListener"
        topic="com.intellij.openapi.vfs.newvfs.BulkFileListener"/>
  </applicationListeners>

  <!--
  Project-level listeners. For more information, see:
  https://plugins.jetbrains.com/docs/intellij/plugin-listeners.html#defining-project-level-listeners
  -->
  <projectListeners>
    <listener
        class="com.example.impl.MyToolwindowListener"
        topic="com.intellij.openapi.wm.ex.ToolWindowManagerListener"/>
  </projectListeners>

  <!--
  Actions. For more information, see:
  https://plugins.jetbrains.com/docs/intellij/basic-action-system.html
  -->
  <actions>
    <action
        id="VssIntegration.GarbageCollection"
        class="com.example.impl.CollectGarbage"
        text="Collect _Garbage"
        description="Run garbage collector">
      <keyboard-shortcut
          first-keystroke="control alt G"
          second-keystroke="C"
          keymap="$default"/>
    </action>
  </actions>

  <!-- Custom extensions declaration. For more information, see:
  https://plugins.jetbrains.com/docs/intellij/plugin-extensions.html#declaring-extensions
  -->
  <extensions defaultExtensionNs="VssIntegration">
    <myExtensionPoint implementation="com.example.impl.MyExtensionImpl"/>
  </extensions>


  <!--
  DEPRECATED: Do not use in new plugins!
  Plugin's application components.
  See https://plugins.jetbrains.com/docs/intellij/plugin-components.html
  for migration steps.
  -->
  <application-components>
    <component>
      <!-- Component's interface class -->
      <interface-class>com.example.Component1Interface</interface-class>
      <!-- Component's implementation class -->
      <implementation-class>com.example.impl.Component1Impl</implementation-class>
    </component>
  </application-components>

  <!--
  DEPRECATED: Do not use in new plugins!
  Plugin's project components.
  See https://plugins.jetbrains.com/docs/intellij/plugin-components.html
  for migration steps.
  -->
  <project-components>
    <component>
      <!-- Interface and implementation classes are the same -->
      <implementation-class>com.example.Component2</implementation-class>

      <!--
      If the "workspace" option is set "true", the component saves its state
      to the .iws file instead of the .ipr file. Note that the <option>
      element is used only if the component implements the
      JDOMExternalizable interface. Otherwise, the use of the <option>
      element takes no effect.
      -->
      <option name="workspace" value="true"/>

      <!--
      If the "loadForDefaultProject" tag is present, the project component
      is instantiated also for the default project.
      -->
      <loadForDefaultProject/>
    </component>
  </project-components>

  <!--
  DEPRECATED: Do not use in new plugins!
  Plugin's module components.
  See https://plugins.jetbrains.com/docs/intellij/plugin-components.html
  for migration steps.
  -->
  <module-components>
    <component>
      <implementation-class>com.example.Component3</implementation-class>
    </component>
  </module-components>
</idea-plugin>
```
