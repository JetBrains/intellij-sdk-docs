---
title: Plugin Services
---

A _service_ is a plugin component loaded on demand when your plugin calls the `getService()` method of the [`ServiceManager`](upsource:///platform/core-api/src/com/intellij/openapi/components/ServiceManager.java) class.

The *IntelliJ Platform* ensures that only one instance of a service is loaded even though the service is called several times. A service must have an implementation class which is used for service instantiation. A service may also have an interface class which is used to obtain the service instance and provides API of the service. The interface and implementation classes are specified in the `plugin.xml` file.

The *IntelliJ Platform* offers three types of services: _application level_ services, _project level_ services and _module level_ services.

## Light Service

Service that is not going to be overridden. No need to register it in a `plugin.xml`.

To register: annotate class using [@Service](https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/Service.java) annotation. If service is written in Java and not Kotlin, mark class as `final`.
 
Restrictions:

* constructor injection is not supported (since it is deprecated), but project level service can define constructor that accepts `Project`, and module level `Module`.
* if service it is a [PersistentStateComponent](https://www.jetbrains.org/intellij/sdk/docs/basics/persisting_state_of_components.html), roaming must be disabled (`roamingType` is set to `RoamingType.DISABLED`).
* service class must be `final`.

## How to Declare a Service?

To declare a service, you can use the following extension points in the IntelliJ Platform:

* `com.intellij.applicationService`: designed to declare an application level service.
* `com.intellij.projectService`: designed to declare a project level service.
* `com.intellij.moduleService`: designed to declare a module level service. Consider not using module services because it can lead to increased memory usage for a project with a lot of modules.

**To declare a service:**

1. In your project, open the context menu of the destination package and click *New* (or press <kbd>Alt</kbd>+<kbd>Insert</kbd>).
2. In the *New* menu, choose *Plugin DevKit* and click *Application Service*, *Project Service* or *Module Service* depending on the type of service you need to use.
3. In the dialog box that opens, you can specify service interface and implementation, or just a service class if you uncheck *Separate interface from implementation* check box.

The IDE will generate new Java interface and class (or just a class if you unchecked *Separate interface from implementation* check box) and register the new service in `plugin.xml` file.

> **Note** Declaring a service via *New* context menu is available since version **2017.3**.


To clarify the service declaration procedure, consider the following fragment of the `plugin.xml` file:

```xml
<extensions defaultExtensionNs="com.intellij">
  <!-- Declare the application level service -->
  <applicationService serviceInterface="mypackage.MyApplicationService" 
                      serviceImplementation="mypackage.MyApplicationServiceImpl" />

  <!-- Declare the project level service -->
  <projectService serviceInterface="mypackage.MyProjectService" 
                  serviceImplementation="mypackage.MyProjectServiceImpl" />
</extensions>
```

If `serviceInterface` isn't specified, it's supposed to have the same value as `serviceImplementation`.

## Retrieving a service

Getting service doesn't need read action and can be performed from any thread. If service requested from several threads, it will be initialized in the first thread and another threads will be blocked until service is not fully initialized. 

To instantiate your service, in Java code, use the following syntax:

```java
MyApplicationService applicationService = ServiceManager.getService(MyApplicationService.class);

MyProjectService projectService = project.getService(MyProjectService.class)
```

In Kotlin code you can use convenient methods:
```kotlin
MyApplicationService applicationService = service<MyApplicationService>()

MyProjectService projectService = project<service<MyProjectService>()
```

### Sample Plugin

This section allows you to download and install a sample plugin illustrating how to create and use a plugin service. This plugin has a project component implementing a service that counts the number of currently opened projects in the IDE. If this number exceeds the maximum allowed number of simultaneously opened projects, the plugin returns an error message and closes the most recently opened project.

<!-- TODO Replace with other plugin URL when available-->

**To install and run the sample plugin**

* Download the included sample plugin project located [here](https://github.com/JetBrains/intellij-sdk-docs/tree/master/code_samples/max_opened_projects).
* Start *IntelliJ IDEA*, on the starting page, click *Open Project*, and then use the *Open Project* dialog box to open the project *max_opened_projects*.
* On the main menu, choose *Run \| Run* or press <kbd>Shift</kbd>+<kbd>F10</kbd>.
* If necessary, change the [Run/Debug Configurations](https://www.jetbrains.com/help/idea/run-debug-configuration-plugin.html).
