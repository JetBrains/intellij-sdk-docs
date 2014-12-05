Plugin configuration file
=======
The main plugin functionality description should be placed in META_INF/plugin.xml
This configuration file contains description, components, information about dependencies and user-defined extension points

    <idea-plugin version="2" url="www.jetbrains.com" use-idea-classloader="true">
      <id>Tutorial</id>
      <name>Basic plugin example</name>
      <version>1.0</version>
      <vendor email="support@jetbrains.com" url="http://www.jetbrains.com">JetBrains</vendor>
      <category>Samples</category>
      <vendor email="support@jetbrains.com" url="http://jetbrains.com">JetBrains</vendor>
      <description><![CDATA[
          Illustration of configuration options.<br>
          <em>most HTML tags may be used</em>
        ]]></description>
      <change-notes><![CDATA[
          Initial release.<br>
          <em>most HTML tags may be used</em>
        ]]>
      </change-notes>

      <!-- http://confluence.jetbrains.com/display/IDEADEV/Build+Number+Ranges for description -->
      <idea-version since-build="139"/>
      <!-- http://confluence.jetbrains.com/display/IDEADEV/Plugin+Compatibility+with+IntelliJ+Platform+Products -->
      <!-- The unique identifiers of the plugins on which this plugin depends -->
      <!-- com.intellij.modules.lang is included in every IntelliJ-based IDE, plugin will be available as well -->
      <depends>com.intellij.modules.lang</depends>
      <!-- Optional dependency on another plugin. If the plugin with the "MySecondPlugin" ID is installed, the contents of mysecondplugin.xml (the format of this file conforms to the format of plugin.xml) will be loaded. -->
      <depends optional="true" config-file="custom-plugin.xml">CustomPlugin</depends>

      <!-- Allows a plugin to integrate its help system (in JavaHelp format) with the IDEA help system. The "file" attribute specifies the name of the JAR file
             in the "help" subdirectory of the plugin directory. The "path" attribute specifies the name of the helpset file within the JAR file.-->
      <helpset file="plugin-help.jar" path="/Help.hs" />

      <!-- Minimum and maximum build of IDEA compatible with the plugin -->
      <idea-version since-build="139.000" until-build="999"/>

      <!-- Resource bundle from which the text of plugin descriptions, action names and etc. will be loaded -->
      <resource-bundle>org.jetbrains.plugins.sample.PluginSampleBundle</resource-bundle>

      <!-- Plugin's application components -->
      <application-components>
        <component>
          <!-- Component's interface class -->
          <interface-class>org.jetbrains.plugins.sample.DummyApplicationComponent</interface-class>
          <!-- Component's implementation class -->
          <implementation-class>org.jetbrains.plugins.sample.DummyApplicationComponentImpl</implementation-class>
        </component>
      </application-components>

      <!-- Plugin's project components -->
      <project-components>
        <component>
          <!-- Interface and implementation classes are the same -->
          <interface-class>org.jetbrains.plugins.sample.DummyProjectComponent</interface-class>
          <implementation-class>org.jetbrains.plugins.sample.DummyProjectComponentImpl</implementation-class>
          <!-- If the "workspace" option is set "true", the component saves its state to the .iws file
            instead of the .ipr file.  Note that the <option> element is used only if the component implements the JDOMExternalizable interface. Otherwise, the use of the <option> element takes no effect.-->

          <option name="workspace" value="true" />
          <!-- If the "loadForDefaultProject" tag is present, the project component is instantiated also for the default project. -->
          <loadForDefaultProject/>
        </component>
      </project-components>

      <!-- Plugin's module components -->
      <module-components>
        <component>
          <interface-class>org.jetbrains.plugins.sample.DummyModuleComponent</interface-class>
          <implementation-class>org.jetbrains.plugins.sample.DummyModuleComponentImpl</implementation-class>
        </component>
      </module-components>

      <!-- Actions -->
      <actions>
        <action id="PluginSample.DummyAction" class="org.jetbrains.plugins.sample.actions.SimpleAction" text="Dummy Action" description="Illustrates how to plug an action in">
          <keyboard-shortcut first-keystroke="control alt A" second-keystroke="C" keymap="$default"/>
        </action>
      </actions>

      <!-- Extension points defined by the plugin. Extension points are registered by a plugin so that other plugins can provide this plugin
             with certain data. The "beanClass" attribute specifies the class the implementations of which can be used for the extension point. -->
      <!--<extensionPoints>-->
        <!--<extensionPoint name="testExtensionPoint" beanClass="com.foo.impl.MyExtensionBean"/>-->
      <!--</extensionPoints>-->

      <!-- Extensions which the plugin adds to extension points defined by the IDEA core or by other plugins.      The "defaultExtensionNs " attribute must be set to the ID of the plugin defining the extension point,
    or to "com.intellij" if the extension point is defined by the IDEA core. The name of the
            tag within the <extensions> tag matches the name of the extension point, and the "implementation" class specifies the name of the
            class added to the extension point. -->
      <extensions >
        <!--<testExtensionPoint implementation="com.foo.impl.MyExtensionImpl"/>-->
      </extensions>
    </idea-plugin>

--------------------

[Link to repository](https://github.com/JetBrains/intellij-community/blob/master/plugins/devkit/resources/META-INF/plugin.xml)



