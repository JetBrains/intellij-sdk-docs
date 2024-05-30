<!-- Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Supporting Module Types

<link-summary>Adding custom module types.</link-summary>

IntelliJ Platform provides a set of standard module types.
However, an application might need a module of a type that isn't supported yet.
This tutorial shows how to register a new module type and link it to the project creation procedure and the UI.

The source code for the [`module`](%gh-sdk-samples-master%/module) and [`project_wizard`](%gh-sdk-samples-master%/project_wizard) code samples is used throughout this tutorial.

## Pre-Requirements

Create an empty plugin project.
See the [](creating_plugin_project.md) section for details.

> The UI for selecting module types and the creation of modules through project wizard is IntelliJ IDEA-specific.
>
{style="note"}

## Register a New Module Type

Add a new `com.intellij.moduleType` implementation with the IntelliJ Platform in the <path>[plugin.xml](plugin_configuration_file.md)</path> configuration file.

```xml
<extensions defaultExtensionNs="com.intellij">
  <moduleType
      id="DEMO_MODULE_TYPE"
      implementationClass="org.intellij.sdk.module.DemoModuleType"/>
</extensions>
```

## Implement `ModuleType` Interface

Create the `DemoModuleType` implementation based on [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java).

`getNodeIcon()` should return module type specific icon.

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleType.java" include-symbol="DemoModuleType"}

## Implement Custom Module Builder

Create `DemoModuleBuilder` based on [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleBuilder.java" include-symbol="DemoModuleBuilder"}

## Provide Custom Wizard Steps

Provide a straightforward implementation of UI components for the project creating stage.
Create a generic `DemoModuleWizardStep` based on [`ModuleWizardStep`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java)

```java
```
{src="module/src/main/java/org/intellij/sdk/module/DemoModuleWizardStep.java" include-symbol="DemoModuleWizardStep"}

## Creating a Module of New Type

After compiling and running the plugin in a development instance, create a new project.
Select <ui-path>File | New | Module...</ui-path>.
A new module type and its settings panel are available in the Project Wizard.

![New Module Type](new_module_type.png){width="800"}
