[//]: # (title: External System Integration)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page provides a high-level overview of the *External System* sub-system.
There are multiple project management systems ([Apache Maven](https://maven.apache.org/), [Gradle](https://www.gradle.org/), [sbt](https://www.scala-sbt.org/), etc.) and IntelliJ Platform provides a mechanism to support them in IDEs.

Most of the project management systems provide a similar set of facilities from the integration point of view:
* build a project from external system config (<path>pom.xml</path>, <path>build.gradle.kts</path>, etc.)
* provide a list of available tasks
* allow to execute a particular task
* and more

That means that we can separate external system-specific logic and general IDE processing.
The *External System* sub-system provides a simple API for wrapping external system elements and extensible IDE-specific processing logic.

## Project Management

### Project Data Domain

The external system wrapper is required to be able to build project info on the basis of the given external system config.
That information is built with the following base classes:
* [`DataNode`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/model/DataNode.java)
* [`Key`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/model/Key.java)
* [`ExternalEntityData`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/model/project/ExternalEntityData.java)

![DataNode](data-node.png)

The `DataNode` class is just a holder for the target data (a data type is defined by the `Key`).
Multiple `DataNode` objects might be organized in directed graph where every edge identifies parent-child relation.

For example, a simple one-module project might look as below:

![DataNode Example](data-node-example.png)

The IDE provides a set of built-in `Key` and `ExternalEntityData` classes but any external system integration or third-party plugin developer might enhance project data by defining custom `Key` and `ExternalEntityData` and store them at a child of appropriate `DataNode`.

### Managing Project Data

Processing project data built on an external system config basis can be performed with [`ProjectDataService`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/service/project/manage/ProjectDataService.java).
It is a strategy which knows how to manage particular `ExternalEntityData`.
For example, when we want to import a project from an external model, we can start with the top level `DataNode` which references project info and then import its data using corresponding service.

Custom services can be registered via [`com.intellij.externalProjectDataService`](https://jb.gg/ipe?extensions=com.intellij.externalProjectDataService) extension point.

The good thing is that we can separate project parsing and management here.
That means that a set of `DataNode`, `Key` and `ProjectDataServices` can be introduced for particular technology and then every external system integration can build corresponding data if necessary using it.

### Importing from External Model

The IntelliJ Platform provides an API for importing projects from external models:
* [`ProjectImportBuilder`](upsource:///java/idea-ui/src/com/intellij/projectImport/ProjectImportBuilder.java)
* [`ProjectImportProvider`](upsource:///java/idea-ui/src/com/intellij/projectImport/ProjectImportProvider.java)

There are two classes built on the *template method* pattern which simplify implementation:
* [`AbstractExternalProjectImportBuilder`](upsource:///java/idea-ui/src/com/intellij/openapi/externalSystem/service/project/wizard/AbstractExternalProjectImportBuilder.java)
* [`AbstractExternalProjectImportProvider`](upsource:///java/idea-ui/src/com/intellij/openapi/externalSystem/service/project/wizard/AbstractExternalProjectImportProvider.java)

Note that [`AbstractExternalProjectImportBuilder`](upsource:///java/idea-ui/src/com/intellij/openapi/externalSystem/service/project/wizard/AbstractExternalProjectImportBuilder.java) is built on top of the 'external system settings' controls.

Concrete implementations should be registered in [`com.intellij.projectImportBuilder`](https://jb.gg/ipe?extensions=com.intellij.projectImportBuilder) and [`com.intellij.projectImportProvider`](https://jb.gg/ipe?extensions=com.intellij.projectImportProvider) extension points accordingly.

Example of the project import provider and builder for Gradle:

* [`JavaGradleProjectImportProvider`](upsource:///plugins/gradle/java/src/service/project/wizard/JavaGradleProjectImportProvider.kt)
* [`JavaGradleProjectImportBuilder`](upsource:///plugins/gradle/java/src/service/project/wizard/JavaGradleProjectImportBuilder.kt)

## Auto-Import

It's possible to configure external system integration to automatically refresh project structure when external project's config files are modified.

> From 2020.1, auto-import cannot be disabled by a user.
>
{type="tip"}

### Auto-Import for ExternalSystemManager Implementation

Describe project's settings files to track by having external system [`ExternalSystemManager`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemManager.java) implement [`ExternalSystemAutoImportAware`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/ExternalSystemAutoImportAware.java).

> The `ExternalSystemAutoImportAware.getAffectedExternalProjectPath()` method is called quite often, that's why it's expected to return control as soon as possible.
> Helper `CachingExternalSystemAutoImportAware` class might be used for caching, i.e. `ExternalSystemManager` which implements `ExternalSystemAutoImportAware` can have a field like `new CachingExternalSystemAutoImportAware(new MyExternalSystemAutoImportAware())` and delegate `ExternalSystemAutoImportAware.getAffectedExternalProjectPath()` calls to it.
>
{type="note"}

### Auto-Import for Standalone External Systems

Some external systems don't have `ExternalSystemManager` (e.g., Maven), but they also can use auto-import core to track changes in settings files.
For this, implement `ExternalSystemProjectAware` interface that describes settings files for tracking and an action to reload the project model.
Then register the instance with `ExternalSystemProjectTracker` to start tracking.

> Multiple `ExternalSystemProjectAware` instances can correspond to a single external system.
> It allows performing project reload differently depending on the set of settings files (project aware per settings file, per module, per external project, etc.).
>
{type="note"}

### Icon for Reload Notification

From 2020.1, the icon for reload notification can be specified per external system.
Implement [`ExternalSystemIconProvider`](upsource:///platform/external-system-api/src/com/intellij/openapi/externalSystem/ui/ExternalSystemIconProvider.kt) and register via [com.intellij.externalIconProvider](https://jb.gg/ipe?extensions=com.intellij.externalIconProvider) extension point in <path>plugin.xml</path>.
Alternatively, set `reloadIcon` field external system implements `ExternalSystemIconProvider` directly.

## Settings

The general idea is that all external system settings controls are represented by implementations of [`ExternalSystemSettingsControl`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/util/ExternalSystemSettingsControl.java) interface.
There are also external system project-local settings and global external system settings.
So, basically particular external system settings UI looks as below:

![Configurable](configurable.png)

It's recommended to extend from [`AbstractExternalProjectSettingsControl`](upsource:///platform/external-system-impl/src/com/intellij/openapi/externalSystem/service/settings/AbstractExternalProjectSettingsControl.java) for implementing project-level settings control as it already handles some of them.

Similar approach is used for providing _Import from External System_ UI - implementation is expected to extend [`AbstractImportFromExternalSystemControl`](upsource:///java/idea-ui/src/com/intellij/openapi/externalSystem/service/settings/AbstractImportFromExternalSystemControl.java) and it has not linked external projects list, but target external project path control:

![Import from external system](import.png)

## Testing

_2022.1_

Use `com.jetbrains.intellij.platform:external-system-test-framework` from [](intellij_artifacts.md).

Relevant base classes:

- [`ExternalSystemImportingTestCase`](upsource:///platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/ExternalSystemImportingTestCase.java)
- [`ExternalSystemTestCase`](upsource:///platform/external-system-api/testFramework/src/com/intellij/platform/externalSystem/testFramework/ExternalSystemTestCase.java)
