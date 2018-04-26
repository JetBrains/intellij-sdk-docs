---
title: Project
---

In the *IntelliJ Platform*, a project encapsulates all your source code, libraries, build instructions into a single organizational unit. Everything you do in the IDE is done within the context of a project. A project defines some collections referred to as modules and libraries. Depending on the logical and functional requirements for the project, you can create a single-module or a multi-module project.

## Working with projects

The *IntelliJ Platform* stores the project configuration data in XML files. The list of those files depends on the plugin [project](https://www.jetbrains.com/help/idea/about-projects.html) format.

For file-based format projects, the information core to the project itself (e.g. location of the component modules, compiler settings, etc.) is stored in the `%project_name%.ipr` file. The information about modules the project includes is stored in `%module_name%.iml` files. Module files are created for each module.

For directory-based format projects, the project and workspace settings are stored in a number of XML files under the `%project_home_directory%/.idea` directory. Each XML file is responsible for its own set of settings and can be recognized by its name: `projectCodeStyle.xml`, `encodings.xml`, `vcs.xml` etc. As for the file-based format projects, `.iml` files describe modules.

Note that you don't need to access project files directly to load or save settings. See [Persisting State of Components](persisting_state_of_components.md) for more information.

To work with projects and project files, you can use the following classes and interfaces:

* [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java) interface.
* [`ProjectRootManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java) abstract class.
* [`ProjectManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectManager.java) abstract class.
* [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface.

Other classes for working with the project model are located in the [projectModel-api.openapi](upsource:///platform/projectModel-api/src/com/intellij/openapi) package. Basic API classes and interfaces for the concepts of [project](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java), [module](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java), [application](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java) and [component](upsource:///platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java) are placed in the [core-api.openapi](upsource:///platform/core-api/src/com/intellij/openapi) package.

### How do I get a list of source roots for all modules in my project?

Use the `ProjectRootManager.getContentSourceRoots()` method. To clarify this, consider the following code snippet:

```java
String projectName = project.getName();
VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
String sourceRootsList = Arrays.stream(vFiles).map(VirtualFile::getUrl).collect(Collectors.joining("\n"));

Messages.showInfoMessage("Source roots for the " + projectName + " plugin:\n" + sourceRootsList, "Project Properties");
```

### Checking if a file belongs to a project

Use [ProjectFileIndex.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) to get this information:
```java
ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();
```

### How do I get the content or source root to which the specified file or directory belongs?

Use the `ProjectFileIndex.getContentRootForFile` and `ProjectFileIndex.getSourceRootForFile` methods. For example:

```java
VirtualFile moduleContentRoot = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(virtualFileOrDirectory);
VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);
```

Note that this method returns `null` if the file or directory does not belong to any source root of modules in the project.
 
### How do I check whether a file or directory is related to the project libraries?

The `ProjectFileIndex` interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources.

You can use the following methods:

* `ProjectFileIndex.`**`isLibraryClassFile`**`(virtualFile)`: Returns `true` if the specified `virtualFile` is a compiled class file.
* `ProjectFileIndex.`**`isInLibraryClasses`**`(virtualFileorDirectory)`: Returns `true` if the specified `virtualFileorDirectory` belongs to library classes.
* `ProjectFileIndex.`**`isInLibrarySource`**`(virtualFileorDirectory)`: Returns `true` if the specified `virtualFileorDirectory` belongs to library sources.

### How do I get the project SDK?

* To get the project-level SDK: `Sdk projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();`
* To get the project-level SDK name: `String projectSdkName = ProjectRootManager.getInstance(project).getProjectSdkName();`

### How do I set the project SDK?

* To set the project-level SDK: `ProjectRootManager.getInstance(project).setProjectSdk(Sdk jdk);`
* To set the project-level SDK name: `ProjectRootManager.getInstance(project).setProjectSdkName(String name);`

Note that by default, the project modules use the project SDK. Optionally, you can configure an individual SDK for each module.

## Changing the project structure

Utility classes which can be used for modifying a project structure can be found in the package [projectModel-impl.openapi](upsource:///platform/projectModel-impl/src/com/intellij/openapi). Its [roots](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/) subpackage contains instances and utilities intended for work with project and module source roots, including [ModuleRootModificationUtil.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) and [ProjectRootUtil.java](upsource:///platform/projectModel-impl/src/com/intellij/openapi/projectRoots/impl/ProjectRootUtil.java). Project structure
changes need to be performed in a [write action](/basics/architectural_overview/general_threading_rules.html#readwrite-lock).

Refer to the [basic example](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/ModificationAction.java) of an on-the-fly project structure modification to learn how it can be implemented.

## Receiving notifications about project structure changes

To receive notifications about changes in project structure (modules or libraries being added or removed, module dependencies being changed and so on),
use the [message bus](/reference_guide/messaging_infrastructure.md) and the `ProjectTopics.PROJECT_ROOTS` topic:

```java
project.getMessageBus().connect().subscribe(ProjectTopics.PROJECT_ROOTS, new ModuleRootListener() {
  @Override
  public void rootsChanged(ModuleRootEvent event) {
  }
});
```

The event only notifies you that something has changed; if you need to know in detail what changes have occurred, you
need to keep a copy of the state of the project structure model which is relevant for you, and to compare it with the
current state after the change.
