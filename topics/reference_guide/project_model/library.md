[//]: # (title: Library)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

A library is an archive of compiled code (such as JAR files) that modules depend on.

The IntelliJ Platform supports three types of libraries:
* **Module Library**: the library classes are visible only in this module and the library information is recorded in the module <path>.iml</path> file.
* **Project Library**: the library classes are visible within the project and the library information is recorded under <path>.idea/libraries</path> directory or in the project <path>.ipr</path> file.
* **Global Library**: the library information is recorded in the <path>applicationLibraries.xml</path> file in <path>$USER_HOME$/.IntelliJIdea/config/options</path> directory. Global libraries are similar to project libraries, but are visible for different projects.

For more information about libraries, refer to [Libraries](https://www.jetbrains.com/help/idea/library.html).

A particular type of programmatically defined libraries is [Predefined Libraries](#predefined-libraries).

## Accessing Libraries and Jars
Package [`com.intellij.openapi.roots.libraries`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries) provides functionality for working with project libraries and JAR files.

### Getting a List of Libraries a Module Depends On
To get the list of libraries that a module depends on, use `OrderEnumerator.forEachLibrary` as follows.

```java
final List<String> libraryNames = new ArrayList<String>();
ModuleRootManager.getInstance(module).orderEntries().forEachLibrary(library -> {
  libraryNames.add(library.getName());
  return true;
});
Messages.showInfoMessage(StringUtil.join(libraryNames, "\n"), "Libraries in Module");
```

This sample code outputs a list of libraries that the given module depends on.

### Getting a List of All Libraries
To manage the lists of application and project libraries, use [`LibraryTable`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries/LibraryTable.java).
The list of application-level library tables is accessed by calling [`LibraryTablesRegistrar.getLibraryTable()`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries/LibraryTablesRegistrar.java), whereas the list of project-level library tables is accessed via `LibraryTablesRegistrar.getLibraryTable(Project)`.
Once you have a `LibraryTable`, you can get the libraries in it by calling `LibraryTable.getLibraries()`.

To get the list of all module libraries defined in a given module, use API from [`OrderEntryUtil`](upsource:///platform/projectModel-impl/src/com/intellij/openapi/roots/impl/OrderEntryUtil.java):

```java
OrderEntryUtil.getModuleLibraries(ModuleRootManager.getInstance(module));
```

### Getting the Library Content
[`Library`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java) provides the `getUrls()` method you can use to get a list of source roots and classes the library includes.
To clarify, consider the following code snippet:

```java
StringBuilder roots = new StringBuilder("The " + lib.getName() + " library includes:\n");
roots.append("Sources:\n");
for (String each : lib.getUrls(OrderRootType.SOURCES)) {
  roots.append(each).append("\n");
}
roots.append("Classes:\n");
for (String each : lib.getUrls(OrderRootType.CLASSES)) {
  roots.append(each).append("\n");
}
Messages.showInfoMessage(roots.toString(), "Library Info");
```

### Creating a Library
To create a library, perform the following steps:
  * Get a [write action](general_threading_rules.md#read-write-lock)
  * Obtain the library table to which you want to add the library. Use one of the following, depending on the library level:
      * `LibraryTablesRegistrar.getInstance().getLibraryTable()`
      * `LibraryTablesRegistrar.getInstance().getLibraryTable(Project)`
      * `ModuleRootManager.getInstance(module).getModifiableModel().getModuleLibraryTable()`
  * Create the library by calling `LibraryTable.createLibrary()`
  * Add contents to the library (see below)
  * For a module-level library, commit the modifiable model returned by `ModuleRootManager.getInstance(module).getModifiableModel()`.

For module-level libraries, you can also use simplified APIs in the [`ModuleRootModificationUtil`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) class to add a library with a single API call.
You can find an example of using these APIs in the [project_model](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/project_model/src/main/java/org/intellij/sdk/project/model/ModificationAction.java) code sample.

### Adding Contents or Modifying a Library
To add or change the roots of a library, you need to perform the following steps:
  * Get a [write action](general_threading_rules.md#read-write-lock)
  * Get a **modifiable model** for the library, using `Library.getModifiableModel()`
  * Use methods such as `Library.ModifiableModel.addRoot()` to perform the necessary changes
  * Commit the model using `Library.ModifiableModel.commit()`.

### Adding a Library Dependency to a Module
Use `ModuleRootModificationUtil.addDependency(Module, Library)` from under a [write action](general_threading_rules.md#read-write-lock).

### Checking Belonging to a Library
The [`ProjectFileIndex`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface implements a number of methods you can use to check whether the specified file belongs to the project library classes or library sources.
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

See the [project_model](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/project_model/src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java) to see how the method mentioned above can be applied.

More details on libraries can be found in the [plugin_model](https://github.com/JetBrains/intellij-sdk-code-samples/blob/main/project_model/src/main/java/org/intellij/sdk/project/model/LibrariesAction.java) code sample.

## Predefined Libraries
EP: `com.intellij.additionalLibraryRootsProvider`

[`AdditionalLibraryRootsProvider`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java)
Allows providing synthetic/predefined libraries ([`SyntheticLibrary`](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/SyntheticLibrary.java)) in a project without exposing them in the model.
By default, they're also hidden from UI.
