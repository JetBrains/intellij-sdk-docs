<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Legacy Project Wizard API

<link-summary>Implementing a custom project creation wizard.</link-summary>

> Plugins targeting versions 2024.2 and newer should implement the [](new_project_wizard.md).

## Implementing a New Module Type

Additional support for specific tools and technologies is usually done via implementing some certain module type, which is attached to the project.
A new module type should be derived from the class [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java).

## Custom Project Wizard

The main utilities to configure a custom project wizard can be found in the package [`lang-api.ide.util.projectWizard`](%gh-ic%/platform/lang-api/src/com/intellij/ide/util/projectWizard).
These classes and interfaces serve the following purposes:

- Modification of the configuration wizard view
- Adding new steps to the wizard
- Providing additional settings for project creation
- Handling activities during project creation
- Initial environment configuration

### Module Type

To create a new module type, implement [`ModuleType`](%gh-ic%/platform/lang-core/src/com/intellij/openapi/module/ModuleType.java) parameterized by [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) and register it in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.moduleType"/></include> in [<path>plugin.xml</path>](plugin_configuration_file.md) file.

### Implementing Module Builder

To set up a new module environment, [`ModuleBuilder`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java) should be extended and registered in the <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.moduleBuilder"/></include>.

Functionality which is mandatory to implement consists of:
- `void setupRootModel(ModifiableRootModel)` - sets up a root model for the new module
- `ModuleType getModuleType()` - returns a module type

See [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) to understand better how to implement a module builder.

> If your module type is based on the Java module and meant to support Java as well, extending [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) is enough.
> No extension point needs to be registered.
> Refer to the [`GroovyAwareModuleBuilder`](%gh-ic%/plugins/groovy/src/org/jetbrains/plugins/groovy/config/GroovyAwareModuleBuilder.java) to see how [`JavaModuleBuilder`](%gh-ic%/java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java) can be extended.

> Starting with the 2022.1 release, IntelliJ-based IDEs use the refreshed project wizard, and some module builder base classes return `false` from `isAvailable()` when the new wizard is enabled.
> If your module builder is not visible in 2022.1, make sure that your `ModuleBuilder.isAvailable()` returns `true`.
>
{style="note"}

### Implementing Module Builder Listener

Module builder listener reacts on a new module creation, which could be done either as a part of the project creation process or as adding a new module to the already existing project.
To provide a certain behavior right after a module has been created, a module builder must implement [`ModuleBuilderListener.moduleCreated(Module)`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleBuilderListener.java).

Examples of the tasks executed right after a module has been created may include configuring module roots, looking up for an SDK and setting it up, adding a specific facet if required, etc.

### Adding New Wizard Steps

Adding new steps to the module wizard can be done by overriding [`AbstractModuleBuilder.createWizardSteps(WizardContext, ModulesProvider)`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/AbstractModuleBuilder.java).

If this method returns a non-empty array of [`ModuleWizardStep`](%gh-ic%/platform/lang-core/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java) objects, new steps will be shown in their indexing order while creating a new module.

`ModuleWizardStep` has two methods to be overridden:
- `JComponent getComponent()` - defines the step's UI
- `void updateDataModel()` - commits data from UI into `ModuleBuilder` and `WizardContext`

## Facet

Facets in IntelliJ are the way to store multiple kinds of module-specific settings, for example, to make a language support or framework available in some given module.
To understand facets better from the end-user's point of view, see the [](facet.md) documentation section.

## Implementing Project Structure Detector

To support the creation of your module when a project is imported from existing sources, extend [`ProjectStructureDetector`](%gh-ic%/java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java).
To detect the files your module supports, implement `ProjectStructureDetector.detectRoots()`.

Detecting files is not enough - it is also required to create a module for the project, if appropriate, by implementing `setupProjectStructure()`.
Here is an example that creates a module if no other modules exist in the project structure.

```java
@Override
public void setupProjectStructure(
    @NotNull Collection<DetectedProjectRoot> roots,
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
