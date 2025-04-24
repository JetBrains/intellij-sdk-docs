<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Legacy Project Wizard API

<link-summary>Implementing a custom project creation wizard.</link-summary>

> Plugins targetting versions 2024.2 and newer should implement [](new_project_wizard.md).

## Project Wizard

Working with the project wizard can be illustrated with the [RedLine SmallTalk plugin](https://github.com/bulenkov/RedlineSmalltalk). See also [](intro_project_wizard.md).

## Implementing a New Module Type

Additional support for specific tools and technologies is usually done via implementing some certain module type which is attached to the project.
New module type should be derived from the class [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java).

## Custom Project Wizard

Main utilities to configure a custom project wizard can be found in the package [`lang-api.ide.util.projectWizard`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/projectWizard).
These classes and interfaces serve the following purposes:

* Modification of the configuration wizard view
* Adding new steps to the wizard
* Providing additional setting for project creation
* Handling activities during project creation
* Initial environment configuration

### Module Type

To create a new module type add an extension

```xml
<moduleType
    id="MY_MODULE"
    implementationClass="st.redline.smalltalk.module.MyModuleType"/>
```

to the [`plugin.xml`](https://github.com/bulenkov/RedlineSmalltalk/blob/master/resources/META-INF/plugin.xml).
A custom module type should extend the [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) generic from [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).
The following [module type implementation](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java) of a custom module type shows how this instance can be registered and implemented.

### Implementing Module Builder

To set up a new module environment [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) class should be extended and registered as an extension point like the following snippet shows:

```xml
<extensions defaultExtensionNs="com.intellij">
  <moduleBuilder
      builderClass="org.jetbrains.plugins.ruby.rails.facet.versions.MyModuleBuilder"/>
</extensions>
```

Functionality which is mandatory to implement consists of:

* Setting up a root model for the new module by overriding
  ```java
  public abstract void setupRootModel(
      ModifiableRootModel modifiableRootModel) throws ConfigurationException;
  ```
* Getting a module type
  ```java
  public abstract ModuleType getModuleType();
  ```

See [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) to understand better how to implement a module builder.

If your module type is based on the Java module and meant to support Java as well, extending [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) is enough.
No extension point needs to be registered.
Refer to [SmallTalk module type](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java) to see how [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) can be derived.

> Starting with the 2022.1 release, IntelliJ-based IDEs use the refreshed project wizard and some module builder base classes return `false` from `isAvailable()` when the new wizard is enabled.
> If your module builder is not visible in 2022.1, make sure that your `ModuleBuilder.isAvailable()` returns `true`.
>
{style="note"}

### Implementing Module Builder Listener

Module builder listener reacts on a new module creation, which could be done either as a part of the project creation process, or as adding a new module to the already existing project.
To provide a certain behavior right after a module has been created, module builder should implement [`ModuleBuilderListener.moduleCreated(Module)`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilderListener.java).

Examples of the tasks executed right after a module has been created may include configuring module roots, looking up for an SDK and setting it up, adding a specific facet if required, etc.
For more details, please see the following [SmallTalk custom module type](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java) implementation.

### Adding New Wizard Steps

Adding new steps to the module wizard can be done by overriding [`AbstractModuleBuilder.createWizardSteps(WizardContext, ModulesProvider)`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/AbstractModuleBuilder.java).
See an example [module builder](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleBuilder.java).
If this method returns a non-empty array of [`ModuleWizardStep`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java) objects, new steps will be shown in their indexing order while creating a new module.
The following [implementation](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleWizardStep.java) for the SmallTalk project type illustrates how a custom wizard step can be created.
The [`RsModuleWizardStep`](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleWizardStep.java) class is derived from `ModuleWizardStep`, which has two methods to be overridden:

* ```java
  public JComponent getComponent();
  ```
  defines how the step will look like
* ```java
  public void updateDataModel();
  ```
  commits data from UI into `ModuleBuilder` and `WizardContext`

## Facet

Facets in IntelliJ are the way to store multiple kinds of module-specific settings, for instance to make a language support or framework available in some given module.
To understand facets better from the end-user's point of view, see the [Facet](facet.md) documentation section.

## Implementing Project Structure Detector

To support the creation of your module when a project is imported from existing sources, extend [`ProjectStructureDetector`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java).
To detect the files your module supports, implement `ProjectStructureDetector.detectRoots()`.

Refer to the [Smalltalk project structure detector](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsProjectStructureDetector.java) to see example implementation.

But detecting the files is not enough, you also need to create a module for the project if appropriate by implementing `setupProjectStructure()`.
Here is an example that creates a module if no other modules exist in the project structure.

```java
@Override
public void setupProjectStructure(@NotNull Collection<DetectedProjectRoot> roots,
                                  @NotNull ProjectDescriptor projectDescriptor,
                                  @NotNull ProjectFromSourcesBuilder builder) {
  List<ModuleDescriptor> modules = projectDescriptor.getModules();
  if (modules.isEmpty()) {
    modules = new ArrayList<>();
    for (DetectedProjectRoot root : roots) {
      modules.add(new ModuleDescriptor(root.getDirectory(),
          MyModuleType.getInstance(), ContainerUtil.emptyList()));
    }
    projectDescriptor.setModules(modules);
  }
}
```
