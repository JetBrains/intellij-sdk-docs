[//]: # (title: Supporting Module Types)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

IntelliJ Platform provides a set of standard module types.
However, an application might need a module of a type that isn't supported yet.
This tutorial shows how to register a new module type and link it to the project creation procedure and the UI.

The source code for the [`module`](%gh-sdk-samples%/module) and [`project_wizard`](%gh-sdk-samples%/project_wizard) code samples is used throughout this tutorial.

## Pre-Requirements

Create an empty plugin project.
See the [](creating_plugin_project.md) section for details.

> The UI for selecting module types and the creation of modules through project wizard is IntelliJ IDEA-specific.
>
{type="note"}

## Register a New Module Type

Add a new `com.intellij.moduleType` implementation with the IntelliJ Platform in the <path>[plugin.xml](plugin_configuration_file.md)</path> configuration file.

```xml
<extensions defaultExtensionNs="com.intellij">
  <moduleType
      id="DEMO_MODULE_TYPE"
      implementationClass="org.intellij.sdk.module.DemoModuleType"/>
</extensions>
```

## Implement ModuleType Interface

Create the `DemoModuleType` implementation based on [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java).

`getNodeIcon()` should return module type specific icon.

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleType.java"}

## Implement Custom Module Builder

Create `DemoModuleBuilder` based on [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleBuilder.java"}

## Provide Custom Wizard Steps

Provide a straightforward implementation of UI components for the project creating stage.
Create a generic `DemoModuleWizardStep` based on [`ModuleWizardStep`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java)

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleWizardStep.java"}

## Creating a Module of New Type

After compiling and running the plugin in a development instance, create a new project.
Select <ui-path>File | New | Module...</ui-path>.
A new module type and its settings panel are available in the Project Wizard.

![New Module Type](new_module_type.png){width="800"}
