<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Services

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
> Other service dependencies must be [acquired only when needed](#retrieving-a-service) in all corresponding methods, e.g., if you need a service to get some data or execute a task, retrieve the service before calling its methods.
> Do not retrieve services in constructors to store them in class fields.
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

### Examples


<tabs group="languages">


<tab title="Java" group-key="java">

Application-level light service:

```java
@Service
public final class MyAppService {
  public void doSomething(String param) {
    // ...
  }
}
```

Project-level light service example:

```java
@Service(Service.Level.PROJECT)
public final class MyProjectService {
  private final Project myProject;

  public MyProjectService(Project project) {
    myProject = project;
  }

  public void doSomething(String param) {
    String projectName = myProject.getName();
    // ...
  }
}
```

</tab>

<tab title="Kotlin" group-key="kotlin">

Application-level light service:

```kotlin
@Service
class MyAppService {
  fun doSomething(param: String) {
    // ...
  }
}
```

Project-level light service example:

```kotlin
@Service(Service.Level.PROJECT)
class MyProjectService(private val project: Project) {
  fun doSomething(param: String) {
    val projectName = project.name
    // ...
  }
}
```

</tab>

</tabs>

## Declaring a Service

To register a non-[Light Service](#light-services), distinct extension points are provided for each type:

* `com.intellij.applicationService` - application-level service
* `com.intellij.projectService` - project-level service
* `com.intellij.moduleService` - module-level service (not recommended, see Note above)

To expose service API, create separate class for `serviceInterface` and extend it in corresponding class registered in `serviceImplementation`.
If `serviceInterface` isn't specified, it's supposed to have the same value as `serviceImplementation`.

To provide custom implementation for test/headless environment, specify `testServiceImplementation`/`headlessImplementation` additionally.

### Example


<tabs group="languages">

<tab title="Java" group-key="java">

Application-level service:

- Interface:

  ```java
  public interface MyAppService {
    void doSomething(String param);
  }
  ```

- Implementation:

  ```java
  public class MyAppServiceImpl implements MyAppService {
    @Override
    public void doSomething(String param) {
      // ...
    }
  }
  ```

Project-level service:

- Interface:

  ```java
  public interface MyProjectService {
    void doSomething(String param);
  }
  ```

- Implementation:

  ```java
  public class MyProjectServiceImpl implements MyProjectService {
    private final Project myProject;

    public MyProjectServiceImpl(Project project) {
      myProject = project;
    }

    public void doSomething(String param) {
      String projectName = myProject.getName();
      // ...
    }
  }
  ```
</tab>

<tab title="Kotlin" group-key="kotlin">

Application-level service:

- Interface:

  ```kotlin
  interface MyAppService {
    fun doSomething(param: String)
  }
  ```

- Implementation:

  ```kotlin
  class MyAppServiceImpl : MyAppService {
    override fun doSomething(param: String) {
      // ...
    }
  }
  ```

Project-level service:

- Interface:

  ```kotlin
  interface MyProjectService {
    fun doSomething(param: String)
  }
  ```

- Implementation:

  ```kotlin
  class MyProjectServiceImpl(private val project: Project)
      : MyProjectService {

    fun doSomething(param: String) {
      val projectName = project.name
      // ...
    }
  }
  ```
</tab>

</tabs>

Registration in <path>plugin.xml</path>:
```xml
<extensions defaultExtensionNs="com.intellij">
  <!-- Declare the application-level service -->
  <applicationService
      serviceInterface="com.example.MyAppService"
      serviceImplementation="com.example.MyAppServiceImpl"/>

  <!-- Declare the project-level service -->
  <projectService
      serviceInterface="com.example.MyProjectService"
      serviceImplementation="com.example.MyProjectServiceImpl"/>
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

<tabs group="languages">
<tab title="Java" group-key="java">

```java
MyAppService applicationService =
    ApplicationManager.getApplication().getService(MyAppService.class);

MyProjectService projectService =
    project.getService(MyProjectService.class);
```

Service implementations can wrap these calls with convenient static `getInstance()` or `getInstance(Project)` method:

```java
MyAppService applicationService = MyAppService.getInstance();

MyProjectService projectService = MyProjectService.getInstance(project);
```

</tab>

<tab title="Kotlin" group-key="kotlin">

```kotlin
val applicationService = service<MyAppService>()

val projectService = project.service<MyProjectService>()
```
</tab>

</tabs>

<procedure title="Getting Service Flow" collapsible="true" default-state="collapsed">

![Getting Service](getting_service.svg){thumbnail="true" thumbnail-same-file="true"}

</procedure>

## Sample Plugin

To clarify how to use services, consider the **maxOpenProjects** sample plugin available in the [code samples](%gh-sdk-samples%/max_opened_projects).

This plugin has an application service counting the number of currently opened projects in the IDE.
If this number exceeds the maximum number of simultaneously opened projects allowed by the plugin (3), it displays an information message.

See [Code Samples](code_samples.md) on how to set up and run the plugin.
