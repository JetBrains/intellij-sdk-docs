---
title: Library
---

A library is an archive of compiled code (such as JAR files) that your modules depend on.
The IntelliJ Platform supports three types of libraries:

* **Module Library**: the library classes are visible only in this module and the library information is recorded in the module *.iml file.
* **Project Library**: the library classes are visible within the project and the library information is recorded in the project *.ipr file or in .idea/libraries.
* **Global Library**: the library information is recorded in the applicationLibraries.xml file into the `<User Home>/.IntelliJIdea/config/options` directory. Global libraries are similar to project libraries, but are visible for the different projects.

For more information about libraries, refer to
[Library](http://www.jetbrains.com/idea/help/library.html).

## Accessing Libraries and Jars

Package
[libraries](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries)
provides functionality for working with project libraries and jars.
Libraries and jars can be retrieved like the following snippet shows

```java
ProjectFileIndex fileIndex = ProjectRootManager.getInstance(project).getFileIndex();
OrderEntry orderEntry : fileIndex.getOrderEntriesForFile(virtualFile));
```
    
## Checking Belonging to a Library

The [ProjectFileIndex](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources.
You can use the following methods:

* To check if a specified virtual file is a compiled class file use

  ```java
  ProjectFileIndex.isLibraryClassFile(virtualFile)
  ```
* To check if a specified virtual file or directory belongs to library classes use

  ```java
  ProjectFileIndex.isInLibraryClasses(virtualFileorDirectory)
  ```
* To check if the specified virtual file or directory belongs to library sources use

  ```java
  ProjectFileIndex.isInLibrarySource(virtualFileorDirectory)
  ```

See the following [code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/ProjectFileIndexSampleAction.java)
to see how the method mentioned above can be applied.


**Note:** 
By default, the project modules use the project SDK. 
Optionally, you can configure individual SDK for each module.


More details on libraries can be found in this
[code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/LibrariesAction.java)
