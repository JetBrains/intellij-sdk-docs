<!-- Copyright 2000-2025 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license. -->

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

> If your plugin depends on Java functionality, see [](plugin_compatibility.md#java).
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

[`PsiUtil.getPackageName()`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/util/PsiUtil.java)

### How do I find the methods overriding a specific method?

[`OverridingMethodsSearch.search()`](%gh-ic%/java/java-indexing-api/src/com/intellij/psi/search/searches/OverridingMethodsSearch.java)

### How do I create a new class/interface/enum/record in a given directory?

[`JavaDirectoryService`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaDirectoryService.java)

### How can I locate specific Java PSI elements within a class/method?

Extend [`JavaElementVisitor`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaElementVisitor.java)
or [`JavaRecursiveElementVisitor`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/JavaRecursiveElementVisitor.java) and override relevant method(s).
Pass it to `PsiElement.accept()` of the parent PSI element (for example, [`PsiClass`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiClass.java)
or [`PsiMethod`](%gh-ic%/java/java-psi-api/src/com/intellij/psi/PsiMethod.java)).

### How do I check the presence of a JVM library?

<primary-label ref="2023.2"/>

Use dedicated (and heavily cached) methods from [`JavaLibraryUtil`](%gh-ic%/java/openapi/src/com/intellij/java/library/JavaLibraryUtil.java):

- `hasLibraryClass()` to check presence via known library class FQN
- `hasLibraryJar()` using Maven coordinates (for example, `io.micronaut:micronaut-core`).
