---
title: Plugin Components
---

> **TIP** Due to performance considerations, please consider using [Services](plugin_services.md) instead whenever possible.

Components are the fundamental concept of plugin integration. There are three kinds of components:

* **Application level components** are created and initialized when your IDE starts up. They can be acquired from the [Application](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java) instance by using the `getComponent(Class)` method.
* **Project level components** are created for each [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) instance in the IDE. (Please note that components may be created even for unopened projects.) They can be acquired from the `Project` instance by using the `getComponent(Class)` method.
* **Module level components** are created for each [`Module`](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java) inside every project loaded in the IDE.
Module level components can be acquired from a `Module` instance with the `getComponent(Class)` method.

Every component should have interface and implementation classes specified in the configuration file. The interface class will be used for retrieving the component from other components, and the implementation class will be used for component instantiation.

Note that two components of the same level ([Application](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java), [Project](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) or [Module](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)) cannot have the same interface class. The same class may be specified for both interface and Implementation.

Each component has a unique name which is used for its externalization and other internal needs. The name of a component is returned by its `getComponentName()` method.

## Components naming notation

It is recommended to name components in the form `<plugin_name>.<component_name>`.

## Application level components

An application component that has no dependencies should have a constructor with no parameters which will be used for its instantiation. If an application component depends on other application components, it can specify these components as constructor parameters. The *IntelliJ Platform* will ensure that the components are instantiated in the correct order to satisfy the dependencies.

Application level components must be registered in the `<application-components>` section of the plugin.xml file (see [Plugin Configuration File](plugin_configuration_file.md)).

## Project level components

Optionally, a project level component's implementation class may implement the [ProjectComponent](upsource:///platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java) interface.

The constructor of a project level component can have a parameter of the [Project](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) type, if it needs the project instance.  It can also specify other application-level or project-level components as parameters, if it depends on those components.

Project level components must be registered in the `<project-components>` section of the `plugin.xml` file (see [Plugin Configuration File](plugin_configuration_file.md)).

## Module level components

Optionally, a module level component's implementation class may implement the [ModuleComponent](upsource:///platform/projectModel-api/src/com/intellij/openapi/module/ModuleComponent.java) interface.

The constructor of a module level component can have a parameter of the Module type, if it needs the module instance. It can also specify other application level, project level or module level components as parameters, if it depends on those components.

Module level components must be registered in the `<module-components>` section of the `plugin.xml` file (see [Plugin Configuration File](plugin_configuration_file.md)).

## Persisting the state of components

The state of every component will be automatically saved and loaded if the component's class implements the [JDOMExternalizable](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) (deprecated) or [PersistentStateComponent](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) interface.

When the component's class implements the [PersistentStateComponent](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) interface, the component state is saved in an XML file that you can specify using the [@State](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/State.java) and [@Storage](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/Storage.java) annotations in your Java code.

When the component's class implements the [JDOMExternalizable](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) interface, the components save their state in the following files:

* Project level components save their state to the project (`.ipr`) file.
 
  However, if the workspace option in the `plugin.xml` file is set to `true`, the component saves its configuration to the workspace (`.iws`) file instead.

* Module level components save their state to the module (`.iml`) file.

For more information and samples, refer to [Persisting State of Components](/basics/persisting_state_of_components.md).

## Defaults

The defaults (a component's predefined settings) should be placed in the `<component_name>.xml` file. Place this file in the plugin's classpath in the folder corresponding to the default package. The `readExternal()` method will be called on the `<component>` root tag.

If a component has defaults, the `readExternal()` method is called twice:

* The first time for defaults
* The second time for saved configuration

## Plugin components lifecycle

The components are loaded in the following order:

* Creation - constructor is invoked.
* Initialization - the `initComponent` method is invoked (if the component implements the [BaseComponent](upsource:///platform/core-api/src/com/intellij/openapi/components/BaseComponent.java) interface).
* Configuration - the `readExternal` method is invoked (if the component implements [JDOMExternalizable](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) interface), or the `loadState` method is invoked (if the component implements [PersistentStateComponent](upsource:///platform/projectModel-api/src/com/intellij/openapi/components/PersistentStateComponent.java) and has non-default persisted state).
* For module components, the `moduleAdded` method of the [ModuleComponent](upsource:///platform/projectModel-api/src/com/intellij/openapi/module/ModuleComponent.java) interface is invoked to notify that a module has been added to the project.
* For project components, the `projectOpened` method of the [ProjectComponent](upsource:///platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java) interface is invoked to notify that a project has been loaded.

The components are unloaded in the following order:

* Saving configuration - the `writeExternal` method is invoked (if the component implements the [JDOMExternalizable](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) interface), or the `getState` method is invoked (if the component implements PersistentStateComponent).
* Disposal - the `disposeComponent` method is invoked.

Note that you should not request any other components using the `getComponent()` method in the constructor of your component, otherwise you'll get an assertion. If you need access to other components when initializing your component, you can specify them as constructor parameters or access them in the `initComponent` method.
