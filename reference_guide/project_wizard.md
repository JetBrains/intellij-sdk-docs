---
title: Project Wizard. Adding Support for Creating New Project Types.
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

## Project Wizard

Working with the project wizard can be illustrated with the
[RedLine SmallTalk plugin](https://github.com/bulenkov/RedlineSmalltalk)

## Implementing New Module Type

Additional support for specific tools and technologies is usually done via implementing some certain module type which is attached to the project.
New module type should be derived from the class
[`ModuleType`](upsource:///platform/lang-api/src/com/intellij/openapi/module/ModuleType.java).


## Project Wizard

Main utilities to configure a custom project wizard can be found in the package
[`lang-api.ide.util.projectWizard`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard).
These classes and interfaces serve the following purposes:

* Modification of the configuration wizard view
* Adding new steps to the wizard
* Providing additional setting for project creation
* Handling activities during project creation
* Initial environment configuration

### Module Type

To create a new module type and an extension

```xml
<moduleType id="MY_MODULE" implementationClass="st.redline.smalltalk.module.MyModuleType"/>
```

to the
[`plugin.xml`](https://github.com/bulenkov/RedlineSmalltalk/blob/master/resources/META-INF/plugin.xml).
A custom module type should extend the
[`ModuleType`](upsource:///platform/lang-api/src/com/intellij/openapi/module/ModuleType.java)
generic from
[`ModuleBuilder`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java).
The following
[module type implementation](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)
of a custom module type show how this instance can be registered and implemented.

### Implementing Module Builder

To set up a new module environment
[`ModuleBuilder`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilder.java)
class should be extended and registered as an extension point like the following snippet shows:

```xml
<extensions defaultExtensionNs="com.intellij">
    <moduleBuilder builderClass="org.jetbrains.plugins.ruby.rails.facet.versions.MyModuleBuilder"/>
</extensions>
```

Functionality which is mandatory to implement consists of:

*  Setting up a root model for the new module by overriding

   ```java
   public abstract void setupRootModel(ModifiableRootModel modifiableRootModel) throws ConfigurationException;
   ```

*  Getting a module type

   ```java
   public abstract ModuleType getModuleType();
   ```

See
[`JavaModuleBuilder`](upsource:///java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java)
to understand better how to implement a module builder.

If your module type is based on the java module and meant to support Java as well, extending
[`JavaModuleBuilder`](upsource:///java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java)
is enough.
No extension point needs to be registered.
Refer to
[SmallTalk module type](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)
to see how
[`JavaModuleBuilder`](upsource:///java/openapi/src/com/intellij/ide/util/projectWizard/JavaModuleBuilder.java)
can be derived.

### Implementing Module Builder Listener

Module builder listener reacts on a new module creation, which could be done either as a part of the project creation process,
or as adding a new module to the already existing project.
To provide a certain behavior right after a module has been created, module builder should implement
[`ModuleBuilderListener`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleBuilderListener.java)
Method

```java
public void moduleCreated(@NotNull final Module module);
```

executed tasks right after a module has been created,
these may include configuring roots looking up for an SDK and setting it up, adding a specific facet if required and others.
For more details please see the following
[SmallTalk custom module type](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleType.java)
implementation.

### Adding New Wizard Steps

Adding new steps to the module wizard can be done by overriding the

```java
public ModuleWizardStep[] createWizardSteps(WizardContext wizardContext, ModulesProvider modulesProvider);
```

method in a custom
[module builder](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleBuilder.java).
If this method returns a non-empty array of ModuleWizardStep objects, new steps will be shown in their indexing order while creating a new module.
The following
[implementation](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleWizardStep.java)
for the SmallTalk project type illustrates how a custom wizard step can be created.
The
[`RsModuleWizardStep`](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsModuleWizardStep.java)
class is derived from
[`ModuleWizardStep`](upsource:///platform/lang-api/src/com/intellij/ide/util/projectWizard/ModuleWizardStep.java),
which has two methods to be overridden:

*  ```java
   public JComponent getComponent();
   ```
   defines how the step will look like
*  ```java
   public void updateDataModel();
   ```
   commits data from UI into ModuleBuilder and WizardContext

## Facet

Facets in IntelliJ are the way to store multiple kinds of module-specific settings, for instance to make a language support or framework available in some given module.
To understand facets better from the point of view of an end-user, please see
[Facet](/reference_guide/project_model/facet.md)
documentation section.


## Implementing Project Structure Detector

To support the creation of your module when a project is imported from existing sources, extend [`ProjectStructureDetector`](upsource:///java/idea-ui/src/com/intellij/ide/util/projectWizard/importSources/ProjectStructureDetector.java).

To detect your files your module supports implement

```java
public abstract DirectoryProcessingResult detectRoots(@NotNull File dir, @NotNull File[] children, @NotNull File base,
                                                        @NotNull List<DetectedProjectRoot> result);
```

Refer to the [Smalltalk project structure detector](https://github.com/bulenkov/RedlineSmalltalk/blob/master/src/st/redline/smalltalk/module/RsProjectStructureDetector.java)

But detecting the files is not enough, you also need to create a module for the project if appropriate by implementing `setupProjectStructure()`. Here is an example that creates a module if no other modules exist in the project structure.

```java
@Override
    public void setupProjectStructure(@NotNull final Collection<DetectedProjectRoot> roots,
        @NotNull final ProjectDescriptor projectDescriptor,
        @NotNull final ProjectFromSourcesBuilder builder)
    {
        List<ModuleDescriptor> modules = projectDescriptor.getModules();
        if (modules.isEmpty())
        {
            modules = new ArrayList<>();
            for (DetectedProjectRoot root : roots)
            {
                modules.add(
                    new ModuleDescriptor(root.getDirectory(), MyModuleType.getInstance(),
                        ContainerUtil.emptyList()));
            }
            projectDescriptor.setModules(modules);
        }
    }
```
