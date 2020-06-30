---
title: Run Configuration Management
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This document describes main classes to work with run configurations and common use case.

* Dummy table of contents
{:toc}

## Configuration Type

The starting point for implementing any run configuration type is the [`ConfigurationType`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java) interface. The list of available configuration types is shown when a user opens the _'Edit run configurations'_ dialog and executes _'Add'_ action:

![Create](/basics/img/create-1.png)

Every type there is represented as an instance of [`ConfigurationType`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java) and registered like below:

```xml
<configurationType implementation="org.jetbrains.plugins.gradle.service.execution.GradleExternalTaskConfigurationType" />
```

The easiest way to implement this interface is to use the [`ConfigurationTypeBase`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/runConfigurationType.kt) base class. In order to use it, you need to inherit from it and to provide the configuration type parameters (ID, name, description and icon) as constructor parameters. In addition to that, you need to call the [`addFactory()`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/runConfigurationType.kt) method to add a configuration factory.

## Configuration Factory

All run configurations are created by the [`ConfigurationFactory`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java) registered for a particular `ConfigurationType`. It's possible that one `ConfigurationType` [has more than one](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationType.java) `ConfigurationFactory`:

![Configuration Factory](/basics/img/create-3.png)

The key API of [`ConfigurationFactory`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java), and the only method that you're required to implement, is the [`createTemplateConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java) method. This method is called once per project to create the template run configuration.

All real run configurations (loaded from the workspace or created by the user) are called by cloning the template through the [`createConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java) method.

You can customize additional aspects of your configuration factory by overriding the [`getIcon`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java), [`getAddIcon`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java)<!--#L55-->, [`getName`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ConfigurationFactory.java)<!--#L51--> and the default settings methods. These additional overrides are optional.

## Run Configuration

The run configuration itself is represented by the [`RunConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfiguration.java) interface. A _'run configuration'_ here is some named profile which can be executed, e.g. application started via `main()` class, test, remote debug to particular machine/port etc.

Here is an example of a Java run configurations defined for a particular project:

![Run Configuration](/basics/img/create-2.png)

When implementing a run configuration, you may want to use one of the common base classes:

* [`RunConfigurationBase`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfigurationBase.java) is a general-purpose superclass that contains the most basic implementation of a run configuration.
* [`LocatableConfigurationBase`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/LocatableConfigurationBase.java) is a common base class that should be used for configurations that can be created from context by a `RunConfigurationProducer`. It supports automatically generating a name for a configuration from its settings and keeping track of whether the name was changed by the user.
* [`ModuleBasedConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/ModuleBasedConfiguration.java) is a base class for a configuration that is associated with a specific module (for example, Java run configurations use the selected module to determine the run classpath).

## Settings Editor

That common run configuration settings might be modified via:

[`RunConfiguration`-specific UI](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfiguration.java). That is handled by [`SettingsEditor`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SettingsEditor.java)<!--#L97-->:

* [`getComponent()`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SettingsEditor.java) method is called by the IDE and shows run configuration specific UI.
* [`resetFrom()`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SettingsEditor.java) is called to discard all non-confirmed user changes made via that UI.
* [`applyTo()`](upsource:///platform/platform-api/src/com/intellij/openapi/options/SettingsEditor.java) is called to confirm the changes, i.e. copy current UI state into the target settings object.

## Persistence

That run configuration settings are persistent, i.e. they are stored at file system and loaded back on the IDE startup. That is performed via [`writeExternal()`](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java) and [`readExternal()`](upsource:///platform/util/src/com/intellij/openapi/util/JDOMExternalizable.java)<!--#L26--> methods of [`RunConfiguration`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RunConfiguration.java) class correspondingly.

The actual configurations stored by the *IntelliJ Platform* are represented by instances of the [`RunnerAndConfigurationSettings`](upsource:///platform/lang-api/src/com/intellij/execution/RunnerAndConfigurationSettings.java) class, which combines a run configuration with runner-specific settings, as well as keeping track of certain run configuration flags such as "temporary" or "singleton".

Dealing with instances of this class becomes necessary when you need to create run configurations from code. This is accomplished with the following two steps:

* `RunManager.createConfiguration()` creates an instance of `RunnerAndConfigurationSettings`.
* `RunManager.addConfiguration()` makes it persistent by adding it to either the list of shared configurations stored in a project, or to the list of local configurations stored in the workspace file.

## Refactoring Support

Most run configurations contain references to classes, files or directories in their settings, and these settings usually need to be updated when the corresponding element is renamed or moved.

In order to support that, your run configuration needs to implement the [`RefactoringListenerProvider`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/RefactoringListenerProvider.java) interface.

In your implementation of `getRefactoringElementListener()`, you need to check whether the element being refactored is the one that your run configuration refers to, and if it is, you return a [`RefactoringElementListener`](upsource:///platform/analysis-api/src/com/intellij/refactoring/listeners/RefactoringElementListener.java) that updates your configuration according to the new name and location of the element.

## Creating Configurations from Context

Many plugins support automatic creation of run configurations from context, so that the user can click, for example, on an application or test class and automatically run it using the correct run configuration type. In order to support that, you need to provide an implementation of the [`RunConfigurationProducer`](upsource:///platform/lang-api/src/com/intellij/execution/actions/RunConfigurationProducer.java)
interface and to register it as `<runConfigurationProducer>` in your `plugin.xml`. (Note that this API has been redesigned in IntelliJ IDEA 13; the old [`RuntimeConfigurationProducer`](upsource:///platform/lang-api/src/com/intellij/execution/junit/RuntimeConfigurationProducer.java) is a much more confusing version of the same API).

The two main methods that you need to implement are:

* `setupConfigurationFromContext()` receives a blank configuration of your type and a `ConfigurationContext` containing information about a source code location (accessible by calling `getLocation()` or `getPsiLocation()`). Your implementation needs to check whether the location is applicable for your configuration type (for example, if it's in a file of the language you're supporting). If not, you need to return false, and if it is, you need to put the correct context-specific settings into the run configuration and return true.
* `isConfigurationFromContext()` checks if the specified configuration of your type was created from the specified context. Implementing this method allows you to reuse an existing run configuration which is applicable to the current context instead of creating a new one and possibly ignoring the customisations the user has performed in the existing one.

Note that, in order to support automatic naming of configurations created from context, your configuration should use
[`LocatableConfigurationBase`](upsource:///platform/lang-api/src/com/intellij/execution/configurations/LocatableConfigurationBase.java) as the base class.

## Running from the Gutter

Take a look at [`RunLineMarkerContributor`](upsource:///platform/execution-impl/src/com/intellij/execution/lineMarker/RunLineMarkerContributor.java) and its implementations.
