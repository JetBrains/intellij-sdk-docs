[//]: # (title: Project)

<!-- Copyright 2000-2022 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

## Working with Projects

The IntelliJ Platform stores the project configuration data in XML files.
The list of those files depends on the chosen [project format](https://www.jetbrains.com/help/idea/creating-and-managing-projects.html#project-formats).

For file-based format projects (legacy), the information core to the project itself (e.g., location of the component modules, compiler settings, etc.) is stored in the <path>$project_name$.ipr</path> file.
The information about modules the project includes is stored in <path>$module_name$.iml</path> files.
Module files are created for each module.

For directory-based format projects, the project and workspace settings are stored in a number of XML files under the <path>$project_home_directory$/.idea</path> directory.
Each XML file is responsible for its own set of settings and can be recognized by its name: <path>projectCodeStyle.xml</path>, <path>encodings.xml</path>, <path>vcs.xml</path> etc.
As for the file-based format projects, <path>.iml</path> files describe modules.

Note that direct access to project files isn't required to load or save settings.
See [Persisting State of Components](persisting_state_of_components.md) for more information.

To work with projects and project files, use the following classes and interfaces:
* [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java)
* [`ProjectRootManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)
* [`ProjectManager`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectManager.java)
* [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)

Other classes for working with the project model are located in the [`projectModel-api.openapi`](upsource:///platform/projectModel-api/src/com/intellij/openapi) package.
Basic API classes and interfaces for the concepts of [`Project`](upsource:///platform/core-api/src/com/intellij/openapi/project/Project.java), [`Module`](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java) and [`Application`](upsource:///platform/core-api/src/com/intellij/openapi/application/Application.java)  are placed in the [`core-api.openapi`](upsource:///platform/core-api/src/com/intellij/openapi) package.

### How to get a Project instance?

A Project instance is available in multiple contexts:

| Context                          | API                                                                                                                                                                                                                                                     |
|----------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](basic_action_system.md) | [`AnActionEvent.getProject()`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)<br/>[`DataContext.getData(CommonDataKeys.PROJECT)`](upsource:///platform/core-ui/src/openapi/actionSystem/DataContext.java) |
| [Editor](editor_basics.md)       | [`Editor.getProject()`](upsource:///platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java)                                                                                                                                                 |
| [Module](module.md)              | [`Module.getProject()`](upsource:///platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                                                                                      |
| [PSI](psi.md)                    | [`PsiElement.getProject()`](upsource:///platform/core-api/src/com/intellij/psi/PsiElement.java)                                                                                                                                                         |
| [Tests](testing_plugins.md)      | [`IdeaProjectTestFixture.getProject()`](upsource:///platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaProjectTestFixture.java)                                                                                                         |

It is also possible to retrieve projects in generic contexts:
* Project from [`VirtualFile`](virtual_file.md):
  * [`ProjectLocator.guessProjectForFile(VirtualFile)`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectLocator.java) - returns any project containing a given file.
  * [`ProjectLocator.getProjectsForFile(VirtualFile)`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectLocator.java) - returns the list of projects that a given file is a part of.
* List of currently opened projects: [`ProjectManager.getOpenProjects()`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectManager.java)

### Getting a List of Source Roots for All Modules in a Project

Use the `ProjectRootManager.getContentSourceRoots()` method.
To clarify this, consider the following code snippet:

```java
String projectName = project.getName();
VirtualFile[] vFiles = ProjectRootManager.getInstance(project)
    .getContentSourceRoots();
String sourceRootsList = Arrays.stream(vFiles)
    .map(VirtualFile::getUrl)
    .collect(Collectors.joining("\n"));
Messages.showInfoMessage("Source roots for the " + projectName +
    " plugin:\n" + sourceRootsList, "Project Properties");
```

### Checking if a File Belongs to a Project

Use [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) to get this information:

```java
ProjectFileIndex projectFileIndex =
    ProjectRootManager.getInstance(project).getFileIndex();
```

### Getting the Content or Source Root to Which a File or Directory Belongs

Use the `ProjectFileIndex.getContentRootForFile()` and `ProjectFileIndex.getSourceRootForFile()` methods.
For example:

```java
VirtualFile moduleContentRoot = ProjectRootManager.getInstance(project)
    .getFileIndex().getContentRootForFile(virtualFileOrDirectory);
VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project)
    .getFileIndex().getSourceRootForFile(virtualFileOrDirectory);
```

Note that this method returns `null` if the file or directory does not belong to any source root of modules in the project.

### Checking Whether a File or Directory Is Related to the Project Libraries

The [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources:
* `isLibraryClassFile()`: Returns `true` if the specified `virtualFile` is a compiled class file.
* `isInLibraryClasses()`: Returns `true` if the specified `virtualFileOrDirectory` belongs to library classes.
* `isInLibrarySource()`: Returns `true` if the specified `virtualFileOrDirectory` belongs to library sources.

### Getting the Project SDK

Note that by default, the project modules use the project SDK.
Optionally, you can configure an individual SDK for each module.
See [SDK](sdk.md) for more details.

## Changing the Project Structure

Utility classes used for modifying the project structure can be found in the package [`projectModel-impl.openapi`](upsource:///platform/projectModel-impl/src/com/intellij/openapi).
Its [`roots`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots) subpackage contains instances and utilities intended for work with project and module source roots, including [`ModuleRootModificationUtil`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) and [`ProjectRootUtil`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/projectRoots/impl/ProjectRootUtil.java).
Project structure changes need to be performed in [write action](general_threading_rules.md#read-write-lock).

Refer to the [project_model](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/project_model/src/main/java/org/intellij/sdk/project/model/ModificationAction.java) code sample to learn how project structure modification can be implemented.

## Receiving Notifications About Project Structure Changes

To receive notifications about changes in project structure (modules or libraries being added or removed, module dependencies being changed, and so on), use the [message bus](messaging_infrastructure.md) and the `ProjectTopics.PROJECT_ROOTS` topic:

```java
project.getMessageBus().connect().subscribe(ProjectTopics.PROJECT_ROOTS,
    new ModuleRootListener() {
      @Override
      public void rootsChanged(ModuleRootEvent event) {
      }
    });
```

If targeting 2019.3 or later, [declarative registration](plugin_listeners.md) is available as well.

The event only notifies that something has changed; if more details are needed about what changes have occurred, keep a copy of the state of the project structure model which is relevant, and to compare it with the state after the change.

## Receiving Notification About Project Close/Open Events

Use [`ProjectManagerListener`](upsource:///platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) [listener](plugin_listeners.md)
