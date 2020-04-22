---
title: Module
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A _module_ is a discrete unit of functionality that can be run, tested, and debugged independently. Modules include such things as source code, build scripts, unit tests, deployment descriptors, etc.

The key components of a module are:
  * **Content roots** - the directories where the files belonging to the module (source code, resources, etc.)
    are stored. Each directory can belong to one and only one module; it's not possible to share a content root
    between multiple modules.
  * **Source roots** - A content root can have multiple **source roots** underneath it. Source roots can have different types:
   regular source roots, test source roots, resource roots, etc. In IntelliJ IDEA, source roots are used as roots of the package hierarchy
   structure (Java classes directly under a source root will be in the root package). Source roots can also be used to
   implement more fine-grained dependency checks (code under a regular source root cannot depend on code under a test
   source root). 
   > **NOTE**  Not all other IntelliJ Platform-based IDEs use source roots.
  * **Order entries** - the dependencies of a module, which are stored in an ordered list. A dependency can be a reference
    to an [SDK](sdk.md), a [library](library.md), or another module.
  * **[Facets](facet.md)** - the list of framework-specific configuration entries.

In addition to that, a module can store other settings, such as a module-specific [SDK](sdk.md), compile output path
settings, etc. 
Plugins can store additional data associated with a module by creating facets or module-level components.


The *IntelliJ Platform* provides a number of classes and interfaces you can use to work with modules:

* [`Module`](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)
* [`ModuleUtil`](upsource:///platform/lang-api/src/com/intellij/openapi/module/ModuleUtil.java)
* [`ModuleManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/module/ModuleManager.java)
* [`ModuleRootManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java)
* [`ModuleRootModel`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModel.java)
* [`ModifiableModuleModel`](upsource:///platform/projectModel-api/src/com/intellij/openapi/module/ModifiableModuleModel.java)
* [`ModifiableRootModel`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModifiableRootModel.java)

This section discusses how to complete some common tasks related to management of modules.

### How do I get a list of modules the project includes?

Use the `ModuleManager.getModules()` method.

### How do I get dependencies and classpath of a module?

_Order entries_ include SDK, libraries and other modules the module uses. With the *IntelliJ IDEA* UI, you can view order entries for a module on the [Dependencies](https://www.jetbrains.com/help/idea/dependencies-tab.html) tab of the *Project Structure* dialog box.

To explore the [module dependencies](https://www.jetbrains.com/help/idea/dependencies-tab.html), use the [`OrderEnumerator`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/OrderEnumerator.java) class.

The following code snippet illustrates how you can get classpath (classes root of all dependencies) for a module:

```java
VirtualFile[] roots = ModuleRootManager.getInstance(module).orderEntries().classes().getRoots();
```

### How do I get the SDK the module uses?

Use the `ModuleRootManager.getSdk()` method. This method returns a value of the [`Sdk`](upsource:///platform/projectModel-api/src/com/intellij/openapi/projectRoots/Sdk.java) type.

The following code snippet illustrates how you can get detailed information on SDK the specified module uses:

```java
ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
Sdk SDK = moduleRootManager.getSdk();
String jdkInfo = "Module: " + module.getName() + " SDK: " + SDK.getName() + " SDK version: "
                 + SDK.getVersionString() + " SDK home directory: " + SDK.getHomePath();
```

### How do I get a list of modules on which this module directly depends?

Use the `ModuleRootManager.getDependencies()` method to get an array of the `Module` type values or the `ModuleRootManager.getDependencyModuleNames()` to get an array of module names. To clarify, consider the following code snippet:

```java
ModuleRootManager moduleRootManager = ModuleRootManager.getInstance(module);
Module[] dependentModules = moduleRootManager.getDependencies();
String[] dependentModulesNames = moduleRootManager.getDependencyModuleNames();
```

### How do I get a list of modules that depend on this module?

Use the `ModuleManager.getModuleDependentModules(module)` method.

Note that you can also check whether a module (*module1*) depends on another specified module (*module2*) using the `ModuleManager.isModuleDependent()` method in the following way:

```java
boolean isDependent = ModuleManager.getInstance(project).isModuleDependent(module1,module2);
```

### How do I get a module to which the specified file or PSI element belongs?

* To get the project module to which the specified file belongs, use the `ModuleUtil.findModuleForFile()` static method.

    To clarify, consider the following code snippet:

```java
String pathToFile = "C:\\users\\firstName.LastName\\plugins\\myPlugin\src\MyAction.java";
VirtualFile virtualFile = LocalFileSystem.getInstance().findFileByPath(pathToFile);
Module module = ModuleUtil.findModuleForFile(virtualFile,myProject);
String moduleName = module == null ? "Module not found" : module.getName();
```

* To get the project module to which the specified [PSI element](/basics/architectural_overview/psi_elements.md) belongs, use the `ModuleUtil.findModuleForPsiElement()` method.


### Accessing Module Roots

Information about module roots can be accessed via [`ModuleRootManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java).
For example, the following snippet shows how to access the content roots of a module:

```java
VirtualFile[] contentRoots = ModuleRootManager.getInstance(module).getContentRoots();
```

### Checking Belonging to a Module Source Root

To check if a virtual file or directory belongs to a module source root, use the `ProjectFileIndex.getSourceRootForFile()` method. This method returns `null` if the file or directory does not belong to any source root of modules in the project.

```java
VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);
```

## Receiving Notifications About Module Changes

To receive notifications about module changes (modules being added, removed or renamed),
use the [message bus](/reference_guide/messaging_infrastructure.md) and the `ProjectTopics.MODULES` topic:

```java
project.getMessageBus().connect().subscribe(ProjectTopics.MODULES, new ModuleListener() {
  @Override
  public void moduleAdded(@NotNull Project project, @NotNull Module module) {

  }
});
```

