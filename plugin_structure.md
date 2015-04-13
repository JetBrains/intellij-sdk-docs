---
layout: editable
title: Plugin Structure
---

<!--
INITIAL_SOURCE https://confluence.jetbrains.com/display/IDEADEV/IntelliJ+IDEA+Plugin+Structure
-->



Plugins are the only supported way to extend IDEA functionality.
A plugin uses API exposed by IDEA or other plugins to implement its functionality.
This document is focused on the plugin system structure and plugin lifecycle.
It doesn't specify any other APIs that may be used by plugins.

The following subjects are covered:
<!--TODO Links from TOC to certain parts of the document-->

* [Plugin Content](plugin_content.html)

* [Plugin Class Loaders](plugin_class_loaders.html)

* [Plugin Components](plugin_components.html)

* [Plugin Extensions and Extension](TODO)

* [Plugin Actions](TODO)

* [Plugin Services](TODO)

* [Plugin Configuration File](TODO)

* [Plugin Dependencies](TODO)

# Plugin Extensions and Extension Points

Intellij IDEA provides the concept of _extensions_ and _extension points_ that allows a plugin to interact with other plugins or with the IDEA core.

## Extension Points

If you want your plugin to allow other plugins to extend its functionality, in the plugin, you must declare one or several _extension points_.
Each extension point defines a class or an interface that is allowed to access this point.

## Extensions

If you want your plugin to extend the functionality of other plugins or the IDEA core, in the plugin, you must declare one or several _extensions_.

## How to Declare Extensions and Extension Points?

You can declare extensions and extension points in the plugin configuration file plugin.xml, within the ```<extensions>``` and ```<extensionPoints>``` sections, respectively.

*To declare an extension point*

*  In the ```<extensionPoints>``` section, insert a child element ```<extensionPoint>``` that defines the extension point name and the name of a bean class or an interface that is allowed to extend the plugin functionality in the *'name'*, *'beanClass'* and *'interface'* attributes, respectively.

To clarify this procedure, consider the following sample section of the plugin.xml file:

```xml
<extensionPoints>
     <extensionPoint name="MyExtensionPoint1" beanClass="MyPlugin.MyBeanClass1">
     <extensionPoint name="MyExtensionPoint2" interface="MyPlugin.MyInterface">
</extensionPoints>
```

The *interface* attribute sets an interface the plugin that contributes to the extension point must implement.

