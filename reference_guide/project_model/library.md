---
title: Library
---

A library is an archive of compiled code (such as JAR files) that your modules depend on.
The IntelliJ Platform supports three types of libraries:

* **Module Library**: the library classes are visible only in this module and the library information is recorded in the module *.iml file.
* **Project Library**: the library classes are visible within the project and the library information is recorded in the project *.ipr file or in .idea/libraries.
* **Global Library**: the library information is recorded in the applicationLibraries.xml file into the `<User Home>/.IntelliJIdea/config/options` directory. Global libraries are similar to project libraries, but are visible for the different projects.

For more information about libraries, refer to
[Library](https://www.jetbrains.com/help/idea/working-with-libraries.html).

## Accessing Libraries and Jars

Package
[libraries](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries)
provides functionality for working with project libraries and jars.

### How do I get a list of libraries available within a module?

To get the list of libraries, use `OrderEnumerator.forEachLibrary` method.
To clarify this, consider the following code snippet that illustrates how to output the list of libraries for the specified module:

```java
final List<String> libraryNames = new ArrayList<String>();
ModuleRootManager.getInstance(module).orderEntries().forEachLibrary(library -> {
  libraryNames.add(library.getName());
  return true;
});
Messages.showInfoMessage(StringUtil.join(libraryNames, "\n"), "Libraries in Module");
```

This sample code outputs a list of libraries for the `module` module.

### How do I get the library content?

The `Library` class provides the `getUrls` method you can use to get a list of source roots and classes the library includes. To clarify, consider the following code snippet:

```java
StringBuilder roots = new StringBuilder("The " + lib.getName() + " library includes:\n");
roots.append("Sources:\n");
for (String each : lib.getUrls(OrderRootType.SOURCES)) {
  roots.append(each).append("\n");
}
roots.append("Classes:\n");
for (String each : lib.getUrls(OrderRootType.CLASSES)) {
  strRoots.append(each).append("\n");
}
Messages.showInfoMessage(roots.toString(), "Library Info");
```

In this sample code, `lib` is of the [Library](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java) type.


### Checking Belonging to a Library

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


More details on libraries can be found in this
[code sample](https://github.com/JetBrains/intellij-sdk-docs/blob/master/code_samples/project_model/src/com/intellij/tutorials/project/model/LibrariesAction.java)
