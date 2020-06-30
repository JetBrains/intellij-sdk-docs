---
title: PSI Cookbook
---
<!-- Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

This page gives a list of recipes for the most common operations for working with the PSI (Program Structure Interface). Unlike [Developing Custom Language Plugins](/reference_guide/custom_language_support.md), it talks about working with the PSI of existing languages (such as Java).

> **TIP** Please see also [Working with PSI efficiently](/reference_guide/performance/performance.md#working-with-psi-efficiently).

### How do I find a file if I know its name but don't know the path?

`FilenameIndex.getFilesByName()`

### How do I find where a particular PSI element is used?

`ReferencesSearch.search()`

### How do I rename a PSI element?

`RefactoringFactory.createRename()`

### How can I cause the PSI for a virtual file to be rebuilt?

`FileContentUtil.reparseFiles()`

## Java Specific

### How do I find all inheritors of a class?

`ClassInheritorsSearch.search()`

### How do I find a class by qualified name?

`JavaPsiFacade.findClass()`

### How do I find a class by short name?

`PsiShortNamesCache.getInstance().getClassesByName()`

### How do I find a superclass of a Java class?

`PsiClass.getSuperClass()`

### How do I get a reference to the containing package of a Java class?

```java
PsiJavaFile javaFile = (PsiJavaFile) psiClass.getContainingFile();
PsiPackage pkg = JavaPsiFacade.getInstance(project).findPackage(javaFile.getPackageName());
```        

or

`com.intellij.psi.util.PsiUtil.getPackageName()`

### How do I find the methods overriding a specific method?

`OverridingMethodsSearch.search()`
