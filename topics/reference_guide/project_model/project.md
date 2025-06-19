<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Project

<link-summary>Introduction to projects and their structure. Retrieving and modifying project setup.</link-summary>

<tldr>

**Product Help:** [Configure projects](https://www.jetbrains.com/help/idea/working-with-projects.html)

</tldr>

> Everything in the IntelliJ Platform SDK is done within the context of a project.

The IntelliJ Platform stores the project configuration data in XML files.
The list of those files depends on the chosen [project format](https://www.jetbrains.com/help/idea/creating-and-managing-projects.html#project-formats):

<tabs>
<tab title="Directory-Based">

Project and workspace settings are stored in a number of XML files under the <path>\$project_home_directory\$/.idea</path> directory.
Each XML file is responsible for its own set of settings and can be recognized by its name: <path>projectCodeStyle.xml</path>, <path>encodings.xml</path>, <path>vcs.xml</path> etc.
As for the file-based format projects, <path>.iml</path> files describe modules.

</tab>

<tab title="File-Based (Legacy)">

For file-based format projects (legacy), the information core to the project itself (e.g., location of the component modules, compiler settings, etc.) is stored in the <path>\$project_name\$.ipr</path> file.
The information about modules the project includes is stored in <path>\$module_name\$.iml</path> files.
Module files are created for each module.

</tab>
</tabs>

Note that direct access to project files isn't required to load or save settings.
See [Persisting State of Components](persisting_state_of_components.md) for more information.

## Working with Projects

<snippet id="useWorkspaceModelAPI">

> The [](workspace_model.md) API is available since 2024.2 for use by third-party plugins and should be preferred over using the [Project Model](project_model.md) API.
>
> See [](workspace_model.md#interoperability) and [](workspace_model_usages.md).
>
{title="Workspace Model API (2024.2+)" style="note"}

</snippet>

To work with projects and project files, use the following classes and interfaces:

* [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java)
* [`ProjectRootManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)
* [`ProjectManager`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManager.java)
* [`ProjectFileIndex`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)

Other classes for working with the project model are located in the [`projectModel-api.openapi`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi) package.
Basic API classes and interfaces for the concepts of [`Project`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/Project.java), [`Module`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java) and [`Application`](%gh-ic%/platform/core-api/src/com/intellij/openapi/application/Application.java)  are placed in the [`core-api.openapi`](%gh-ic%/platform/core-api/src/com/intellij/openapi) package.

### How to get a `Project` instance?

A Project instance is available in multiple contexts:

| Context                    | API                                                                                                                                                                                                                                                      |
|----------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| [Action](action_system.md) | <p>[`AnActionEvent.getProject()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/actionSystem/AnActionEvent.java)</p><p>[`DataContext.getData(CommonDataKeys.PROJECT)`](%gh-ic%/platform/core-ui/src/openapi/actionSystem/DataContext.java)</p> |
| [Editor](editor_basics.md) | [`Editor.getProject()`](%gh-ic%/platform/editor-ui-api/src/com/intellij/openapi/editor/Editor.java)                                                                                                                                                      |
| [Module](module.md)        | [`Module.getProject()`](%gh-ic%/platform/core-api/src/com/intellij/openapi/module/Module.java)                                                                                                                                                           |
| [PSI](psi.md)              | [`PsiElement.getProject()`](%gh-ic%/platform/core-api/src/com/intellij/psi/PsiElement.java)                                                                                                                                                              |
| [Tests](testing_plugins.md) | [`IdeaProjectTestFixture.getProject()`](%gh-ic%/platform/testFramework/src/com/intellij/testFramework/fixtures/IdeaProjectTestFixture.java)                                                                                                              |

It is also possible to retrieve projects in generic contexts:
* Project from [`VirtualFile`](virtual_file.md):
  * [`ProjectLocator.guessProjectForFile(VirtualFile)`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/ProjectLocator.kt) - returns any project containing a given file.
  * [`ProjectLocator.getProjectsForFile(VirtualFile)`](%gh-ic%/platform/core-api/src/com/intellij/openapi/project/ProjectLocator.kt) - returns the list of projects that a given file is a part of.
* List of currently opened projects: [`ProjectManager.getOpenProjects()`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManager.java)

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

### Checking If a File Belongs to a Project

Use [`ProjectFileIndex`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) to get this information:

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

The [`ProjectFileIndex`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources:
* `isLibraryClassFile()`: Returns `true` if the specified `virtualFile` is a compiled class file.
* `isInLibraryClasses()`: Returns `true` if the specified `virtualFileOrDirectory` belongs to library classes.
* `isInLibrarySource()`: Returns `true` if the specified `virtualFileOrDirectory` belongs to library sources.

### Getting the Project SDK

Note that by default, the project modules use the project SDK.
Optionally, you can configure an individual SDK for each module.
See [SDK](sdk.md) for more details.

## Changing the Project Structure

Utility classes used for modifying the project structure can be found in the package [`projectModel-impl.openapi`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi).
Its [`roots`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots) subpackage contains instances and utilities intended for work with project and module source roots, including [`ModuleRootModificationUtil`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) and [`ProjectRootUtil`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/projectRoots/impl/ProjectRootUtil.java).
Project structure changes need to be performed in [write action](threading_model.md#write-actions).

Refer to the [project_model](%gh-sdk-samples-master%/project_model/src/main/java/org/intellij/sdk/project/model/ModificationAction.java) code sample to learn how project structure modification can be implemented.

## Receiving Notifications About Project Structure Changes

To receive notifications about changes in project structure (modules or libraries being added or removed, module dependencies being changed, and so on), use the [message bus](messaging_infrastructure.md) and the `ProjectTopics.PROJECT_ROOTS` topic:

```java
project.getMessageBus().connect().subscribe(
    ProjectTopics.PROJECT_ROOTS,
    new ModuleRootListener() {
      @Override
      public void rootsChanged(@NotNull ModuleRootEvent event) {
        // action
      }
    });
```

[Declarative registration](plugin_listeners.md) is available as well.

The event only notifies that something has changed; if more details are needed about what changes have occurred, keep a copy of the state of the project structure model which is relevant, and to compare it with the state after the change.

## Receiving Notification About Project Close/Open Events

Use [`ProjectManagerListener`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/project/ProjectManagerListener.java) [listener](plugin_listeners.md) or [project open activity](plugin_components.md#project-open).
