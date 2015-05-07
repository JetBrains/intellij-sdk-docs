---
layout: editable
title: Library
---

A library is an archive of compiled code (such as JAR files) that your modules depend on.
IntelliJ IDEA supports three types of libraries:

* **Module Library**: the library classes are visible only in this module and the library information is recorded in the module *.iml file.
* **Project Library**: the library classes are visible within the project and the library information is recorded in the project *.ipr file or in .idea/libraries.
* **Global Library**: the library information is recorded in the applicationLibraries.xml file into the <User Home>/.IntelliJIdea/config/options directory. Global libraries are similar to project libraries, but are visible for the different projects.

For more information about libraries, refer to
[Library](http://www.jetbrains.com/idea/webhelp/library.html).

## Accessing Libraries and Jars

Package
[libraries](https://github.com/JetBrains/intellij-community/tree/master/platform/projectModel-api/src/com/intellij/openapi/roots/libraries)
provides functionality for working with project libraries and jars.
Libraries and jars can be retrieved like the following snippet shows

    ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
    OrderEntry orderEntry : fileIndex.getOrderEntriesForFile(virtualFile));
    
## Checking Belonging to a Library

The ProjectFileIndex interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources.
You can use the following methods:

* ```ProjectFileIndex.isLibraryClassFile(virtualFile)```: Returns true if the specified virtualFile is a compiled class file.
* ```ProjectFileIndex.isInLibraryClasses(virtualFileorDirectory)```: Returns true if the specified virtualFileorDirectory belongs to library classes.
* ```ProjectFileIndex.isInLibrarySource(virtualFileorDirectory)```: Returns true if the specified virtualFileorDirectory belongs to library sources.

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectFileIndexSampleAction.java)


Note that by default, the project modules use the project SDK. 
Optionally, you can configure individual SDK for each module.

### Checking Belonging to a Module Source Root

Use the ProjectFileIndex.getSourceRootForFile method. For example:

```VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);```

Note that this method returns null if the file or directory does not belong to any source root of modules in the project.

[Code sample] (https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/ProjectFileIndexSampleAction.java)


More details can be found in this
[code sample](https://github.com/JetBrains/intellij-sdk/blob/master/code_samples/project_model/src/com/intellij/plugins/project/model/LibrariesAction.java)


