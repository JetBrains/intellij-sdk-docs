<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# Library

<link-summary>Accessing and managing libraries.</link-summary>

<tldr>

**Product Help:** [Libraries](https://www.jetbrains.com/help/idea/library.html)

</tldr>

A library is an archive of compiled code (such as JAR files) that modules depend on.

A particular type of programmatically defined libraries is [Predefined Libraries](#predefined-libraries).

## Library Types

The IntelliJ Platform supports three types of libraries:

### Module Library

The library classes are visible only in this module and the library information is recorded in the module <path>.iml</path> file.

### Project Library

The library classes are visible within the project and the library information is recorded under <path>.idea/libraries</path> directory or in the project <path>.ipr</path> file.

### Global Library

The library information is recorded in the <path>applicationLibraries.xml</path> file in <path>\$USER_HOME\$/.IntelliJIdea/config/options</path> directory. Global libraries are similar to project libraries, but are visible for different projects.

## Working with Libraries

<include from="project.md" element-id="useWorkspaceModelAPI"/>

Package [`com.intellij.openapi.roots.libraries`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries) provides functionality for working with project libraries and JAR files.

### Getting a List of Libraries a Module Depends On

To get the list of libraries that a module depends on, use `OrderEnumerator.forEachLibrary` as follows.

```java
List<String> libraryNames = new ArrayList<>();
ModuleRootManager.getInstance(module).orderEntries().forEachLibrary(library -> {
  libraryNames.add(library.getName());
  return true;
});
Messages.showInfoMessage(StringUtil.join(libraryNames, "\n"), "Libraries in Module");
```

This sample code outputs a list of libraries that the given module depends on.

### Getting a List of All Libraries

To manage the lists of application and project libraries, use [`LibraryTable`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/LibraryTable.java).
The list of application-level library tables is accessed by calling [`LibraryTablesRegistrar.getLibraryTable()`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/LibraryTablesRegistrar.java), whereas the list of project-level library tables is accessed via `LibraryTablesRegistrar.getLibraryTable(Project)`.
Get the list of libraries in it via `LibraryTable.getLibraries()`.

To get the list of all module libraries defined in a given module, use API from [`OrderEntryUtil`](%gh-ic%/platform/projectModel-impl/src/com/intellij/openapi/roots/impl/OrderEntryUtil.java):

```java
OrderEntryUtil.getModuleLibraries(ModuleRootManager.getInstance(module));
```

### Getting the Library Content

[`Library`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/libraries/Library.java) provides the `getUrls()` method to get a list of source roots and classes the library includes.

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

For module-level libraries, use the simplified APIs from [`ModuleRootModificationUtil`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootModificationUtil.java) to add a library with a single API call.
An example of using these APIs can be found in the [project_model](%gh-sdk-samples-master%/project_model/src/main/java/org/intellij/sdk/project/model/ModificationAction.java) code sample.

<procedure title="Library Creation Steps">

* Get a [write action](threading_model.md#write-actions)
* Get the library table add the library to. Use one of the following, depending on the library level:
    * `LibraryTablesRegistrar.getInstance().getLibraryTable()`
    * `LibraryTablesRegistrar.getInstance().getLibraryTable(Project)`
    * `ModuleRootManager.getInstance(module).getModifiableModel().getModuleLibraryTable()`
* Create the library by calling `LibraryTable.createLibrary()`
* Add contents to the library (see below)
* For a module-level library, commit the modifiable model returned by `ModuleRootManager.getInstance(module).getModifiableModel()`.

</procedure>

### Adding Contents or Modifying a Library

<procedure title="Adding/Changing Library Roots">

* Get a [write action](threading_model.md#write-actions)
* Get a **modifiable model** for the library, using `Library.getModifiableModel()`
* Use methods such as `Library.ModifiableModel.addRoot()` to perform the necessary changes
* Commit the model using `Library.ModifiableModel.commit()`.

</procedure>

### Adding a Library Dependency to a Module

Use `ModuleRootModificationUtil.addDependency(Module, Library)` from under a [write action](threading_model.md#write-actions).

### Checking Belonging to a Library

The [`ProjectFileIndex`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) interface implements a number of methods that can used to check whether the specified file belongs to the project library classes or library sources.

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

See the [project_model](%gh-sdk-samples-master%/project_model/src/main/java/org/intellij/sdk/project/model/ProjectFileIndexSampleAction.java) sample plugin to see how the method mentioned above can be applied.

More details on libraries can be found in the [plugin_model](%gh-sdk-samples-master%/project_model/src/main/java/org/intellij/sdk/project/model/LibrariesAction.java) code sample.

## Predefined Libraries

[`AdditionalLibraryRootsProvider`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/AdditionalLibraryRootsProvider.java) registered in <include from="snippets.topic" element-id="ep"><var name="ep" value="com.intellij.additionalLibraryRootsProvider"/></include> allows
providing synthetic/predefined libraries ([`SyntheticLibrary`](%gh-ic%/platform/projectModel-api/src/com/intellij/openapi/roots/SyntheticLibrary.java)) in a project without exposing them in the model.
By default, they're also hidden from UI.
