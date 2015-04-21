Project model. Project roots and libraries. Configuring the project from code.
=================

This topic considers the concept of IntelliJ IDEA projects and related subjects, such as modules, facets, libraries, SDK.
The project structure and Java classes you can use to manage projects and modules have been considered.

#Project and It's Components
This section briefly discusses the IDEA project structure, project components and related terms. For more information about projects and their components, refer to
[Project] (http://www.jetbrains.com/idea/webhelp/project.html),
[Module] (http://www.jetbrains.com/idea/webhelp/module.html),
[Library] (http://www.jetbrains.com/idea/webhelp/library.html),
and
[Facet] (http://www.jetbrains.com/idea/webhelp/facet.html)
in
[IntelliJ IDEA Web Help] (https://www.jetbrains.com/idea/help/intellij-idea.html).

##Project
In IntelliJ IDEA, a project encapsulates all your source code, libraries, build instructions into a single organizational unit.
Everything you do in IntelliJ IDEA, is done within the context of a project.
A project defines some collections referred to as modules and libraries.
Depending on the logical and functional requirements to the project, you can create a single-module or a multi-module project.

##Module
A module is a discrete unit of functionality that can be run, tested, and debugged independently.
Modules includes such things as source code, build scripts, unit tests, deployment descriptors, etc.
In the project, each module can use a specific SDK or inherit SDK defined on the project level (see the SDK section later in this document).
A module can depend on other modules of the project.

##Library
A library is an archive of compiled code (such as JAR files) that your modules depend on.
IntelliJ IDEA supports three types of libraries:

* **Module Library**: the library classes are visible only in this module and the library information is recorded in the module *.iml file.
* **Project Library**: the library classes are visible within the project and the library information is recorded in the project *.ipr file or in .idea/libraries.
* **Global Library**: the library information is recorded in the applicationLibraries.xml file into the <User Home>/.IntelliJIdea/config/options directory. Global libraries are similar to project libraries, but are visible for the different projects.

For more information about libraries, refer to
[Library] (http://www.jetbrains.com/idea/webhelp/library.html).

##SDK
Every project uses a Software Development Kit (SDK).
For Java projects, SDK is referred to as JDK (Java Development Kit).
SDK determines which API library is used to build the project. If your project is multi-module, the project SDK by default is common for all modules within the project.
Optionally, you can configure individual SDK for each module.
For more information about SDKs, see
[JDKs] (http://www.jetbrains.com/idea/webhelp/jdks.html)
and
[Configuring Project JDK] (http://www.jetbrains.com/idea/webhelp/configuring-project-jdk.html)
in IntelliJ IDEA Web Help.

##Facet
A facet represents certain configuration, specific for a particular framework/technology, associated with a module. A module can have multiple facets. E.g. Spring specific configuration is stored in a Spring facet.
For more information about facets see
[Facet] (http://www.jetbrains.com/idea/webhelp/facet.html)
and
[Facet Dependencies] (http://www.jetbrains.com/idea/webhelp/available-facets-and-their-dependencies.html)
in IntelliJ IDEA Web Help.

#Project Structure
A project consists of one or several modules.
Each module includes the plugin source code and so called order entries that refer to SDK and libraries the module uses.
By default, all modules uses the project SDK.
In addition, a module can optionally have a set of facets.
This document explains how you can explore and change the structure of projects using API.

#Working with Projects
This section explains how to complete some common tasks related to management of projects.
The Java classes and interfaces that you can use to explore and change the project contents are discussed.

##How to Work with Project Files?
**IntelliJ IDEA** stores the project configuration data in XML files.
The list of those files depends on the plugin
[project] (http://www.jetbrains.com/idea/webhelp/project.html)
format.
For file-based format projects, the information core to the project itself (e.g. location of the component modules, compiler settings, etc.) is stored in the <%project name%>.IPR file.
The information about modules the project includes is stored in <%module name%>.IML files.
Module files are created for each module.

**TODO** Links to files mentioned above

For directory-based format projects, the project and workspace settings are stored in a number of XML files under the <%Project home directory%>/.idea directory.
Each XML file is responsible for its own set of settings and can be recognized by its name: projectCodeStyle.xml, encodings.xml, vcs.xml etc.
As for the file-based format projects, .IML files describe modules.

Main classes providing work with the project model are located in the package
[projectModel-api.openapi](https://github.com/JetBrains/intellij-community/tree/master/platform/projectModel-api/src/com/intellij/openapi).
Basic API classes and interfaces for the concepts of
[project] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/project/Project.java),
[module] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/module/Module.java),
[application] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/application/Application.java),
and
[component] (https://github.com/JetBrains/intellij-community/blob/master/platform/core-api/src/com/intellij/openapi/components/ProjectComponent.java)
are placed in the
[core-api.openapi] (https://github.com/JetBrains/intellij-community/tree/master/platform/core-api/src/com/intellij/openapi)
package.

##How do I get a list of source roots for all modules in my project?
Use the
```ProjectRootManager.getContentSourceRoots() method```.
To clarify this, see the following:

[code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ShowSourceRootsActions.java).

##How do I check whether a file is related to a project?
Use [ProjectFileIndex.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)
to get this information.

###How do I get an instance of the ProjectFileIndex interface?
Use the ProjectRootManager.getFileIndex() method. For example:
```ProjectFileIndex projectFileIndex = ProjectRootManager.getInstance(project).getFileIndex();```

###How do I get a module to which a file belongs?
To determine a module in the project in question to which the specified virtual file belongs, use the
ProjectFileIndex.getModuleForFile(virtualFile) method:

```Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(virtualFile);```

Note that this method returns null if the file does not belong to any module.
You can also use the ProjectFileIndex.getContentRootForFile method to get the module content root to which the specified file or directory belongs:

```VirtualFile moduleContentRoot = ProjectRootManager.getInstance(project).getFileIndex().getContentRootForFile(virtualFileOrDirectory);```

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectFileIndexSampleAction.java)

###How do I get the module source root or library source root to which the specified file or directory belongs?

Use the ProjectFileIndex.getSourceRootForFile method. For example:

```VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);```

Note that this method returns null if the file or directory does not belong to any source root of modules in the project.

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectFileIndexSampleAction.java)

###How do I check whether a file or directory is related to the project libraries?
The ProjectFileIndex interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources.
You can use the following methods:

* ```ProjectFileIndex.isLibraryClassFile(virtualFile)```: Returns true if the specified virtualFile is a compiled class file.
* ```ProjectFileIndex.isInLibraryClasses(virtualFileorDirectory)```: Returns true if the specified virtualFileorDirectory belongs to library classes.
* ```ProjectFileIndex.isInLibrarySource(virtualFileorDirectory)```: Returns true if the specified virtualFileorDirectory belongs to library sources.

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectFileIndexSampleAction.java)

###How do I get and set the project SDK?
* To get the project-level SDK: ```Sdk projectSDK = ProjectRootManager.getInstance(project).getProjectSdk();```
* To get the project-level SDK name: ```String projectSDKName = ProjectRootManager.getInstance(project).getProjectSdkName();```
* To set the project-level SDK: ```ProjectRootManager.getInstance(project).setProjectSdk(Sdk jdk);```
* To set the project-level SDK name: ```ProjectRootManager.getInstance(project).setProjectSdkName(String name);```

Note that by default, the project modules use the project SDK. Optionally, you can configure individual SDK for each module.

###How to get a file module?
```final Module currentModule = fileIndex.getModuleForFile(virtualFile);```

###How to get a module file index?
Information about model roots can be accessed via the class
 [ModuleRootManager.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java),
for example, an instance of
[ModuleFileIndex.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleFileIndex.java)
can be obtained, which is analogical to the
[ProjectFileIndex.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)
but in the scope of a module

```ModuleRootManager.getInstance(currentModule).getFileIndex()```

##Changing the project structure
Utility classes which can be used for modifying a project structure can be found in the package
[projectModel-impl.openapi] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-impl/src/com/intellij/openapi).
It's
[roots] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-impl/src/com/intellij/openapi/roots/)
subpackage contains instances and utilities meant to work with project and module source roots, including
[ModuleRootModificationUtil.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-impl/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java)
and
[ProjectRootUtil.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-impl/src/com/intellij/openapi/projectRoots/impl/ProjectRootUtil.java)

A basic example can be viewed
[here] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ModificationAction.java)

##Accessing libraries and jars
Package
[libraries] (https://github.com/JetBrains/intellij-community/tree/master/platform/projectModel-api/src/com/intellij/openapi/roots/libraries)
provides functionality for working with project libraries and jars.
Libraries and jars can be retrieved like the following snippet shows

    ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
    OrderEntry orderEntry : fileIndex.getOrderEntriesForFile(virtualFile));

More details can be found in this
[code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/LibrariesAction.java)

##Project Sdk information
Main information about the project Sdk can be accessed via
[ProjectRootManager.java] (https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectRootManager.java)
like the following example shows

```String projectSdk = ProjectRootManager.getInstance(project).getProjectSdk();```

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectSdkAction.java)