The *beanClass* attribute sets a bean class that specifies one or several properties annotated with the
[@Attribute](https://github.com/JetBrains/intellij-community/blob/master/xml/dom-openapi/src/com/intellij/util/xml/Attribute.java)
annotation.
The plugin that contributes to the extension point will read those properties from the plugin.xml file. 
To clarify this, consider the following sample ```MyBeanClass1``` bean class used in the above plugin.xml file:

```java
public class MyBeanClass1 extends AbstractExtensionPointBean {
  @Attribute("key")
  public String key;

  @Attribute("implementationClass")
  public String implementationClass;

  public String getKey() {
    return key;
  }

  public String getClass() {
    return implementationClass;
  {

}
```
Note that to declare an extension designed to access the MyExtensionPoint1 extension point, your plugin.xml file must contain the <MyExtensionPoint1> tag with the "key" and "implementationClass" attributes set to appropriate values (see the sample plugin.xml file below).

*To declare an extension*

1. For the \<extensions\> element, set the *xmlns* (deprecated) or *defaultExtensionNs* attribute to one of the following values:

    *  _com.intellij_, if your plugin extends the IDEA core functionality.
    
    *  _ID of a plugin_, if your plugin extends a functionality of another plugin.
     
2. Add a new child element to the \<extensions\> element.
The child element name must match the name of the extension point you want the extension to access.

3. Depending on the type of the extension point, do one of the following:

    *  If the extension point was declared using the *interface* attribute, for newly added child element, set the *implementation* attribute to the name of the class that implements the specified interface.
    
    *  If the extension point was declared using the *beanClass* attribute, for newly added child element, set all attributes annotated with the
    [@Attribute](https://github.com/JetBrains/intellij-community/blob/master/xml/dom-openapi/src/com/intellij/util/xml/Attribute.java)
    annotations in the specified bean class.

To clarify this procedure, consider the following sample section of the plugin.xml file that defines two extensions designed to access the _appStarter_ and _applicationConfigurable_ extension points in the IDEA core and one extension to access the _MyExtensionPoint1_ extension point in a test plugin:

```xml
<!-- Declare extensions to access extension points in the IDEA core. These extension points
      have been declared using the "interface" attribute.
 -->
  <extensions defaultExtensionNs="com.intellij">
     <appStarter implementation="MyTestPackage.MyTestExtension1"></appStarter>
     <applicationConfigurable implementation="MyTestPackage.MyTestExtension2"></applicationConfigurable>
  </extensions>
 <!-- Declare extensions to access extension points in a custom plugin
      The MyExtensionPoint1 extension point has been declared using *beanClass* attribute.
 -->
  <extensions defaultExtensionNs="MyPluginID">
     <MyExtensionPoint1 key="keyValue" implementationClass="MyTestPackage.MyClassImpl"></MyExtensionPoint1>
  </extensions>
```

## How to Get the Extension Points List?

To get a list of extension points available in the *IntelliJ IDEA* core, consult the _\<extensionPoints\>_ section of the following XML configuration files:

* [LangExtensionPoints.xml](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/META-INF/LangExtensionPoints.xml)

* [PlatformExtensionPoints.xml](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/META-INF/PlatformExtensionPoints.xml)

* [VcsExtensionPoints.xml](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-resources/src/META-INF/VcsExtensionPoints.xml)

## Additional Information and Samples

For samples plugins and detailed instructions on how to create your plugin that contributes to the IDEA core, refer to 
[Customizing the IDEA Settings Dialog](TODO)
and
[Creation of Tool Windows](TODO).

# Plugin Actions

*Intellij IDEA* provides the concept of _actions_. 
An action is a class, derived from the
[AnAction](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/actionSystem/AnAction.java)
class, whose ```actionPerformed``` method is called when the menu item or toolbar button is selected. 
The system of actions allows plugins to add their own items to IDEA menus and toolbars.
Actions are organized into groups, which, in turn, can contain other groups. 
A group of actions can form a toolbar or a menu. Subgroups of the group can form submenus of the menu.
You can find detailed information on how to create and register your actions in 
[IntelliJ IDEA Action System](action_system.md).

# Plugin Services

*IntelliJ IDEA* provides the concept of _services_. 
A _service_ is a plugin component loaded on demand, when your plugin calls the ```getService``` method of the 
[ServiceManager](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ServiceManager.java)
class. 
*IntelliJ IDEA* ensures that only one instance of a service is loaded even though the service is called several times. 
A service must have the interface and implementation classes specified in the plugin.xml file.
The service implementation class is used for service instantiation.
*IntelliJ IDEA* offers three types of services: _application level_ services, _project level_ services and _module level_ services.

## How to Declare a Service?

To declare a service, you can use the following extension points in the IDEA core:

* *applicationService*: designed to declare an application level service.

* *projectService*: designed to declare a project level service.

* *moduleService*: designed to declare a module level service.

*To declare a service:*

1. Add the appropriate child element (\<applicationService\>, \<projectService\> or \<moduleService\>) to the \<extensions\> section of the plugin.xml file.

2.  For the newly added child element, set the following attributes:

    *  *serviceInterface*: specifies the service interface class.
    
    *  *serviceImplementation*: specifies the service implementation class.

Note that the interface and implementation classes can be the same.

To clarify the service declaration procedure, consider the following fragment of the plugin.xml file:

```xml
<extensions defaultExtensionNs="com.intellij">
    <!-- Declare the application level service -->
      <applicationService serviceInterface="Mypackage.MyServiceInterfaceClass" serviceImplementation="Mypackage.MyServiceImplClass">
      </applicationService>

    <!-- Declare the project level service -->
      <projectService serviceInterface="Mypackage.MyProjectServiceInterfaceClass" serviceImplementation="Mypackage.MyProjectServiceImplClass">
      </projectService>
 </extensions>
```

### How It Works?

To instantiate your service, in Java code, use the following syntax:

```java
MyServiceImplClass service = ServiceManager.getService(MyServiceImplClass.class);
```


### Sample Plugin

This section allows you to download and install a sample plugin illustrating how to create and use a plugin service.
This plugin has a project component implementing a service that counts a number of currently opened projects in *IntelliJ IDEA*. 
If this number exceeds the maximum allowed number of simultaneously opened projects, the plugin returns an error
message and closes the most recently opened project.

<!-- TODO Replace with other plugin URL when available-->

*To install and run the sample plugin*

*  Click [here](/attachments/MaxOpenedProjects.zip) to download the .Zip archive that contains the sample plugin project.

*  Extract all files from the .Zip archive to a separate folder.

*  Start *IntelliJ IDEA*, on the starting page, click *Open Project*, and then use the *Open Project* dialog box to open the downloaded project *MaxOpenedProjects*.

*  On the main menu, choose *Run \| Run* or press *Shift + F10*.

*  If necessary, change the [Run/Debug Configurations](http://www.jetbrains.com/idea/webhelp/run-debug-configuration-plugin.html).


# Plugin Configuration File plugin.xml

The following is a sample plugin configuration file.
This sample showcases and describes all elements that can be used in the plugin.xml file.

```xml
<!-- url="" specifies the URL of the plugin homepage (displayed in the Welcome Screen and in "Plugins" settings dialog) -->
<idea-plugin url="http://www.jetbrains.com/idea">

    <!-- Plugin name -->
    <name>VssIntegration</name>

    <!-- Unique identifier of the plugin. Cannot be changed between the plugin versions. If not specified, assumed to be equal to <name>. -->
    <id>VssIntegration</id>

    <!-- Description of the plugin. -->
    <description>Vss integration plugin</description>

    <!-- Description of changes in the latest version of the plugin. Displayed in the "Plugins" settings dialog and in the plugin repository Web interface. -->
    <change-notes>Initial release of the plugin.</change-notes>

    <!-- Plugin version -->
    <version>1.0</version>

    <!-- The vendor of the plugin. The optional "url" attribute specifies the URL of the vendor homepage. The optional "email"
           attribute specifies the e-mail address of the vendor. The optional "logo" attribute specifies the path within the plugin JAR
           to a 16x16 icon to be displayed next to the plugin name in the welcome screen.   -->
    <vendor url="http://www.jetbrains.com" email="support@jetbrains.com" logo="icons/plugin.png">Foo Inc.</vendor>

    <!-- The unique identifiers of the plugins on which this plugin depends. -->
    <depends>MyFirstPlugin</depends>
    <!-- Optional dependency on another plugin. If the plugin with the "MySecondPlugin" ID is installed, the contents of mysecondplugin.xml (the format of this file conforms to the format of plugin.xml) will be loaded. -->
    <depends optional="true" config-file="mysecondplugin.xml">MySecondPlugin</depends>

    <!-- Allows a plugin to integrate its help system (in JavaHelp format) with the IDEA help system. The "file" attribute specifies the name of the JAR file
           in the "help" subdirectory of the plugin directory. The "path" attribute specifies the name of the helpset file within the JAR file.-->
    <helpset file="myhelp.jar" path="/Help.hs" />

    <!-- Minimum and maximum build of IDEA compatible with the plugin -->
    <idea-version since-build="3000" until-build="3999"/>

    <!-- Resource bundle from which the text of plugin descriptions, action names and etc. will be loaded -->
    <resource-bundle>messages.MyPluginBundle</resource-bundle>

    <!-- Plugin's application components -->
    <application-components>
        <component>
            <!-- Component's interface class -->
            <interface-class>com.foo.Component1Interface</interface-class>
            <!-- Component's implementation class -->
            <implementation-class>com.foo.impl.Component1Impl</implementation-class>
        </component>
    </application-components>

    <!-- Plugin's project components -->
    <project-components>
        <component>
            <!-- Interface and implementation classes are the same -->
            <interface-class>com.foo.Component2</interface-class>
            <!-- If the "workspace" option is set "true", the component saves its state to the .iws file
              instead of the .ipr file.  Note that the <option> element is used only if the component implements the JDOMExternalizable interface. Otherwise, the use of the <option> element takes no effect.
              -->

            <option name="workspace" value="true" />
            <!-- If the "loadForDefaultProject" tag is present, the project component is instantiated also for the default project. -->
            <loadForDefaultProject>
        </component>
    </project-components>

    <!-- Plugin's module components -->
    <module-components>
        <component>
            <interface-class>com.foo.Component3</interface-class>
        </component>
    </module-components>

    <!-- Actions -->
    <actions>
        <action id="VssIntegration.GarbageCollection" class="com.foo.impl.CollectGarbage" text="Collect _Garbage" description="Run garbage collector">
            <keyboard-shortcut first-keystroke="control alt G" second-keystroke="C" keymap="$default"/>
        </action>
    </actions>

    <!-- Extension points defined by the plugin. Extension points are registered by a plugin so that other plugins can provide this plugin
           with certain data. The "beanClass" attribute specifies the class the implementations of which can be used for the extension point. -->
    <extensionPoints>
        <extensionPoint name="testExtensionPoint" beanClass="com.foo.impl.MyExtensionBean"/>
    </extensionPoints>

    <!-- Extensions which the plugin adds to extension points defined by the IDEA core or by other plugins.      The "defaultExtensionNs " attribute must be set to the ID of the plugin defining the extension point,
 or to "com.intellij" if the extension point is defined by the IDEA core. The name of the
          tag within the <extensions> tag matches the name of the extension point, and the "implementation" class specifies the name of the
          class added to the extension point. -->
    <extensions xmlns="VssIntegration">
        <testExtensionPoint implementation="com.foo.impl.MyExtensionImpl"/>
    </extensions>
</idea-plugin>
```

# Plugin Dependencies

In your plugin, you may depend on classes from other plugins, either bundled, third-party or your own. 
In order to do so, you need to perform the following two steps:

*  Add the jars of the plugin you're depending on to the classpath of your IntelliJ IDEA SDK.
(**Note**: Don't add the plugin jars as a library: this will fail at runtime because IntelliJ IDEA will load two separate copies of the dependency plugin classes.)

*  Add a <depends> tag to your plugin.xml, adding the ID of the plugin you're depending on as the contents of the tag.
For example: 

```xml
<depends>org.jetbrains.idea.maven</depends>
```

To find out the ID of the plugin you're depending on, locate the META-INF/plugin.xml file inside its jar and check the contents of the <id> tag.
