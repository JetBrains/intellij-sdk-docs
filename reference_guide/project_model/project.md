---
title: Project
---

In the *IntelliJ Platform*, a project encapsulates all your source code, libraries, build instructions into a single organizational unit. Everything you do in the IDE, is done within the context of a project. A project defines some collections referred to as modules and libraries. Depending on the logical and functional requirements to the project, you can create a single-module or a multi-module project.

## Working with projects

The *IntelliJ Platform* stores the project configuration data in XML files. The list of those files depends on the plugin [project](http://www.jetbrains.com/idea/help/project.html) format.

For file based format projects, the information core to the project itself (e.g. location of the component modules, compiler settings, etc.) is stored in the `%project_name%.ipr` file. The information about modules the project includes is stored in `%module_name%.iml` files. Module files are created for each module.

For directory based format projects, the project and workspace settings are stored in a number of XML files under the `%project_home_directory%/.idea` directory. Each XML file is responsible for its own set of settings and can be recognized by its name: `projectCodeStyle.xml`, `encodings.xml`, `vcs.xml` etc. As for the file-based format projects, `.iml` files describe modules.

Main classes providing work with the project model are located in the package [projectModel-api.openapi](upsource:///platform/projectModel-api/src/com/intellij/openapi). Basic API classes and interfaces for the concepts of [project](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java), [module](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java), [application](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java), and [component](upsource:///platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java) are placed in the [core-api.openapi](upsource:///platform/core-api/src/com/intellij/openapi) package.

### Finding source roots

To get an array of all the source roots for a project use `ProjectRootManager.getContentSourceRoots()` method like this code snippet shows:

```java
VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentSourceRoots();
```

### Checking if a file belongs to a project

Use [ProjectFileIndex.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) to get this information.

### Getting an Instance of the ProjectFileIndex Interface

Use the `ProjectRootManager.getFileIndex()` method. For example:

```java
ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();
```

Note that this method returns `null` if the file does not belong to any module. You can also use the `ProjectFileIndex.getContentRootForFile()` method to get the module content root to which the specified file or directory belongs:

```java
VirtualFile moduleContentRoot = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(virtualFileOrDirectory);
```

## Changing the project structure

Utility classes which can be used for modifying a project structure can be found in the package [projectModel-impl.openapi](upsource:///platform/projectModel-impl/src/com/intellij/openapi). It's [roots](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/) subpackage contains instances and utilities meant to work with project and module source roots, including [ModuleRootModificationUtil.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) and [ProjectRootUtil.java](upsource:///platform/projectModel-impl/src/com/intellij/openapi/projectRoots/impl/ProjectRootUtil.java).

Refer to the [basic example](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/ModificationAction.java) of on-the-fly project structure modification to learn how it can be implemented.
