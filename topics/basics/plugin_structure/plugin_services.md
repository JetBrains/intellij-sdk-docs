# Services

<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

<link-summary>Registering and using on-demand services to encapsulate plugin functionality.</link-summary>

A _service_ is a plugin component loaded on demand when your plugin calls the `getService()` method of corresponding [`ComponentManager`](%gh-ic%/platform/extensions/src/com/intellij/openapi/components/ComponentManager.java) instance (see [Types](#types)).
The IntelliJ Platform ensures that only one instance of a service is loaded even though it is called several times.
Services are used to encapsulate logic operating on a set of related classes or to provide some reusable functionality that can be used across the plugin project, and conceptually don't differ from the service classes in other languages or frameworks.

A service must have an implementation class that is used for service instantiation.
A service may also have an interface class used to obtain the service instance and provide the service's API.

A service needing a shutdown hook/cleanup routine can implement [`Disposable`](%gh-ic%/platform/util/src/com/intellij/openapi/Disposable.java) and perform necessary work in `dispose()` (see [Automatically Disposed Objects](disposers.md#automatically-disposed-objects)).

#### Types

The IntelliJ Platform offers three types of services: _application-level_ services (global singleton), _project-level_ services, and _module-level_ services.
For the latter two, a separate instance of the service is created for each instance of its corresponding scope, see [Project Model Introduction](project_structure.md).

> Avoid using module-level services as it can increase memory usage for projects with many modules.
>
{style="note"}

#### Constructor

Project/Module-level service constructors can have a [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java)/[`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java) argument.
To improve startup performance, avoid any heavy initializations in the constructor.

> Using constructor injection of dependency services is deprecated (and not supported in [](#light-services)) for performance reasons.
>
> Other dependencies must be [acquired only when needed](#retrieving-a-service) in all corresponding methods (see `someServiceMethod()` in [Project Service Sample](#project-service-sample)).
>
> Use inspection <control>Plugin DevKit | Code | Non-default constructors for service and extension class</control> to verify code.
>
{style="warning"}

## Light Services

A service not going to be overridden does not need to be registered in <path>[plugin.xml](plugin_configuration_file.md)</path> (see [Declaring a Service](#declaring-a-service)).
Instead, annotate service class with [`@Service`](%gh-ic%/platform/core-api/src/com/intellij/openapi/components/Service.java).
Project-level services must specify `@Service(Service.Level.PROJECT)`.
The service instance will be created in scope according to the caller (see [Retrieving a Service](#retrieving-a-service)).

Restrictions:

* None of these attributes is required: `os`, `client`, `overrides`, `id`, `preload`.
* Service class must be `final`.
* Constructor injection of dependency services is not supported.
* If application-level service is a [PersistentStateComponent](persisting_state_of_components.md), roaming must be disabled (`roamingType = RoamingType.DISABLED`).

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

> If declared services are intended to be used by other plugins depending on your plugin, consider [bundling their sources](bundling_plugin_openapi_sources.md) in the plugin distribution.
>
{style="note"}

## Retrieving a Service

> Do not acquire service instances eagerly or store them in fields, but obtain them in the place(s) where they will be used.
>
{style="warning"}

Getting service doesn't need a read action and can be performed from any thread.
If a service is requested from several threads, it will be initialized in the first thread, and other threads will be blocked until it is fully initialized.

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

<procedure title="Getting Service Flow" collapsible="true" default-state="collapsed">

![Getting Service](getting_service.svg){thumbnail="true" thumbnail-same-file="true"}

</procedure>

## Project Service Sample

This minimal sample shows [Light Service](#light-services) `ProjectService` interacting with another project-level service `AnotherService` (not shown here).

<path>ProjectService.java</path>

```java
@Service(Service.Level.PROJECT)
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

To clarify how to use services, consider the **maxOpenProjects** sample plugin available in the [code samples](%gh-sdk-samples%/max_opened_projects).

This plugin has an application service counting the number of currently opened projects in the IDE.
If this number exceeds the maximum number of simultaneously opened projects allowed by the plugin (3), it displays a warning message.

See [Code Samples](code_samples.md) on how to set up and run the plugin.
