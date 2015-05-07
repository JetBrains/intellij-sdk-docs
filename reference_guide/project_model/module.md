---
layout: editable
title: Module
---
A module is a discrete unit of functionality that can be run, tested, and debugged independently.
Modules includes such things as source code, build scripts, unit tests, deployment descriptors, etc.
In the project, each module can use a specific SDK or inherit SDK defined on the project level (see the SDK section later in this document).
A module can depend on other modules of the project.

## Getting Current Module
To determine a module in the project in question to which the specified virtual file belongs, use the
ProjectFileIndex.getModuleForFile(virtualFile) method:

```java
Module module = ProjectRootManager.getInstance(project).getFileIndex().getModuleForFile(virtualFile);
```


## Accessing Module Roots
Information about model roots can be accessed via the class
[ModuleRootManager.java](https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java),
for example, an instance of
[ModuleFileIndex.java](https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ModuleFileIndex.java)
can be obtained, which is analogical to the
[ProjectFileIndex.java](https://github.com/JetBrains/intellij-community/blob/master/platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java)
but in the scope of a module

```java
ModuleRootManager.getInstance(currentModule).getFileIndex()
```

### Checking Belonging to a Module Source Root

To check if a virtual file or directory belongs to a module source root, use the 
```ProjectFileIndex.getSourceRootForFile``` 
method. 
This method returns null if the file or directory does not belong to any source root of modules in the project.


```java
VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);
```
