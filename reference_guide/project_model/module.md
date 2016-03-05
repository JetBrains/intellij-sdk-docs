---
title: Module
---

A _module_ is a discrete unit of functionality that can be run, tested, and debugged independently. Modules includes such things as source code, build scripts, unit tests, deployment descriptors, etc.  In the project, each module can use a specific SDK or inherit the SDK defined on the project level (see the [SDK](sdk.md) section later in this guide). A module can depend on other modules of the project.

## Getting the current module

To determine the module in the project in question to which the specified [`VirtualFile`](upsource:///platform/core-api/src/com/intellij/openapi/vfs/VirtualFile.java) belongs use `ModuleUtilCore#findModuleForFile`. Use `ModuleUtilCore#findModuleForPsiElement` to find the module for a given `PsiElement`.

## Accessing module roots

Information about module roots can be accessed via the class [ModuleRootManager.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleRootManager.java), for example, an instance of [ModuleFileIndex.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ModuleFileIndex.java) can be obtained, which is similar to the [ProjectFileIndex.java](upsource:///platform/projectModel-api/src/com/intellij/openapi/roots/ProjectFileIndex.java) but in the scope of a module.

```java
ModuleRootManager.getInstance(currentModule).getFileIndex()
```

## Checking belonging to a module source root

To check if a virtual file or directory belongs to a module source root, use the `ProjectFileIndex.getSourceRootForFile` method. This method returns null if the file or directory does not belong to any source root of modules in the project.

```java
VirtualFile moduleSourceRoot = ProjectRootManager.getInstance(project).getFileIndex().getSourceRootForFile(virtualFileOrDirectory);
```
