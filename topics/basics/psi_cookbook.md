<!-- Copyright 2000-2023 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

# PSI Cookbook

<link-summary rel="excerpt"/>
<p id="excerpt">
This page gives recipes for the most common operations for working with the PSI (Program Structure Interface).
</p>

Unlike [Developing Custom Language Plugins](custom_language_support.md), it is about working with the PSI of existing languages (such as Java).

> See also the [](psi_performance.md) section.
>

## General

### How do I find a file if I know its name but don't know the path?

[`FilenameIndex.getFilesByName()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/FilenameIndex.java)

### How do I find where a particular PSI element is used?

[`ReferencesSearch.search()`](%gh-ic%/platform/indexing-api/src/com/intellij/psi/search/searches/ReferencesSearch.java)

### How do I rename a PSI element?

[`RefactoringFactory.createRename()`](%gh-ic%/platform/lang-api/src/com/intellij/refactoring/RefactoringFactory.java)

### How can I cause the PSI for a virtual file to be rebuilt?

[`FileContentUtil.reparseFiles()`](%gh-ic%/platform/analysis-api/src/com/intellij/util/FileContentUtil.java)

## Java Specific

> If your plugin depends on Java functionality and targets 2019.2 or later, please make sure to follow the steps from this [blog post](https://blog.jetbrains.com/platform/2019/06/java-functionality-extracted-as-a-plugin/).
> Also consider using [UAST](uast.md) if your plugin supports other JVM languages.
>
{style="note"}

### How do I find all inheritors of a class?

[`ClassInheritorsSearch.search()`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/searches/ClassInheritorsSearch.java)

### How do I find a class by qualified name?

[`JavaPsiFacade.findClass()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaPsiFacade.java)

### How do I find a class by short name?

[`PsiShortNamesCache.getClassesByName()`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/PsiShortNamesCache.java)

### How do I find a superclass of a Java class?

[`PsiClass.getSuperClass()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiClass.java)

### How do I get a reference to the containing package of a Java class?

```java
  PsiJavaFile javaFile = (PsiJavaFile)psiClass.getContainingFile();
  PsiPackage psiPackage = JavaPsiFacade.getInstance(project)
        .findPackage(javaFile.getPackageName());
```

or

[`PsiUtil.getPackageName()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/util/PsiUtil.java)

### How do I find the methods overriding a specific method?

[`OverridingMethodsSearch.search()`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/searches/OverridingMethodsSearch.java)

### How do I check the presence of a JVM library?

_2023.2_

Use dedicated (and heavily cached) methods from [`JavaLibraryUtil`](%gh-ic%/java/openapi/src/com/intellij/java/library/JavaLibraryUtil.java):

- `hasLibraryClass()` to check presence via known library class FQN
- `hasLibraryJar()` using Maven coordinates (for example, `io.micronaut:micronaut-core`).
