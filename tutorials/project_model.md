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



