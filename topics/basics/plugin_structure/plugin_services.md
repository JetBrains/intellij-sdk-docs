[//]: # (title: Services)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A _service_ is a plugin component loaded on demand when your plugin calls the `getService()` method of corresponding [`ComponentManager`](upsource:///platform/extensions/src/com/intellij/openapi/components/ComponentManager.java) instance (see [Types](#types)).
The IntelliJ Platform ensures that only one instance of a service is loaded even though it is called several times.

A service must have an implementation class that is used for service instantiation.
A service may also have an interface class used to obtain the service instance and provide the service's API.

A service needing a shutdown hook/cleanup routine can implement [`Disposable`](upsource:///platform/util/src/com/intellij/openapi/Disposable.java) and perform necessary work in `dispose()` (see [Automatically Disposed Objects](disposers.md#automatically-disposed-objects)).

Services are used to encapsulate logic operating on a set of related classes or to provide some reusable functionality that can be used across the plugin project, and conceptually don't differ from the service classes in other languages or frameworks.

#### Types
The IntelliJ Platform offers three types of services: _application-level_ services (global singleton), _project-level_ services, and _module-level_ services.
For the latter two, a separate instance of the service is created for each instance of its corresponding scope, see [Project Model Introduction](project_structure.md).

> Please consider not using module-level services because it can increase memory usage for projects with many modules.
>
{type="note"}

#### Constructor
Project/Module-level service constructors can have a `Project`/`Module` argument.
To improve startup performance, avoid any heavy initializations in the constructor.

> Please note that using constructor injection is deprecated (and not supported in [Light Services](#light-services)) for performance reasons.
> Other dependencies should be [acquired only when needed](#retrieving-a-service) in all corresponding methods (see `someServiceMethod()` in [Project Service Sample](#project-service-sample)).
>
{type="note"}

## Light Services

> Light Services are available since IntelliJ Platform 2019.3.
>
{type="note"}

A service not going to be overridden does not need to be registered in <path>plugin.xml</path> (see [Declaring a Service](#declaring-a-service)).
Instead, annotate service class with [`@Service`](upsource:///platform/core-api/src/com/intellij/openapi/components/Service.java).
The service instance will be created in scope according to the caller (see [Retrieving a Service](#retrieving-a-service)).

Restrictions:

* Service class must be `final`.
* Constructor injection is not supported (since it is deprecated).
* If service is a [PersistentStateComponent](persisting_state_of_components.md), roaming must be disabled (`roamingType = RoamingType.DISABLED`).

See [Project-Level Service](#project-service-sample) below for a sample.

## Declaring a Service

To register a non-[Light Service](#light-services), distinct extension points are provided for each type:

* `com.intellij.applicationService` - application-level service
* `com.intellij.projectService` - project-level service
* `com.intellij.moduleService` - module-level service (not recommended, see Note above)

To expose service API, create separate class for `serviceInterface` and extend it in corresponding class registered in `serviceImplementation`.
If `serviceInterface` isn't specified, it's supposed to have the same value as `serviceImplementation`.

To provide custom implementation for test/headless environment, specify `testServiceImplementation`/`headlessImplementation` additionally.

<path>plugin.xml</path>
```xml
<extensions defaultExtensionNs="com.intellij">
  <!-- Declare the application-level service -->
  <applicationService
      serviceInterface="mypackage.MyApplicationService"
      serviceImplementation="mypackage.MyApplicationServiceImpl"/>

  <!-- Declare the project-level service -->
  <projectService
      serviceInterface="mypackage.MyProjectService"
      serviceImplementation="mypackage.MyProjectServiceImpl"/>
</extensions>
```

## Retrieving a Service

Getting service doesn't need a read action and can be performed from any thread.
If a service is requested from several threads, it will be initialized in the first thread, and other threads will be blocked until the service is fully initialized.

<tabs>
<tab title="Java">

```java
MyApplicationService applicationService = ApplicationManager.getApplication()
  .getService(MyApplicationService.class);

MyProjectService projectService = project.getService(MyProjectService.class);
```

Service implementations can wrap these calls with convenient static `getInstance()` or `getInstance(Project)` method:

```java
MyApplicationService applicationService = MyApplicationService.getInstance();

MyProjectService projectService = MyProjectService.getInstance(project);
```

</tab>

<tab title="Kotlin">

```kotlin
val applicationService = service<MyApplicationService>()

val projectService = project.service<MyProjectService>()
```
</tab>

</tabs>

<procedure title="Getting Service Flow" initial-collapse-state="collapsed">

![Getting Service](getting_service.svg){thumbnail="true" thumbnail-same-file="true"}

</procedure>

## Project Service Sample

This minimal sample shows [Light Service](#light-services) `ProjectService` interacting with another project-level service `AnotherService` (not shown here).

<path>ProjectService.java</path>

```java
@Service
public final class ProjectService {

  private final Project myProject;

  public ProjectService(Project project) {
    myProject = project;
  }

  public void someServiceMethod(String parameter) {
    AnotherService anotherService = myProject.getService(AnotherService.class);
    String result = anotherService.anotherServiceMethod(parameter, false);
    // do some more stuff
  }
}
```

## Sample Plugin

To clarify how to use services, consider the **maxOpenProjects** sample plugin available in the [code samples](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/max_opened_projects).

This plugin has an application service counting the number of currently opened projects in the IDE.
If this number exceeds the maximum number of simultaneously opened projects allowed by the plugin (3), it displays a warning message.

See [Code Samples](code_samples.md) on how to set up and run the plugin.
