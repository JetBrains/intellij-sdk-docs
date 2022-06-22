[//]: # (title: Project Structure)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<!--TODO split into parts accordingly to the table of contents-->

This topic considers the concept of projects based on the IntelliJ Platform and related subjects, such as _modules_, _facets_, _libraries_, and _SDK_.
The project structure and Java classes available to manage projects and modules are discussed.

> Internal changes related to a significant redesign of the representation of project models are included in 2020.3 release; please see [blog post](https://blog.jetbrains.com/platform/2020/10/new-implementation-of-project-model-interfaces-in-2020-3/) for details.
> This shouldn't affect any plugins using the IntelliJ API properly and which don't access internal classes.
>
{type="note"}

## Project and Its Components
This section briefly discusses the IDEA project structure, project components, and related terms.
For more information about projects and their components, refer to [Project](https://www.jetbrains.com/help/idea/about-projects.html), [Module](https://www.jetbrains.com/help/idea/creating-and-managing-modules.html), [Library](https://www.jetbrains.com/help/idea/working-with-libraries.html), [Facet](https://www.jetbrains.com/help/idea/adding-support-for-frameworks-and-technologies.html#facets) in the IntelliJ IDEA Web Help.

Use [`ProjectSettingsService`](upsource:///platform/lang-impl/src/com/intellij/openapi/roots/ui/configuration/ProjectSettingsService.java) to open related entries in <control>Project Structure</control> dialog.

### Project
In the IntelliJ Platform, a _project_ encapsulates all of a project's source code, libraries, and build instructions into a single organizational unit.
Everything done using the IntelliJ Platform SDK is done within the context of a project.
A project defines collections referred to as _modules_ and _libraries_.
Depending on the project's logical and functional requirements, you can create a _single-module_ or a _multi-module_ project.

### Module
A _module_ is a discrete unit of functionality that can be run, tested, and debugged independently.
Modules include such things as source code, build scripts, unit tests, deployment descriptors, etc.
In a project, each module can use a specific SDK or inherit the SDK defined at the project level (see the [SDK](#sdk) section below in this document).
A module can depend on other modules of the project.

### Library
A _library_ is an archive of compiled code (such as JAR files) on which modules depend.
The IntelliJ Platform supports three types of libraries:
* **Module Library**: the library classes are visible only in this module, and the library information is recorded in the module's <path>.iml</path> file.
* **Project Library**: the library classes are visible within the project, and the library information is recorded in the project's <path>.ipr</path> file or in <path>.idea/libraries</path>.
* **Global Library**: the library information is recorded in the <path>applicationLibraries.xml</path> file in the <path>~/.IntelliJIdea/config/options</path> directory.
  Global libraries are similar to project libraries but are visible for different projects.

For more information about libraries, refer to [Library](https://www.jetbrains.com/help/idea/working-with-libraries.html).

### SDK
Every project uses a Software Development Kit (_SDK_).
For Java projects, SDK is referred to as JDK (Java Development Kit).

The SDK determines which API library is used to build the project.
If a project is multi-module, the project SDK is common for all modules within the project by default.
Optionally, a project can configure an individual SDK for each module.

For more information about SDKs, see [Working with SDKs](https://www.jetbrains.com/help/idea/working-with-sdks.html) in the IntelliJ IDEA Web Help.

### Facet
A _facet_ represents a certain configuration, specific for a particular framework/technology associated with a module.
A module can have multiple facets.
E.g., Spring-specific configuration is stored in a Spring facet.

Facets are documented under [Facet](https://www.jetbrains.com/help/idea/adding-support-for-frameworks-and-technologies.html#facets) and [Language and Framework Specific Guidelines](https://www.jetbrains.com/help/idea/language-and-framework-specific-guidelines.html) in the IntelliJ IDEA Web Help.

## Additional Information
For more information on each of these entities, see:

- [Project](project.md)
- [Module](module.md)
- [SDK](sdk.md)
- [Library](library.md)
- [External system integration](external_system_integration.md) (for projects imported from Gradle or similar build systems)
