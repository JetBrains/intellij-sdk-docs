[//]: # (title: Supporting Module Types)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

IntelliJ Platform provides a set of standard module types.
However, an application might need a module of a type that isn't supported yet.
This tutorial shows how to register a new module type and link it to the project creation procedure and the UI.

The source code for the [`module`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/module) and [`project_wizard`](https://github.com/JetBrains/intellij-sdk-code-samples/tree/main/project_wizard) code samples is used throughout this tutorial.

## Pre-Requirements

Create an empty plugin project, see [Creating a Plugin Project](gradle_build_system.md).

> The UI for selecting module types and the creation of modules through project wizard is IntelliJ IDEA-specific.
>
{type="note"}

## Register a New Module Type

Add a new `com.intellij.moduleType` implementation with the IntelliJ Platform in the <path>plugin.xml</path> configuration file.

```xml
<extensions defaultExtensionNs="com.intellij">
  <moduleType
      id="DEMO_MODULE_TYPE"
      implementationClass="org.intellij.sdk.module.DemoModuleType"/>
</extensions>
```

## Implement ModuleType Interface

Create the `DemoModuleType` implementation based on [`ModuleType`](upsource:///platform/lang-core/src/com/intellij/openapi/module/ModuleType.java).

`getNodeIcon()` should return module type specific icon.

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleType.java"}

## Implement Custom Module Builder

Create `DemoModuleBuilder` based on [`ModuleBuilder`](upsource:///platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleBuilder.java"}

## Provide Custom Wizard Steps

Provide a straightforward implementation of UI components for the project creating stage.
Create a generic `DemoModuleWizardStep` based on [`ModuleWizardStep`](upsource:///platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java)

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleWizardStep.java"}

## Creating a Module of New Type

After compiling and running the plugin in a development instance, create a new project.
Select <menupath>File | New | Module...</menupath>.
A new module type and its settings panel are available in the Project Wizard.

![New Module Type](new_module_type.png){width="800"}
