[//]: # (title: PSI Cookbook)

<!-- Copyright 2000-2022 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file. -->

<excerpt rel="excerpt"/>
<p id="excerpt">
This page gives recipes for the most common operations for working with the PSI (Program Structure Interface).
</p>

Unlike [Developing Custom Language Plugins](custom_language_support.md), it is about working with the PSI of existing languages (such as Java).

> Please see also [](psi_performance.md).
>
{type="tip"}

## General

### How do I find a file if I know its name but don't know the path?

[`FilenameIndex.getFilesByName()`](upsource:///platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java)

### How do I find where a particular PSI element is used?

[`ReferencesSearch.search()`](upsource:///platform/indexing-api/src/com/intellij/psi/search/searches/ReferencesSearch.java)

### How do I rename a PSI element?

[`RefactoringFactory.createRename()`](upsource:///platform/lang-api/src/com/intellij/refactoring/RefactoringFactory.java)

### How can I cause the PSI for a virtual file to be rebuilt?

[`FileContentUtil.reparseFiles()`](upsource:///platform/analysis-api/src/com/intellij/util/FileContentUtil.java)

## Java Specific

> If your plugin depends on Java functionality and targets 2019.2 or later, please make sure to follow the steps from this [blog post](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/).
> Also consider using [UAST](uast.md) if your plugin supports other JVM languages.
>
{type="note"}

### How do I find all inheritors of a class?

[`ClassInheritorsSearch.search()`](upsource:///java/java-indexing-api/src/com/intellij/psi/search/searches/ClassInheritorsSearch.java)

### How do I find a class by qualified name?

[`JavaPsiFacade.findClass()`](upsource:///java/java-psi-api/src/com/intellij/psi/JavaPsiFacade.java)

### How do I find a class by short name?

[`PsiShortNamesCache.getClassesByName()`](upsource:///java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java)

### How do I find a superclass of a Java class?

[`PsiClass.getSuperClass()`](upsource:///java/java-psi-api/src/com/intellij/psi/PsiClass.java)

### How do I get a reference to the containing package of a Java class?

```java
PsiJavaFile javaFile = (PsiJavaFile) psiClass.getContainingFile();
PsiPackage psiPackage = JavaPsiFacade.getInstance(project)
        .findPackage(javaFile.getPackageName());
```

or

[`PsiUtil.getPackageName()`](upsource:///java/java-psi-api/src/com/intellij/psi/util/PsiUtil.java)

### How do I find the methods overriding a specific method?

[`OverridingMethodsSearch.search()`](upsource:///java/java-indexing-api/src/com/intellij/psi/search/searches/OverridingMethodsSearch.java)
