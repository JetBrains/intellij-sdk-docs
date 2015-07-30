---
title: Plugin Components
---

Components are the fundamental concept of plugin integration.
There are three kinds of components:
<!-- TODO Table Of Contents -->

*  Application-level

*  Project-level

*  Module-level


Application-level components are created and initialized when IntelliJ IDEA starts up.
They can be acquired from the
[Application](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/application/Application.java)
instance by using the ```getComponent(Class)``` method.

Project-level components are created for each
[Project](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/project/Project.java)
instance in IntelliJ IDEA. (Please note that components may be created even for unopened projects.)
They can be acquired from the Project instance by using the ```getComponent(Class)``` method.

Module-level components are created for each
[Module](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java)
in every project loaded in IntelliJ IDEA.
Module-level components can be acquired from a Module instance with the same method.

Every component should have interface and implementation classes specified in the configuration file.
The interface class will be used for retrieving the component from other components, and the implementation class will be used for component instantiation.
Note that two components of the same level (
[Application](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/application/Application.java),
[Project](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/project/Project.java)
or
[Module](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java)
) cannot have the same interface class.
Interface and implementation classes may be the same.

Each component has a unique name which is used for its externalization and other internal needs.
The name of a component is returned by its ```getComponentName()``` method.

### Components Naming Notation

It is recommended to name components in `<plugin_name>.<component_name>` form.

### Application Level Components

Optionally, application-level component's implementation class may implement the
[ApplicationComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ApplicationComponent.java)
interface.
An application component that has no dependencies should have a constructor with no parameters which will be used for its instantiation.
If an application component depends on other application components, it can specify these components as constructor parameters. IntelliJ IDEA will ensure that the components are instantiated in the correct order to satisfy the dependencies.
Note that application-level components must be registered in the ```<application-components>``` section of the plugin.xml file (see Plugin Configuration File below).

#### Quick creation of application components

*IntelliJ IDEA* suggests a simplified way to create application components, with all the required infrastructure.
The IntelliJ interface will help you declare the application component's implementation class, and will automatically make appropriate changes to the ```<application-components>``` section of the *plugin.xml* file.

**To create and register an application component:**

1.  In your project, open the context menu of the destination package and click *New* (or press *ALT + INSERT*).

2.  In the *New* menu, click *Application Component*.

3.  In the *New Application Component* dialog box that opens, enter the application component name, and then click *OK*.

*IntelliJ IDEA* will generate a new Java class that implements the
[ApplicationComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ApplicationComponent.java)
interface; register the newly created component in the *plugin.xml* file; add a node to the module tree view; and open the created application component class file in the editor.

### Project Level Components

Project-level component's implementation class may implement the
[ProjectComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java)
interface.
The constructor of a project-level component can have a parameter of the
[Project](https://github.com/JetBrains/intellij-community/tree/master/platform/platform-api/src/com/intellij/openapi/project/Project.java)
type, if it needs the project instance.
It can also specify other application-level or project-level components as parameters, if it depends on those components.

Note that project-level components must be registered in the ```<project-components>``` section of the *plugin.xml* file (see Plugin Configuration File below).

#### Quick creation of project components

 <!--TODO Link to demo source code -->
*IntelliJ IDEA* suggests a simplified way to create project components, with all the required infrastructure.
The IDEA interface will help you declare the project component's implementation class, and will automatically make appropriate changes to the ```<project-components>``` section of the `plugin.xml` file.

**To create and register a project component**

1.  In your project, open the context menu of the destination package and click *New* (or press *ALT + INSERT*).

2.  In the *New* menu, click *Project Component*.

3.  In the *New Project Component* dialog box that opens, enter the project component name, and then click *OK*.

*IntelliJ IDEA* will generate a new Java class that implements the
[ProjectComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java)
interface; register the newly created component in the *plugin.xml* file; add a node to the module tree view; and open the created application component class file in the editor.


### Module Level Components

Optionally, Module-level component's implementation class may implement the
[ModuleComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/ModuleComponent.java)
interface.
The constructor of a module-level component can have a parameter of the Module type, if it needs the module instance.
It can also specify other application-level, project-level or module-level components as parameters, if it depends on those components.

Note that module-level components must be registered in the ```<module-components>``` section of the `plugin.xml` file (see Plugin Configuration File below).

#### Quick creation of module components

*IntelliJ IDEA* suggests a simplified way to create module components, with all the required infrastructure.
The IDEA interface will help you declare the module component's implementation class, and will automatically make appropriate changes to the `<module-components>` section of the `plugin.xml` file.

*To create and register a module component*

* In your project, open the context menu of the destination package and click *New* (or press *ALT + INSERT*).
* In the *New* menu, click *Module Component*.
* In the *New Module Component* dialog box that opens, enter the module component name, and then click *OK*.

*IntelliJ IDEA* will generate a new Java class that implements the
[ModuleComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/ModuleComponent.java)
interface; register the newly created component in the `plugin.xml` file; add a node to the module tree view; and open the created application component class file in the editor.

### Persisting State of Components

The state of every component will be automatically saved and loaded if the component's class implements the
[JDOMExternalizable](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java)
(deprecated) or
[PersistentStateComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/components/PersistentStateComponent.java)
interface.

When the component's class implements the
[PersistentStateComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/platform-api/src/com/intellij/openapi/components/PersistentStateComponent.java)
interface, the component state is saved in an XML file that you can specify using the
[@State](https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/components/State.java)
and
[@Storage](https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/components/Storage.java)
annotations in your Java code.

When the component's class implements the
[JDOMExternalizable](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java)
interface, the components save their state in the following files:

*  Project-level components save their state to the project (.ipr) file.
However, if the workspace option in the plugin.xml file is set to _true_, the component saves its configuration to the workspace (.iws) file instead.

*  Module-level components save their state to the module (.iml) file.

For more information and samples, refer to 
[Persisting State of Components](/basics/persisting_state_of_components.html).

### Defaults


The defaults (components' predefined settings) should be placed in the `<component_name>.xml` file.
Place this file in the plugin's classpath in the folder corresponding to the default package.
The ```readExternal()``` method will be called on the `<component>` root tag.

If a component has defaults, the ```readExternal()``` method is called twice:

*  the first time for defaults

*  the second time for saved configuration

### Plugin Components Lifecycle

The components are loaded in the following order:

*  Creation - constructor is invoked.

*  Initialization - the ```initComponent``` method is invoked (if the component implements the
[ApplicationComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ApplicationComponent.java)
interface).

*  Configuration - the ```readExternal``` method is invoked (if the component implements
[JDOMExternalizable](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java)
interface), or the ```loadState``` method is invoked (if the component implements
[PersistentStateComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/PersistentStateComponent.java)
and has non-default persisted state).

*  For module components, the ```moduleAdded``` method of the
[ModuleComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/ModuleComponent.java)
interface is invoked to notify that a module has been added to the project.

*  For project components, the ```projectOpened``` method of the
[ProjectComponent](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/project/ProjectComponent.java)
interface is invoked to notify that a project has been loaded.

The components are unloaded in the following order:

*  Saving configuration - the ```writeExternal``` method is invoked (if the component implements the
[JDOMExternalizable](https://github.com/JetBrains/intellij-community/blob/master/platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java)
interface), or the ```getState``` method is invoked (if the component implements PersistentStateComponent).

* Disposal - the ```disposeComponent``` method is invoked.

Note that you should not request any other components using the ```getComponent()``` method in the constructor of your component, otherwise you'll get an assertion.
If you need access to other components when initializing your component, you can specify them as constructor parameters or access them in the ```initComponent``` method.
