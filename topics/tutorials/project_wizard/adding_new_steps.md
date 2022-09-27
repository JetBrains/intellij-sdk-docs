[//]: # (title: Adding New Steps to Project Wizard)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This tutorial shows how to add an extra step to the Project Wizard to provide additional project configuration settings.

## Pre-Requirements

Create an empty plugin project.
See [Creating a Plugin Project](gradle_build_system.md) to know how to do it.

## Register Module Builder

Project configuration settings depend on the project's module type.
Register a new `com.intellij.moduleBuilder` extension point in the <path>[plugin.xml](plugin_configuration_file.md)</path> configuration file.

```xml
<extensions defaultExtensionNs="com.intellij">
  <moduleBuilder
      builderClass="org.intellij.sdk.project.wizard.DemoModuleWizardStep"
      id="DEMO_STEP"
      order="first"/>
</extensions>
```

## Create a Custom Module Builder

Extend [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) class to provide custom configuration.

```java
public class DemoModuleWizardStep extends ModuleBuilder {
  public void setupRootModel(ModifiableRootModel modifiableRootModel)
      throws ConfigurationException {

  }
}
```

## Define Module Type

Set a module type for the extra wizard step to provide.
In this example, choose an `EMPTY` module type.

```java
public class DemoModuleWizardStep extends ModuleBuilder {
  public void setupRootModel(ModifiableRootModel modifiableRootModel)
      throws ConfigurationException {

  }

  public ModuleType getModuleType() {
    return ModuleType.EMPTY; //or it could be any other module type
  }
}
```

## Design and Implement Wizard Steps

Provide an implementation of a custom UI component to be added to the Wizard.
In this case, leave it as a label.

```java
public class DemoModuleWizardStep extends ModuleBuilder {
  public void setupRootModel(ModifiableRootModel modifiableRootModel)
      throws ConfigurationException {

  }

  public ModuleType getModuleType() {
    return ModuleType.EMPTY;
  }

  @Override
  public ModuleWizardStep[] createWizardSteps(
      @NotNull WizardContext wizardContext,
      @NotNull ModulesProvider modulesProvider) {
    return new ModuleWizardStep[]{new ModuleWizardStep() {
      @Override
      public JComponent getComponent() {
        return new JLabel("Put your content here");
      }

      @Override
      public void updateDataModel() {

      }
    }};
  }
}
```

## Checking UI Appearance

After compiling and running the plugin, create a new project using a source-compiled instance of IntelliJ IDEA.

![New Project](empty_project.png)

Choose an *Empty Module* type, click next, and get to the just added extra step.

![Extra Step](extra_step.png)

Modify and tune the UI component depending on requirements.
