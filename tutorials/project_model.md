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